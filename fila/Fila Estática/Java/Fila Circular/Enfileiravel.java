public interface Enfileiravel {
	void enfileirar(Object elemento);
	Object desenfileirar();
	Object espiar();
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}