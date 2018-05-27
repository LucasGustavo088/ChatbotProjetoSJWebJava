package service;

import model.Resposta;
import java.util.ArrayList;
import dao.RespostaDAO;


public class RespostaService {
  RespostaDAO dao = new RespostaDAO();
  
  public int criar(Resposta cliente) {
    return dao.criar(cliente);
  }

}
