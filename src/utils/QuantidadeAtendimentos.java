package utils;

public class QuantidadeAtendimentos {
	public int primeira_tentativas = 0;
	public int segunda_tentativas = 0;
	public int terceira_tentivas = 0;
	public int getPrimeira_tentativas() {
		return primeira_tentativas;
	}
	public void setPrimeira_tentativas(int primeira_tentativas) {
		this.primeira_tentativas = primeira_tentativas;
	}
	public int getSegunda_tentativas() {
		return segunda_tentativas;
	}
	public void setSegunda_tentativas(int segunda_tentativas) {
		this.segunda_tentativas = segunda_tentativas;
	}
	public int getTerceira_tentivas() {
		return terceira_tentivas;
	}
	public void setTerceira_tentivas(int terceira_tentivas) {
		this.terceira_tentivas = terceira_tentivas;
	}
	public int getEncaminhamento_humano() {
		return encaminhamento_humano;
	}
	public void setEncaminhamento_humano(int encaminhamento_humano) {
		this.encaminhamento_humano = encaminhamento_humano;
	}
	public int encaminhamento_humano = 0;
}
