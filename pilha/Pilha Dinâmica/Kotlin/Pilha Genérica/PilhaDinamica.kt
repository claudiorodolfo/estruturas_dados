class PilhaDinamica<T>(private val tamanho: Int = 10) : Empilhavel<T> {

	private var ponteiroTopo: NoDuplo<T>? = null
	private var quantidade = 0
 
	override fun empilhar(dado: T?) {
		if (!estaCheia()) {
			var noTemp = NoDuplo<T>(dado)
			//noTemp.dado = dado
			noTemp.anterior = ponteiroTopo
			if (!estaVazia())
				ponteiroTopo?.proximo = noTemp

			ponteiroTopo = noTemp
			quantidade = quantidade.inc()
		} else {
			println("Pilha Cheia!")
		}
	}
	
	override fun desempilhar(): T? {
		var dadoTopo: T? = null
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo?.dado
			ponteiroTopo = ponteiroTopo?.anterior
			quantidade = quantidade.dec()
			if (!estaVazia())
				ponteiroTopo?.proximo = null
				
		} else {
			println("Pilha Vazia!")
		}
		
		return dadoTopo
	}
	
	override fun espiar(): T? {
		var dadoTopo: T? = null
		if (!estaVazia())
			dadoTopo = ponteiroTopo?.dado
		else
			println("Pilha Vazia!")

		return dadoTopo
	}
	
	override fun atualizar(dado: T?) {
		if (!estaVazia())
			ponteiroTopo?.dado = dado
		else
			println("Pilha Vazia!")
	}

	override fun estaCheia(): Boolean {
		return quantidade == tamanho
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
	override fun imprimir(): String {
		var ponteiroAuxiliar = ponteiroTopo
		var resultado = "["
		for (i in 0 until quantidade) {
			resultado += "${ponteiroAuxiliar?.dado}"
			if (i != quantidade-1)
				resultado += ","
			
			ponteiroAuxiliar = ponteiroAuxiliar?.anterior
		}
		return "$resultado]"
	}
}