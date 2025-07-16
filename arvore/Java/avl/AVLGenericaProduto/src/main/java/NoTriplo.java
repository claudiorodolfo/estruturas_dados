/**
 * Representa um nó de árvore binária AVL, contendo ponteiros para o genitor (pai),
 * filhos esquerdo e direito, além do valor armazenado e a altura do nó.
 * @param <T> Tipo do dado armazenado no nó.
 */
public class NoTriplo<T> {
	private T dado;
	private NoTriplo<T> genitor;
	private NoTriplo<T> esquerda;
	private NoTriplo<T> direita;
	private int altura;

	/**
	 * Cria um novo nó com altura inicial zero.
	 */
	public NoTriplo() {
		altura = 0;
	}
	
	/**
	 * Retorna a altura do nó na árvore AVL.
	 * @return Altura do nó.
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Define a altura do nó na árvore AVL.
	 * @param altura Nova altura.
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Retorna o dado armazenado neste nó.
	 * @return Dado do nó.
	 */
	public T getDado() {
		return dado;
	}

	/**
	 * Define o dado armazenado neste nó.
	 * @param dado Novo valor.
	 */
	public void setDado(T dado) {
		this.dado = dado;
	}	

	/**
	 * Retorna o nó genitor (pai) deste nó.
	 * @return Nó genitor.
	 */
	public NoTriplo<T> getGenitor() {
		return genitor;
	}

	/**
	 * Define o nó genitor (pai) deste nó.
	 * @param genitor Novo nó genitor.
	 */
	public void setGenitor(NoTriplo<T> genitor) {
		this.genitor = genitor;
	}

	/**
	 * Retorna o filho à esquerda deste nó.
	 * @return Nó à esquerda.
	 */
	public NoTriplo<T>  getEsquerda() {
		return esquerda;
	}

	/**
	 * Define o filho à esquerda deste nó.
	 * @param esquerda Novo nó à esquerda.
	 */
	public void setEsquerda(NoTriplo<T> esquerda) {
		this.esquerda = esquerda;
	}	

	/**
	 * Retorna o filho à direita deste nó.
	 * @return Nó à direita.
	 */
	public NoTriplo<T> getDireita() {
		return direita;
	}

	/**
	 * Define o filho à direita deste nó.
	 * @param direita Novo nó à direita.
	 */
	public void setDireita(NoTriplo<T> direita) {
		this.direita = direita;
	}
}