//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel<T> {
	void inserir(T dado);	// C
	T obterRaiz();			// R
	T extrair();			// D

	//auxiliares
	String imprimir();
	boolean estaVazia();
	boolean estaCheia();
}