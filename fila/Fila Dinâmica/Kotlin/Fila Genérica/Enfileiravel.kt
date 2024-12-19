interface Enfileiravel<T> {
    // Metodos principais
    fun enfileirar(dado: T?)
    fun desenfileirar(): T?
    fun atualizar(dado: T?)    
    fun frente(): T?
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}