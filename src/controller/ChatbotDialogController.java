package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.env.ISourceMethod;
//import org.junit.internal.runners.model.EachTestNotifier;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Atendimento;
import model.AtendimentoHasPergunta;
import model.AtendimentoHasResposta;
import model.Chat;
import model.Cliente;
import model.PalavraChave;
import model.PalavraChaveHasPergunta;
import model.PalavraChaveHasResposta;
import model.Pergunta;
import model.PerguntaHasResposta;
import model.PerguntaHasResposta;
import model.Resposta;
import model.Topico;
import service.AtendimentoHasPerguntaService;
import service.AtendimentoHasRespostaService;
import service.AtendimentoService;
import service.ClienteService;
import service.PalavraChaveHasPerguntaService;
import service.PalavraChaveHasRespostaService;
import service.PalavraChaveService;
import service.PerguntaHasRespostaService;
import service.PerguntaService;
import service.RespostaService;

import service.TopicoService;
import utils.Debug;

import utils.Json;


import model.Topico;
/**
 * Servlet implementation class ChatbotDialogController
 */
@WebServlet("/ChatbotDialogController")
public class ChatbotDialogController extends HttpServlet {
        private static final long serialVersionUID = 1L;
        public String mensagemRespostaNaoEncontrada = "Desculpe, não encontrei algo correspondente.";
        /**
         * @see HttpServlet#HttpServlet()
         */
        public ChatbotDialogController() {
                super();
                // TODO Auto-generated constructor stub
        }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                response.getWriter().append("Served at: ").append(request.getContextPath());
        }

        public Date dataAtual() {
                return new Date();
        }

        public void salvarAtendimento(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

                PrintWriter out = response.getWriter();

                //cliente
                Cliente cliente = new Cliente();
                cliente.setNome_cliente(request.getParameter("nome"));
                cliente.setEmail_cliente(request.getParameter("email")); 
                cliente.setAtivo(1);
                ClienteService cs = new ClienteService();
                int idCliente = cs.criar(cliente);

                //atendimento
                Atendimento atendimento = new Atendimento();
                atendimento.setAtivo(1);
                atendimento.setStatus("Não finalizado");
                atendimento.setId_cliente(idCliente);
                atendimento.setData_atualizacao(new Date());
                atendimento.setData_criacao(new Date());
                atendimento.setQtd_tentativa(0);

                AtendimentoService as = new AtendimentoService();
                int idAtendimento = as.criar(atendimento);

                //JSON
                JsonObject jobj = new JsonObject();
                jobj.add("atendimento", new Gson().toJsonTree(atendimento));
                jobj.addProperty("status", "1");
                out.write(jobj.toString());

        }


        public void salvarMensagemBanco(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

                if(url[2].equals("pergunta")) {
                        Pergunta pergunta = new Pergunta();
                        pergunta.setDescricao(request.getParameter("dados_mensagem[mensagem]"));
                        pergunta.setAtivo(1);
                        pergunta.setData_atualizacao(dataAtual());
                        pergunta.setData_criacao(dataAtual());

                        PerguntaService ps = new PerguntaService();
                        int idPergunta;
                        idPergunta = ps.criar(pergunta);

                        Integer.parseInt(url[3]);
                        AtendimentoHasPergunta atendimentoHasPergunta = new AtendimentoHasPergunta();
                        atendimentoHasPergunta.setId_pergunta(idPergunta);
                        atendimentoHasPergunta.setId_atendimento(Integer.parseInt(url[3]));
                        atendimentoHasPergunta.setData_atualizacao(dataAtual());
                        atendimentoHasPergunta.setData_criacao(dataAtual());

                        AtendimentoHasPerguntaService ahps = new AtendimentoHasPerguntaService();

                        ahps.criar(atendimentoHasPergunta);
                } else {
                        Resposta resposta = new Resposta();
                        resposta.setDescricao(request.getParameter("dados_mensagem[mensagem]"));
                        resposta.setAtivo(1);
                        resposta.setData_criacao(dataAtual());
                        resposta.setData_atualizacao(dataAtual());
                        RespostaService rs = new RespostaService();
                        int idResposta = rs.criar(resposta);

                        AtendimentoHasResposta atendimentoHasResposta = new AtendimentoHasResposta();
                        atendimentoHasResposta.setId_resposta(idResposta);
                        atendimentoHasResposta.setId_atendimento(Integer.parseInt(url[3]));
                        atendimentoHasResposta.setData_atualizacao(dataAtual());
                        atendimentoHasResposta.setData_criacao(dataAtual());

                        AtendimentoHasRespostaService ahps = new AtendimentoHasRespostaService();
                        ahps.criar(atendimentoHasResposta);
                }

                //JSON
                PrintWriter out = response.getWriter();
                JsonObject jobj = new JsonObject();
                jobj.addProperty("status", "1");
                out.write(jobj.toString());
        }


        public void obterRespostaAjax (String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException  {
                String mensagemUsuario = request.getParameter("mensagem_usuario");

                String[] palavrasChaveMensagem = trasformarStringPalavraChave(mensagemUsuario);

                ArrayList <PalavraChave> palavraChavePerguntas = new ArrayList <PalavraChave> ();

                /*
                 * Obtendo todas as perguntas com as palavras-chaves da mensagem perguntada. 
                 */ 
                for(int i =0; i < palavrasChaveMensagem.length; i++) {
                        PalavraChaveService pcs = new PalavraChaveService();
                        ArrayList<PalavraChave> palavraChaveCadastro = pcs.obterPalavraChaveComString(palavrasChaveMensagem[i]);

                        if(palavraChaveCadastro.size() == 0) {
                                continue;
                        }

                        for(PalavraChave palavra_chave : palavraChaveCadastro) {
                                palavraChavePerguntas.add(pcs.carregar_cadastro_completo(palavra_chave.getId(), response));
                        }
                }

                /*
                 * Verificando a pergunta com maior peso a partir das palavras-chaves
                 */
                for (PalavraChave palavraChavePergunta : palavraChavePerguntas) {
                        for(PalavraChaveHasPergunta palavraChaveHasPergunta : palavraChavePergunta.palavraChaveHasPergunta) {
                                if(palavraChaveHasPergunta.pergunta == null) {
                                        continue;
                                }

                                /* ==== PESOS DE DEFINIÇÃO DE MELHOR RESPOSTA =====*/
                                int peso = 0;

                                //1) Número de ocorrências 
                                peso += obterPesoComparacaoString(
                                                palavraChaveHasPergunta.pergunta.getDescricao(),
                                                palavrasChaveMensagem
                                                );

                                //2) Respostas satisfatórias
                                if(palavraChaveHasPergunta.pergunta.perguntaHasResposta != null && palavraChaveHasPergunta.pergunta.perguntaHasResposta.getPontuacao() != 0) {
                                        peso += palavraChaveHasPergunta.pergunta.perguntaHasResposta.getPontuacao();
                                }


                                //3) Palavras-chaves contém no tópico principal
                                if(palavraChaveHasPergunta.pergunta.perguntaHasResposta != null && palavraChaveHasPergunta.pergunta.perguntaHasResposta.topico != null) {
                                        peso += obterPesoComparacaoString(
                                                        palavraChaveHasPergunta.pergunta.perguntaHasResposta.topico.getNome(),
                                                        palavrasChaveMensagem
                                                        );
                                }

                                palavraChaveHasPergunta.pergunta.peso_pergunta = peso; 
                        }
                }
                
                
                /*
                 * Verificando qual pergunta_has_resposta tem maior peso de provável resposta.
                 */
                Pergunta respostaFinal = new Pergunta();
                ArrayList<Pergunta> respostasFinais = new ArrayList<Pergunta>();
                
                int maiorPeso = -1;
                for(PalavraChave palavraChavePergunta : palavraChavePerguntas) {
                        for(PalavraChaveHasPergunta palavraChaveHasPergunta : palavraChavePergunta.palavraChaveHasPergunta) {
                                if(palavraChaveHasPergunta.pergunta == null ) {

                                        continue;
                                } else if(palavraChaveHasPergunta.pergunta.perguntaHasResposta == null ) {

                                        continue;

                                } else if (palavraChaveHasPergunta.pergunta.perguntaHasResposta.resposta == null) {

                                        continue;

                                } else if(palavraChaveHasPergunta.pergunta.perguntaHasResposta.resposta.getDescricao().equals("")) {

                                        continue;
                                }

                                if(palavraChaveHasPergunta.pergunta.perguntaHasResposta.getPontuacao() > maiorPeso) {
                                        boolean jaExiste = false;
                                        
                                        //1) Retirando redundância e deixando as respostas integras
                                        for(Pergunta respostaFinalBusca : respostasFinais) {
                                                if(palavraChaveHasPergunta.pergunta.getId() == respostaFinalBusca.getId()) {
                                                        jaExiste = true;
                                                }
                                        }
                                        
                                        if(!jaExiste) {
                                                respostasFinais.add(palavraChaveHasPergunta.pergunta);
                                        }
                                        
                                }
                        }
                }
                
                if(respostaFinal.getId() == 0) {
                        
                }
                
                Json.jsonEncode(respostasFinais, response);
                
        }

        public void salvarPerguntaUsuarioExterno(String mensagemUsuario) {
                Pergunta perguntaCadastro = new Pergunta();
                perguntaCadastro.setDescricao(mensagemUsuario);
                perguntaCadastro.setAtivo(1);
                perguntaCadastro.setUsuario_externo(1);
                PerguntaService ps = new PerguntaService();
                int idPergunta = ps.criar(perguntaCadastro);
                System.out.println(idPergunta);
                PalavraChaveService palavraChaveService = new PalavraChaveService();

                //Quebrando a pergunta em várias palavras chaves.
                String[] palavrasChavesPergunta = trasformarStringPalavraChave(mensagemUsuario);
                for (int keyPergunta = 0; keyPergunta < palavrasChavesPergunta.length; keyPergunta++) {
                        int idPalavraChaveRetorno = -1;
                        if(palavraChaveService.verificarJaExistePalavraChave(palavrasChavesPergunta[keyPergunta])) {
                                idPalavraChaveRetorno = palavraChaveService.carregar_id("WHERE NOME = '" + palavrasChavesPergunta[keyPergunta] + "' LIMIT 1");
                        }else {
                                PalavraChave palavraChavePrincipal = new PalavraChave();
                                palavraChavePrincipal.setNome(palavrasChavesPergunta[keyPergunta]);
                                palavraChavePrincipal.setAtivo(1);
                                idPalavraChaveRetorno = palavraChaveService.criar(palavraChavePrincipal);
                        }

                        if(idPalavraChaveRetorno == -1) {
                                System.out.println("Erro ao adicionar palavra chave de perguntas");
                        }

                        PalavraChaveHasPergunta palavraChaveHasPergunta = new PalavraChaveHasPergunta();
                        palavraChaveHasPergunta.setId_pergunta(idPergunta);
                        palavraChaveHasPergunta.setId_palavra_chave(idPalavraChaveRetorno);

                        PalavraChaveHasPerguntaService palavraChaveHasPerguntaService = new PalavraChaveHasPerguntaService();
                        palavraChaveHasPerguntaService.criar(palavraChaveHasPergunta);
                }
        }

        public int obterPesoComparacaoString(String texto, String[] possiveis_ocorrencias) {
                /*
                 * Pesos:
                 * Ocorrências no texto: 1 ponto;
                 */

                int peso = 0;

                for (int i = 0; i < possiveis_ocorrencias.length; i++) {
                        //ocorrencias = substr_count(texto, possivel_ocorrencia);
                        if(texto.indexOf(possiveis_ocorrencias[i]) !=-1) {
                                peso++;
                        }
                }

                return peso;
        }

        public void ArrayListString(ArrayList<PalavraChave> arrayList) {
                String results = "+";
                for(PalavraChave p : arrayList) {
                        results += p.toString(); 
                }
                System.out.println(results);
        }

        public String[] trasformarStringPalavraChave(String string) {
                String stringFiltrada = escaparCaracteresNotacao(string);

                String palavras_chave[] = stringFiltrada.split(" ");
                return palavras_chave;
        }

        public String escaparCaracteresNotacao(String string) {

                string = string.replace("(", "");
                string = string.replace(")", "");
                string = string.replace(".", "");
                string = string.replace("?", "");
                string = string.replace("!", "");

                return string;
        }

        public void finalizarAtendimento(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
                PrintWriter out = response.getWriter();
                //atendimento
                boolean status = false;

                Atendimento atendimento = new Atendimento();
                atendimento.setStatus("finalizado");
                atendimento.setData_finalizacao(dataAtual());

                atendimento.setId(Integer.parseInt(request.getParameter("id_atendimento")));

                if(request.getParameter("id_atendimento").equals("") == false) {

                        AtendimentoService as = new AtendimentoService();
                        as.finalizar_atendimento(atendimento);
                        status = true;

                }

                //JSON
                JsonObject jobj = new JsonObject();
                jobj.add("atendimento", new Gson().toJsonTree(atendimento));
                jobj.addProperty("status", "1");
                out.write(jobj.toString());

        }


        public void atualizarStatusAtendimento(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
                PrintWriter out = response.getWriter();
                //atendimento
                boolean status = false;
                if(request.getParameter("status").equals("") != true) {
                        Atendimento atendimento = new Atendimento();
                        atendimento.setStatus(request.getParameter("status"));
                        atendimento.setId(Integer.parseInt(request.getParameter("id_atendimento")));

                        AtendimentoService as = new AtendimentoService();
                        as.query("UPDATE atendimento SET STATUS = '" + atendimento.getStatus() + "' WHERE ID = " + atendimento.getId());
                        status = true;

                }

                //JSON
                JsonObject jobj = new JsonObject();
                jobj.addProperty("status", "1");
                out.write(jobj.toString());

        }

        public void respostaSatisfatoria(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
                boolean retorno = false;

                if(request.getParameter("id_pergunta_resposta") != null) {

                        int id_pergunta_resposta = Integer.parseInt(request.getParameter("id_pergunta_resposta"));
                        PerguntaHasRespostaService phr = new PerguntaHasRespostaService();
                        phr.aumentarPontuacao(id_pergunta_resposta);

                        retorno = true;
                } 

                //JSON
                PrintWriter out = response.getWriter();
                JsonObject jobj = new JsonObject();
                jobj.addProperty("status", retorno);
                out.write(jobj.toString());
        }

        public void carregarMensagensChat(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
                boolean retorno = true;

                AtendimentoService atendimentoService = new AtendimentoService();
                ArrayList<Atendimento> atendimentos = atendimentoService.carregarCadastroCompleto("WHERE ID = " + url[2], response);

                if(atendimentos.isEmpty()) {
                        retorno = false;
                        return;
                }

                Atendimento atendimento = atendimentos.get(0);

                List<Chat> chat = new ArrayList<Chat>();

                if(atendimento.atendimentoHasPergunta != null) {
                        for(AtendimentoHasPergunta atendimentoHasPergunta : atendimento.atendimentoHasPergunta) {
                                Chat chatPergunta = new Chat();
                                chatPergunta.data_criacao = atendimentoHasPergunta.getData_criacao();
                                chatPergunta.pergunta = atendimentoHasPergunta.pergunta;
                                chatPergunta.id_atendimento = atendimentoHasPergunta.getId_atendimento();
                                chat.add(chatPergunta);
                        }
                }

                if(atendimento.atendimentoHasResposta != null) {
                        for(AtendimentoHasResposta atendimentoHasResposta : atendimento.atendimentoHasResposta) {
                                Chat chatResposta = new Chat();
                                chatResposta.data_criacao = atendimentoHasResposta.getData_criacao();
                                chatResposta.resposta = atendimentoHasResposta.resposta;
                                chatResposta.id_atendimento = atendimentoHasResposta.getId_atendimento();
                                chat.add(chatResposta);
                        }
                }

                if(!chat.isEmpty()) {
                        Collections.sort(chat, new Comparator<Chat>() {
                                @Override
                                public int compare(Chat o1, Chat o2) {
                                        if (((Chat) o1).getData_criacao() == null || ((Chat) o1).getData_criacao() == null)
                                                return 0;
                                        return o1.getData_criacao().compareTo(o2.getData_criacao());
                                }
                        });
                }

                atendimento.chat = chat;



                //JSON
                PrintWriter out = response.getWriter();
                JsonObject jobj = new JsonObject();
                jobj.addProperty("status", retorno);
                jobj.add("atendimento", new Gson().toJsonTree(atendimento));
                out.write(jobj.toString());
        }


        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}
