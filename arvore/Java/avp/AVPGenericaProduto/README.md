# AVP Genérica Produto - Árvore Vermelho-Preto para Produtos

Implementação de uma Árvore Vermelho-Preto (Red-Black Tree) genérica em Java, especializada para trabalhar com objetos do tipo `Produto`, que mantém o balanceamento através de propriedades específicas de cores dos nós.

## 📋 Descrição

A Árvore Vermelho-Preto é uma árvore binária de pesquisa auto-balanceável que garante que a altura da árvore seja sempre O(log n), garantindo operações eficientes de inserção, remoção e busca. Esta implementação é especializada para trabalhar com objetos `Produto`, que possuem código de barras, nome e preço.

### Propriedades da Árvore Vermelho-Preto

1. **Raiz é preta**: A raiz da árvore é sempre preta
2. **Folhas são pretas**: Todos os nós folha (null) são considerados pretos
3. **Nós vermelhos têm filhos pretos**: Se um nó é vermelho, seus filhos são pretos
4. **Caminhos têm mesma quantidade de nós pretos**: Todos os caminhos da raiz até as folhas têm o mesmo número de nós pretos

## 🏗️ Estrutura do Projeto

```
AVPGenericaProduto/
├── src/
│   ├── main/java/
│   │   ├── Arborizavel.java      # Interface para estruturas arbóreas
│   │   ├── AVP.java              # Implementação da Árvore Vermelho-Preto
│   │   ├── AVPProdutoMainCLI.java # Interface de linha de comando
│   │   ├── Cor.java              # Enum para cores dos nós
│   │   ├── NoTriplo.java         # Nó da árvore com referência tripla
│   │   └── Produto.java          # Classe Produto com código de barras
│   └── test/java/
│       └── AVPProdutoTest.java   # Testes unitários
├── pom.xml                       # Configuração Maven
├── .gitignore                    # Arquivos ignorados pelo Git
└── README.md                     # Este arquivo
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
mvn exec:java
```

### Geração do JAR Executável

```bash
mvn package
java -jar target/avp-generica-produto-1.0.0.jar
```

### Geração da Documentação

```bash
mvn javadoc:javadoc
```

## 📚 Classes Principais

### `AVP<T extends Comparable<T>>`

Classe principal que implementa a Árvore Vermelho-Preto genérica.

**Métodos principais:**
- `inserir(T elemento)`: Insere um elemento na árvore
- `remover(T elemento)`: Remove um elemento da árvore
- `buscar(T elemento)`: Busca um elemento na árvore
- `imprimir()`: Imprime a árvore em ordem
- `imprimirPreOrdem()`: Imprime a árvore em pré-ordem
- `imprimirPosOrdem()`: Imprime a árvore em pós-ordem

### `Produto`

Classe que representa um produto com:
- `codigoBarras`: String única do produto
- `nome`: Nome do produto
- `preco`: Preço do produto

**Métodos principais:**
- `compareTo(Produto outro)`: Compara produtos pelo código de barras
- `equals(Object obj)`: Verifica igualdade pelo código de barras
- `hashCode()`: Hash baseado no código de barras
- `toString()`: Representação string do produto

### `NoTriplo<T>`

Representa um nó da árvore com:
- Dado do nó
- Referência para o filho esquerdo
- Referência para o filho direito
- Cor do nó (vermelho ou preto)

### `Cor`

Enum que define as cores possíveis para os nós:
- `VERMELHO`
- `PRETO`

### `Arborizavel<T>`

Interface que define as operações básicas para estruturas arbóreas:
- `inserir(T elemento)`
- `remover(T elemento)`
- `buscar(T elemento)`

## 🔧 Operações da Árvore Vermelho-Preto

### Inserção

1. Insere o novo nó como em uma árvore binária de pesquisa normal
2. Colore o novo nó de vermelho
3. Aplica as regras de balanceamento para manter as propriedades da árvore

### Remoção

1. Remove o nó como em uma árvore binária de pesquisa normal
2. Aplica as regras de balanceamento para manter as propriedades da árvore

### Balanceamento

O balanceamento é realizado através de:
- **Rotações**: Esquerda e direita
- **Recoloração**: Mudança de cores dos nós
- **Propriedades**: Garantia das propriedades da árvore vermelho-preto

## 🧪 Testes

O projeto inclui testes unitários abrangentes que verificam:

- Inserção de produtos
- Remoção de produtos
- Busca de produtos
- Impressão em diferentes ordens
- Balanceamento da árvore
- Propriedades da árvore vermelho-preto
- Comparação de produtos por código de barras

Para executar os testes:

```bash
mvn test
```

## 📊 Complexidade

- **Inserção**: O(log n)
- **Remoção**: O(log n)
- **Busca**: O(log n)
- **Espaço**: O(n)

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

**Nota**: Esta implementação é educacional e pode ser usada como referência para entender como funcionam as Árvores Vermelho-Preto com objetos complexos como Produtos. 