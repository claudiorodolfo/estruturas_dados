public class NoDuplo<T> {
	private NoDuplo<T> anterior;
	private T dado;
	private NoDuplo<T> proximo;
	
	public NoDuplo() {
	}

	public NoDuplo(T dado) {
		this.dado = dado;
	}
		
	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	public NoDuplo<T> getAnterior() {
		return anterior;
	}

	public NoDuplo<T> getProximo() {
		return proximo;
	}
	
	public void setAnterior(NoDuplo<T> anterior) {
		this.anterior = anterior;
	}
	
	public void setProximo(NoDuplo<T> proximo) {
		this.proximo = proximo;
	}
}