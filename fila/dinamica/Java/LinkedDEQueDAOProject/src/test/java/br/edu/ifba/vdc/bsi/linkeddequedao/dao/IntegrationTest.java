package br.edu.ifba.vdc.bsi.linkeddequedao;

import br.edu.ifba.vdc.bsi.linkeddequedao.app.BookService;
import br.edu.ifba.vdc.bsi.linkeddequedao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkeddequedao.model.Book;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Teste de integração para verificar se todo o sistema funciona corretamente.
 * Este teste simula o fluxo completo do sistema.
 */
public class IntegrationTest {

    @Test
    public void testCompleteSystemFlow() {
        // 1. Obter o DAO do BookService
        BookDAO dbAccessor = BookService.getDBBook();
        assertNotNull(dbAccessor);
        
        // 2. Criar livros
        Book book1 = new Book(1L, "Dom Casmurro", "Machado de Assis",
                            LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90);
        
        Book book2 = new Book(2L, "O Cortiço", "Aluísio Azevedo",
                            LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 24.90);
        
        Book book3 = new Book(3L, "Capitães da Areia", "Jorge Amado",
                            LocalDate.of(1937, 1, 1), "978-85-359-0272-3", 32.50);
        
        // 3. Adicionar livros à pilha
        dbAccessor.addBook(book1);
        dbAccessor.addBook(book2);
        dbAccessor.addBook(book3);
        
        // 4. Verificar se o último livro adicionado está no topo
        Book topBook = dbAccessor.getBook(null);
        assertNotNull(topBook);
        assertEquals(book3.getId(), topBook.getId());
        assertEquals("Capitães da Areia", topBook.getTitle());
        
        // 5. Atualizar o livro do topo
        Book updatedBook = new Book(3L, "Capitães da Areia - Edição Especial", "Jorge Amado",
                                  LocalDate.of(1937, 1, 1), "978-85-359-0272-3", 45.00);
        dbAccessor.updateBook(updatedBook);
        
        Book retrievedUpdatedBook = dbAccessor.getBook(null);
        assertEquals("Capitães da Areia - Edição Especial", retrievedUpdatedBook.getTitle());
        assertEquals(Double.valueOf(45.00), retrievedUpdatedBook.getPrice());
        
        // 6. Remover o livro do topo
        Book removedBook = dbAccessor.deleteBook(null);
        assertEquals(book3.getId(), removedBook.getId());
        
        // 7. Verificar se o próximo livro está no topo
        Book newTopBook = dbAccessor.getBook(null);
        assertEquals(book2.getId(), newTopBook.getId());
        assertEquals("O Cortiço", newTopBook.getTitle());
        
        // 8. Testar a impressão dos livros
        String printResult = dbAccessor.printBooks();
        assertTrue(printResult.contains("O Cortiço"));
        assertTrue(printResult.contains("Dom Casmurro"));
        assertFalse(printResult.contains("Capitães da Areia")); // Já foi removido
        
        // 9. Remover todos os livros restantes
        Book removedBook2 = dbAccessor.deleteBook(null);
        assertEquals(book2.getId(), removedBook2.getId());
        
        Book removedBook1 = dbAccessor.deleteBook(null);
        assertEquals(book1.getId(), removedBook1.getId());
        
        // 10. Verificar se a pilha está vazia
        String emptyResult = dbAccessor.printBooks();
        assertEquals("[]", emptyResult);
    }

    @Test
    public void testStackLIFOBehavior() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        // Adicionar livros em ordem
        Book book1 = new Book(1L, "Primeiro");
        Book book2 = new Book(2L, "Segundo");
        Book book3 = new Book(3L, "Terceiro");
        
        dbAccessor.addBook(book1);
        dbAccessor.addBook(book2);
        dbAccessor.addBook(book3);
        
        // Verificar ordem LIFO (Last In, First Out)
        Book top1 = dbAccessor.deleteBook(null);
        assertEquals("Terceiro", top1.getTitle());
        
        Book top2 = dbAccessor.deleteBook(null);
        assertEquals("Segundo", top2.getTitle());
        
        Book top3 = dbAccessor.deleteBook(null);
        assertEquals("Primeiro", top3.getTitle());
    }

    @Test
    public void testSystemWithRealData() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        // Simular dados reais de livros
        Book[] books = {
            new Book(1L, "1984", "George Orwell", LocalDate.of(1949, 6, 8), "978-0-452-28423-4", 19.99),
            new Book(2L, "O Senhor dos Anéis", "J.R.R. Tolkien", LocalDate.of(1954, 7, 29), "978-0-547-92822-7", 25.99),
            new Book(3L, "Harry Potter", "J.K. Rowling", LocalDate.of(1997, 6, 26), "978-0-439-35529-6", 22.99)
        };
        
        // Adicionar todos os livros
        for (Book book : books) {
            dbAccessor.addBook(book);
        }
        
        // Verificar se o último adicionado está no topo
        Book topBook = dbAccessor.getBook(null);
        assertEquals("Harry Potter", topBook.getTitle());
        
        // Testar operações de atualização
        Book updatedBook = new Book(3L, "Harry Potter e a Pedra Filosofal", "J.K. Rowling",
                                  LocalDate.of(1997, 6, 26), "978-0-439-35529-6", 24.99);
        dbAccessor.updateBook(updatedBook);
        
        Book retrievedBook = dbAccessor.getBook(null);
        assertEquals("Harry Potter e a Pedra Filosofal", retrievedBook.getTitle());
        assertEquals(Double.valueOf(24.99), retrievedBook.getPrice());
        
        // Verificar impressão
        String result = dbAccessor.printBooks();
        assertTrue(result.contains("Harry Potter"));
        assertTrue(result.contains("O Senhor dos Anéis"));
        assertTrue(result.contains("1984"));
    }
}
