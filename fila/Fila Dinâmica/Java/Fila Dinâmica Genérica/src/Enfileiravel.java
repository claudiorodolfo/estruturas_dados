public interface Enfileiravel<T> {
	void enfileirar(T elemento);	//C
	T espiar();						//R
	void atualizar(T elemento);		//U
	T desenfileirar();				//D

	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}