package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import model.PalavraChave;
import model.PalavraChaveHasPergunta;
import model.PalavraChaveHasResposta;
import model.Pergunta;
import model.PerguntaHasResposta;
import model.Resposta;
import model.Topico;
import service.PalavraChaveHasPerguntaService;
import service.PalavraChaveHasRespostaService;
import service.PalavraChaveService;
import service.PerguntaHasRespostaService;
import service.PerguntaService;
import service.RespostaService;
import service.TopicoService;
import utils.Alerta;
import utils.Debug;
import utils.ResultadosAjax;

/**
 * Servlet Filter implementation class ChatbotController
 */
@WebFilter("/ChatbotController")
public class ChatbotController implements Filter {

	/**
	 * Default constructor. 
	 */
	public ChatbotController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void listar_topicos(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/listar_topicos.jsp");

		dispatcher.forward(request, response);
	}

	public void adicionar_palavra_chave_pergunta(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/adicionar_palavra_chave_pergunta.jsp");

		dispatcher.forward(request, response);
	}
	
	public void editar_palavra_chave_pergunta(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/adicionar_palavra_chave_pergunta.jsp");

		dispatcher.forward(request, response);
	}

	public void listar_topicos_ajax(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		TopicoService topicoService = new TopicoService();
		ArrayList<Topico> topicos = topicoService.carregarCadastro("WHERE ATIVO = 1 ORDER BY DATA_CRIACAO DESC");
		ArrayList<ArrayList> aaData = new ArrayList<ArrayList>();
		for(Topico topico : topicos) {
			String botao_editar = " <a href='/ChatbotProjetoSJWebJava/chatbot/editar_palavra_chave_pergunta/" + topico.getId() + "' class='btn btn-default'><i class='fas fa-pencil-alt'></i> Editar</a>"; 
			String botao_excluir = " <a href='/ChatbotProjetoSJWebJava/chatbot/excluir_palavra_chave_pergunta/" + topico.getId() + "' class='btn btn-danger'><i class='fas fa-times'></i> Excluir</a>";                                  

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			ArrayList<String> data = new ArrayList<String>();
			data.add(String.valueOf(topico.getId()));
			data.add(topico.getNome());
			data.add(sdf.format(topico.getData_criacao()));
			data.add(botao_editar + botao_excluir);

			aaData.add(data);

		}

		ResultadosAjax resultado = new ResultadosAjax();
		resultado.aaData = aaData;
		resultado.iTotalDisplayRecords = aaData.size();
		resultado.iTotalRecords = aaData.size();
		resultado.sEcho = 1;
		Debug.debug(resultado, response);

	}

	public void voltarAdicionarPalavraChavePergunta(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/adicionar_palavra_chave_pergunta.jsp");

		dispatcher.forward(request, response);
	}

