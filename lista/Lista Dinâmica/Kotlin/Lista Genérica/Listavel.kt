interface Listavel<T> {
    // Metodos principais
    fun anexar(dado: T?)                  //C
    fun inserir(posicao: Int, dado: T?)   //C
    fun selecionar(posicao: Int): T?      //R
    fun selecionarTodos(): Array<T?>      //R
    fun atualizar(posicao: Int, dado: T?) //U
    fun apagar(posicao: Int): T?          //D
    fun apagarTodos(): Array<T?>          //D
    fun limpar()                            //D
    
	//fun juntar(lista1: Listavel, lista2: Listavel): Listavel
	//fun subLista(inicio: Int, fim: Int): Listavel
    // Metodos auxiliares
    fun tamanho(): Int  
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}