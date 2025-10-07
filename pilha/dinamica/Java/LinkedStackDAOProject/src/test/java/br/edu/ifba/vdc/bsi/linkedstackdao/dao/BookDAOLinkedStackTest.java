package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Testes unitários para a classe BookDAOLinkedStack.
 * Testa todos os métodos da interface BookDAO implementados pela classe.
 */
public class BookDAOLinkedStackTest {

    private BookDAOLinkedStack bookDAO;
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        bookDAO = new BookDAOLinkedStack();
        
        book1 = new Book(1L, "Dom Casmurro", "Machado de Assis",
                        LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90);
        
        book2 = new Book(2L, "O Cortiço", "Aluísio Azevedo",
                        LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 24.90);
        
        book3 = new Book(3L, "Capitães da Areia", "Jorge Amado",
                        LocalDate.of(1937, 1, 1), "978-85-359-0272-3", 32.50);
    }

    @Test
    public void testAddBook() {
        bookDAO.addBook(book1);
        
        Book retrievedBook = bookDAO.getBook(book1.getId());
        assertNotNull(retrievedBook);
        assertEquals(book1.getId(), retrievedBook.getId());
        assertEquals(book1.getTitle(), retrievedBook.getTitle());
    }

    @Test
    public void testAddMultipleBooks() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        // Test that all books can be retrieved by their IDs
        Book retrieved1 = bookDAO.getBook(book1.getId());
        Book retrieved2 = bookDAO.getBook(book2.getId());
        Book retrieved3 = bookDAO.getBook(book3.getId());
        
        assertNotNull(retrieved1);
        assertNotNull(retrieved2);
        assertNotNull(retrieved3);
        
        assertEquals(book1.getId(), retrieved1.getId());
        assertEquals(book2.getId(), retrieved2.getId());
        assertEquals(book3.getId(), retrieved3.getId());
    }

    @Test
    public void testGetBook() {
        bookDAO.addBook(book1);
        
        Book retrievedBook = bookDAO.getBook(book1.getId());
        assertNotNull(retrievedBook);
        assertEquals(book1.getId(), retrievedBook.getId());
        assertEquals(book1.getTitle(), retrievedBook.getTitle());
        assertEquals(book1.getAuthor(), retrievedBook.getAuthor());
    }

    @Test
    public void testGetBookDoesNotRemove() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // First get by ID
        Book firstGet = bookDAO.getBook(book1.getId());
        assertNotNull(firstGet);
        assertEquals(book1.getId(), firstGet.getId());
        
        // Second get should return the same book (not removed)
        Book secondGet = bookDAO.getBook(book1.getId());
        assertNotNull(secondGet);
        assertEquals(book1.getId(), secondGet.getId());
        assertEquals(firstGet.getId(), secondGet.getId());
    }

    @Test
    public void testUpdateBook() {
        bookDAO.addBook(book1);
        
        Book updatedBook = new Book(1L, "Dom Casmurro Atualizado", "Machado de Assis",
                                  LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 35.90);
        
        bookDAO.updateBook(updatedBook);
        
        Book retrievedBook = bookDAO.getBook(book1.getId());
        assertNotNull(retrievedBook);
        assertEquals("Dom Casmurro Atualizado", retrievedBook.getTitle());
        assertEquals(Double.valueOf(35.90), retrievedBook.getPrice());
    }

    @Test
    public void testDeleteBook() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // Delete book1 by ID
        Book deletedBook = bookDAO.deleteBook(book1.getId());
        assertNotNull(deletedBook);
        assertEquals(book1.getId(), deletedBook.getId());
        
        // Verify book1 is deleted
        Book remainingBook = bookDAO.getBook(book1.getId());
        assertNull(remainingBook);
        
        // Verify book2 still exists
        Book book2Retrieved = bookDAO.getBook(book2.getId());
        assertNotNull(book2Retrieved);
        assertEquals(book2.getId(), book2Retrieved.getId());
    }

    @Test
    public void testDeleteMultipleBooks() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        // Delete books by ID
        Book deleted1 = bookDAO.deleteBook(book1.getId());
        assertNotNull(deleted1);
        assertEquals(book1.getId(), deleted1.getId());
        
        Book deleted2 = bookDAO.deleteBook(book2.getId());
        assertNotNull(deleted2);
        assertEquals(book2.getId(), deleted2.getId());
        
        Book deleted3 = bookDAO.deleteBook(book3.getId());
        assertNotNull(deleted3);
        assertEquals(book3.getId(), deleted3.getId());
        
        // Verify all books are deleted
        assertNull(bookDAO.getBook(book1.getId()));
        assertNull(bookDAO.getBook(book2.getId()));
        assertNull(bookDAO.getBook(book3.getId()));
    }

    @Test
    public void testSortBooksByTitle() {
        // This method is implemented, should return empty array initially
        Book[] sortedBooks = bookDAO.sortBooksByTitle();
        assertNotNull(sortedBooks);
        assertEquals(0, sortedBooks.length);
    }

    @Test
    public void testGetAllBooks() {
        // This method is implemented, should return empty array initially
        Book[] allBooks = bookDAO.getAllBooks();
        assertNotNull(allBooks);
        assertEquals(0, allBooks.length);
    }

    @Test
    public void testPrintBooksEmpty() {
        String result = bookDAO.printBooks();
        assertEquals("[]", result);
    }

    @Test
    public void testPrintBooksSingle() {
        bookDAO.addBook(book1);
        String result = bookDAO.printBooks();
        assertTrue(result.contains("Dom Casmurro"));
        assertTrue(result.contains("Machado de Assis"));
    }

    @Test
    public void testPrintBooksMultiple() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        String result = bookDAO.printBooks();
        assertTrue(result.contains("Capitães da Areia")); // Last added (top)
        assertTrue(result.contains("O Cortiço"));
        assertTrue(result.contains("Dom Casmurro")); // First added (bottom)
    }

    @Test
    public void testStackBehavior() {
        // Test that all books can be added and retrieved by ID
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        // All books should be retrievable by their IDs
        Book retrieved1 = bookDAO.getBook(book1.getId());
        Book retrieved2 = bookDAO.getBook(book2.getId());
        Book retrieved3 = bookDAO.getBook(book3.getId());
        
        assertNotNull(retrieved1);
        assertNotNull(retrieved2);
        assertNotNull(retrieved3);
        
        assertEquals(book1.getId(), retrieved1.getId());
        assertEquals(book2.getId(), retrieved2.getId());
        assertEquals(book3.getId(), retrieved3.getId());
        
        // Remove book3 by ID
        Book removedBook = bookDAO.deleteBook(book3.getId());
        assertNotNull(removedBook);
        assertEquals(book3.getId(), removedBook.getId());
        
        // Verify book3 is deleted but others remain
        assertNull(bookDAO.getBook(book3.getId()));
        assertNotNull(bookDAO.getBook(book1.getId()));
        assertNotNull(bookDAO.getBook(book2.getId()));
    }

    @Test
    public void testUpdateTopBook() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // Update book2 by ID
        Book updatedBook = new Book(2L, "O Cortiço Atualizado", "Aluísio Azevedo",
                                  LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 30.00);
        
        bookDAO.updateBook(updatedBook);
        
        Book retrievedBook = bookDAO.getBook(book2.getId());
        assertNotNull(retrievedBook);
        assertEquals("O Cortiço Atualizado", retrievedBook.getTitle());
        assertEquals(Double.valueOf(30.00), retrievedBook.getPrice());
        
        // book1 should still exist
        Book remainingBook = bookDAO.getBook(book1.getId());
        assertNotNull(remainingBook);
        assertEquals(book1.getId(), remainingBook.getId());
    }

    @Test
    public void testComplexOperations() {
        // Add books
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // Update top
        Book updatedBook2 = new Book(2L, "O Cortiço Modificado", "Aluísio Azevedo",
                                   LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 25.00);
        bookDAO.updateBook(updatedBook2);
        
        // Add another book
        bookDAO.addBook(book3);
        
        // Check final state - all books should be retrievable by ID
        Book retrieved1 = bookDAO.getBook(book1.getId());
        Book retrieved2 = bookDAO.getBook(book2.getId());
        Book retrieved3 = bookDAO.getBook(book3.getId());
        
        assertNotNull(retrieved1);
        assertNotNull(retrieved2);
        assertNotNull(retrieved3);
        
        // Check that book2 was updated
        assertEquals("O Cortiço Modificado", retrieved2.getTitle());
        assertEquals(Double.valueOf(25.00), retrieved2.getPrice());
        
        // Remove book3 by ID
        Book removedBook = bookDAO.deleteBook(book3.getId());
        assertNotNull(removedBook);
        assertEquals(book3.getId(), removedBook.getId());
        
        // Verify book3 is deleted but others remain
        assertNull(bookDAO.getBook(book3.getId()));
        assertNotNull(bookDAO.getBook(book1.getId()));
        assertNotNull(bookDAO.getBook(book2.getId()));
    }
}
