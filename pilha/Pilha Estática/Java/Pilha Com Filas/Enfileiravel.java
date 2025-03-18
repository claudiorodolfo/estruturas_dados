public interface Enfileiravel {
	void enfileirar(Object elemento);
	Object desenfileirar();
	Object frente();
	Object atualizarInicio();
	Object atualizarFim();	
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}