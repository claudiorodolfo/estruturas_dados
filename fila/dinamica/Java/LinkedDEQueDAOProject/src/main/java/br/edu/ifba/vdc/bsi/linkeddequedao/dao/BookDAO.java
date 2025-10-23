package br.edu.ifba.vdc.bsi.linkeddequedao.dao;

import br.edu.ifba.vdc.bsi.linkeddequedao.model.Book;
import java.time.LocalDate;

public interface BookDAO {
    // Operações básicas CRUD
    void addBook(Book book);
    Book[] getAllBooks();
    void updateBook(Book newBook);
    Book deleteBook(Long id);
    
    // Operações de consulta específicas para livros
    Book getBookById(Long id);
    Book[] getBooksByAuthor(String author);
    Book[] getBooksByPublicationDate(LocalDate date);
    Book[] getBooksByTitle(String title);
    Book getBookByIsbn(String isbn);
    Book[] getBooksByPriceRange(double minPrice, double maxPrice);
    Book[] getBooksByDateRange(LocalDate minDate, LocalDate maxDate);

    // Operações de análise e estatísticas
    Book getMostExpensiveBook();
    Book getCheapestBook();
    Book getNewestBook(); 
    Book getOldestBook();

    // Operações de relatório e estatísticas
    String printBooks();
    int getTotalBooks();
    double getAveragePrice();
    
    // Operações de gerenciamento
    boolean isBookAvailable(Long id);
    
    // Operações de backup e restore
    void clearAllBooks();
}