package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletResponse;

import dao.AtendimentoDAO;
import model.Atendimento;
import model.Cliente;
import utils.Debug;
import utils.Helper;

public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();
  
  public int criar(Atendimento atendimento) {
    return dao.criar(atendimento);
  }
  
  public void finalizar_atendimento(Atendimento atendimento ) {
	  dao.query("UPDATE atendimento SET STATUS = 'finalizado', DATA_FINALIZACAO = '" + Helper.dataAtual() + "' WHERE ID = " + atendimento.getId());
  }
  
  public ArrayList<Atendimento> carregarCadastroCompleto(String query, ServletResponse response) throws IOException {
	  ArrayList<Atendimento> atendimentos =  dao.carregarCadastro(query);
	  
	  for(Atendimento atendimento : atendimentos) {
		  ClienteService clienteService = new ClienteService();
		  if(atendimento.getId_cliente() != 0) {
			  ArrayList<Cliente> clientes = clienteService.carregarCadastro("WHERE ID = " + atendimento.getId_cliente() + " AND ATIVO = 1 ORDER BY ID DESC");
			  if(!clientes.isEmpty()) {
				  atendimento.cliente = clientes.get(0);
			  }
		  }
		  
	  }
	  
	  return atendimentos;
	}

}
