//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel<T> {
	void inserir(T dado);
	T extrair();
	T obterRaiz();
	
	String imprimir();
	boolean estaVazia();
	boolean estaCheia();
}