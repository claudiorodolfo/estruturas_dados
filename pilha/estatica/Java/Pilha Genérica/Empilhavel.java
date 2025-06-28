public interface Empilhavel<T> {
	//métodos principais
	void empilhar(T dado);	//C
	T espiar();				//R
	void atualizar(T dado);	//U
	T desempilhar();		//D

	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}