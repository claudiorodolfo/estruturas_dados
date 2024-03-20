import kotlin.random.Random
//import kotlin.system.measureTimeMillis
//import kotlin.system.measureNanoTime

fun main()  {
    val gerador = Random    
    //10 números aleatórios entre 300 e 400
    val dados = Array<Int>(10) { gerador.nextInt(300, 401) }
    var b = InsertionSort(dados)

    b.imprimir()
    val tempoInicial = System.currentTimeMillis()
    val tempoInicialNano = System.nanoTime()
    b.ordenar()
    val tempoFinalNano = System.nanoTime()
    val tempoFinal = System.currentTimeMillis()
    
    b.imprimir()
    println("Tempo de execução: ${(tempoFinalNano - tempoInicialNano)} ns")
    println("Tempo de execução: ${(tempoFinal - tempoInicial)} ms")
}