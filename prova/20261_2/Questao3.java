public class Questao3 {
    //Tipo 1
    //1. Verificar se a posição é válida.
    //2. Criar a string de resultado.
    //3. Percorrer a lista até a posição informada.
    //4. Adicionar o dado na string de resultado.
    //5. Coloca vírgula entre os dados.
    //6. Retorna a string de resultado.
    public String imprimirAte(int posicao) {
        if (posicao < 0 || posicao > quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        String resultado = ""; 
        NoDuplo ponteiroAuxiliar = ponteiroInicio; 
        for (int i = 0; i < posicao; i++) {
            resultado += ponteiroAuxiliar.getDado(); 
            if (i < posicao - 1) { 
                resultado += ","; 
            } 
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return "[" + resultado + "]"; 
    }

    //Tipo 2
    //1. Verificar se a posição é válida.
    //2. Criar a string de resultado.
    //3. Percorrer a lista até a posição informada, para posicionamento do ponteiro auxiliar.
    //3. Percorrer a lista a partir da posição informada.
    //4. Adicionar o dado na string de resultado.
    //5. Coloca vírgula entre os dados.
    //6. Retornar a string de resultado.
    public String imprimirApartirDe(int posicao) {
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        String resultado = ""; 
        // posiciona o ponteiro
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        for (int i = posicao; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado(); 
            if (i < quantidade - 1) { 
                resultado += ","; 
            } 
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return "[" + resultado + "]"; 
    }

    //Tipo 3
    //1. Verificar se a lista está vazia ou tem apenas um elemento.
    //2. Percorrer a lista.
    //3. Verificar se o dado atual é maior que o próximo.
    //4. Retornar false, caso o dado atual seja maior que o próximo.
    //5. Retornar true, caso todos os dados sejam menores ou iguais ao próximo.
    public boolean estaOrdenada() { 
        if (estaVazia() || quantidade == 1) {
            return true;
        }
        NoDuplo ponteiroAuxiliar = ponteiroInicio; 
        while (ponteiroAuxiliar.getProximo() != null) { 
            Double ant = (Double) ponteiroAuxiliar.getDado(); 
            Double prox = (Double) ponteiroAuxiliar.getProximo().getDado(); 
            if (ant > prox) { 
                return false; 
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo(); 
        }
        return true; 
    }
}