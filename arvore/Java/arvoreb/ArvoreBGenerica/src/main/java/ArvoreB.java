public class ArvoreB<T extends Comparable<T>> implements Arborizavel<T> {
    private NoArvoreB<T> raiz;
    private final int ordem;

    public ArvoreB() {
        this(6);
    }

    public ArvoreB(int ordem) {
        if (ordem < 3)
            throw new IllegalArgumentException("A ordem da árvore B deve ser pelo menos 3.");
        this.ordem = ordem;
        this.raiz = new NoArvoreB<>(ordem);
    }

    @Override
    public void inserir(T valor) {
        if (raiz.cheio()) {
            NoArvoreB<T> novaRaiz = new NoArvoreB<>(ordem);
            novaRaiz.ponteirosFilhos.add(raiz);
            novaRaiz.dividirFilho(0);
            raiz = novaRaiz;
        }
        raiz.inserirNaoCheio(valor);
    }

    @Override
    public boolean existe(T valor) {
        return raiz.buscar(valor) != null;
    }

    @Override
    public T apagar(T valor) {
        if (raiz == null) return null;

        raiz.apagar(valor);

        if (raiz.chaves.size() == 0) {
            if (!raiz.isFolha()) {
                raiz = raiz.ponteirosFilhos.get(0);
            } else {
                raiz = null;
            }
        }
        return valor; // Retorna o valor removido (simplificado)
    }

    @Override
    public void limpar() {
        raiz = new NoArvoreB<>(ordem);
    }

    @Override
    public NoArvoreB<T> getRaiz() {
        return raiz;
    }

    @Override
    public String imprimirEmOrdem() {
        StringBuilder sb = new StringBuilder();
        emOrdem(raiz, sb);
        return sb.toString();
    }

    private void emOrdem(NoArvoreB<T> no, StringBuilder sb) {
        for (int i = 0; i < no.chaves.size(); i++) {
            if (!no.isFolha()) {
                emOrdem(no.ponteirosFilhos.get(i), sb);
            }
            sb.append(no.chaves.get(i)).append(" ");
        }
        if (!no.isFolha()) {
            emOrdem(no.ponteirosFilhos.get(no.chaves.size()), sb);
        }
    }

    public NoArvoreB<T> buscar(T valor) {
        return raiz.buscar(valor);
    }

    public void exibirEmOrdem() {
        if (raiz != null) {
            raiz.imprimirEmOrdem();
            System.out.println();
        } else {
            System.out.println("Árvore vazia.");
        }
    }
}
