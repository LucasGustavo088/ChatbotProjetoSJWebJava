package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletResponse;

import dao.PalavraChaveDAO;
import model.PalavraChave;
import model.PalavraChaveHasPergunta;
import model.Pergunta;
import model.PerguntaHasResposta;
import model.Resposta;
import model.Topico;
import utils.Debug;

public class PalavraChaveService {

	public PalavraChaveDAO dao = new PalavraChaveDAO();


	public ArrayList<PalavraChave> obter_palavra_chave_com_string(String palavra_chave) {
		return dao.carregarCadastro("where NOME = '" + palavra_chave + "'");
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
					ArrayList<Resposta> respostas = respostaService.carregarCadastro("WHERE ID_RESPOSTA = " + palavraChaveHasPerguntas.pergunta.perguntaHasResposta.getId_resposta() + "");

					if(!respostas.isEmpty()) {
						palavraChaveHasPerguntas.pergunta.perguntaHasResposta.resposta = respostas.get(0);
					}

					//Topico
					TopicoService topicoService = new TopicoService();
					ArrayList<Topico> topicos = topicoService.carregarCadastro("WHERE ID_RESPOSTA = " + palavraChaveHasPerguntas.pergunta.perguntaHasResposta.getId_topico() + "");

					if(!topicos.isEmpty()) {
						palavraChaveHasPerguntas.pergunta.perguntaHasResposta.topico = topicos.get(0);
					}
				}
			}


		}

		return palavraChaveCadastro;
	}
	
	public PalavraChave verificar_ja_existe_palavra_chave(PalavraChave palavra_chave) {
		return dao.verificar_ja_existe_palavra_chave(palavra_chave);
	}
	
	
}
