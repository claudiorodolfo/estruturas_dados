enum class Order {ASCENDING, DESCENDING}

class ListaEstaticaCircular {

    private var dados: Array<Int> = arrayOf(5, 7, 2, 6, 1, 4, 3, 0, 9, 8)

    //Algoritmo Bubblesort
    fun sort(by: Order) {
        //É necessário um laço mais externo que a cada iteração, 
        //faça com que o maior elemento em sua faixa de elementos, vá para 
        //sua posição correta (último, penúltimo, antepenúltimo, etc.)
        //Laço mais interno que compara dados adjacentes, e troca os seus 
        //conteúdos caso estejam decrescente.        
        if (by == Order.ASCENDING) {
            for (i in 0 until dados.size-1) {
                for (j in 0 until dados.size-i-1) {
                    if (dados[j] > dados[j+1]) {
                        val temp = dados[i]
                        dados[i] = dados[j]
                        dados[j] = temp
                    }  
                }
            }
        }
        //É necessário um laço mais externo que a cada iteração, 
        //faça com que o menor elemento em sua faixa de elementos, vá para 
        //sua posição correta (último, penúltimo, antepenúltimo, etc.)
        //Laço mais interno que compara dados adjacentes, e troca os seus 
        //conteúdos caso estejam crescente.
        if (by == Order.DESCENDING) {
            for (i in 0 until dados.size-1) {
                for (j in 0 until dados.size-i-1) {
                    if (dados[j] < dados[j+1]) {
                        val temp = dados[i]
                        dados[i] = dados[j]
                        dados[j] = temp 
                    }         
                }
            }
        }

    }
}

class Questao2e3 {

    var p1: Empilhavel = PilhaEstatica()
    var p2: Empilhavel = PilhaEstaticaInvertida()

    fun isPalindrome(data: String): Boolean {
        var retorno = true

        for (i in 0 until (data.length/2))
            p1.empilhar(data[i])
    
        for (i in (data.length/2+1) until data.length) {
            val parte1 = p1.desempilhar() as Char
            var parte2 = data[i]
            if (parte1 != parte2) {
                retorno = false
                break
            }
        }
        return retorno
    }

    fun decToBin(data: String): String {
        var dividendo = data.toInt()
        while (dividendo > 1) {
            val resto = dividendo % 2
            val quociente = dividendo / 2
            dividendo = quociente
            p2.empilhar(resto)
        }
        p2.empilhar(dividendo)
        return p2.imprimir()
    }    
}