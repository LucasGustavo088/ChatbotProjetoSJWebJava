package service;

import java.util.ArrayList;

import dao.PerguntaHasRespostaDAO;
import dao.TopicoDAO;
import model.Topico;

public class TopicoService {
	TopicoDAO dao = new TopicoDAO();
	PerguntaHasRespostaDAO perguntaHasRespostaDao = new PerguntaHasRespostaDAO();

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
		perguntaHasRespostaDao.query("UPDATE pergunta_has_resposta SET ATIVO = 0 WHERE ID_TOPICO = " + id_topico);
	}


}
