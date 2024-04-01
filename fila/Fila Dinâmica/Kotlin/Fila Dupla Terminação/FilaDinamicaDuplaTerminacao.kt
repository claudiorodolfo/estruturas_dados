class FilaDinamicaDuplaTerminacao(private val tamanho: Int = 10) : Enfileiravel {

	private var ponteiroInicio: NoDuplo? = null
	private var ponteiroFim: NoDuplo? = null
	private var quantidade: Int = 0
 
    override fun enfileirarInicio(dado: Any?) {

    }

	override fun enfileirarFim(dado: Any?) {
		if (!estaCheia()) {
			var noTemp: NoDuplo? = NoDuplo(dado)
			//noTemp?.dado = dado
			noTemp?.anterior = ponteiroFim
			if (!estaVazia()) {
				ponteiroInicio?.proximo = noTemp
			}
			ponteiroFim = noTemp
			quantidade++
		} else {
			println("Fila Cheia!")
		}
	}
	
    override fun desenfileirarFim(): Any? {
    }

	override fun desenfileirarInicio(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = ponteiroInicio?.dado
			ponteiroInicio = ponteiroInicio?.proximo
			quantidade--
			if (!estaVazia()) {
				ponteiroInicio?.anterior = null
			}
		} else {
			println("Fila Vazia!")
		}
		return dadoAux
	}

	override fun tras(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = ponteiroFim?.dado
		} else {
			println("Fila Vazia!")
		}
		return dadoAux
	}

	override fun frente(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = ponteiroInicio?.dado
		} else {
			println("Fila Vazia!")
		}
		return dadoAux
	}

    override fun atualizarFim(dado: Any?) {
		if (!estaVazia()) {
			ponteiroFim?.dado = dado 
		} else {
			println("Fila Vazia!")
		}
	}	

    override fun atualizarInicio(dado: Any?) {
		if (!estaVazia()) {
			ponteiroInicio?.dado = dado 
		} else {
			println("Fila Vazia!")
		}
	}	

	override fun estaCheia(): Boolean {
		return quantidade == tamanho
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
    override fun imprimirTrasPraFrente(): String {
		var ponteiroAuxiliar: NoDuplo? = ponteiroFim
		var resultado : String = "["
		for (i in 0 until quantidade) {
			if (i == quantidade-1)
				resultado += "${ponteiroAuxiliar?.dado}"
			else
				resultado += "${ponteiroAuxiliar?.dado},\n"
			
			ponteiroAuxiliar = ponteiroAuxiliar?.anterior
		}
		return (resultado + "]")

    }
    
	override fun imprimirFrentePraTras(): String {
		var ponteiroAuxiliar: NoDuplo? = ponteiroInicio
		var resultado : String = "["
		for (i in 0 until quantidade) {
			if (i == quantidade-1)
				resultado += "${ponteiroAuxiliar?.dado}"
			else
				resultado += "${ponteiroAuxiliar?.dado},\n"
			
			ponteiroAuxiliar = ponteiroAuxiliar?.proximo
		}
		return (resultado + "]")
	}
}