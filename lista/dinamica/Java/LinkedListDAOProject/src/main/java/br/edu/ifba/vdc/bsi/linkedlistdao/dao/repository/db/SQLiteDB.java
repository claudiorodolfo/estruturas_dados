package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por gerenciar a estrutura do banco de dados SQLite.
 * Cria e gerencia as tabelas necessárias para o sistema de livros.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0.0
 * @since 2024-01-01
 */
public class SQLiteDB {
    
    private static final Logger LOGGER = Logger.getLogger(SQLiteDB.class.getName());
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
        LOGGER.info("Inicializando banco de dados SQLite...");
        
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            // Cria a tabela de livros
            createBooksTable(statement);
            
            // Commit das alterações
            sqliteConnection.commit();
            
            LOGGER.info("Banco de dados SQLite inicializado com sucesso");
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inicializar banco de dados", e);
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
            CREATE TABLE IF NOT EXISTS books (
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
        LOGGER.info("Tabela 'books' criada/verificada com sucesso");
        
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
        String indexAuthorSQL = "CREATE INDEX IF NOT EXISTS idx_books_author ON books(author)";
        statement.execute(indexAuthorSQL);
        
        // Índice para busca por título
        String indexTitleSQL = "CREATE INDEX IF NOT EXISTS idx_books_title ON books(title)";
        statement.execute(indexTitleSQL);
        
        // Índice para busca por ISBN
        String indexIsbnSQL = "CREATE INDEX IF NOT EXISTS idx_books_isbn ON books(isbn)";
        statement.execute(indexIsbnSQL);
        
        // Índice para busca por data de publicação
        String indexDateSQL = "CREATE INDEX IF NOT EXISTS idx_books_publication_date ON books(publication_date)";
        statement.execute(indexDateSQL);
        
        // Índice para busca por preço
        String indexPriceSQL = "CREATE INDEX IF NOT EXISTS idx_books_price ON books(price)";
        statement.execute(indexPriceSQL);
        
        LOGGER.info("Índices criados/verificados com sucesso");
    }
    
    /**
     * Verifica se a tabela de livros existe.
     * 
     * @return true se a tabela existir, false caso contrário
     * @throws SQLException se houver erro na verificação
     */
    public boolean isBooksTableExists() throws SQLException {
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String checkTableSQL = """
                SELECT name FROM sqlite_master 
                WHERE type='table' AND name='books'
                """;
            
            return statement.executeQuery(checkTableSQL).next();
        }
    }
    
    /**
     * Limpa todos os dados da tabela de livros.
     * 
     * @throws SQLException se houver erro na limpeza
     */
    public void clearBooksTable() throws SQLException {
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String clearTableSQL = "DELETE FROM books";
            int rowsAffected = statement.executeUpdate(clearTableSQL);
            
            sqliteConnection.commit();
            
            LOGGER.info("Tabela 'books' limpa com sucesso. Registros removidos: " + rowsAffected);
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao limpar tabela de livros", e);
            sqliteConnection.rollback();
            throw e;
        }
    }
    
    /**
     * Remove a tabela de livros completamente.
     * 
     * @throws SQLException se houver erro na remoção
     */
    public void dropBooksTable() throws SQLException {
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String dropTableSQL = "DROP TABLE IF EXISTS books";
            statement.execute(dropTableSQL);
            
            sqliteConnection.commit();
            
            LOGGER.info("Tabela 'books' removida com sucesso");
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao remover tabela de livros", e);
            sqliteConnection.rollback();
            throw e;
        }
    }
    
    /**
     * Recria a tabela de livros (remove e cria novamente).
     * 
     * @throws SQLException se houver erro na recriação
     */
    public void recreateBooksTable() throws SQLException {
        LOGGER.info("Recriando tabela de livros...");
        
        try (Connection connection = sqliteConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            // Remove a tabela se existir
            dropBooksTable();
            
            // Cria a tabela novamente
            createBooksTable(statement);
            
            sqliteConnection.commit();
            
            LOGGER.info("Tabela de livros recriada com sucesso");
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao recriar tabela de livros", e);
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
                    sqliteConnection.getConnectionInfo(), isBooksTableExists());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Erro ao obter informações do banco", e);
        }
        return "Banco de dados não disponível";
    }
}
