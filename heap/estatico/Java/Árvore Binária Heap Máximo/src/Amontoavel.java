/**
 * Interface que define as operações básicas de uma estrutura de dados heap.
 * Um heap é uma árvore binária especial que satisfaz a propriedade de heap.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public interface Amontoavel {
	/**
	 * Insere um novo elemento no heap.
	 * 
	 * @param dado o elemento a ser inserido
	 */
	void inserir(Long dado);	// C

	/**
	 * Extrai e remove o elemento raiz do heap.
	 * 
	 * @return o elemento raiz removido
	 */
	Long extrair();				// D

	/**
	 * Obtém o elemento raiz do heap sem removê-lo.
	 * 
	 * @return o elemento raiz
	 */
	Long obterRaiz();			// R

	//auxiliares
	/**
	 * Retorna uma representação em string do heap.
	 * 
	 * @return string representando o estado atual do heap
	 */
	String imprimir();

	/**
	 * Verifica se o heap está vazio.
	 * 
	 * @return true se o heap estiver vazio, false caso contrário
	 */
	boolean estaVazia();

	/**
	 * Verifica se o heap está cheio.
	 * 
	 * @return true se o heap estiver cheio, false caso contrário
	 */
	boolean estaCheia();
}