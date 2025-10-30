package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.db;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.SQLiteConnection;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.SQLiteDB;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Book;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do repositório para persistência de livros em SQLite.
 * Contém toda a lógica de acesso aos dados SQL.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0.0
 * @since 2024-01-01
 */
public class BookRepositorySQLite {
    
    private final SQLiteConnection sqliteConnection;
    private final SQLiteDB sqliteDB;
    
    /**
     * Construtor que inicializa a conexão e o banco de dados.
     */
    public BookRepositorySQLite() {
        this.sqliteConnection = SQLiteConnection.getInstance();
        this.sqliteDB = new SQLiteDB(sqliteConnection);
        
        try {
            // Inicializa o banco de dados se necessário
            if (!sqliteDB.isBooksTableExists()) {
                sqliteDB.initializeDatabase();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao inicializar banco de dados", e);
        }
    }
    
    public void insertBook(Book book) {
        String sql = """
            INSERT INTO book (id, title, author, publication_date, isbn, price, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """;
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            
            if (book.getPublicationDate() != null) {
                statement.setDate(4, Date.valueOf(book.getPublicationDate()));
            } else {
                statement.setNull(4, Types.DATE);
            }
            
            statement.setString(5, book.getIsbn());
            
            if (book.getPrice() != null) {
                statement.setDouble(6, book.getPrice());
            } else {
                statement.setNull(6, Types.REAL);
            }
            
            statement.executeUpdate();
            sqliteConnection.commit();
            
            System.out.println("Livro inserido com sucesso: " + book.getTitle());
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir livro: " + e.getMessage());
            try {
                sqliteConnection.rollback();
            } catch (SQLException rollbackException) {
                System.err.println("Erro no rollback: " + rollbackException.getMessage());
            }
            throw new RuntimeException("Erro ao inserir livro", e);
        }
    }
    
    public Book[] selectAllBooks() {
        String sql = "SELECT * FROM book ORDER BY id";
        List<Book> books = new ArrayList<>();
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                books.add(book);
            }
            
