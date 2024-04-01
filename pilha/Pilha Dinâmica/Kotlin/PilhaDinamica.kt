class PilhaDinamica(private val tamanho: Int = 10) : Empilhavel {

	private var ponteiroTopo: NoDuplo? = NoDuplo()
	private var quantidade: Int = 0
 
	override fun empilhar(dado: Any?) {
		if (!estaCheia()) {
			var noTemp: NoDuplo? = NoDuplo(dado)
			//noTemp?.dado = dado
			noTemp?.anterior = ponteiroTopo
			if (!estaVazia()) {
				ponteiroTopo?.proximo = noTemp
			}
			ponteiroTopo = noTemp
			quantidade++
		} else {
			println("Pilha Cheia!")
		}
	}
	
	override fun desempilhar(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo?.dado
			ponteiroTopo = ponteiroTopo?.anterior
			quantidade--
			if (!estaVazia()) {
				ponteiroTopo?.proximo = null
			}
		} else {
			println("Pilha Vazia!")
		}
		return dadoTopo
	}
	
	override fun espiar(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo?.dado
		} else {
			println("Pilha Vazia!")
		}
		return dadoTopo
	}
	
	override fun atualizar(dado: Any?) {
		if (!estaVazia()) {
			ponteiroTopo?.dado = dado
		} else {
			println("Pilha Vazia!")
		}
	}

	override fun estaCheia(): Boolean {
		return quantidade == tamanho
	}
	
	override fun estaVazia(): Boolean {
		return quantidade == 0
	}
	
	override fun imprimir(): String {
		var ponteiroAuxiliar: NoDuplo? = ponteiroTopo
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
}