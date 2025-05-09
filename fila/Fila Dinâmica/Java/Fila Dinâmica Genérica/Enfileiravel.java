public interface Enfileiravel<T> {
	void enfileirar(T elemento);
	T desenfileirar();
	T espiar();
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}