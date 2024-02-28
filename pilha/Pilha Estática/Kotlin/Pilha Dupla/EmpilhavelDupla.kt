public interface EmpilhavelDupla {
	//métodos da Pilha1
    fun empilhar1(dado: Any?)
    fun desempilhar1(): Any?
    fun topo1(): Any?
    fun estaCheia1(): Boolean
    fun estaVazia1(): Boolean
    fun imprimir1(): String	
	
	//métodos da Pilha2
    fun empilhar2(dado: Any?)
    fun desempilhar2(): Any?
    fun topo2(): Any?
    fun estaCheia2(): Boolean  //igual a estaCheia1()
    fun estaVazia2(): Boolean
    fun imprimir2(): String	
}