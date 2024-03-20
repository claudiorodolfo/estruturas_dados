class Bubblesort(private var dados: Array<Int>) {

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
            for (j in 0 until dados.size-i-1) {
                if (dados[j] > dados[j+1])
                    trocar(j, j+1)             
            }
        }
    }
}