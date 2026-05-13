package repository.dinamicas.lista;

import repository.Listavel;
import repository.dinamicas.NoDuplo;
import repository.OverflowException;
import repository.UnderflowException;

public class ListaDinamica implements Listavel {
    private NoDuplo ponteiroInicio;
    private NoDuplo ponteiroFim;
    private int tamanhoMaximo;
    private int quantidade;

    public ListaDinamica(int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("capacidade deve ser maior que zero.");
        }
        ponteiroInicio = null;
        ponteiroFim = null;
        this.tamanhoMaximo = tamanhoMaximo;
        quantidade = 0;
    }

    public ListaDinamica() {
        this(10);
    }

    @Override
    public void inserir(Object objeto, int posicao) {
        if (estaCheia()) {
            throw new OverflowException("Lista cheia");
        }
        if (posicao < 0 || posicao > quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for(int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        NoDuplo ant = (ponteiroAuxiliar != null) ? ponteiroAuxiliar.getAnterior() : ponteiroFim;
        NoDuplo prox = ponteiroAuxiliar;

        NoDuplo novoNo = new NoDuplo(objeto);
        novoNo.setProximo(prox);
        novoNo.setAnterior(ant);
        if (prox != null)
            prox.setAnterior(novoNo);
        else
            ponteiroFim = novoNo;


        if (ant != null)
            ant.setProximo(novoNo);
        else
            ponteiroInicio = novoNo;

        quantidade++;
    }

    @Override
    public void anexar(Object objeto) {
        if (estaCheia()) {
            throw new OverflowException("Lista cheia");
        }
        NoDuplo novoNo = new NoDuplo(objeto);
        novoNo.setAnterior(ponteiroFim);
        if (!estaVazia()) {
            ponteiroFim.setProximo(novoNo);
        } else {
            ponteiroInicio = novoNo;
        }
        ponteiroFim = novoNo;
        quantidade++;
    }

    @Override
    public Object selecionar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return ponteiroAuxiliar.getDado();
    }

    @Override
    public Object[] selecionarTodos() {
        Object[] arrayRetorno = new Object[quantidade];
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = ponteiroAuxiliar.getDado();
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return arrayRetorno;
    }

    @Override
    public void atualizar(Object objeto, int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        ponteiroAuxiliar.setDado(objeto);
    }

    @Override
    public Object apagar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        Object retorno = ponteiroAuxiliar.getDado();
        NoDuplo ant = ponteiroAuxiliar.getAnterior();
        NoDuplo prox = ponteiroAuxiliar.getProximo();
        if (ant != null) {
            ant.setProximo(prox);
        }else {
            ponteiroInicio = ponteiroInicio.getProximo();
        }
        if (prox != null) {
            prox.setAnterior(ant);
        } else {
            ponteiroFim = ponteiroFim.getAnterior();
        }
        quantidade--;
        return retorno;
    }

    @Override
    public void limpar() {
        ponteiroInicio = ponteiroFim = null;
        quantidade = 0;
    }

    @Override
    public int tamanho() {
        return quantidade;
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

    private NoDuplo noNaPosicao(int posicao) {
        NoDuplo p = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            p = p.getProximo();
        }
        return p;
    }
}
