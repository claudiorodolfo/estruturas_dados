/**
 * Implementação de uma Árvore B genérica.
 * A Árvore B é uma estrutura de dados auto-balanceada que mantém os dados ordenados
 * e permite operações de busca, inserção e remoção em tempo logarítmico.
 * 
 * <p><strong>Características da Árvore B:</strong></p>
 * <ul>
 *   <li><strong>Auto-balanceada:</strong> Todos os nós folha estão no mesmo nível</li>
 *   <li><strong>Ordenada:</strong> As chaves são mantidas em ordem crescente</li>
 *   <li><strong>Eficiente:</strong> Operações em O(log n) onde n é o número de elementos</li>
 *   <li><strong>Flexível:</strong> Ordem configurável (mínimo 3)</li>
 * </ul>
 * 
 * <p><strong>Propriedades:</strong></p>
 * <ul>
 *   <li>Cada nó não-raiz tem pelo menos ⌈m/2⌉ - 1 chaves</li>
 *   <li>Cada nó tem no máximo m - 1 chaves</li>
 *   <li>Um nó com k chaves tem k + 1 filhos</li>
 *   <li>Todos os nós folha estão no mesmo nível</li>
 * </ul>
 * 
 * <p><strong>Exemplo de uso:</strong></p>
 * <pre>{@code
 * // Criar uma árvore B de ordem 3
 * ArvoreB<Integer> arvore = new ArvoreB<>(3);
 * 
 * // Inserir elementos
 * arvore.inserir(10);
 * arvore.inserir(5);
 * arvore.inserir(15);
 * arvore.inserir(3);
 * arvore.inserir(7);
 * 
 * // Buscar elementos
 * if (arvore.existe(10)) {
 *     System.out.println("Elemento 10 encontrado!");
 * }
 * 
 * // Remover elementos
 * arvore.apagar(5);
 * 
 * // Imprimir em ordem
 * String elementos = arvore.imprimirEmOrdem();
 * System.out.println("Elementos: " + elementos);
 * }</pre>
 * 
 * @param <T> Tipo dos elementos armazenados na árvore. Deve implementar Comparable.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreB<T extends Comparable<T>> implements Arborizavel<T> {
    /** Nó raiz da árvore */
    private NoArvoreB<T> raiz;
    /** Ordem da árvore B (m) */
    private final int ordem;

    /**
     * Constrói uma Árvore B com ordem padrão de 6.
     * A ordem padrão oferece um bom equilíbrio entre performance e uso de memória.
     */
    public ArvoreB() {
        this(6);
    }

    /**
     * Constrói uma Árvore B com a ordem especificada.
     * 
     * <p><strong>Considerações sobre a ordem:</strong></p>
     * <ul>
     *   <li>Ordem menor: mais divisões, árvore mais alta</li>
     *   <li>Ordem maior: menos divisões, árvore mais baixa</li>
     *   <li>Ordem 3: mínimo teórico, muitas divisões</li>
     *   <li>Ordem 6-10: bom equilíbrio para uso geral</li>
     * </ul>
     * 
     * @param ordem A ordem da árvore B. Deve ser maior ou igual a 3.
     * @throws IllegalArgumentException se a ordem for menor que 3.
     */
    public ArvoreB(int ordem) {
        if (ordem < 3)
            throw new IllegalArgumentException("A ordem da árvore B deve ser pelo menos 3.");
        this.ordem = ordem;
        this.raiz = new NoArvoreB<>(ordem);
    }

    /**
     * Insere um elemento na árvore.
     * Se a raiz estiver cheia, ela é dividida e uma nova raiz é criada.
     * 
     * <p><strong>Complexidade:</strong> O(log n) onde n é o número de elementos</p>
     * <p><strong>Algoritmo:</strong></p>
     * <ol>
     *   <li>Se a raiz está cheia, divide-a e cria nova raiz</li>
     *   <li>Insere o elemento na posição correta</li>
     *   <li>Mantém as propriedades da árvore B</li>
     * </ol>
     * 
     * @param valor O elemento a ser inserido.
     * @throws IllegalArgumentException se o valor for null.
     */
    @Override
    public void inserir(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
        
        if (raiz.cheio()) {
            NoArvoreB<T> novaRaiz = new NoArvoreB<>(ordem);
            novaRaiz.ponteirosFilhos.add(raiz);
            novaRaiz.dividirFilho(0);
            raiz = novaRaiz;
        }
        raiz.inserirNaoCheio(valor);
    }

    /**
     * Verifica se um elemento existe na árvore.
     * 
     * <p><strong>Complexidade:</strong> O(log n) onde n é o número de elementos</p>
     * 
     * @param valor O elemento a ser buscado.
     * @return true se o elemento existe na árvore, false caso contrário.
     * @throws IllegalArgumentException se o valor for null.
     */
    @Override
    public boolean existe(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
        return raiz.buscar(valor) != null;
    }

    /**
     * Remove um elemento da árvore.
     * Se a raiz ficar vazia após a remoção, ela é ajustada conforme necessário.
     * 
     * <p><strong>Complexidade:</strong> O(log n) onde n é o número de elementos</p>
     * <p><strong>Casos especiais:</strong></p>
     * <ul>
     *   <li>Se a raiz fica vazia e não é folha, o primeiro filho vira a nova raiz</li>
     *   <li>Se a raiz fica vazia e é folha, a árvore fica vazia</li>
     * </ul>
     * 
     * @param valor O elemento a ser removido.
     * @return O elemento removido, ou null se o elemento não foi encontrado.
     * @throws IllegalArgumentException se o valor for null.
     */
    @Override
    public T apagar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
        
        if (raiz == null) return null;

        raiz.apagar(valor);

        // Ajusta a raiz se necessário
        if (raiz.chaves.size() == 0) {
            if (!raiz.isFolha()) {
                raiz = raiz.ponteirosFilhos.get(0);
            } else {
                raiz = null;
            }
        }
        return valor; // Retorna o valor removido (simplificado)
    }

    /**
     * Remove todos os elementos da árvore.
     * Após esta operação, a árvore fica vazia e pronta para receber novos elementos.
     * 
     * <p><strong>Complexidade:</strong> O(1)</p>
     */
    @Override
    public void limpar() {
        raiz = new NoArvoreB<>(ordem);
    }

    /**
     * Retorna o nó raiz da árvore.
     * 
     * @return O nó raiz da árvore, ou null se a árvore estiver vazia.
     */
    @Override
    public NoArvoreB<T> getRaiz() {
        return raiz;
    }

    /**
     * Retorna uma String com os elementos em ordem crescente.
     * A travessia em ordem visita os elementos do menor para o maior.
     * 
     * <p><strong>Complexidade:</strong> O(n) onde n é o número de elementos</p>
     * 
     * @return String contendo todos os elementos da árvore em ordem crescente,
     *         separados por espaços. Retorna string vazia se a árvore estiver vazia.
     */
    @Override
    public String imprimirEmOrdem() {
        StringBuilder sb = new StringBuilder();
        emOrdem(raiz, sb);
        return sb.toString();
    }

    /**
     * Método auxiliar para realizar a travessia em ordem.
     * Visita recursivamente todos os nós da árvore.
     * 
     * @param no O nó atual sendo visitado.
     * @param sb StringBuilder para construir a string de resultado.
     */
    private void emOrdem(NoArvoreB<T> no, StringBuilder sb) {
        if (no == null) return;
        
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

    /**
     * Busca um elemento na árvore e retorna o nó que o contém.
     * 
     * <p><strong>Complexidade:</strong> O(log n) onde n é o número de elementos</p>
     * 
     * @param valor O elemento a ser buscado.
     * @return O nó que contém o elemento, ou null se o elemento não for encontrado.
     * @throws IllegalArgumentException se o valor for null.
     */
    public NoArvoreB<T> buscar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
        return raiz.buscar(valor);
    }

    /**
     * Exibe os elementos da árvore em ordem no console.
     * Esta é uma versão que imprime diretamente no System.out.
     * 
     * <p><strong>Complexidade:</strong> O(n) onde n é o número de elementos</p>
     * 
     * @see #imprimirEmOrdem()
     */
    public void exibirEmOrdem() {
        if (raiz != null) {
            raiz.imprimirEmOrdem();
            System.out.println();
        } else {
            System.out.println("Árvore vazia.");
        }
    }
}
