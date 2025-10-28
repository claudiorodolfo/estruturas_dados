package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;
import java.time.LocalDate;

public interface BookDAO {
    // Operações básicas CRUD
    void addBook(Book book);
    Book getBookById(long id);
    Book[] getAllBooks();
    void updateBook(Book newBook);
    Book deleteBook(long id);
    
    // Operações de consulta específicas para livros
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
    boolean isBookAvailable(long id);
    
    // Operações de backup e restore
    void clearAllBooks();
}