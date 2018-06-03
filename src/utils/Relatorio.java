package utils;

import java.util.ArrayList;

import model.Atendimento;

public class Relatorio {
	
	public Filtro filtro;
	public Filtro getFiltro() {
		return filtro;
	}
	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	public ArrayList<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(ArrayList<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	public QuantidadeAtendimentos getQuantidade_atendimentos() {
		return quantidade_atendimentos;
	}
	public void setQuantidade_atendimentos(QuantidadeAtendimentos quantidade_atendimentos) {
		this.quantidade_atendimentos = quantidade_atendimentos;
	}
	public ArrayList<Atendimento> atendimentos = new ArrayList<Atendimento>();
	public QuantidadeAtendimentos quantidade_atendimentos;
}
