class QuickSort(private var lista: ListaDinamica): Ordenavel {

	override fun imprimir() {
		println(lista.imprimir())
	}

	private fun trocar(no1: NoDuplo, no2: NoDuplo) {
		val temp = no1.dado
		no1.dado = no2.dado
		no2.dado = temp
	}

	override fun ordenar() {
		quicksort(lista.ponteiroInicio, lista.ponteiroFim)
	}	
	
	private fun quicksort(inicio: NoDuplo?, fim: NoDuplo?) {
		if (inicio != null && fim != null && 
				inicio != fim && inicio != fim.proximo) {
			val pivo = particionar(inicio, fim)
			quicksort(inicio, pivo.anterior)
			quicksort(pivo.proximo, fim)
		}
	}
	
	private fun particionar(inicio: NoDuplo, fim: NoDuplo): NoDuplo {
		val pivoValor = inicio.dado
		var i = inicio
		var j = inicio.proximo

		while (j != null && j != fim.proximo) {
			if ((j.dado as Int) < (pivoValor as Int)) {
				i = i.proximo ?: i			
				trocar(i, j)
			}
			j = j.proximo
		}		 		
		trocar(i, inicio)
		return i
	}
}