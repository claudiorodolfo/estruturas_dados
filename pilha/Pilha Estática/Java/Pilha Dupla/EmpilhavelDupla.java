public interface EmpilhavelDupla {
	//métodos da Pilha1
	void empilhar1(Object dado);
	Object desempilhar1();
	Object topo1();	
	boolean estaCheia1(); //igual a estaCheia2()
	boolean estaVazia1();
	String imprimir1();
	
	//métodos da Pilha2
	void empilhar2(Object dado);
	Object desempilhar2();
	Object topo2();	
	boolean estaCheia2(); //igual a estaCheia1()
	boolean estaVazia2();
	String imprimir2();
}