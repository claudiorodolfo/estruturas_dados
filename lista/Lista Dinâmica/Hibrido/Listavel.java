public interface Listavel {
	
	//anexa(insere ao final da lista) a lista o novo dado fornecido
	void anexar(Object dado);
	//insere o novo dado fornecido na lista, numa posicao logica informada 
	void inserir(int posicao, Object dado);	
	//retorna o elemento que está numa posicao logica informada
	Object selecionar(int posicao);
	//retorna o elemento que está numa posicao logica informada
	Object[] selecionarTodos();
	//substitui o elemento de uma posicao logica informada,
	//pelo novo elemento fornecido
	void atualizar(int posicao, Object novoDado);	
	//remove o elemento de uma posicao logica informada
	Object apagar(int posicao);
	
	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}