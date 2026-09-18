//TIPO 1
import java.util.Arrays;

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
import java.util.Arrays;

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