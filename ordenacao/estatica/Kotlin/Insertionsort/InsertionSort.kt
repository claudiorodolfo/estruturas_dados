class InsertionSort(private var dados: Array<Int>): Ordenavel {

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
        for (i in 1 until dados.size) {
            var j = i
            while ((j > 0) && dados[j] < dados[j-1]) {
                trocar(j, j-1)
                j--
            }
        }
    }
}