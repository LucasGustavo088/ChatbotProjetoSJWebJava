package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.Atendimento;
import model.Cliente;
import service.AtendimentoService;
import service.ClienteService;

/**
 * Servlet implementation class ChatbotDialogController
 */
@WebServlet("/ChatbotDialogController")
public class ChatbotDialogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
