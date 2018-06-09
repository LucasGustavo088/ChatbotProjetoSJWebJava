package service;

import java.util.ArrayList;

import dao.TopicoDAO;
import model.Atendimento;
import model.Topico;
import model.Users;

public class TopicoService {
	TopicoDAO dao = new TopicoDAO();
	
	public int criar(Topico topico) {
	    return dao.criar(topico);
	  }
	/* public ArrayList<Topico> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}*/
	 
	 public boolean verificar_nome_topico_existente(String nome) {
		 ArrayList<Topico> topico = dao.carregarCadastro("WHERE nome ='" + nome);
		 
		 if(topico.isEmpty()) {
			 return false;
		 }else {
			 return true;
		 }
		 
	 }
	 
	 
	 
	
}
