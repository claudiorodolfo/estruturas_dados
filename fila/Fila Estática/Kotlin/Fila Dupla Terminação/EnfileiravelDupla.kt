interface EnfileiravelDupla {
    // Metodos principais
    fun enfileirarInicio(dado: Any?)
	fun enfileirarFim(dado: Any?) //mesmo que enfileirar da Fila Normal
    fun desenfileirarInicio(): Any? //mesmo que desenfileirar da Fila Normal
	fun desenfileirarFim(): Any?
    fun espiarInicio(): Any? //mesmo que espiar da Fila Normal
    fun espiarFim(): Any?
    // Metodos auxiliares
    fun estaCheia(): Boolean
    fun estaVazia(): Boolean
    fun imprimir(): String
}