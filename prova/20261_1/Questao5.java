public class Questao5 {
    // Tipo 1
    //1. Instanciar a lista de alunos.
    //2. Criar o array de retorno.
    //3. Percorrer a lista selecionando os alunos.
    //4. Verificar se o curso do aluno é diferente de null.
    //5. Comparar corretamente o curso do aluno.
    //6. Armazenar o aluno no array de retorno, caso o curso seja igual aoinformado.
    //7. Atualizou o índice do array de retorno.
    //8. Retornou o array de alunos.
    private Listavel listaAlunos = new ListaEstatica(20);
    public Aluno[] getAlunosPorCurso(String curso) {
        Aluno[] alunosRetorno = new Aluno[listaAlunos.tamanho()];
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

    // Tipo 2
    //1. Instanciar a lista de produtos.
    //2. Percorrer a lista selecionando os produtos.
    //3. Verificar se a categoria do produto é diferente de null.
    //4. Comparar corretamente a categoria do produto.
    //5. Apagar o produto da lista, caso a categoria seja igual a informada.
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

    // Tipo 3
    //1. Instanciar a pilha de pacientes.
    //2. Criar pilha auxiliar.
    //3. Percorrer a pilha desempilhando os pacientes.
    //4. Verificar se a ubs do paciente é diferente de null.
    //5. Comparar corretamente a ubs do paciente.
    //6. Armazenar o paciente na pilha auxiliar, caso a ubs seja igual a informada.
    //7. Restaurar a pilha original usando a pilha auxiliar.
    private Empilhavel pilhaPacientes = new PilhaEstatica(20);
    public void apagaPacientesPorUBS(String ubs) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        while (!pilhaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) pilhaPacientes.desempilhar();
            if (paciente.getUBS() == null || 
                    !paciente.getUBS().equalsIgnoreCase(ubs)) {
                pilhaAuxiliar.empilhar(paciente);
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaPacientes.empilhar(pilhaAuxiliar.desempilhar());
        }
    }
}