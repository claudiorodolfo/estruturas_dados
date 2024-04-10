fun main() {
    var fila: Enfileiravel = FilaDinamica(7)
    fila.enfileirar("Instituto")
    fila.enfileirar("Federal")
    println("Topo: ${fila.frente()}")
    fila.enfileirar("de")
    fila.enfileirar("Educação")
    fila.enfileirar("Ciência")
    fila.enfileirar("e")
    println("Topo: ${fila.frente()}")
    val conteudo = fila.desenfileirar()
    fila.desenfileirar()
    fila.enfileirar("Tecnologia")
    fila.enfileirar("da")
    fila.enfileirar("Bahia")
    fila.enfileirar(fila.desenfileirar())
    fila.enfileirar(conteudo)
    println("fila=${fila.imprimir()}")
}