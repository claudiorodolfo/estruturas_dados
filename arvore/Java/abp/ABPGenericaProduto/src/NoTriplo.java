/**
 * Representa um nó de árvore binária com ponteiros para o genitor, esquerda e direita.
 * 
 * @param <T> Tipo do dado armazenado no nó.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class NoTriplo<T> {

	/**
	 * Dado armazenado no nó.
	 */
	private T dado;

	/**
	 * Referência ao nó genitor (pai).
	 */
	private NoTriplo<T> genitor;

	/**
	 * Referência ao nó filho à esquerda.
	 */
	private NoTriplo<T> esquerda;

	/**
	 * Referência ao nó filho à direita.
	 */
	private NoTriplo<T> direita;

	/**
	 * Retorna o dado armazenado.
	 * @return Dado do nó.
	 */
	public T getDado() {
		return dado;
	}

	/**
	 * Define o dado do nó.
	 * @param dado Novo valor.
	 */
	public void setDado(T dado) {
		this.dado = dado;
	}	

	/**
	 * Retorna o genitor (pai) do nó.
	 * @return Nó genitor.
	 */
	public NoTriplo<T> getGenitor() {
		return genitor;
	}

	/**
	 * Define o genitor (pai) do nó.
	 * @param genitor Novo nó genitor.
	 */
	public void setGenitor(NoTriplo<T> genitor) {
		this.genitor = genitor;
	}

	/**
	 * Retorna o filho à esquerda.
	 * @return Nó à esquerda.
	 */
	public NoTriplo<T>  getEsquerda() {
		return esquerda;
	}

	/**
	 * Define o filho à esquerda.
	 * @param esquerda Novo nó à esquerda.
	 */
	public void setEsquerda(NoTriplo<T> esquerda) {
		this.esquerda = esquerda;
	}	

	/**
	 * Retorna o filho à direita.
	 * @return Nó à direita.
	 */
	public NoTriplo<T> getDireita() {
		return direita;
	}

	/**
	 * Define o filho à direita.
	 * @param direita Novo nó à direita.
	 */
	public void setDireita(NoTriplo<T> direita) {
		this.direita = direita;
	}
}