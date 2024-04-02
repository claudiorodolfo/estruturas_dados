//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	fun inserir(dado: Any?) 	//insert
	fun extrair(): Any?			//extract
	fun obterRaiz(): Any?			//get
	
	fun imprimir(): String
	fun estaVazia(): Boolean
	fun estaCheia(): Boolean
}