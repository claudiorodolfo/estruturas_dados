class ListaDinamica(private val tamanho: Int = 10) : Listavel {

	private var ponteiroInicio: NoDuplo? = null
	private var ponteiroFim: NoDuplo? = null
	private var quantidade = 0

	//idêntico ao enfileirar de FilaDinamica
	override fun anexar(dado: Any?) {
		if (!estaCheia()) {
			val noTemp = NoDuplo(dado)
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
				//para os metodos update, delete, select e insert
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
				//para os metodos update, delete, select e insert
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

	override fun limpar() {
		ponteiroInicio = null
		ponteiroFim = null
		quantidade = 0
	}

	override fun apagarTodos(): Array<Any?> {
		var dadosAux: Array<Any?> = selecionarTodos()
		limpar()
		return dadosAux
	}

	override fun apagar(posicao: Int): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que será feita alguma operação. Esse codigo é o mesmo
				//para os metodos update, delete, select e insert
				var ponteiroAuxiliar = ponteiroInicio
				for (i in 0 until posicao)
					ponteiroAuxiliar = ponteiroAuxiliar?.proximo
				///////////////////////////////
				dadoAux = ponteiroAuxiliar?.dado

				val ponteiroAnterior = ponteiroAuxiliar?.anterior
				val ponteiroProximo  = ponteiroAuxiliar?.proximo

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
				val noTemp = NoDuplo(dado)
				//noTemp.dado = dado
				
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que será feita alguma operação. Esse codigo é o mesmo
				//para os metodos update, delete, select e insert
				/*
				var ponteiroAuxiliar = ponteiroInicio
				for (i in 0 until posicao)
					ponteiroAuxiliar = ponteiroAuxiliar?.proximo
				///////////////////////////////
				val ponteiroProximo = ponteiroAuxiliar
				val ponteiroAnterior = ponteiroFim
				if (ponteiroAuxiliar != null)
					ponteiroAnterior = ponteiroAuxiliar.anterior
				*/
				var ponteiroAnterior: NoDuplo? = null
				var ponteiroProximo = ponteiroInicio

				for (i in 0 until posicao) {
					ponteiroAnterior = ponteiroProximo
					ponteiroProximo = ponteiroProximo?.proximo
				}

				//todas insercoes, exceto inicio
				if (ponteiroAnterior != null)						
					ponteiroAnterior.proximo = noTemp
				else	//insercao no inicio
					ponteiroInicio = noTemp

				//todas insercoes, exceto fim
				if (ponteiroProximo != null)
					ponteiroProximo.anterior = noTemp
				else	//insercao no fim
					ponteiroFim = noTemp						
			
				noTemp.proximo = ponteiroProximo
				noTemp.anterior = ponteiroAnterior						

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

	override fun tamanho(): Int {
		return quantidade
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