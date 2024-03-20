class SelectionSort(private var dados: Array<Int>) {

    //print
    fun imprimir() {
        for (item in dados) {
            print("$item ")
        }
        println("")
    }

    //swap
    fun trocar(i: Int, j: Int) {
        val temp = dados[i]
        dados[i] = dados[j]
        dados[j] = temp
    }

    //sort
    fun ordenar() {
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