            System.out.println("Total de livros recuperados: " + books.size());
            
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar todos os livros: " + e.getMessage());
            throw new RuntimeException("Erro ao recuperar livros", e);
        }
        
        return books.toArray(new Book[0]);
    }
    
    public void updateBook(Book book) {
        String sql = """
            UPDATE book 
            SET title = ?, author = ?, publication_date = ?, isbn = ?, price = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id = ?
            """;
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            
            if (book.getPublicationDate() != null) {
                statement.setDate(3, Date.valueOf(book.getPublicationDate()));
            } else {
                statement.setNull(3, Types.DATE);
            }
            
            statement.setString(4, book.getIsbn());
            
            if (book.getPrice() != null) {
                statement.setDouble(5, book.getPrice());
            } else {
                statement.setNull(5, Types.REAL);
            }
            
            statement.setLong(6, book.getId());
            
            int rowsAffected = statement.executeUpdate();
            sqliteConnection.commit();
            
            if (rowsAffected == 0) {
                throw new RuntimeException("Livro não encontrado para atualização: " + book.getId());
            }
            
            System.out.println("Livro atualizado com sucesso: " + book.getTitle());
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
            try {
                sqliteConnection.rollback();
            } catch (SQLException rollbackException) {
                System.err.println("Erro no rollback: " + rollbackException.getMessage());
            }
            throw new RuntimeException("Erro ao atualizar livro", e);
        }
    }
    
    public Book deleteBook(long id) {
        // Primeiro, busca o livro para retorná-lo
        Book book = selectBookById(id);
        if (book == null) {
            throw new RuntimeException("Livro não encontrado para exclusão: " + id);
        }
        
        String sql = "DELETE FROM book WHERE id = ?";
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            
            int rowsAffected = statement.executeUpdate();
            sqliteConnection.commit();
            
            if (rowsAffected == 0) {
                throw new RuntimeException("Livro não encontrado para exclusão: " + id);
            }
            
            System.out.println("Livro excluído com sucesso: " + book.getTitle());
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir livro: " + e.getMessage());
            try {
                sqliteConnection.rollback();
            } catch (SQLException rollbackException) {
                System.err.println("Erro no rollback: " + rollbackException.getMessage());
            }
            throw new RuntimeException("Erro ao excluir livro", e);
        }
        
        return book;
    }
    
    public Book selectBookById(long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createBookFromResultSet(resultSet);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar livro", e);
        }
        
        return null;
    }
    
    public Book[] selectBooksByAuthor(String author) {
        String sql = "SELECT * FROM book WHERE author LIKE ? ORDER BY title";
        return executeSearchQuery(sql, "%" + author + "%");
    }
    
    public Book[] selectBooksByPublicationDate(LocalDate date) {
        String sql = "SELECT * FROM book WHERE publication_date = ? ORDER BY title";
        return executeSearchQuery(sql, Date.valueOf(date));
    }
    
    public Book[] selectBooksByTitle(String title) {
        String sql = "SELECT * FROM book WHERE title LIKE ? ORDER BY title";
        return executeSearchQuery(sql, "%" + title + "%");
    }
    
    public Book selectBookByIsbn(String isbn) {
        String sql = "SELECT * FROM book WHERE isbn = ?";
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, isbn);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createBookFromResultSet(resultSet);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro por ISBN: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar livro por ISBN", e);
        }
        
        return null;
    }
    
    public Book[] selectBooksByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM book WHERE price BETWEEN ? AND ? ORDER BY price";
        return executeSearchQuery(sql, minPrice, maxPrice);
    }
    
    public Book[] selectBooksByDateRange(LocalDate minDate, LocalDate maxDate) {
        String sql = "SELECT * FROM book WHERE publication_date BETWEEN ? AND ? ORDER BY publication_date";
        return executeSearchQuery(sql, Date.valueOf(minDate), Date.valueOf(maxDate));
    }
    
    public Book selectMostExpensiveBook() {
        String sql = "SELECT * FROM book WHERE price = (SELECT MAX(price) FROM book) LIMIT 1";
        return executeSingleBookQuery(sql);
    }
    
    public Book selectCheapestBook() {
        String sql = "SELECT * FROM book WHERE price = (SELECT MIN(price) FROM book) LIMIT 1";
        return executeSingleBookQuery(sql);
    }
    
    public Book selectNewestBook() {
        String sql = "SELECT * FROM book WHERE publication_date = (SELECT MAX(publication_date) FROM books) LIMIT 1";
        return executeSingleBookQuery(sql);
    }
    
    public Book selectOldestBook() {
        String sql = "SELECT * FROM book WHERE publication_date = (SELECT MIN(publication_date) FROM books) LIMIT 1";
        return executeSingleBookQuery(sql);
    }
    
    public int countTotalBooks() {
        String sql = "SELECT COUNT(*) FROM book";
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao contar livros: " + e.getMessage());
            throw new RuntimeException("Erro ao contar livros", e);
        }
        
        return 0;
    }
    
    public double calculateAveragePrice() {
        String sql = "SELECT AVG(price) FROM book WHERE price IS NOT NULL";
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao calcular preço médio: " + e.getMessage());
            throw new RuntimeException("Erro ao calcular preço médio", e);
        }
        
        return 0.0;
    }
    
    public boolean existsBook(long id) {
        return selectBookById(id) != null;
    }
    
    public void clearAllBooks() {
        try {
            sqliteDB.clearBooksTable();
            System.out.println("Todos os livros foram removidos");
        } catch (SQLException e) {
            System.err.println("Erro ao limpar todos os livros: " + e.getMessage());
            throw new RuntimeException("Erro ao limpar livros", e);
        }
    }
    
    /**
     * Executa uma consulta de busca e retorna um array de livros.
     * 
     * @param sql SQL da consulta
     * @param params parâmetros da consulta
     * @return array de livros encontrados
     */
    private Book[] executeSearchQuery(String sql, Object... params) {
        List<Book> books = new ArrayList<>();
        
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = createBookFromResultSet(resultSet);
                    books.add(book);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta de busca: " + e.getMessage());
            throw new RuntimeException("Erro ao executar consulta", e);
        }
        
        return books.toArray(new Book[0]);
    }
    
    /**
     * Executa uma consulta que retorna um único livro.
     * 
     * @param sql SQL da consulta
     * @return livro encontrado ou null
     */
    private Book executeSingleBookQuery(String sql) {
        try (Connection connection = sqliteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            if (resultSet.next()) {
                return createBookFromResultSet(resultSet);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta de livro único: " + e.getMessage());
            throw new RuntimeException("Erro ao executar consulta", e);
        }
        
        return null;
    }
    
    /**
     * Cria um objeto Book a partir de um ResultSet.
     * 
     * @param resultSet ResultSet com os dados do livro
     * @return objeto Book criado
     * @throws SQLException se houver erro ao acessar os dados
     */
    private Book createBookFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        
        Date publicationDateSql = resultSet.getDate("publication_date");
        LocalDate publicationDate = null;
        if (publicationDateSql != null) {
            publicationDate = publicationDateSql.toLocalDate();
        }
        
        String isbn = resultSet.getString("isbn");
        Double price = resultSet.getDouble("price");
        if (resultSet.wasNull()) {
            price = null;
        }
        
        return new Book(id, title, author, publicationDate, isbn, price);
    }
}
