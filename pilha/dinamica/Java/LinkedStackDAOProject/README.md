# 📚 LinkedStackDAOProject

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-green.svg)](https://junit.org/junit4/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> **Implementação de uma Pilha Dinâmica de Livros em Java**  
> Sistema de gerenciamento de livros utilizando estrutura de dados pilha (stack) com implementação dinâmica duplamente encadeada.

## 🎯 Sobre o Projeto

O **LinkedStackDAOProject** é uma implementação completa de um sistema de gerenciamento de livros utilizando uma **pilha dinâmica duplamente encadeada**. O projeto demonstra conceitos fundamentais de estruturas de dados, padrões de design e boas práticas de desenvolvimento Java.

### ✨ Características Principais

- 🏗️ **Arquitetura em Camadas**: Separação clara entre modelo, DAO, repositório e aplicação
- 🔗 **Pilha Dinâmica**: Implementação com nós duplamente encadeados
- 📖 **CRUD Completo**: Operações de criação, leitura, atualização e exclusão
- 🧪 **Testes Abrangentes**: Cobertura completa com JUnit 4
- 📦 **Maven**: Gerenciamento de dependências e build automatizado
- 🎨 **Padrões de Design**: DAO, Factory Method e Interface Segregation

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                    BookService (App Layer)                  │
├─────────────────────────────────────────────────────────────┤
│              BookDAOLinkedStack (DAO Layer)                 │
├─────────────────────────────────────────────────────────────┤
│      LinkedStack<T>, DoubleNode<T> (Repository Layer)       │
├─────────────────────────────────────────────────────────────┤
│                    Book (Model Layer)                       │
└─────────────────────────────────────────────────────────────┘
```

## 📁 Estrutura do Projeto

```
src/
├── main/java/br/edu/ifba/vdc/bsi/linkedstackdao/
│   ├── app/
│   │   └── BookService.java              # Classe principal da aplicação
│   ├── dao/
│   │   ├── BookDAO.java                   # Interface do DAO
│   │   └── BookDAOLinkedStack.java        # Implementação do DAO
│   ├── dao/repository/
│   │   ├── Stackable.java                 # Interface da pilha
│   │   ├── LinkedStack.java              # Implementação da pilha
│   │   └── DoubleNode.java                # Nó duplamente encadeado
│   └── model/
│       └── Book.java                      # Modelo de dados
└── test/java/br/edu/ifba/vdc/bsi/linkedstackdao/
    ├── app/
    │   └── BookServiceTest.java           # Testes da aplicação
    ├── dao/
    │   └── BookDAOLinkedStackTest.java    # Testes do DAO
    ├── dao/repository/
    │   ├── LinkedStackTest.java          # Testes da pilha
    │   └── DoubleNodeTest.java           # Testes do nó
    ├── model/
    │   └── BookTest.java                 # Testes do modelo
    └── IntegrationTest.java              # Testes de integração
```

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 | Linguagem de programação |
| **Maven** | 3.6+ | Gerenciamento de dependências |
| **JUnit** | 4.13.2 | Framework de testes |
| **Hamcrest** | 1.3 | Biblioteca de asserções |
| **JavaDoc** | 3.5.0 | Geração de documentação |

## 📋 Pré-requisitos

- ☕ **Java 21** ou superior
- 🔧 **Maven 3.6+** ou superior
- 💻 **IDE** (IntelliJ IDEA, Eclipse, VS Code)

## 🛠️ Instalação e Configuração

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/estruturas_dados.git
cd estruturas_dados/pilha/dinamica/Java/LinkedStackDAOProject
```

### 2. Compile o projeto
```bash
mvn clean compile
```

### 3. Execute os testes
```bash
mvn test
```

### 4. Execute a aplicação
```bash
mvn exec:java
```

## 🎮 Como Usar

### Execução Básica
```bash
# Compilar e executar
mvn clean compile exec:java

# Gerar JAR executável
mvn clean package
java -jar target/book-stack-1.0.0.jar
```

### Exemplo de Uso Programático
```java
// Obter o DAO
BookDAO bookDAO = BookService.getDBBook();

// Criar livros
Book book1 = new Book(1L, "Dom Casmurro", "Machado de Assis",
                    LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90);

// Adicionar à pilha
bookDAO.addBook(book1);

// Consultar o topo da pilha
Book topBook = bookDAO.getBook(null);
System.out.println("Topo: " + topBook.getTitle());

// Atualizar o topo
Book updatedBook = new Book(1L, "Dom Casmurro - Edição Especial", "Machado de Assis",
                          LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 35.90);
bookDAO.updateBook(updatedBook);

// Remover do topo
Book removedBook = bookDAO.deleteBook(null);
System.out.println("Removido: " + removedBook.getTitle());
```

## 🧪 Testes

O projeto possui **66 testes unitários** cobrindo todas as funcionalidades:

### Executar Todos os Testes
```bash
mvn test
```

### Executar Testes Específicos
```bash
# Testes do modelo
mvn test -Dtest=BookTest

# Testes da pilha
mvn test -Dtest=LinkedStackTest

# Testes de integração
mvn test -Dtest=IntegrationTest
```

### Cobertura de Testes
- ✅ **Book**: 12 testes (construtores, validação, toString)
- ✅ **LinkedStack**: 18 testes (LIFO, exceções, tipos genéricos)
- ✅ **DoubleNode**: 10 testes (encadeamento, referências)
- ✅ **BookDAOLinkedStack**: 15 testes (CRUD, pilha)
- ✅ **BookService**: 8 testes (factory, integração)
- ✅ **IntegrationTest**: 3 testes (fluxo completo)

## 📊 Funcionalidades

### 🔧 Operações da Pilha
- **Push**: Adicionar elemento ao topo
- **Pop**: Remover elemento do topo
- **Peek**: Consultar elemento do topo (sem remover)
- **Update**: Atualizar elemento do topo
- **isEmpty**: Verificar se está vazia
- **isFull**: Verificar se está cheia

### 📚 Operações de Livros
- **addBook**: Adicionar livro à pilha
- **getBook**: Consultar livro do topo
- **updateBook**: Atualizar livro do topo
- **deleteBook**: Remover livro do topo
- **printBooks**: Listar todos os livros

## 🎨 Padrões de Design Implementados

| Padrão | Implementação | Benefício |
|--------|---------------|-----------|
| **DAO** | `BookDAO` → `BookDAOLinkedStack` | Separação de responsabilidades |
| **Factory Method** | `BookService.getDBBook()` | Criação flexível de objetos |
| **Interface Segregation** | `Stackable<T>` | Contratos específicos |
| **Generic Types** | `LinkedStack<T>`, `DoubleNode<T>` | Reutilização de código |

## 🔍 Características Técnicas

### Pilha Dinâmica Duplamente Encadeada
- **Vantagens**: Crescimento dinâmico, inserção/remoção O(1)
- **Estrutura**: Nós com referências anterior e próxima
- **Comportamento**: LIFO (Last In, First Out)

### Tratamento de Exceções
```java
// Pilha vazia
NoSuchElementException: "Pilha Vazia!"

// Pilha cheia
NoSuchElementException: "Pilha Cheia!"

// Campos obrigatórios
IllegalArgumentException: "campo obrigatório não pode ser nulo!"
```

## 📈 Performance

| Operação | Complexidade | Descrição |
|----------|--------------|-----------|
| **Push** | O(1) | Adicionar ao topo |
| **Pop** | O(1) | Remover do topo |
| **Peek** | O(1) | Consultar topo |
| **Update** | O(1) | Atualizar topo |
| **toString** | O(n) | Listar todos os elementos |

## 🤝 Contribuição

1. **Fork** o projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. Abra um **Pull Request**

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Cláudio Rodolfo Sousa de Oliveira**
- 📧 Email: [claudiorodolfo@ifba.edu.br](mailto:claudiorodolfol@ifba.edu.br)
- 💼 LinkedIn: [Cláudio Rodolfo](https://linkedin.com/in/claudiorodolfo)
- 🐙 GitHub: [@claudiorodolfo](https://github.com/claudiorodolfo)

## 🙏 Agradecimentos

- Instituto Federal da Bahia (IFBA)
- Professor de Estruturas de Dados
- Comunidade Java
- Contribuidores do projeto

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela! ⭐**

[![GitHub stars](https://img.shields.io/github/stars/seu-usuario/estruturas_dados?style=social)](https://github.com/seu-usuario/estruturas_dados)

</div>
