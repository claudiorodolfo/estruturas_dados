# AVL Genérica Produto - Projeto Maven

Este projeto implementa uma **Árvore AVL Genérica para Produtos** em Java, estruturada no formato Maven.

## Descrição

A Árvore AVL (Adelson-Velsky e Landis) é uma árvore binária de pesquisa auto-balanceada que mantém a propriedade de que a diferença de altura entre as subárvores esquerda e direita de qualquer nó não pode ser maior que 1. Esta implementação é específica para gerenciar produtos com código de barras, permitindo operações eficientes de inserção, busca e remoção com balanceamento automático.

## Estrutura do Projeto

```
AVLGenericaProduto/
├── pom.xml                          # Configuração Maven
├── README.md                        # Este arquivo
├── MIGRATION_GUIDE.md               # Guia de migração
├── .gitignore                       # Arquivos ignorados pelo Git
├── src/
│   ├── main/
│   │   ├── java/                    # Código fonte principal
│   │   │   ├── AVL.java            # Implementação da AVL
│   │   │   ├── AVLProdutoMainCLI.java # Interface de linha de comando
│   │   │   ├── Arborizavel.java    # Interface da árvore
│   │   │   ├── NoTriplo.java       # Classe do nó da árvore
│   │   │   └── Produto.java        # Classe do produto
│   │   └── resources/              # Recursos da aplicação
│   └── test/
│       ├── java/                   # Testes unitários
│       │   └── AVLProdutoTest.java # Testes da AVL de produtos
│       └── resources/              # Recursos para testes
└── target/                         # Arquivos gerados (ignorado pelo Git)
```

## Funcionalidades

- **Inserção de Produtos**: Adiciona produtos mantendo o balanceamento da AVL
- **Busca de Produtos**: Busca produtos por código de barras
- **Remoção de Produtos**: Remove produtos preservando o balanceamento
- **Travessias**: Imprime produtos em pré-ordem, em-ordem e pós-ordem
- **Limpeza**: Remove todos os produtos da árvore
- **Auto-balanceamento**: Mantém a árvore sempre balanceada

## Classe Produto

A classe `Produto` implementa `Comparable<Produto>` e possui:
- **Nome**: String identificadora do produto
- **Código de Barras**: Long usado como chave única para ordenação
- **Validações**: Verifica se código de barras é positivo e nome não é vazio
- **Equals/HashCode**: Baseados no código de barras
- **Comparable**: Ordenação por código de barras

## Características da AVL

- **Altura balanceada**: A diferença de altura entre subárvores não excede 1
- **Operações eficientes**: Inserção, remoção e busca em O(log n)
- **Rotações**: Implementa rotações simples e duplas para manter o balanceamento
- **Fator de balanceamento**: Cada nó armazena sua altura para cálculo de balanceamento
- **Performance garantida**: Todas as operações em O(log n)

## Pré-requisitos

- Java 21 ou superior
- Maven 3.6 ou superior

## Como Usar

### Compilação

```bash
mvn compile
```

### Execução dos Testes

```bash
mvn test
```

### Execução da Aplicação

```bash
mvn exec:java
```

### Geração de JAR Executável

```bash
mvn package
```

### Execução do JAR

```bash
java -jar target/avl-generica-produto-1.0.0.jar
```

### Geração de Documentação

```bash
mvn javadoc:javadoc
```

A documentação será gerada em `target/site/apidocs/`.

## Exemplo de Uso

Ao executar a aplicação, você verá um menu interativo:

```
==== Árvore AVL de Produtos ====
1. Inserir produto
2. Buscar produto
3. Remover produto
4. Imprimir produtos em ordem
5. Imprimir produtos em pré-ordem
6. Imprimir produtos em pós-ordem
7. Limpar árvore
0. Sair
```

### Exemplo de Operações

1. **Inserir Produto**:
   - Nome: "Café"
   - Código: 1001
   - Resultado: Produto inserido: Produto(Café,1001)

2. **Inserir Outro Produto**:
   - Nome: "Leite"
   - Código: 1002
   - Resultado: Produto inserido: Produto(Leite,1002)

3. **Buscar Produto**:
   - Código: 1001
   - Resultado: Produto encontrado: Produto(Café,1001)

4. **Listar Produtos**:
   - Resultado: Produtos em ordem crescente por código de barras

## Validações Implementadas

### Classe Produto
- **Nome**: Não pode ser nulo ou vazio
- **Código de Barras**: Deve ser positivo
- **Equals/HashCode**: Baseados no código de barras
- **Comparable**: Ordenação por código de barras

### Tratamento de Erros
- **Entradas Inválidas**: Validação de números e strings
- **Produtos Inexistentes**: Mensagens claras para busca/remoção
- **Exceções**: Captura e exibe erros de forma amigável

## Testes Implementados

### Casos de Teste
- Inserção e busca de produtos
- Remoção de folhas, nós com um filho e dois filhos
- Operações em árvore vazia
- Inserção de produtos duplicados
- Balanceamento da árvore AVL
- Impressão em diferentes ordens
- Limpeza da árvore

## Dependências

- **JUnit 4.13.2**: Para testes unitários
- **Hamcrest 1.3**: Para asserções mais expressivas nos testes

## Vantagens da AVL

1. **Performance garantida**: Todas as operações em O(log n)
2. **Balanceamento automático**: Não requer intervenção manual
3. **Eficiência**: Menos rotações que árvores rubro-negras
4. **Simplicidade**: Algoritmos de rotação bem definidos
5. **Adequada para aplicações críticas**: Garante performance consistente

## Licença

Este projeto é parte do repositório de estruturas de dados educacionais. 