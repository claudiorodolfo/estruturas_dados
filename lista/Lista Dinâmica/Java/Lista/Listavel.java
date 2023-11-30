public interface Listavel {
	
	//anexa(insere ao final da lista) a lista o novo dado fornecido
	void anexar(Object dado) throws OverflowException;
	
	//insere o novo dado fornecido na lista, numa posicao logica informada 
	void inserir(int posicao, Object dado) throws OverflowException;
	
	//retorna o dado que está numa posicao logica informada
	Object selecionar(int posicao);
	
	//retorna todos os dado da ED
	Object[] selecionarTodos();	
	//substitui o dado de uma posicao logica informada,
	//pelo novo dado fornecido
	void atualizar(int posicao, Object novodado);
	
	//remove o dado de uma posicao logica informada
	Object apagar(int posicao) throws UnderflowException;
	
	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}