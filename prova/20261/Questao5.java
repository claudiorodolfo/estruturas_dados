// Tipo 1
public class Questao5 {
    //1. Instanciou a lista de alunos.
    //2. Criou o array de retorno.
    //3. Percorreu a lista selecionando os alunos.
    //4. Verificou se o curso do aluno é diferente de null.
    //5. Comparou corretamente o curso do aluno.
    //6. Armazenou o aluno no array de retorno, caso o curso seja igual aoinformado.
    //7. Atualizou o índice do array de retorno.
    //8. Retornou o array de alunos.
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
    //1. Instanciou a lista de produtos.
    //2. Percorreu a lista selecionando os produtos.
    //3. Verificou se a categoria do produto é diferente de null.
    //4. Comparou corretamente a categoria do produto.
    //5. Apagou o produto da lista, caso a categoria seja igual a informada.
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
    //1. Instanciou a pilha de pacientes.
    //2. Criou pilha auxiliar.
    //3. Percorreu a pilha desempilhando os pacientes.
    //4. Verificou se a ubs do paciente é diferente de null.
    //5. Comparou corretamente a ubs do paciente.
    //6. Armazenou o paciente na pilha auxiliar, caso a ubs seja igual a informada.
    //7. Restaurou a pilha original usando a pilha auxiliar.
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