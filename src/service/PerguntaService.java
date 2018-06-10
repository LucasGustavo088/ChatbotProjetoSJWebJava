package service;

import model.PalavraChaveHasPergunta;
import model.Pergunta;
import java.util.ArrayList;
import dao.PerguntaDAO;


public class PerguntaService {
  PerguntaDAO dao = new PerguntaDAO();
  
  public int criar(Pergunta pergunta) {
    return dao.criar(pergunta);
  }
  
  public ArrayList<Pergunta> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}

}
