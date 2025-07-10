# Árvore B+ (B Mais) - Implementação em Java

Implementação de uma árvore B+ genérica em Java, seguindo o padrão Maven, com nomes e comentários em português.

## 📋 Descrição

A árvore B+ (B Mais) é uma estrutura de dados que mantém dados ordenados e permite busca, inserção e remoção eficientes. É uma variação da árvore B que mantém todos os dados nas folhas e usa uma lista encadeada para navegação sequencial.

### Características da Árvore B+

1. **Todos os dados estão nas folhas**: Apenas os nós folha contêm valores
2. **Nós internos**: Contêm apenas chaves para navegação
3. **Lista encadeada**: Os nós folha são conectados para navegação sequencial
4. **Balanceamento**: Todos os nós folha estão no mesmo nível
5. **Ordem**: Cada nó tem entre ⌈m/2⌉ e m chaves (onde m é a ordem da árvore)

## 🏗️ Estrutura do Projeto

```
ArvoreBPlus/
├── src/
│   ├── main/java/com/estruturasdados/
│   │   ├── ArvoreBMais.java      # Classe principal da árvore B+
│   │   ├── NoBMais.java         # Nó da árvore B+
│   │   └── ArvoreBMaisPrincipal.java  # Classe principal com demo
│   └── test/java/com/estruturasdados/
│       └── ArvoreBMaisTeste.java  # Testes unitários
├── pom.xml                     # Configuração Maven
├── .gitignore                  # Arquivos ignorados pelo Git
├── README.md                   # Este arquivo
```

## 🚀 Como Usar

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6 ou superior

### Compilação

```bash
mvn clean compile
```

### Execução dos Testes

```bash
mvn test
```

### Execução da Aplicação

```bash
mvn exec:java -Dexec.mainClass="com.estruturasdados.ArvoreBMaisPrincipal"
```

### Geração do JAR Executável

```bash
mvn package
java -jar target/ArvoreBPlus-1.0.0.jar
```

## 📚 Classes Principais

### `ArvoreBMais<C extends Comparable<C>, V>`

Classe principal que implementa a árvore B+ genérica.

**Construtor:**
```java
ArvoreBMais<Integer, String> arvore = new ArvoreBMais<>(3); // Ordem 3
```

**Métodos principais:**
- `inserir(C chave, V valor)`: Insere uma chave-valor na árvore
- `buscar(C chave)`: Busca um valor pela chave
- `remover(C chave)`: Remove uma chave-valor da árvore
- `obterTodosOsValores()`: Retorna todos os valores em ordem
- `tamanho()`: Retorna o número de elementos
- `estaVazia()`: Verifica se a árvore está vazia
- `limpar()`: Remove todos os elementos

### `NoBMais<C, V>`

Representa um nó da árvore B+.

**Propriedades:**
- `chaves`: Lista de chaves do nó
- `valores`: Lista de valores (apenas para nós folha)
- `filhos`: Lista de filhos (apenas para nós internos)
- `proximo`: Referência para o próximo nó folha
- `folha`: Indica se o nó é uma folha

### `ArvoreBMaisPrincipal`

Classe principal com interface de linha de comando para demonstrar o uso da árvore B+.

## 🔧 Operações da Árvore B+

### Inserção

1. **Buscar a folha apropriada**: Percorrer a árvore até encontrar a folha onde inserir
2. **Inserir na folha**: Adicionar a chave-valor na posição correta

### Busca

1. **Percorrer até a folha**: Usar as chaves dos nós internos para navegar
2. **Buscar na folha**: Procurar a chave na folha encontrada

### Remoção

1. **Encontrar a folha**: Localizar a folha que contém a chave
2. **Remover da folha**: Remover a chave-valor

### Navegação Sequencial

- **obterTodosOsValores()**: Percorre todas as folhas
- **Eficiência**: O(1) para acessar o próximo elemento

## 📊 Complexidade

- **Inserção**: O(log n)
- **Busca**: O(log n)
- **Remoção**: O(log n)
- **Navegação sequencial**: O(1) por elemento
- **Espaço**: O(n)

## 🧪 Testes

O projeto inclui testes unitários abrangentes que verificam:

- Inserção e busca de elementos
- Remoção de elementos
- Navegação sequencial
- Operações em árvore vazia
- Tamanho e estado da árvore

Para executar os testes:

```bash
mvn test
```

## 💡 Exemplo de Uso

```java
// Criar uma árvore B+ de ordem 3
ArvoreBMais<Integer, String> arvore = new ArvoreBMais<>(3);

// Inserir elementos
arvore.inserir(10, "dez");
arvore.inserir(5, "cinco");
arvore.inserir(15, "quinze");

// Buscar elementos
String valor = arvore.buscar(10); // Retorna "dez"

// Remover elementos
String removido = arvore.remover(5); // Retorna "cinco"

// Navegar sequencialmente
List<String> todosValores = arvore.obterTodosOsValores();
// Retorna ["cinco", "dez", "quinze"]
```

## 🎯 Vantagens da Árvore B+

1. **Eficiência**: Todas as operações são O(log n)
2. **Navegação sequencial**: Acesso eficiente a elementos em ordem
3. **Balanceamento**: Altura uniforme em todos os caminhos
4. **Uso em disco**: Estrutura ideal para sistemas de arquivos e bancos de dados
5. **Flexibilidade**: Ordem configurável para otimizar performance

## 🤝 Contribuição

Para contribuir com o projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👨‍💻 Autor

Desenvolvido como parte do estudo de estruturas de dados em Java.

---

**Nota**: Esta implementação é educacional e pode ser usada como referência para entender como funcionam as árvores B+ e suas aplicações em sistemas de armazenamento. 