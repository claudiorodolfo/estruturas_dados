import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de uma Árvore B genérica.
 * Uma árvore B é uma estrutura de dados em árvore que mantém os dados ordenados
 * e permite inserção, exclusão e busca em tempo logarítmico.
 * 
 * @param <T> Tipo dos dados armazenados na árvore.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreB<T extends Comparable<T>> implements Arborizavel<T> {
    
    /**
     * Nó raiz da árvore.
     */
    private NoArvoreB<T> raiz;
    
    /**
     * Ordem da árvore B (número mínimo de filhos).
     */
    private int ordem;
    
    /**
     * Cria uma árvore B vazia com ordem padrão 3.
     */
    public ArvoreB() {
        this(3);
    }
    
    /**
     * Cria uma árvore B vazia com a ordem especificada.
     * @param ordem Ordem da árvore B (deve ser >= 2).
     */
    public ArvoreB(int ordem) {
        if (ordem < 2) {
            throw new IllegalArgumentException("A ordem deve ser >= 2");
        }
        this.ordem = ordem;
        this.raiz = new NoArvoreB<>(ordem);
    }
    
    /**
     * Retorna o nó raiz da árvore.
     * @return Nó raiz.
     */
    @Override
    public NoArvoreB<T> getRaiz() {
        return raiz;
    }
    
    /**
     * Remove todos os elementos da árvore.
     */
    @Override
    public void limpar() {
        raiz = new NoArvoreB<>(ordem);
    }
    
    /**
     * Insere um novo elemento na árvore.
     * @param dado Elemento a ser inserido.
     */
    @Override
    public void inserir(T dado) {
        NoArvoreB<T> raizAtual = raiz;
        
        // Se a raiz está cheia, divida-a
        if (raizAtual.isCheio()) {
            NoArvoreB<T> novaRaiz = new NoArvoreB<>(ordem);
            novaRaiz.setFolha(false);
            novaRaiz.adicionarFilho(raizAtual);
            dividirFilho(novaRaiz, 0);
            raiz = novaRaiz;
        }
        
        inserirNaoCheio(raiz, dado);
    }
    
    /**
     * Insere um elemento em um nó que não está cheio.
     * @param no Nó onde inserir.
     * @param dado Elemento a ser inserido.
     */
    private void inserirNaoCheio(NoArvoreB<T> no, T dado) {
        int i = no.getNumeroChaves() - 1;
        
        if (no.isFolha()) {
            // Encontra a posição correta e insere
            while (i >= 0 && no.getChaves().get(i).compareTo(dado) > 0) {
                i--;
            }
            no.adicionarChave(dado);
        } else {
            // Encontra o filho apropriado
            while (i >= 0 && no.getChaves().get(i).compareTo(dado) > 0) {
                i--;
            }
            i++;
            
            // Se o filho está cheio, divida-o
            if (no.getFilhos().get(i).isCheio()) {
                dividirFilho(no, i);
                if (dado.compareTo(no.getChaves().get(i)) > 0) {
                    i++;
                }
            }
            
            inserirNaoCheio(no.getFilhos().get(i), dado);
        }
    }
    
    /**
     * Divide um filho cheio.
     * @param pai Nó pai.
     * @param indiceFilho Índice do filho a ser dividido.
     */
    private void dividirFilho(NoArvoreB<T> pai, int indiceFilho) {
        NoArvoreB<T> filho = pai.getFilhos().get(indiceFilho);
        NoArvoreB<T> novoFilho = new NoArvoreB<>(ordem);
        novoFilho.setFolha(filho.isFolha());
        
        // Move metade das chaves para o novo filho
        int meio = filho.getNumeroChaves() / 2;
        for (int i = meio + 1; i < filho.getNumeroChaves(); i++) {
            novoFilho.adicionarChave(filho.getChaves().get(i));
        }
        
        // Move metade dos filhos para o novo filho
        if (!filho.isFolha()) {
            for (int i = meio + 1; i <= filho.getNumeroChaves(); i++) {
                novoFilho.adicionarFilho(filho.getFilhos().get(i));
            }
        }
        
        // Remove as chaves e filhos movidos do filho original
        for (int i = filho.getNumeroChaves() - 1; i >= meio; i--) {
            filho.getChaves().remove(i);
        }
        if (!filho.isFolha()) {
            for (int i = filho.getNumeroChaves(); i >= meio + 1; i--) {
                filho.getFilhos().remove(i);
            }
        }
        
        // Insere a chave do meio no pai
        pai.adicionarChave(filho.getChaves().get(meio));
        filho.getChaves().remove(meio);
        
        // Insere o novo filho no pai
        pai.adicionarFilho(novoFilho);
    }
    
    /**
     * Remove um elemento da árvore.
     * @param dado Elemento a ser removido.
     * @return Elemento removido, ou null se não encontrado.
     */
    @Override
    public T apagar(T dado) {
        if (raiz == null) {
            return null;
        }
        
        // Chama a função recursiva de remoção
        T resultado = removerRecursivo(raiz, dado);
        
        // Se a raiz ficou vazia e não é folha, atualiza a raiz
        if (raiz.getNumeroChaves() == 0 && !raiz.isFolha()) {
            raiz = raiz.getFilhos().get(0);
        }
        
        return resultado;
    }
    
    /**
     * Remove um elemento recursivamente.
     * @param no Nó atual.
     * @param dado Elemento a ser removido.
     * @return Elemento removido, ou null se não encontrado.
     */
    private T removerRecursivo(NoArvoreB<T> no, T dado) {
        int indice = encontrarIndice(no, dado);
        
        if (indice < no.getNumeroChaves() && no.getChaves().get(indice).equals(dado)) {
            // A chave está neste nó
            if (no.isFolha()) {
                // Caso 1: Nó é folha
                return no.removerChave(dado) ? dado : null;
            } else {
                // Caso 2: Nó não é folha
                return removerDeNaoFolha(no, indice);
            }
        } else {
            // A chave não está neste nó
            if (no.isFolha()) {
                return null; // Chave não encontrada
            }
            
            // Garante que o filho tem pelo menos 'ordem' chaves
            if (no.getFilhos().get(indice).getNumeroChaves() < ordem) {
                preencherFilho(no, indice);
            }
            
            // Se o último filho foi fundido, ajusta o índice
            if (indice > 0 && indice == no.getNumeroChaves()) {
                indice--;
            }
            
            return removerRecursivo(no.getFilhos().get(indice), dado);
        }
    }
    
    /**
     * Encontra o índice da chave no nó.
     * @param no Nó.
     * @param dado Chave a ser encontrada.
     * @return Índice da chave.
     */
    private int encontrarIndice(NoArvoreB<T> no, T dado) {
        int i = 0;
        while (i < no.getNumeroChaves() && dado.compareTo(no.getChaves().get(i)) > 0) {
            i++;
        }
        return i;
    }
    
    /**
     * Remove uma chave de um nó não folha.
     * @param no Nó.
     * @param indice Índice da chave a ser removida.
     * @return Chave removida.
     */
    private T removerDeNaoFolha(NoArvoreB<T> no, int indice) {
        T chave = no.getChaves().get(indice);
        
        // Caso 2a: O filho anterior tem pelo menos 'ordem' chaves
        if (no.getFilhos().get(indice).getNumeroChaves() >= ordem) {
            T predecessor = encontrarPredecessor(no, indice);
            no.getChaves().set(indice, predecessor);
            return removerRecursivo(no.getFilhos().get(indice), predecessor);
        }
        // Caso 2b: O filho posterior tem pelo menos 'ordem' chaves
        else if (no.getFilhos().get(indice + 1).getNumeroChaves() >= ordem) {
            T sucessor = encontrarSucessor(no, indice);
            no.getChaves().set(indice, sucessor);
            return removerRecursivo(no.getFilhos().get(indice + 1), sucessor);
        }
        // Caso 2c: Ambos os filhos têm menos de 'ordem' chaves
        else {
            concatenarFilhos(no, indice);
            return removerRecursivo(no.getFilhos().get(indice), chave);
        }
    }
    
    /**
     * Encontra o predecessor de uma chave.
     * @param no Nó.
     * @param indice Índice da chave.
     * @return Predecessor.
     */
    private T encontrarPredecessor(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> atual = no.getFilhos().get(indice);
        while (!atual.isFolha()) {
            atual = atual.getFilhos().get(atual.getNumeroChaves());
        }
        return atual.getChaves().get(atual.getNumeroChaves() - 1);
    }
    
    /**
     * Encontra o sucessor de uma chave.
     * @param no Nó.
     * @param indice Índice da chave.
     * @return Sucessor.
     */
    private T encontrarSucessor(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> atual = no.getFilhos().get(indice + 1);
        while (!atual.isFolha()) {
            atual = atual.getFilhos().get(0);
        }
        return atual.getChaves().get(0);
    }
    
    /**
     * Preenche um filho que tem menos de 'ordem' chaves.
     * @param no Nó pai.
     * @param indice Índice do filho.
     */
    private void preencherFilho(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> filho = no.getFilhos().get(indice);
        
        // Tenta emprestar do irmão anterior
        if (indice > 0 && no.getFilhos().get(indice - 1).getNumeroChaves() >= ordem) {
            emprestarDoAnterior(no, indice);
        }
        // Tenta emprestar do irmão posterior
        else if (indice < no.getNumeroChaves() && no.getFilhos().get(indice + 1).getNumeroChaves() >= ordem) {
            emprestarDoPosterior(no, indice);
        }
        // Funde com o irmão anterior ou posterior
        else {
            if (indice > 0) {
                fundirComAnterior(no, indice);
            } else {
                fundirComPosterior(no, indice);
            }
        }
    }
    
    /**
     * Empresta uma chave do irmão anterior.
     * @param no Nó pai.
     * @param indice Índice do filho.
     */
    private void emprestarDoAnterior(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> filho = no.getFilhos().get(indice);
        NoArvoreB<T> irmao = no.getFilhos().get(indice - 1);
        
        // Move a chave do pai para o filho
        filho.adicionarChave(no.getChaves().get(indice - 1));
        
        // Move a última chave do irmão para o pai
        no.getChaves().set(indice - 1, irmao.getChaves().get(irmao.getNumeroChaves() - 1));
        irmao.getChaves().remove(irmao.getNumeroChaves() - 1);
        
        // Move o último filho do irmão para o filho
        if (!irmao.isFolha()) {
            filho.adicionarFilho(irmao.getFilhos().get(irmao.getNumeroChaves()));
            irmao.getFilhos().remove(irmao.getNumeroChaves());
        }
    }
    
    /**
     * Empresta uma chave do irmão posterior.
     * @param no Nó pai.
     * @param indice Índice do filho.
     */
    private void emprestarDoPosterior(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> filho = no.getFilhos().get(indice);
        NoArvoreB<T> irmao = no.getFilhos().get(indice + 1);
        
        // Move a chave do pai para o filho
        filho.adicionarChave(no.getChaves().get(indice));
        
        // Move a primeira chave do irmão para o pai
        no.getChaves().set(indice, irmao.getChaves().get(0));
        irmao.getChaves().remove(0);
        
        // Move o primeiro filho do irmão para o filho
        if (!irmao.isFolha()) {
            filho.adicionarFilho(irmao.getFilhos().get(0));
            irmao.getFilhos().remove(0);
        }
    }
    
    /**
     * Funde com o irmão anterior.
     * @param no Nó pai.
     * @param indice Índice do filho.
     */
    private void fundirComAnterior(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> filho = no.getFilhos().get(indice);
        NoArvoreB<T> irmao = no.getFilhos().get(indice - 1);
        
        // Move a chave do pai para o irmão
        irmao.adicionarChave(no.getChaves().get(indice - 1));
        no.getChaves().remove(indice - 1);
        
        // Move todas as chaves do filho para o irmão
        for (T chave : filho.getChaves()) {
            irmao.adicionarChave(chave);
        }
        
        // Move todos os filhos do filho para o irmão
        for (NoArvoreB<T> filhoFilho : filho.getFilhos()) {
            irmao.adicionarFilho(filhoFilho);
        }
        
        // Remove o filho do pai
        no.getFilhos().remove(indice);
    }

    /**
     * Funde com o irmão posterior.
     * @param no Nó pai.
     * @param indice Índice do filho.
     */
    private void fundirComPosterior(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> filho = no.getFilhos().get(indice);
        NoArvoreB<T> irmao = no.getFilhos().get(indice + 1);
        
        // Move a chave do pai para o filho
        filho.adicionarChave(no.getChaves().get(indice));
        no.getChaves().remove(indice);
        
        // Move todas as chaves do irmão para o filho
        for (T chave : irmao.getChaves()) {
            filho.adicionarChave(chave);
        }
        
        // Move todos os filhos do irmão para o filho
        for (NoArvoreB<T> filhoIrmao : irmao.getFilhos()) {
            filho.adicionarFilho(filhoIrmao);
        }
        
        // Remove o irmão do pai
        no.getFilhos().remove(indice + 1);
    }
    
    /**
     * Funde dois filhos de um nó.
     * @param no Nó pai.
     * @param indice Índice do primeiro filho.
     */
    private void concatenarFilhos(NoArvoreB<T> no, int indice) {
        NoArvoreB<T> filho1 = no.getFilhos().get(indice);
        NoArvoreB<T> filho2 = no.getFilhos().get(indice + 1);
        
        // Move a chave do pai para o primeiro filho
        filho1.adicionarChave(no.getChaves().get(indice));
        
        // Move todas as chaves do segundo filho para o primeiro
        for (T chave : filho2.getChaves()) {
            filho1.adicionarChave(chave);
        }
        
        // Move todos os filhos do segundo filho para o primeiro
        for (NoArvoreB<T> filhoFilho : filho2.getFilhos()) {
            filho1.adicionarFilho(filhoFilho);
        }
        
        // Remove a chave do pai e o segundo filho
        no.getChaves().remove(indice);
        no.getFilhos().remove(indice + 1);
    }
    
    /**
     * Busca um elemento na árvore.
     * @param dado Elemento a ser buscado.
     * @return true se o elemento existe, false caso contrário.
     */
    @Override
    public boolean existe(T dado) {
        return buscarRecursivo(raiz, dado) != null;
    }
    
    /**
     * Busca um elemento recursivamente.
     * @param no Nó atual.
     * @param dado Elemento a ser buscado.
     * @return Elemento encontrado, ou null se não encontrado.
     */
    private T buscarRecursivo(NoArvoreB<T> no, T dado) {
        if (no == null) {
            return null;
        }
        
        int i = 0;
        while (i < no.getNumeroChaves() && dado.compareTo(no.getChaves().get(i)) > 0) {
            i++;
        }
        
        if (i < no.getNumeroChaves() && dado.equals(no.getChaves().get(i))) {
            return no.getChaves().get(i);
        }
        
        if (no.isFolha()) {
            return null;
        }
        
        return buscarRecursivo(no.getFilhos().get(i), dado);
    }
    
    /**
     * Retorna uma String com os elementos em pré-ordem.
     * @return Elementos em pré-ordem.
     */
    @Override
    public String imprimirPreOrdem() {
        return formataSaida(imprimirPreOrdemRec(raiz));
    }
    
    /**
     * Retorna uma String com os elementos em ordem.
     * @return Elementos em ordem.
     */
    @Override
    public String imprimirEmOrdem() {
        return formataSaida(imprimirEmOrdemRec(raiz));
    }
    
    /**
     * Retorna uma String com os elementos em pós-ordem.
     * @return Elementos em pós-ordem.
     */
    @Override
    public String imprimirPosOrdem() {
        return formataSaida(imprimirPosOrdemRec(raiz));
    }
    
    /**
     * Percorre a árvore em pré-ordem recursivamente.
     */
    private String imprimirPreOrdemRec(NoArvoreB<T> no) {
        if (no == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < no.getNumeroChaves(); i++) {
            sb.append(no.getChaves().get(i)).append(" ");
            if (!no.isFolha()) {
                sb.append(imprimirPreOrdemRec(no.getFilhos().get(i))).append(" ");
            }
        }
        if (!no.isFolha()) {
            sb.append(imprimirPreOrdemRec(no.getFilhos().get(no.getNumeroChaves())));
        }
        
        return sb.toString();
    }
    
    /**
     * Percorre a árvore em ordem recursivamente.
     */
    private String imprimirEmOrdemRec(NoArvoreB<T> no) {
        if (no == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < no.getNumeroChaves(); i++) {
            if (!no.isFolha()) {
                sb.append(imprimirEmOrdemRec(no.getFilhos().get(i))).append(" ");
            }
            sb.append(no.getChaves().get(i)).append(" ");
        }
        if (!no.isFolha()) {
            sb.append(imprimirEmOrdemRec(no.getFilhos().get(no.getNumeroChaves())));
        }
        
        return sb.toString();
    }
    
    /**
     * Percorre a árvore em pós-ordem recursivamente.
     */
    private String imprimirPosOrdemRec(NoArvoreB<T> no) {
        if (no == null) return "";
        
        StringBuilder sb = new StringBuilder();
        if (!no.isFolha()) {
            sb.append(imprimirPosOrdemRec(no.getFilhos().get(0))).append(" ");
        }
        for (int i = 0; i < no.getNumeroChaves(); i++) {
            sb.append(no.getChaves().get(i)).append(" ");
            if (!no.isFolha()) {
                sb.append(imprimirPosOrdemRec(no.getFilhos().get(i + 1))).append(" ");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Formata a saída removendo espaços extras.
     */
    private String formataSaida(String msg) {
        return msg.replaceAll("\\s+", " ").trim();
    }
} 