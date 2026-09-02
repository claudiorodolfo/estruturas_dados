public class Questao2 {
    //Tipo 1
    //1. Instanciar a pilha de alunos.
    //2. Criar variável para armazenar o aluno encontrado.
    //3. Criou pilha auxiliar.
    //4. Percorrer a pilha desempilhando elementos.
    //5. Armazenar temporariamente os elementos na pilha auxiliar.
    //6. Comparar a matrícula corretamente.
    //7. Armazenar o aluno encontrado, caso a matrícula seja igual a informada.
    //8. Interromper a busca ao encontrar o aluno.
    //9. Restaurar a pilha original usando a pilha auxiliar.
    //10. Retornar o aluno encontrado ou null caso não exista.
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

    //Tipo 2
    //1. Instanciar a fila de produtos.
    //2. Criar fila auxiliar.
    //3. Criar variável para armazenar o produto removido.
    //4. Percorrer a fila desenfileirando os produtos.
    //5. Comparar corretamente o código do produto.
    //6. Copiar o produto para a fila auxiliar, caso o código seja diferente do informado.
    //7. Armazenar o produto encontrado, caso o código seja igual ao informado.
    //8. Interromper a busca ao encontrar o produto.
    //9. Passar o restante dos produtos para a fila auxiliar.
    //10. Restaurar a fila original usando a fila auxiliar.
    //11. Retornar o produto encontrado ou null caso não exista.
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

    //Tipo 3
    //1. Instanciar a fila de pacientes.
    //2. Criar fila auxiliar.
    //3. Criar variável para armazenar o paciente mais velho.
    //4. Verificar se a fila está sem pacientes.
    //5. Selecionar corretamente o primeiro paciente.
    //4. Percorrer o restante da fila desenfileirando os pacientes.
    //5. Armazenar temporariamente os pacientes na fila auxiliar
    //7. Verificar se a data de nascimento é diferente de null.
    //8. Comparar corretamente a data de nascimento do paciente.
    //9. Selecionar corretamente o paciente mais velho.
    //10. Atualizar a fila principal com a fila auxiliar.
    //11. Retornar o paciente mais velho.
    private Enfileiravel filaPacientes = new FilaEstatica(20);
    public Paciente getPacienteMaisVelho() {
        if (filaPacientes.estaVazia()) {
            return null;
        }
        Enfileiravel filaAuxiliar = new FilaEstatica(20);

        Paciente paciente = (Paciente) filaPacientes.desenfileirar();
        filaAuxiliar.enfileirar(paciente);
        Paciente pacienteMaisVelho = paciente;

        while (!filaPacientes.estaVazia()) {
            paciente = (Paciente) filaPacientes.desenfileirar();
            filaAuxiliar.enfileirar(paciente);
            if (paciente.getDataNascimento() != null && 
                    paciente.getDataNascimento().isBefore(pacienteMaisVelho.getDataNascimento())) {
                pacienteMaisVelho = paciente;
            }
        }
        filaPacientes = filaAuxiliar;
        return pacienteMaisVelho;
    }
}