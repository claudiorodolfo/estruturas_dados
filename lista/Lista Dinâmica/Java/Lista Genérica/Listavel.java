public interface Listavel<T> {
	
	//anexa(insere ao final da lista) a lista o novo dado fornecido
	void anexar(T dado) throws OverflowException;
	
	//insere o novo dado fornecido na lista, numa posicao logica informada 
	void inserir(int posicao, T dado) throws OverflowException;
	
	//retorna o elemento que está numa posicao logica informada
	T selecionar(int posicao);
	
	//retorna o elemento que está numa posicao logica informada
	T[] selecionarTodos();

	//substitui o elemento de uma posicao logica informada,
	//pelo novo elemento fornecido
	void atualizar(int posicao, T novoDado);
	
	//remove o elemento de uma posicao logica informada
	T apagar(int posicao)throws UnderflowException;
	
	//métodos auxiliares
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}