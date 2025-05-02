public interface Empilhavel {
	void empilhar(Object dado);
	Object desempilhar();
	Object espiar();
	void atualizar(Object dado);
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}