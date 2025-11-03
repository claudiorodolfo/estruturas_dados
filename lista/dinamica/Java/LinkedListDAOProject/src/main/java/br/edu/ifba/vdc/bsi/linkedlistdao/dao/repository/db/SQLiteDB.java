package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável por gerenciar a estrutura do banco de dados SQLite.
 * Cria e gerencia as tabelas necessárias para o sistema de livros.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0.0
 * @since 2024-01-01
 */
public class SQLiteDB {
    
    private final SQLiteConnection sqliteConnection;
    
    /**
     * Construtor que recebe uma instância de SQLiteConnection.
     * 
     * @param sqliteConnection instância de SQLiteConnection
     */
    public SQLiteDB(SQLiteConnection sqliteConnection) {
        this.sqliteConnection = sqliteConnection;
    }
    
    /**
     * Inicializa o banco de dados criando as tabelas necessárias.
     * 
     * @throws SQLException se houver erro na criação das tabelas
     */
    public void initializeDatabase() throws SQLException {
        System.out.println("Inicializando banco de dados SQLite...");
        
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            // Cria a tabela de book
            createBooksTable(statement);
            
            // Commit das alterações
            sqliteConnection.commit();
            
            System.out.println("Banco de dados SQLite inicializado com sucesso");
            
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
            sqliteConnection.rollback();
            throw e;
        }
    }
    
    /**
     * Cria a tabela de livros com todos os campos necessários.
     * 
     * @param statement Statement para execução de comandos SQL
     * @throws SQLException se houver erro na criação da tabela
     */
    private void createBooksTable(Statement statement) throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS book (
                id INTEGER PRIMARY KEY,
                title TEXT NOT NULL,
                author TEXT,
                publication_date DATE,
                isbn TEXT,
                price REAL,
                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
            )
            """;
        
        statement.execute(createTableSQL);
        System.out.println("Tabela 'book' criada/verificada com sucesso");
        
        // Cria índices para melhorar performance
        createIndexes(statement);
    }
    
    /**
     * Cria índices para otimizar as consultas mais comuns.
     * 
     * @param statement Statement para execução de comandos SQL
     * @throws SQLException se houver erro na criação dos índices
     */
    private void createIndexes(Statement statement) throws SQLException {
        // Índice para busca por autor
        String indexAuthorSQL = "CREATE INDEX IF NOT EXISTS idx_books_author ON book(author)";
        statement.execute(indexAuthorSQL);
        
        // Índice para busca por título
        String indexTitleSQL = "CREATE INDEX IF NOT EXISTS idx_books_title ON book(title)";
        statement.execute(indexTitleSQL);
        
        // Índice para busca por ISBN
        String indexIsbnSQL = "CREATE INDEX IF NOT EXISTS idx_books_isbn ON book(isbn)";
        statement.execute(indexIsbnSQL);
        
        // Índice para busca por data de publicação
        String indexDateSQL = "CREATE INDEX IF NOT EXISTS idx_books_publication_date ON book(publication_date)";
        statement.execute(indexDateSQL);
        
        // Índice para busca por preço
        String indexPriceSQL = "CREATE INDEX IF NOT EXISTS idx_books_price ON book(price)";
        statement.execute(indexPriceSQL);
        
        System.out.println("Índices criados/verificados com sucesso");
    }
    
    /**
     * Verifica se a tabela de livros existe.
     * 
     * @return true se a tabela existir, false caso contrário
     * @throws SQLException se houver erro na verificação
     */
    public boolean isBookTableExists() throws SQLException {
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String checkTableSQL = """
                SELECT name 
                FROM sqlite_master 
                WHERE type='table' AND name='book'
                """;
            
            return statement.executeQuery(checkTableSQL).next();
        }
    }
    
    /**
     * Limpa todos os dados da tabela de livros.
     * 
     * @throws SQLException se houver erro na limpeza
     */
    public void clearBookTable() throws SQLException {
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String clearTableSQL = "DELETE FROM book";
            int rowsAffected = statement.executeUpdate(clearTableSQL);
            
            sqliteConnection.commit();
            
            System.out.println("Tabela 'book' limpa com sucesso. Registros removidos: " + rowsAffected);
            
        } catch (SQLException e) {
            System.err.println("Erro ao limpar tabela de livros: " + e.getMessage());
            sqliteConnection.rollback();
            throw e;
        }
    }
    
    /**
     * Remove a tabela de livros completamente.
     * 
     * @throws SQLException se houver erro na remoção
     */
    public void dropBookTable() throws SQLException {
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String dropTableSQL = "DROP TABLE IF EXISTS book";
            statement.execute(dropTableSQL);
            
            sqliteConnection.commit();
            
            System.out.println("Tabela 'book' removida com sucesso");
            
        } catch (SQLException e) {
            System.err.println("Erro ao remover tabela de livros: " + e.getMessage());
            sqliteConnection.rollback();
            throw e;
        }
    }
    
    /**
     * Recria a tabela de livros (remove e cria novamente).
     * 
     * @throws SQLException se houver erro na recriação
     */
    public void recreateBookTable() throws SQLException {
        System.out.println("Recriando tabela book...");
        
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            // Remove a tabela se existir
            dropBookTable();
            
            // Cria a tabela novamente
            createBooksTable(statement);
            
            sqliteConnection.commit();
            
            System.out.println("Tabela book recriada com sucesso");
            
        } catch (SQLException e) {
            System.err.println("Erro ao recriar tabela book: " + e.getMessage());
            sqliteConnection.rollback();
            throw e;
        }
    }
    
    /**
     * Retorna informações sobre o banco de dados.
     * 
     * @return String com informações do banco
     */
    public String getDatabaseInfo() {
        try {
            if (sqliteConnection.isConnectionValid()) {
                return String.format("SQLite Database - Connection: %s, Table exists: %s", 
                    sqliteConnection.getConnectionInfo(), isBookTableExists());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter informações do banco: " + e.getMessage());
        }
        return "Banco de dados não disponível";
    }
}
