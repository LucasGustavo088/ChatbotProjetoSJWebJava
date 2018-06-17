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
		return quantidadeAtendimentos;
	}
	public void setQuantidade_atendimentos(QuantidadeAtendimentos quantidade_atendimentos) {
		this.quantidadeAtendimentos = quantidade_atendimentos;
	}
	public ArrayList<Atendimento> atendimentos = new ArrayList<Atendimento>();
	public QuantidadeAtendimentos quantidadeAtendimentos;
}
