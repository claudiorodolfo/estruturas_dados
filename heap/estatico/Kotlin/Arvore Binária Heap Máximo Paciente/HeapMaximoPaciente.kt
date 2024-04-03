data class Paciente(val nome: String, val idade: Int, val prioridade: Int)

class HeapMaximoPaciente(private val tamanho: Int = 10): Amontoavel {

	private var dados = Array<Paciente>(tamanho)
    private var ponteiroFim = -1 
	
    override fun inserir(dado: Paciente) {
        if (!estaCheia()) {
			ponteiroFim = ponteiroFim.inc()
            dados[ponteiroFim] = dado
			ajustarAcima(ponteiroFim)
        } else {
			println("Fila de Prioridades Cheia!")
		}
    }

    private fun ajustarAcima(indice: Int) {
        var indiceAtual = indice
        while (indiceAtual != 0) {
            val indicePai = indicePai(indiceAtual)
            if (dados[indicePai].prioridade > dados[indiceAtual].prioridade) {
                trocar(indiceAtual, indicePai)
                indiceAtual = indicePai
            } else {
                break
            }
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

    private fun ajustarAbaixo(pai: Int) {
        val filhoEsquerdo = indiceFilhoEsquerda(pai)
        val filhoDireito = indiceFilhoDireita(pai)
        var menor = pai;

        if (filhoEsquerdo <= ponteiroFim) //está dentro dos valores válidos do array
            if (dados[menor] > dados[filhoEsquerdo])
                menor = filhoEsquerdo

        if (filhoDireito <= ponteiroFim) //está dentro dos valores válidos do array
            if (dados[menor] > dados[filhoDireito])
                menor = filhoDireito

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

	override fun extrair(): Int? {
		var dadoRaiz: Int? = null
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

    override fun obter(): Int? {
        var dadoRaiz: Int? = null
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