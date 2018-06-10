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
	
	public void listar_topicos_ajax(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/chatbot/listar_topicos.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
