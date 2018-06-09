package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import model.Topico;
import service.TopicoService;
import utils.Debug;

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

		TopicoService topicoService = new TopicoService();
		ArrayList<Topico> topicos = topicoService.carregarCadastro("");
		Debug.debug(topicos, response);
		/*
		ArrayList<ArrayList> aaData = new ArrayList<ArrayList>();
		for(Atendimento atendimento : atendimentos) {
			String botao_atender = " <a onclick='redirecionar_para_atendimento(" + atendimento.getId() + ");' class='btn btn-success'> Atender</a>"; 

			String status = "Chatbot";

			if(atendimento.getStatus().equals("atendimento_iniciado")) {
				status = "Atendimento iniciado";
			} else {
				botao_atender = " <a onclick='redirecionar_para_atendimento(" + atendimento.getId() + ");' class='btn btn-primary'> Visualizar</a>";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			ArrayList<String> data = new ArrayList<String>();
			data.add(String.valueOf(atendimento.getId()));
			data.add(atendimento.cliente.getNome_cliente());
			data.add(atendimento.cliente.getEmail_cliente());
			data.add(sdf.format(atendimento.getData_criacao()));
			data.add(status);
			data.add(botao_atender);
			
			aaData.add(data);

		}
		
		ResultadosAjax resultado = new ResultadosAjax();
		resultado.aaData = aaData;
		resultado.iTotalDisplayRecords = aaData.size();
		resultado.iTotalRecords = aaData.size();
		resultado.sEcho = 1;
		Debug.debug(resultado, response);*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
