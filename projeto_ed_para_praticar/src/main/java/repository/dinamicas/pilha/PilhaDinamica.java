package repository.dinamicas.pilha;

import java.util.NoSuchElementException;

import repository.Empilhavel;
import repository.ed_dinamicas.NoDuplo;

public class PilhaDinamica implements Empilhavel {
    private NoDuplo ponteiroTopo;
    private int tamanhoMaximo;
    private int quantidade;

    public PilhaDinamica() {
        this(10);
    }

    public PilhaDinamica(int tamanhoMaximo) {
        ponteiroTopo = null;
        this.tamanhoMaximo = tamanhoMaximo;
        quantidade = 0;
    }

    @Override
    public Object desempilhar() {
        if (estaVazia()) {
            throw new NoSuchElementException("Pilha Vazia!");
        }
        Object dadoTopo = ponteiroTopo.getDado();
        ponteiroTopo = ponteiroTopo.getAnterior();
        if (ponteiroTopo != null) {
            ponteiroTopo.setProximo(null);
        }
        quantidade--;
        return dadoTopo;
    }

    @Override
    public void empilhar(Object dado) {
        if (estaCheia()) {
            throw new NoSuchElementException("Pilha Cheia!");
        }
        NoDuplo novoNo = new NoDuplo(dado);
        if (ponteiroTopo == null) {
            ponteiroTopo = novoNo;
        } else {
            novoNo.setAnterior(ponteiroTopo);
            ponteiroTopo.setProximo(novoNo);
            ponteiroTopo = novoNo;
        }
        quantidade++;
    }

    @Override
    public void atualizar(Object dado) {
        if (estaVazia()) {
            throw new NoSuchElementException("Pilha Vazia!");
        }
        ponteiroTopo.setDado(dado);
    }

    @Override
    public Object espiar() {
        if (estaVazia()) {
            throw new NoSuchElementException("Pilha Vazia!");
        }
        return ponteiroTopo.getDado();
    }

    @Override
    public boolean estaCheia() {
        return quantidade == tamanhoMaximo;
    }

    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    @Override
    public String imprimir() {
        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroTopo;
        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();
            if (i != quantidade - 1) {
                resultado += ",";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
        }
        return "[" + resultado + "]";
    }

}
