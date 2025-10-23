package br.edu.ifba.vdc.bsi.linkedlistdao.dao;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.db.BookRepositorySQLite;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Book;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Implementação do DAO para persistência de livros em banco de dados SQLite.
 * Contém apenas regras de negócio, delegando acesso aos dados para o BookRepositorySQLite.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0.0
 * @since 2024-01-01
 */
public class BookDAOSQLite implements BookDAO {
    
    private static final Logger LOGGER = Logger.getLogger(BookDAOSQLite.class.getName());
    private final BookRepositorySQLite bookRepositorySQLite;
    
    /**
     * Construtor que inicializa o repositório de dados.
     */
    public BookDAOSQLite() {
        bookRepositorySQLite = new BookRepositorySQLite();
    }
    
    @Override
    public void addBook(Book book) {
        // Regra de negócio: validar se livro já existe
        if (bookRepositorySQLite.existsBook(book.getId())) {
            throw new IllegalArgumentException("Livro com ID " + book.getId() + " já existe!");
        }
        
        // Regra de negócio: validar campos obrigatórios
        validateBook(book);
        
        // Delegar para o repository
        bookRepositorySQLite.insertBook(book);
        
        LOGGER.info("Livro adicionado com sucesso: " + book.getTitle());
    }
    
    @Override
    public Book[] getAllBooks() {
        // Regra de negócio: retornar todos os livros ordenados
        Book[] books = bookRepositorySQLite.selectAllBooks();
        
        LOGGER.info("Total de livros recuperados: " + books.length);
        
        return books;
    }
    
    @Override
    public void updateBook(Book newBook) {
        // Regra de negócio: validar se livro existe
        if (!bookRepositorySQLite.existsBook(newBook.getId())) {
            throw new IllegalArgumentException("Livro com ID " + newBook.getId() + " não existe!");
        }
        
        // Regra de negócio: validar campos obrigatórios
        validateBook(newBook);
        
        // Delegar para o repository
        bookRepositorySQLite.updateBook(newBook);
        
        LOGGER.info("Livro atualizado com sucesso: " + newBook.getTitle());
    }
    
    @Override
    public Book deleteBook(long id) {
        // Regra de negócio: validar se livro existe
        if (!bookRepositorySQLite.existsBook(id)) {
            throw new IllegalArgumentException("Livro com ID " + id + " não existe!");
        }
        
        // Delegar para o repository
        Book deletedBook = bookRepositorySQLite.deleteBook(id);
        
        LOGGER.info("Livro excluído com sucesso: " + deletedBook.getTitle());
        
        return deletedBook;
    }
    
    @Override
    public Book getBookById(long id) {
        // Delegar para o repository
        return bookRepositorySQLite.selectBookById(id);
    }
    
    @Override
    public Book[] getBooksByAuthor(String author) {
        // Regra de negócio: validar parâmetro
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor não pode ser nulo ou vazio!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByAuthor(author);
    }
    
    @Override
    public Book[] getBooksByPublicationDate(LocalDate date) {
        // Regra de negócio: validar parâmetro
        if (date == null) {
            throw new IllegalArgumentException("Data não pode ser nula!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByPublicationDate(date);
    }
    
    @Override
    public Book[] getBooksByTitle(String title) {
        // Regra de negócio: validar parâmetro
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByTitle(title);
    }
    
    @Override
    public Book getBookByIsbn(String isbn) {
        // Regra de negócio: validar parâmetro
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser nulo ou vazio!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBookByIsbn(isbn);
    }
    
    @Override
    public Book[] getBooksByPriceRange(double minPrice, double maxPrice) {
        // Regra de negócio: validar parâmetros
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Preços não podem ser negativos!");
        }
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Preço mínimo não pode ser maior que o máximo!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByPriceRange(minPrice, maxPrice);
    }
    
    @Override
    public Book[] getBooksByDateRange(LocalDate minDate, LocalDate maxDate) {
        // Regra de negócio: validar parâmetros
        if (minDate == null || maxDate == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas!");
        }
        if (minDate.isAfter(maxDate)) {
            throw new IllegalArgumentException("Data mínima não pode ser posterior à máxima!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByDateRange(minDate, maxDate);
    }
    
    @Override
    public Book getMostExpensiveBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectMostExpensiveBook();
    }
    
    @Override
    public Book getCheapestBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectCheapestBook();
    }
    
    @Override
    public Book getNewestBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectNewestBook();
    }
    
    @Override
    public Book getOldestBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectOldestBook();
    }
    
    @Override
    public String printBooks() {
        // Regra de negócio: formatar lista de livros
        Book[] books = getAllBooks();
        StringBuilder sb = new StringBuilder();
        
        sb.append("=== LISTA DE LIVROS ===\n");
        sb.append("Total de livros: ").append(books.length).append("\n\n");
        
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        
        return sb.toString();
    }
    
    @Override
    public int getTotalBooks() {
        // Delegar para o repository
        return bookRepositorySQLite.countTotalBooks();
    }
    
    @Override
    public double getAveragePrice() {
        // Delegar para o repository
        return bookRepositorySQLite.calculateAveragePrice();
    }
    
    @Override
    public boolean isBookAvailable(long id) {
        // Delegar para o repository
        return bookRepositorySQLite.existsBook(id);
    }
    
    @Override
    public void clearAllBooks() {
        // Regra de negócio: confirmar operação destrutiva
        LOGGER.warning("Operação destrutiva: removendo todos os livros");
        
        // Delegar para o repository
        bookRepositorySQLite.clearAllBooks();
        
        LOGGER.info("Todos os livros foram removidos");
    }
    
    /**
     * Valida se um livro possui todos os campos obrigatórios.
     * 
     * @param book livro a ser validado
     * @throws IllegalArgumentException se algum campo obrigatório for inválido
     */
    private void validateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo!");
        }
        
        if (book.getId() == null) {
            throw new IllegalArgumentException("ID do livro é obrigatório!");
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do livro é obrigatório!");
        }
        
        // Validações adicionais de negócio
        if (book.getPrice() != null && book.getPrice() < 0) {
            throw new IllegalArgumentException("Preço do livro não pode ser negativo!");
        }
        
        if (book.getPublicationDate() != null && book.getPublicationDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de publicação não pode ser futura!");
        }
    }
}