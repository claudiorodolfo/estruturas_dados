//Tipo 1
public class Questao2 {
    private Empilhavel pilhaAlunos = new PilhaEstatica(20);
    public Aluno getAluno(int matricula) {
        Aluno alunoRetorno = null;
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        while (!pilhaAlunos.estaVazia()) {        
            Aluno aluno = (Aluno) pilhaAlunos.desempilhar();
            pilhaAuxiliar.empilhar(aluno);
            if (aluno.getMatricula() == matricula) {
                alunoRetorno = aluno;
                break;
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaAlunos.empilhar(pilhaAuxiliar.desempilhar());
        }
        return alunoRetorno;
    }
}

//Tipo 2
public class Questao2 {
    private Enfileiravel filaProdutos = new FilaEstatica(20);
    public Produto apagaProduto(int codigo) {
        Enfileiravel filaAuxiliar = new FilaEstatica(20);
        Produto produtoRetorno = null;
        while (!filaProdutos.estaVazia()) {
            Produto produto = (Produto) filaProdutos.desenfileirar();
            if (produto.getCodigo() != codigo) {
                filaAuxiliar.enfileirar(produto);
            } else {
                produtoRetorno = produto;
                break;
            }
        }
        while (!filaProdutos.estaVazia()) {
            filaAuxiliar.enfileirar(filaProdutos.desenfileirar());
        }
        filaProdutos = filaAuxiliar;
        return produtoRetorno;
    }
}
//Tipo 3
public class Questao2 {
    private Enfileiravel filaPacientes = new FilaEstatica(20);
    public Paciente getPacienteMaisVelho() {
        Enfileiravel filaAuxiliar = new FilaEstatica(20);
        Paciente pacienteMaisVelho = null;
        while (!filaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) filaPacientes.desenfileirar();
            filaAuxiliar.enfileirar(paciente);
            if (pacienteMaisVelho == null || 
                    paciente.getDataNascimento() != null && 
                    paciente.getDataNascimento().isBefore(pacienteMaisVelho.getDataNascimento())) {
                pacienteMaisVelho = paciente;
            }
        }
        filaPacientes = filaAuxiliar;
        return pacienteMaisVelho;
    }
}