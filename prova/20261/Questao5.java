// Tipo 1
public class Questao5 {
    private Listavel listaAlunos = new ListaEstatica(20);
    public Aluno[] getAlunosPorCurso(String curso) {
        Aluno[] alunosRetorno = new Aluno[20];
        int indice = 0;
        for (int i = 0; i < listaAlunos.tamanho(); i++) {
            Aluno aluno = (Aluno) listaAlunos.selecionar(i);
            if (aluno.getCurso() != null && 
                    aluno.getCurso().equalsIgnoreCase(curso)) {
                alunosRetorno[indice] = aluno;
                indice++;
            }
        }
        return Arrays.copyOf(alunosRetorno, indice);
    }
}

// Tipo 2
public class Questao5 {
    private Listavel listaProdutos = new ListaEstatica(20);
    public void apagaProdutosPorCategoria(String categoria) {
        for (int i = listaProdutos.tamanho()-1; i >= 0 ; i--) {
            Produto produto = (Produto) listaProdutos.selecionar(i);
            if (produto.getCategoria() != null && 
                    produto.getCategoria().equalsIgnoreCase(categoria)) {
                listaProdutos.apagar(i);
            }
        }
    }
}

// Tipo 3
public class Questao5 {
    private Empilhavel pilhaPacientes = new PilhaEstatica(20);
    public void apagaPacientesPorUBS(String ubs) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        while (!pilhaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) pilhaPacientes.desempilhar();
            if (paciente.getUBS() != null && 
                    paciente.getUBS().equalsIgnoreCase(ubs)) {
                pilhaAuxiliar.empilhar(paciente);
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaPacientes.empilhar(pilhaAuxiliar.desempilhar());
        }
    }
}