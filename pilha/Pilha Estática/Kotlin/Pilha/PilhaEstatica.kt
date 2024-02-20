class PilhaEstatica (
    private val tamanho: Int = 10
): Empilhavel {
    
	private var ponteiroTopo: Int = -1
	private var dados: Array<Any?> = arrayOfNulls(tamanho)
    
	override fun empilhar(dado: Any?) {
		if (!estaCheia()) {
			ponteiroTopo++
			dados[ponteiroTopo] = dado
		} else {
			println("Stack is full!")
		}
	}
	
	override fun desempilhar(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]
			ponteiroTopo--
		} else {
			println("Stack is empty!")
		}
		return dadoTopo
	}
	
	override fun topo(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]
		} else {
			println("Stack is empty!")
		}
		return dadoTopo
	}
	
	override fun estaCheia(): Boolean {
		return (ponteiroTopo == dados.size - 1)
	}
	
	override fun estaVazia(): Boolean {
		return (ponteiroTopo == -1)
	}
	
	override fun imprimir(): String {
		var resultado = "["
		for (i in ponteiroTopo downTo 0) {
			if (i == 0)
				resultado += "${dados[i]}"
			else
				resultado += "${dados[i]}, "
		}
		return "$resultado]"
	}
}
