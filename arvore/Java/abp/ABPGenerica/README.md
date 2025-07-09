# ABP Genérica - Projeto Maven

Este projeto implementa uma **Árvore Binária de Pesquisa (ABP) Genérica** em Java, estruturada no formato Maven.

## Descrição

A ABP (Árvore Binária de Pesquisa) é uma estrutura de dados hierárquica onde cada nó tem no máximo dois filhos, e os valores menores ficam à esquerda e os maiores à direita. Esta implementação é genérica, permitindo armazenar qualquer tipo de dados que implemente `Comparable<T>`.

## Estrutura do Projeto

```
ABPGenerica/
├── pom.xml                          # Configuração Maven
├── README.md                        # Este arquivo
├── MIGRATION_GUIDE.md               # Guia de migração
├── .gitignore                       # Arquivos ignorados pelo Git
├── src/
│   ├── main/
│   │   ├── java/                    # Código fonte principal
│   │   │   ├── ABP.java            # Implementação da ABP
│   │   │   ├── ABPMainCLI.java     # Interface de linha de comando
│   │   │   ├── Arborizavel.java    # Interface da árvore
│   │   │   └── NoTriplo.java       # Classe do nó da árvore
│   │   └── resources/              # Recursos da aplicação
│   └── test/
│       ├── java/                   # Testes unitários
│       │   └── ABPTest.java        # Testes da ABP
│       └── resources/              # Recursos para testes
└── target/                         # Arquivos gerados (ignorado pelo Git)
```

## Funcionalidades

- **Inserção**: Adiciona elementos mantendo a propriedade da ABP
- **Remoção**: Remove elementos preservando a estrutura da árvore
- **Busca**: Verifica se um elemento existe na árvore
- **Travessias**: Imprime a árvore em pré-ordem, em-ordem e pós-ordem
- **Limpeza**: Remove todos os elementos da árvore

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
java -jar target/abp-generica-1.0.0.jar
```

### Geração de Documentação

```bash
mvn javadoc:javadoc
```

A documentação será gerada em `target/site/apidocs/`.

## Exemplo de Uso

Ao executar a aplicação, você verá um menu interativo:

```
=== Arvore Binaria de Pesquisa ===
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

## Dependências

- **JUnit 4.13.2**: Para testes unitários
- **Hamcrest 1.3**: Para asserções mais expressivas nos testes

## Licença

Este projeto é parte do repositório de estruturas de dados educacionais. 