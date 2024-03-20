class InsertionSort(private var dados: Array<Int>) {

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
        for (i in 1 until dados.size) {
            val pivo =  dados[i]
            var j = i - 1
            while (j >= 0 && dados[j] > pivo) {
                dados[j+1] = dados[j]
                j--
            }
            dados[j+1] = pivo
        }
    }
}