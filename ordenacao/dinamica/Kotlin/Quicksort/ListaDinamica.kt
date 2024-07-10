class ListaDinamica(private val tamanho: Int = 10) : Listavel {

	var ponteiroInicio: NoDuplo? = null
	var ponteiroFim: NoDuplo? = null
	private var quantidade = 0

	override fun anexar(dado: Any?) {
		if (!estaCheia()) {
			val noTemp = NoDuplo(dado)
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