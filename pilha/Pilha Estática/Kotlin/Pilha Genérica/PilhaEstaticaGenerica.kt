class PilhaEstaticaGenerica<T> : Empilhavel<T> {

    private var ponteiroTopo: Int
    private var dados: Array<Any?>

    constructor(tamanho: Int) {
        ponteiroTopo = -1
        dados = arrayOfNulls(tamanho)
    }

    constructor() : this(10)

    override fun empilhar(dado: T?) {
        if (!estaCheia()) {
            ponteiroTopo++
            dados[ponteiroTopo] = dado
        } else {
            println("Pilha Cheia!")
        }
    }

    override fun desempilhar(): T? {
        var dadoTopo: T? = null
        if (!estaVazia()) {
            dadoTopo = dados[ponteiroTopo] as T
            ponteiroTopo--
        } else {
            println("Pilha Vazia!")
        }
        return dadoTopo
    }

    override fun topo(): T? {
        var dadoTopo: T? = null
        if (!estaVazia()) {
            dadoTopo = dados[ponteiroTopo] as T
        } else {
            println("Pilha Vazia!")
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
            resultado += if (i == 0)
                "${dados[i]}"
            else
                "${dados[i]},"
                
        }
        return "$resultado]"
    }
}