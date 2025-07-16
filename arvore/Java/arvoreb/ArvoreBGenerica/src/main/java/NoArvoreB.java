import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó de Árvore B.
 * Cada nó pode conter múltiplas chaves e múltiplos filhos.
 * @param <T> Tipo dos dados armazenados no nó.
 */
public class NoArvoreB<T extends Comparable<T>> {
    private List<T> chaves;
    private List<NoArvoreB<T>> ponteirosFilhos;
    private boolean folha;
    private int ordem;

    public NoArvoreB(int ordem) {
        this.ordem = ordem;
        this.chaves = new ArrayList<>();
        this.ponteirosFilhos = new ArrayList<>();
        this.folha = true;
    }

    public List<T> getChaves() { return chaves; }
    public void setChaves(List<T> chaves) { this.chaves = chaves; }
    public List<NoArvoreB<T>> getFilhos() { return ponteirosFilhos; }
    public void setFilhos(List<NoArvoreB<T>> ponteirosFilhos) { this.ponteirosFilhos = ponteirosFilhos; }
    public boolean isFolha() { return folha; }
    public void setFolha(boolean folha) { this.folha = folha; }
    public int getOrdem() { return ordem; }
    public int getNumeroChaves() { return chaves.size(); }
    public boolean isCheio() { return chaves.size() == ordem - 1; }

    /**
     * Insere um valor em um nó que não está cheio.
     */
    public void inserirNaoCheio(T valor) {
        int i = chaves.size() - 1;
        if (folha) {
            chaves.add(null); // espaço para nova chave
            while (i >= 0 && valor.compareTo(chaves.get(i)) < 0) {
                chaves.set(i + 1, chaves.get(i));
                i--;
            }
            chaves.set(i + 1, valor);
        } else {
            while (i >= 0 && valor.compareTo(chaves.get(i)) < 0) {
                i--;
            }
            i++;
            if (ponteirosFilhos.get(i).isCheio()) {
                dividirFilho(i);
                if (valor.compareTo(chaves.get(i)) > 0) {
                    i++;
                }
            }
            ponteirosFilhos.get(i).inserirNaoCheio(valor);
        }
    }

    /**
     * Divide o filho i deste nó.
     */
    public void dividirFilho(int i) {
        NoArvoreB<T> y = ponteirosFilhos.get(i);
        NoArvoreB<T> z = new NoArvoreB<>(ordem);
        z.folha = y.folha;
        int t = ordem / 2;
        // Move as chaves do meio para frente para z
        for (int j = 0; j < t - 1; j++) {
            z.chaves.add(y.chaves.remove(t));
        }
        // Se não for folha, move os filhos também
        if (!y.folha) {
            for (int j = 0; j < t; j++) {
                z.ponteirosFilhos.add(y.ponteirosFilhos.remove(t));
            }
        }
        chaves.add(i, y.chaves.remove(t - 1));
        ponteirosFilhos.add(i + 1, z);
    }

    /**
     * Busca um valor neste nó ou nos filhos.
     */
    public NoArvoreB<T> buscar(T valor) {
        int i = 0;
        while (i < chaves.size() && valor.compareTo(chaves.get(i)) > 0) {
            i++;
        }
        if (i < chaves.size() && valor.compareTo(chaves.get(i)) == 0) {
            return this;
        }
        if (folha) {
            return null;
        }
        return ponteirosFilhos.get(i).buscar(valor);
    }

    /**
     * Percursos para impressão
     */
    public void preOrdem(StringBuilder sb) {
        for (T chave : chaves) {
            sb.append(chave).append(" ");
        }
        if (!folha) {
            for (NoArvoreB<T> filho : ponteirosFilhos) {
                filho.preOrdem(sb);
            }
        }
    }
    public void emOrdem(StringBuilder sb) {
        for (int i = 0; i < chaves.size(); i++) {
            if (!folha) {
                ponteirosFilhos.get(i).emOrdem(sb);
            }
            sb.append(chaves.get(i)).append(" ");
        }
        if (!folha) {
            ponteirosFilhos.get(chaves.size()).emOrdem(sb);
        }
    }
    public void posOrdem(StringBuilder sb) {
        if (!folha) {
            for (NoArvoreB<T> filho : ponteirosFilhos) {
                filho.posOrdem(sb);
            }
        }
        for (T chave : chaves) {
            sb.append(chave).append(" ");
        }
    }
    public void exibir() {
        for (int i = 0; i < chaves.size(); i++) {
            if (!folha) {
                ponteirosFilhos.get(i).exibir();
            }
            System.out.print(chaves.get(i) + " ");
        }
        if (!folha) {
            ponteirosFilhos.get(chaves.size()).exibir();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < chaves.size(); i++) {
            sb.append(chaves.get(i));
            if (i < chaves.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
} 