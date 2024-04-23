interface Listavel {
    // Metodos principais
    fun anexar(dado: Any?)                  //C
    fun inserir(posicao: Int, dado: Any?)   //C
    fun selecionar(posicao: Int): Any?      //R
    fun selecionarTodos(): Array<Any?>      //R
    //fun selecionarProximo(): Any?           //R
    //fun selecionarAnterior(): Any?          //R   
    fun atualizar(posicao: Int, dado: Any?) //U
    fun apagar(posicao: Int): Any?          //D

    // Metodos auxiliares
    fun tamanho(): Int  
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}