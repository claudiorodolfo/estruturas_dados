class PilhaEstaticaInvertida : Empilhavel {
    
	private var ponteiroTopo: Int
	private var dados: Array<Any?>
	
	constructor(tamanho: Int) {
		dados = arrayOfNulls(tamanho)
		ponteiroTopo = tamanho
	}
	
	constructor() : this(10)
    
	override fun atualizar(dado: Any?) {
		if (!estaVazia())
			dados[ponteiroTopo] = dado
		else
			println("Stack is empty!")
	}
		
	override fun empilhar(dado: Any?) {
		if (!estaCheia()) {
			ponteiroTopo--
			dados[ponteiroTopo] = dado
		} else {
			println("Stack is full!")
		}
	}
	
	override fun desempilhar(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]
			ponteiroTopo++
		} else {
			println("Stack is empty!")
		}
		return dadoTopo
	}
	
	override fun espiar(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]
		} else {
			println("Stack is empty!")
		}
		return dadoTopo
	}
	
	override fun estaCheia(): Boolean {
		return (ponteiroTopo == 0)
	}
	
	override fun estaVazia(): Boolean {
		return (ponteiroTopo == dados.size)
	}
	
	override fun imprimir(): String {
		var resultado = "["
		for (i in ponteiroTopo .. dados.size-1) {
			if (i == dados.size-1)
				resultado += "${dados[i]}"
			else
				resultado += "${dados[i]}, "
		}
		return "$resultado]"
	}
}