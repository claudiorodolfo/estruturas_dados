public class ArvoreB<T extends Comparable<T>> {
    private NoArvoreB<T> raiz;
    private int ordem;

    public ArvoreB(int ordem) {
        this.ordem = ordem;
        raiz = null;
    }

    public void inserir(T chave) {
        if (raiz == null) {
            raiz = new NoArvoreB<>(ordem);
            raiz.chaves.add(chave);
        } else {
            if (raiz.cheio()) {
                NoArvoreB<T> novaRaiz = new NoArvoreB<>(ordem);
                novaRaiz.ponteirosFilhos.add(raiz);
                novaRaiz.dividirFilho(0);
                novaRaiz.inserirNaoCheio(chave);
                raiz = novaRaiz;
            } else {
                raiz.inserirNaoCheio(chave);
            }
        }
    }

    public void apagar(T chave) {
        if (raiz == null) return;

        raiz.apagar(chave);

        if (raiz.chaves.size() == 0) {
            if (!raiz.isFolha()) {
                raiz = raiz.ponteirosFilhos.get(0);
            } else {
                raiz = null;
            }
        }
    }

    public NoArvoreB<T> buscar(T chave) {
        if (raiz == null) return null;
        return raiz.buscar(chave);
    }

    public void imprimirEmOrdem() {
        if (raiz != null) {
            raiz.imprimirEmOrdem();
            System.out.println();
        } else {
            System.out.println("Árvore vazia.");
        }
    }
}
