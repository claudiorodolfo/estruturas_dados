package repository.pilha;

//Stackable
public interface Empilhavel {
	//métodos principais
	void empilhar(Object dado);	//C - push
	Object espiar();			//R - peek
	void atualizar(Object dado);//U - update
	Object desempilhar();		//D - pop
	
	//métodos auxiliares
	boolean estaCheia();	//isFull
	boolean estaVazia();	//isEmpty
	String imprimir();	    //print
}