package br.edu.ifba.vdc.bsi.arraystackdao.dao;

import br.edu.ifba.vdc.bsi.arraystackdao.model.Book;

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