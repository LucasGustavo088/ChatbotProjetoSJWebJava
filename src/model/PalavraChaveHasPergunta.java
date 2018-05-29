package model;

public class PalavraChaveHasPergunta {
		private int id;
		private int  id_pergunta;
		private int  id_palavra_chave;
		
		
		public Pergunta pergunta;
		public PalavraChave palavraChave;
		
		
		
		public Pergunta getPergunta() {
			return pergunta;
		}
		public void setPergunta(Pergunta pergunta) {
			this.pergunta = pergunta;
		}
		public PalavraChave getPalavraChave() {
			return palavraChave;
		}
		public void setPalavraChave(PalavraChave palavraChave) {
			this.palavraChave = palavraChave;
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
		public int getId_palavra_chave() {
			return id_palavra_chave;
		}
		public void setId_palavra_chave(int id_palavra_chave) {
			this.id_palavra_chave = id_palavra_chave;
		}
		
		
}
