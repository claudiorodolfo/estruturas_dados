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

    private fun merge(left: Node?, right: Node?): Node? {
        var result: Node? = null
        if (left == null) return right
        if (right == null) return left

        if (left.data <= right.data) {
            result = left
            result.next = merge(left.next, right)
            result.next?.prev = result
        } else {
            result = right
            result.next = merge(left, right.next)
            result.next?.prev = result
        }
        return result
    }
	
    private fun mergeSort(inicio: Node?): Node? {
        if (inicio == null || inicio.next == null) return inicio
        val (left, right) = splitList(inicio)
        val sortedLeft = mergeSort(left)
        val sortedRight = mergeSort(right)
        return merge(sortedLeft, sortedRight)
    }
	
    private fun splitList(start: Node?): Pair<Node?, Node?> {
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
        head = mergeSort(head)
        var temp = head
        while (temp?.next != null) {
            temp = temp.next
        }
        tail = temp
    }
}