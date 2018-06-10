package service;

import java.util.ArrayList;

import dao.TopicoDAO;
import model.Atendimento;
import model.Topico;
import model.Users;

public class TopicoService {
	TopicoDAO dao = new TopicoDAO();

	public int criar(Topico topico) {
		return dao.criar(topico);
	}
	public ArrayList<Topico> carregarCadastro(String query) {
		return dao.carregarCadastro(query);
	}

	public void query(String query) {
		dao.query(query);
	}

	public boolean verificar_nome_topico_existente(String nome) {
		ArrayList<Topico> topico = dao.carregarCadastro("WHERE nome = '" + nome + "'");

		if(topico.isEmpty()) {
			return false;
		}else {
			return true;
		}

	}

	public void excluirTopico(int id_topico) {
		dao.query("UPDATE topico SET ATIVO = 0 WHERE ID = " + id_topico);
	}


}
