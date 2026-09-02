package br.edu.ifba.vdc.bsi.ed;

public interface EnfileiravelGenerica<T> {
	void enfileirar(T dado);
	T desenfileirar();
	T frente();
	void atualizarInicio(T dado);
	void atualizarFim(T dado);
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}