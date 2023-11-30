//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	//insert
	void inserir(Object dado);
	//extract
	Object extrair();
	//get
	Object obterRaiz();
	
	//print
	String imprimir();
	//isEmpty
	boolean estaVazia();
	//isFull
	boolean estaCheia();
}