package service;

import java.util.ArrayList;

import dao.PalavraChaveDAO;
import model.PalavraChave;

public class PalavraChaveService {
	
	public PalavraChaveDAO dao = new PalavraChaveDAO();
	
	
	public ArrayList<PalavraChave> obter_palavra_chave_com_string(String palavra_chave) {
		return dao.carregarCadastro("where NOME = " + palavra_chave);
	}
	
	public ArrayList<PalavraChave> carregar_cadastro_completo(int idPalavraChave) {
		return dao.carregarCadastro("");
	}
}
