class QuickSort(private var lista: Listavel): Ordenavel {

    //swap
    private fun trocar(no1: NoDuplo, no2: NoDuplo) {
        val temp = no1.dado
        no1.dado = no2.dado
        no2.dado = temp
    }

    //print
	override fun imprimir(): String {
		var ponteiroAuxiliar = lista.ponteiroInicio
		var resultado = "["
		for (i in 0 until quantidade) {
			resultado += ponteiroAuxiliar?.dado
			if (i != quantidade-1)
				resultado += ","
			
			ponteiroAuxiliar = ponteiroAuxiliar?.proximo
		}
		return "$resultado]"
	}

    private fun quickSort(inferior: NoDuplo?, superior: NoDuplo?) {
        if (inferior != null && superior != null && inferior != superior && inferior.anterior != superior) {
            val pivo = particao(inferior, superior)
            quickSort(inferior, pivo?.anterior)
            quickSort(pivo?.proximo, superior)
        }
    }
	
    //sort
    override fun ordenar() {
        quickSort(lista.ponteiroInicio, lista.ponteiroFim)
    }
}