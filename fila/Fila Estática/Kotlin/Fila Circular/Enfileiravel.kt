interface Enfileiravel {
    // Metodos principais
    fun enfileirar(dado: Any?)  //C
    fun atualizar(dado: Any?)   //U
    fun frente(): Any?          //R
    fun desenfileirar(): Any?   //D    
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}