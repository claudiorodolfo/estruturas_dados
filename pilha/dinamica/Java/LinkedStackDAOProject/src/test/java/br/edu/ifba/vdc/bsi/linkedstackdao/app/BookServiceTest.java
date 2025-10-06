package br.edu.ifba.vdc.bsi.linkedstackdao.app;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Testes unitários para a classe BookService.
 * Testa os métodos estáticos da classe BookService.
 */
public class BookServiceTest {

    @Test
    public void testGetDBBook() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        assertNotNull(dbAccessor);
        assertTrue(dbAccessor instanceof br.edu.ifba.vdc.bsi.linkedstackdao.dao.BookDAOLinkedStack);
    }

    @Test
    public void testGetDBBookReturnsNewInstance() {
        BookDAO dbAccessor1 = BookService.getDBBook();
        BookDAO dbAccessor2 = BookService.getDBBook();
        
        // Should return different instances
        assertNotSame(dbAccessor1, dbAccessor2);
    }

    @Test
    public void testGetDBBookCanBeUsed() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        // Test that the returned DAO can be used
        Book testBook = new Book(1L, "Test Book", "Test Author",
                               LocalDate.of(2023, 1, 1), "978-1234567890", 19.99);
        
        dbAccessor.addBook(testBook);
        Book retrievedBook = dbAccessor.getBook(null);
        
        assertNotNull(retrievedBook);
        assertEquals(testBook.getId(), retrievedBook.getId());
        assertEquals(testBook.getTitle(), retrievedBook.getTitle());
    }

    @Test
    public void testGetDBBookMultipleOperations() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        Book book1 = new Book(1L, "Book 1", "Author 1",
                            LocalDate.of(2023, 1, 1), "978-1111111111", 10.00);
        Book book2 = new Book(2L, "Book 2", "Author 2",
                            LocalDate.of(2023, 2, 1), "978-2222222222", 20.00);
        
        // Add books
        dbAccessor.addBook(book1);
        dbAccessor.addBook(book2);
        
        // Test retrieval
        Book topBook = dbAccessor.getBook(null);
        assertEquals(book2.getId(), topBook.getId());
        
        // Test update
        Book updatedBook = new Book(2L, "Book 2 Updated", "Author 2",
                                 LocalDate.of(2023, 2, 1), "978-2222222222", 25.00);
        dbAccessor.updateBook(updatedBook);
        
        Book retrievedUpdatedBook = dbAccessor.getBook(null);
        assertEquals("Book 2 Updated", retrievedUpdatedBook.getTitle());
        assertEquals(Double.valueOf(25.00), retrievedUpdatedBook.getPrice());
        
        // Test deletion
        Book deletedBook = dbAccessor.deleteBook(null);
        assertEquals(book2.getId(), deletedBook.getId());
        
        // Check remaining book
        Book remainingBook = dbAccessor.getBook(null);
        assertEquals(book1.getId(), remainingBook.getId());
    }

    @Test
    public void testGetDBBookStackBehavior() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        Book book1 = new Book(1L, "First Book");
        Book book2 = new Book(2L, "Second Book");
        Book book3 = new Book(3L, "Third Book");
        
        // Add books in order
        dbAccessor.addBook(book1);
        dbAccessor.addBook(book2);
        dbAccessor.addBook(book3);
        
        // Should get last added book (LIFO)
        Book topBook = dbAccessor.getBook(null);
        assertEquals(book3.getId(), topBook.getId());
        
        // Remove in reverse order
        Book removed1 = dbAccessor.deleteBook(null);
        assertEquals(book3.getId(), removed1.getId());
        
        Book removed2 = dbAccessor.deleteBook(null);
        assertEquals(book2.getId(), removed2.getId());
        
        Book removed3 = dbAccessor.deleteBook(null);
        assertEquals(book1.getId(), removed3.getId());
    }

    @Test
    public void testGetDBBookPrintBooks() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        // Test empty stack
        String emptyResult = dbAccessor.printBooks();
        assertEquals("[]", emptyResult);
        
        // Test with books
        Book book1 = new Book(1L, "Book 1", "Author 1",
                            LocalDate.of(2023, 1, 1), "978-1111111111", 10.00);
        Book book2 = new Book(2L, "Book 2", "Author 2",
                            LocalDate.of(2023, 2, 1), "978-2222222222", 20.00);
        
        dbAccessor.addBook(book1);
        dbAccessor.addBook(book2);
        
        String result = dbAccessor.printBooks();
        assertTrue(result.contains("Book 2")); // Last added (top)
        assertTrue(result.contains("Book 1")); // First added (bottom)
        assertTrue(result.contains("Author 1"));
        assertTrue(result.contains("Author 2"));
    }

    @Test
    public void testGetDBBookUnimplementedMethods() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        // These methods are not implemented and should return null
        assertNull(dbAccessor.sortBooks());
        assertNull(dbAccessor.getAllBooks());
    }

    @Test
    public void testGetDBBookWithNullParameters() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        Book testBook = new Book(1L, "Test Book");
        dbAccessor.addBook(testBook);
        
        // getBook and deleteBook accept null parameters
        Book retrievedBook = dbAccessor.getBook(null);
        assertNotNull(retrievedBook);
        assertEquals(testBook.getId(), retrievedBook.getId());
        
        Book deletedBook = dbAccessor.deleteBook(null);
        assertNotNull(deletedBook);
        assertEquals(testBook.getId(), deletedBook.getId());
    }
}
