class FilaEstaticaComPilhas(val tamanho: Int = 10) : Enfileiravel {
 
	private var pilha1: Empilhavel = PilhaEstatica(tamanho) 
	private var pilha2: Empilhavel = PilhaEstatica(tamanho)

	override fun enfileirar(dado: Any?) {
        //coloca dado na pilha2
        pilha2.empilhar(dado)
        //volta dados da pilha1 para pilha2
        while(!pilha1.estaVazia()) {
            pilha2.empilhar(pilha1.desempilhar())
        }
        //volta todos os dados pra pilha1
        while(!pilha2.estaVazia()) {
            pilha1.empilhar(pilha2.desempilhar())
        }        
    }
	
    override fun atualizar(dado: Any?) {
        pilha1.atualizar(dado)
    }

	override fun desenfileirar(): Any? {
        return pilha1.desempilhar()
    }
	
	override fun frente(): Any? {
        return pilha1.espiar()
    }
	
	override fun estaCheia(): Boolean {
        return pilha1.estaCheia()
    }
	
	override fun estaVazia(): Boolean {
        return pilha1.estaVazia()
	}
	
	override fun imprimir(): String {
        return pilha1.imprimir()
    }
}