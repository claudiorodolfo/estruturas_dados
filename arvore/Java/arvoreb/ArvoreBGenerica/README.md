# 🌳 Árvore B Genérica em Java

[![Build with Maven](https://img.shields.io/badge/build-Maven-blue)](https://maven.apache.org/)
[![JUnit Tested](https://img.shields.io/badge/tests-JUnit%204-green)](https://junit.org/junit4/)

Implementação genérica de uma Árvore B em Java, pronta para uso acadêmico, didático ou profissional. Suporta qualquer tipo que implemente `Comparable<T>`, com operações eficientes de inserção, busca, remoção e percursos.

---

## ✨ Características

- **Genérica:** Funciona com qualquer tipo comparável (`Comparable<T>`)
- **Balanceada:** Mantém a árvore sempre balanceada
- **Eficiente:** Operações em tempo logarítmico
- **Flexível:** Ordem configurável (padrão: 3)
- **Testada:** Cobertura de testes automatizados com JUnit
- **CLI:** Interface de linha de comando para uso rápido

---

## 📁 Estrutura do Projeto

```
ArvoreBGenerica/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Arborizavel.java
│   │       ├── NoArvoreB.java
│   │       ├── ArvoreB.java
│   │       └── ArvoreBGenericaMainCLI.java
│   └── test/
│       └── java/
│           └── ArvoreBGenericaTest.java
```

---

## 🚀 Como Usar

### 1. Compilar o projeto
```bash
mvn compile
```

### 2. Executar a interface de linha de comando (CLI)
```bash
mvn exec:java -Dexec.mainClass=ArvoreBGenericaMainCLI
```

### 3. Executar os testes automatizados
```bash
mvn test
```

---

## 🧑‍💻 Exemplo de Uso em Código

```java
// Criar uma árvore B com ordem 3
ArvoreB<String> arvore = new ArvoreB<>();

// Inserir elementos
arvore.inserir("banana");
arvore.inserir("abacaxi");
arvore.inserir("cereja");

// Verificar se existe
if (arvore.existe("banana")) {
    System.out.println("Banana encontrada!");
}

// Remover elemento
String removido = arvore.apagar("abacaxi");

// Imprimir em ordem
System.out.println(arvore.imprimirEmOrdem());
```

---

## 📝 Funcionalidades

- Inserção, busca e remoção de elementos
- Impressão em ordem, pré-ordem e pós-ordem
- Limpeza total da árvore
- Suporte a diferentes tipos de dados (`String`, `Integer`, `Double`, etc.)
- Testes de balanceamento e duplicidade

---

## 📚 Propriedades da Árvore B

- Todos os nós folha estão no mesmo nível
- Cada nó contém entre `ordem-1` e `2*ordem-1` chaves
- Cada nó interno tem entre `ordem` e `2*ordem` filhos
- As chaves em cada nó estão ordenadas

---

## 💡 Casos de Uso

- Sistemas de banco de dados
- Sistemas de arquivos
- Indexação de dados
- Estruturas de dados persistentes

---

## 👨‍🏫 Autor

Cláudio Rodolfo Sousa de Oliveira

---

## 🏷️ Licença

Este projeto é livre para fins acadêmicos e didáticos. 