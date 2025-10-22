# 📚 LinkedListDAOProject

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-green.svg)](https://junit.org/junit4/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> **Implementação de uma Lista Dinâmica de Livros em Java**  
> Sistema de gerenciamento de livros utilizando estrutura de dados lista (linked list) com implementação dinâmica duplamente encadeada.

## 🎯 Sobre o Projeto

O **LinkedListDAOProject** é uma implementação de um sistema de gerenciamento de livros utilizando uma **lista dinâmica duplamente encadeada**. O projeto demonstra conceitos fundamentais de estruturas de dados, padrões de design e boas práticas de desenvolvimento Java.

### ✨ Características Principais

- 🏗️ **Arquitetura em Camadas**: Separação clara entre modelo, DAO, repositório e aplicação
- 🔗 **Lista Dinâmica**: Implementação com nós duplamente encadeados
- 📖 **CRUD Completo**: Operações de criação, leitura, atualização e exclusão
- 🧪 **Testes Abrangentes**: Cobertura completa com JUnit 4
- 📦 **Maven**: Gerenciamento de dependências e build automatizado
- 🎨 **Padrões de Design**: DAO, Factory Method e Interface Segregation

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                  BookService (App Layer)                    │
├─────────────────────────────────────────────────────────────┤
│              BookDAOLinkedList (DAO Layer)                  │
├─────────────────────────────────────────────────────────────┤
│       LinkedList<T>, DoubleNode<T> (DataBase Layer)          │
├─────────────────────────────────────────────────────────────┤
│                    Book (Model Layer)                       │
└─────────────────────────────────────────────────────────────┘
```

## 📁 Estrutura do Projeto

```
src/
├── main/java/br/edu/ifba/vdc/bsi/linkedlistdao/
│   ├── app/
│   │   └── BookService.java              # Classe principal da aplicação
│   ├── dao/
│   │   ├── BookDAO.java                   # Interface do DAO para Livro
│   │   └── BookDAOLinkedList.java        # Implementação do DAO para Livro
│   ├── dao/repository/
│   │   ├── Listable.java                  # Interface da lista
│   │   ├── LinkedList.java               # Implementação da lista dinâmica
│   │   └── DoubleNode.java                # Nó duplamente encadeado
│   └── model/
│       └── Book.java                      # Modelo de dados para Livro
└── test/java/br/edu/ifba/vdc/bsi/linkedlistdao/
    ├── app/
    │   └── BookServiceTest.java           # Testes da aplicação
    ├── dao/
    │   └── BookDAOLinkedListTest.java     # Testes do DAO
    ├── dao/repository/
    │   ├── LinkedListTest.java           # Testes da lista
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
cd estruturas_dados/lista/dinamica/Java/LinkedListDAOProject
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
java -jar target/book-list-1.0.0.jar
```

### Exemplo de Uso Programático
```java
// Obter o DAO
BookDAO bookDAO = BookService.getDBBook();

// Criar livros
Book book1 = new Book(1L, "Dom Casmurro", "Machado de Assis",
                    LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90);

// Adicionar à lista
bookDAO.addBook(book1);

// Consultar livro por ID
Book foundBook = bookDAO.getBook(1L);
System.out.println("Encontrado: " + foundBook.getTitle());

// Atualizar livro
Book updatedBook = new Book(1L, "Dom Casmurro - Edição Especial", "Machado de Assis",
                          LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 35.90);
bookDAO.updateBook(updatedBook);

// Remover livro
Book removedBook = bookDAO.deleteBook(1L);
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

# Testes da lista
mvn test -Dtest=LinkedListTest

# Testes de integração
mvn test -Dtest=IntegrationTest
```

### Cobertura de Testes
- ✅ **Book**: 12 testes (construtores, validação, toString)
- ✅ **LinkedList**: 18 testes (inserção, remoção, busca, tipos genéricos)
- ✅ **DoubleNode**: 10 testes (encadeamento, referências)
- ✅ **BookDAOLinkedList**: 15 testes (CRUD, lista)
- ✅ **BookService**: 8 testes (factory, integração)
- ✅ **IntegrationTest**: 3 testes (fluxo completo)

## 📊 Funcionalidades

### 🔧 Operações da Lista
- **add**: Adicionar elemento no início
- **addLast**: Adicionar elemento no final
- **remove**: Remover elemento por ID
- **get**: Buscar elemento por ID
- **update**: Atualizar elemento por ID
- **isEmpty**: Verificar se está vazia
- **size**: Retornar tamanho da lista

### 📚 Operações de Livros
- **addBook**: Adicionar livro à lista
- **getBook**: Buscar livro por ID
- **updateBook**: Atualizar livro por ID
- **deleteBook**: Remover livro por ID
- **printBooks**: Listar todos os livros

## 🎨 Padrões de Design Implementados

| Padrão | Implementação | Benefício |
|--------|---------------|-----------|
| **DAO** | `BookDAO` → `BookDAOLinkedList` | Separação de responsabilidades |
| **Factory Method** | `BookService.getDBBook()` | Criação flexível de objetos |
| **Interface Segregation** | `Listable<T>` | Contratos específicos |
| **Generic Types** | `LinkedList<T>`, `DoubleNode<T>` | Reutilização de código |

## 🔍 Características Técnicas

### Lista Dinâmica Duplamente Encadeada
- **Vantagens**: Crescimento dinâmico, inserção/remoção O(1)
- **Estrutura**: Nós com referências anterior e próxima
- **Comportamento**: Acesso sequencial e por posição

### Tratamento de Exceções
```java
// Lista vazia
NoSuchElementException: "Lista Vazia!"

// Elemento não encontrado
NoSuchElementException: "Elemento não encontrado!"

// Campos obrigatórios
IllegalArgumentException: "campo obrigatório não pode ser nulo!"
```

## 📈 Performance

| Operação | Complexidade | Descrição |
|----------|--------------|-----------|
| **add** | O(1) | Adicionar no início |
| **addLast** | O(1) | Adicionar no final |
| **remove** | O(n) | Remover por ID |
| **get** | O(n) | Buscar por ID |
| **update** | O(n) | Atualizar por ID |
| **isEmpty** | O(1) | Verificar se está vazia |
| **size** | O(1) | Retornar tamanho |
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
