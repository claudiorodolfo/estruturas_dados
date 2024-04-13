class ListaDinamica(private val tamanho: Int = 10) : Listavel {
 
	private var ponteiroInicio: NoDuplo? = null
	private var ponteiroFim: NoDuplo? = null
	private var quantidade = 0

	//idêntico ao enfileirar de FilaDinamica
	override fun anexar(dado: Any?) {
		if (!estaCheia()) {
			var noTemp: NoDuplo? = NoDuplo(dado)
			//noTemp?.dado = dado
			if (!estaVazia())
				ponteiroFim?.proximo = noTemp
			else
				ponteiroInicio = noTemp

			noTemp?.anterior = ponteiroFim				
			ponteiroFim = noTemp
			quantidade = quantidade.inc()
		} else {
			println("Fila Cheia!")
		}
	}

	override fun selecionarTodos(): Array<Any?> {        
		var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
		if (!estaVazia()) {
			var ponteiroAux = ponteiroInicio
			for (i in 0 until quantidade)
				dadosAux[i] = dados[(ponteiroAux+i)%dados.size]
            
        }
        return dadosAux
	}

	override fun selecionar (posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			//índice/posição lógica é válida? 
			if (posicao >= 0 && 
					posicao < quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica = 
					(ponteiroInicio + posicao) % dados.size
				dadoAux = dados[posicaoFisica]
			} else {
				println("Indice Inválido!")	
			}
		} else {
			println("Lista Vazia!")		
		}
		return dadoAux
	}

	override fun atualizar (posicao: Int, dado: Any?) {
		if (!estaVazia()) {
			//índice/posição lógica é válida? 
			if (posicao >= 0 && 
					posicao < quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica = 
					(ponteiroInicio + posicao) % dados.size
				dados[posicaoFisica] = dado
			} else {
				println("Indice Inválido!")	
			}
		} else {
			println("Lista Vazia!")		
		}
	}

	override fun estaCheia(): Boolean {
		return quantidade == dados.size
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
	override fun imprimir(): String {
		var resultado = "["
		var ponteiroAux = ponteiroInicio
		for (i in 0 until quantidade) {
			resultado += if (i == ponteiroFim)
				"${dados[(ponteiroAux+i) % dados.size]}"
			else
				"${dados[(ponteiroAux+i) % dados.size]}, "			
		}
		return "$resultado]"
	}

	override fun apagar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			//índice/posição lógica é válida? 
			if (posicao >= 0 && 
					posicao < quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica = 
				(ponteiroInicio + posicao) % dados.size
				dadoAux = dados[posicaoFisica]
				var ponteiroAux = posicaoFisica
				for (i in posicao until (quantidade-1)) {
					var atual = ponteiroAux
					var proximo = (ponteiroAux+1)%dados.size

					dados[atual] = dados[proximo]
					ponteiroAux++
				}
				ponteiroFim--
				if (ponteiroFim == -1) 
					ponteiroFim = dados.size - 1
				
				quantidade--
			} else {
				println("Indice Inválido!")
			}
		} else {
			println("Lista Vazia!")
		}
		return dadoAux
	}

	override fun inserir(posicao: Int, dado: Any?) {
		if (!estaCheia()) {
			//índice/posição é válido? 
			if (posicao >= 0 && 
					posicao <= quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica = 
					(ponteiroInicio + posicao) % dados.size

				var ponteiroAux = ponteiroFim+1
				for (i in posicao until quantidade) {
					var anterior = ponteiroAux-1

					if(ponteiroAux == dados.size) {
						ponteiroAux = 0			
					} 
					var atual = ponteiroAux

					dados[atual] = dados[anterior]
					ponteiroAux--
				}

				dados[posicaoFisica] = dado
				ponteiroFim++
				if (ponteiroFim == dados.size) {
					ponteiroFim = 0
				}
				quantidade++
			} else {
				println("Indice Inválido")
			}
		} else {
			println("Lista Cheia!")
		}
	}
}