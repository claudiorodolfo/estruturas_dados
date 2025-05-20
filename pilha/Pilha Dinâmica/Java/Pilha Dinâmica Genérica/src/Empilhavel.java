/**
 * Interface que define as operações básicas de uma pilha.
 * Esta interface define os métodos que devem ser implementados
 * por qualquer classe que represente uma estrutura de dados do tipo pilha.
 *
 * @param <T> o tipo dos elementos armazenados na pilha
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-05-01
 */
public interface Empilhavel<T> {
	
	/**
	 * Adiciona um elemento ao topo da pilha.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a pilha estiver cheia
	 */
	void empilhar(T dado);
	
	/**
	 * Remove e retorna o elemento do topo da pilha.
	 *
	 * @return o elemento removido do topo
	 * @throws java.util.NoSuchElementException se a pilha estiver vazia
	 */
	T desempilhar();
	
	/**
	 * Retorna o elemento do topo da pilha sem removê-lo.
	 *
	 * @return o elemento do topo
	 * @throws java.util.NoSuchElementException se a pilha estiver vazia
	 */
	T espiar();
	
	/**
	 * Retorna um array contendo todos os elementos da pilha.
	 * O elemento do topo será o último elemento do array.
	 *
	 * @param dado o elemento substituirá o elemento do topo
	 * @throws java.util.NoSuchElementException se a pilha estiver vazia
	 */
	void atualizar(T dado);
	
	/**
	 * Verifica se a pilha está cheia.
	 *
	 * @return true se a pilha estiver cheia, false caso contrário
	 */
	boolean estaCheia();
	
	/**
	 * Verifica se a pilha está vazia.
	 *
	 * @return true se a pilha estiver vazia, false caso contrário
	 */
	boolean estaVazia();
	
	/**
	 * Retorna uma representação em string da pilha.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 * O elemento do topo será o último elemento na string.
	 *
	 * @return string representando a pilha
	 */
	String imprimir();
}