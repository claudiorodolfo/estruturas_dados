public interface Empilhavel {
	void empilhar(Object dado);
	Object desempilhar();
	Object topo();
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}