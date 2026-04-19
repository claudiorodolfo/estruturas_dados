package repository.dinamicas.fila;

import java.util.NoSuchElementException;

import repository.DuplamenteEnfileiravel;
import repository.dinamicas.NoDuplo;

public class FilaDinamicaComDuplaTerminacao extends FilaDinamica implements DuplamenteEnfileiravel {

    public FilaDinamicaComDuplaTerminacao(int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("capacidade deve ser maior que zero.");
        }
        ponteiroInicio = null;
        ponteiroFim = null;
        this.tamanhoMaximo = tamanhoMaximo;
        quantidade = 0;
    }

    public FilaDinamicaComDuplaTerminacao() {
        this(10);
    }

    @Override
    public void enfileirarInicio(Object objeto) {
        if (estaCheia()) {
            throw new NoSuchElementException("Fila cheia");
        }
        NoDuplo novoNo = new NoDuplo(objeto);
        if (estaVazia()) {
            ponteiroInicio = ponteiroFim = novoNo;
        } else {
            novoNo.setProximo(ponteiroInicio);
            ponteiroInicio.setAnterior(novoNo);
            ponteiroInicio = novoNo;
        }
        quantidade++;
    }

    @Override
    public Object tras() {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        return ponteiroFim.getDado();
    }

    @Override
    public Object desenfileirarFim() {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        Object dadoFim = ponteiroFim.getDado();
        ponteiroFim = ponteiroFim.getAnterior();
        if (ponteiroFim != null) {
            ponteiroFim.setProximo(null);
        } else {
            ponteiroInicio = null;
        }
        quantidade--;
        return dadoFim;
    }

    @Override
    public String imprimirTrasPraFrente() {
        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroFim;
        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();
            if (i != quantidade - 1) {
                resultado += ",";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
        }
        return "[ " + resultado + " ]";
    }
}
