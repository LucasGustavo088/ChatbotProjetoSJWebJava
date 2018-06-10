package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

public class EditarPais implements Command {
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pId = 0;
		String pNome = null;
		long pPopulacao = 0L;
		float pArea = 0;
		
		pNome = request.getParameter("nome");
		
		if(request.getParameter("populacao") != null) {
			pPopulacao = Long.parseLong(request.getParameter("populacao"));
		} 
		
		if(request.getParameter("area") != null) {
			pArea = Float.parseFloat(request.getParameter("area"));
		}
		
		if(request.getParameter("id") != null) {
			pId = Integer.parseInt(request.getParameter("id"));
		}
		
		Pais pais = new Pais();
		pais.setId(pId);
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);

		PaisService cs = new PaisService();
		
		RequestDispatcher view = null;
		pais = cs.carregar(pId);
		
		request.setAttribute("pais", pais);
		view = request.getRequestDispatcher("AlterarPais.jsp");

		view.forward(request, response);

	}
	
	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == pais.getId()){
				return i;
			}
		}
		return -1;
	}
}