	public void p_adicionar_palavra_chave_pergunta_ajax(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {

		String topicos_principal = request.getParameter("topico");
		Topico topico = new Topico();
		topico.setNome(topicos_principal);
		topico.setAtivo(1);

		if(topicos_principal.equals("")) {
			Alerta.alerta("O tópico está vazio.", "erro", request);
			voltarAdicionarPalavraChavePergunta(request, response);
		}

		TopicoService ts = new TopicoService();
		if(ts.verificar_nome_topico_existente(topicos_principal)) {
			Alerta.alerta("O nome do tópico já existe.", "erro", request);
			voltarAdicionarPalavraChavePergunta(request, response);
		}

		int id_topico = ts.criar(topico);

		String[] perguntas = request.getParameterValues("perguntas");
		String[] respostas = request.getParameterValues("respostas");

		if(perguntas.length == 0) {
			Alerta.alerta("As perguntas estão vazias.", "erro", request);
			voltarAdicionarPalavraChavePergunta(request, response);
		}

		String[] palavrasChavesResposta;
		String[] palavrasChavesPergunta;
		for (int key = 0; key < respostas.length; key++) {

			Resposta resposta_cadastro = new Resposta();
			resposta_cadastro.setDescricao(respostas[key]);
			resposta_cadastro.setAtivo(1);
			RespostaService rs = new RespostaService();
			int id_resposta = rs.criar(resposta_cadastro);

			Pergunta pergunta_cadastro = new Pergunta();
			pergunta_cadastro.setDescricao(perguntas[key]);
			pergunta_cadastro.setAtivo(1);
			PerguntaService ps = new PerguntaService();
			int id_pergunta = ps.criar(pergunta_cadastro);

			PerguntaHasResposta  pergunta_has_resposta = new PerguntaHasResposta();
			pergunta_has_resposta.setId_pergunta(id_pergunta);
			pergunta_has_resposta.setId_resposta(id_resposta);
			pergunta_has_resposta.setPontuacao(0);
			pergunta_has_resposta.setId_topico(id_topico);
			PerguntaHasRespostaService perguntaHasRespostaService = new PerguntaHasRespostaService();
			perguntaHasRespostaService.criar(pergunta_has_resposta);

			PalavraChave palavraChave = new PalavraChave();
			PalavraChaveService palavraChaveService = new PalavraChaveService();


			//Quebrando a resposta em várias palavras chaves.
			palavrasChavesResposta = transformar_string_palavras_chave(respostas[key]);
			for (int keyResposta = 0; keyResposta < palavrasChavesResposta.length; keyResposta++) {
				int id_palavra_chave = -1;
				if(palavraChaveService.verificarJaExistePalavraChave(palavrasChavesResposta[keyResposta])) {
					System.out.println("ja existe resposta");
					id_palavra_chave = palavraChaveService.carregar_id("WHERE NOME = '" + palavrasChavesResposta[keyResposta] + "' LIMIT 1");
				}else {
					PalavraChave palavraChavePrincipal = new PalavraChave();
					palavraChavePrincipal.setNome(palavrasChavesResposta[keyResposta]);
					palavraChavePrincipal.setAtivo(1);
					System.out.println("criando nova palavra chave resposta");
					id_palavra_chave = palavraChaveService.criar(palavraChavePrincipal);
				}

				if(id_palavra_chave == -1) {
					System.out.println("Erro ao adicionar palavra chave de respostas");
				}

				System.out.println("id_palavra_chave: " + id_palavra_chave);
				PalavraChaveHasResposta palavra_chave_has_resposta = new PalavraChaveHasResposta();
				palavra_chave_has_resposta.setId_resposta(id_resposta);
				palavra_chave_has_resposta.setId_palavra_chave(id_palavra_chave);
				palavra_chave_has_resposta.setPont_respsota(0);

				PalavraChaveHasRespostaService palavraChaveHasRespostaService = new PalavraChaveHasRespostaService();
				palavraChaveHasRespostaService.criar(palavra_chave_has_resposta);
			}

			//Quebrando a pergunta em várias palavras chaves.
			palavrasChavesPergunta = transformar_string_palavras_chave(perguntas[key]);
			for (int keyPergunta = 0; keyPergunta < palavrasChavesPergunta.length; keyPergunta++) {
				int id_palavra_chave = -1;
				if(palavraChaveService.verificarJaExistePalavraChave(palavrasChavesPergunta[keyPergunta])) {
					System.out.println("ja existe pergunta");
					id_palavra_chave = palavraChaveService.carregar_id("WHERE NOME = '" + palavrasChavesPergunta[keyPergunta] + "' LIMIT 1");
				}else {
					PalavraChave palavraChavePrincipal = new PalavraChave();
					palavraChavePrincipal.setNome(palavrasChavesPergunta[keyPergunta]);
					palavraChavePrincipal.setAtivo(1);
					System.out.println("criando nova palavra chave pergunta");
					id_palavra_chave = palavraChaveService.criar(palavraChavePrincipal);
				}

				if(id_palavra_chave == -1) {
					System.out.println("Erro ao adicionar palavra chave de perguntas");
				}

				System.out.println("id_palavra_chave: " + id_palavra_chave);
				PalavraChaveHasPergunta palavra_chave_has_pergunta = new PalavraChaveHasPergunta();
				palavra_chave_has_pergunta.setId_pergunta(id_pergunta);
				palavra_chave_has_pergunta.setId_palavra_chave(id_palavra_chave);

				PalavraChaveHasPerguntaService palavraChaveHasPerguntaService = new PalavraChaveHasPerguntaService();
				palavraChaveHasPerguntaService.criar(palavra_chave_has_pergunta);
			}


		}

		Alerta.alerta("Tópico cadastrado com sucesso", "success", request); 

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/listar_topicos.jsp");
		dispatcher.forward(request, response);

	}
	
	public void excluir_palavra_chave_pergunta(String[] url, ServletRequest request, ServletResponse response)throws ServletException, IOException {
		TopicoService ts = new TopicoService();
		ts.excluirTopico(Integer.parseInt(url[2]));
		Alerta.alerta("Tópico excluído com sucesso", "success", request);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/listar_topicos.jsp");
		dispatcher.forward(request, response);
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

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
