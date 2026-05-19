public class Questao4 {
    //Tipo 1
    //1. Instanciou a lista de alunos.
    //2. Criou variável para armazenar o aluno encontrado.
    //3. Percorreu a lista selecionando os alunos.
    //4. Verificou se a matrícula do aluno é igual à matrícula passada como parâmetro.
    //5. Armazenou o aluno encontrado, caso a matrícula seja igual a informada.
    //6. Apagou o aluno da lista, caso a matrícula seja igual a informada.
    //7. Retornou o aluno encontrado ou null caso não exista.
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

    // Tipo 2
    //1. Instanciou a lista de produtos.
    //2. Criou variável para armazenar o produto encontrado.
    //3. Percorreu a lista selecionando os produtos.
    //4. Verificou se o código do produto é igual ao código informado.
    //5. Armazenou o produto encontrado, caso o código seja igual ao informado.
    //6. Interrompeu a busca ao encontrar o produto.
    //7. Retornou o produto encontrado ou null caso não exista.
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

    // Tipo 3
    //1. Instanciou a pilha de pacientes.
    //2. Criou pilha auxiliar.
    //3. Percorreu a pilha desempilhando os pacientes.
    //4. Verificou se o cpf do paciente é igual ao cpf informado.
    //5. Armazenou o próprio paciente, caso o cpf seja diferente.
    //6. Armazenou o paciente fornecido como parâmetro, caso o cpf seja igual.
    //7. Interrompeu a busca ao encontrar o paciente.
    //8. Restaurou a pilha original usando a pilha auxiliar.
    //9. Retornou se o paciente foi atualizado ou não.
    private Empilhavel pilhaPacientes = new PilhaEstatica(20);
    public boolean updatePaciente(Paciente p) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        boolean pacienteAtualizado = false;
        while (!pilhaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) pilhaPacientes.desempilhar();
            if (!paciente.getCPF().equals(p.getCPF())) {
                pilhaAuxiliar.empilhar(paciente);
            }
            else {
                pilhaAuxiliar.empilhar(p);
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