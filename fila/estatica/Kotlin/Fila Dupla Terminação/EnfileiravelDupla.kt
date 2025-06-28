interface EnfileiravelDupla {
    // Metodos principais
    fun enfileirarInicio(dado: Any?)
    fun enfileirarFim(dado: Any?) //mesmo que enfileirar da Fila Normal
    fun desenfileirarInicio(): Any? //mesmo que desenfileirar da Fila Normal
    fun desenfileirarFim(): Any?
    fun atualizarInicio(dado: Any?)  
    fun atualizarFim(dado: Any?)          
    fun frente(): Any? //mesmo que espiar da Fila Normal
    fun tras(): Any?
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimirFrentePraTras(): String //mesmo que imprimir da Fila Normal
    fun imprimirTrasPraFrente(): String
}
