package service;

import dao.AtendimentoDAO;
import model.Atendimento;


public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();
  
  public int criar(Atendimento atendimento) {
    return dao.criar(atendimento);
  }
  
  public void finalizar_atendimento(Atendimento atendimento ) {
	  dao.finalizar_atendimento(atendimento);
  }

}
