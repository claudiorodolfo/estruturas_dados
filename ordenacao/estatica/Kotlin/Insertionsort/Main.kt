fun main()  {
    val dados = arrayOf(5, 7, 2, 6, 1, 4, 3, 0, 9, 8)
    var b = InsertionSort(dados)
    b.imprimir()
    b.ordenar()
    b.imprimir()
}