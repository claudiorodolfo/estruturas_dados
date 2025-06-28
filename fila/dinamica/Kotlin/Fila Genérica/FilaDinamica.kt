class FilaDinamica<T>(private val tamanho: Int = 10) : Enfileiravel<T> {

	private var ponteiroInicio: NoDuplo<T>? = null
	private var ponteiroFim: NoDuplo<T>? = null
	private var quantidade = 0
 
	override fun enfileirar(dado: T?) {
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
	
	override fun desenfileirar(): T? {
		var dadoAux: T? = null
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
	
	override fun frente(): T? {
		var dadoAux: T? = null
		if (!estaVazia())
			dadoAux = ponteiroInicio?.dado
		else
			println("Fila Vazia!")
		
		return dadoAux
	}

    override fun atualizar(dado: T?) {
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
		var ponteiroAuxiliar = ponteiroInicio
		var resultado = "["
		for (i in 0 until quantidade) {
			resultado += "${ponteiroAuxiliar?.dado}"
			if (i != quantidade-1)
				resultado += ","
			
			ponteiroAuxiliar = ponteiroAuxiliar?.proximo
		}
		return "$resultado]"
	}
}