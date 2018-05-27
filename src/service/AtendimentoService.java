package service;

import dao.AtendimentoDAO;
import model.Atendimento;


public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();
  
  public int criar(Atendimento atendimento) {
    return dao.criar(atendimento);
  }

}
