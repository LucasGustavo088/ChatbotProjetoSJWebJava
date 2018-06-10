package service;

import dao.PalavraChaveHasRespostaDAO;
import model.PalavraChaveHasResposta;

public class PalavraChaveHasRespostaService {
	
	PalavraChaveHasRespostaDAO dao = new PalavraChaveHasRespostaDAO();
	
	 public int criar(PalavraChaveHasResposta palavraChaveHasResposta) {
		    return dao.criar(palavraChaveHasResposta);
		  }

}
