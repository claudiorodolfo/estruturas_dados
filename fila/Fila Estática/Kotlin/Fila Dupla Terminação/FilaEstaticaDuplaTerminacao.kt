class FilaEstaticaDuplaTerminacao(private val tamanho: Int = 10) : EnfileiravelDupla {
 
	private var ponteiroInicio = 0
	private var ponteiroFim = -1 
	private var dados: Array<Any?> = arrayOfNulls(tamanho)
    private var quantidade = 0

	override fun atualizarInicio(dado: Any?) {
		if (!estaVazia())
			dados[ponteiroInicio] = dado
		else
			println("Queue is empty!")
	}

	override fun atualizarFim(dado: Any?) {
		if (!estaVazia())
			dados[ponteiroFim] = dado
		else
			println("Queue is empty!")
	}

	override fun enfileirarFim(dado: Any?) {
		if (!estaCheia()) {
			ponteiroFim++
			//patch pra fila funcionar de forma circular
			if (ponteiroFim == dados.size)
				ponteiroFim = 0
			quantidade++
			//fim do patch			
			dados[ponteiroFim] = dado
		} else {
			println("Queue is full!")
		}
	}

    override fun enfileirarInicio(dado: Any?) {
        if (!estaCheia()) {
            ponteiroInicio--
            //patch pra fila funcionar de forma circular
            if (ponteiroInicio == -1) {
                ponteiroInicio = dados.size-1
                if (quantidade == 0)
                    ponteiroFim = dados.size-1
            }
            quantidade++
            //fim do patch			
            dados[ponteiroInicio] = dado
        } else {
            println("Queue is full!")
        }
    }

	override fun desenfileirarInicio(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = dados[ponteiroInicio]
			ponteiroInicio++
			//patch pra fila funcionar de forma circular
			if (ponteiroInicio == dados.size)
				ponteiroInicio = 0
			quantidade--
			//fim do patch	
		} else {
			println("Queue is empty!")
		}
		return dadoAux
	}

	override fun desenfileirarFim(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = dados[ponteiroFim]
			ponteiroFim--
			//patch pra fila funcionar de forma circular
			if (ponteiroFim == -1)
				ponteiroFim = dados.size-1
			quantidade--
			//fim do patch	
		} else {
			println("Queue is empty!")
		}
		return dadoAux
	}

	override fun frente(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = dados[ponteiroInicio]
		} else {
			println("Queue is empty!")
		}
		return dadoAux
	}

	override fun tras(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = dados[ponteiroFim]
		} else {
			println("Queue is empty!")
		}
		return dadoAux
	}
    
	override fun estaCheia(): Boolean {
		return quantidade == dados.size
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
	override fun imprimirFrentePraTras(): String {
		var resultado = "["
		var ponteiroAux = ponteiroInicio
		for (i in 0 .. quantidade-1) {
			if (i == quantidade-1)
				resultado += "${dados[ponteiroAux]}"
			else
				resultado += "${dados[ponteiroAux]},"

			ponteiroAux++
            if (ponteiroAux == dados.size)
                ponteiroAux = 0			
		}
		return "$resultado]"
	}

	override fun imprimirTrasPraFrente(): String {
		var resultado = "["
		var ponteiroAux = ponteiroFim
		for (i in quantidade-1 downTo 0) {
			if (i == 0)
				resultado += "${dados[ponteiroAux]}"
			else
				resultado += "${dados[ponteiroAux]},"

			ponteiroAux--
            if (ponteiroAux == -1)
                ponteiroAux = dados.size-1			
		}
		return "$resultado]"
	}    
}