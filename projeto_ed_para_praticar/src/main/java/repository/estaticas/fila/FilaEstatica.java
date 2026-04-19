package repository.estaticas.fila;

import repository.Enfileiravel;

public class FilaEstatica implements Enfileiravel {
    protected Object[] dados;
    protected int ponteiroFim;
    protected int ponteiroInicio;
    protected int quantidade;

    //construtores
    public FilaEstatica(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da fila deve ser maior que zero.");
        }
        dados = new Object[tamanho];
        ponteiroInicio = 0;
        ponteiroFim = -1;
        quantidade = 0;
    }

    public FilaEstatica() {
        this(10);
    }

    @Override
    public void limpar() {
        ponteiroInicio = 0;
        ponteiroFim = -1;
        quantidade = 0;
    }
    
    @Override
    public void enfileirar(Object objeto) {
        if (!estaCheia()) {
            if (estaVazia()) {
                ponteiroFim = ponteiroInicio;
            } else {
                ponteiroFim = avancarPonteiro(ponteiroFim);
            }
            dados[ponteiroFim] = objeto;
            quantidade++;
        } else {
            System.err.println("Fila cheia");
        }
    }

    @Override
    public Object desenfileirar() {
        Object objetoRetorno = null;
        if (!estaVazia()) {
            objetoRetorno = dados[ponteiroInicio];
            ponteiroInicio = avancarPonteiro(ponteiroInicio);
            quantidade--;
        } else {
            System.err.println("Fila vazia");
        }
        return objetoRetorno;
    }

	@Override
    public void atualizarFim(Object objeto) {
        if (!estaVazia()) {
            dados[ponteiroFim] = objeto;
        } else {
            System.err.println("Fila vazia");
        }
    }

    @Override
    public void atualizarInicio(Object objeto) {
        if (!estaVazia()) {
            dados[ponteiroInicio] = objeto;
        } else {
            System.err.println("Fila vazia");
        }
    }
    
    @Override
    public Object frente() {
        if (!estaVazia()) {
        return dados[ponteiroInicio];
        } else {
            System.err.println("Fila vazia");
        }
        return null;
    }

    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    @Override
    public boolean estaCheia() {
        return quantidade == dados.length;
    }

    @Override
    public String imprimir() {
        String resultado = "";
        for(int i = 0; i < quantidade; i++) {
            resultado += dados[(i+ponteiroInicio) % dados.length];
            if  (i != quantidade - 1)
                resultado += ",";

        }
        return "[ " + resultado + " ]";
    }

    protected int avancarPonteiro(int ponteiro) {
        return (ponteiro + 1) % dados.length;
    }

    protected int retrocederPonteiro(int ponteiro) {
        return (ponteiro - 1 + dados.length) % dados.length;
    }
}