class MergeSort(private var lista: ListaDinamica): Ordenavel {

	override fun imprimir() {
		println(lista.imprimir())
	}

	private fun trocar(no1: NoDuplo, no2: NoDuplo) {
		val temp = no1.dado
		no1.dado = no2.dado
		no2.dado = temp
	}

	override fun ordenar() {
		mergesort(lista)
	}
	
    private fun mergesort(lista: ListaDinamica) {

    }

    //merge
    fun mesclar(esq: NoDuplo?, dir: NoDuplo?): NoDuplo? {

    }
}