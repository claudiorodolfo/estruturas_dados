fun main() {
    var fila: Enfileiravel = FilaEstaticaCircular()
    fila.enfileirar("A");
    println("Espiar: ${fila.espiar()}")	
    fila.enfileirar("B")
    fila.enfileirar("C")
    fila.enfileirar("D")
    fila.desenfileirar()
    fila.enfileirar("E")
    val conteudo = fila.desenfileirar()
    fila.enfileirar("F")
    println("Espiar: ${fila.espiar()}")
    println("Espiar: ${fila.espiar()}")	
    fila.enfileirar("G");
    fila.enfileirar(conteudo)
    fila.enfileirar("I")
    fila.enfileirar("J")
    fila.enfileirar("K")				
    println("Fila=${fila.imprimir()}")
}