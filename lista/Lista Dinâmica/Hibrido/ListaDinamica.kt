class ListaDinamica(private val tamanho: Int = 10) : Listavel {

	private var ponteiroInicio: NoDuplo? = null
	private var ponteiroFim: NoDuplo? = null
	private var quantidade = 0

	//idêntico ao enfileirar de FilaDinamica
	override fun anexar(dado: Any?) {
		if (!estaCheia()) {
			var noTemp = NoDuplo(dado)
			//noTemp.dado = dado
			noTemp.anterior = ponteiroFim				
			if (!estaVazia())
				ponteiroFim?.proximo = noTemp
			else
				ponteiroInicio = noTemp
			
			ponteiroFim = noTemp
			quantidade = quantidade.inc()
		} else {
			println("Lista Cheia!")
		}
	}

	override fun selecionarTodos(): Array<Any?> {        
		var dadosAux: Array<Any?> = arrayOfNulls(quantidade)
		if (!estaVazia()) {
			var ponteiroAuxiliar = ponteiroInicio
			for (i in 0 until quantidade) {
				dadosAux[i] = ponteiroAuxiliar?.dado	
				ponteiroAuxiliar = ponteiroAuxiliar?.proximo
			}
		} else {
			println("Lista Vazia!")
		}
		return dadosAux
	}

	override fun selecionar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que será feita alguma operação. Esse codigo é o mesmo
				//para os metodos update, delete e select
				var ponteiroAuxiliar = ponteiroInicio
				for (i in 0 until posicao)
					ponteiroAuxiliar = ponteiroAuxiliar?.proximo
				///////////////////////////////
				dadoAux = ponteiroAuxiliar?.dado
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
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que será feita alguma operação. Esse codigo é o mesmo
				//para os metodos update, delete e select
				var ponteiroAuxiliar = ponteiroInicio
				for (i in 0 until posicao)
					ponteiroAuxiliar = ponteiroAuxiliar?.proximo
				///////////////////////////////
				ponteiroAuxiliar?.dado = dado
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
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que será feita alguma operação. Esse codigo é o mesmo
				//para os metodos update, delete e select
				var ponteiroAuxiliar = ponteiroInicio
				for (i in 0 until posicao)
					ponteiroAuxiliar = ponteiroAuxiliar?.proximo
				///////////////////////////////
				dadoAux = ponteiroAuxiliar?.dado

				var ponteiroAnterior = ponteiroAuxiliar?.anterior
				var ponteiroProximo  = ponteiroAuxiliar?.proximo

				if (ponteiroAnterior != null) 
					ponteiroAnterior.proximo = ponteiroProximo
				//remocao do inicio, joga o ponteiro de inicio para o proximo nodo.
				else
					ponteiroInicio = ponteiroInicio?.proximo
				
				if (ponteiroProximo != null) 
					ponteiroProximo.anterior = ponteiroAnterior
				//remocao do fim, joga o ponteiro de fim para o nodo anterior.
				else
					ponteiroFim = ponteiroFim?.anterior

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
				var noTemp = NoDuplo(dado)
				//noTemp.dado = dado
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que serah feita alguma operacao. Lembrando que nesse metodo
				//auxiliar ira parar no nodo subsequente ao nodo que devera 
				//ser inserido				
				var ponteiroAnterior: NoDuplo? = null
				var ponteiroProximo = ponteiroInicio

				for (i in 0 until posicao) {
					ponteiroAnterior = ponteiroProximo
					ponteiroProximo = ponteiroProximo?.proximo
				}

				if (ponteiroAnterior != null)
					ponteiroAnterior.proximo = noTemp
				//se o anterior é nulo é pq a insercao está sendo no inicio
				else
					ponteiroInicio = noTemp
				

				if (ponteiroProximo != null)
					ponteiroProximo.anterior = noTemp
				//se o proximo é nulo é pq a insercao está sendo no fim (append)
				else
					ponteiroFim = noTemp
				
				noTemp.anterior = ponteiroAnterior
				noTemp.proximo  = ponteiroProximo

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
			resultado += ponteiroAuxiliar?.dado
			if (i != quantidade-1)
				resultado += ","
			
			ponteiroAuxiliar = ponteiroAuxiliar?.proximo
		}
		return "$resultado]"
	}	
}