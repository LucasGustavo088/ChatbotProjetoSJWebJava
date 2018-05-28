package model;

import java.util.*;

public class PalavraChave {

		public int id;
		public String nome;
		public int palavra_chave_principal;
		public int ativo;
		public Date data_atualizacao;
		public Date data_criacao;
		
		public PalavraChaveHasResposta palavraChaveHasResposta;
		public PerguntaHasResposta perguntaHasResposta;
		public ArrayList<PalavraChaveHasPergunta> palavraChaveHasPergunta;
		
		
		
		public PalavraChaveHasResposta getPalavraChaveHasResposta() {
			return palavraChaveHasResposta;
		}
		
		public void setPalavraChaveHasResposta(PalavraChaveHasResposta palavraChaveHasResposta) {
			this.palavraChaveHasResposta = palavraChaveHasResposta;
		}
		public PerguntaHasResposta getPerguntaHasResposta() {
			return perguntaHasResposta;
		}
		public void setPerguntaHasResposta(PerguntaHasResposta perguntaHasResposta) {
			this.perguntaHasResposta = perguntaHasResposta;
		}
		public ArrayList<PalavraChaveHasPergunta> getPalavraChaveHasPergunta() {
			return palavraChaveHasPergunta;
		}
		public void setPalavraChaveHasPergunta(ArrayList<PalavraChaveHasPergunta> palavraChaveHasPergunta) {
			this.palavraChaveHasPergunta = palavraChaveHasPergunta;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getPalavra_chave_principal() {
			return palavra_chave_principal;
		}
		public void setPalavra_chave_principal(int palavra_chave_principal) {
			this.palavra_chave_principal = palavra_chave_principal;
		}
		public int getAtivo() {
			return ativo;
		}
		public void setAtivo(int ativo) {
			this.ativo = ativo;
		}
		public Date getData_atualizacao() {
			return data_atualizacao;
		}
		public void setData_atualizacao(Date data_atualizacao) {
			this.data_atualizacao = data_atualizacao;
		}
		public Date getData_criacao() {
			return data_criacao;
		}
		public void setData_criacao(Date data_criacao) {
			this.data_criacao = data_criacao;
		}
		
		@Override
		public String toString() {
			return "PalavraChave [id=" + id + ", nome=" + nome + ", palavra_chave_principal=" + palavra_chave_principal
					+ ", ativo=" + ativo + ", data_atualizacao=" + data_atualizacao + ", data_criacao=" + data_criacao
					+ ", palavraChaveHasResposta=" + palavraChaveHasResposta + ", perguntaHasResposta="
					+ perguntaHasResposta + ", palavraChaveHasPergunta=" + palavraChaveHasPergunta + "]";
		}
		
		
}
