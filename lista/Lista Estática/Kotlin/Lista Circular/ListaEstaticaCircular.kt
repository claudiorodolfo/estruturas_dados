class ListaEstaticaCircular : Listavel {
 
	private var ponteiroInicio: Int
	private var ponteiroFim: Int
	private var dados: Array<Any?>
	private var quantidade: Int

	constructor(tamanho: Int) {
		dados = arrayOfNulls(tamanho)
		ponteiroInicio = 0
		ponteiroFim = -1
		quantidade = 0	
	}
	
	constructor() : this(10) {}
    
	override fun anexar(dado: Any?) {
		if (!estaCheia()) {
			ponteiroFim++
			if (ponteiroFim == dados.size)
				ponteiroFim = 0

			quantidade++			
			dados[ponteiroFim] = dado
		} else {
			println("List is full!")
		}
	}

	override fun selecionarTodos(): Array<Any?> {        
		var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
		if (!estaVazia()) {
			var ponteiroAux: Int = ponteiroInicio
            for (i in 0 until quantidade)  {
				if (ponteiroAux == dados.size) {
                    ponteiroAux = 0
                }
				dadosAux[i] = dados[ponteiroAux]
				ponteiroAux++
            }
        }
        return dadosAux
	}

	override fun selecionar (posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			//índice/posição é válido? 
			if (posicao >= 0 && 
					posicao < quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica: Int = 
					(ponteiroInicio + posicao) % dados.size
				dadoAux = dados[posicaoFisica]
			} else {
				println("Indice Invalido!")	
			}
		} else {
			println("Lista Vazia!")		
		}
		return dadoAux
	}

	override fun atualizar (posicao: Int, dado: Any?) {
		if (!estaVazia()) {
			//índice/posição é válido? 
			if (posicao >= 0 && 
					posicao < quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica: Int = 
					(ponteiroInicio + posicao) % dados.size
				dados[posicaoFisica] = dado
			} else {
				println("Indice Invalido!")	
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
		var resultado : String = "["
		var ponteiroAux: Int = ponteiroInicio
		for (i in 0 until quantidade) {
			if (i == ponteiroFim)
				resultado += 
					"${dados[ponteiroAux % dados.size]}"
			else
				resultado += 
					"${dados[ponteiroAux % dados.size]}, "
			
			ponteiroAux++				
		}
		return resultado + "]"
	}

	override fun apagar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			//índice/posição é válido? 
			if (posicao >= 0 && 
					posicao < quantidade) {
				//mapeamento:
				//DE endereçamento lógico 
				//(informado pelo usuário)
				//PARA endereçamento físico 
				//(onde o dado está no array)
				var posicaoFisica: Int = 
				(ponteiroInicio + posicao) % dados.size
				dadoAux = dados[posicaoFisica]
				var ponteiroAux: Int = posicaoFisica
				for (i in posicao until (quantidade-1)) {
					var atual: Int = ponteiroAux
					if (ponteiroAux == dados.size-1) {
						ponteiroAux = -1			
					}
					var proximo: Int = ponteiroAux+1

					dados[atual] = dados[proximo]
					ponteiroAux++
				}
				ponteiroFim--
				if (ponteiroFim == -1) {
					ponteiroFim = dados.size - 1
				}
				quantidade--
			} else {
				println("Indice Invalido!")
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
				var posicaoFisica: Int = 
					(ponteiroInicio + posicao) % dados.size

				var ponteiroAux: Int = ponteiroFim+1
				for (i in posicao until quantidade) {
					var anterior: Int = ponteiroAux-1

					if(ponteiroAux == dados.size) {
						ponteiroAux = 0			
					} 
					var atual: Int = ponteiroAux

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
				println("Indice Invalido")
			}
		} else {
			println("Lista Cheia!")
		}
	}
}