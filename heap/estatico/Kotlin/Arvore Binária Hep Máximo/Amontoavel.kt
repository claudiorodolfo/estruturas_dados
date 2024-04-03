//Heap = Monte
//Heapify = Organizar dados na forma de um Heap
//Heapifiable = Amontoavel
public interface Amontoavel {
	fun inserir(dado: Int) 	//insert	C
	fun extrair(): Int?		//extract	D
	fun obter(): Int?		//get		R
	fun atualizar(dado: Int)//update	U
	
	fun imprimir(): String
	fun estaVazia(): Boolean
	fun estaCheia(): Boolean

	//private fun indiceFilhoEsquerda(indicePai: Int): Int	
	//private fun indiceFilhoDireita(indicePai: Int): Int	
	//private fun indicePai(indiceFilho: Int): Int
	//private fun trocar(i: Int, j: Int)	//swap
	//private fun ajustarAbaixo(pai: Int) 	//heapifyDown
	//private fun ajustarAcima(indice: Int) //heapifyUp
}