public interface DuplamenteEnfileiravel {
	//funciona como o enfileirar de Enfileiravel
	void enfileirarFim(Object dado);
	//desenfileirar no inicio (funciona como o desenfileirar de Enfileiravel
	Object desenfileirarInicio();
	//funciona como o espiar de Enfileiravel
	Object espiarInicio();
	//funciona como o estaCheia de Enfileiravel
	boolean estaCheia();
	//funciona como o estaVazia de Enfileiravel
	boolean estaVazia();
	//funciona como o imprimir de Enfileiravel
	String imprimir();

	Object espiarFim();
	Object desenfileirarFim();
	void enfileirarInicio(Object dado);
}