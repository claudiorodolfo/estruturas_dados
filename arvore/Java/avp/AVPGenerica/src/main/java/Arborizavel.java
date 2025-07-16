/**
 * Interface que define operações básicas para árvores binárias.
 * <p>
 * Fornece o contrato para árvores genéricas, incluindo busca, inserção, remoção e impressões.
 * </p>
 *
 * @param <T> Tipo dos dados armazenados na árvore.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public interface Arborizavel<T> {

    /**
     * Retorna o nó raiz da árvore.
     * @return Nó raiz ou null se a árvore estiver vazia.
     */
    NoTriplo<T> getRaiz();

    /**
     * Insere um elemento na árvore.
     * @param dado Elemento a ser inserido.
     */
    void inserir(T dado);

    /**
     * Remove um elemento da árvore.
     * @param dado Elemento a ser removido.
     * @return Elemento removido, ou null se não encontrado.
     */
    T apagar(T dado);

    /**
     * Verifica se um elemento existe na árvore.
     * @param dado Elemento a ser buscado.
     * @return true se o elemento existe, false caso contrário.
     */
    boolean existe(T dado);

    /**
     * Remove todos os elementos da árvore, tornando-a vazia.
     */
    void limpar();

    /**
     * Retorna uma String com os elementos em pré-ordem.
     * @return Elementos em pré-ordem, separados por vírgula.
     */
    String imprimirPreOrdem();

    /**
     * Retorna uma String com os elementos em ordem.
     * @return Elementos em ordem, separados por vírgula.
     */
    String imprimirEmOrdem();

    /**
     * Retorna uma String com os elementos em pós-ordem.
     * @return Elementos em pós-ordem, separados por vírgula.
     */
    String imprimirPosOrdem();
} 