# Projeto ED para Praticar

Projeto em Java para praticar **Estruturas de Dados** e a aplicação de operações de CRUD/consulta sobre entidades de domínio (`Livro` e `Carro`) usando diferentes estruturas estáticas.

## Objetivo

Este projeto foi criado para exercitar:

- implementação de estruturas de dados;
- manipulação de dados por meio de DAOs;
- operações baseadas no CRUD (Criar, Ler, Atualizar, Apagar);
- organização em camadas simples (`model`, `repository`, `dao`, `app`).

## Tecnologias

- Java (JDK 8+)
- Estruturas implementadas manualmente (sem frameworks)

## Estrutura do projeto

```text
projeto_ed_para_praticar/
├── app/
│   └── MainLivro.java
├── dao/
│   ├── LivroDAO.java
│   ├── LivroDAOPilhaEstatica.java
│   ├── LivroDAOFilaEstatica.java
│   ├── LivroDAOListaEstatica.java
│   ├── CarroDAO.java
│   ├── CarroDAOPilhaEstatica.java
│   ├── CarroDAOFilaEstatica.java
│   └── CarroDAOListaEstatica.java
├── model/
│   ├── Livro.java
│   └── Carro.java
└── repository/
    ├── pilha/
    │   ├── Empilhavel.java
    │   └── estatica/PilhaEstatica.java
    ├── fila/
    │   ├── Enfileiravel.java
    │   ├── DuplamenteEnfileiravel.java
    │   └── estatica/
    │       ├── FilaEstatica.java
    │       └── FilaEstaticaComDuplaTerminacao.java
    └── lista/
        ├── Listavel.java
        └── estatica/ListaEstatica.java
```

## Estruturas de dados presentes

- **Pilha estática** (`PilhaEstatica`)
  - operações: empilhar, desempilhar, espiar, atualizar topo;
- **Fila estática circular** (`FilaEstatica`)
  - operações: enfileirar, desenfileirar, frente, atualizar início/fim;
- **Fila estática circular de dupla terminação (Deque)** (`FilaEstaticaComDuplaTerminacao`)
  - operações adicionais: enfileirar no início e desenfileirar no fim;
- **Lista estática circular** (`ListaEstatica`)
  - operações: inserir em posição, anexar, selecionar, atualizar e apagar.

## Domínios modelados

- **Livro**
  - campos principais: `id`, `titulo`, `autor`, `dataPublicacao`, `isbn`, `preco`.
- **Carro**
  - campos principais: `placa`, `marca`, `modelo`, `cor`, `nomeProprietario`, `chegada`.

## Como executar

Atualmente, o ponto de entrada disponível é o `MainLivro`.

1. Entre na pasta do projeto:

   ```bash
   cd projeto_ed_para_praticar
   ```

2. Compile apenas os arquivos necessarios para o `MainLivro`:

   ```bash
   javac model/Livro.java repository/pilha/Empilhavel.java repository/pilha/estatica/PilhaEstatica.java dao/LivroDAO.java dao/LivroDAOPilhaEstatica.java app/MainLivro.java
   ```

3. Execute:

   ```bash
   java app.MainLivro
   ```

## Menu atual (`MainLivro`)

O menu permite, entre outras ações:

- carregar exemplos;
- cadastrar/listar livros;
- buscar por ID, ISBN, autor, título e datas;
- buscar por faixa de preço e faixa de datas;
- atualizar e remover livros;
- visualizar estatísticas (preço médio, mais caro/barato, mais novo/antigo);
- limpar a base em memória.

## Alternando a estratégia de armazenamento de `Livro`

No arquivo `app/MainLivro.java`, você pode trocar a implementação do DAO:

- `LivroDAOPilhaEstatica` (ativo no momento);
- `LivroDAOFilaEstatica` (esqueleto);
- `LivroDAOListaEstatica` (esqueleto).

## Status do projeto 

- `LivroDAOPilhaEstatica`: possui implementação funcional das operações do `LivroDAO`.
- `LivroDAOFilaEstatica` e `LivroDAOListaEstatica`: possuem implementação parcial das operações do `LivroDAO`.
- DAOs de `Carro`: estão como base para prática e ainda precisam de implementação completa.
- `MainCarro`: ainda inexistente mas importante para validação dos DAOS de `Carro`.

## Passos sugeridos
- implementar os métodos faltantes do DAO de fila para `Livro` usando fila estática (LivroDAOFilaEstatica.java);
- implementar os métodos faltantes do DAO de lista para `Livro` usando lista estática (LivroDAOListaEstatica.java);
- implementar os métodos do DAO de pilha para `Carro` usando pilha estática (CarroDAOPilhaEstatica.java);
- implementar os métodos do DAO de fila para `Carro` usando fila estática (CarroDAOFilaEstatica.java);
- implementar os métodos do DAO de lista para `Carro` usando lista estática (CarroDAOListaEstatica.java);
- criar classe `MainCarro` para validar os cenários utilizando as estruturas de dados estáticas para `Carro`.

