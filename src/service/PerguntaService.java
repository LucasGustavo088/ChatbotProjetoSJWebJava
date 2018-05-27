package service;

import model.Pergunta;
import java.util.ArrayList;
import dao.PerguntaDAO;


public class PerguntaService {
  PerguntaDAO dao = new PerguntaDAO();
  
  public int criar(Pergunta cliente) {
    return dao.criar(cliente);
  }

}
