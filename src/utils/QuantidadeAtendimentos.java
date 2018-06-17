package utils;

public class QuantidadeAtendimentos {
	public int primeiraTentativas = 0;
	public int segundaTentativas = 0;
	public int terceiraTentativas = 0;
	public int getPrimeira_tentativas() {
		return primeiraTentativas;
	}
	public void setPrimeira_tentativas(int primeiraTentativas) {
		this.primeiraTentativas = primeiraTentativas;
	}
	public int getSegunda_tentativas() {
		return segundaTentativas;
	}
	public void setSegunda_tentativas(int segunda_tentativas) {
		this.segundaTentativas = segunda_tentativas;
	}
	public int getTerceira_tentivas() {
		return terceiraTentativas;
	}
	public void setTerceira_tentivas(int terceira_tentivas) {
		this.terceiraTentativas = terceira_tentivas;
	}
	public int getEncaminhamento_humano() {
		return encaminhamentoHumano;
	}
	public void setEncaminhamento_humano(int encaminhamento_humano) {
		this.encaminhamentoHumano = encaminhamento_humano;
	}
	public int encaminhamentoHumano = 0;
}
