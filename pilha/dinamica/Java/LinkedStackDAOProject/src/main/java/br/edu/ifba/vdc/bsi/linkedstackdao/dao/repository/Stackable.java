package br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository;

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
public interface Stackable<T> {
    void push(T data);      //C
    T peek();               //R
    void update(T newData); //U
    T pop();                //D

    boolean isEmpty();
    boolean isFull();
    String toString();
}