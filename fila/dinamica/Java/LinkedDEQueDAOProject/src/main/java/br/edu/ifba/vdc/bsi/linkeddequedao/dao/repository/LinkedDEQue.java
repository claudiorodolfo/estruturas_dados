package br.edu.ifba.vdc.bsi.linkeddequedao.dao.repository;

import java.util.NoSuchElementException;

/**
 * Implementação de uma fila com dupla terminação dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma fila com dupla terminação que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na fila
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-10-20
 * @see DEQueable
 * @see DoubleNode
 */
public class LinkedDEQue<T> implements DEQueable<T> {
	/** Ponteiro para o início da fila */
	private DoubleNode<T> headPointer;

	/** Ponteiro para o fim da fila */
	private DoubleNode<T> tailPointer;

    /** Quantidade atual de elementos */
    private int amount;

    /** Tamanho máximo da ED */
    private int length;

	/**
	 * Construtor padrão que cria uma fila com capacidade para 10 elementos.
	 */
    public LinkedDEQue() {
        this(10);
    }

	/**
	 * Construtor que cria uma fila com capacidade personalizada.
	 *
	 * @param length a capacidade máxima da fila
	 */
    public LinkedDEQue(int length) {
		headPointer = null;
		tailPointer = null;
		amount = 0;
		this.length = length;
    }

	/**
	 * Adiciona um elemento ao final da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	@Override
	public T beginDequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		T data = headPointer.getData();
		headPointer = headPointer.getNext();
		amount--;
		if (!isEmpty()) {
			headPointer.setPrevious(null);
		} else {
			headPointer = null;
		}
		return data;	
	}

	/**
	 * Adiciona um elemento ao final da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	@Override
	public T endDequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		T data = tailPointer.getData();
		tailPointer = tailPointer.getPrevious();
		amount--;
		if (!isEmpty()) {
			tailPointer.setNext(null);
		} else {
			headPointer = null;
		}
		return data;	
	}

	/**
	 * Adiciona um elemento ao início da fila.
	 *
	 * @param data o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
    @Override
    public void beginEnqueue(T data) {
		if (isFull()) {
			throw new NoSuchElementException("Pilha Cheia!");
		}
		DoubleNode<T> tempNode = new DoubleNode<T>();
		tempNode.setData(data);

		if (!isEmpty()) {
			headPointer.setPrevious(tempNode);
		} else {
			tailPointer = tempNode;
		}

		tempNode.setNext(headPointer);
		headPointer = tempNode;
		amount++;	
    }

	/**
	 * Adiciona um elemento ao fim da fila.
	 *
	 * @param data o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
    @Override
    public void endEnqueue(T data) {
		if (isFull()) {
			throw new NoSuchElementException("Pilha Cheia!");
		}
		DoubleNode<T> tempNode = new DoubleNode<T>();
		tempNode.setData(data);

		if (!isEmpty()) {
			tailPointer.setNext(tempNode);
		} else {
			headPointer = tempNode;
		}

		tempNode.setPrevious(tailPointer);
		tailPointer = tempNode;
		amount++;	
    }

	/**
	 * Retorna o elemento do início da fila sem removê-lo.
	 *
	 * @return o elemento do início
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */	
	@Override
	public T front() {
		if(isEmpty()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		return headPointer.getData();
	}

	/**
	 * Retorna o elemento do fim da fila sem removê-lo.
	 *
	 * @return o elemento do fim
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */	
	@Override
	public T rear() {
		if(isEmpty()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		return tailPointer.getData();
	}

	/**
	 * Atualiza o elemento do início da fila.
	 *
	 * @param data o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public void beginUpdate(T data) {
		if(isEmpty()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		headPointer.setData(data);
	}

	/**
	 * Atualiza o elemento do fim da fila.
	 *
	 * @param data o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public void endUpdate(T data) {
		if(isEmpty()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		tailPointer.setData(data);
	}

	/**
	 * Verifica se a fila está cheia.
	 *
	 * @return true se a fila estiver cheia, false caso contrário
	 */
	@Override
	public boolean isFull() {
		return (amount == length);
	}

	/**
	 * Verifica se a fila está vazia.
	 *
	 * @return true se a fila estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return (amount == 0);
	}

	/**
	 * Retorna uma representação em string da fila do início para o final.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a fila do início para o final
	 */
	@Override
	public String printBeginToEnd() {
		String result = "";
		DoubleNode<T> auxNode = headPointer;
		for (int i = 0; i < amount; i++) {
			result += auxNode.getData();
			if (i != amount-1) {
				result +=  ",";
			}
			auxNode = auxNode.getNext();
		}
		return "[" + result + "]";
	}

	/**
	 * Retorna uma representação em string da fila do fim para o início.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a fila do fim para o início
	 */
	@Override
	public String printEndToBegin() {
		String result = "";
		DoubleNode<T> auxNode = tailPointer;
		for (int i = 0; i < amount; i++) {
			result += auxNode.getData();
			if (i != amount-1) {
				result +=  ",";
			}
			auxNode = auxNode.getPrevious();
		}
		return "[" + result + "]";
	}
}