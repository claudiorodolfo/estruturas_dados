package abp;

public class NoTriplo<T> {
	private T dado;
	private NoTriplo<T> genitor;
	private NoTriplo<T> esquerda;
	private NoTriplo<T> direita;

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}	

	public NoTriplo<T> getGenitor() {
		return genitor;
	}

	public void setGenitor(NoTriplo<T> genitor) {
		this.genitor = genitor;
	}

	public NoTriplo<T>  getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(NoTriplo<T> esquerda) {
		this.esquerda = esquerda;
	}	

	public NoTriplo<T> getDireita() {
		return direita;
	}

	public void setDireita(NoTriplo<T> direita) {
		this.direita = direita;
	}
}