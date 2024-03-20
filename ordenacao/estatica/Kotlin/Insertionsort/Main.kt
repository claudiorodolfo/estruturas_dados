import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.system.measureNanoTime

fun main()  {
    val gerador = Random    
    //10 números aleatórios entre 300 e 400, incluindo-os
    val dados = Array<Int>(10) { gerador.nextInt(300, 401) }
    var b = InsertionSort(dados)
    //cópia de b
    var b1 = b

    b.imprimir()
    val tempoNano = measureNanoTime {
        b.ordenar()
    }      
    //neste momento b já está ordenado, 
    //então tenho que ordenar novamente o array original, 
    //por isso da necessidade de b1
    val tempoMili = measureTimeMillis {
        b1.ordenar()
    }
 
    b.imprimir()
    println("Tempo de execução: $tempoNano ns")
    println("Tempo de execução: $tempoMili ms")
}