import java.util.NoSuchElementException;

/**
 * Interface que define as operações básicas de uma fila dupla terminacão genérica.
 * Esta interface define os métodos que devem ser implementados
 * por qualquer classe que represente uma estrutura de dados do tipo fila dupla terminacão.
 *
 * @param <T> o tipo dos elementos armazenados na fila
 * @author mac
 * @version 1.0
 */
public interface Enfileiravel<T> {
	
	/**
	 * Adiciona um elemento ao início da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	void enfileirarInicio(T dado);
	
	/**
	 * Adiciona um elemento ao final da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	void enfileirarFim(T dado);
	
	/**
	 * Retorna o elemento do início da fila sem removê-lo.
	 *
	 * @return o elemento do início
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	T frente();
	
	/**
	 * Retorna o elemento do final da fila sem removê-lo.
	 *
	 * @return o elemento do final
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	T tras();
	
	/**
	 * Atualiza o elemento do início da fila.
	 *
	 * @param dado o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	void atualizarInicio(T dado);
	
	/**
	 * Atualiza o elemento do final da fila.
	 *
	 * @param dado o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	void atualizarFim(T dado);
	
	/**
	 * Remove e retorna o elemento do início da fila.
	 *
	 * @return o elemento removido do início
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	T desenfileirarInicio();
	
	/**
	 * Remove e retorna o elemento do final da fila.
	 *
	 * @return o elemento removido do final
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	T desenfileirarFim();
	
	/**
	 * Verifica se a fila está cheia.
	 *
	 * @return true se a fila estiver cheia, false caso contrário
	 */
	boolean estaCheia();
	
	/**
	 * Verifica se a fila está vazia.
	 *
	 * @return true se a fila estiver vazia, false caso contrário
	 */
	boolean estaVazia();
	
	/**
	 * Retorna uma representação em string da fila do final para o início.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a fila do final para o início
	 */
	String imprimirDeTrasPraFrente();
	
	/**
	 * Retorna uma representação em string da fila do início para o final.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a fila do início para o final
	 */
	String imprimirDeFrentePraTras();
}