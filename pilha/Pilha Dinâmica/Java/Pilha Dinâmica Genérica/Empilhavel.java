public interface Empilhavel<T> {
	void empilhar(T dado);
	T desempilhar();
	T espiar();
	void atualizar(T dado);
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}