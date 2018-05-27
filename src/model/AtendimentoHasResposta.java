package model;

import java.util.*;

public class AtendimentoHasResposta {
	private int id;
	private int id_resposta;
	private int id_atendimento;
	private Date data_criacao;
	private Date data_atualizacao;
	
	private Atendimento atendimento;
	private Resposta resposta;
	
	
	
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
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
	public int getId_resposta() {
		return id_resposta;
	}
	public void setId_resposta(int id_resposta) {
		this.id_resposta = id_resposta;
	}
	public int getId_atendimento() {
		return id_atendimento;
	}
	public void setId_atendimento(int id_atendimento) {
		this.id_atendimento = id_atendimento;
	}
	public Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public Date getData_atualizacao() {
		return data_atualizacao;
	}
	public void setData_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}
	
	
}
