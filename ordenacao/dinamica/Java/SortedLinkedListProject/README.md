# Projeto de Lista Dinâmica Ordenada

## 📋 Descrição

Projeto Java que implementa algoritmos de ordenação utilizando uma estrutura de dados dinâmica baseada em lista duplamente encadeada. O projeto oferece uma aplicação interativa para testar diferentes algoritmos de ordenação (Bubble Sort, Insertion Sort e Selection Sort) com suporte para ordenação crescente (ASC) e decrescente (DESC).

## ✨ Características

- **Lista Duplamente Encadeada**: Implementação dinâmica com nós duplamente encadeados
- **Múltiplos Algoritmos de Ordenação**: 
  - Bubble Sort
  - Insertion Sort
  - Selection Sort
- **Ordenação Bidirecional**: Suporte para ordem crescente (ASC) e decrescente (DESC)
- **Aplicação Interativa**: Interface de linha de comando para testar os algoritmos
- **Testes Pré-definidos**: Conjunto de testes automatizados para validação

## 🏗️ Estrutura do Projeto

```
SortedLinkedListProject/
├── src/
│   ├── main/java/
│   │       └── br/edu/ifba/vdc/bsi/sortedlinkedlist/
│   │           ├── app/
│   │           │   └── SortedLinkedListApp.java      # Aplicação principal interativa
│   │           └── dao/repository/list/
│   │               ├── LinkedList.java                # Implementação da lista duplamente encadeada
│   │               ├── DoubleNode.java                # Nó da lista duplamente encadeada
│   │               ├── Listable.java                  # Interface para listas
│   │               ├── OverflowException.java         # Exceção para lista cheia
│   │               ├── UnderflowException.java        # Exceção para lista vazia
│   │               └── sorting/
│   │                   ├── SortedLinkedList.java      # Lista ordenável
│   │                   ├── Sortable.java              # Interface para estruturas ordenáveis
│   │                   ├── SortAlgorithm.java         # Enum dos algoritmos disponíveis
│   │                   └── SortOrder.java             # Enum da ordem de ordenação
│   └── test/java/
│           └── br/edu/ifba/vdc/bsi/sortedlinkedlist/
│               └── SortedLinkedListTest.java          # Testes unitários
├── pom.xml                                            # Configuração Maven
└── README.md                                          # Este arquivo
```

## 📦 Requisitos

- **Java**: JDK 25 ou superior
- **Maven**: 3.6.0 ou superior (para compilação e gerenciamento de dependências)
- **JUnit**: 5.9.2 (incluído via Maven)

## 🚀 Como Compilar e Executar

### Compilação

```bash
# Compilar o projeto
mvn clean compile

# Compilar e executar os testes
mvn test

# Gerar o JAR executável
mvn clean package
```

### Execução

```bash
# Executar a aplicação interativa
mvn exec:java -Dexec.mainClass="br.edu.ifba.vdc.bsi.sortedlinkedlist.app.SortedLinkedListApp"

# Ou após gerar o JAR
java -cp target/sorted-linked-list-1.0.0.jar br.edu.ifba.vdc.bsi.sortedlinkedlist.app.SortedLinkedListApp
```

## 💻 Como Usar

### Aplicação Interativa

Ao executar a aplicação, você terá acesso a um menu interativo com as seguintes opções:

1. **Inserir valor na lista**: Adiciona um único valor
2. **Inserir múltiplos valores**: Adiciona vários valores de uma vez (separados por espaço)
3. **Visualizar lista atual**: Exibe o estado atual da lista
4. **Ordenar lista**: Permite escolher o algoritmo e a ordem de ordenação
5. **Limpar lista**: Remove todos os elementos
6. **Carregar lista de teste padrão**: Carrega uma lista pré-definida para testes
7. **Executar testes pré-definidos**: Executa testes automatizados
0. **Sair**: Encerra a aplicação

### Exemplo de Uso Programático

```java
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortedLinkedList;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortOrder;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortAlgorithm;

// Criar uma lista ordenável
SortedLinkedList list = new SortedLinkedList();

// Adicionar elementos
list.append(64);
list.append(34);
list.append(25);
list.append(12);
list.append(22);

// Ordenar em ordem crescente usando Bubble Sort
list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
System.out.println(list.print()); // [12,22,25,34,64]

// Ordenar em ordem decrescente usando Insertion Sort
list.sort(SortOrder.DESC, SortAlgorithm.INSERTION_SORT);
System.out.println(list.print()); // [64,34,25,22,12]

// Ordenar usando Selection Sort (ordem crescente por padrão)
list.sort(SortOrder.ASC, SortAlgorithm.SELECTION_SORT);
System.out.println(list.print()); // [12,22,25,34,64]
```

## 🔧 Algoritmos Implementados

### Bubble Sort
- **Complexidade**: O(n²)  em todos os casos (tem como melhorá-lo para se tornar O(n) no melhor caso
- **Descrição**: Compara elementos adjacentes e os troca se estiverem na ordem errada, repetindo até que a lista esteja ordenada.

### Insertion Sort
- **Complexidade**: O(n²) no pior caso, O(n) no melhor caso
- **Descrição**: Constrói a lista ordenada um elemento por vez, inserindo cada novo elemento na posição correta. Da posição do elemento até o ínicio (configuração padrão), os elementos já estão ordenados.

### Selection Sort
- **Complexidade**: O(n²) em todos os casos
- **Descrição**: Encontra o menor (configuração padrão) ou maior elemento e o coloca na posição correta, repetindo para os elementos restantes.

## 📚 Estrutura de Classes

### Classes Principais

- **`SortedLinkedList`**: Estende `LinkedList` e implementa `Sortable`, fornecendo métodos de ordenação
- **`LinkedList<T>`**: Implementação genérica de lista duplamente encadeada
- **`DoubleNode<T>`**: Representa um nó da lista com referências para o próximo e anterior
- **`SortedLinkedListApp`**: Aplicação interativa para testar os algoritmos

### Interfaces e Enums

- **`Sortable`**: Interface que define métodos de ordenação
- **`Listable<T>`**: Interface que define operações básicas de lista
- **`SortAlgorithm`**: Enum com os algoritmos disponíveis (BUBBLE_SORT, INSERTION_SORT, SELECTION_SORT)
- **`SortOrder`**: Enum com as ordens disponíveis (ASC, DESC)

## 🧪 Testes

O projeto inclui testes pré-definidos que podem ser executados através da aplicação interativa ou via JUnit:

```bash
# Executar testes unitários
mvn test
```

Os testes cobrem:
- Ordenação crescente e decrescente para cada algoritmo
- Casos especiais (lista vazia, lista com um elemento, lista já ordenada)
- Validação de resultados esperados

## 📝 Notas Técnicas

- A lista utiliza uma estrutura duplamente encadeada, permitindo navegação bidirecional
- A busca por índice é otimizada: se o índice estiver na primeira metade, a busca começa do início; caso contrário, começa do fim
- A lista possui capacidade máxima configurável (padrão: 10 elementos)
- Todas as operações validam limites e lançam exceções apropriadas

## 👨‍💻 Autor

Projeto desenvolvido como parte do curso de Estruturas de Dados.

## 📄 Licença

Este projeto é disponibilizado para fins educacionais.

## 🔄 Versão

**Versão**: 1.0.0

---

Para mais informações, consulte a documentação JavaDoc no código-fonte.

