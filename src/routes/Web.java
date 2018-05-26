package routes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

/**
 * Servlet Filter implementation class Web
 */
@WebFilter("/*")
public class Web implements Filter {
	ArrayList< Route > rotas = new ArrayList< Route >();
	
	/**
	 * Default constructor. 
	 */
	public Web() {
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
		/*
		/*  Web filter - Rotas
		*/
		
		//Configurações de enconding
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//Obtendo dados de session
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//Obtendo dados do usuario na session
		Usuario logado = (Usuario) session.getAttribute("logado");
		
		//Caminho de url
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		
		//Command
		String comando = req.getParameter("command");
		if(comando == null){
			comando = "";
		}
		
		//Inicializando rotas
		inicializar_rotas();
		
		Route rotaRedirect = obterRotaComUrl(uri);
		
		//Mandando o usuário para a página de login caso ele não esteja logado.
		if (logado == null && !uri.equals(path + "/login.jsp")
				&& !comando.equals("FazerLogin")) {
			((HttpServletResponse) response).sendRedirect(path + "/login.jsp");
		}
		
		
		chain.doFilter(request, response);
		
	}
	
	public Route obterRotaComUrl(String url) {
		Route rotaEncontrada = new Route (null, null, null, null);
		
		for (Route rota : rotas) {
			if(rota.getUrl() == url) {
				rotaEncontrada =  rota;
			}
		}
		
		return rotaEncontrada;
	}
	
	public String obterRotaComName() {
		return "";
	}
	
	public void inicializar_rotas() {
		//Autorização
        rotas.add( new Route("/auth/logout", "Auth/LogoutController", "logout", "logout"));
        rotas.add( new Route("/", "HomeController", "index", "home"));

        //Dashboard
        rotas.add( new Route("/dashboard", "DashboardController", "home", "dashboard.home"));
        rotas.add( new Route("/dashboard/atendimento/{id}", "DashboardController", "atendimento", "dashboard.atendimento"));
        rotas.add( new Route("/dashboard/listar_pendencias_ajax", "DashboardController", "listar_pendencias_ajax", "dashboard.listar_pendencias_ajax"));

        //Utilizador   
        rotas.add( new Route("/utilizador/remover_alerta/{id}", "UtilizadorController", "remover_alerta", "utilizador.remover_alerta"));

        //Chatbot
        rotas.add( new Route("chatbot/listar_topicos_ajax", "ChatbotController", "listar_topicos_ajax", "chatbot.listar_topicos_ajax"));
        rotas.add( new Route("chatbot/configuracoes", "ChatbotController", "configuracoes", "chatbot.configuracoes"));
        rotas.add( new Route("chatbot/listar_perguntas_respostas_ajax", "ChatbotController", "listar_perguntas_respostas_ajax", "chatbot.listar_perguntas_respostas_ajax"));
        rotas.add( new Route("chatbot/listar_topicos", "ChatbotController", "listar_topicos", "chatbot.listar_topicos"));
        rotas.add( new Route("chatbot/adicionar_palavra_chave_pergunta", "ChatbotController", "adicionar_palavra_chave_pergunta", "chatbot.adicionar_palavra_chave_pergunta"));
        rotas.add( new Route("chatbot/editar_palavra_chave_pergunta/{id}", "ChatbotController", "editar_palavra_chave_pergunta", "editar_palavra_chave_pergunta"));
        rotas.add( new Route("chatbot/excluir_palavra_chave_pergunta/{id}", "ChatbotController", "excluir_palavra_chave_pergunta", "excluir_palavra_chave_pergunta"));
        rotas.add( new Route("p_adicionar_palavra_chave_pergunta", "ChatbotController", "p_adicionar_palavra_chave_pergunta", "chatbot.p_adicionar_palavra_chave_pergunta"));

        //Chatbot Dialog
        rotas.add( new Route("chatbot_dialog/obter_resposta_ajax", "ChatbotDialogController", "obter_resposta_ajax", "chatbotdialog.obter_resposta_ajax"));
        rotas.add( new Route("chatbot_dialog/salvar_atendimento", "ChatbotDialogController", "salvar_atendimento", "chatbotdialog.salvar_atendimento"));
        rotas.add( new Route("/chatbot_dialog/carregar_mensagens_chat/{id_atendimento}", "ChatbotDialogController", "carregar_mensagens_chat", "chatbot_dialog.carregar_mensagens_chat"));
        rotas.add( new Route("/chatbot_dialog/salvar_mensagem_banco/{pergunta_ou_resposta}/{id_atendimento}", "ChatbotDialogController", "salvar_mensagem_banco", "chatbot_dialog.salvar_mensagem_banco"));
        rotas.add( new Route("/chatbot_dialog/atualizar_status_atendimento", "ChatbotDialogController", "atualizar_status_atendimento", "chatbot_dialog.atualizar_status_atendimento"));
        rotas.add( new Route("/chatbot_dialog/resposta_satisfatoria", "ChatbotDialogController", "resposta_satisfatoria", "chatbot_dialog.resposta_satisfatoria"));
        rotas.add( new Route("/chatbot_dialog/finalizar_atendimento", "ChatbotDialogController", "finalizar_atendimento", "chatbot_dialog.finalizar_atendimento"));

        //Relatório
        rotas.add( new Route("/relatorio/listar_pendencias", "RelatorioController", "listar_pendencias", "relatorio.listar_pendencias"));
        rotas.add( new Route("relatorio/gerar_relatorio", "RelatorioController", "gerar_relatorio", "relatorio.gerar_relatorio"));

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
