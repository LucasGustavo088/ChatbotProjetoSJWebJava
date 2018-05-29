package service;

import java.util.ArrayList;

import dao.TopicoDAO;
import model.Topico;

public class TopicoService {
	TopicoDAO dao = new TopicoDAO();
	
	 public ArrayList<Topico> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}
}
