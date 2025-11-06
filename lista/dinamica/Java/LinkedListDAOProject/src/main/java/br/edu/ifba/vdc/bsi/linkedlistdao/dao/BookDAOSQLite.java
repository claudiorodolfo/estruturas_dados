package br.edu.ifba.vdc.bsi.linkedlistdao.dao;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.db.BookRepositorySQLite;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Book;
import java.time.LocalDate;

/**
 * Implementação do DAO para persistência de livros em banco de dados SQLite.
 * Contém apenas regras de negócio, delegando acesso aos dados para o BookRepositorySQLite.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0.0
 * @since 2025-11-01
 * @see BookDAO
 * @see Book
 * @see BookRepositorySQLite
 */
public class BookDAOSQLite implements BookDAO {
    private final BookRepositorySQLite bookRepositorySQLite;
    
    /**
     * Construtor que inicializa o repositório de dados.
     */
    public BookDAOSQLite() {
        bookRepositorySQLite = new BookRepositorySQLite();
    }
    
    /**
     * Adiciona um novo livro ao banco de dados.
     * Valida se o livro já existe e se todos os campos obrigatórios estão preenchidos.
     * 
     * @param book livro a ser adicionado
     * @throws IllegalArgumentException se o livro já existir ou se algum campo obrigatório for inválido
     */
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
        
