//Tipo 1
public class Questao4 {
    private Listavel listaAlunos = new ListaEstatica(20);
    public Aluno apagaAluno(int matricula) {
        Aluno alunoRetorno = null;
        for (int i = 0; i < listaAlunos.tamanho(); i++) {
            Aluno aluno = (Aluno) listaAlunos.selecionar(i);
            if (aluno.getMatricula() == matricula) {
                alunoRetorno = aluno;
                listaAlunos.apagar(i);
                break;
            }
        }
        return alunoRetorno;
    }
}

// Tipo 2
public class Questao4 {
    private Listavel listaProdutos = new ListaEstatica(20);
    public Produto getProduto(int codigo) {
        Produto produtoRetorno = null;
        for (int i = 0; i < listaProdutos.tamanho(); i++) {
            Produto produto = (Produto) listaProdutos.selecionar(i);
            if (produto.getCodigo() == codigo) {
                produtoRetorno = produto;
                break;
            }
        }
        return produtoRetorno;
    }
}

// Tipo 3
public class Questao4 {
    private Empilhavel pilhaPacientes = new PilhaEstatica(20);
    public boolean updatePaciente(Paciente p) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        boolean pacienteAtualizado = false;
        while (!pilhaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) pilhaPacientes.desempilhar();
            if (paciente.getMatricula() != p.getMatricula()) {
                pilhaAuxiliar.empilhar(paciente);
            }
            else {
                pilhaPacientes.empilhar(p);
                pacienteAtualizado = true;
                break;
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaPacientes.empilhar(pilhaAuxiliar.desempilhar());
        }
        return pacienteAtualizado;
    }
}