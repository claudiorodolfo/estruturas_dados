//Clusterização, Conteinerização
class MapaEspalhamento(private val tamanhoTabela: Int = 10): Espalhavel {

	private var tabelaEspalhamento: Array<Listavel> = Array(tamanhoTabela) { ListaDinamica() }
	//Controla quantos elementos há na estrutura Mapa Espalhamento (HashMap)
	private var quantidade = 0
	
	//funcao que espalha os elementos "chave,valor" para cairem em listas diferentes
	//baseado no primeiro caracter da chave
	private fun funcaoEspalhamento(chave: Any): Int {
		val chaveString = chave.toString()
		val letraInicial = chaveString.lowercase()[0]
		return letraInicial.code % tamanhoTabela
	}

	///funcao que espalha os elementos "chave,valor" para cairem em listas diferentes
	//baseado em todos os caracteres da chave
	private fun funcaoEspalhamento2(chave: Any): Int {
		val chaveString = chave.toString()

		var total = 0
		for (i in 0 until chaveString.length) {
			val letra = chaveString.lowercase()[i]
			total += letra.code
		}
		return total % tamanhoTabela
	}	
	
	override fun adicionar(mapa: Mapa) {
		val indice = funcaoEspalhamento(mapa.chave)
		val listaTemp = tabelaEspalhamento[indice]
		// Se a chave existir, atualiza o mapa; 
		//caso contrário, insere o mapa
		if (contemChave(mapa.chave)) {
			for (i in 0 until listaTemp.tamanho()) {
				val elementoLista = listaTemp.selecionar(i) as Mapa
				val chaveLista = elementoLista.chave
	
				if (chaveLista == mapa.chave) {
					listaTemp.atualizar(i, mapa)
					break
				}
			}
		} else {
			listaTemp.anexar(mapa)
			quantidade = quantidade.inc()
		}
	}
	
	override fun remover(chave: Any): Any? {
		var dadoAuxiliar: Any? = null
		if (!estaVazia()) {
			if (contemChave(chave)) {
				// Obtém o índice da tabela de espalhamento que a chave pertence
				// Seleciona um item da tabela (esse item é uma lista)
				val indice = funcaoEspalhamento(chave)				
				val listaTemp = tabelaEspalhamento[indice] 
				
				// Para cada elemento da lista é verificado se a chave informada é igual à chave do elemento	
				for (i in 0 until listaTemp.tamanho()) {
					val elementoLista = listaTemp.selecionar(i) as Mapa
					val chaveLista = elementoLista.chave
	
					if (chave == chaveLista) {
						dadoAuxiliar = elementoLista.dado
						listaTemp.apagar(i)
						quantidade = quantidade.dec()
						break
					}
				}
			} else {
				println("Chave não existente.")
			}
		} else {
			println("A estrutura de dados está vazia.")
		}
		return dadoAuxiliar
	}
		
	override fun contemChave(chave: Any): Boolean {
		var chaveEncontrada = false
		if (!estaVazia()) {
			// Obtém o índice da tabela de espalhamento que a chave pertence
			// Seleciona um item da tabela (esse item é uma lista)
			val indice = funcaoEspalhamento(chave)
			val listaTemp = tabelaEspalhamento[indice] 
			
			// Para cada elemento da lista é verificado se a chave informada é igual à chave do elemento	
			for (i in 0 until listaTemp.tamanho()) {
				val elementoLista = listaTemp.selecionar(i) as Mapa
				val chaveLista = elementoLista.chave
	
				if (chave == chaveLista) {
					chaveEncontrada = true
					break
				}
			}        
		} else {
			println("A estrutura de dados está vazia.")
		}
		return chaveEncontrada
	}
	
	override fun buscar(chave: Any): Any? {
		var dadoAuxiliar: Any? = null
		if (!estaVazia()) {
			if (contemChave(chave)) {
				// Obtém o índice da tabela de espalhamento que a chave pertence
				// Seleciona um item da tabela (esse item é uma lista)
				val indice = funcaoEspalhamento(chave)
				val listaTemp = tabelaEspalhamento[indice] 
				
				// Para cada elemento da lista é verificado se a chave informada é igual à chave do elemento
				for (i in 0 until listaTemp.tamanho()) {
					val elementoLista = listaTemp.selecionar(i) as Mapa
					val chaveLista = elementoLista.chave
	
					if (chave == chaveLista) {
						dadoAuxiliar = elementoLista.dado
						break
					}
				}
			} else {
				println("Chave não existente.")
			}
		} else {
			println("A estrutura de dados está vazia")
		}
		return dadoAuxiliar
	}	
	
	override fun tamanho(): Int {
		return quantidade
	}
	
	override fun estaVazia(): Boolean {
		return (quantidade == 0)
	}
	
	override fun imprimir(): String {
		var resultado = "["
		for (i in 0 until tabelaEspalhamento.size) {
			val listaTemp = tabelaEspalhamento[i] 
			resultado += listaTemp.imprimir()
		}
		return "$resultado]"	
	}
}