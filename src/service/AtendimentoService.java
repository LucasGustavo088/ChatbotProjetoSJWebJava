package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletResponse;

import dao.AtendimentoDAO;
import model.Atendimento;
import model.AtendimentoHasPergunta;
import model.AtendimentoHasResposta;
import model.Cliente;
import model.Pergunta;
import model.Resposta;
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
				//Cliente
				ArrayList<Cliente> clientes = clienteService.carregarCadastro("WHERE ID = " + atendimento.getId_cliente() + " AND ATIVO = 1 ORDER BY ID DESC");
				if(!clientes.isEmpty()) {
					atendimento.cliente = clientes.get(0);
				}

				//Pergunta
				AtendimentoHasPerguntaService AtendimentoHasPerguntaService = new AtendimentoHasPerguntaService();
				ArrayList<AtendimentoHasPergunta> atendimentoHasPerguntas = AtendimentoHasPerguntaService
						.carregarCadastro("WHERE ID_ATENDIMENTO = " + atendimento.getId());
				
				if(!atendimentoHasPerguntas.isEmpty()) {

					for(AtendimentoHasPergunta atendimentoHasPergunta : atendimentoHasPerguntas) {
						PerguntaService perguntaService = new PerguntaService();
						ArrayList<Pergunta> perguntas = perguntaService.carregarCadastro("WHERE ID = " + atendimentoHasPergunta.getId_pergunta());

						if(!perguntas.isEmpty()) {
							atendimentoHasPergunta.pergunta = perguntas.get(0);
						}
					}

					atendimento.atendimentoHasPergunta = atendimentoHasPerguntas;
				}
				
				//Resposta
		        AtendimentoHasRespostaService AtendimentoHasRespostaService = new AtendimentoHasRespostaService();
		        ArrayList<AtendimentoHasResposta> atendimentoHasRespostas = AtendimentoHasRespostaService
		            .carregarCadastro("WHERE ID_ATENDIMENTO = " + atendimento.getId());
		        
		        if(!atendimentoHasRespostas.isEmpty()) {

		          for(AtendimentoHasResposta atendimentoHasResposta : atendimentoHasRespostas) {
		            RespostaService respostaService = new RespostaService();
		            ArrayList<Resposta> respostas = respostaService.carregarCadastro("WHERE ID = " + atendimentoHasResposta.getId_resposta());

		            if(!respostas.isEmpty()) {
		              atendimentoHasResposta.resposta = respostas.get(0);
		            }
		          }

		          atendimento.atendimentoHasResposta = atendimentoHasRespostas;
		        }

			}



		}

		return atendimentos;
	}

	public void query(String query) {
		dao.query(query);
	}

}
