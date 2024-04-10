class FilaDinamica(private val tamanho: Int = 10) : Enfileiravel {

	private var ponteiroInicio: NoDuplo? = null
	private var ponteiroFim: NoDuplo? = null
	private var quantidade: Int = 0
 
	override fun enfileirar(dado: Any?) {
		if (!estaCheia()) {
			var noTemp: NoDuplo? = NoDuplo(dado)
			//noTemp?.dado = dado
			noTemp?.anterior = ponteiroFim
			if (!estaVazia())
				ponteiroInicio?.proximo = noTemp

			ponteiroFim = noTemp
			quantidade = quantidade.inc()
		} else {
			println("Fila Cheia!")
		}
	}
	
	override fun desenfileirar(): Any? {
		var dadoAux: Any? = null
		if (!estaVazia()) {
			dadoAux = ponteiroInicio?.dado
			ponteiroInicio = ponteiroInicio?.proximo
			quantidade = quantidade.dec()
			if (!estaVazia())
				ponteiroInicio?.anterior = null

		} else {
			println("Fila Vazia!")
		}
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

    override fun atualizar(dado: Any?) {
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
	
	override fun imprimir(): String {
		var ponteiroAuxiliar: NoDuplo? = ponteiroInicio
		var resultado : String = "["
		for (i in 0 until quantidade) {
			resultado += "${ponteiroAuxiliar?.dado}"
			if (i != quantidade-1)
				resultado += ",\n"
			
			ponteiroAuxiliar = ponteiroAuxiliar?.proximo
		}
		return "$resultado]"
	}
}