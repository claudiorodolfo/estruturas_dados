package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list;

/**
 * Interface que define as operações básicas de uma lista dinâmica.
 * Esta interface define os métodos que devem ser implementados
 * por qualquer classe que represente uma estrutura de dados do tipo lista dinâmica.
 *
 * @param <T> o tipo dos elementos armazenados na lista.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-10-22
 */
public interface Listable<T> {
    void append(T data);            //C - Adiciona elemento no final da lista
    void insert(int index, T data); //C - Adiciona elemento em posição específica
    T select(int index);            //R - Obtém elemento por índice
    T[] selectAll();                //R - Obtém todos os elementos da lista
    void update(int index, T data); //U - Atualiza elemento em posição específica
    T delete(int index);            //D - Remove elemento por índice
    void clear();                   //D - Limpa toda a lista

    int size();                 //Retorna tamanho da lista
    boolean isEmpty();          //Verifica se está vazia
    boolean isFull();           //Verifica se está cheia
    String print();             //Imprime lista do início ao fim
}