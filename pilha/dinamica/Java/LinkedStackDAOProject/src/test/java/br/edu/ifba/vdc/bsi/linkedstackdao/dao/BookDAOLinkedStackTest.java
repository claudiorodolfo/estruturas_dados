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
        
        Book retrievedBook = bookDAO.getBook(null);
        assertEquals(book1.getId(), retrievedBook.getId());
        assertEquals(book1.getTitle(), retrievedBook.getTitle());
    }

    @Test
    public void testAddMultipleBooks() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        // Last added book should be on top (LIFO)
        Book topBook = bookDAO.getBook(null);
        assertEquals(book3.getId(), topBook.getId());
        assertEquals(book3.getTitle(), topBook.getTitle());
    }

    @Test
    public void testGetBook() {
        bookDAO.addBook(book1);
        
        Book retrievedBook = bookDAO.getBook(null);
        assertNotNull(retrievedBook);
        assertEquals(book1.getId(), retrievedBook.getId());
        assertEquals(book1.getTitle(), retrievedBook.getTitle());
        assertEquals(book1.getAuthor(), retrievedBook.getAuthor());
    }

    @Test
    public void testGetBookDoesNotRemove() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // First get
        Book firstGet = bookDAO.getBook(null);
        assertEquals(book2.getId(), firstGet.getId());
        
        // Second get should return the same book (not removed)
        Book secondGet = bookDAO.getBook(null);
        assertEquals(book2.getId(), secondGet.getId());
        assertEquals(firstGet.getId(), secondGet.getId());
    }

    @Test
    public void testUpdateBook() {
        bookDAO.addBook(book1);
        
        Book updatedBook = new Book(1L, "Dom Casmurro Atualizado", "Machado de Assis",
                                  LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 35.90);
        
        bookDAO.updateBook(updatedBook);
        
        Book retrievedBook = bookDAO.getBook(null);
        assertEquals("Dom Casmurro Atualizado", retrievedBook.getTitle());
        assertEquals(Double.valueOf(35.90), retrievedBook.getPrice());
    }

    @Test
    public void testDeleteBook() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // Delete the top book (book2)
        Book deletedBook = bookDAO.deleteBook(null);
        assertEquals(book2.getId(), deletedBook.getId());
        
        // Now the top should be book1
        Book remainingBook = bookDAO.getBook(null);
        assertEquals(book1.getId(), remainingBook.getId());
    }

    @Test
    public void testDeleteMultipleBooks() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        // Delete in LIFO order
        Book deleted1 = bookDAO.deleteBook(null);
        assertEquals(book3.getId(), deleted1.getId());
        
        Book deleted2 = bookDAO.deleteBook(null);
        assertEquals(book2.getId(), deleted2.getId());
        
        Book deleted3 = bookDAO.deleteBook(null);
        assertEquals(book1.getId(), deleted3.getId());
    }

    @Test
    public void testSortBooks() {
        // This method is not implemented, should return null
        Book[] sortedBooks = bookDAO.sortBooks();
        assertNull(sortedBooks);
    }

    @Test
    public void testGetAllBooks() {
        // This method is not implemented, should return null
        Book[] allBooks = bookDAO.getAllBooks();
        assertNull(allBooks);
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
        // Test LIFO behavior
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        
        // Should get the last added book
        Book topBook = bookDAO.getBook(null);
        assertEquals(book3.getId(), topBook.getId());
        
        // Remove top book
        Book removedBook = bookDAO.deleteBook(null);
        assertEquals(book3.getId(), removedBook.getId());
        
        // Now top should be book2
        Book newTopBook = bookDAO.getBook(null);
        assertEquals(book2.getId(), newTopBook.getId());
    }

    @Test
    public void testUpdateTopBook() {
        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        
        // Update the top book (book2)
        Book updatedBook = new Book(2L, "O Cortiço Atualizado", "Aluísio Azevedo",
                                  LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 30.00);
        
        bookDAO.updateBook(updatedBook);
        
        Book retrievedBook = bookDAO.getBook(null);
        assertEquals("O Cortiço Atualizado", retrievedBook.getTitle());
        assertEquals(Double.valueOf(30.00), retrievedBook.getPrice());
        
        // book1 should still be underneath
        bookDAO.deleteBook(null);
        Book remainingBook = bookDAO.getBook(null);
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
        
        // Check final state
        Book topBook = bookDAO.getBook(null);
        assertEquals(book3.getId(), topBook.getId());
        
        // Remove top
        Book removedBook = bookDAO.deleteBook(null);
        assertEquals(book3.getId(), removedBook.getId());
        
        // Check next book (should be the updated book2)
        Book nextBook = bookDAO.getBook(null);
        assertEquals("O Cortiço Modificado", nextBook.getTitle());
    }
}
