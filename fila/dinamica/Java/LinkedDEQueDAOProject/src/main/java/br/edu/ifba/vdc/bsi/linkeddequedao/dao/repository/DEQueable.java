package br.edu.ifba.vdc.bsi.linkeddequedao.dao.repository;

/**
 * Interface que define as operações básicas de uma pilha.
 * Esta interface define os métodos que devem ser implementados
 * por qualquer classe que represente uma estrutura de dados do tipo pilha.
 *
 * @param <T> o tipo dos elementos armazenados na pilha
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.2
 * @since 2025-10-21
 */
public interface DEQueable<T> {
    void beginEnqueue(T data);  //C
    void enqueue(T data);       //C
    T front();                  //R
    T rear();                   //R
    void beginUpdate(T data);   //U
    void endUpdate(T data);     //U
    T dequeue();                //D
    T endDequeue();             //D


    boolean isEmpty();
    boolean isFull();
    String print();
    String printEndToBegin();
}