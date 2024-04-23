//Hashable
public interface Espalhavel {
	//put(), armazena um par de objetos especificados
	fun adicionar(mapa: Mapa)
	//remove(), remove o  objeto com a chave especificada
	fun remover(chave: String): Any?
	//containsKey(), consulta se uma determinada chave existe na tabela
	fun contemChave(chave: String): Boolean
	//get(), retorna o objeto  associado a chave especificada
	fun buscar(chave: String): Any?
	//size(), retorna o número de elementos da estrutura
	fun tamanho(): Int
	
	//contains(), consulta se um determinado valor existe na tabela
	//fun contemValor(dado: Any): Boolean
	//getAll(), retorna todos os objetos armazenados
	//fun buscarTodos(): Array<Any>

	//informa se a estrutura de dados está vazia
	fun estaVazia(): Boolean
	//imprime o conteúdo da estrutura de dados
	fun imprimir(): String
}