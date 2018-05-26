package model;

public class PerguntaHasResposta {
	private int id;
	private int id_pergunta;
	private int id_resposta;
	private int pont_resposta;
	
	private Pergunta pergunta;
	private Resposta resposta;
	private PalavraChave palavraChave;
	private Topico topico;
	
	
	
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	public Resposta getResposta() {
		return resposta;
	}
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	public PalavraChave getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(PalavraChave palavraChave) {
		this.palavraChave = palavraChave;
	}
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_pergunta() {
		return id_pergunta;
	}
	public void setId_pergunta(int id_pergunta) {
		this.id_pergunta = id_pergunta;
	}
	public int getId_resposta() {
		return id_resposta;
	}
	public void setId_resposta(int id_resposta) {
		this.id_resposta = id_resposta;
	}
	public int getPont_resposta() {
		return pont_resposta;
	}
	public void setPont_resposta(int pont_resposta) {
		this.pont_resposta = pont_resposta;
	}
	
	

}
