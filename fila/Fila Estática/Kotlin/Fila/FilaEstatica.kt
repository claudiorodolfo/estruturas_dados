class FilaEstatica : Enfileiravel {
 
	private var ponteiroInicio: Int
	private var ponteiroFim: Int
	private var dados: Array<Any?>
	
	constructor(tamanho: Int) {
		dados = arrayOfNulls(tamanho)
		ponteiroInicio = 0
		ponteiroFim = -1		
	}
	
	constructor() : this(10) {}
    
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
		var resultado : String = "["
		for (i in ponteiroInicio..ponteiroFim) {
			if (i == ponteiroFim)
				resultado += "${dados[i]}"
			else
				resultado += "${dados[i]},"
		}
		return (resultado + "]")
	}
}