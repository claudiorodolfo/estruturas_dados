package br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository;

import java.util.NoSuchElementException;

/**
 * Implementação de uma pilha dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma pilha que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na pilha
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-06-04
 */
public class LinkedStack<T> implements Stackable<T> {
	/** Ponteiro para o topo da pilha */
	private DoubleNode<T> topPointer;

    /** Quantidade atual de elementos */
    private int amount;

    /** Tamanho máximo da pilha */
    private int length;

	/**
	 * Construtor padrão que cria uma pilha com capacidade para 10 elementos.
	 */
    public LinkedStack() {
        this(10);
    }

	/**
	 * Construtor que cria uma pilha com capacidade personalizada.
	 *
	 * @param length a capacidade máxima da pilha
	 */
    public LinkedStack(int length) {
		topPointer = null;
		amount = 0;
		this.length = length;
    }
  
	/**
	 * Adiciona um elemento ao topo da pilha.
	 *
	 * @param data o elemento a ser adicionado
	 * @throws NoSuchElementException se a pilha estiver cheia
	 */
    @Override
    public void push(T data) {
		if (isFull()) {
			throw new NoSuchElementException("Pilha Cheia!");
		}
		DoubleNode<T> tempNode = new DoubleNode<T>();
		tempNode.setData(data);
		tempNode.setPrevious(topPointer);
		if (!isEmpty()) {
			topPointer.setNext(tempNode);
		}
		topPointer = tempNode;
		amount++;	
    }

    /**
	 * Remove e retorna o elemento do topo da pilha.
	 *
	 * @return o elemento removido do topo
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
    @Override
    public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		T topData = topPointer.getData();
		topPointer = topPointer.getPrevious();
		amount--;
		if (!isEmpty()) {
			topPointer.setNext(null);
		}
		return topData;
    }

	/**
	 * Retorna o elemento do topo da pilha sem removê-lo.
	 *
	 * @return o elemento do topo
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
    @Override
    public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		return topPointer.getData();
    }

	/**
	 * Atualiza o topo da pilha.
	 *
	 * @return array com todos os elementos da pilha
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
	@Override
	public void update(T newData) {
		if (isEmpty()) {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		topPointer.setData(newData);
	}

	/**
	 * Verifica se a pilha está cheia.
	 *
	 * @return true se a pilha estiver cheia, false caso contrário
	 */
	@Override
	public boolean isFull() {
		return (amount == length);
	}

	/**
	 * Verifica se a pilha está vazia.
	 *
	 * @return true se a pilha estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return (amount == 0);
	}

	/**
	 * Retorna uma representação em string da pilha.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 * O elemento do topo será o último elemento na string.
	 *
	 * @return string representando a pilha
	 */
	@Override
	public String toString() {
		String result = "";
		DoubleNode<T> auxPointer = topPointer;
		for (int i = 0; i < amount; i++) {
			result += auxPointer.getData();
			if (i != amount - 1) {
				result += ",";
			}
			auxPointer = auxPointer.getPrevious();
		}
		return "[" + result + "]";
	}
}