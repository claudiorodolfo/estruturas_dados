class PilhaEstaticaComFilas(val tamanho: Int = 10) : Empilhavel {
	
	private var f1: Enfileiravel = FilaEstaticaCircular(tamanho)
	private var f2: Enfileiravel = FilaEstaticaCircular(tamanho)
 	
	override fun empilhar(dado: Any?) {
		if (!estaCheia()) {	
			//joga todo mundo pra fila 2
			while(!f1.estaVazia())
				f2.enfileirar(f1.desenfileirar());

			//enfileira o dado
			f1.enfileirar(dado)
			//enfileira os dados pre-existentes
			while(!f2.estaVazia())
				f1.enfileirar(f2.desenfileirar())
		} else {
			println("Pilha Cheia!")
		}			
	}

	override fun atualizar(): Any? {
		return f1.atualizar()
	}

	override fun desempilhar(): Any? {
		return f1.desenfileirar()
	}
	
	override fun espiar(): Any? {
		return f1.frente()
	}
		
	override fun estaCheia(): Boolean {
		return f1.estaCheia()
	}
		
	override fun estaVazia(): Boolean {
		return f1.estaVazia()
	}
		
	override fun imprimir(): String {
		return f1.imprimir()
	}
}