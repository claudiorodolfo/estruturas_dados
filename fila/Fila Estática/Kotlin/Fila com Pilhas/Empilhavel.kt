//https://www.javainuse.com/java2kot
//https://play.kotlinlang.org/
interface Empilhavel {
    // Metodos principais
    fun empilhar(dado: Any?)
    fun desempilhar(): Any?
    fun espiar(): Any?
    fun atualizar(dado: Any?)
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}