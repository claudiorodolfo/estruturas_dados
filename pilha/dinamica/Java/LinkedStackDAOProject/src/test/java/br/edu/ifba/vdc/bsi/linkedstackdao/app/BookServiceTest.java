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
        Book retrievedBook = dbAccessor.getBook(testBook.getId());
        
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
        
        // Test retrieval by ID
        Book retrieved1 = dbAccessor.getBook(book1.getId());
        Book retrieved2 = dbAccessor.getBook(book2.getId());
        
        assertNotNull(retrieved1);
        assertNotNull(retrieved2);
        assertEquals(book1.getId(), retrieved1.getId());
        assertEquals(book2.getId(), retrieved2.getId());
        
        // Test update
        Book updatedBook = new Book(2L, "Book 2 Updated", "Author 2",
                                 LocalDate.of(2023, 2, 1), "978-2222222222", 25.00);
        dbAccessor.updateBook(updatedBook);
        
        Book retrievedUpdatedBook = dbAccessor.getBook(book2.getId());
        assertNotNull(retrievedUpdatedBook);
        assertEquals("Book 2 Updated", retrievedUpdatedBook.getTitle());
        assertEquals(Double.valueOf(25.00), retrievedUpdatedBook.getPrice());
        
        // Test deletion by ID
        Book deletedBook = dbAccessor.deleteBook(book2.getId());
        assertNotNull(deletedBook);
        assertEquals(book2.getId(), deletedBook.getId());
        
        // Check remaining book
        Book remainingBook = dbAccessor.getBook(book1.getId());
        assertNotNull(remainingBook);
        assertEquals(book1.getId(), remainingBook.getId());
        
        // Verify book2 is deleted
        assertNull(dbAccessor.getBook(book2.getId()));
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
        
        // All books should be retrievable by ID
        Book retrieved1 = dbAccessor.getBook(book1.getId());
        Book retrieved2 = dbAccessor.getBook(book2.getId());
        Book retrieved3 = dbAccessor.getBook(book3.getId());
        
        assertNotNull(retrieved1);
        assertNotNull(retrieved2);
        assertNotNull(retrieved3);
        
        assertEquals(book1.getId(), retrieved1.getId());
        assertEquals(book2.getId(), retrieved2.getId());
        assertEquals(book3.getId(), retrieved3.getId());
        
        // Remove by ID
        Book removed1 = dbAccessor.deleteBook(book3.getId());
        assertNotNull(removed1);
        assertEquals(book3.getId(), removed1.getId());
        
        Book removed2 = dbAccessor.deleteBook(book2.getId());
        assertNotNull(removed2);
        assertEquals(book2.getId(), removed2.getId());
        
        Book removed3 = dbAccessor.deleteBook(book1.getId());
        assertNotNull(removed3);
        assertEquals(book1.getId(), removed3.getId());
        
        // Verify all books are deleted
        assertNull(dbAccessor.getBook(book1.getId()));
        assertNull(dbAccessor.getBook(book2.getId()));
        assertNull(dbAccessor.getBook(book3.getId()));
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
    public void testGetDBBookImplementedMethods() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        // These methods are implemented and should work
        Book[] allBooks = dbAccessor.getAllBooks();
        assertNotNull(allBooks);
        assertEquals(0, allBooks.length); // Empty initially
        
        Book[] sortedByTitle = dbAccessor.sortBooksByTitle();
        assertNotNull(sortedByTitle);
        assertEquals(0, sortedByTitle.length); // Empty initially
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
