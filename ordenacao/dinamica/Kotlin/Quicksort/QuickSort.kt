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

    private fun particao(NoInferior: NoDuplo?, NoSuperior: NoDuplo?): NoDuplo? {
        val dadoPivo = NoSuperior?.dado ?: 0
        var i = NoInferior
        for (j in NoInferior?.proximo? until NoSuperior)) {
            if (j?.dado ?: 0 < dadoPivo) {
                i = i?.proximo
                trocar(i!!, j!!)
            }
        }
        i = i?.proximo
        troca(i!!, NoSuperior!!)
        return i
    }

    private fun quickSort(NoInferior: NoDuplo?, NoSuperior: NoDuplo?) {
        if (NoInferior != null && NoSuperior != null && 
                NoInferior != NoSuperior && 
                NoInferior.anterior != NoSuperior) {
            val pivo = particao(NoInferior, NoSuperior)
            quickSort(NoInferior, pivo?.anterior)
            quickSort(pivo?.proximo, NoSuperior)
        }
    }
	
    //sort
    override fun ordenar() {
        quickSort(lista.ponteiroInicio, lista.ponteiroFim)
    }
}