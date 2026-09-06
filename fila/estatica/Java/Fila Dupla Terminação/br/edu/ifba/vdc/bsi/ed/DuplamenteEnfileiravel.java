package br.edu.ifba.vdc.bsi.ed;

public interface DuplamenteEnfileiravel extends Enfileiravel {
	void enfileirarInicio(Object dado);
	Object desenfileirarFim();
	Object tras();
	String imprimirDeTrasPraFrente();
}