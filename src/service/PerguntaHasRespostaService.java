package service;

import model.Atendimento;
import model.PalavraChaveHasPergunta;
import model.Pergunta;
import model.PerguntaHasResposta;

import java.util.ArrayList;
import dao.PerguntaHasRespostaDAO;


public class PerguntaHasRespostaService {
	PerguntaHasRespostaDAO dao = new PerguntaHasRespostaDAO();
	
	public int criar(PerguntaHasResposta perguntaHasResposta) {
	    return dao.criar(perguntaHasResposta);
	  }
  
  public ArrayList<PerguntaHasResposta> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}

  public void aumentarPontuacao(int id_pergunta_resposta) {
	  
	  ArrayList<PerguntaHasResposta> perguntaHasRespostas = dao.carregarCadastro("WHERE ID = " + id_pergunta_resposta);
	  if(!perguntaHasRespostas.isEmpty()) {
		  int pontuacao = perguntaHasRespostas.get(0).getPontuacao();
		  pontuacao += 1;
		  dao.query("UPDATE pergunta_has_resposta SET PONTUACAO = "  + pontuacao + " WHERE ID = " + id_pergunta_resposta);
	  }
      

  }

}
