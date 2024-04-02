class ArvoreBinariaHeapMinimo(private val tamanho: Int = 10): Amontoavel {

	private var dados: Array<Any?> = arrayOfNulls(tamanho)
    private var ponteiroFim = -1 
	
    override fun inserir(dado: Any?) {
        if (!estaCheia()) {
			ponteiroFim.inc()
			dados[ponteiroFim] = dado
			ajustarAcima(ponteiroFim)
        } else {
			println("Heap está Cheio!")
		}
    }

    private fun ajustarAcima(indice: Int) {
        var indiceAtual = indice
        while (indiceAtual > 0) {
            if (dados[indicePai(indiceAtual)] as Int > dados[indiceAtual] as Int) {
                trocar(indiceAtual, indicePai(indiceAtual))
                indiceAtual = indicePai(indiceAtual)
            } else {
                break
            }
        }
    }

    private fun ajustarAbaixo(pai: Int) {
        val filhoEsquerdo = 2 * pai + 1
        val filhoDireito = 2 * pai + 2
        var menor = pai;

        if (filhoEsquerdo <= ponteiroFim) { //está dentro dos valores válidos do array
            if (dados[menor] as Int > dados[filhoEsquerdo] as Int) {
                menor = filhoEsquerdo
            }
        }

        if (filhoDireito <= ponteiroFim) { //está dentro dos valores válidos do array
            if (dados[menor] as Int > dados[filhoDireito] as Int) {
                menor = filhoDireito
            }
        }

        if (menor != pai) {
            trocar(pai, menor)
            ajustarAbaixo(menor)
        }
    }
	
    private fun trocar(i: Int, j: Int) {
        val temp = dados[i]
        dados[i] = dados[j]
        dados[j] = temp
    }

    private fun indicePai(filho: Int): Int {
        return filho/2
    }

	override fun extrair(): Any? {
		var raiz: Any? = null
        if (!estaVazia()) {
			raiz = dados[0]
			dados[0] = dados[ponteiroFim]
			ponteiroFim.dec()
			ajustarAbaixo(0)
        } else {
			println("Heap está Vazio!")
		}
        return raiz
    }

    override fun obterRaiz(): Any? {
        var dadoRaiz: Any? = null
        if (!estaVazia())
            dadoRaiz = dados[0]

        return dadoRaiz
    }
        
	override fun estaVazia(): Boolean {
        return ponteiroFim == -1
    }

	override fun estaCheia(): Boolean {
		return ponteiroFim == dados.size - 1
    }

	override fun imprimir(): String {
		var resultado = "["
		for (i in 0..ponteiroFim) {
            resultado += "${dados[i]}"
			if (i != ponteiroFim)
				resultado += ","
		}
		return "$resultado]"
	}
}