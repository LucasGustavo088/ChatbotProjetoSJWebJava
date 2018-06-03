package model;
import java.util.*;

public class Atendimento{
	
	private int id;
	private int ativo;
	private Date data_atualizacao; 
	private Date data_criacao;
	private int id_atendente;
	private String status;
	private int id_cliente;
	public String duracao_atendimento;
	private int qtd_tentativa;
	private Date data_finalizacao;
	public Cliente cliente;
	public ArrayList<AtendimentoHasPergunta> atendimentoHasPergunta = new ArrayList<AtendimentoHasPergunta>();
	public ArrayList<AtendimentoHasResposta> atendimentoHasResposta = new ArrayList<AtendimentoHasResposta>();
	public List<Chat> chat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getId_atendente() {
		return id_atendente;
	}
	public void setId_atendente(int id_atendente) {
		this.id_atendente = id_atendente;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getDuracao_atendimento() {
		return duracao_atendimento;
	}
	public void setDuracao_atendimento(String duracao_atendimento) {
		this.duracao_atendimento = duracao_atendimento;
	}
	public int getQtd_tentativa() {
		return qtd_tentativa;
	}
	public void setQtd_tentativa(int qtd_tentativa) {
		this.qtd_tentativa = qtd_tentativa;
	}
	public Date getData_finalizacao() {
		return data_finalizacao;
	}
	public void setData_finalizacao(Date data_finalizacao) {
		this.data_finalizacao = data_finalizacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<AtendimentoHasPergunta> getAtendimentoHasPergunta() {
		return atendimentoHasPergunta;
	}
	public void setAtendimentoHasPergunta(ArrayList<AtendimentoHasPergunta> atendimentoHasPergunta) {
		this.atendimentoHasPergunta = atendimentoHasPergunta;
	}
	public ArrayList<AtendimentoHasResposta> getAtendimentoHasResposta() {
		return atendimentoHasResposta;
	}
	public void setAtendimentoHasResposta(ArrayList<AtendimentoHasResposta> atendimentoHasResposta) {
		this.atendimentoHasResposta = atendimentoHasResposta;
	}
	public List<Chat> getChat() {
		return chat;
	}
	public void setChat(List<Chat> chat) {
		this.chat = chat;
	}
	
	
	
	
	

}
