public interface Enfileiravel {
    void enfileirar(Object dado); 		//C	enqueue
    Object frente();					//R	front
    void atualizarInicio(Object dado);	//U	update
    void atualizarFim(Object dado);		//U	update  
    Object desenfileirar();				//D dequeue
										//S sort
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}