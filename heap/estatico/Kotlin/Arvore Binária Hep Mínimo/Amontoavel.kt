//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	fun inserir(dado: Int) 	//insert
	fun extrair(): Int		//extract
	fun obter(): Int		//get
	
	fun imprimir(): String
	fun estaVazia(): Boolean
	fun estaCheia(): Boolean

	//private fun indicePai(filho: Int): Int
	//private fun trocar(i: Int, j: Int)
	//private fun ajustarAbaixo(pai: Int) //heapifyDown
	//private fun ajustarAcima(indice: Int) //heapifyUp
}