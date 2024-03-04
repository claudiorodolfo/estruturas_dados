interface Empilhavel {
    // Metodos principais
    fun empilhar(dado: Any?)
    fun desempilhar(): Any?
    fun topo(): Any?
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}