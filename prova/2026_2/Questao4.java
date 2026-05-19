public class Questao4 {
    //Tipo 1
    // 1. Criar o novo nó.
    // 2. Verificar se a lista está vazia.
    // 3. Percorrer a lista procurando o elemento informado.
    // 4. Caso encontre, parar a busca.
    // 5. Caso não encontre, o ponteiro ficará no último nó.
    // 6. Armazenar as referências do nó anterior e do próximo.
    // 7. Atualizar os ponteiros do novo nó.
    // 8. Atualizar os ponteiros dos nós vizinhos.
    // 9. Atualizar o ponteiro fim, se necessário.
    public void inserirDepois(Object procurado, Object novo) { 
        NoDuplo novoNo = new NoDuplo(novo); 
        if (estaVazia()) { 
            ponteiroInicio = ponteiroFim = novoNo; 
        } else {
            NoDuplo ponteiroAuxiliar = ponteiroInicio; 
            // Procura o elemento ou para no último nó
            while (ponteiroAuxiliar.getProximo() != null &&
                    !ponteiroAuxiliar.getDado().equals(procurado)) { 
                ponteiroProximo = ponteiroProximo.getProximo();
            }
            NoDuplo ant = ponteiroAuxiliar;
            NoDuplo prox = ponteiroAuxiliar.getProximo();
            novoNo.setAnterior(ant); 
            novoNo.setProximo(prox); 
            ant.setProximo(novoNo);
            if (prox != null) {
                prox.setAnterior(novoNo);
            } else {
                ponteiroFim = novoNo;
            }
        }
        quantidade++;
    }

    //Tipo 2
    // 1. Criar o novo nó.
    // 2. Verificar se a lista está vazia.
    // 3. Percorrer a lista procurando o elemento informado.
    // 4. Caso encontre, parar a busca.
    // 5. Caso não encontre, o ponteiro ficará no primeiro nó.
    // 6. Armazenar as referências do nó anterior e do próximo.
    // 7. Atualizar os ponteiros do novo nó.
    // 8. Atualizar os ponteiros dos nós vizinhos.
    // 9. Atualizar o ponteiro início, se necessário.
    public void inserirAntes(Object procurado, Object novo) { 
        NoDuplo novoNo = new NoDuplo(novo); 
        if (estaVazia()) { 
            ponteiroInicio = ponteiroFim = novoNo; 
        } else {
            NoDuplo ponteiroAuxiliar = ponteiroFim; 
            // Procura o elemento ou para no primeiro nó
            while (ponteiroAuxiliar.getAnterior() != null &&
                    !ponteiroAuxiliar.getDado().equals(procurado)) { 
                ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
            }
            NoDuplo prox = ponteiroAuxiliar;
            NoDuplo ant = ponteiroAuxiliar.getAnterior();
            novoNo.setAnterior(ant); 
            novoNo.setProximo(prox); 
            prox.setAnterior(novoNo);
            if (ant != null) {
                ant.setProximo(novoNo);
            } else {
                ponteiroInicio = novoNo;
            }
        }
        quantidade++;
    }

    //Tipo 3
    //1. Percorrer a lista selecionando os dados.
    //2. Verificar se a lista está cheia.
    //3. Criar o novo nó.
    //4. Verificar se a lista está vazia.
    //5. Armazenar o novo nó.
    //6. Retornar.
    public Listavel concatena(Listavel outraLista) {
        if (outraLista.estaVazia()) {
            return this;
        }
        for (int i = 0; i < outraLista.tamanho(); i++) {
            Object dado = outraLista.selecionar(i);
            NoDuplo novoNo = new NoDuplo(dado);
            novoNo.setAnterior(ponteiroFim);
            if (!estaVazia()) {
                ponteiroFim.setProximo(novoNo);
            } else {
                ponteiroInicio = novoNo;
            }
            ponteiroFim = novoNo;
            quantidade++;
        }
        return this;
    }
}