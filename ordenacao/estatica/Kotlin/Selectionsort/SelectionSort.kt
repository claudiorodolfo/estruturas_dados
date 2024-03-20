class SelectionSort(private var dados: Array<Int>): Ordenavel {

    //swap
    private fun trocar(i: Int, j: Int) {
        val temp = dados[i]
        dados[i] = dados[j]
        dados[j] = temp
    }

    //print
    override fun imprimir() {
        for (item in dados) {
            print("$item ")
        }
        println("")
    }
	
    //sort
    override fun ordenar() {
        for (i in 0 until dados.size-1) {
            var indiceMenor = i
            for (j in i+1 until dados.size) {
                if (dados[j] < dados[indiceMenor])
                    indiceMenor = j
            }
            trocar(i, indiceMenor)
        }
    }
}