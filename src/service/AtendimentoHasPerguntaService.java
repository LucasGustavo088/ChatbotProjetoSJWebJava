package service;

import model.AtendimentoHasPergunta;
import java.util.ArrayList;
import dao.AtendimentoHasPerguntaDAO;


public class AtendimentoHasPerguntaService {
  AtendimentoHasPerguntaDAO dao = new AtendimentoHasPerguntaDAO();
  
  public int criar(AtendimentoHasPergunta atendimento_has_pergunta) {
    return dao.criar(atendimento_has_pergunta);
  }

}
