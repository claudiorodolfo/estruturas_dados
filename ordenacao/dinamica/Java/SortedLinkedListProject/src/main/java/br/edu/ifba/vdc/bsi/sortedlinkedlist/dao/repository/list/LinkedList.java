package br.edu.ifba.vdc.bsi.sortedlinkedlist.list;

import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.OverflowException;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.UnderflowException;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.Sortable;

/**
 * Implementação de uma lista dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma lista que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na lista
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.2
 * @since 2025-10-30
 * @see Listable
 * @see DoubleNode
 * @see Sortable
 */
public class LinkedList<T> implements Listable<T> {
	/** Ponteiro para o início da lista */
	protected DoubleNode<T> headPointer;

	/** Ponteiro para o fim da lista */
	protected DoubleNode<T> tailPointer;

    /** Quantidade atual de elementos */
    protected int amount;

    /** Tamanho máximo da ED */
    protected int length;

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

		DoubleNode<T> tempNode = new DoubleNode<T>(data);

        DoubleNode<T> previous = null;
        DoubleNode<T> next = headPointer;

		// percorre a lista até a posição desejada
		//posicionando os ponteiros previous e next
		//antes e depois de onde ficará o novo nodo.
		for (int i = 0; i < index; i++) {
			previous = next;
			next = next.getNext();
		}

		if (previous != null) {
			previous.setNext(tempNode);
			// se o anterior é nulo é pq a insercao está sendo no inicio
		} else {
			headPointer = tempNode;
		}

		if (next != null) {
			next.setPrevious(tempNode);
			// se o proximo é nulo é pq a insercao está sendo no fim
		} else {
			tailPointer = tempNode;
		}

		tempNode.setPrevious(previous);
		tempNode.setNext(next);

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

		DoubleNode<T> tempNode = null;
		//nó a ser manipulado está mais perto do ínicio
        if (index <= amount/2) {
            tempNode = getNodeAt(index);
        } else {
            tempNode = getNodeAtByEnd(index);
        }

		return tempNode.getData();
	}

	/**
	 * Obtém todos os elementos da lista como um array.
	 *
	 * @return array com todos os elementos da lista
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] selectAll() {
		T[] array = (T[]) new Object[amount];
		DoubleNode<T> current = headPointer;
		for (int i = 0; i < amount; i++) {
			array[i] = current.getData();
			current = current.getNext();
		}
		return  array;
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

		DoubleNode<T> tempNode = null;
		//nó a ser manipulado está mais perto do ínicio
        if (index <= amount/2) {
            tempNode = getNodeAt(index);
        } else {
            tempNode = getNodeAtByEnd(index);
        }

		tempNode.setData(data);
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

		DoubleNode<T> tempNode = null;
		//nó a ser manipulado está mais perto do ínicio
        if (index <= amount/2) {
            tempNode = getNodeAt(index);
        } else {
            tempNode = getNodeAtByEnd(index);
        }

		DoubleNode<T> previous = tempNode.getPrevious();
		DoubleNode<T> next = tempNode.getNext();

		if (previous != null) {
			previous.setNext(next);
			// remoção do início, avança o ponteiro de inicio para o proximo nodo.
		} else {
			headPointer = headPointer.getNext();
		}
		if (next != null) {
			next.setPrevious(previous);
			// remocao do fim, retrocede o ponteiro de fim para o nodo anterior.
		} else {
			tailPointer = tailPointer.getPrevious();
		}

		amount--;
		return tempNode.getData();
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
	 * Método auxiliar para obter um nó em uma posição específica, a partir do início da ED.
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

	/**
	 * Método auxiliar para obter um nó em uma posição específica, a partir do fim da ED.
	 *
	 * @param index a posição do nó
	 * @return o nó na posição especificada
	 */
	private DoubleNode<T> getNodeAtByEnd(int index) {
		DoubleNode<T> current = tailPointer;
		for (int i = 0; i < (amount - 1) - index; i++) {
			current = current.getPrevious();
		}
		return current;
	}
}