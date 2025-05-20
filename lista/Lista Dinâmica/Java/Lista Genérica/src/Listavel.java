/**
 * Interface que define as operações básicas de uma lista.
 * Esta interface define os métodos que devem ser implementados
 * por qualquer classe que represente uma estrutura de dados do tipo lista.
 *
 * @param <T> o tipo dos elementos armazenados na lista
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-05-01
 */
public interface Listavel<T> {
	
	/**
	 * Adiciona um elemento ao final da lista.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws OverflowException se a lista estiver cheia
	 */
	void anexar(T dado);
	
	/**
	 * Insere um elemento em uma posição específica da lista.
	 *
	 * @param posicao a posição onde o elemento será inserido
	 * @param dado o elemento a ser inserido
	 * @throws OverflowException se a lista estiver cheia
	 * @throws IndexOutOfBoundsException se a posição for inválida
	 */
	void inserir(int posicao, T dado);
	
	/**
	 * Retorna o elemento em uma posição específica da lista.
	 *
	 * @param posicao a posição do elemento desejado
	 * @return o elemento na posição especificada
	 * @throws UnderflowException se a lista estiver vazia
	 * @throws IndexOutOfBoundsException se a posição for inválida
	 */
	T selecionar(int posicao);
	
	/**
	 * Retorna um array contendo todos os elementos da lista.
	 *
	 * @return array com todos os elementos da lista
	 * @throws UnderflowException se a lista estiver vazia
	 */
	T[] selecionarTodos();

	/**
	 * Atualiza o elemento em uma posição específica da lista.
	 *
	 * @param posicao a posição do elemento a ser atualizado
	 * @param novoDado o novo elemento
	 * @throws UnderflowException se a lista estiver vazia
	 * @throws IndexOutOfBoundsException se a posição for inválida
	 */
	void atualizar(int posicao, T novoDado);
	
	/**
	 * Remove o elemento em uma posição específica da lista.
	 *
	 * @param posicao a posição do elemento a ser removido
	 * @return o elemento removido
	 * @throws UnderflowException se a lista estiver vazia
	 * @throws IndexOutOfBoundsException se a posição for inválida
	 */
	T apagar(int posicao);
	
	/**
	 * Verifica se a lista está cheia.
	 *
	 * @return true se a lista estiver cheia, false caso contrário
	 */
	boolean estaCheia();
	
	/**
	 * Verifica se a lista está vazia.
	 *
	 * @return true se a lista estiver vazia, false caso contrário
	 */
	boolean estaVazia();
	
	/**
	 * Retorna uma representação em string da lista.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a lista
	 */
	String imprimir();
}