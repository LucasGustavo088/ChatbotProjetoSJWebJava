package model;

public class PalavraChaveHasResposta {
	private int id;
	private int id_palavra_chave;
	private int id_resposta;
	private int pont_respsota;
	
	private PalavraChave palavraChave;
	private Resposta resposta;
	
	
	
	public PalavraChave getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(PalavraChave palavraChave) {
		this.palavraChave = palavraChave;
	}
	public Resposta getResposta() {
		return resposta;
	}
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_palavra_chave() {
		return id_palavra_chave;
	}
	public void setId_palavra_chave(int id_palavra_chave) {
		this.id_palavra_chave = id_palavra_chave;
	}
	public int getId_resposta() {
		return id_resposta;
	}
	public void setId_resposta(int id_resposta) {
		this.id_resposta = id_resposta;
	}
	public int getPont_respsota() {
		return pont_respsota;
	}
	public void setPont_respsota(int pont_respsota) {
		this.pont_respsota = pont_respsota;
	}
	
	


}
