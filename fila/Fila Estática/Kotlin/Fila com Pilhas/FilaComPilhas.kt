class FilaComPilhas(val tamanho: Int = 10) : Enfileiravel {
 
	private var pilha1: Empilhavel = PilhaEstatica(tamanho) 
	private var pilha2: Empilhavel = PilhaEstatica(tamanho)


	override fun atualizar(dado: Any?) {
        pilha2.atualizar(dado)
    }

	override fun enfileirar(dado: Any?) {
        //coloca dado na pilha1
        pilha1.empilhar(dado)
        //volta dados da pilha2 para pilha1
        while(!pilha2.estaVazia()) {
            pilha1.empilhar(pilha2.desempilhar())
        }
        //volta todos os dados pra pilha2
        while(!pilha1.estaVazia()) {
            pilha2.empilhar(pilha1.desempilhar())
        }        
    }
	
	override fun desenfileirar(): Any? {
        return pilha2.desempilhar()
    }
	
	override fun frente(): Any? {
        return pilha2.espiar()
    }
	
	override fun estaCheia(): Boolean {
        return pilha2.estaCheia()
    }
	
	override fun estaVazia(): Boolean {
        return pilha2.estaVazia()
	}
	
	override fun imprimir(): String {
        return pilha2.imprimir()
    }
}