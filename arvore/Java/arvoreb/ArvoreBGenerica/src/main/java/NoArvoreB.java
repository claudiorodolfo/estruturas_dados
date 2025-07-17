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
    int ordem; // grau mínimo t

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
        chaves = new ArrayList<>();
        ponteirosFilhos = new ArrayList<>();
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
        // Começa do último índice da lista de chaves do nó
        int i = chaves.size() - 1;
        // Verifica se o nó atual é folha
        if (isFolha()) {
            // Adiciona uma posição "nula" no final para abrir espaço para a nova chave
            chaves.add(null);
            // Move as chaves maiores que o valor para a direita, para liberar espaço
            while (i >= 0 && chave.compareTo(chaves.get(i)) < 0) {
                chaves.set(i + 1, chaves.get(i)); // desloca a chave para a direita
                i--; // move para a próxima chave à esquerda
            }
            // Insere a nova chave na posição correta
            chaves.set(i + 1, chave);
        } else {
            // Se não for folha, encontra o índice do filho onde o valor deve ser inserido
            while (i >= 0 && chave.compareTo(chaves.get(i)) < 0) {
                i--; // move para a esquerda enquanto a chave for maior que o valor
            }
            i++; // ajusta o índice para o filho correto
            // Se o filho onde vamos inserir está cheio, precisamos dividi-lo
            if (ponteirosFilhos.get(i).cheio()) {
                // divide o filho no índice i
                dividirFilho(i); 
                // Após a divisão, a chave do meio sobe, e temos dois filhos: um à esquerda e um à direita
                // Decidimos em qual dos dois inserir o valor
                if (chave.compareTo(chaves.get(i)) > 0) {
                    i++; // se o valor for maior que a chave do meio, inserimos no novo filho à direita
                }
            }
            // Chama recursivamente o método para inserir o valor no filho apropriado
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
        // Nó que será dividido (filho cheio no índice indicado)
        NoArvoreB<T> filhoCheio = ponteirosFilhos.get(i);
        // Cria um novo nó que vai receber metade das chaves do filhoCheio
        NoArvoreB<T> novoFilho = new NoArvoreB<>(filhoCheio.ordem);

        // Calcula o índice da chave do meio
        int t = ordem;

        // Move as chaves da segunda metade para novoFilho
        for (int j = 0; j < t - 1; j++) {
            novoFilho.chaves.add(filhoCheio.chaves.remove(t));
        }

        // Se o filhoCheio não é folha, também devemos mover os filhos correspondentes
        if (!filhoCheio.isFolha()) {
            for (int j = 0; j < t; j++) {
                novoFilho.ponteirosFilhos.add(filhoCheio.ponteirosFilhos.remove(t));
            }
        }
        // Move a chave do meio do filhoCheio para este nó (o pai)
        chaves.add(i, filhoCheio.chaves.remove(t - 1));
        // Insere o novo filho na lista de ponteirosFilhos deste nó
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
        // Verifica se o valor passado é nulo, lançando exceção se for
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser null.");
        }
    
        int i = 0;
    
        // Percorre as chaves do nó até encontrar uma chave maior ou igual ao valor
        while (i < chaves.size() && valor.compareTo(chaves.get(i)) > 0) {
            i++;
        }
    
        // Se encontrou a chave igual ao valor, retorna o nó atual
        if (i < chaves.size() && valor.compareTo(chaves.get(i)) == 0) {
            return this;
        }
    
        // Se chegou a uma folha e não encontrou a chave, retorna null (não encontrado)
        if (isFolha()) {
            return null;
        }
    
        // Caso contrário, desce recursivamente para o filho correspondente
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
         // Encontra o índice da primeira chave maior ou igual ao valor
        int idx = encontrarIndice(valor);
        // Verifica se a chave está presente neste nó
        if (idx < chaves.size() && chaves.get(idx).compareTo(valor) == 0) {
            // === Caso 1: A chave está neste nó ===
            if (isFolha()) {
                // Se for folha, remove diretamente a chave
                chaves.remove(idx);
            } else {
                // Se não for folha, a remoção é mais complexa
                removerDeNaoFolha(idx);
            }
        } else {
            // === Caso 2: A chave não está neste nó ===
            if (isFolha()) 
                return; // Se for folha e a chave não está aqui, ela não existe na árvore

            // Verifica se o filho onde a chave deveria estar é o último
            boolean ultimaChave = (idx == chaves.size());

            // Se o filho não tem o número mínimo de chaves, tenta ajustá-lo (emprestar ou fundir)
            if (ponteirosFilhos.get(idx).chaves.size() < ordem) {
                preencher(idx);
            }

            // Após possível ajuste, decide para qual filho continuar a recursão
            if (ultimaChave && idx > chaves.size()) {
                // Se foi fundido com o filho anterior, desce para ele
                ponteirosFilhos.get(idx - 1).apagar(valor);
            } else {
                // Caso normal, segue para o filho correspondente
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
        // Obtém a chave que será removida, na posição 'idx'
        T chave = chaves.get(idx);
        // Caso 1: O filho anterior (à esquerda) tem chaves suficientes para emprestar (>= ordem)
        if (ponteirosFilhos.get(idx).chaves.size() >= ordem) {
            // Busca o antecessor da chave (maior chave na subárvore à esquerda)
            T antecessor = obterAntecessor(idx);
            // Substitui a chave atual pelo antecessor
            chaves.set(idx, antecessor);
            // Remove recursivamente o antecessor do filho correspondente
            ponteirosFilhos.get(idx).apagar(antecessor);
        // Caso 2: O filho seguinte (à direita) tem chaves suficientes para emprestar (>= ordem)
        } else if (ponteirosFilhos.get(idx + 1).chaves.size() >= ordem) {
            // Busca o sucessor da chave (menor chave na subárvore à direita)
            T sucessor = obterSucessor(idx);
            // Substitui a chave atual pelo sucessor
            chaves.set(idx, sucessor);
            // Remove recursivamente o sucessor do filho correspondente
            ponteirosFilhos.get(idx + 1).apagar(sucessor);
        // Caso 3: Ambos os filhos têm número mínimo de chaves (< ordem)
        } else {
            // Junta o filho idx com o filho idx+1 e move a chave para o novo nó combinado
            juntar(idx);
            // Após juntar, a chave original está agora no filho combinado (idx), então remove lá
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
        // Obtém o ponteiro para o filho à esquerda da chave em 'idx'
        NoArvoreB<T> atual = ponteirosFilhos.get(idx);
    
        // Desce até o nó folha mais à direita da subárvore esquerda
        while (!atual.isFolha()) {
            // Sempre segue para o último filho (o mais à direita)
            atual = atual.ponteirosFilhos.get(atual.ponteirosFilhos.size() - 1);
        }
    
        // Retorna a última chave do nó folha alcançado, que é o antecessor da chave em 'idx'
        return atual.chaves.get(atual.chaves.size() - 1);
    }

    /**
     * Obtém o sucessor de uma chave (menor chave na subárvore direita).
     * 
     * @param idx Índice da chave.
     * @return O sucessor da chave.
     */
    private T obterSucessor(int idx) {
        // Acessa o filho à direita da chave na posição 'idx'
        NoArvoreB<T> atual = ponteirosFilhos.get(idx + 1);
    
        // Desce recursivamente pelo filho mais à esquerda até encontrar um nó folha
        while (!atual.isFolha()) {
            atual = atual.ponteirosFilhos.get(0);
        }
    
        // Retorna a primeira chave do nó folha encontrado, que é o sucessor da chave em 'idx'
        return atual.chaves.get(0);
    }

    /**
     * Preenche um filho que tem menos chaves que o mínimo necessário.
     * Pode emprestar chaves de irmãos ou juntar nós.
     * 
     * @param idx Índice do filho a ser preenchido.
     */
    private void preencher(int idx) {
        // Caso 1: Se o filho à esquerda (índice anterior) existe e tem chaves suficientes (≥ ordem),
        // empresta uma chave dele para o filho em idx.
        if (idx != 0 && ponteirosFilhos.get(idx - 1).chaves.size() >= ordem) {
            emprestarDoAnterior(idx);
        }
        // Caso 2: Se o filho à direita (índice posterior) existe e tem chaves suficientes (≥ ordem),
        // empresta uma chave dele para o filho em idx.
        else if (idx != chaves.size() && ponteirosFilhos.get(idx + 1).chaves.size() >= ordem) {
            emprestarDoProximo(idx);
        }
        // Caso 3: Nenhum dos irmãos tem chaves para emprestar, então é necessário fazer uma fusão.
        else {
            // Se o filho em idx não for o último, junta com o irmão da direita
            if (idx != chaves.size()) {
                juntar(idx);
            }
            // Se for o último filho, junta com o irmão à esquerda
            else {
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
        // Obtém o filho que está com menos chaves do que o permitido
        NoArvoreB<T> filho = ponteirosFilhos.get(idx);
    
        // Obtém o irmão imediatamente à esquerda (anterior), que tem chaves sobrando
        NoArvoreB<T> irmao = ponteirosFilhos.get(idx - 1);
    
        // Move a chave do pai (que separa o filho e o irmão) para o início das chaves do filho
        filho.chaves.add(0, chaves.get(idx - 1));
    
        // Se o irmão não for uma folha, move também o último ponteiro filho do irmão para o início dos ponteiros do filho
        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.add(0, irmao.ponteirosFilhos.remove(irmao.ponteirosFilhos.size() - 1));
        }
    
        // Atualiza a chave do pai com a última chave do irmão, mantendo a ordem correta da árvore
        chaves.set(idx - 1, irmao.chaves.remove(irmao.chaves.size() - 1));
    }

    /**
     * Empresta uma chave do irmão próximo.
     * 
     * @param idx Índice do filho que receberá a chave.
     */
    private void emprestarDoProximo(int idx) {
        // Obtém o filho que está com menos chaves do que o permitido
        NoArvoreB<T> filho = ponteirosFilhos.get(idx);
    
        // Obtém o irmão à direita (próximo), que tem chaves sobrando e pode emprestar uma
        NoArvoreB<T> irmao = ponteirosFilhos.get(idx + 1);
    
        // Move a chave do pai (entre os dois filhos) para o final das chaves do filho necessitado
        filho.chaves.add(chaves.get(idx));
    
        // Se o irmão não for uma folha, move também o primeiro ponteiro filho do irmão para o final dos ponteiros do filho
        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.add(irmao.ponteirosFilhos.remove(0));
        }
    
        // Substitui a chave do pai pela primeira chave do irmão, mantendo a ordem correta
        chaves.set(idx, irmao.chaves.remove(0));
    }

    /**
     * Junta este nó com o próximo irmão.
     * 
     * @param idx Índice do filho que será juntado com o próximo.
     */
    private void juntar(int idx) {
        // Obtém o filho à esquerda (posição idx)
        NoArvoreB<T> filho = ponteirosFilhos.get(idx);
    
        // Obtém o irmão à direita (posição idx + 1)
        NoArvoreB<T> irmao = ponteirosFilhos.get(idx + 1);
    
        // Remove a chave do nó atual (pai) que separa os dois filhos e adiciona ao filho da esquerda
        filho.chaves.add(chaves.remove(idx));
    
        // Adiciona todas as chaves do irmão direito ao filho da esquerda
        filho.chaves.addAll(irmao.chaves);
    
        // Se o irmão direito não for uma folha, também move seus ponteiros filhos para o filho esquerdo
        if (!irmao.isFolha()) {
            filho.ponteirosFilhos.addAll(irmao.ponteirosFilhos);
        }
    
        // Remove o ponteiro para o irmão direito, já que ele foi fundido com o filho esquerdo
        ponteirosFilhos.remove(idx + 1);
    }

    /**
     * Encontra o índice onde uma chave deveria estar ou está.
     * Se chaves = [10, 20, 30] e valor = 25, ele retorna 2, pois 25 deve estar entre 20 e 30.
     * 
     * @param valor A chave a ser localizada.
     * @return O índice onde a chave está ou deveria estar.
     */
    private int encontrarIndice(T valor) {
        int idx = 0;
        // Percorre a lista de chaves enquanto:
        // - Não chegou ao fim da lista
        // - A chave atual for menor que o valor buscado (compareTo < 0)
        while (idx < chaves.size() && chaves.get(idx).compareTo(valor) < 0) {
            idx++;
        }
        // Retorna o índice onde:
        // - 'valor' está presente, ou
        // - 'valor' deveria ser inserido para manter a ordem
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
    
        // Para cada chave no nó, execute:
        for (i = 0; i < chaves.size(); i++) {
            // Se o nó não for folha, imprime recursivamente o filho à esquerda da chave atual
            if (!isFolha()) {
                ponteirosFilhos.get(i).imprimirEmOrdem();
            }
    
            // Imprime a chave atual seguida de espaço
            System.out.print(chaves.get(i) + " ");
        }
    
        // Após imprimir todas as chaves, se o nó não for folha, imprime recursivamente o último filho
        if (!isFolha()) {
            ponteirosFilhos.get(i).imprimirEmOrdem();
        }
    }
}
