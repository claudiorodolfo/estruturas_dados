# Projeto ED para Praticar

Projeto em Java para praticar **Estruturas de Dados** e operações de CRUD/consulta sobre entidades de domínio (`Livro` e `Carro`), usando estruturas **estáticas** e **dinâmicas** implementadas manualmente.

## Objetivo

Este projeto foi criado para exercitar:

- implementação de estruturas de dados (alocação em arrays e encadeamento);
- manipulação de dados por meio de DAOs;
- operações baseadas no CRUD (Criar, Ler, Atualizar, Apagar) e consultas específicas;
- organização em camadas (`model`, `repository`, `dao`, `app`).

## Tecnologias

- Java 21 (`maven.compiler.release` no `pom.xml`)
- Apache Maven (compilação e execução)
- Estruturas implementadas manualmente (sem frameworks de persistência)

## Estrutura do projeto

Layout Maven padrão; o código-fonte fica em `src/main/java`.

```text
projeto_ed_para_praticar/
├── pom.xml
└── src/main/java/
    ├── app/
    │   ├── MainLivro.java      # menu interativo (uso principal)
    │   └── MainCarro.java      # esqueleto (sem fluxo ainda)
    ├── dao/
    │   ├── LivroDAO.java
    │   ├── CarroDAO.java
    │   ├── livro/              # DAOs de Livro (pilha/fila/lista × estático/dinâmico)
    │   └── carro/              # DAOs de Carro (esqueleto; em construção)
    ├── model/
    │   ├── Livro.java
    │   └── Carro.java
    └── repository/
        ├── Empilhavel.java
        ├── Enfileiravel.java
        ├── DuplamenteEnfileiravel.java
        ├── Listavel.java
        ├── estaticas/
        │   ├── pilha/PilhaEstatica.java
        │   ├── fila/FilaEstatica.java
        │   ├── fila/FilaEstaticaComDuplaTerminacao.java
        │   └── lista/ListaEstatica.java
        └── dinamicas/
            ├── NoDuplo.java
            ├── pilha/PilhaDinamica.java
            ├── fila/FilaDinamica.java
            ├── fila/FilaDinamicaComDuplaTerminacao.java
            └── lista/ListaDinamica.java
```

## Estruturas de dados presentes

### Estáticas (`repository.estaticas`)

- **Pilha** (`PilhaEstatica`): empilhar, desempilhar, espiar, atualizar topo.
- **Fila circular** (`FilaEstatica`): enfileirar, desenfileirar, frente, atualizar início/fim.
- **DEQue circular** (`FilaEstaticaComDuplaTerminacao`): enfileirar no início, desenfileirar no fim, entre outras.
- **Lista circular** (`ListaEstatica`): inserir em posição, anexar, selecionar, atualizar, apagar.

### Dinâmicas (`repository.dinamicas`)

- **Pilha encadeada** (`PilhaDinamica`).
- **Fila encadeada** (`FilaDinamica`).
- **DEQue encadeado** (`FilaDinamicaComDuplaTerminacao`).
- **Lista duplamente encadeada** (`ListaDinamica`) com `NoDuplo`.

## Domínios modelados

- **Livro**: `id`, `titulo`, `autor`, `dataPublicacao`, `isbn`, `preco`, entre outros.
- **Carro**: `placa`, `marca`, `modelo`, `cor`, `nomeProprietario`, `chegada`, entre outros.

## Como executar

Use Maven a partir da pasta `projeto_ed_para_praticar`:

```bash
cd projeto_ed_para_praticar
mvn compile
```

Executar o menu de livros (classe principal configurada no `exec-maven-plugin`):

```bash
mvn exec:java
```

Equivalente explícito:

```bash
mvn exec:java -Dexec.mainClass=app.MainLivro
```

Após `mvn compile`, também é possível rodar com o classpath gerado em `target/classes`:

```bash
java -cp target/classes app.MainLivro
```

Para outra classe principal (por exemplo `MainCarro`, quando houver implementação):

```bash
mvn exec:java -Dexec.mainClass=app.MainCarro
```

## Menu atual (`MainLivro`)

O menu permite, entre outras ações:

- carregar exemplos;
- cadastrar e listar livros;
- buscar por ID, ISBN, autor, título e datas;
- buscar por faixa de preço e faixa de datas;
- atualizar e remover livros;
- estatísticas (preço médio, mais caro/barato, mais novo/antigo);
- limpar a base em memória.

## Alternando a estratégia de armazenamento de `Livro`

No arquivo `app/MainLivro.java`, troque a instância do `LivroDAO` no início do `main`:

- **Estáticas**: `LivroDAOPilhaEstatica`, `LivroDAOFilaEstatica`, `LivroDAOListaEstatica`.
- **Dinâmicas**: `LivroDAOPilhaDinamica`, `LivroDAOFilaDinamica`, `LivroDAOListaDinamica`.

Descomente uma linha e comente as demais para escolher a estrutura subjacente.

## Status do projeto

- **DAOs de `Livro`**: implementam `LivroDAO` nas variantes pilha/fila/lista (estático e dinâmico).
- **DAOs de `Carro`**: classes-base para prática; ainda não implementam `CarroDAO` de forma completa (conforme comentários no código).
- **`MainCarro`**: arquivo presente, porém sem menu ou cenários de teste — próximo passo natural após implementar os DAOs de carro.

## Passos sugeridos

- Implementar os métodos da interface `CarroDAO` nas classes em `dao/carro`, escolhendo pilha/fila/lista estática ou dinâmica conforme o exercício.
- Preencher `MainCarro` com um fluxo semelhante ao de `MainLivro` para validar cada implementação.
