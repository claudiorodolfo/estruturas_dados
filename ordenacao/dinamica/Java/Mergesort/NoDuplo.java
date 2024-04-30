public class NoDuplo {
	private NoDuplo anterior;
	private Object dado;
	private NoDuplo proximo;
	
	public NoDuplo getAnterior() {
		return anterior;
	}
	
	public Object getDado() {
		return dado;
	}
	
	public NoDuplo getProximo() {
		return proximo;
	}
	
	public void setAnterior(NoDuplo anterior) {
		this.anterior = anterior;
	}
	
	public void setDado(Object dado) {
		this.dado = dado;
	}
	
	public void setProximo(NoDuplo proximo) {
		this.proximo = proximo;
	}
}