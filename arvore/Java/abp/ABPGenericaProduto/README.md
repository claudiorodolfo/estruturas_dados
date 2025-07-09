# ABP Genérica Produto - Projeto Maven

Este projeto implementa uma **Árvore Binária de Pesquisa (ABP) Genérica para Produtos** em Java, estruturada no formato Maven.

## Descrição

A ABP (Árvore Binária de Pesquisa) é uma estrutura de dados hierárquica onde cada nó tem no máximo dois filhos, e os valores menores ficam à esquerda e os maiores à direita. Esta implementação é específica para gerenciar produtos com código de barras, permitindo operações eficientes de inserção, busca e remoção.

## Estrutura do Projeto

```
ABPGenericaProduto/
├── pom.xml                          # Configuração Maven
├── README.md                        # Este arquivo
├── MIGRATION_GUIDE.md               # Guia de migração
├── .gitignore                       # Arquivos ignorados pelo Git
├── src/
│   ├── main/
│   │   ├── java/                    # Código fonte principal
│   │   │   ├── ABP.java            # Implementação da ABP
│   │   │   ├── ABPProdutoMainCLI.java # Interface de linha de comando
│   │   │   ├── Arborizavel.java    # Interface da árvore
│   │   │   ├── NoTriplo.java       # Classe do nó da árvore
│   │   │   └── Produto.java        # Classe do produto
│   │   └── resources/              # Recursos da aplicação
│   └── test/
│       ├── java/                   # Testes unitários
│       │   └── ABPProdutoTest.java # Testes da ABP de produtos
│       └── resources/              # Recursos para testes
└── target/                         # Arquivos gerados (ignorado pelo Git)
```

## Funcionalidades

- **Inserção de Produtos**: Adiciona produtos mantendo a propriedade da ABP
- **Busca de Produtos**: Busca produtos por código de barras
- **Remoção de Produtos**: Remove produtos preservando a estrutura da árvore
- **Listagem Ordenada**: Imprime produtos em ordem crescente por código de barras
- **Limpeza**: Remove todos os produtos da árvore

## Classe Produto

A classe `Produto` implementa `Comparable<Produto>` e possui:
- **Nome**: String identificadora do produto
- **Código de Barras**: Long usado como chave única para ordenação
- **Validações**: Verifica se código de barras é positivo e nome não é vazio

## Pré-requisitos

- Java 11 ou superior
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
java -jar target/abp-generica-produto-1.0.0.jar
```

### Geração de Documentação

```bash
mvn javadoc:javadoc
```

A documentação será gerada em `target/site/apidocs/`.

## Exemplo de Uso

Ao executar a aplicação, você verá um menu interativo:

```
==== Árvore Binária de Produtos ====
1. Inserir produto
2. Buscar produto
3. Remover produto
4. Imprimir produtos em ordem
5. Limpar árvore
0. Sair
```

### Exemplo de Operações

1. **Inserir Produto**:
   - Nome: "Notebook Dell"
   - Código: 123456789

2. **Inserir Outro Produto**:
   - Nome: "Mouse Logitech"
   - Código: 987654321

3. **Buscar Produto**:
   - Código: 123456789
   - Resultado: Produto encontrado: Produto(Notebook Dell,123456789)

4. **Listar Produtos**:
   - Resultado: Produtos em ordem crescente por código de barras

## Dependências

- **JUnit 4.13.2**: Para testes unitários
- **Hamcrest 1.3**: Para asserções mais expressivas nos testes

## Licença

Este projeto é parte do repositório de estruturas de dados educacionais. 