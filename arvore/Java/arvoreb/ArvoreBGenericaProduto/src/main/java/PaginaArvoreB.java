import java.util.ArrayList;
import java.util.List;

public class PaginaArvoreB<T extends Comparable<T>> {
    List<T> chaves;
    List<PaginaArvoreB<T>> ponteirosFilhos;
    int ordem;

    public PaginaArvoreB(int ordem) {
        this.ordem = ordem;
        this.chaves = new ArrayList<>();
        this.ponteirosFilhos = new ArrayList<>();
    }

    public List<T> getChaves() { return chaves; }
    public void setChaves(List<T> chaves) { this.chaves = chaves; }
    public List<PaginaArvoreB<T>> getFilhos() { return ponteirosFilhos; }
    public void setFilhos(List<PaginaArvoreB<T>> ponteirosFilhos) { this.ponteirosFilhos = ponteirosFilhos; }
    public int getOrdem() { return ordem; }
    public int getNumeroChaves() { return chaves.size(); }
    public boolean isCheio() { return chaves.size() == 2 * ordem - 1; }
    public boolean cheio() { return isCheio(); }
    public boolean isFolha() { return ponteirosFilhos.isEmpty(); }

    public void adicionarChave(T chave) {
        chaves.add(chave);
        chaves.sort(null);
    }

    public boolean removerChave(T chave) {
        return chaves.remove(chave);
    }

    public void adicionarFilho(PaginaArvoreB<T> filho) {
        ponteirosFilhos.add(filho);
    }

    public boolean removerFilho(PaginaArvoreB<T> filho) {
        return ponteirosFilhos.remove(filho);
    }

    public void inserirNaoCheio(T chave) {
        int i = chaves.size() - 1;

        if (isFolha()) {
            chaves.add(null);
            while (i >= 0 && chave.compareTo(chaves.get(i)) < 0) {
                chaves.set(i + 1, chaves.get(i));
                i--;
            }
            chaves.set(i + 1, chave);
        } else {
            while (i >= 0 && chave.compareTo(chaves.get(i)) < 0) {
                i--;
            }
            i++;
            if (ponteirosFilhos.get(i).cheio()) {
                dividirFilho(i);
                if (chave.compareTo(chaves.get(i)) > 0) {
                    i++;
                }
            }
            ponteirosFilhos.get(i).inserirNaoCheio(chave);
        }
    }

    public void dividirFilho(int i) {
        PaginaArvoreB<T> filhoCheio = ponteirosFilhos.get(i);
        PaginaArvoreB<T> novoFilho = new PaginaArvoreB<>(filhoCheio.ordem);

        int t = ordem;

        // Move as chaves da segunda metade para novoFilho
        for (int j = 0; j < t - 1; j++) {
            novoFilho.chaves.add(filhoCheio.chaves.remove(t));
        }

        // Se não for folha, move os filhos também
        if (!filhoCheio.isFolha()) {
            for (int j = 0; j < t; j++) {
                novoFilho.ponteirosFilhos.add(filhoCheio.ponteirosFilhos.remove(t));
            }
        }

        chaves.add(i, filhoCheio.chaves.remove(t - 1));
        ponteirosFilhos.add(i + 1, novoFilho);
    }

    public PaginaArvoreB<T> buscar(T valor) {
        int i = 0;
        while (i < chaves.size() && valor.compareTo(chaves.get(i)) > 0) {
            i++;
        }
        if (i < chaves.size() && valor.compareTo(chaves.get(i)) == 0) {
            return this;
        }
        if (isFolha()) {
            return null;
        }
        return ponteirosFilhos.get(i).buscar(valor);
    }

    public void apagar(T valor) {
        int idx = encontrarIndice(valor);

        if (idx < chaves.size() && chaves.get(idx).compareTo(valor) == 0) {
            if (isFolha()) {
                chaves.remove(idx);
            } else {
                removerDeNaoFolha(idx);
            }
        } else {
            if (isFolha()) return;

            boolean ultimaChave = (idx == chaves.size());

            if (ponteirosFilhos.get(idx).chaves.size() < ordem) {
                preencher(idx);
            }

            if (ultimaChave && idx > chaves.size()) {
                ponteirosFilhos.get(idx - 1).apagar(valor);
            } else {
                ponteirosFilhos.get(idx).apagar(valor);
            }
        }
    }

