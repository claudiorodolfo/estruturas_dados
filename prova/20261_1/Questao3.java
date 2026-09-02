public class Questao3 {
    //Tipo 1
    //1. Instanciar a pilha de alunos.
    //2. Criar pilha auxiliar.
    //3. Percorrer a pilha desempilhando elementos.
    //4. Verificar se o curso do aluno é diferente de null.    
    //5. Comparar corretamente o curso do aluno.
    //6. Não copiar o aluno para a pilha auxiliar, caso o curso seja diferente do informado.
    //7. Restaurar a pilha original usando a pilha auxiliar.
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

    //Tipo 2
    //1. Instanciar a fila de produtos.
    //2. Criar o array de retorno.
    //3. Criar fila auxiliar.
    //3. Percorrer a fila desenfileirando os produtos.
    //4. Armazenar temporariamente os produtos na fila auxiliar.
    //6. Verificar se a categoria é diferente de null.
    //5. Comparar corretamente a categoria do produto.
    //6. Armazenar o produto no array de retorno, caso a categoria seja igual a informada.
    //7. Restaurar a fila original usando a fila auxiliar.
    //8. Retornar o array de produtos.
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

    //Tipo 3
    //1. Instanciar a fila de pacientes.
    //2. Criar o array de retorno.
    //3. Criar fila auxiliar.
    //4. Percorreu a fila desenfileirando os pacientes.
    //5. Armazenar temporariamente os elementos na fila auxiliar.
    //6. Verificar se a data de nascimento é diferente de null.
    //6. Comparar corretamente a faixa etária do paciente.
    //7. Armazenar o paciente no array de retorno, caso a data de nascimento esteja dentro da faixa informada.
    //8. Restaurar a fila original usando a fila auxiliar.
    //9. Retornar o array de pacientes.
    private Enfileiravel filaPacientes = new FilaEstatica(20);
    public Paciente[] getPacientesPorFaixaEtaria(LocalDate dataInicial, LocalDate dataFinal) {
        Paciente[] pacientesRetorno = new Paciente[20];
        Enfileiravel filaAuxiliar = new FilaEstatica(20);
        int indice = 0;
        while (!filaPacientes.estaVazia()) {
            Paciente paciente = (Paciente) filaPacientes.desenfileirar();
            filaAuxiliar.enfileirar(paciente);
            if (paciente.getDataNascimento() != null && 
                    !paciente.getDataNascimento().isBefore(dataInicial) && 
                    !paciente.getDataNascimento().isAfter(dataFinal)) {
                pacientesRetorno[indice] = paciente;
                indice++;
            }
        }
        filaPacientes = filaAuxiliar;
        return Arrays.copyOf(pacientesRetorno, indice);
    }
}