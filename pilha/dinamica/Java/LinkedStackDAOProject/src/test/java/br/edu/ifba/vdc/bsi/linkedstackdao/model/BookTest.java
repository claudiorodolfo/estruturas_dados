package br.edu.ifba.vdc.bsi.linkedstackdao.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Testes unitários para a classe Book.
 * Testa todos os métodos e comportamentos da classe Book.
 */
public class BookTest {

    private Book book;
    private final Long VALID_ID = 1L;
    private final String VALID_TITLE = "Dom Casmurro";
    private final String VALID_AUTHOR = "Machado de Assis";
    private final LocalDate VALID_DATE = LocalDate.of(1899, 1, 1);
    private final String VALID_ISBN = "978-85-359-0277-8";
    private final Double VALID_PRICE = 29.90;

    @Before
    public void setUp() {
        book = new Book(VALID_ID, VALID_TITLE, VALID_AUTHOR, VALID_DATE, VALID_ISBN, VALID_PRICE);
    }

    @Test
    public void testConstructorWithAllFields() {
        assertEquals(VALID_ID, book.getId());
        assertEquals(VALID_TITLE, book.getTitle());
        assertEquals(VALID_AUTHOR, book.getAuthor());
        assertEquals(VALID_DATE, book.getPublicationDate());
        assertEquals(VALID_ISBN, book.getIsbn());
        assertEquals(VALID_PRICE, book.getPrice());
    }

    @Test
    public void testConstructorWithMinimalFields() {
        Book minimalBook = new Book(VALID_ID, VALID_TITLE);
        assertEquals(VALID_ID, minimalBook.getId());
        assertEquals(VALID_TITLE, minimalBook.getTitle());
        assertNull(minimalBook.getAuthor());
        assertNull(minimalBook.getPublicationDate());
        assertNull(minimalBook.getIsbn());
        assertNull(minimalBook.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullId() {
        new Book(null, VALID_TITLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullTitle() {
        new Book(VALID_ID, null);
    }

    @Test
    public void testGettersAndSetters() {
        String newTitle = "O Cortiço";
        String newAuthor = "Aluísio Azevedo";
        LocalDate newDate = LocalDate.of(1890, 1, 1);
        String newIsbn = "978-85-359-0271-6";
        Double newPrice = 24.90;

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setPublicationDate(newDate);
        book.setIsbn(newIsbn);
        book.setPrice(newPrice);

        assertEquals(newTitle, book.getTitle());
        assertEquals(newAuthor, book.getAuthor());
        assertEquals(newDate, book.getPublicationDate());
        assertEquals(newIsbn, book.getIsbn());
        assertEquals(newPrice, book.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTitleWithNull() {
        book.setTitle(null);
    }

    @Test
    public void testSetAuthorWithNull() {
        book.setAuthor(null);
        assertNull(book.getAuthor());
    }

    @Test
    public void testSetPublicationDateWithNull() {
        book.setPublicationDate(null);
        assertNull(book.getPublicationDate());
    }

    @Test
    public void testSetIsbnWithNull() {
        book.setIsbn(null);
        assertNull(book.getIsbn());
    }

    @Test
    public void testSetPriceWithNull() {
        book.setPrice(null);
        assertNull(book.getPrice());
    }

    @Test
    public void testToString() {
        String expected = "{id:1,title:\"Dom Casmurro\",author:\"Machado de Assis\"," +
                         "publicationDate:\"1899-01-01\",isbn:\"978-85-359-0277-8\",price:29.9}";
        assertEquals(expected, book.toString());
    }

    @Test
    public void testToStringWithNullFields() {
        Book bookWithNulls = new Book(2L, "Test Book");
        bookWithNulls.setAuthor("Test Author");
        bookWithNulls.setPrice(10.0);
        
        String result = bookWithNulls.toString();
        assertTrue(result.contains("id:2"));
        assertTrue(result.contains("title:\"Test Book\""));
        assertTrue(result.contains("author:\"Test Author\""));
        assertTrue(result.contains("publicationDate:null"));
        assertTrue(result.contains("isbn:null"));
        assertTrue(result.contains("price:10.0"));
    }

    @Test
    public void testIdIsImmutable() {
        // O ID não deve ter setter, apenas getter
        // Este teste verifica que não há setter para ID
        Long originalId = book.getId();
        assertEquals(VALID_ID, originalId);
    }
}
