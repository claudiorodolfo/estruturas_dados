import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó de uma Árvore B.
 * Cada nó contém uma lista de chaves ordenadas e ponteiros para nós filhos.
 * A Árvore B é uma estrutura de dados auto-balanceada que mantém os dados ordenados
 * e permite operações de busca, inserção e remoção em tempo logarítmico.
 * 
 * <p><strong>Propriedades da Árvore B:</strong></p>
 * <ul>
 *   <li>Todos os nós folha estão no mesmo nível</li>
 *   <li>Cada nó não-raiz tem pelo menos ⌈m/2⌉ - 1 chaves</li>
 *   <li>Cada nó tem no máximo m - 1 chaves</li>
 *   <li>Um nó com k chaves tem k + 1 filhos</li>
 * </ul>
 * 
 * <p><strong>Exemplo de uso:</strong></p>
 * <pre>{@code
 * NoArvoreB<Integer> no = new NoArvoreB<>(3);
 * no.inserirNaoCheio(10);
 * no.inserirNaoCheio(5);
 * no.inserirNaoCheio(15);
 * 
 * NoArvoreB<Integer> resultado = no.buscar(10);
 * if (resultado != null) {
 *     System.out.println("Chave encontrada!");
 * }
 * }</pre>
 * 
 * @param <T> Tipo das chaves armazenadas no nó. Deve implementar Comparable.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class NoArvoreB<T extends Comparable<T>> {
    /** Lista de chaves ordenadas no nó */
    List<T> chaves;
    /** Lista de ponteiros para nós filhos */
    List<NoArvoreB<T>> ponteirosFilhos;
    /** Ordem da árvore B (m) */
    int ordem;

    /**
     * Constrói um novo nó de árvore B com a ordem especificada.
     * 
     * @param ordem A ordem da árvore B. Deve ser maior ou igual a 3.
     * @throws IllegalArgumentException se a ordem for menor que 3.
     */
    public NoArvoreB(int ordem) {
        if (ordem < 3) {
            throw new IllegalArgumentException("A ordem da árvore B deve ser pelo menos 3.");
        }
        this.ordem = ordem;
        this.chaves = new ArrayList<>();
        this.ponteirosFilhos = new ArrayList<>();
    }

    /**
     * Verifica se este nó é uma folha (não tem filhos).
     * 
     * @return true se o nó é uma folha, false caso contrário.
     */
    public boolean isFolha() {
        return ponteirosFilhos.isEmpty();
    }

    /**
     * Verifica se este nó está cheio (tem o número máximo de chaves).
     * 
     * @return true se o nó está cheio, false caso contrário.
     */
    public boolean cheio() {
        return chaves.size() == 2 * ordem - 1;
    }

    /**
     * Insere uma chave em um nó que não está cheio.
     * Esta operação mantém as chaves ordenadas e pode causar divisão de nós filhos.
     * 
     * <p><strong>Algoritmo:</strong></p>
     * <ol>
     *   <li>Se o nó é folha, insere a chave na posição correta</li>
     *   <li>Se não é folha, encontra o filho apropriado</li>
     *   <li>Se o filho está cheio, divide-o antes de inserir</li>
     *   <li>Recursivamente insere no filho correto</li>
     * </ol>
     * 
     * @param chave A chave a ser inserida.
     * @throws IllegalArgumentException se a chave for null.
     */
    public void inserirNaoCheio(T chave) {
        if (chave == null) {
            throw new IllegalArgumentException("Chave não pode ser null.");
        }
        
        int i = chaves.size() - 1;

        if (isFolha()) {
            // Inserção em nó folha
            chaves.add(null);
            while (i >= 0 && chave.compareTo(chaves.get(i)) < 0) {
                chaves.set(i + 1, chaves.get(i));
                i--;
            }
            chaves.set(i + 1, chave);
        } else {
            // Inserção em nó interno
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

    /**
     * Divide um filho cheio em dois nós.
     * Esta operação é necessária para manter as propriedades da árvore B
     * quando um filho fica cheio durante a inserção.
     * 
     * <p><strong>Processo de divisão:</strong></p>
     * <ol>
     *   <li>Move metade das chaves do filho para um novo nó</li>
     *   <li>Move metade dos ponteiros filhos (se não for folha)</li>
     *   <li>Promove a chave do meio para o nó pai</li>
     *   <li>Adiciona o novo nó como filho</li>
     * </ol>
     * 
     * @param i Índice do filho a ser dividido.
     * @throws IndexOutOfBoundsException se o índice for inválido.
     */
    public void dividirFilho(int i) {
        if (i < 0 || i >= ponteirosFilhos.size()) {
            throw new IndexOutOfBoundsException("Índice do filho inválido: " + i);
        }
        
        NoArvoreB<T> filhoCheio = ponteirosFilhos.get(i);
        NoArvoreB<T> novoFilho = new NoArvoreB<>(filhoCheio.ordem);

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

    /**
     * Busca uma chave na subárvore enraizada neste nó.
     * 
     * <p><strong>Complexidade:</strong> O(log n) onde n é o número de nós na árvore</p>
     * 
     * @param valor A chave a ser buscada.
     * @return O nó que contém a chave, ou null se a chave não for encontrada.
     * @throws IllegalArgumentException se o valor for null.
     */
    public NoArvoreB<T> buscar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
        
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

    /**
     * Remove uma chave da subárvore enraizada neste nó.
     * Esta operação mantém as propriedades da árvore B através de rebalanceamento.
     * 
     * <p><strong>Casos de remoção:</strong></p>
     * <ol>
     *   <li>Chave está em um nó folha</li>
     *   <li>Chave está em um nó interno</li>
     *   <li>Chave não está no nó atual</li>
     * </ol>
     * 
     * @param valor A chave a ser removida.
     * @throws IllegalArgumentException se o valor for null.
     */
    public void apagar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
        
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

    /**
     * Remove uma chave de um nó interno.
     * Substitui a chave pelo antecessor ou sucessor, ou junta nós se necessário.
     * 
     * @param idx Índice da chave a ser removida.
     */
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

    /**
     * Obtém o antecessor de uma chave (maior chave na subárvore esquerda).
     * 
     * @param idx Índice da chave.
     * @return O antecessor da chave.
     */
    private T obterAntecessor(int idx) {
        NoArvoreB<T> atual = ponteirosFilhos.get(idx);
        while (!atual.isFolha()) {
            atual = atual.ponteirosFilhos.get(atual.ponteirosFilhos.size() - 1);
        }
        return atual.chaves.get(atual.chaves.size() - 1);
    }

    /**
     * Obtém o sucessor de uma chave (menor chave na subárvore direita).
     * 
     * @param idx Índice da chave.
     * @return O sucessor da chave.
     */
    private T obterSucessor(int idx) {
        NoArvoreB<T> atual = ponteirosFilhos.get(idx + 1);
        while (!atual.isFolha()) {
            atual = atual.ponteirosFilhos.get(0);
        }
        return atual.chaves.get(0);
    }

    /**
     * Preenche um filho que tem menos chaves que o mínimo necessário.
     * Pode emprestar chaves de irmãos ou juntar nós.
     * 
     * @param idx Índice do filho a ser preenchido.
     */
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

    /**
     * Empresta uma chave do irmão anterior.
     * 
     * @param idx Índice do filho que receberá a chave.
     */
    private void emprestarDoAnterior(int idx) {
        NoArvoreB<T> filho = ponteirosFilhos.get(idx);
        NoArvoreB<T> irmao = ponteirosFilhos.get(idx - 1);

        filho.chaves.add(0, chaves.get(idx - 1));

        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.add(0, irmao.ponteirosFilhos.remove(irmao.ponteirosFilhos.size() - 1));
        }

        chaves.set(idx - 1, irmao.chaves.remove(irmao.chaves.size() - 1));
    }

    /**
     * Empresta uma chave do irmão próximo.
     * 
     * @param idx Índice do filho que receberá a chave.
     */
    private void emprestarDoProximo(int idx) {
        NoArvoreB<T> filho = ponteirosFilhos.get(idx);
        NoArvoreB<T> irmao = ponteirosFilhos.get(idx + 1);

        filho.chaves.add(chaves.get(idx));

        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.add(irmao.ponteirosFilhos.remove(0));
        }

        chaves.set(idx, irmao.chaves.remove(0));
    }

    /**
     * Junta este nó com o próximo irmão.
     * 
     * @param idx Índice do filho que será juntado com o próximo.
     */
    private void juntar(int idx) {
        NoArvoreB<T> filho = ponteirosFilhos.get(idx);
        NoArvoreB<T> irmao = ponteirosFilhos.get(idx + 1);

        filho.chaves.add(chaves.remove(idx));
        filho.chaves.addAll(irmao.chaves);

        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.addAll(irmao.ponteirosFilhos);
        }

        ponteirosFilhos.remove(idx + 1);
    }

    /**
     * Encontra o índice onde uma chave deveria estar ou está.
     * 
     * @param valor A chave a ser localizada.
     * @return O índice onde a chave está ou deveria estar.
     */
    private int encontrarIndice(T valor) {
        int idx = 0;
        while (idx < chaves.size() && chaves.get(idx).compareTo(valor) < 0) {
            idx++;
        }
        return idx;
    }

    /**
     * Imprime as chaves da subárvore em ordem (travessia in-order).
     * Esta é uma versão que imprime diretamente no console.
     * 
     * @see #imprimirEmOrdem()
     */
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
}
