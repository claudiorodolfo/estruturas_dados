//Tipo 1
public class Questao3 {
    private Empilhavel pilhaAlunos = new PilhaEstatica(20);
    public void apagaAlunosPorCurso(String curso) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        while (!pilhaAlunos.estaVazia()) {
            Aluno aluno = (Aluno) pilhaAlunos.desempilhar();
            if (aluno.getCurso() != null && 
                    !aluno.getCurso().equalsIgnoreCase(curso)) {
                pilhaAuxiliar.empilhar(aluno);
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaAlunos.empilhar(pilhaAuxiliar.desempilhar());
        }
    }
}

//Tipo 2
public class Questao3 {
    private Enfileiravel filaProdutos = new FilaEstatica(20);
    public Produto[] getProdutosPorCategoria(String categoria) {
        Produto[] produtosRetorno = new Produto[20];
        Enfileiravel filaAuxiliar = new FilaEstatica(20);
        int indice = 0;
        while (!filaProdutos.estaVazia()) {
            Produto produto = (Produto) filaProdutos.desenfileirar();
            filaAuxiliar.enfileirar(produto);
            if (produto.getCategoria() != null && 
                    produto.getCategoria().equalsIgnoreCase(categoria)) {
                produtosRetorno[indice] = produto;
                indice++;
            }
        }
        filaProdutos = filaAuxiliar;
        return Arrays.copyOf(produtosRetorno, indice);
    }
}

//Tipo 3
public class Questao3 {
    private Enfileiravel filaPacientes = new FilaEstatica(20);
    public Paciente[] getPacientesPorFaixaEtaria(LocalDate dataInicial, LocalDate dataFinal) {
        Paciente[] pacientesRetorno = new Paciente[20];
        Enfileiravel filaAuxiliar = new FilaEstatica(20);
        int indice = 0;
        while (!filaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) filaPacientes.desenfileirar();
            filaAuxiliar.enfileirar(paciente);
            if (paciente.getDataNascimento() != null && 
                    paciente.getDataNascimento().isAfter(dataInicial) && 
                    paciente.getDataNascimento().isBefore(dataFinal)) {
                pacientesRetorno[indice] = paciente;
                indice++;
            }
        }
        filaPacientes = filaAuxiliar;
        return Arrays.copyOf(pacientesRetorno, indice);
    }
}