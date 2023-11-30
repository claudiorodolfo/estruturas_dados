public interface Empilhavel<T> {
	//métodos principais
	void empilhar(T dado);
	T desempilhar();
	T topo();
	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}