package service;

import java.util.ArrayList;

import dao.PalavraChaveDAO;
import model.PalavraChave;
import model.PalavraChaveHasPergunta;

public class PalavraChaveService {
	
	public PalavraChaveDAO dao = new PalavraChaveDAO();
	
	
	public ArrayList<PalavraChave> obter_palavra_chave_com_string(String palavra_chave) {
		return dao.carregarCadastro("where NOME = '" + palavra_chave + "'");
	}
	
	public PalavraChave carregar_cadastro_completo(int idPalavraChave) {
		ArrayList<PalavraChave> palavraChaveList = dao.carregarCadastro("WHERE ID = '" + idPalavraChave + "' LIMIT 1");
		PalavraChave palavraChaveCadastro = palavraChaveList.get(0); 
		
		/*->with([
                'palavra_chave_has_pergunta.pergunta.pergunta_has_resposta.resposta',
                'palavra_chave_has_pergunta.pergunta.pergunta_has_resposta.topico',
        ])*/
		
		//PalavraChaveHasPergunta
		ArrayList<PalavraChaveHasPergunta> palavraChaveHasPergunta;
		PalavraChaveHasPerguntaService palavraChaveHasPerguntaService = new PalavraChaveHasPerguntaService();
		palavraChaveCadastro.palavraChaveHasPergunta = palavraChaveHasPerguntaService.carregarCadastro("WHERE ID_PALAVRA_CHAVE = '" + palavraChaveCadastro.getId() + "'");
		
		
		
		
		return palavraChaveCadastro;
	}
	
	public PalavraChave verificar_ja_existe_palavra_chave(PalavraChave palavra_chave) {
		return dao.verificar_ja_existe_palavra_chave(palavra_chave);
	}
	
	
}
