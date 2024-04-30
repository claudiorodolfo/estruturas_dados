fun main()  {
    var lista: Listavel = ListaDinamica(20)
	lista.anexar(5)
	lista.anexar(7)
	lista.anexar(2)
	lista.anexar(6)
	lista.anexar(1)
	lista.anexar(4)
	lista.anexar(3)
	lista.anexar(0)
	lista.anexar(9)
	lista.anexar(8)
	
    var b: Ordenavel = QuickSort(lista)
    b.imprimir()
    b.ordenar()
    b.imprimir()
}