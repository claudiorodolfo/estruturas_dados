interface Empilhavel<T> {
    // métodos principais
    fun empilhar(dado: T?)
    fun desempilhar(): T?
    fun topo(): T?
    
    // métodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}
