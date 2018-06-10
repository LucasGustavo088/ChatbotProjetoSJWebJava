package service;

import model.Cliente;
import model.Pergunta;

import java.util.ArrayList;
import dao.ClienteDAO;


public class ClienteService {
  ClienteDAO dao = new ClienteDAO();
  
  public int criar(Cliente cliente) {
    return dao.criar(cliente);
  }
  
  public ArrayList <Cliente> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}

}
