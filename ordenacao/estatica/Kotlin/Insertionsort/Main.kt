import kotlin.random.Random

fun main()  {
    val random = Random
    //10 números aleatórios entre 300 e 400
    val dados = Array<Int>(10) { random.nextInt(300, 401) }
    var b = InsertionSort(dados)
    b.imprimir()
    b.ordenar()
    b.imprimir()
}