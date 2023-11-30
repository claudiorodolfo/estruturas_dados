public interface Empilhavel {
	//métodos principais
	void empilhar(Object dado);
	Object desempilhar();
	Object topo();
	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}