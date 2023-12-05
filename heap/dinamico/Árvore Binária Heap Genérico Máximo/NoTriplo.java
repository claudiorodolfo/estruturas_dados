public class NoTriplo<T> {
	private NoTriplo<T> genitor;
	private NoTriplo<T> esquerda;
	private NoTriplo<T> direita;
	private T dado;

	public NoTriplo<T> getGenitor() {
		return genitor;
	}	

	public NoTriplo<T> getEsquerda() {
		return esquerda;
	}
	
	public NoTriplo<T> getDireita() {
		return direita;
	}

	public T getDado() {
		return dado;
	}

	public void setGenitor(NoTriplo<T> genitor) {
		this.genitor = genitor;
	}
		
	public void setEsquerda(NoTriplo<T> esquerda) {
		this.esquerda = esquerda;
	}
	
	public void setDireita(NoTriplo<T> direita) {
		this.direita = direita;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}	
}