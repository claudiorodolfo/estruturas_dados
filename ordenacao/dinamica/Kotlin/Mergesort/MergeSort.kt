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
	
	fun mergesort(lista: ListaDinamica) {
		val inicio = lista.ponteiroInicio
		if (inicio == null || inicio.proximo == null) {
			return  // Lista vazia ou com um único elemento
		}
	
		// Dividir a lista ao meio
		val (esquerda, direita) = dividirLista(lista)
	
		// Recursivamente ordenar as partes
		mergesort(esquerda)
		mergesort(direita)
	
		// Mesclar as partes ordenadas
		lista.ponteiroInicio = merge(esquerda.inicio, direita.inicio)
	}

    //merge
    // Função para mesclar duas listas ordenadas
	private fun mesclar(esq: NoDuplo?, dir: NoDuplo?): NoDuplo? {
    	if (esq == null) return dir
    	if (dir == null) return esq

	    var resultado: NoDuplo? = null
	    if (esq.valor <= dir.valor) {
        	resultado = esq
        	resultado.proximo = mesclar(esq.proximo, dir)
        	resultado.proximo?.anterior = resultado
    	} else {
	        resultado = dir
        	resultado.proximo = mesclar(esq, dir.proximo)
        	resultado.proximo?.anterior = resultado
    	}
    	return resultado
	}

// Função auxiliar para dividir a lista em duas partes
fun dividirLista(lista: ListaDinamica): Pair<ListaDinamica, ListaDinamica> {
    val inicio = lista.ponteiroInicio ?: return Pair(ListaDinamica(), ListaDinamica())

    var lento = inicio
    var rapido = inicio.proximo

    // O rápido se move duas vezes mais rápido que o lento
    while (rapido != null) {
        rapido = rapido.proximo
        if (rapido != null) {
            lento = lento!!.proximo
            rapido = rapido.proximo
        }
    }

    // lento é o nó do meio
    val direita = ListaDinamica()
    direita.inicio = lento?.proximo
    lento?.proximo = null  // Separar a lista na metade

    val esquerda = ListaDinamica()
    esquerda.inicio = inicio

    return Pair(esquerda, direita)
}
	
}