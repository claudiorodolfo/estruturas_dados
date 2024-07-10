class QuickSort(private var lista: ListaDinamica): Ordenavel {

    override fun imprimir(): String {
        return lista.imprimir()
    }

	override fun ordenar() {
		quicksort(lista.ponteiroInicio, lista.ponteiroFim)
	}	
	
	private fun quicksort(inicio: NoDuplo?, fim: NoDuplo?) {
		if (inicio != null && fim != null && inicio != fim && inicio != fim.proximo) {
			val pivo = particionar(inicio, fim)
			quicksort(inicio, pivo.anterior)
			quicksort(pivo.proximo, fim)
		}
	}
	
	private fun particionar(inicio: NoDuplo, fim: NoDuplo): NoDuplo {
		val pivoValor = fim.dado
		var i = inicio.anterior
	
		var j = inicio
		while (j != fim) {
			if (j.dado as Int <= pivoValor as Int) {
				i = if (i == null) inicio else i.proximo
                trocar(i!!, j)
			}
			j = j.proximo!!
		}
	
		i = if (i == null) inicio else i.proximo
        trocar(i!!, fim)
	
		return i
	}

    private fun trocar(no1: NoDuplo, no2: NoDuplo) {
        val temp = no1.dado
        no1.dado = no2.dado
        no2.dado = temp
    }    
}