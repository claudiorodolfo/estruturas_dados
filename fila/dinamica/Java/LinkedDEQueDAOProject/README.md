# 📚 LinkedDEQueDAOProject

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-green.svg)](https://junit.org/junit4/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> **Implementação de uma Fila Dinâmica com Dupla Terminação de Livros em Java**  
> Sistema de gerenciamento de livros utilizando estrutura de dados fila (queue) com implementação dinâmica duplamente encadeada e com dupla terminação.

## 🎯 Sobre o Projeto

O **LinkedDEQueDAOProject** é uma implementação de um sistema de gerenciamento de livros utilizando uma **fila dinâmica com dupla terminação duplamente encadeada**. O projeto demonstra conceitos fundamentais de estruturas de dados, padrões de design e boas práticas de desenvolvimento Java.

### ✨ Características Principais

- 🏗️ **Arquitetura em Camadas**: Separação clara entre modelo, DAO, repositório e aplicação
- 🔗 **Fila Dinâmica Dupla Terminação**: Implementação com nós duplamente encadeados
- 📖 **CRUD Completo**: Operações de criação, leitura, atualização e exclusão
- 🧪 **Testes Abrangentes**: Cobertura completa com JUnit 4
- 📦 **Maven**: Gerenciamento de dependências e build automatizado
- 🎨 **Padrões de Design**: DAO, Factory Method e Interface Segregation

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                  BookService (App Layer)                    │
├─────────────────────────────────────────────────────────────┤
│              BookDAOLinkedDEQue (DAO Layer)                 │
├─────────────────────────────────────────────────────────────┤
│       LinkedDEQue<T>, DoubleNode<T> (DataBase Layer)        │
├─────────────────────────────────────────────────────────────┤
│                    Book (Model Layer)                       │
└─────────────────────────────────────────────────────────────┘
```

## 📁 Estrutura do Projeto

```
src/
├── main/java/br/edu/ifba/vdc/bsi/linkeddequedao/
│   ├── app/
│   │   └── BookService.java              # Classe principal da aplicação
│   ├── dao/
│   │   ├── BookDAO.java                   # Interface do DAO para Livro
│   │   └── BookDAOLinkedDEQue.java        # Implementação do DAO para Livro
│   ├── dao/repository/
│   │   ├── Queueable.java                 # Interface da fila
│   │   ├── LinkedDEQue.java              # Implementação da fila dinâmica
│   │   └── DoubleNode.java                # Nó duplamente encadeado
│   └── model/
│       └── Book.java                      # Modelo de dados para Livro
└── test/java/br/edu/ifba/vdc/bsi/linkeddequedao/
    ├── app/
    │   └── BookServiceTest.java           # Testes da aplicação
    ├── dao/
    │   └── BookDAOLinkedDEQueTest.java    # Testes do DAO
    ├── dao/repository/
    │   ├── LinkedDEQueTest.java          # Testes da fila
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
cd estruturas_dados/fila/dinamica/Java/LinkedDEQueDAOProject
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
java -jar target/book-queue-1.0.0.jar
```

### Exemplo de Uso Programático
```java
// Obter o DAO
BookDAO bookDAO = BookService.getDBBook();

// Criar livros
Book book1 = new Book(1L, "Dom Casmurro", "Machado de Assis",
                    LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90);

// Adicionar à fila (enfileirar)
bookDAO.addBook(book1);

// Consultar o primeiro da fila
Book firstBook = bookDAO.getBook(null);
System.out.println("Primeiro: " + firstBook.getTitle());

// Atualizar o primeiro
Book updatedBook = new Book(1L, "Dom Casmurro - Edição Especial", "Machado de Assis",
                          LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 35.90);
bookDAO.updateBook(updatedBook);

// Remover o primeiro da fila (desenfileirar)
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

# Testes da fila
mvn test -Dtest=LinkedDEQueTest

# Testes de integração
mvn test -Dtest=IntegrationTest
```

### Cobertura de Testes
- ✅ **Book**: 12 testes (construtores, validação, toString)
- ✅ **LinkedDEQue**: 18 testes (FIFO, exceções, tipos genéricos)
- ✅ **DoubleNode**: 10 testes (encadeamento, referências)
- ✅ **BookDAOLinkedDEQue**: 15 testes (CRUD, fila)
- ✅ **BookService**: 8 testes (factory, integração)
- ✅ **IntegrationTest**: 3 testes (fluxo completo)

## 📊 Funcionalidades

### 🔧 Operações da Fila
- **enqueue**: Adicionar elemento ao final da fila
- **dequeue**: Remover e retornar o elemento do início da fila
- **peek**: Consultar elemento do início (sem remover)
- **update**: Atualizar elemento do início
- **isEmpty**: Verificar se está vazia
- **isFull**: Verificar se está cheia

### 📚 Operações de Livros
- **addBook**: Adicionar livro à fila (enfileirar)
- **getBook**: Consultar primeiro livro da fila
- **updateBook**: Atualizar primeiro livro da fila
- **deleteBook**: Remover primeiro livro da fila (desenfileirar)
- **printBooks**: Listar todos os livros

## 🎨 Padrões de Design Implementados

| Padrão | Implementação | Benefício |
|--------|---------------|-----------|
| **DAO** | `BookDAO` → `BookDAOLinkedDEQue` | Separação de responsabilidades |
| **Factory Method** | `BookService.getDBBook()` | Criação flexível de objetos |
| **Interface Segregation** | `Queueable<T>` | Contratos específicos |
| **Generic Types** | `LinkedDEQue<T>`, `DoubleNode<T>` | Reutilização de código |

## 🔍 Características Técnicas

### Fila Dinâmica Dupla Terminação Duplamente Encadeada
- **Vantagens**: Crescimento dinâmico, inserção/remoção O(1), acesso em ambas as extremidades
- **Estrutura**: Nós com referências anterior e próxima
- **Comportamento**: FIFO (First In, First Out) com dupla terminação

### Tratamento de Exceções
```java
// Fila vazia
NoSuchElementException: "Fila Vazia!"

// Fila cheia
NoSuchElementException: "Fila Cheia!"

// Campos obrigatórios
IllegalArgumentException: "campo obrigatório não pode ser nulo!"
```

## 📈 Performance

| Operação | Complexidade | Descrição |
|----------|--------------|-----------|
| **enqueue** | O(1) | Adicionar ao final |
| **dequeue** | O(1) | Remover do início |
| **peek** | O(1) | Consultar início |
| **update** | O(1) | Atualizar início |
| **isEmpty** | O(1) | Verificar se está vazia |
| **isFull** | O(1) | Verificar se está cheia |
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
- Comunidade Java
- Contribuidores do projeto

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela! ⭐**

[![GitHub stars](https://img.shields.io/github/stars/seu-usuario/estruturas_dados?style=social)](https://github.com/seu-usuario/estruturas_dados)

</div>
