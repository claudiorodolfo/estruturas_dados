# 📚 Estruturas de Dados

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8+-purple.svg)](https://kotlinlang.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> **Coleção completa de implementações de estruturas de dados em Java e Kotlin**  
> Repositório educacional com implementações práticas de algoritmos e estruturas de dados fundamentais.

## 🎯 Sobre o Projeto

Este repositório contém uma **coleção abrangente** de implementações de estruturas de dados em **Java** e **Kotlin**, desenvolvidas como parte do curso de Estruturas de Dados. Cada implementação inclui testes unitários, documentação completa e exemplos práticos.

## 📁 Estrutura do Repositório

```
estruturas_dados/
├── 📚 arvore/                    # Árvores
│   ├── Java/
│   │   ├── abp/                  # Árvore Binária de Pesquisa
│   │   ├── avl/                  # Árvore AVL
│   │   ├── avp/                  # Árvore Vermelha-Preta
│   │   └── arvoreb/              # Árvore B e B+
│   └── Kotlin/
├── 🔗 fila/                      # Filas
│   ├── dinamica/                 # Implementação dinâmica
│   └── estatica/                 # Implementação estática
├── 🗂️ hash/                       # Tabelas Hash
├── 📊 heap/                      # Heaps
├── 📋 lista/                     # Listas
│   ├── dinamica/                 # Lista dinâmica
│   └── estatica/                 # Lista estática
├── 🔄 ordenacao/                 # Algoritmos de Ordenação
│   ├── dinamica/                 # Ordenação dinâmica
│   └── estatica/                 # Ordenação estática
├── 📚 pilha/                     # Pilhas
│   ├── dinamica/                 # Pilha dinâmica
│   └── estatica/                 # Pilha estática
└── 📝 prova/                     # Provas e exercícios
```

## 🚀 Projetos Principais

### 📚 Pilha Dinâmica (LinkedStackDAOProject)
- **Localização**: `pilha/dinamica/Java/LinkedStackDAOProject/`
- **Descrição**: Sistema completo de gerenciamento de livros com pilha dinâmica
- **Tecnologias**: Java 21, Maven, JUnit 4
- **Características**: DAO Pattern, Testes Abrangentes, Arquitetura em Camadas

### 🌳 Árvores Binárias
- **ABP**: Árvore Binária de Pesquisa genérica
- **AVL**: Árvore AVL balanceada
- **AVP**: Árvore Vermelha-Preta
- **Árvore B**: Estrutura para sistemas de arquivos

### 🔗 Filas e Pilhas
- **Implementações Estáticas**: Arrays com tamanho fixo
- **Implementações Dinâmicas**: Listas encadeadas
- **Fila Dupla Terminação**: Inserção/remoção em ambas as extremidades

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| **Java** | 21 | Linguagem principal |
| **Kotlin** | 1.8+ | Linguagem alternativa |
| **Maven** | 3.6+ | Gerenciamento de dependências |
| **JUnit** | 4.13.2 | Testes unitários |
| **Hamcrest** | 1.3 | Asserções expressivas |

## 📋 Pré-requisitos

- ☕ **Java 21** ou superior
- 🔧 **Maven 3.6+** ou superior
- 🐘 **Kotlin 1.8+** (para projetos Kotlin)
- 💻 **IDE** (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Como Executar

### Compilar Todo o Projeto
```bash
# Na raiz do projeto
mvn clean compile
```

### Executar Projeto Específico
```bash
# Navegar para o projeto desejado
cd pilha/dinamica/Java/LinkedStackDAOProject

# Compilar e executar
mvn clean compile exec:java
```

### Executar Testes
```bash
# Testes de um projeto específico
cd pilha/dinamica/Java/LinkedStackDAOProject
mvn test

# Testes de todos os projetos
mvn test
```

## 📊 Estatísticas do Repositório

- **📁 Total de Projetos**: 50+
- **☕ Projetos Java**: 40+
- **🐘 Projetos Kotlin**: 10+
- **🧪 Testes Unitários**: 500+
- **📚 Estruturas Implementadas**: 20+

## 🎓 Estruturas de Dados Implementadas

### 📚 Pilhas (Stacks)
- ✅ Pilha Estática (Array)
- ✅ Pilha Dupla
- ✅ Pilha Dinâmica (Pilha Encadeada)
- ✅ Pilha com Filas

### 🔗 Filas (Queues)
- ✅ Fila Estática (Array)
- ✅ Fila Estática Circular
- ✅ Fila Dinâmica (Fila Encadeada)
- ✅ Fila com Dupla Terminação
- ✅ Fila com Pilhas

### 📋 Listas (Lists)
- ✅ Lista Estática (Array)
- ✅ Lista Estática Circular
- ✅ Lista Dinâmica (Lista Encadeada)
- ✅ Lista Dinâmica (Lista Duplamente Encadeada)

### 🌳 Árvores (Trees)
- ✅ Árvore Binária de Pesquisa (ABP)
- ✅ Árvore AVL
- ✅ Árvore Vermelha-Preta (AVP)
- ✅ Árvore B
- ✅ Árvore B+
- ✅ Heap Binário

### 🔍 Tabelas Hash (Hash Tables)
- ✅ HashMap com Espalhamento
- ✅ Resolução de Conflitos
- ✅ Rehashing

### 🔄 Algoritmos de Ordenação
- ✅ Bubble Sort
- ✅ Selection Sort
- ✅ Insertion Sort
- ✅ Merge Sort
- ✅ Quick Sort
- ✅ Heap Sort

## 🧪 Testes e Qualidade

### Cobertura de Testes
- **Testes Unitários**: JUnit 4
- **Testes de Integração**: Fluxos completos
- **Testes de Performance**: Algoritmos com grandes volumes
- **Testes de Edge Cases**: Casos extremos e exceções

### Padrões de Qualidade
- ✅ **Clean Code**: Código limpo e legível
- ✅ **SOLID Principles**: Princípios de design
- ✅ **Design Patterns**: Padrões de projeto
- ✅ **Documentation**: JavaDoc completo
- ✅ **Error Handling**: Tratamento de exceções

## 📈 Complexidade Algorítmica

| Estrutura | Inserção | Busca | Remoção | Espaço |
|-----------|----------|-------|---------|--------|
| **Pilha** | O(1) | O(1) | O(1) | O(n) |
| **Fila** | O(1) | O(1) | O(1) | O(n) |
| **ABP** | O(log n) | O(log n) | O(log n) | O(n) |
| **AVL** | O(log n) | O(log n) | O(log n) | O(n) |
| **Hash** | O(1) | O(1) | O(1) | O(n) |

## 🤝 Contribuição

1. **Fork** o projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. Abra um **Pull Request**

### Diretrizes de Contribuição
- 📝 Documente seu código
- 🧪 Adicione testes unitários
- 📚 Atualize a documentação
- ✅ Siga os padrões de código existentes

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Cláudio Rodolfo Sousa de Oliveira**
- 📧 Email: [claudiorodolfo@ifba.edu.br](mailto:claudiorodolfol@ifba.edu.br)
- 💼 LinkedIn: [Cláudio Rodolfo](https://linkedin.com/in/claudiorodolfo)
- 🐙 GitHub: [@claudiorodolfo](https://github.com/claudiorodolfo)

## 🏫 Instituição

**Instituto Federal da Bahia (IFBA)**  
*Curso: Bacharelado em Sistemas de Informação*  
*Disciplina: Estruturas de Dados*

## 🙏 Agradecimentos

- Instituto Federal da Bahia (IFBA)
- Professor de Estruturas de Dados
- Comunidade Java e Kotlin
- Contribuidores do projeto

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela! ⭐**

[![GitHub stars](https://img.shields.io/github/stars/claudiorodolfo/estruturas_dados?style=social)](https://github.com/claudiorodolfo/estruturas_dados)

**📚 Aprenda. Implemente. Contribua. 📚**

</div>
