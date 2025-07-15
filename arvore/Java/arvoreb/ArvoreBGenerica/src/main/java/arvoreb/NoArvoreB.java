package arvoreb;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó de Árvore B.
 * Cada nó pode conter múltiplas chaves e múltiplos filhos.
 * 
 * @param <T> Tipo dos dados armazenados no nó.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class NoArvoreB<T extends Comparable<T>> {
    
    /**
     * Lista de chaves armazenadas no nó.
     */
    private List<T> chaves;
    
    /**
     * Lista de filhos do nó.
     */
    private List<NoArvoreB<T>> ponteirosFilhos;
    
    /**
     * Indica se o nó é uma folha.
     */
    private boolean folha;
    
    /**
     * Ordem da árvore B (número máximo de filhos).
     */
    private int ordem;
    
    /**
     * Construtor padrão. Cria um nó folha vazio.
     * @param ordem Ordem da árvore B.
     */
    public NoArvoreB(int ordem) {
        this.ordem = ordem;
        this.chaves = new ArrayList<>();
        this.ponteirosFilhos = new ArrayList<>();
        this.folha = true;
    }
    
    /**
     * Retorna a lista de chaves.
     * @return Lista de chaves.
     */
    public List<T> getChaves() {
        return chaves;
    }
    
    /**
     * Define a lista de chaves.
     * @param chaves Nova lista de chaves.
     */
    public void setChaves(List<T> chaves) {
        this.chaves = chaves;
    }
    
    /**
     * Retorna a lista de filhos.
     * @return Lista de filhos.
     */
    public List<NoArvoreB<T>> getFilhos() {
        return ponteirosFilhos;
    }
    
    /**
     * Define a lista de filhos.
     * @param filhos Nova lista de filhos.
     */
    public void setFilhos(List<NoArvoreB<T>> ponteirosFilhos) {
        this.ponteirosFilhos = ponteirosFilhos;
    }
    
    /**
     * Verifica se o nó é uma folha.
     * @return true se é folha, false caso contrário.
     */
    public boolean isFolha() {
        return folha;
    }
    
    /**
     * Define se o nó é uma folha.
     * @param folha true se é folha, false caso contrário.
     */
    public void setFolha(boolean folha) {
        this.folha = folha;
    }
    
    /**
     * Retorna a ordem da árvore B.
     * @return Ordem da árvore.
     */
    public int getOrdem() {
        return ordem;
    }
    
    /**
     * Retorna o número de chaves no nó.
     * @return Número de chaves.
     */
    public int getNumeroChaves() {
        return chaves.size();
    }
    
    /**
     * Verifica se o nó está cheio.
     * @return true se está cheio, false caso contrário.
     */
    public boolean isCheio() {
        return chaves.size() == 2 * ordem - 1;
    }
    
    /**
     * Adiciona uma chave ao nó.
     * @param chave Chave a ser adicionada.
     */
    public void adicionarChave(T chave) {
        chaves.add(chave);
        chaves.sort(null); // Ordena as chaves
    }
    
    /**
     * Remove uma chave do nó.
     * @param chave Chave a ser removida.
     * @return true se a chave foi removida, false caso contrário.
     */
    public boolean removerChave(T chave) {
        return chaves.remove(chave);
    }
    
    /**
     * Adiciona um filho ao nó.
     * @param filho Filho a ser adicionado.
     */
    public void adicionarFilho(NoArvoreB<T> filho) {
        ponteirosFilhos.add(filho);
    }
    
    /**
     * Remove um filho do nó.
     * @param filho Filho a ser removido.
     * @return true se o filho foi removido, false caso contrário.
     */
    public boolean removerFilho(NoArvoreB<T> filho) {
        return ponteirosFilhos.remove(filho);
    }
    
    /**
     * Retorna uma representação em string do nó.
     * @return String com as chaves do nó.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < chaves.size(); i++) {
            sb.append(chaves.get(i));
            if (i < chaves.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
} 