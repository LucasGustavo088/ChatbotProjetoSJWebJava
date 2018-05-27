package service;

import model.AtendimentoHasResposta;
import java.util.ArrayList;
import dao.AtendimentoHasRespostaDAO;


public class AtendimentoHasRespostaService {
  AtendimentoHasRespostaDAO dao = new AtendimentoHasRespostaDAO();
  
  public int criar(AtendimentoHasResposta atendimento_has_pergunta) {
    return dao.criar(atendimento_has_pergunta);
  }

}
