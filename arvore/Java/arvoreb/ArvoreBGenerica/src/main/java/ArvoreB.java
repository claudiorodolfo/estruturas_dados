import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementação simplificada de uma Árvore B de ordem definida pelo usuário usando ArrayList.
 * Adaptada de um código funcional, com nomes e comentários em português.
 */
public class ArvoreB implements Arborizavel<Integer> {
    private ArrayList<Integer> chaves;
    private ArrayList<ArvoreB> filhos;
    private boolean folha;
    private final int ordem;

    /**
     * Construtor padrão: ordem 6.
     */
    public ArvoreB() {
        this(6);
    }

    /**
     * Construtor que permite definir a ordem da árvore B.
     * @param ordem Ordem da árvore B (mínimo 3).
     */
    public ArvoreB(int ordem) {
        if (ordem < 3) throw new IllegalArgumentException("A ordem da árvore B deve ser pelo menos 3.");
        this.ordem = ordem;
        chaves = new ArrayList<>();
        filhos = new ArrayList<>();
        folha = true;
    }

    @Override
    public void inserir(Integer valor) {
        ArvoreB x = this;
        if (x.folha && x.chaves.size() == ordem) {
            int t = x.dividirFilho(-1);
            x = this;
            int i;
            for (i = 0; i < x.chaves.size(); i++) {
                if (valor < x.chaves.get(0)) {
                    break;
                } else if (valor > x.chaves.get(i) && (i + 1 == x.chaves.size() || valor < x.chaves.get(i + 1))) {
                    i++;
                    break;
                }
            }
            x = x.filhos.get(i);
        } else {
            while (!x.folha) {
                int i;
                for (i = 0; i < x.chaves.size(); i++) {
                    if (valor < x.chaves.get(0)) {
                        break;
                    } else if (valor > x.chaves.get(i) && (i + 1 == x.chaves.size() || valor < x.chaves.get(i + 1))) {
                        i++;
                        break;
                    }
                }
                if (x.filhos.get(i).chaves.size() == ordem) {
                    int t = x.dividirFilho(i);
                    x.chaves.add(t);
                    Collections.sort(x.chaves);
                    continue;
                }
                x = x.filhos.get(i);
            }
        }
        x.chaves.add(valor);
        Collections.sort(x.chaves);
    }

    private int dividirFilho(int i) {
        ArvoreB novoIrmao = new ArvoreB(ordem);
        novoIrmao.folha = true;
        int meio;
        int meioIdx = (ordem - 1) / 2;
        if (i == -1) {
            meio = chaves.get(meioIdx);
            chaves.remove(meioIdx);
            ArvoreB novoFilho = new ArvoreB(ordem);
            novoFilho.folha = false;
            folha = true;
            // Move as chaves após o meio para o novo irmão
            while (chaves.size() > meioIdx) {
                novoIrmao.chaves.add(chaves.get(meioIdx));
                chaves.remove(meioIdx);
            }
            // Move os filhos após o meio, se existirem
            if (!filhos.isEmpty()) {
                while (filhos.size() > meioIdx + 1) {
                    novoIrmao.filhos.add(filhos.get(meioIdx + 1));
                    filhos.remove(meioIdx + 1);
                }
            }
            novoFilho.chaves.add(meio);
            novoFilho.filhos.add(this);
            novoFilho.filhos.add(novoIrmao);
            // Atualiza referência da raiz
            this.chaves = novoFilho.chaves;
            this.filhos = novoFilho.filhos;
            this.folha = novoFilho.folha;
            return meio;
        } else {
            ArvoreB y = filhos.get(i);
            meio = y.chaves.get(meioIdx);
            y.chaves.remove(meioIdx);
            while (y.chaves.size() > meioIdx) {
                novoIrmao.chaves.add(y.chaves.get(meioIdx));
                y.chaves.remove(meioIdx);
            }
            filhos.add(i + 2, novoIrmao);
            return meio;
        }
    }

    @Override
    public boolean existe(Integer valor) {
        return buscar(this, valor);
    }

    private boolean buscar(ArvoreB no, int valor) {
        int i = 0;
        while (i < no.chaves.size() && valor > no.chaves.get(i)) {
            i++;
        }
        if (i < no.chaves.size() && valor == no.chaves.get(i)) {
            return true;
        }
        if (no.folha) {
            return false;
        }
        return buscar(no.filhos.get(i), valor);
    }

    @Override
    public Integer apagar(Integer valor) {
        // Remoção não implementada nesta versão simplificada
        throw new UnsupportedOperationException("Remoção não implementada nesta versão simplificada.");
    }

    @Override
    public void limpar() {
        chaves.clear();
        filhos.clear();
        folha = true;
    }

    @Override
    public NoArvoreB<Integer> getRaiz() {
        // Não utilizado nesta versão simplificada
        return null;
    }

    @Override
    public String imprimirPreOrdem() {
        StringBuilder sb = new StringBuilder();
        preOrdem(this, sb);
        return sb.toString();
    }

    private void preOrdem(ArvoreB no, StringBuilder sb) {
        for (int i = 0; i < no.chaves.size(); i++) {
            sb.append(no.chaves.get(i)).append(" ");
        }
        if (!no.folha) {
            for (int i = 0; i <= no.chaves.size(); i++) {
                preOrdem(no.filhos.get(i), sb);
            }
        }
    }

    @Override
    public String imprimirEmOrdem() {
        StringBuilder sb = new StringBuilder();
        emOrdem(this, sb);
        return sb.toString();
    }

    private void emOrdem(ArvoreB no, StringBuilder sb) {
        for (int i = 0; i < no.chaves.size(); i++) {
            if (!no.folha) {
                emOrdem(no.filhos.get(i), sb);
            }
            sb.append(no.chaves.get(i)).append(" ");
        }
        if (!no.folha) {
            emOrdem(no.filhos.get(no.chaves.size()), sb);
        }
    }

    @Override
    public String imprimirPosOrdem() {
        StringBuilder sb = new StringBuilder();
        posOrdem(this, sb);
        return sb.toString();
    }

    private void posOrdem(ArvoreB no, StringBuilder sb) {
        if (!no.folha) {
            for (int i = 0; i <= no.chaves.size(); i++) {
                posOrdem(no.filhos.get(i), sb);
            }
        }
        for (int i = 0; i < no.chaves.size(); i++) {
            sb.append(no.chaves.get(i)).append(" ");
        }
    }

    // Método para exibir a árvore (traverse)
    public void exibir() {
        System.out.print("Árvore B: ");
        exibir(this);
        System.out.println();
    }

    private void exibir(ArvoreB no) {
        for (int i = 0; i < no.chaves.size(); i++) {
            if (!no.folha) {
                exibir(no.filhos.get(i));
            }
            System.out.print(no.chaves.get(i) + " ");
        }
        if (!no.folha) {
            exibir(no.filhos.get(no.chaves.size()));
        }
    }
} 