package routes;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		//Cors
		// Authorize (allow) all domains to consume the content
        //((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        //((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");

		//Configurações de enconding
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//Obtendo dados de session
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		//Obtendo dados do usuario na session
		//Users logado = (Usuario) session.getAttribute("logado");

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
		uri = uri.replace("/ChatbotProjetoSJWebJava/", "");
		
		System.out.println(uri);
    	
		try {
			runRoute(uri, request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Mandando o usuário para a página de login caso ele não esteja logado.
		/*if (logado == null && !uri.equals(path + "/login.jsp")
				&& !comando.equals("FazerLogin")) {
			((HttpServletResponse) response).sendRedirect(path + "/login.jsp");
		}*/

		//chain.doFilter(request, response);

	}

	public void runRoute(String url, ServletRequest request, ServletResponse response) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException, ServletException {
		//get current url
		String urlArray[];
		urlArray = url.split("/");
		boolean found = false;
		ArrayList< String > param = new ArrayList< String >();
		Route rotaEncontrada = new Route(null, null, null, null);

		//Verify all of the url of routes.php is equal to current url
		for(Route route : this.rotas){
			//System.out.println(route.getUrl());
			String routeArray[] = route.getUrl().split("/");
			
			/*for(int i = 0; i < routeArray.length; i++){
				if(routeArray[i].contains("{") != false && urlArray.length == routeArray.length){
					routeArray[i] = urlArray[i];
					param.add(urlArray[i]);
				}
				route.setUrl(String.join("/", routeArray));
			}*/
			
			
			if(urlArray.length < 2) {
				PrintWriter out = response.getWriter();
				out.println("Página não encontrada");
				return;
			}
			
			if(urlArray[0].equals("public")) {
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/" + url);
				dispatcher.forward(request, response);
				return;
			}
			
			if(isClass("controller." + route.getController()) && urlArray[0].equals(routeArray[0]) && urlArray[1].equals(route.getFuncao())){
				
				found = true;
				rotaEncontrada.setUrl(route.getUrl());
				rotaEncontrada.setController(route.getController());
				rotaEncontrada.setFuncao(route.getFuncao());
				rotaEncontrada.setName(route.getName());


				break;
			} 
			// else if(1 == 1){
			//  echo 'verificar se o controller e método existe';
			// } else {
			//  throw new Exception("Erro 404", 1);
			// } 
		}
		
		if(found){
			//Autorização
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			int logado = (int) session.getAttribute("logado");
			System.out.println("ele esta logado? " + logado);
			
			System.out.println(rotaEncontrada.toString());

		    //load the AppTest at runtime
			Class cls = Class.forName("controller." + rotaEncontrada.getController());
			Object obj = cls.newInstance();
				
			//call the printIt method
			Method method = cls.getDeclaredMethod(rotaEncontrada.getFuncao(), String[].class, ServletRequest.class, ServletResponse.class);
			method.invoke(obj, url.split("/"),request, response);

			//controller->action();
			//mudar o request e deixar apenas como 2 parametros
			/*switch (count(param)){
			case 1:
				controller->action(param[0], this->get_request());
				break;
			case 2:
				controller->action(param[0], param[1], this->get_request());
				break;
			case 3:
				controller->action(param[0], param[1], param[2], this->get_request());
				break;
			default:
				controller->action(this->get_request());
				break;
			}*/
		} else {
			//Container::page_not_found();
			System.out.println("Página não encontrada");
		}
	}
	
	public boolean isClass(String className) {
	    try  {
	        Class.forName(className);
	        return true;
	    }  catch (ClassNotFoundException e) {
	        return false;
	    }
	}


	public String obterRotaComName() {
		return "";
	}

	public void inicializar_rotas() {
		//Autorização
		rotas.add( new Route("auth/logout", "Auth/LogoutController", "logout", "logout"));
		rotas.add( new Route("", "HomeController", "index", "home"));
		rotas.add( new Route("autorizacao/login", "AutorizacaoController", "login", "login"));
		rotas.add( new Route("autorizacao/logar", "AutorizacaoController", "logar", "logar"));
		
		//Dashboard
		rotas.add( new Route("dashboard/home", "DashboardController", "home", "dashboard.home"));
		rotas.add( new Route("dashboard/atendimento/{id}", "DashboardController", "atendimento", "dashboard.atendimento"));
		rotas.add( new Route("dashboard/listar_pendencias_ajax", "DashboardController", "listar_pendencias_ajax", "dashboard.listar_pendencias_ajax"));

		//Utilizador   
		rotas.add( new Route("utilizador/remover_alerta/{id}", "UtilizadorController", "remover_alerta", "utilizador.remover_alerta"));

		//Chatbot
		rotas.add( new Route("chatbot/listar_topicos_ajax/", "ChatbotController", "listar_topicos_ajax", "chatbot.listar_topicos_ajax"));
		rotas.add( new Route("chatbot/configuracoes/", "ChatbotController", "configuracoes", "chatbot.configuracoes"));
		rotas.add( new Route("chatbot/listar_perguntas_respostas_ajax/", "ChatbotController", "listar_perguntas_respostas_ajax", "chatbot.listar_perguntas_respostas_ajax"));
		rotas.add( new Route("chatbot/listar_topicos", "ChatbotController", "listar_topicos", "chatbot.listar_topicos"));
		rotas.add( new Route("chatbot/adicionar_palavra_chave_pergunta/", "ChatbotController", "adicionar_palavra_chave_pergunta", "chatbot.adicionar_palavra_chave_pergunta"));
		rotas.add( new Route("chatbot/editar_palavra_chave_pergunta/{id}/", "ChatbotController", "editar_palavra_chave_pergunta", "editar_palavra_chave_pergunta"));
		rotas.add( new Route("chatbot/excluir_palavra_chave_pergunta/{id}/", "ChatbotController", "excluir_palavra_chave_pergunta", "excluir_palavra_chave_pergunta"));
		rotas.add( new Route("p_adicionar_palavra_chave_pergunta/", "ChatbotController", "p_adicionar_palavra_chave_pergunta", "chatbot.p_adicionar_palavra_chave_pergunta"));

		//Chatbot Dialog
		rotas.add( new Route("chatbot_dialog/obter_resposta_ajax/", "ChatbotDialogController", "obter_resposta_ajax", "chatbotdialog.obter_resposta_ajax"));
		rotas.add( new Route("chatbot_dialog/salvar_atendimento/", "ChatbotDialogController", "salvar_atendimento", "chatbotdialog.salvar_atendimento"));
		rotas.add( new Route("chatbot_dialog/carregar_mensagens_chat/{id_atendimento}/", "ChatbotDialogController", "carregar_mensagens_chat", "chatbot_dialog.carregar_mensagens_chat"));
		rotas.add( new Route("chatbot_dialog/salvar_mensagem_banco/pergunta/", "ChatbotDialogController", "salvar_mensagem_banco", "chatbot_dialog.salvar_mensagem_banco"));
		rotas.add( new Route("chatbot_dialog/salvar_mensagem_banco/resposta/", "ChatbotDialogController", "salvar_mensagem_banco", "chatbot_dialog.salvar_mensagem_banco"));
		rotas.add( new Route("chatbot_dialog/atualizar_status_atendimento/", "ChatbotDialogController", "atualizar_status_atendimento", "chatbot_dialog.atualizar_status_atendimento"));
		rotas.add( new Route("chatbot_dialog/resposta_satisfatoria/", "ChatbotDialogController", "resposta_satisfatoria", "chatbot_dialog.resposta_satisfatoria"));
		rotas.add( new Route("chatbot_dialog/finalizar_atendimento/", "ChatbotDialogController", "finalizar_atendimento", "chatbot_dialog.finalizar_atendimento"));

		//Relatório
		rotas.add( new Route("relatorio/listar_pendencias/", "RelatorioController", "listar_pendencias", "relatorio.listar_pendencias"));
		rotas.add( new Route("relatorio/gerar_relatorio/", "RelatorioController", "gerar_relatorio", "relatorio.gerar_relatorio"));
	}
	
	public static boolean estaLogado(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		boolean logado = (boolean) session.getAttribute("logado");
		System.out.println("ele esta logado? " + logado);
		
		return true;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
