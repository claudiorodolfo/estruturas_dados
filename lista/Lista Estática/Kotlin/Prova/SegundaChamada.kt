enum class Order {ASCENDING, DESCENDING}

class ListaEstaticaCircular {

    private var dados: Array<Int> = arrayOf(5, 7, 2, 6, 1, 4, 3, 0, 9, 8)

//Ordena - Algoritmo Bubblesort
//Para 1 único elemento considera-se que já está ordenado
//É necessário um laço mais externo que a cada iteração, 
//faça com que o maior elemento em sua faixa de elementos, vá para 
//sua posição correta (último, penúltimo, antepenúltimo, etc.)
//Laço mais interno que compara nodos adjacentes, e troca os seus conteúdos caso
//estejam decrescente.
    fun sort(by: Order) {
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

        if (by == Order.DESCENDING) {
            for (i in 0 until dados.size-1) {
                for (j in 0 until dados.size-i-1) {
                    if (dados[j] < dados[j+1])
                    val temp = dados[i]
                    dados[i] = dados[j]
                    dados[j] = temp          
                }
            }
        }

    }
}

class Questao2e3 {

    p1:Empilhavel = PilhaEstatica(100)
    p2:Empilhavel = PilhaEstaticaInvertida(100)

    fun isPalindrome(data: String): Boolean {
        var retorno = true;

        for (i in 0 until (data.length()/2))
            p1.empilhar(data.charAt(i))
    
        for (i in (data.length()/2+1) until data.length()) {
            var parte1 = (Char) p1.desempilhar()
            var parte2 = data.charAt(i))
            if (parte1 != parte2) {
                retorno = false
                break
            }
        }
        return retorno
    }

    fun decToBin(data: String): String {
        var dividendo = Integer.parseInt(data)
        while (dividendo > 1) {
            resto = dividendo % 2
            quociente = dividendo / 2
            dividendo = quociente
            p2.empilhar(resto)
        }
        p2.empilhar(dividendo)
        return p2.imprimir()
    }    
}