package service;

import dao.AtendimentoDAO;
import model.Atendimento;
import utils.Helper;


public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();
  
  public int criar(Atendimento atendimento) {
    return dao.criar(atendimento);
  }
  
  public void finalizar_atendimento(Atendimento atendimento ) {
	  dao.query("UPDATE atendimento SET STATUS = 'finalizado', DATA_FINALIZACAO = '" + Helper.dataAtual() + "' WHERE ID = " + atendimento.getId());
  }

}
