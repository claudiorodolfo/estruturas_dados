# AVL Genérica - Projeto Maven

Este projeto implementa uma **Árvore AVL Genérica** em Java, estruturada no formato Maven.

## Descrição

A Árvore AVL (Adelson-Velsky e Landis) é uma árvore binária de pesquisa auto-balanceada. Ela mantém a propriedade de que a diferença de altura entre as subárvores esquerda e direita de qualquer nó não pode ser maior que 1. Esta implementação é genérica, permitindo armazenar qualquer tipo de dados que implemente `Comparable<T>`.

## Estrutura do Projeto

```
AVLGenerica/
├── pom.xml                          # Configuração Maven
├── README.md                        # Este arquivo
├── MIGRATION_GUIDE.md               # Guia de migração
├── .gitignore                       # Arquivos ignorados pelo Git
├── src/
│   ├── main/
│   │   ├── java/                    # Código fonte principal
│   │   │   ├── AVL.java            # Implementação da AVL
│   │   │   ├── AVLPrincipalCLI.java # Interface de linha de comando
│   │   │   ├── Arborizavel.java    # Interface da árvore
│   │   │   └── NoTriplo.java       # Classe do nó da árvore
│   │   └── resources/              # Recursos da aplicação
│   └── test/
│       ├── java/                   # Testes unitários
│       └── resources/              # Recursos para testes
└── target/                         # Arquivos gerados (ignorado pelo Git)
```

## Funcionalidades

- **Inserção**: Adiciona elementos mantendo o balanceamento da AVL
- **Remoção**: Remove elementos preservando o balanceamento
- **Busca**: Verifica se um elemento existe na árvore
- **Travessias**: Imprime a árvore em pré-ordem, em-ordem e pós-ordem
- **Limpeza**: Remove todos os elementos da árvore
- **Auto-balanceamento**: Mantém a árvore sempre balanceada

## Características da AVL

- **Altura balanceada**: A diferença de altura entre subárvores não excede 1
- **Operações eficientes**: Inserção, remoção e busca em O(log n)
- **Rotações**: Implementa rotações simples e duplas para manter o balanceamento
- **Fator de balanceamento**: Cada nó armazena seu fator de balanceamento

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
java -jar target/avl-generica-1.0.0.jar
```

### Geração de Documentação

```bash
mvn javadoc:javadoc
```

A documentação será gerada em `target/site/apidocs/`.

## Exemplo de Uso

Ao executar a aplicação, você verá um menu interativo:

```
=== Arvore Binaria AVL ===
0. SAIR
1. Inserir
2. Apagar
3. Existe?
4. Imprimir
5. Limpar
Escolha uma opcao (0-5):
```

### Sequência de Teste Recomendada

Para testar a funcionalidade, insira os valores nesta ordem: `4 2 6 1 3 5 7`

Resultados esperados:
- **Pré-ordem**: [4,2,1,3,6,5,7]
- **Em-ordem**: [1,2,3,4,5,6,7]
- **Pós-ordem**: [1,3,2,5,7,6,4]

### Demonstração do Auto-balanceamento

A AVL automaticamente rebalanceia a árvore após inserções e remoções:

1. Insira valores em sequência: `1, 2, 3, 4, 5, 6, 7`
2. Observe como a árvore se rebalanceia automaticamente
3. Use a opção 4 para ver as diferentes travessias

## Dependências

- **JUnit 4.13.2**: Para testes unitários
- **Hamcrest 1.3**: Para asserções mais expressivas nos testes

## Vantagens da AVL

1. **Balanceamento automático**: Mantém a árvore sempre balanceada
2. **Operações eficientes**: Todas as operações em O(log n)
3. **Previsibilidade**: Altura máxima limitada
4. **Adequada para aplicações críticas**: Garante performance consistente

## Licença

Este projeto é parte do repositório de estruturas de dados educacionais. 