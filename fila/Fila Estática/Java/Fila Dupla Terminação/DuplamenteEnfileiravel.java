public interface DuplamenteEnfileiravel {
	void enfileirarInicio(Object dado);
	//enfileirar no final (funciona como o enfileirar de Enfileiravel)
	void enfileirarFim(Object dado);
	//desenfileirar no inicio (funciona como o desenfileirar de Enfileiravel)
	Object desenfileirarInicio();
	Object desenfileirarFim();
	//espiar no inicio (funciona como o espiar de Enfileiravel)
	Object espiarInicio();
	//espiar no final
	Object espiarFim();
	//estaCheio (funciona como o estaCheia de Enfileiravel)
	boolean estaCheia();
	//estaVazio (funciona como o estaVazia de Enfileiravel)
	boolean estaVazia();
	//paraTexto (funciona como o imprimir de Enfileiravel)
	String imprimir();
}