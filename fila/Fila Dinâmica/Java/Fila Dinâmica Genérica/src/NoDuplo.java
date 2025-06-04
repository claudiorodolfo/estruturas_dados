/**
 * Classe que representa um nó duplamente encadeado.
 * Esta classe é utilizada para implementar estruturas de dados
 * que necessitam de nós com referências para o próximo e anterior.
 *
 * @param <T> o tipo do dado armazenado no nó
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-06-04
 */
public class NoDuplo<T> {
	
	/** O dado armazenado no nó */
	private T dado;
	
	/** Referência para o nó anterior */
	private NoDuplo<T> anterior;
	
	/** Referência para o próximo nó */
	private NoDuplo<T> proximo;

	public NoDuplo() {
	}

	public NoDuplo(T dado) {
		this.dado = dado;
	}

	/**
	 * Retorna o dado armazenado no nó.
	 *
	 * @return o dado do nó
	 */
	public T getDado() {
		return dado;
	}

	/**
	 * Define o dado a ser armazenado no nó.
	 *
	 * @param dado o novo dado
	 */
	public void setDado(T dado) {
		this.dado = dado;
	}

	/**
	 * Retorna a referência para o nó anterior.
	 *
	 * @return o nó anterior
	 */
	public NoDuplo<T> getAnterior() {
		return anterior;
	}

	/**
	 * Define a referência para o nó anterior.
	 *
	 * @param anterior o novo nó anterior
	 */
	public void setAnterior(NoDuplo<T> anterior) {
		this.anterior = anterior;
	}

	/**
	 * Retorna a referência para o próximo nó.
	 *
	 * @return o próximo nó
	 */
	public NoDuplo<T> getProximo() {
		return proximo;
	}

	/**
	 * Define a referência para o próximo nó.
	 *
	 * @param proximo o novo próximo nó
	 */
	public void setProximo(NoDuplo<T> proximo) {
		this.proximo = proximo;
	}
}