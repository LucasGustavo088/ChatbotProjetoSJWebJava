package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.PalavraChave;
import model.PalavraChaveHasPergunta;
import model.PalavraChaveHasPerguntaDAO;

public class PalavraChaveHasPerguntaService {
	PalavraChaveHasPerguntaDAO dao = new PalavraChaveHasPerguntaDAO();
	
	public ArrayList<PalavraChaveHasPergunta> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}

}
