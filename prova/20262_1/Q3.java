//TIPO 1
Empilhavel pilhaAlunos = new PilhaEstatica(20);

public Aluno apagaAluno(int matricula) {
    Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
    Aluno encontrado = null;

    while (!pilhaAlunos.estaVazia()) {
        Aluno aluno = (Aluno) pilhaAlunos.desempilhar();
        if (aluno.getMatricula() == matricula) {
            encontrado = aluno;
            break;
        }
        pilhaAuxiliar.empilhar(aluno);
    }

    while (!pilhaAuxiliar.estaVazia()) {
        pilhaAlunos.empilhar(pilhaAuxiliar.desempilhar());
    }
    return encontrado;
}

//TIPO 2
Empilhavel pilhaProdutos = new PilhaEstatica(20);

public void apagaProdutosPorCategoria(String categoria) {
    Empilhavel pilhaAuxiliar = new PilhaEstatica(20);

    while (!pilhaProdutos.estaVazia()) {
        Produto produto = (Produto) pilhaProdutos.desempilhar();
        if (produto.getCategoria() == null
                || !produto.getCategoria().equalsIgnoreCase(categoria)) {
            pilhaAuxiliar.empilhar(produto);
        }
    }

    while (!pilhaAuxiliar.estaVazia()) {
        pilhaProdutos.empilhar(pilhaAuxiliar.desempilhar());
    }
}