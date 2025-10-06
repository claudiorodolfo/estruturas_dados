package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;

public interface BookDAO {
    // cria um novo livro
    void addBook(Book book);
    // retorna um livro
    Book getBook(Long id);
    // atualiza um livro
    void updateBook(Book newBook);
    // apaga um livro
    Book deleteBook(Long id);

    // ordena os livros
    Book[] sortBooks();
    // retorna todos os livros
    Book[] getAllBooks();
    //imprime os livros armazenados
    String printBooks();
}