    private void removerDeNaoFolha(int idx) {
        T chave = chaves.get(idx);

        if (ponteirosFilhos.get(idx).chaves.size() >= ordem) {
            T antecessor = obterAntecessor(idx);
            chaves.set(idx, antecessor);
            ponteirosFilhos.get(idx).apagar(antecessor);
        } else if (ponteirosFilhos.get(idx + 1).chaves.size() >= ordem) {
            T sucessor = obterSucessor(idx);
            chaves.set(idx, sucessor);
            ponteirosFilhos.get(idx + 1).apagar(sucessor);
        } else {
            juntar(idx);
            ponteirosFilhos.get(idx).apagar(chave);
        }
    }

    private T obterAntecessor(int idx) {
        PaginaArvoreB<T> atual = ponteirosFilhos.get(idx);
        while (!atual.isFolha()) {
            atual = atual.ponteirosFilhos.get(atual.ponteirosFilhos.size() - 1);
        }
        return atual.chaves.get(atual.chaves.size() - 1);
    }

    private T obterSucessor(int idx) {
        PaginaArvoreB<T> atual = ponteirosFilhos.get(idx + 1);
        while (!atual.isFolha()) {
            atual = atual.ponteirosFilhos.get(0);
        }
        return atual.chaves.get(0);
    }

    private void preencher(int idx) {
        if (idx != 0 && ponteirosFilhos.get(idx - 1).chaves.size() >= ordem) {
            emprestarDoAnterior(idx);
        } else if (idx != chaves.size() && ponteirosFilhos.get(idx + 1).chaves.size() >= ordem) {
            emprestarDoProximo(idx);
        } else {
            if (idx != chaves.size()) {
                juntar(idx);
            } else {
                juntar(idx - 1);
            }
        }
    }

    private void emprestarDoAnterior(int idx) {
        PaginaArvoreB<T> filho = ponteirosFilhos.get(idx);
        PaginaArvoreB<T> irmao = ponteirosFilhos.get(idx - 1);

        filho.chaves.add(0, chaves.get(idx - 1));

        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.add(0, irmao.ponteirosFilhos.remove(irmao.ponteirosFilhos.size() - 1));
        }

        chaves.set(idx - 1, irmao.chaves.remove(irmao.chaves.size() - 1));
    }

    private void emprestarDoProximo(int idx) {
        PaginaArvoreB<T> filho = ponteirosFilhos.get(idx);
        PaginaArvoreB<T> irmao = ponteirosFilhos.get(idx + 1);

        filho.chaves.add(chaves.get(idx));

        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.add(irmao.ponteirosFilhos.remove(0));
        }

        chaves.set(idx, irmao.chaves.remove(0));
    }

    private void juntar(int idx) {
        PaginaArvoreB<T> filho = ponteirosFilhos.get(idx);
        PaginaArvoreB<T> irmao = ponteirosFilhos.get(idx + 1);

        filho.chaves.add(chaves.remove(idx));
        filho.chaves.addAll(irmao.chaves);

        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.addAll(irmao.ponteirosFilhos);
        }

        ponteirosFilhos.remove(idx + 1);
    }

    private int encontrarIndice(T valor) {
        int idx = 0;
        while (idx < chaves.size() && chaves.get(idx).compareTo(valor) < 0) {
            idx++;
        }
        return idx;
    }

    public void imprimirEmOrdem() {
        int i;
        for (i = 0; i < chaves.size(); i++) {
            if (!isFolha()) {
                ponteirosFilhos.get(i).imprimirEmOrdem();
            }
            System.out.print(chaves.get(i) + " ");
        }
        if (!isFolha()) {
            ponteirosFilhos.get(i).imprimirEmOrdem();
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