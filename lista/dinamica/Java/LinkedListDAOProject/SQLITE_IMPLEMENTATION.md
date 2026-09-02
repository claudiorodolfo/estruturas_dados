# 🗄️ Implementação SQLite - Sistema de Livros

Este documento explica como foi implementada a persistência em SQLite no projeto LinkedListDAOProject, demonstrando o desacoplamento do código e a facilidade de troca entre diferentes implementações de persistência.

## 🎯 Objetivo

Demonstrar como o código bem estruturado permite trocar facilmente entre:
- **LinkedList** (persistência em memória)
- **SQLite** (persistência em banco de dados)

Sem alterar a lógica de negócio ou a interface do usuário.

## 🏗️ Arquitetura Implementada

```
┌─────────────────────────────────────────────────────────────┐
│                  BookService (App Layer)                    │
├─────────────────────────────────────────────────────────────┤
│              BookDAO Interface (Contract)                   │
├─────────────────────────────────────────────────────────────┤
│    BookDAOLinkedList    │    BookDAOSQLite (DAO Layer)      │
├─────────────────────────────────────────────────────────────┤
│   LinkedList<T>         │  SQLiteConnection + SQLiteDB      │
├─────────────────────────────────────────────────────────────┤
│                    Book (Model Layer)                       │
└─────────────────────────────────────────────────────────────┘
```

## 📁 Novos Arquivos Criados

### 1. **SQLiteConnection.java** (Repository Layer)
- **Localização**: `dao/repository/SQLiteConnection.java`
- **Responsabilidade**: Gerenciar conexões com SQLite
- **Padrão**: Singleton
- **Funcionalidades**:
  - Conexão única e reutilizável
  - Controle de transações (commit/rollback)
  - Validação de conexão
  - Logging de operações

### 2. **SQLiteDB.java** (Repository Layer)
- **Localização**: `dao/repository/SQLiteDB.java`
- **Responsabilidade**: Gerenciar estrutura do banco
- **Funcionalidades**:
  - Criação de tabelas
  - Criação de índices para performance
  - Operações de manutenção (limpar, recriar tabelas)
  - Verificação de existência de tabelas

### 3. **BookDAOSQLite.java** (DAO Layer)
- **Localização**: `dao/BookDAOSQLite.java`
- **Responsabilidade**: Implementar BookDAO com SQLite
- **Funcionalidades**:
  - Todas as operações CRUD
  - Consultas complexas (por autor, preço, data)
  - Estatísticas (livro mais caro, mais barato, etc.)
  - Tratamento de exceções e transações

### 4. **SQLiteExample.java** (Demo)
- **Localização**: `app/SQLiteExample.java`
- **Responsabilidade**: Demonstrar uso do SQLite
- **Funcionalidades**:
  - Comparação entre LinkedList e SQLite
  - Exemplos de operações CRUD
  - Demonstração do desacoplamento

## 🔧 Modificações Realizadas

### 1. **pom.xml**
```xml
<!-- SQLite JDBC Driver -->
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.44.1.0</version>
</dependency>
```

### 2. **BookService.java**
```java
// Novos métodos para obter diferentes implementações
public static BookDAO getSQLiteBook() {
    return new BookDAOSQLite();
}

public static BookDAO getBookDAO(String type) {
    if ("sqlite".equalsIgnoreCase(type)) {
        return new BookDAOSQLite();
    } else {
        return new BookDAOLinkedList();
    }
}
```

## 🚀 Como Usar

### 1. **Execução com Escolha de Persistência**
```bash
mvn exec:java
```
O sistema perguntará:
- `1` para LinkedList (memória)
- `2` para SQLite (banco de dados)

### 2. **Uso Programático**
```java
// Usando LinkedList
BookDAO linkedListDAO = BookService.getDBBook();

// Usando SQLite
BookDAO sqliteDAO = BookService.getSQLiteBook();

// Usando factory method
BookDAO dao = BookService.getBookDAO("sqlite");
```

