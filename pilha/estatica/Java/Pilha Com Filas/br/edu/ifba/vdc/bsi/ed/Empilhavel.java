package br.edu.ifba.vdc.bsi.ed;

public interface Empilhavel {
	void empilhar(Object elemento);
	Object desempilhar();
	Object espiar();
	void atualizar(Object dado);
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}