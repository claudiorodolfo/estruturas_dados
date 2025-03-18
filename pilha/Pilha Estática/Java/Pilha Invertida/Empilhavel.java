public interface Empilhavel {
	//métodos principais
	void empilhar(Object dado);	//C
	Object espiar();			//R
	void atualizar(Object dado);//U
	Object desempilhar();		//D
	
	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}