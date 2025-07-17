/**
 * Interface para estruturas de dados em árvore.
 * Define operações básicas para árvores, incluindo inserção, remoção, busca e navegação.
 * Esta interface é implementada por diferentes tipos de árvores como Árvore B, AVL, ABP, etc.
 * 
 * <p>A interface garante que todos os elementos sejam comparáveis, permitindo
 * operações de ordenação e busca eficientes.</p>
 * 
 * <p><strong>Exemplo de uso:</strong></p>
 * <pre>{@code
 * Arborizavel<Integer> arvore = new ArvoreB<>(3);
 * arvore.inserir(10);
 * arvore.inserir(5);
 * arvore.inserir(15);
 * 
 * if (arvore.existe(10)) {
 *     System.out.println("Elemento encontrado!");
 * }
 * 
 * String elementos = arvore.imprimirEmOrdem();
 * System.out.println("Elementos em ordem: " + elementos);
 * }</pre>
 * 
 * @param <T> Tipo dos dados armazenados na árvore. Deve implementar Comparable.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public interface Arborizavel<T extends Comparable<T>> {
    
    /**
     * Insere um elemento na árvore.
     * A inserção mantém as propriedades da árvore, garantindo que ela permaneça
     * balanceada e ordenada.
     * 
     * <p><strong>Complexidade:</strong> O(log n) para árvores balanceadas</p>
     * 
     * @param dado Elemento a ser inserido na árvore.
     * @throws IllegalArgumentException se o dado for null.
     */
    void inserir(T dado);
    
    /**
     * Remove um elemento da árvore.
     * A remoção mantém as propriedades da árvore, reorganizando os nós conforme necessário.
     * 
     * <p><strong>Complexidade:</strong> O(log n) para árvores balanceadas</p>
     * 
     * @param dado Elemento a ser removido da árvore.
     * @return Elemento removido, ou null se o elemento não foi encontrado.
     * @throws IllegalArgumentException se o dado for null.
     */
    T apagar(T dado);
    
    /**
     * Verifica se um elemento existe na árvore.
     * Realiza uma busca eficiente para determinar se o elemento está presente.
     * 
     * <p><strong>Complexidade:</strong> O(log n) para árvores balanceadas</p>
     * 
     * @param dado Elemento a ser buscado na árvore.
     * @return true se o elemento existe na árvore, false caso contrário.
     * @throws IllegalArgumentException se o dado for null.
     */
    boolean existe(T dado);
    
    /**
     * Remove todos os elementos da árvore.
     * Após esta operação, a árvore fica vazia e pronta para receber novos elementos.
     * 
     * <p><strong>Complexidade:</strong> O(1)</p>
     */
    void limpar();
    
    /**
     * Retorna uma String com os elementos em ordem crescente.
     * A travessia em ordem visita os elementos do menor para o maior.
     * 
     * <p><strong>Complexidade:</strong> O(n) onde n é o número de elementos</p>
     * 
     * @return String contendo todos os elementos da árvore em ordem crescente,
     *         separados por espaços. Retorna string vazia se a árvore estiver vazia.
     */
    String imprimirEmOrdem();
    
    /**
     * Retorna o nó raiz da árvore.
     * O nó raiz é o ponto de entrada para navegar pela estrutura da árvore.
     * 
     * @return Nó raiz da árvore, ou null se a árvore estiver vazia.
     */
    PaginaArvoreB<T> getRaiz();
} 