# 🛒 Árvore B Genérica de Produtos em Java

[![Build with Maven](https://img.shields.io/badge/build-Maven-blue)](https://maven.apache.org/)
[![JUnit Tested](https://img.shields.io/badge/tests-JUnit%204-green)](https://junit.org/junit4/)

Implementação genérica de uma Árvore B para manipulação de produtos em Java. Suporta qualquer tipo de produto que implemente `Comparable<T>`, com operações eficientes de inserção, busca, remoção e percursos.

---

## ✨ Características

- **Genérica:** Funciona com qualquer tipo de produto comparável (`Comparable<T>`)  
- **Balanceada:** Mantém a árvore sempre balanceada  
- **Eficiente:** Operações em tempo logarítmico  
- **Flexível:** Ordem configurável (padrão: 3)  
- **Testada:** Cobertura de testes automatizados com JUnit  
- **CLI:** Interface de linha de comando para uso rápido

---

## 📁 Estrutura do Projeto

```
ArvoreBGenericaProduto/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Arborizavel.java
│   │       ├── NoArvoreB.java
│   │       ├── ArvoreB.java
│   │       ├── Produto.java
│   │       └── ArvoreBProdutoMainCLI.java
│   └── test/
│       └── java/
│           └── ArvoreBProdutoTest.java
```

---

## 🚀 Como Usar

### 1. Compilar o projeto
```bash
mvn compile
```

### 2. Executar a interface de linha de comando (CLI)
```bash
mvn exec:java -Dexec.mainClass=ArvoreBProdutoMainCLI
```

### 3. Executar os testes automatizados
```bash
mvn test
```

---

## 🧑‍💻 Exemplo de Uso em Código

```java
// Criar uma árvore B de produtos
ArvoreB<Produto> arvore = new ArvoreB<>();

// Inserir produtos
arvore.inserir(new Produto("Caneta", 2.50));
arvore.inserir(new Produto("Lápis", 1.20));
arvore.inserir(new Produto("Borracha", 0.80));

// Verificar se existe
if (arvore.existe(new Produto("Caneta", 2.50))) {
    System.out.println("Caneta encontrada!");
}

// Remover produto
Produto removido = arvore.apagar(new Produto("Lápis", 1.20));

// Imprimir em ordem
System.out.println(arvore.imprimirEmOrdem());
```

---

## 📝 Funcionalidades

- Inserção, busca e remoção de produtos
- Impressão em ordem, pré-ordem e pós-ordem
- Limpeza total da árvore
- Suporte a diferentes tipos de produtos
- Testes de balanceamento e duplicidade

---

## 📚 Propriedades da Árvore B

- Todos os nós folha estão no mesmo nível
- Cada nó contém entre `ordem-1` e `2*ordem-1` chaves
- Cada nó interno tem entre `ordem` e `2*ordem` filhos
- As chaves em cada nó estão ordenadas

---

## 💡 Casos de Uso

- Estoques e inventários
- Catálogos de produtos
- Sistemas de busca e ordenação
- Indexação de dados

---

## 👨‍🏫 Autor

Cláudio Rodolfo Sousa de Oliveira

---

## 🏷️ Licença

Este projeto é livre para fins acadêmicos e didáticos. 