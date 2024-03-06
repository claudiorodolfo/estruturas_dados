public interface EmpilhavelDupla {
	//métodos da Pilha1
    fun empilhar1(dado: Any?)
    fun desempilhar1(): Any?
    fun espiar1(): Any?
    fun atualizar1(dado: Any?)     
    fun estaCheia1(): Boolean  //igual a estaCheia2()
    fun estaVazia1(): Boolean
    fun imprimir1(): String	
	
	//métodos da Pilha2
    fun empilhar2(dado: Any?)
    fun desempilhar2(): Any?
    fun atualizar2(dado: Any?) 
    fun espiar2(): Any?
    fun estaCheia2(): Boolean  //igual a estaCheia1()
    fun estaVazia2(): Boolean
    fun imprimir2(): String	
}