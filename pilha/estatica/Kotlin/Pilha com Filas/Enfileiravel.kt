interface Enfileiravel {
    // Metodos principais
    fun enfileirar(dado: Any?)
    fun desenfileirar(): Any?
    fun atualizar(dado: Any?)       
    fun frente(): Any?
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}