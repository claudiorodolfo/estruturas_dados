package br.edu.ifba.vdc.bsi.linkeddequedao.dao;

import br.edu.ifba.vdc.bsi.linkeddequedao.model.Book;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Testes unitários para a classe BookDAOLinkedDEQue.
 * Testa as operações básicas de CRUD e consultas específicas.
 */
public class BookDAOLinkedDEQueTest {

    private BookDAOLinkedDEQue bookDAO;

    @Before
    public void setUp() {
        bookDAO = new BookDAOLinkedDEQue();
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1L, "Test Book");
        bookDAO.addBook(book);
        
        Book[] allBooks = bookDAO.getAllBooks();
        assertEquals(1, allBooks.length);
        assertEquals(book, allBooks[0]);
    }

    @Test
    public void testGetBookById() {
        Book book = new Book(1L, "Test Book");
        bookDAO.addBook(book);
        
        Book foundBook = bookDAO.getBookById(1L);
        assertNotNull(foundBook);
        assertEquals(book, foundBook);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book(1L, "Book 1");
        Book book2 = new Book(2L, "Book 2");
        
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        Book[] allBooks = bookDAO.getAllBooks();
        assertEquals(2, allBooks.length);
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book(1L, "Test Book");
        bookDAO.addBook(book);
        
        Book deletedBook = bookDAO.deleteBook(1L);
        assertNotNull(deletedBook);
        assertEquals(book, deletedBook);
        
        Book[] allBooks = bookDAO.getAllBooks();
        assertEquals(0, allBooks.length);
    }

    @Test
    public void testUpdateBook() {
        Book originalBook = new Book(1L, "Original Title");
        bookDAO.addBook(originalBook);
        
        Book updatedBook = new Book(1L, "Updated Title");
        bookDAO.updateBook(updatedBook);
        
        Book foundBook = bookDAO.getBookById(1L);
        assertNotNull(foundBook);
        assertEquals("Updated Title", foundBook.getTitle());
    }

    @Test
    public void testGetBooksByAuthor() {
        Book book1 = new Book(1L, "Book 1", "Author A", null, null, null);
        Book book2 = new Book(2L, "Book 2", "Author B", null, null, null);
        Book book3 = new Book(3L, "Book 3", "Author A", null, null, null);
        
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        Book[] booksByAuthor = bookDAO.getBooksByAuthor("Author A");
        assertEquals(2, booksByAuthor.length);
    }

    @Test
    public void testGetBooksByTitle() {
        Book book1 = new Book(1L, "Java Programming");
        Book book2 = new Book(2L, "Python Programming");
        Book book3 = new Book(3L, "Java Advanced");
        
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        Book[] javaBooks = bookDAO.getBooksByTitle("Java Programming");
        assertEquals(1, javaBooks.length);
        assertEquals(book1, javaBooks[0]);
    }

    @Test
    public void testGetTotalBooks() {
        assertEquals(0, bookDAO.getTotalBooks());
        
        Book book1 = new Book(1L, "Book 1");
        Book book2 = new Book(2L, "Book 2");
        
        bookDAO.addBook(book1);
        assertEquals(1, bookDAO.getTotalBooks());
        
        bookDAO.addBook(book2);
        assertEquals(2, bookDAO.getTotalBooks());
    }

    @Test
    public void testIsBookAvailable() {
        Book book = new Book(1L, "Test Book");
        bookDAO.addBook(book);
        
        assertTrue(bookDAO.isBookAvailable(1L));
        assertFalse(bookDAO.isBookAvailable(2L));
    }

    @Test
    public void testClearAllBooks() {
        Book book1 = new Book(1L, "Book 1");
        Book book2 = new Book(2L, "Book 2");
        
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        assertEquals(2, bookDAO.getTotalBooks());
        
        bookDAO.clearAllBooks();
        
        assertEquals(0, bookDAO.getTotalBooks());
    }
}
