package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletResponse;

import dao.ConnectionFactory;
import dao.PalavraChaveDAO;
import model.Atendimento;
import model.PalavraChave;
import model.PalavraChaveHasPergunta;
import model.Pergunta;
import model.PerguntaHasResposta;
import model.Resposta;
import model.Topico;
import utils.Debug;
import utils.Helper;
import utils.Json;

public class PalavraChaveService {

	public PalavraChaveDAO dao = new PalavraChaveDAO();


	public ArrayList<PalavraChave> obter_palavra_chave_com_string(String palavra_chave) {
		return dao.carregarCadastro("where NOME = '" + palavra_chave + "'");
	}
	
	public int criar(PalavraChave palavraChave) {
	    return dao.criar(palavraChave);
	  }
	
	public PalavraChave carregar_cadastro_completo(int idPalavraChave, ServletResponse response) throws IOException {
		ArrayList<PalavraChave> palavraChaveList = dao.carregarCadastro("WHERE ID = '" + idPalavraChave + "' LIMIT 1");
		PalavraChave palavraChaveCadastro = palavraChaveList.get(0); 

		/*->with([
                'palavra_chave_has_pergunta. (ok)
                pergunta (ok).
                pergunta_has_resposta (ok).
                resposta (ok)',
                'palavra_chave_has_pergunta (ok).
                pergunta (ok).
                pergunta_has_resposta (ok).
                topico (ok) ',
        ])*/

		//PalavraChaveHasPergunta
		PalavraChaveHasPerguntaService palavraChaveHasPerguntaService = new PalavraChaveHasPerguntaService();
		palavraChaveCadastro.palavraChaveHasPergunta = palavraChaveHasPerguntaService.carregarCadastro("WHERE ID_PALAVRA_CHAVE = '" + palavraChaveCadastro.getId() + "'");

		for(PalavraChaveHasPergunta palavraChaveHasPerguntas : palavraChaveCadastro.palavraChaveHasPergunta) {

			//Pergunta
			PerguntaService perguntaService = new PerguntaService();
			ArrayList<Pergunta> perguntas = perguntaService.carregarCadastro("WHERE ID = '" + palavraChaveHasPerguntas.getId_pergunta() + "'");
			if(!perguntas.isEmpty()) {
				palavraChaveHasPerguntas.pergunta = perguntas.get(0);

				//perguntaHasResposta
				PerguntaHasRespostaService perguntaHasRespostaService = new PerguntaHasRespostaService();
				ArrayList<PerguntaHasResposta> perguntaHasRespostas = perguntaHasRespostaService.carregarCadastro("WHERE ID_PERGUNTA = " + palavraChaveHasPerguntas.pergunta.getId() + "");
				palavraChaveHasPerguntas.pergunta.perguntaHasResposta = null;
				
				if(!perguntaHasRespostas.isEmpty()) {
					palavraChaveHasPerguntas.pergunta.perguntaHasResposta = perguntaHasRespostas.get(0);
					
					//Resposta
					RespostaService respostaService = new RespostaService();
					ArrayList<Resposta> respostas = respostaService.carregarCadastro("WHERE ID = " + palavraChaveHasPerguntas.pergunta.perguntaHasResposta.getId_resposta() + "");

					if(!respostas.isEmpty()) {
						palavraChaveHasPerguntas.pergunta.perguntaHasResposta.resposta = respostas.get(0);
					}

					//Topico
					TopicoService topicoService = new TopicoService();
					ArrayList<Topico> topicos = topicoService.carregarCadastro("WHERE ID = " + palavraChaveHasPerguntas.pergunta.perguntaHasResposta.getId_topico() + "");
					palavraChaveHasPerguntas.pergunta.perguntaHasResposta.topico = null;
					if(!topicos.isEmpty()) {
						palavraChaveHasPerguntas.pergunta.perguntaHasResposta.topico = topicos.get(0);
					}
				}
			}


		}
		
		return palavraChaveCadastro;
	}
	
	public int carregar_id(String query) {
		ArrayList<PalavraChave> palavras_chave = dao.carregarCadastro(query);
		
		if(palavras_chave.isEmpty()) {
			return -1;
		} else {
			PalavraChave palavraChave = palavras_chave.get(0);
			return palavraChave.getId();
		}
	}
  
	
	public boolean verificar_ja_existe_palavra_chave(String palavra_chave) {
		ArrayList<PalavraChave> palavras_chave = dao.carregarCadastro("WHERE NOME = '" + palavra_chave + "'");
		if(palavras_chave.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