## 📊 Estrutura do Banco SQLite

### Tabela `books`
```sql
CREATE TABLE books (
    id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    author TEXT,
    publication_date DATE,
    isbn TEXT,
    price REAL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🎨 Padrões de Design Utilizados

| Padrão | Implementação | Benefício |
|--------|---------------|-----------|
| **DAO** | `BookDAO` → `BookDAOSQLite` | Separação de responsabilidades |
| **Singleton** | `SQLiteConnection` | Conexão única e reutilizável |
| **Factory Method** | `BookService.getSQLiteBook()` | Criação flexível de objetos |
| **Repository** | `SQLiteDB` | Encapsulamento de operações de banco |
| **Template Method** | Operações CRUD padronizadas | Consistência na implementação |

## 🔍 Vantagens do Desacoplamento

### 1. **Mesma Interface, Diferentes Implementações**
```java
// Código idêntico para ambas as implementações
BookDAO dao = getBookDAO("sqlite"); // ou "linkedlist"
dao.addBook(livro);
Book[] livros = dao.getAllBooks();
```

### 2. **Troca Fácil de Persistência**
- **Desenvolvimento**: Use LinkedList (mais rápido)
- **Produção**: Use SQLite (persistente)
- **Testes**: Use qualquer uma

### 3. **Manutenibilidade**
- Mudanças na interface afetam ambas implementações
- Lógica de negócio permanece inalterada
- Fácil adição de novas implementações (PostgreSQL, MongoDB, etc.)

## 📈 Performance

| Operação | LinkedList | SQLite | Observação |
|----------|------------|--------|------------|
| **Inserção** | O(1) | O(1) | SQLite com índices |
| **Busca por ID** | O(n) | O(log n) | SQLite com índice primário |
| **Busca por Autor** | O(n) | O(log n) | SQLite com índice |
| **Listagem** | O(n) | O(n) | Similar performance |
| **Persistência** | Volátil | Permanente | SQLite persiste dados |

## 🧪 Testes

### Executar Testes Existentes
```bash
mvn test
```

### Testar SQLite Especificamente
```java
@Test
public void testSQLiteOperations() {
    BookDAO sqliteDAO = new BookDAOSQLite();
    // Testes específicos para SQLite
}
```

## 🔧 Configurações

### Arquivo de Banco
- **Localização**: `books.db` (na raiz do projeto)
- **Tipo**: SQLite 3
- **Encoding**: UTF-8

### Logs
- **Nível**: INFO para operações principais
- **Localização**: Console
- **Inclui**: Conexões, transações, erros

## 🚨 Tratamento de Erros

### Conexão
- Reconexão automática se conexão perdida
- Rollback automático em caso de erro
- Logs detalhados para debugging

### Dados
- Validação de campos obrigatórios
- Tratamento de valores nulos
- Exceções específicas para cada operação

## 📝 Exemplo Completo

```java
public class ExemploUso {
    void main() {
        // Escolha da implementação
        BookDAO dao = BookService.getSQLiteBook();
        
        // Operações idênticas independente da implementação
        Book livro = new Book(1L, "Dom Casmurro", "Machado de Assis");
        dao.addBook(livro);
        
        Book encontrado = dao.getBookById(1L);
        IO.println("Encontrado: " + encontrado.getTitle());
        
        // SQLite persiste os dados automaticamente
        // LinkedList perde os dados ao fechar o programa
    }
}
```

## 🎓 Lições Demonstradas

1. **Desacoplamento**: Interface comum, implementações diferentes
2. **Flexibilidade**: Troca fácil entre persistências
3. **Manutenibilidade**: Código organizado em camadas
4. **Reutilização**: Mesma lógica, diferentes backends
5. **Escalabilidade**: Fácil adição de novas implementações

---

**⭐ Esta implementação demonstra como código bem estruturado permite evolução e manutenção sem quebrar funcionalidades existentes! ⭐**
