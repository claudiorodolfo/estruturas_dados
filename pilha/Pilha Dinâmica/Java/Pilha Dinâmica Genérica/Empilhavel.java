public interface Empilhavel<T> {
	void empilhar(T dado);
	T desempilhar();
	T topo();
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}