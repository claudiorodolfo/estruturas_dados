//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	void inserir(Object dado);	// C
	Object obterRaiz();			// R
	Object extrair();			// D

	//auxiliares
	String imprimir();
	boolean estaVazia();
	boolean estaCheia();
}