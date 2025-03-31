public interface DuplamenteEnfileiravel {
	//NOVO
	void enfileirarInicio(Object dado);
	//funciona como o enfileirar de Enfileiravel
	void enfileirarFim(Object dado);
	
	//funciona como o desenfileirar de Enfileiravel
	Object desenfileirarInicio();
	//NOVO
	Object desenfileirarFim();
	
	//frente (funciona como o frente de Enfileiravel)
	Object frente();
	//NOVO
	Object tras();

	//funciona como o atualizarInicio de Enfileiravel
	void atualizarInicio(Object dado);
	//funciona como o atualizarFim de Enfileiravel
	void atualizarFim(Object dado);
	
	//funciona como o estaCheia de Enfileiravel
	boolean estaCheia();
	//funciona como o estaVazia de Enfileiravel
	boolean estaVazia();
	//funciona como o imprimir de Enfileiravel
	String imprimirDeFrentePraTras();
	//NOVO
	String imprimirDeTrasPraFrente();
}