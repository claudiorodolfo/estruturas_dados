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
			println("Lista Cheia!")
		}
	}

	override fun selecionarTodos(): Array<Any?> {        
		var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
		if (!estaVazia()) {
			//selecionar aq           
        }
        return dadosAux
	}

	override fun selecionar (posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
			//selecionar aq
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
			if (posicao >= 0 && posicao < quantidade) {
				//atualizar aq
			} else {
				println("Indice Inválido!")	
			}
		} else {
			println("Lista Vazia!")		
		}
	}

	override fun apagar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
				//remove aq
				quantidade = quantidade.dec()
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
			if (posicao >= 0 && posicao <= quantidade) {
				//inserção aq
				quantidade = quantidade.inc()
			} else {
				println("Indice Inválido!")
			}
		} else {
			println("Lista Cheia!")
		}
	}

	override fun estaCheia(): Boolean {
		return quantidade == tamanho
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
	override fun imprimir(): String {
		var ponteiroAuxiliar = ponteiroInicio
		var resultado = "["
		for (i in 0 until quantidade) {
			resultado += "${ponteiroAuxiliar?.dado}"
			if (i != quantidade-1)
				resultado += ","
			
			ponteiroAuxiliar = ponteiroAuxiliar?.proximo
		}
		return "$resultado]"
	}	
}