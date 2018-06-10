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

import model.Topico;
import service.TopicoService;
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
	
	public void listar_topicos_ajax(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

		TopicoService topicoService = new TopicoService();
		ArrayList<Topico> topicos = topicoService.carregarCadastro("WHERE ATIVO = 1 ORDER BY DATA_CRIACAO DESC");
		ArrayList<ArrayList> aaData = new ArrayList<ArrayList>();
		for(Topico topico : topicos) {
			String botao_editar = " <a href='chatbot/editar_palavra_chave_pergunta/" + topico.getId() + "' class='btn btn-default'><i class='fas fa-pencil-alt'></i> Editar</a>"; 
			String botao_excluir = " <a href='chatbot/excluir_palavra_chave_pergunta/" + topico.getId() + "' class='btn btn-danger'><i class='fas fa-times'></i> Excluir</a>";                                  

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

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
