class FilaDinamicaDuplaTerminacao(private val tamanho: Int = 10) : EnfileiravelDupla {

	private var ponteiroInicio: NoDuplo? = null
	private var ponteiroFim: NoDuplo? = null
	private var quantidade = 0
 
    override fun enfileirarInicio(dado: Any?) {
		if (!estaCheia()) {
			var noTemp = NoDuplo(dado)
			//noTemp.dado = dado
			noTemp.proximo = ponteiroInicio
			if (!estaVazia())
				ponteiroInicio?.anterior = noTemp
			else
				ponteiroFim = noTemp

			ponteiroInicio = noTemp
			quantidade = quantidade.inc()
		} else {
			println("Fila Cheia!")
		}
    }

	override fun enfileirarFim(dado: Any?) {
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
			println("Fila Cheia!")
		}
	}
	
    override fun desenfileirarFim(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = ponteiroFim?.dado
			ponteiroFim = ponteiroFim?.anterior
			quantidade = quantidade.dec()
			if (!estaVazia())
				ponteiroFim?.proximo = null
			else
				ponteiroInicio = null			
		} else {
			println("Fila Vazia!")
		}
		return dadoAux
	}

	override fun desenfileirarInicio(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = ponteiroInicio?.dado
			ponteiroInicio = ponteiroInicio?.proximo
			quantidade = quantidade.dec()
			if (!estaVazia())
				ponteiroInicio?.anterior = null
			else
				ponteiroFim = null			
		} else {
			println("Fila Vazia!")
		}
		return dadoAux
	}

	override fun tras(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia())
			dadoAux = ponteiroFim?.dado
		else
			println("Fila Vazia!")

		return dadoAux
	}

	override fun frente(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia())
			dadoAux = ponteiroInicio?.dado
		else
			println("Fila Vazia!")

		return dadoAux
	}

    override fun atualizarFim(dado: Any?) {
		if (!estaVazia())
			ponteiroFim?.dado = dado 
		else
			println("Fila Vazia!")
	}	

    override fun atualizarInicio(dado: Any?) {
		if (!estaVazia())
			ponteiroInicio?.dado = dado 
		else
			println("Fila Vazia!")
	}	

	override fun estaCheia(): Boolean {
		return quantidade == tamanho
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
    override fun imprimirTrasPraFrente(): String {
		var ponteiroAuxiliar = ponteiroFim
		var resultado = "["
		for (i in 0 until quantidade) {
			resultado += ponteiroAuxiliar?.dado	
			if (i != quantidade-1)
				resultado += ","			
			
			ponteiroAuxiliar = ponteiroAuxiliar?.anterior
		}
		return "$resultado]"
    }
    
	override fun imprimirFrentePraTras(): String {
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