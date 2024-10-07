class ListaEstaticaCircular {
	private var dados: Array<Any?>
	private var quantidade = 0
	private var ponteiroInicio = 0
	private var ponteiroFim = -1

	constructor(tamanho: Int) { dados = arrayOfNulls(tamanho) }
	fun estaCheia(): Boolean { return quantidade == dados.size }	
	fun estaVazia(): Boolean { return quantidade == 0 }

    //*************Questão 2****************
    //Provas Tipo 1 e 2
	fun apagar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
				val posicaoFisica = logicaPraFisica(posicao)
				dadoAux = dados[posicaoFisica]
				var ponteiroAux = posicaoFisica
				for (i in 0 ..< posicao) {
					var proximo = ponteiroAux
					var anterior = retroceder(proximo)

					dados[proximo] = dados[anterior]
					ponteiroAux = retroceder(ponteiroAux)
				}
				ponteiroInicio = avancar(ponteiroInicio)
				quantidade--           
			} else {
				println("Invalid Index!")
			}
		} else {
			println("List is Empty!")
		}
		return dadoAux
	} 

    //Provas Tipo 3 e 4       
	fun inserir(posicao: Int, dado: Any?) {
		if (!estaCheia()) {
			if (posicao >= 0 && posicao <= quantidade) {
				val posicaoFisica = logicaPraFisica(posicao)
				var ponteiroAux = retroceder(ponteiroInicio)
				for (i in 0 ..< posicao) {
					var anterior = ponteiroAux
					var proximo = avancar(anterior)

					dados[anterior] = dados[proximo]
					ponteiroAux = avancar(ponteiroAux)
				}
				dados[retroceder(posicaoFisica)] = dado
				ponteiroInicio = retroceder(ponteiroInicio)
				quantidade++
			} else {
				println("Invalid Index")
			}
		} else {
			println("List is full!")
		}
	}

    //*************Questão 3****************
    //Provas Tipo 2 e 4
	fun imprimirDeInicioParaPosicao(posicao: Int): String? {
		var resultado: String? = null
		if (!estaVazia()) 
			if (posicao >= 0 && posicao < quantidade) {
				resultado = "["
				val ponteiroAux = ponteiroInicio
				for (i in 0 .. posicao) {
					resultado += "${dados[(ponteiroAux+i) % dados.size]}"
					if (i != posicao) resultado += ", "		
				}
				resultado += "]"
			} else {
				println("Invalid Index")
			}
        else
        	resultado = "[]"

		return resultado
	}

    //Provas Tipo 1 e 3
	fun imprimirDePosicaoParaFim(posicao: Int): String? {
		var resultado: String? = null
		if (!estaVazia()) 
			if (posicao >= 0 && posicao < quantidade) {
				resultado = "["
				val ponteiroAux = logicaPraFisica(posicao)
				for (i in 0 .. (quantidade-posicao-1)) {
					resultado += "${dados[(ponteiroAux+i) % dados.size]}"
					if (i != quantidade-posicao-1) resultado += ", "	
				}
				resultado += "]"
			} else {
				println("Invalid Index")
			}
        else
        	resultado = "[]"

		return resultado	
	} 
    
    //Alguns métodos auxiliares para economizar a escrita de código
    private fun logicaPraFisica(posicao: Int): Int {
		return (ponteiroInicio + posicao) % dados.size
	}

	private fun avancar(ponteiro: Int): Int {
		var aux = ponteiro
		aux++
		return if (aux == dados.size) 0 else aux
	}

	private fun retroceder(ponteiro: Int): Int {
		var aux = ponteiro
		aux--
		return if (aux == -1) dados.size-1 else aux
	}
}