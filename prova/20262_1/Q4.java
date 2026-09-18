import java.util.Arrays;

public class Q4 {
    //TIPO 1
    //1. Instanciar a fila de alunos.
    //2. Verificar se o curso informado é diferente de null.
    //3. Retornar um array vazio caso o curso seja null.
    //4. Criar uma fila auxiliar.
    //5. Criar array para armazenar os alunos encontrados.
    //6. Criar variável para contar a quantidade de alunos encontrados.
    //7. Percorrer a fila desenfileirando os alunos.
    //8. Armazenar temporariamente todos os alunos na fila auxiliar.
    //9. Verificar se o curso do aluno é diferente de null.
    //10. Comparar o curso corretamente (sem diferenciar maiúsculas e minúsculas).
    //11. Armazenar o aluno no array, caso o curso seja igual ao informado.
    //12. Atualizar a fila principal com a fila auxiliar.
    //13. Retornar o array com os alunos encontrados.
    Enfileiravel filaAlunos = new FilaEstaticaCircular(20);

    public Aluno[] getAlunosPorCurso(String curso) {
        if (curso == null)
            return new Aluno[0];

        Enfileiravel filaAuxiliar = new FilaEstaticaCircular(20);
        Aluno[] arrayResultado = new Aluno[20];
        int quantidade = 0;

        while (!filaAlunos.estaVazia()) {
            Aluno aluno = (Aluno) filaAlunos.desenfileirar();
            filaAuxiliar.enfileirar(aluno);
            if (aluno.getCurso() != null &&
                    aluno.getCurso().equalsIgnoreCase(curso)) {
                arrayResultado[quantidade] = aluno;
                quantidade++;
            }
        }
        filaAlunos = filaAuxiliar;
        return Arrays.copyOf(arrayResultado, quantidade);
    }

    //TIPO 2
    //1. Instanciar a fila de produtos.
    //2. Verificar se a categoria informada é diferente de null.
    //3. Retornar um array vazio caso a categoria seja null.
    //4. Criar uma fila auxiliar.
    //5. Criar array para armazenar os produtos encontrados.
    //6. Criar variável para contar a quantidade de produtos encontrados.
    //7. Percorrer a fila desenfileirando os produtos.
    //8. Armazenar temporariamente todos os produtos na fila auxiliar.
    //9. Verificar se a categoria do produto é diferente de null.
    //10. Comparar a categoria corretamente (sem diferenciar maiúsculas e minúsculas).
    //11. Armazenar o produto no array, caso a categoria seja igual à informada.
    //12. Atualizar a fila principal com a fila auxiliar.
    //13. Retornar o array com os produtos encontrados.
    Enfileiravel filaProdutos = new FilaEstaticaCircular(20);

    public Produto[] getProdutosPorCategoria(String categoria) {
        if (categoria == null)
            return new Produto[0];

        Enfileiravel filaAuxiliar = new FilaEstaticaCircular(20);
        Produto[] arrayResultado = new Produto[20];
        int quantidade = 0;

        while (!filaProdutos.estaVazia()) {
            Produto produto = (Produto) filaProdutos.desenfileirar();
            filaAuxiliar.enfileirar(produto);
            if (produto.getCategoria() != null &&
                    produto.getCategoria().equalsIgnoreCase(categoria)) {
                arrayResultado[quantidade] = produto;
                quantidade++;
            }
        }
        filaProdutos = filaAuxiliar;
        return Arrays.copyOf(arrayResultado, quantidade);
    }
}