        System.out.println("Livro adicionado com sucesso: " + book.getTitle());
    }
    
    /**
     * Recupera todos os livros cadastrados no banco de dados.
     * 
     * @return array contendo todos os livros
     */
    @Override
    public Book[] getAllBooks() {
        // Regra de negócio: retornar todos os livros ordenados
        Book[] books = bookRepositorySQLite.selectAllBooks();
        
        System.out.println("Total de livros recuperados: " + books.length);
        
        return books;
    }
    
    /**
     * Atualiza as informações de um livro existente no banco de dados.
     * Valida se o livro existe e se todos os campos obrigatórios estão preenchidos.
     * 
     * @param newBook livro com as informações atualizadas
     * @throws IllegalArgumentException se o livro não existir ou se algum campo obrigatório for inválido
     */
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
        
        System.out.println("Livro atualizado com sucesso: " + newBook.getTitle());
    }
    
    /**
     * Remove um livro do banco de dados pelo seu ID.
     * 
     * @param id identificador único do livro a ser removido
     * @return livro removido do banco de dados
     * @throws IllegalArgumentException se o livro não existir
     */
    @Override
    public Book deleteBook(long id) {
        // Regra de negócio: validar se livro existe
        if (!bookRepositorySQLite.existsBook(id)) {
            throw new IllegalArgumentException("Livro com ID " + id + " não existe!");
        }
        
        // Delegar para o repository
        Book deletedBook = bookRepositorySQLite.deleteBook(id);
        
        System.out.println("Livro excluído com sucesso: " + deletedBook.getTitle());
        
        return deletedBook;
    }
    
    /**
     * Busca um livro pelo seu ID.
     * 
     * @param id identificador único do livro
     * @return livro encontrado ou null se não existir
     */
    @Override
    public Book getBookById(long id) {
        // Delegar para o repository
        return bookRepositorySQLite.selectBookById(id);
    }
    
    /**
     * Busca todos os livros de um determinado autor.
     * 
     * @param author nome do autor a ser pesquisado
     * @return array contendo os livros do autor especificado
     * @throws IllegalArgumentException se o autor for nulo ou vazio
     */
    @Override
    public Book[] getBooksByAuthor(String author) {
        // Regra de negócio: validar parâmetro
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor não pode ser nulo ou vazio!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByAuthor(author);
    }
    
    /**
     * Busca todos os livros publicados em uma data específica.
     * 
     * @param date data de publicação a ser pesquisada
     * @return array contendo os livros publicados na data especificada
     * @throws IllegalArgumentException se a data for nula
     */
    @Override
    public Book[] getBooksByPublicationDate(LocalDate date) {
        // Regra de negócio: validar parâmetro
        if (date == null) {
            throw new IllegalArgumentException("Data não pode ser nula!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByPublicationDate(date);
    }
    
    /**
     * Busca livros pelo título.
     * 
     * @param title título do livro a ser pesquisado
     * @return array contendo os livros com o título especificado
     * @throws IllegalArgumentException se o título for nulo ou vazio
     */
    @Override
    public Book[] getBooksByTitle(String title) {
        // Regra de negócio: validar parâmetro
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBooksByTitle(title);
    }
    
    /**
     * Busca um livro pelo seu ISBN.
     * 
     * @param isbn código ISBN do livro
     * @return livro encontrado ou null se não existir
     * @throws IllegalArgumentException se o ISBN for nulo ou vazio
     */
    @Override
    public Book getBookByIsbn(String isbn) {
        // Regra de negócio: validar parâmetro
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser nulo ou vazio!");
        }
        
        // Delegar para o repository
        return bookRepositorySQLite.selectBookByIsbn(isbn);
    }
    
    /**
     * Busca livros dentro de uma faixa de preços.
     * 
     * @param minPrice preço mínimo da faixa
     * @param maxPrice preço máximo da faixa
     * @return array contendo os livros dentro da faixa de preços especificada
     * @throws IllegalArgumentException se os preços forem negativos ou se o preço mínimo for maior que o máximo
     */
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
    
    /**
     * Busca livros publicados dentro de uma faixa de datas.
     * 
     * @param minDate data mínima da faixa
     * @param maxDate data máxima da faixa
     * @return array contendo os livros publicados dentro da faixa de datas especificada
     * @throws IllegalArgumentException se as datas forem nulas ou se a data mínima for posterior à máxima
     */
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
    
    /**
     * Busca o livro mais caro cadastrado.
     * 
     * @return livro com o maior preço ou null se não houver livros cadastrados
     */
    @Override
    public Book getMostExpensiveBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectMostExpensiveBook();
    }
    
    /**
     * Busca o livro mais barato cadastrado.
     * 
     * @return livro com o menor preço ou null se não houver livros cadastrados
     */
    @Override
    public Book getCheapestBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectCheapestBook();
    }
    
    /**
     * Busca o livro mais recente cadastrado (com a data de publicação mais recente).
     * 
     * @return livro mais recente ou null se não houver livros cadastrados
     */
    @Override
    public Book getNewestBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectNewestBook();
    }
    
    /**
     * Busca o livro mais antigo cadastrado (com a data de publicação mais antiga).
     * 
     * @return livro mais antigo ou null se não houver livros cadastrados
     */
    @Override
    public Book getOldestBook() {
        // Delegar para o repository
        return bookRepositorySQLite.selectOldestBook();
    }
    
    /**
     * Gera uma representação em string formatada de todos os livros cadastrados.
     * 
     * @return string formatada contendo a lista de todos os livros
     */
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
    
    /**
     * Retorna o número total de livros cadastrados.
     * 
     * @return quantidade total de livros no banco de dados
     */
    @Override
    public int getTotalBooks() {
        // Delegar para o repository
        return bookRepositorySQLite.countTotalBooks();
    }
    
    /**
     * Calcula o preço médio de todos os livros cadastrados.
     * 
     * @return preço médio dos livros ou 0.0 se não houver livros cadastrados
     */
    @Override
    public double getAveragePrice() {
        // Delegar para o repository
        return bookRepositorySQLite.calculateAveragePrice();
    }
    
    /**
     * Verifica se um livro existe no banco de dados.
     * 
     * @param id identificador único do livro
     * @return true se o livro existir, false caso contrário
     */
    @Override
    public boolean isBookAvailable(long id) {
        // Delegar para o repository
        return bookRepositorySQLite.existsBook(id);
    }
    
    /**
     * Remove todos os livros do banco de dados.
     * Esta é uma operação destrutiva que não pode ser desfeita.
     */
    @Override
    public void clearAllBooks() {
        // Regra de negócio: confirmar operação destrutiva
        System.out.println("Operação destrutiva: removendo todos os livros");
        
        // Delegar para o repository
        bookRepositorySQLite.clearAllBooks();
        
        System.out.println("Todos os livros foram removidos");
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