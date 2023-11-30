public class NoTriplo {
	private NoTriplo genitor;
	private NoTriplo esquerda;
	private NoTriplo direita;
	private Object dado;

	public NoTriplo getGenitor() {
		return genitor;
	}	

	public NoTriplo getEsquerda() {
		return esquerda;
	}
	
	public NoTriplo getDireita() {
		return direita;
	}

	public Object getDado() {
		return dado;
	}

	public void setPai(NoTriplo genitor) {
		this.genitor = genitor;
	}
		
	public void setEsquerda(NoTriplo esquerda) {
		this.esquerda = esquerda;
	}
	
	public void setDireita(NoTriplo direita) {
		this.direita = direita;
	}

	public void setDado(Object dado) {
		this.dado = dado;
	}	
}