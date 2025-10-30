package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados SQLite.
 * Implementa o padrão Singleton para garantir uma única instância de conexão.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0.0
 * @since 2024-01-01
 */
public class SQLiteConnection {
    private static final String DB_URL = "jdbc:sqlite:books.db";
    private static SQLiteConnection instance;
    private Connection connection;
    
    /**
     * Construtor privado para implementar o padrão Singleton.
     */
    private SQLiteConnection() {
        initializeConnection();
    }
    
    /**
     * Retorna a instância única da classe SQLiteConnection.
     * 
     * @return instância única de SQLiteConnection
     */
    public static synchronized SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }
    
    /**
     * Inicializa a conexão com o banco de dados SQLite.
     * Cria o arquivo de banco se não existir.
     */
    private void initializeConnection() {
        try {
            // Carrega o driver SQLite
            Class.forName("org.sqlite.JDBC");
            
            // Estabelece a conexão
            connection = DriverManager.getConnection(DB_URL);
            
            // Configurações da conexão
            connection.setAutoCommit(false);
            
            System.out.println("Conexão com SQLite estabelecida com sucesso: " + DB_URL);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite não encontrado: " + e.getMessage());
            throw new RuntimeException("Driver SQLite não encontrado", e);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco SQLite: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar com o banco SQLite", e);
        }
    }
    
    /**
     * Retorna a conexão ativa com o banco de dados.
     * 
     * @return Connection ativa
     * @throws SQLException se a conexão não estiver disponível
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("Conexão perdida, tentando reconectar...");
            initializeConnection();
        }
        return connection;
    }
    
    /**
     * Verifica se a conexão está ativa e válida.
     * 
     * @return true se a conexão estiver ativa, false caso contrário
     */
    public boolean isConnectionValid() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            System.err.println("Erro ao verificar validade da conexão: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão com SQLite fechada com sucesso");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
    /**
     * Executa um commit na transação atual.
     * 
     * @throws SQLException se houver erro no commit
     */
    public void commit() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
            System.out.println("Commit executado com sucesso");
        }
    }
    
    /**
     * Executa um rollback na transação atual.
     * 
     * @throws SQLException se houver erro no rollback
     */
    public void rollback() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            System.out.println("Rollback executado com sucesso");
        }
    }
    
    /**
     * Retorna informações sobre a conexão atual.
     * 
     * @return String com informações da conexão
     */
    public String getConnectionInfo() {
        try {
            if (connection != null && !connection.isClosed()) {
                return String.format("SQLite Connection - URL: %s, AutoCommit: %s, Valid: %s", 
                    DB_URL, connection.getAutoCommit(), connection.isValid(5));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter informações da conexão: " + e.getMessage());
        }
        return "Conexão não disponível";
    }
}
