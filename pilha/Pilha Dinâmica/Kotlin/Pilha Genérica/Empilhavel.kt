interface Empilhavel<T> {
    // Metodos principais
    fun empilhar(dado: T?)
    fun desempilhar(): T?
    fun espiar(): T?
    fun atualizar(dado: T?)     
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}