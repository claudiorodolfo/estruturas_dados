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

    private fun merge(noEsquerdo: NoDuplo?, noDireito: NoDuplo?): NoDuplo? {
        var result: NoDuplo? = null
        if (noEsquerdo == null) return noDireito
        if (noDireito == null) return noEsquerdo

        if (noEsquerdo.data <= noDireito.data) {
            result = noEsquerdo
            result.next = merge(noEsquerdo.next, noDireito)
            result.next?.prev = result
        } else {
            result = noDireito
            result.next = merge(noEsquerdo, noDireito.next)
            result.next?.prev = result
        }
        return result
    }
	
    private fun mergeSort(inicio: NoDuplo?): NoDuplo? {
        if (inicio == null || inicio.next == null) return inicio
        val (noEsquerdo, noDireito) = splitList(inicio)
        val sortednoEsquerdo = mergeSort(noEsquerdo)
        val sortednoDireito = mergeSort(noDireito)
        return merge(sortednoEsquerdo, sortednoDireito)
    }
	
    private fun splitList(start: NoDuplo?): Pair<NoDuplo?, NoDuplo?> {
        var fast = start
        var slow = start
        while (fast?.next != null && fast.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        val mid = slow?.next
        slow?.next = null
        return Pair(start, mid)
    }
	
    //sort
    override fun ordenar() {
        head = mergeSort(lista.ponteiroInicio)
        var temp = lista.ponteiroInicio
        while (temp?.proximo != null) {
            temp = temp.pfoximo
        }
        lista.ponteiroFim = temp
    }
}