class FilaEstatica(private val tamanho: Int = 10) : Enfileiravel {
 
	private var ponteiroInicio = 0
	private var ponteiroFim = -1 
	private var dados: Array<Any?> = arrayOfNulls(tamanho)
    
	override fun enfileirar(dado: Any?) {
		if (!estaCheia()) {
			ponteiroFim++
			dados[ponteiroFim] = dado
		} else {
			println("Queue is full!")
		}
	}
	
	override fun desenfileirar(): Any? {
		var dadoInicio: Any? = null
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio]
			ponteiroInicio++
		} else {
			println("Queue is empty!")
		}
		return dadoInicio
	}
	
	override fun espiar(): Any? {
		var dadoInicio: Any? = null
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio]
		} else {
			println("Queue is empty!")
		}
		return dadoInicio
	}
	
	override fun estaCheia(): Boolean {
		return ponteiroFim == dados.size - 1
	}
	
	override fun estaVazia(): Boolean {
		return ponteiroInicio == ponteiroFim + 1
	}
	
	override fun imprimir(): String {
		var resultado = "["
		for (i in ponteiroInicio..ponteiroFim) {
			if (i == ponteiroFim)
				resultado += "${dados[i]}"
			else
				resultado += "${dados[i]},"
		}
		return "$resultado]"
	}
}