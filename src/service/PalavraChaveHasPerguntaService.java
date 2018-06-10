package service;

import java.util.ArrayList;

import model.PalavraChaveHasPergunta;
import dao.PalavraChaveHasPerguntaDAO;

public class PalavraChaveHasPerguntaService {
	PalavraChaveHasPerguntaDAO dao = new PalavraChaveHasPerguntaDAO();
	
	public ArrayList<PalavraChaveHasPergunta> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}
	
	public int criar(PalavraChaveHasPergunta palavraChaveHasPergunta) {
	    return dao.criar(palavraChaveHasPergunta);
	  }


}
