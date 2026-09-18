public class Q3 {
    //TIPO 1
    //1. Instanciar a pilha de alunos.
    //2. Criar uma pilha auxiliar.
    //3. Criar variável para armazenar o aluno encontrado.
    //4. Percorrer a pilha desempilhando elementos.
    //5. Comparar a matrícula corretamente.
    //6. Armazenar o aluno encontrado, caso a matrícula seja igual a informada.
    //7. Interromper a busca ao encontrar o aluno.
    //8. Armazenar temporariamente na pilha auxiliar os alunos com matrícula diferente.
    //9. Restaurar a pilha original usando a pilha auxiliar.
    //10. Retornar o aluno encontrado ou null caso não exista.
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
    //1. Instanciar a pilha de produtos.
    //2. Criar uma pilha auxiliar.
    //3. Percorrer a pilha desempilhando elementos.
    //4. Verificar se a categoria do produto é diferente de null.
    //5. Comparar a categoria corretamente (sem diferenciar maiúsculas e minúsculas).
    //6. Armazenar temporariamente na pilha auxiliar os produtos de categoria diferente da informada.
    //7. Descartar os produtos cuja categoria seja igual à informada.
    //8. Restaurar a pilha original usando a pilha auxiliar.
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
}