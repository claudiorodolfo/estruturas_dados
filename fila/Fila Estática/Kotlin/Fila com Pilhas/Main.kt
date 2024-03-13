fun main() {
    var fila: Enfileiravel = FilaComPilhas()
    fila.enfileirar("A");
    println("Frente: ${fila.frente()}")	
    fila.enfileirar("B")
    fila.enfileirar("C")
    fila.enfileirar("D")
    fila.desenfileirar()
    fila.enfileirar("E")
    val conteudo = fila.desenfileirar()
    fila.enfileirar("F")
    println("Frente: ${fila.frente()}")
    println("Frente: ${fila.frente()}")	
    fila.enfileirar("G");
    fila.enfileirar(conteudo)
    fila.enfileirar("I")
    fila.enfileirar("J")
    fila.enfileirar("K")				
    println("Fila=${fila.imprimir()}")
}