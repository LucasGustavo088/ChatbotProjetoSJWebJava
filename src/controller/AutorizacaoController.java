package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
>>>>>>> ajustes no login

import service.UsersService;

/**
 * Servlet Filter implementation class AutorizacaoController
 */
@WebFilter("/AutorizacaoController")
public class AutorizacaoController implements Filter {

	/**
	 * Default constructor. 
	 */
	public AutorizacaoController() {
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

	public void login(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		redirecionarLogin(request, response);
	}

	public void logar(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		String email = "";
		String password = "";
		if(request.getParameter("email") == null || request.getParameter("password") == null) {
			redirecionarLogin(request, response);
			System.out.println("email ou senha nulos");
		} else {
			email = request.getParameter("email");
			password = request.getParameter("password");
			if(logarUsuario(email, password)) {
				System.out.println("logado");

				
				HttpServletRequest req = (HttpServletRequest) request;
			    HttpSession session = req.getSession(false);
			    
				session.setAttribute("logado", 1);
				

				//Request
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/dashboard/home.jsp");
				dispatcher.forward(request, response);
			} else {
				System.out.println("erro ao logar");
			}
		}


	}
<<<<<<< HEAD
=======
	
	public void logout(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {				
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(false);
	    
		session.setAttribute("logado", 1);
		
		//Request
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/dashboard/home.jsp");
		dispatcher.forward(request, response);
	}
>>>>>>> ajustes no login

	public void redirecionarLogin(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		//Request
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	
	private boolean logarUsuario(String email, String password) {
		UsersService usersService = new UsersService();
		return usersService.logarUsuario(email, password);
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
