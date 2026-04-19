package repository.estaticas.lista;

import repository.Listavel;

public class ListaEstatica implements Listavel {
    //variáveis de instância
    private Object[] dados;
    private int quantidade;
    private int ponteiroFim;
    private int ponteiroInicio;

    //construtores
    public ListaEstatica(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da lista deve ser maior que zero.");
        }
        dados = new Object[tamanho];
        ponteiroInicio = 0;
        ponteiroFim = -1;
        quantidade = 0;
    }

    public ListaEstatica() {
        this(10);
    }

    @Override
    public int tamanho() {
        return quantidade;
    }

    @Override
    public void inserir(Object objeto, int posicao) {
        if (!estaCheia()) {
            if (posicao >= 0 && posicao <= quantidade) {
                if (posicao <= quantidade/2) {
                    estrategiaInsercaoInicio(posicao);
                } else {
                    estrategiaInsercaoFim(posicao);
                }
                dados[mapearPosicaoFisica(posicao)] = objeto;
                quantidade++;
            } else {
                System.err.println("Posição inválida");
            }
        } else {
            System.err.println("Lista cheia");
        }
    }

    private void estrategiaInsercaoInicio(int posicao) {
        if (estaVazia()) {
            ponteiroFim = ponteiroInicio;
            return;
        }

        for (int i = 0; i < posicao; i++) {
            int depois = mapearPosicaoFisica(i);
            int antes = retrocederPonteiro(depois);
            dados[antes] = dados[depois];
        }
        ponteiroInicio = retrocederPonteiro(ponteiroInicio);
    }

    private void estrategiaInsercaoFim(int posicao) {
        if (estaVazia()) {
            ponteiroFim = ponteiroInicio;
            return;
        }
        for (int i = quantidade - 1; i >= posicao; i--) {
            int depois = mapearPosicaoFisica(i + 1);
            int antes = retrocederPonteiro(depois);
            dados[depois] = dados[antes];
        }
        ponteiroFim = avancarPonteiro(ponteiroFim);
    }

    @Override
    public Object apagar(int posicao) {
        Object objetoRetorno = null;
        if (posicao >= 0 && posicao < quantidade) {
            objetoRetorno = dados[mapearPosicaoFisica(posicao)];
            if (posicao <= quantidade/2) {
                estrategiaRemocaoInicio(posicao);
            } else {
                estrategiaRemocaoFim(posicao);
            }
            quantidade--;
        } else {
            System.err.println("Posição inválida");
        }
        return objetoRetorno;
    }

    private void estrategiaRemocaoInicio(int posicao) {
        for (int i = posicao; i > 0; i--) {
            int depois = mapearPosicaoFisica(i);
            int antes = retrocederPonteiro(depois);
            dados[depois] = dados[antes];
        }
        ponteiroInicio = avancarPonteiro(ponteiroInicio);
    }

    private void estrategiaRemocaoFim(int posicao) {
        for (int i = posicao; i < quantidade - 1; i++) {
            int antes = mapearPosicaoFisica(i);
            int depois = avancarPonteiro(antes);
            dados[antes] = dados[depois];
        }
        ponteiroFim = retrocederPonteiro(ponteiroFim);
    }

    @Override
    public void limpar() {
        ponteiroInicio = 0;
        ponteiroFim = -1;
        quantidade = 0;
    }

    @Override
    //identico ao enfileirar da FilaEstatica
    public void anexar(Object objeto) {
        if (!estaCheia()) {
            if (estaVazia()) {
                ponteiroFim = ponteiroInicio;
            } else {
                ponteiroFim = avancarPonteiro(ponteiroFim);
            }
            dados[ponteiroFim] = objeto;
            quantidade++;
        } else {
            System.err.println("Lista cheia");
        }
    }

    @Override
    public void atualizar(Object objeto, int posicao) {
        if (posicao >= 0 && posicao < quantidade) {
            int posicaoFisica = mapearPosicaoFisica(posicao); 
            dados[posicaoFisica] = objeto;
        } else {
            System.err.println("Posição inválida");
        }
    }

    @Override
    public Object selecionar(int posicao) {
        Object objetoRetorno = null;
        if (posicao >= 0 && posicao < quantidade) {
            int posicaoFisica = mapearPosicaoFisica(posicao);
            objetoRetorno = dados[posicaoFisica];
        } else {
            System.err.println("Posição inválida");
        }
        return objetoRetorno;
    }

    @Override
    public Object[] selecionarTodos() {
        Object[] arrayRetorno = new Object[quantidade];
        int ponteiroTemporario = ponteiroInicio;
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = dados[ponteiroTemporario];
            ponteiroTemporario = avancarPonteiro(ponteiroTemporario);
        } 
        return arrayRetorno;
    }

    //métodos auxiliares
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
        for (int i = 0; i < quantidade; i++) {
            resultado += dados[(i+ponteiroInicio) % dados.length];
            if (i != quantidade - 1)
                resultado += ",";
        }
        return "[ " + resultado + " ]";
    }

    private int mapearPosicaoFisica(int posicao) {
        return (posicao + ponteiroInicio) % dados.length;
    }

    private int avancarPonteiro(int ponteiro) {
        return (ponteiro + 1) % dados.length;
    }

    private int retrocederPonteiro(int ponteiro) {
        return (ponteiro - 1 + dados.length) % dados.length;
    }
}
