class ArvoreBinariaHeapMinimo(private val tamanho: Int = 10): Amontoavel {

    private var dados = LongArray(tamanho){0}
    private var ponteiroFim = -1 
	
    override fun inserir(dado: Long) {
        if (!estaCheia()) {
			ponteiroFim = ponteiroFim.inc()
            dados[ponteiroFim] = dado
			ajustarAcima(ponteiroFim)
        } else {
			println("Heap está Cheio!")
		}
    }

    private fun ajustarAcima(indice: Int) {
        var indiceFilho = indice
        while (indiceFilho != 0) {
            val indicePai = indicePai(indiceFilho)
            if (dados[indicePai] > dados[indiceFilho]) {
                trocar(indiceFilho, indicePai)	// Troca o elemento com seu pai se for menor
                indiceFilho = indicePai		//O pai passa a ser o novo filho, para continuar ajustando acima
            } else {
                break
            }
        }
    }
    
    private fun ajustarAcimaRec(indice: Int) {
        var indiceFilho = indice
        // Caso base: o elemento está na raiz, então não precisa ajustar
        if (indiceFilho == 0) return  
    
        val indicePai = indicePai(indiceFilho)
        if (dados[indicePai] > dados[indiceFilho]) {
            trocar(indiceFilho, indicePai)	// Troca o elemento com seu pai se for menor
            ajustarAcimaRec(indicePai)			// Chama recursivamente para continuar ajustando acima
        }
    }
    
    private fun ajustarAbaixo(indice: Int) {
        var pai = indice
    
        while (pai <= ponteiroFim) {
            val filhoEsquerdo = indiceFilhoEsquerda(pai)
            val filhoDireito = indiceFilhoDireita(pai)
            var menor = pai  // Assume que o pai é o menor inicialmente
    
            if (filhoEsquerdo <= ponteiroFim) //está dentro dos valores válidos do array (ou seja, o nó pai tem filho esquerdo)?
                if (dados[filhoEsquerdo] < dados[menor])
                    menor = filhoEsquerdo
    
            if (filhoDireito <= ponteiroFim) //está dentro dos valores válidos do array (ou seja, o nó pai tem filho direito)?
                if (dados[filhoDireito] < dados[menor])
                    menor = filhoDireito
    
            // O menor não é o pai. Realiza a troca e continua ajustando para baixo
            if (menor != pai) {
                trocar(pai, menor)
                pai = menor
            } else {
                break // Se o menor é o pai, significa que o heap está ajustado
            }
        }
    }
      
    private fun ajustarAbaixoRec(indice: Int) {
        val pai = indice
        val filhoEsquerdo = indiceFilhoEsquerda(pai)
        val filhoDireito = indiceFilhoDireita(pai)
        var menor = pai;	// Assume que o pai é o menor inicialmente
    
        if (filhoEsquerdo <= ponteiroFim) //está dentro dos valores válidos do array (ou seja, o nó pai tem filho esquerdo)?
            if (dados[menor] > dados[filhoEsquerdo])	//filho menor que o pai
                menor = filhoEsquerdo
    
        if (filhoDireito <= ponteiroFim) //está dentro dos valores válidos do array (ou seja, o nó pai tem filho direito)?
            if (dados[menor] > dados[filhoDireito])		//filho menor que o pai
                menor = filhoDireito
                
        // O menor não é o pai. Realiza a troca e continua ajustando para baixo
        if (menor != pai) {
            trocar(pai, menor)
            ajustarAbaixoRec(menor)
        }
    }

    private fun indicePai(indiceFilho: Int): Int {
        return (indiceFilho-1)/2
    }

    private fun indiceFilhoEsquerda(indicePai: Int): Int {
        return 2 * indicePai + 1
    }

	private fun indiceFilhoDireita(indicePai: Int): Int	{
        return (2 * indicePai + 1) + 1
    }
	
    private fun trocar(i: Int, j: Int) {
        val temp = dados[i]
        dados[i] = dados[j]
        dados[j] = temp
    }

    override fun extrair(): Long? {
		var dadoRaiz: Long? = null
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

    override fun atualizar(dado: Long){
        if (!estaVazia()) {
            dados[0] = dado
            ajustarAbaixo(0)
        } else {
            print("Heap Vazia!")
        }
    }

    override fun obter(): Long? {
        var dadoRaiz: Long? = null
        if (!estaVazia())
            dadoRaiz = dados[0]
        else
            print("Heap Vazia!")

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