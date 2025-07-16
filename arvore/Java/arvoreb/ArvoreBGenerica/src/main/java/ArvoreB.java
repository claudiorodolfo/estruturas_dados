import java.util.Objects;

/**
 * Implementação de uma Árvore B genérica de ordem definida pelo usuário.
 */
public class ArvoreB<T extends Comparable<T>> implements Arborizavel<T> {
    private NoArvoreB<T> raiz;
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
        if (ordem < 3)
            throw new IllegalArgumentException("A ordem da árvore B deve ser pelo menos 3.");
        this.ordem = ordem;
        this.raiz = new NoArvoreB<>(ordem);
    }

    @Override
    public void inserir(T valor) {
        if (raiz.isCheio()) {
            NoArvoreB<T> novaRaiz = new NoArvoreB<>(ordem);
            novaRaiz.setFolha(false);
            novaRaiz.getFilhos().add(raiz);
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
        // Remoção não implementada nesta versão simplificada
        throw new UnsupportedOperationException("Remoção não implementada nesta versão simplificada.");
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
    public String imprimirPreOrdem() {
        StringBuilder sb = new StringBuilder();
        raiz.preOrdem(sb);
        return sb.toString();
    }

    @Override
    public String imprimirEmOrdem() {
        StringBuilder sb = new StringBuilder();
        raiz.emOrdem(sb);
        return sb.toString();
    }

    @Override
    public String imprimirPosOrdem() {
        StringBuilder sb = new StringBuilder();
        raiz.posOrdem(sb);
        return sb.toString();
    }

    public void exibir() {
        System.out.print("Árvore B: ");
        raiz.exibir();
        System.out.println();
    }
} 