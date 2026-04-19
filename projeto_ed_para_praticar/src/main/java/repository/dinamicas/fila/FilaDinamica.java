package repository.dinamicas.fila;

import java.util.NoSuchElementException;

import repository.Enfileiravel;
import repository.dinamicas.NoDuplo;

public class FilaDinamica implements Enfileiravel {
    protected NoDuplo ponteiroInicio;
    protected NoDuplo ponteiroFim;
    protected int tamanhoMaximo;
    protected int quantidade;

    public FilaDinamica(int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("capacidade deve ser maior que zero.");
        }
        ponteiroInicio = null;
        ponteiroFim = null;
        this.tamanhoMaximo = tamanhoMaximo;
        quantidade = 0;
    }

    public FilaDinamica() {
        this(10);
    }

    @Override
    public void enfileirar(Object dado) {
        if (estaCheia()) {
            throw new NoSuchElementException("Fila cheia");
        }
        NoDuplo novoNo = new NoDuplo(dado);
        if (estaVazia()) {
            ponteiroInicio = ponteiroFim = novoNo;
        } else {
            ponteiroFim.setAnterior(novoNo);
            novoNo.setProximo(ponteiroFim);
            ponteiroFim = novoNo;
        }
        quantidade++;
    }

    @Override
    public Object frente() {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        return ponteiroInicio.getDado();
    }

    @Override
    public void atualizarInicio(Object novoDado) {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        ponteiroInicio.setDado(novoDado);
    }

    @Override
    public void atualizarFim(Object novoDado) {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        ponteiroFim.setDado(novoDado);
    }

    @Override
    public Object desenfileirar() {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        Object dadoInicio = ponteiroInicio.getDado();
        ponteiroInicio = ponteiroInicio.getProximo();
        if (ponteiroInicio == null) {
            ponteiroFim = null;
        }
        quantidade--;
        return dadoInicio;
    }

    @Override
    public void limpar() {
        ponteiroInicio = ponteiroFim = null;
        quantidade = 0;
    }

    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    @Override
    public boolean estaCheia() {
        return quantidade == tamanhoMaximo;
    }

    @Override
    public String imprimir() {
        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();
            if (i != quantidade - 1) {
                resultado += ",";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return "[ " + resultado + " ]";
    }
}
