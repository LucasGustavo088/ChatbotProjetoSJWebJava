package service;

import model.Cliente;
import java.util.ArrayList;
import dao.ClienteDAO;


public class ClienteService {
  ClienteDAO dao = new ClienteDAO();
  
  public int criar(Cliente cliente) {
    return dao.criar(cliente);
  }

}
