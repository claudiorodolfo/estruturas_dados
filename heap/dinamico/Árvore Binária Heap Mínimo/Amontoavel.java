//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	void inserir(Object dado);
	Object extrair();
	Object obterRaiz();
		
	String imprimir();
	boolean estaVazia();
	boolean estaCheia();
}