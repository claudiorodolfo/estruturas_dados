package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list.OverflowException;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list.UnderflowException;

/**
 * Implementação de uma lista dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma lista que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na lista
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-10-22
 * @see Listable
 * @see DoubleNode
 */
public class LinkedList<T> implements Listable<T> {
	/** Ponteiro para o início da lista */
	private DoubleNode<T> headPointer;

	/** Ponteiro para o fim da lista */
	private DoubleNode<T> tailPointer;

    /** Quantidade atual de elementos */
    private int amount;

    /** Tamanho máximo da ED */
    private int length;

	/**
	 * Construtor padrão que cria uma lista com capacidade para 10 elementos.
	 */
    public LinkedList() {
        this(10);
    }

	/**
	 * Construtor que cria uma lista com capacidade personalizada.
	 *
	 * @param length a capacidade máxima da lista
	 */
    public LinkedList(int length) {
		headPointer = null;
		tailPointer = null;
		amount = 0;
		this.length = length;
    }

	/**
	 * Adiciona um elemento no final da lista.
	 *
	 * @param data o elemento a ser adicionado
	 * @throws OverflowException se a lista estiver cheia
	 */
	@Override
	public void append(T data) {
		if (isFull()) {
			throw new OverflowException("Lista Cheia!");
		}
		DoubleNode<T> tempNode = new DoubleNode<T>(data);

		if (!isEmpty()) {
			tailPointer.setNext(tempNode);
			tempNode.setPrevious(tailPointer);
		} else {
			headPointer = tempNode;
		}

		tailPointer = tempNode;
		amount++;
	}

	/**
	 * Adiciona um elemento em uma posição específica da lista.
	 *
	 * @param index a posição onde o elemento será inserido
	 * @param data o elemento a ser adicionado
	 * @throws OverflowException se a lista estiver cheia
	 * @throws IndexOutOfBoundsException se o índice for inválido
	 */
	@Override
	public void insert(int index, T data) {
		if (isFull()) {
			throw new OverflowException("Lista Cheia!");
		}
		if (index < 0 || index > amount) {
			throw new IndexOutOfBoundsException("Índice inválido: " + index);
		}

		if (index == amount) {
			append(data);
			return;
		}

		DoubleNode<T> tempNode = new DoubleNode<T>(data);

		if (index == 0) {
			// Inserir no início
			tempNode.setNext(headPointer);
			if (headPointer != null) {
				headPointer.setPrevious(tempNode);
			}
			headPointer = tempNode;
			if (amount == 0) {
				tailPointer = tempNode;
			}
		} else {
			// Inserir no meio
			DoubleNode<T> current = getNodeAt(index);
			DoubleNode<T> previous = current.getPrevious();

			tempNode.setNext(current);
			tempNode.setPrevious(previous);
			previous.setNext(tempNode);
			current.setPrevious(tempNode);
		}
		amount++;
	}

	/**
	 * Obtém um elemento por índice.
	 *
	 * @param index o índice do elemento
	 * @return o elemento na posição especificada
	 * @throws IndexOutOfBoundsException se o índice for inválido
	 * @throws UnderflowException se a lista estiver vazia
	 */
	@Override
	public T select(int index) {
		if (isEmpty()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (index < 0 || index >= amount) {
			throw new IndexOutOfBoundsException("Índice inválido: " + index);
		}
		return getNodeAt(index).getData();
	}

	/**
	 * Obtém todos os elementos da lista como um array.
	 *
	 * @return array com todos os elementos da lista
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] selectAll() {
		if (isEmpty()) {
			return (T[]) new Object[0];
		}

		Object[] array = new Object[amount];
		DoubleNode<T> current = headPointer;
		for (int i = 0; i < amount; i++) {
			array[i] = current.getData();
			current = current.getNext();
		}
		return (T[]) array;
	}

	/**
	 * Atualiza um elemento em uma posição específica.
	 *
	 * @param index a posição do elemento a ser atualizado
	 * @param data o novo elemento
	 * @throws IndexOutOfBoundsException se o índice for inválido
	 * @throws UnderflowException se a lista estiver vazia
	 */
	@Override
	public void update(int index, T data) {
		if (isEmpty()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (index < 0 || index >= amount) {
			throw new IndexOutOfBoundsException("Índice inválido: " + index);
		}
		getNodeAt(index).setData(data);
	}

	/**
	 * Remove um elemento por índice.
	 *
	 * @param index o índice do elemento a ser removido
	 * @return o elemento removido
	 * @throws IndexOutOfBoundsException se o índice for inválido
	 * @throws UnderflowException se a lista estiver vazia
	 */
	@Override
	public T delete(int index) {
		if (isEmpty()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (index < 0 || index >= amount) {
			throw new IndexOutOfBoundsException("Índice inválido: " + index);
		}

		DoubleNode<T> nodeToRemove = getNodeAt(index);
		T data = nodeToRemove.getData();

		if (amount == 1) {
			// Único elemento
			headPointer = null;
			tailPointer = null;
		} else if (index == 0) {
			// Remover do início
			headPointer = nodeToRemove.getNext();
			headPointer.setPrevious(null);
		} else if (index == amount - 1) {
			// Remover do fim
			tailPointer = nodeToRemove.getPrevious();
			tailPointer.setNext(null);
		} else {
			// Remover do meio
			DoubleNode<T> previous = nodeToRemove.getPrevious();
			DoubleNode<T> next = nodeToRemove.getNext();
			previous.setNext(next);
			next.setPrevious(previous);
		}

		amount--;
		return data;
	}

	/**
	 * Limpa toda a lista.
	 */
	@Override
	public void clear() {
		headPointer = null;
		tailPointer = null;
		amount = 0;
	}

	/**
	 * Retorna o tamanho da lista.
	 *
	 * @return o número de elementos na lista
	 */
	@Override
	public int size() {
		return amount;
	}

	/**
	 * Verifica se a lista está vazia.
	 *
	 * @return true se a lista estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return (amount == 0);
	}

	/**
	 * Verifica se a lista está cheia.
	 *
	 * @return true se a lista estiver cheia, false caso contrário
	 */
	@Override
	public boolean isFull() {
		return (amount == length);
	}

	/**
	 * Retorna uma representação em string da lista.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a lista
	 */
	@Override
	public String print() {
		String result = "";
		DoubleNode<T> auxNode = headPointer;
		for (int i = 0; i < amount; i++) {
			result += auxNode.getData();
			if (i != amount - 1) {
				result += ",";
			}
			auxNode = auxNode.getNext();
		}
		return "[" + result + "]";
	}

	/**
	 * Método auxiliar para obter um nó em uma posição específica.
	 *
	 * @param index a posição do nó
	 * @return o nó na posição especificada
	 */
	private DoubleNode<T> getNodeAt(int index) {
		DoubleNode<T> current = headPointer;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}
}