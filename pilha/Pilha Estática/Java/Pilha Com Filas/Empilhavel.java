public interface Empilhavel {
	void empilhar(Object elemento);
	Object desempilhar();
	Object espiar();
	Object atualizar();
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}