package model;

import java.util.*;

public class Pergunta {
	private int id;
	private String descricao;
	private int ativo;
	private Date data_atualizacao;
	private Date data_criacao;
	private int usuario_externo;
	public PerguntaHasResposta perguntaHasResposta;
	public int peso_pergunta;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public int getUsuario_externo() {
		return usuario_externo;
	}
	public void setUsuario_externo(int usuario_externo) {
		this.usuario_externo = usuario_externo;
	}
	
	

}
