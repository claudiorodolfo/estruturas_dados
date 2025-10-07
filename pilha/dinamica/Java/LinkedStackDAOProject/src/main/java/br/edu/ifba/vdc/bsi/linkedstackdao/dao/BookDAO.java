package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;
import java.time.LocalDate;

public interface BookDAO {
    // Operações básicas CRUD
    void addBook(Book book);
    Book getBook(Long id);
    void updateBook(Book newBook);
    Book deleteBook(Long id);
    
    // Operações de consulta específicas para livros
    Book[] getBooksByAuthor(String author);
    Book getBookByIsbn(String isbn);
    
    // Operações de análise e estatísticas
    Book[] getMostExpensiveBooks(int limit);
    Book[] getCheapestBooks(int limit);
    
    // Operações de ordenação específicas
    Book[] sortBooksByTitle();
    Book[] sortBooksByAuthor();
    
    // Operações de relatório
    Book[] getAllBooks();
    String printBooks();
    int getTotalBooks();

}