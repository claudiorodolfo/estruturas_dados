class Gabarito(val tamanho: Int = 10) {
	private var dados: Array<Any?> = arrayOfNulls(tamanho)
	private var quantidade = 0
	private var ponteiroInicio = 0
	private var ponteiroFim = -1
	
	fun estaCheia(): Boolean { return quantidade == dados.size}
	fun estaVazia(): Boolean { return quantidade == 0 }	
	
    private fun logicaPraFisica(posicao: Int): Int {
        return (ponteiroInicio + posicao) % dados.size
    }

    private fun avancar(ponteiro: Int): Int {
        var aux = ponteiro
        aux.inc()
        return if (aux == dados.size) 0 else aux
    }

    private fun retroceder(ponteiro: Int): Int {
        var aux = ponteiro
        aux.dec()
        return if (aux == -1) dados.size-1 else aux
    }

    private fun trocar(i: Int, j: Int) {
        val dadoAux = dados[i]
        dados[i] = dados[j]
        dados[j] = dadoAux
    }

	//Questão 00
	fun inserirInicio(dado: Any?) {
		if (!estaCheia()) {
            ponteiroInicio = retroceder(ponteiroInicio)
            dados[ponteiroInicio] = dado
			quantidade.inc() 
            //primeira inserção, estado inicial
            if (ponteiroFim == -1)
                ponteiroFim = ponteiroInicio
		}
	}

	//Questão 01
	fun inserirFim(dado: Any?) {
		if (!estaCheia()) {
			ponteiroFim = avancar(ponteiroFim)
			dados[ponteiroFim] = dado
			quantidade.inc()
		}
	}

	//Questão 02
	fun inserir(posicao: Int, dado: Any?) {
		if (!estaCheia()) {
			if (posicao >= 0 && posicao <= quantidade) {
				var posicaoFisica = logicaPraFisica(posicao)
                var atual = ponteiroFim
                var anterior = atual
                atual = avancar(atual)

				for (i in posicao until quantidade) {
					dados[atual] = dados[anterior]
					atual = retroceder(atual)
                    anterior = retroceder(anterior)
				}
				dados[posicaoFisica] = dado
				ponteiroFim = avancar(ponteiroFim)
				quantidade.inc()
			}
		}
	}

	//Questão 03
	fun buscarInicio(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia())
			dadoAux = dados[ponteiroInicio]
		return dadoAux
	}

	//Questão 04
	fun buscarFim(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia())
			dadoAux = dados[ponteiroFim]
		return dadoAux
	}

	//Questão 05
	fun buscar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia())
			if (posicao >= 0 && posicao < quantidade) {
				var posicaoFisica = logicaPraFisica(posicao)
				dadoAux = dados[posicaoFisica]
			}
		return dadoAux
	}

	//Questão 06
	fun buscarTodos(): Array<Any?> {
		var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
		if (!estaVazia()) {
			var ponteiroAux = ponteiroInicio
			for (i in 0 until quantidade) {
				dadosAux[i] = dados[ponteiroAux]
                ponteiroAux = avancar(ponteiroAux)	
            }
		}
		return dadosAux
	}

	//Questão 07
    fun primeiraOcorrencia(dado: Any?): Int {
		var ocorrencia = -1
		if (!estaVazia()) {	
            var ponteiroAux = ponteiroInicio
            for (i in 0 until quantidade) {
                var dadoAtual = dados[ponteiroAux]
                ponteiroAux = avancar(ponteiroAux)
                if (dadoAtual == dado) {
                    ocorrencia = i
                    break
                }	
            }
        }
		return ocorrencia
	}  

	//Questão 08
    fun ultimaOcorrencia(dado: Any?): Int {
		var ocorrencia = quantidade
		if (!estaVazia()) {		
            var ponteiroAux = ponteiroFim
            for (i in quantidade-1 downTo 0) {
                var dadoAtual = dados[ponteiroAux]
                ponteiroAux = retroceder(ponteiroAux)
                if (dadoAtual == dado) {
                    ocorrencia = i
                    break
                }	
            }	
        }	
		return ocorrencia
	}

	//Questão 09
	fun atualizarInicio(dado: Any?) {
		if (!estaVazia())
			dados[ponteiroInicio] = dado
	}

	//Questão 10
	fun atualizarFim(dado: Any?) {
		if (!estaVazia())
			dados[ponteiroFim] = dado
	}
		
	//Questão 11
	fun atualizar (posicao: Int, dado: Any?) {
		if (!estaVazia())
			if (posicao >= 0 && posicao < quantidade) {
				var posicaoFisica = logicaPraFisica(posicao)
				dados[posicaoFisica] = dado
			}
	}

	//Questão 12
    fun atualizarTodos(dado: Any?) {
		var ponteiroAux = ponteiroInicio
		for (i in 0 until quantidade) {
		    dados[ponteiroAux] = dado
            ponteiroAux = avancar(ponteiroAux)	
        }
    }

	//Questão 13
	fun apagarInicio(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = dados[ponteiroInicio]
			ponteiroInicio = avancar(ponteiroInicio)
			quantidade.dec()
		}
		return dadoAux
	}

	//Questão 14
	fun apagarFim(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = dados[ponteiroFim]
			ponteiroFim = retroceder(ponteiroFim)
			quantidade.dec()
		}
		return dadoAux
	}

	//Questão 15
	fun apagar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
				var posicaoFisica = logicaPraFisica(posicao)
				dadoAux = dados[posicaoFisica]
				
                var atual = posicaoFisica
                var proximo = atual
                proximo = avancar(proximo)    
				for (i in posicao until (quantidade-1)) {
					dados[atual] = dados[proximo]
                    atual = avancar(atual)
                    proximo = avancar(proximo)
				}
				ponteiroFim = retroceder(ponteiroFim)
				quantidade.dec()
			}
		}
		return dadoAux
	}

	//Questão 16
	fun apagarTodos(): Array<Any?> {
		var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
        if (!estaVazia()) {
			var ponteiroAux = ponteiroInicio
			for (i in 0 until quantidade) {
				dadosAux[i] = dados[ponteiroAux]
                ponteiroAux = avancar(ponteiroAux)	
            }	
			ponteiroInicio = 0		
			ponteiroFim = -1
			quantidade = 0
		}	
		return dadosAux	
	}

	//Questão 17
 	fun ordenarCrescente() {
		for (i in 0+ponteiroInicio until quantidade+ponteiroInicio) {
			for (j in 0+ponteiroInicio until (quantidade-1)+ponteiroInicio) {
				if ((dados[j%dados.size] as Int) > (dados[(j+1)%dados.size] as Int))
					trocar(j%dados.size, (j+1)%dados.size)
			}
		}
	}

	//Questão 18
    fun ordenarDecrescente() {
		for (i in 0+ponteiroInicio until quantidade+ponteiroInicio) {
			for (j in 0+ponteiroInicio until (quantidade-1)+ponteiroInicio) {
				if ((dados[j%dados.size] as Int) < (dados[(j+1)%dados.size] as Int))
					trocar(j%dados.size, (j+1)%dados.size)
			}
		}
	}

    //Questão 19
    fun inverter() {
		if (!estaVazia()) {
			var ponteiroAuxInicio = ponteiroInicio
			var ponteiroAuxFim = ponteiroFim                        
			for (i in 0 until quantidade/2) {
				trocar(ponteiroAuxInicio, ponteiroAuxFim)
				ponteiroAuxInicio = avancar(ponteiroAuxInicio)
                ponteiroAuxFim =retroceder(ponteiroAuxFim)
			}
		}
    }
}

fun main() {
    val gabarito = Gabarito()
}