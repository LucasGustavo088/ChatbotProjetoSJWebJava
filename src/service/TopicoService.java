package service;

import java.util.ArrayList;

import dao.PerguntaHasRespostaDAO;
import dao.TopicoDAO;
import model.Pergunta;
import model.Resposta;
import model.PerguntaHasResposta;
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
	public Topico carregarCadastroCompleto(int id_topico) {
		ArrayList<Topico> topicos = dao.carregarCadastro("WHERE ID = " + id_topico);
		Topico topico = new Topico();
		
		if(topicos.isEmpty()) {
			return null;
		} else {
			topico = topicos.get(0);
			
			//PerguntaHasResposta
			PerguntaHasRespostaService perguntaHasRespostaService = new PerguntaHasRespostaService();
			ArrayList<PerguntaHasResposta> perguntasHasRespostas = perguntaHasRespostaService.carregarCadastro("WHERE ID_TOPICO = " + id_topico);
			
			if(!perguntasHasRespostas.isEmpty()) {
				for(PerguntaHasResposta perguntasHasResposta : perguntasHasRespostas) {
					PerguntaService ps = new PerguntaService();
					ArrayList<Pergunta> perguntas = ps.carregarCadastro("WHERE ID = " + perguntasHasResposta.getId_pergunta());
					if(!perguntas.isEmpty()) {
						perguntasHasResposta.pergunta = perguntas.get(0);
					}
					
					RespostaService rs = new RespostaService();
					ArrayList<Resposta> respostas = rs.carregarCadastro("WHERE ID = " + perguntasHasResposta.getId_resposta());
					if(!respostas.isEmpty()) {
						perguntasHasResposta.resposta = respostas.get(0);
					}
				}
				
				topico.perguntaHasResposta = perguntasHasRespostas;
			}
			
			return topico;
		}
	}


}
