package service;

import model.PalavraChaveHasPergunta;
import model.Pergunta;
import model.PerguntaHasResposta;

import java.util.ArrayList;
import dao.PerguntaHasRespostaDAO;


public class PerguntaHasRespostaService {
	PerguntaHasRespostaDAO dao = new PerguntaHasRespostaDAO();
  
  public ArrayList<PerguntaHasResposta> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}

}
