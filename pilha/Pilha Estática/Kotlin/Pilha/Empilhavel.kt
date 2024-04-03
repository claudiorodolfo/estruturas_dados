//https://www.javainuse.com/java2kot
//https://play.kotlinlang.org/
interface Empilhavel {
    // Metodos principais
    fun empilhar(dado: Any?)    //C
    fun atualizar(dado: Any?)   //U
    fun espiar(): Any?          //R
    fun desempilhar(): Any?     //D
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}