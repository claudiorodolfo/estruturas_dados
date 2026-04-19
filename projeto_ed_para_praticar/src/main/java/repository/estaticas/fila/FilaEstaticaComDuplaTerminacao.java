package repository.estaticas.fila;

import repository.DuplamenteEnfileiravel;

public class FilaEstaticaComDuplaTerminacao extends FilaEstatica implements DuplamenteEnfileiravel {

    public FilaEstaticaComDuplaTerminacao(int tamanho) {
        super(tamanho);
    }

    public FilaEstaticaComDuplaTerminacao() {
        super();
    }

    @Override
    public Object tras() {
        if (!estaVazia()) {
            return dados[ponteiroFim];
        } else {
            System.err.println("Fila vazia");
        }
        return null;

    }

    @Override
    public void enfileirarInicio(Object objeto) {
        if (!estaCheia()) {
            if (estaVazia()) {
                ponteiroFim = ponteiroInicio;
            } else {
                ponteiroInicio = retrocederPonteiro(ponteiroInicio);
            }
            dados[ponteiroInicio] = objeto;
            quantidade++;
        } else {
            System.err.println("Fila cheia");
        }

    }

    @Override
    public Object desenfileirarFim() {
        Object objetoRetorno = null;
        if (!estaVazia()) {
            objetoRetorno = dados[ponteiroFim];
            ponteiroFim = retrocederPonteiro(ponteiroFim);
            quantidade--;
        } else {
            System.err.println("Fila vazia");
        }
        return objetoRetorno;
    }

    @Override
    public String imprimirTrasPraFrente() { 
        String resultado = "";
        int indiceTemporario = ponteiroFim;
        for (int i = 0; i < quantidade; i++) {
            resultado += dados[indiceTemporario];
            if (i != quantidade - 1) {
                resultado += ",";
            }
            indiceTemporario = retrocederPonteiro(indiceTemporario);
        }
        return "[ " + resultado + " ]";
    
    }
}