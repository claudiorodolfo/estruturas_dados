fun main() {
	val lista = ListaDinamica(20)
	val dados = arrayOf(5, 7, 2, 6, 1, 4, 3, 0, 9, 8)
	for (dado in dados)
		lista.anexar(dado)
	
	var m = MergeSort(lista)
	m.imprimir()
	m.ordenar()
	m.imprimir()
}