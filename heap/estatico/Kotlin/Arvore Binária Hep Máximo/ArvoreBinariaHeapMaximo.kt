class ArvoreBinariaHeapMaximo(private val tamanho: Int = 10): Amontoavel {

	private var dados = IntArray(tamanho){0}
    private var ponteiroFim = -1 
	
    override fun inserir(dado: Int) {
        if (!estaCheia()) {
			ponteiroFim = ponteiroFim.inc()
            dados[ponteiroFim] = dado
			ajustarAcima(ponteiroFim)
        } else {
			println("Heap está Cheio!")
		}
    }

    private fun ajustarAcima(indice: Int) {
        var indiceAtual = indice
        while (indiceAtual != 0) {
            if (dados[indicePai(indiceAtual)] < dados[indiceAtual]) {
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
        var maior = pai;

        if (filhoEsquerdo <= ponteiroFim) //está dentro dos valores válidos do array
            if (dados[maior] < dados[filhoEsquerdo])
                maior = filhoEsquerdo

        if (filhoDireito <= ponteiroFim) //está dentro dos valores válidos do array
            if (dados[maior] < dados[filhoDireito])
                maior = filhoDireito

        if (maior != pai) {
            trocar(pai, maior)
            ajustarAbaixo(maior)
        }
    }
	
    private fun trocar(i: Int, j: Int) {
        val temp = dados[i]
        dados[i] = dados[j]
        dados[j] = temp
    }

    private fun indicePai(indiceFilho: Int): Int {
        return (indiceFilho-1)/2
    }

	override fun extrair(): Int {
		var dadoRaiz = -1
        if (!estaVazia()) {
			dadoRaiz = dados[0]
			dados[0] = dados[ponteiroFim]
			ponteiroFim = ponteiroFim.dec()
			ajustarAbaixo(0)
        } else {
			println("Heap está Vazio!")
		}
        return dadoRaiz
    }

    override fun atualizar(dado: Int){
        if (!estaVazia()) {
            dados[0] = dado
            ajustarAbaixo(0)
        } else {
            print("Heap Vazia!")
        }
    }

    override fun obter(): Int {
        var dadoRaiz = -1
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