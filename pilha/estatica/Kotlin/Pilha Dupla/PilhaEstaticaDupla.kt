class PilhaEstaticaDupla : EmpilhavelDupla {
    
	private var ponteiroTopo1: Int
	private var ponteiroTopo2: Int
    private var dados: Array<Any?>
	
	constructor(tamanho: Int) {
		dados = arrayOfNulls(tamanho)
		ponteiroTopo1 = -1
        ponteiroTopo2 = tamanho
	}
	
	constructor() : this(10)

    //PILHA 1
	override fun atualizar1(dado: Any?) {
		if (!estaVazia1())
			dados[ponteiroTopo1] = dado
		else
			println("Stack1 is empty!")
	}

	override fun empilhar1(dado: Any?) {
		if (!estaCheia1()) {
			ponteiroTopo1++
			dados[ponteiroTopo1] = dado
		} else {
			println("Stack1 is full!")
		}
	}
	
	override fun desempilhar1(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia1()) {
			dadoTopo = dados[ponteiroTopo1]
			ponteiroTopo1--
		} else {
			println("Stack1 is empty!")
		}
		return dadoTopo
	}
	
	override fun espiar1(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia1()) {
			dadoTopo = dados[ponteiroTopo1]
		} else {
			println("Stack1 is empty!")
		}
		return dadoTopo
	}
	
	override fun estaCheia1(): Boolean {
		return (ponteiroTopo1 == ponteiroTopo2-1)
	}
	
	override fun estaVazia1(): Boolean {
		return (ponteiroTopo1 == -1)
	}
	
	override fun imprimir1(): String {
		var resultado = "["
		for (i in ponteiroTopo1 downTo 0) {
			if (i == 0)
				resultado += "${dados[i]}"
			else
				resultado += "${dados[i]}, "
		}
		return "$resultado]"
	}

    //PILHA 2
	override fun atualizar2(dado: Any?) {
		if (!estaVazia2())
			dados[ponteiroTopo2] = dado
		else
			println("Stack2 is empty!")
	}

	override fun empilhar2(dado: Any?) {
		if (!estaCheia2()) {
			ponteiroTopo2--
			dados[ponteiroTopo2] = dado
		} else {
			println("Stack2 is full!")
		}
	}
	
	override fun desempilhar2(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia2()) {
			dadoTopo = dados[ponteiroTopo2]
			ponteiroTopo2++
		} else {
			println("Stack2 is empty!")
		}
		return dadoTopo
	}
	
	override fun espiar2(): Any? {
		var dadoTopo: Any? = null
		if (!estaVazia2()) {
			dadoTopo = dados[ponteiroTopo2]
		} else {
			println("Stack2 is empty!")
		}
		return dadoTopo
	}
	
	override fun estaCheia2(): Boolean {
		return estaCheia1()
	}
	
	override fun estaVazia2(): Boolean {
		return (ponteiroTopo2 == dados.size)
	}
	
	override fun imprimir2(): String {
		var resultado = "["
		for (i in ponteiroTopo2 .. dados.size-1) {
			if (i == dados.size-1)
				resultado += "${dados[i]}"
			else
				resultado += "${dados[i]}, "
		}
		return "$resultado]"
	}
}