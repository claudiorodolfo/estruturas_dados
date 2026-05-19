public class Questao1 {
    //Tipo 1
    //1. Instanciar a fila de pacientes.
    //2. Criar fila auxiliar.
    //3. Criar variável para armazenar o paciente mais nome.
    //4. Verificar se a fila está sem pacientes.
    //5. Selecionar corretamente o primeiro paciente.
    //4. Percorrer o restante da fila desenfileirando os pacientes.
    //5. Armazenar temporariamente os pacientes na fila auxiliar
    //7. Verificar se a data de nascimento é diferente de null.
    //8. Comparar corretamente a data de nascimento do paciente.
    //9. Selecionar corretamente o paciente mais novo.
    //10. Atualizar a fila principal com a fila auxiliar.
    //11. Retornar o paciente mais novo.
    private Enfileiravel filaPacientes1 = new FilaEstatica(20);
    public Paciente getPacienteMaisNovo() {
        if (filaPacientes1.estaVazia()) {
            return null;
        }
        Enfileiravel filaAuxiliar = new FilaEstatica(20);

        Paciente paciente = (Paciente) filaPacientes1.desenfileirar();
        filaAuxiliar.enfileirar(paciente);
        Paciente pacienteMaisNovo = paciente;

        while (!filaPacientes1.estaVazia()) {
            paciente = (Paciente) filaPacientes1.desenfileirar();
            filaAuxiliar.enfileirar(paciente);
            if (paciente.getDataNascimento() != null && 
                    pacienteMaisNovo.getDataNascimento() != null &&
                    paciente.getDataNascimento().isAfter(pacienteMaisNovo.getDataNascimento())) {
                pacienteMaisNovo = paciente;
            }
        }
        filaPacientes1 = filaAuxiliar;
        return pacienteMaisNovo;
    }

    //Tipo 2
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
    private Enfileiravel filaPacientes2 = new FilaEstatica(20);
    public Paciente getPacienteMaisVelho() {
        if (filaPacientes2.estaVazia()) {
            return null;
        }
        Enfileiravel filaAuxiliar = new FilaEstatica(20);

        Paciente paciente = (Paciente) filaPacientes2.desenfileirar();
        filaAuxiliar.enfileirar(paciente);
        Paciente pacienteMaisVelho = paciente;

        while (!filaPacientes2.estaVazia()) {
            paciente = (Paciente) filaPacientes2.desenfileirar();
            filaAuxiliar.enfileirar(paciente);
            if (paciente.getDataNascimento() != null && 
                    pacienteMaisVelho.getDataNascimento() != null &&
                    paciente.getDataNascimento().isBefore(pacienteMaisVelho.getDataNascimento())) {
                pacienteMaisVelho = paciente;
            }
        }
        filaPacientes2 = filaAuxiliar;
        return pacienteMaisVelho;
    }

    //Tipo 3
    //1. Instanciar a lista de produtos.
    //2. Criar o array de produtos removidos.
    //3. Criar a quantidade de produtos removidos.
    //4. Percorrer a lista selecionando os produtos.
    //5. Verificar se a categoria do produto é igual à categoria informada.
    //6. Armazenar o produto removido.
    //7. Restaurar a lista original usando a lista auxiliar.
    //8. Retornar o array de produtos removidos.
    private Listavel listaProdutos = new ListaEstatica(20);
    public Produto[] apagaProdutosPorCategoria(String categoria) {
        Produto[] produtosRetorno = new Produto[listaProdutos.tamanho()];    
        int indice = 0;
        for (int i = 0; i < listaProdutos.tamanho(); i++) {    
            Produto atual = (Produto) listaProdutos.selecionar(i);    
            if (atual.getCategoria() != null && 
                    atual.getCategoria().equalsIgnoreCase(categoria)) {    
                produtosRetorno[indice] = atual;    
                indice++;
                listaProdutos.apagar(i);
                i--;
            }
        }    
        return Arrays.copyOf(produtosRetorno, indice);
    }
}