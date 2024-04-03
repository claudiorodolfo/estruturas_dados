public interface Amontoavel {
	fun inserir(dado: Int) 	//insert	C
	fun extrair(): Int?		//extract	D
	fun obter(): Int?		//get		R
	fun atualizar(dado: Int)//update	U
	
	fun imprimir(): String
	fun estaVazia(): Boolean
	fun estaCheia(): Boolean
}