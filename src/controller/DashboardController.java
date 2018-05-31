package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Atendimento;
import model.Cliente;
import service.AtendimentoService;
import service.ClienteService;

/**
 * Servlet Filter implementation class DashboardController
 */
@WebFilter("/DashboardController")
public class DashboardController implements Filter {

    /**
     * Default constructor. 
     */
    public DashboardController() {
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
	
	public void home(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/home.jsp");
		
	}
	
	public void atendimento(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("atendimentoteste.jsp");
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
