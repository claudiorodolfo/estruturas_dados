//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	void inserir(Long dado);	// C
	Long obterRaiz();			// R
	Long extrair();			// D

	//auxiliares
	String imprimir();
	boolean estaVazia();
	boolean estaCheia();
}