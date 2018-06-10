package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.env.ISourceMethod;
import org.junit.internal.runners.model.EachTestNotifier;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Atendimento;
import model.AtendimentoHasPergunta;
import model.AtendimentoHasResposta;
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
	public String mensagemRespostaNaoEncontrada = "Desculpe, não encontrei nada correspondente.";
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

	public Date data_atual() {
		return new Date();
	}

	public void salvar_atendimento(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

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


	public void salvar_mensagem_banco(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		if(url[2].equals("pergunta")) {
			System.out.println("pergunta");
			Pergunta pergunta = new Pergunta();
			pergunta.setDescricao(request.getParameter("mensagem"));
			pergunta.setAtivo(1);
			pergunta.setData_atualizacao(data_atual());
			pergunta.setData_criacao(data_atual());

			PerguntaService ps = new PerguntaService();
			int idPergunta;
			idPergunta = ps.criar(pergunta);

			Integer.parseInt(url[3]);
			AtendimentoHasPergunta atendimento_has_pergunta = new AtendimentoHasPergunta();
			atendimento_has_pergunta.setId_pergunta(idPergunta);
			atendimento_has_pergunta.setId_atendimento(Integer.parseInt(url[3]));
			atendimento_has_pergunta.setData_atualizacao(data_atual());
			atendimento_has_pergunta.setData_criacao(data_atual());

			AtendimentoHasPerguntaService ahps = new AtendimentoHasPerguntaService();

			ahps.criar(atendimento_has_pergunta);
		} else {
			System.out.println("resposta");
			Resposta resposta = new Resposta();
			resposta.setDescricao(request.getParameter("mensagem"));
			resposta.setAtivo(1);
			resposta.setData_criacao(data_atual());

			RespostaService rs = new RespostaService();
			int idResposta = rs.criar(resposta);

			AtendimentoHasResposta atendimento_has_resposta = new AtendimentoHasResposta();
			atendimento_has_resposta.setId_resposta(idResposta);
			atendimento_has_resposta.setId_atendimento(Integer.parseInt(request.getParameter("id_atendimento")));
			atendimento_has_resposta.setData_atualizacao(data_atual());
			atendimento_has_resposta.setData_criacao(data_atual());

			AtendimentoHasRespostaService ahps = new AtendimentoHasRespostaService();
			ahps.criar(atendimento_has_resposta);
		}

		//JSON
		PrintWriter out = response.getWriter();
		JsonObject jobj = new JsonObject();
		jobj.addProperty("status", "1");
		out.write(jobj.toString());
	}


	public void obter_resposta_ajax (String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException  {
		String mensagem_usuario = request.getParameter("mensagem_usuario");

		String[] palavras_chave_mensagem = transformar_string_palavras_chave(mensagem_usuario);

		ArrayList <PalavraChave> palavra_chave_perguntas = new ArrayList <PalavraChave> ();

		/*
		 * Obtendo todas as perguntas com as palavras-chaves da mensagem perguntada. 
		 */ 
		for(int i =0; i < palavras_chave_mensagem.length; i++) {
			PalavraChaveService pcs = new PalavraChaveService();
			ArrayList<PalavraChave> palavra_chave_cadastro = pcs.obter_palavra_chave_com_string(palavras_chave_mensagem[i]);

			if(palavra_chave_cadastro.size() == 0) {
				continue;
			}

			for(PalavraChave palavra_chave : palavra_chave_cadastro) {
				palavra_chave_perguntas.add(pcs.carregar_cadastro_completo(palavra_chave.getId(), response));
			}
		}



		/*
		 * Verificando a pergunta com maior peso a partir das palavras-chaves
		 */
		for (PalavraChave palavra_chave_pergunta : palavra_chave_perguntas) {
			for(PalavraChaveHasPergunta palavra_chave_has_pergunta : palavra_chave_pergunta.palavraChaveHasPergunta) {
				if(palavra_chave_has_pergunta.pergunta == null) {
					continue;
				}

				/* ==== PESOS DE DEFINIÇÃO DE MELHOR RESPOSTA =====*/
				int peso = 0;

				//1) Número de ocorrências 
				peso += obterPesoComparacaoString(
						palavra_chave_has_pergunta.pergunta.getDescricao(),
						palavras_chave_mensagem
						);

				//2) Respostas satisfatórias
				if(palavra_chave_has_pergunta.pergunta.perguntaHasResposta != null && palavra_chave_has_pergunta.pergunta.perguntaHasResposta.getPontuacao() != 0) {
					peso += palavra_chave_has_pergunta.pergunta.perguntaHasResposta.getPontuacao();
				}


				//3) Palavras-chaves contém no tópico principal
				if(palavra_chave_has_pergunta.pergunta.perguntaHasResposta != null && palavra_chave_has_pergunta.pergunta.perguntaHasResposta.topico != null) {
					peso += obterPesoComparacaoString(
							palavra_chave_has_pergunta.pergunta.perguntaHasResposta.topico.getNome(),
							palavras_chave_mensagem
							);
				}

				palavra_chave_has_pergunta.pergunta.peso_pergunta = peso; 
			}
		}

		/*
		 * Verificando qual pergunta_has_resposta tem maior peso de provável resposta.
		 */
		Pergunta respostaFinal = new Pergunta();
		int maior_peso = -1;
		for(PalavraChave palavra_chave_pergunta : palavra_chave_perguntas) {
			for(PalavraChaveHasPergunta palavra_chave_has_pergunta : palavra_chave_pergunta.palavraChaveHasPergunta) {
				if(palavra_chave_has_pergunta.pergunta.perguntaHasResposta == null ) {

					continue;

				} else if (palavra_chave_has_pergunta.pergunta.perguntaHasResposta.resposta == null) {

					continue;

				} else if(palavra_chave_has_pergunta.pergunta.perguntaHasResposta.resposta.getDescricao().equals("")) {

					continue;
				}

				if(palavra_chave_has_pergunta.pergunta.perguntaHasResposta.getPontuacao() > maior_peso) {
					respostaFinal = palavra_chave_has_pergunta.pergunta;
				}
			}
		}

		//$this->salvar_pergunta_usuario_externo($mensagem_usuario);

		if(respostaFinal.getId() == 0) {
			PerguntaHasResposta perguntaHasResposta = new PerguntaHasResposta();
			perguntaHasResposta.setId_resposta(-1);
			respostaFinal.perguntaHasResposta = perguntaHasResposta;
			Resposta resposta = new Resposta();
			resposta.setDescricao(this.mensagemRespostaNaoEncontrada);
			respostaFinal.perguntaHasResposta.resposta = resposta;
		}

		Json.jsonEncode(respostaFinal, response);
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
			results += p.toString(); //if you implement toString() for Dog then it will be added here
		}
		System.out.println(results);
	}

	public String[] transformar_string_palavras_chave(String string) {
		String string_filtrada = escapar_caracteres_notacao(string);

		String palavras_chave[] = string_filtrada.split(" ");
		return palavras_chave;
	}

	public String escapar_caracteres_notacao(String string) {

		string = string.replace("(", "");
		string = string.replace(")", "");
		string = string.replace(".", "");
		string = string.replace("?", "");
		string = string.replace("!", "");

		return string;
	}

	public void finalizar_atendimento(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//atendimento
		boolean status = false;

		Atendimento atendimento = new Atendimento();
		atendimento.setStatus("finalizado");
		atendimento.setData_finalizacao(data_atual());

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

	public void resposta_satisfatoria(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
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

	public void p_adicionar_palavra_chave_pergunta(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {

		String topicos_principal = request.getParameter("nome");
		Topico topico = new Topico();
		topico.setNome(topicos_principal);
		topico.setAtivo(1);

		if(topicos_principal.equals("")) {
			// alerta('O tópico está vazio.', 'erro');
			// copiar função alerta do php e colocar no java utils
		}

		TopicoService ts = new TopicoService();
		if(ts.verificar_nome_topico_existente(topicos_principal)) {
			/* alerta('O nome do tópico já existe.', 'erro');
            voltar_atras();*/
		}
		//criar a array vazio, depois converter String em array 
		int id_topico = ts.criar(topico);

		ArrayList<Pergunta> Perguntas = new ArrayList<>() ;
		ArrayList<Resposta> Respostas = new ArrayList<>();


		String respostasString = request.getParameter("respostas");
		String perguntasString = request.getParameter("perguntas");

		if(perguntasString.isEmpty()) {
			/*alerta('As perguntas estão vazias.', 'erro');
            voltar_atras();*/
		}


		String[] palavras_chaves_resposta;
		String[] palavras_chaves_pergunta;

		for (Resposta resposta : Respostas) {

			Resposta resposta_cadastro = new Resposta();
			resposta_cadastro.setDescricao(resposta.getDescricao());
			resposta_cadastro.setAtivo(1);

			RespostaService rs = new RespostaService();
			int id_resposta = rs.criar(resposta_cadastro);


			Pergunta pergunta_cadastro = new Pergunta();
			pergunta_cadastro.setDescricao(resposta.getDescricao());
			pergunta_cadastro.setAtivo(1);

			PerguntaService ps = new PerguntaService();
			int id_pergunta = ps.criar(pergunta_cadastro);
			
			PerguntaHasResposta  pergunta_has_resposta = new PerguntaHasResposta();
			pergunta_has_resposta.setId(id_pergunta);
			pergunta_has_resposta.setId(id_resposta);
			pergunta_has_resposta.setPontuacao(0);
			pergunta_has_resposta.setId_topico(id_topico);
			
			PerguntaHasRespostaService perguntaHasRespostaService = new PerguntaHasRespostaService();
			perguntaHasRespostaService.criar(pergunta_has_resposta);
			PalavraChave palavraChave = new PalavraChave();
			PalavraChaveService palavraChaveService = new PalavraChaveService();
			int id_palavra_chave = -1;
			
			//Quebrando a resposta em várias palavras chaves.
			palavras_chaves_resposta = transformar_string_palavras_chave(resposta.getDescricao());
			for (String palavra_chave_resposta : palavras_chaves_resposta) {
				
				
				if(palavraChaveService.verificar_ja_existe_palavra_chave(palavra_chave_resposta)) {
					id_palavra_chave = palavraChaveService.carregar_id("WHERE NOME = '" + palavra_chave_resposta + "' LIMIT 1");
				}else {
					PalavraChave palavra_chave_principal = new PalavraChave();
					palavra_chave_principal.setNome(palavra_chave_resposta);
					palavra_chave_principal.setAtivo(1);
					id_palavra_chave = palavraChaveService.criar(palavra_chave_principal);
				}
				
				PalavraChaveHasResposta palavra_chave_has_resposta = new PalavraChaveHasResposta();
				palavra_chave_has_resposta.setId_resposta(id_resposta);
				palavra_chave_has_resposta.setId_palavra_chave(id_palavra_chave);
				palavra_chave_has_resposta.setPont_respsota(0);
				
				PalavraChaveHasRespostaService palavraChaveHasRespostaService = new PalavraChaveHasRespostaService();
				palavraChaveHasRespostaService.criar(palavra_chave_has_resposta);
			}
			
			//Quebrando a perguntas em várias palavras chaves
			//Lucas arrumar a linha abaixo em php era $palavras_chaves_pergunta = $this->transformar_string_palavras_chave($perguntas[$key]['pergunta'])
			palavras_chaves_pergunta = transformar_string_palavras_chave("pergunta");
			for (String palavra_chave_pergunta : palavras_chaves_pergunta) {
				
				if(palavraChaveService.verificar_ja_existe_palavra_chave(palavra_chave_pergunta)) {
					id_palavra_chave = palavraChaveService.carregar_id("WHERE NOME = '" + palavra_chave_pergunta + "' LIMIT 1");
				}else {
					PalavraChave palavra_chave_principal = new PalavraChave();
					palavra_chave_principal.setNome(palavra_chave_pergunta);
					palavra_chave_principal.setAtivo(1);
					id_palavra_chave = palavraChaveService.criar(palavra_chave_principal);
				}
				
				PalavraChaveHasPergunta palavra_chave_has_pergunta = new PalavraChaveHasPergunta();
				palavra_chave_has_pergunta.setId_pergunta(id_pergunta);
				palavra_chave_has_pergunta.setId_palavra_chave(id_palavra_chave);
				
				PalavraChaveHasPerguntaService palavraChaveHasPerguntaService = new PalavraChaveHasPerguntaService();
				palavraChaveHasPerguntaService.criar(palavra_chave_has_pergunta);
			}
		}
		
		//alerta('Tópico cadastrado com sucesso', 'success'); 
		//return redirect()->route('chatbot.listar_topicos');
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/ChatBot/listar_topico.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
