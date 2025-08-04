package br.edu.ifba.vdc.bsi.stackdao;

public interface BookStackDAO {
    // empilha um livro no topo
    void push(Book book);
    // desempilha o livro que está no topo
    Book pop();
    // atualiza o livro no topo
    void update(Book book);
    // consulta o livro no topo, sem removê-lo
    Book peek();
    // verifica se a pilha está vazia
    boolean isEmpty();
    // verifica se a pilha está cheia
    boolean isFull();
    // retorna a quantidade de livros na pilha
    int size();
    // imprime a pilha
    String toString();
}