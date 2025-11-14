package br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting;

/**
 * Interface para estruturas de dados ordenáveis.
 * Classes que implementam esta interface devem fornecer
 * métodos para ordenar seus conteúdos.
 */
public interface Sortable {
    /**
     * Ordena os elementos na estrutura de dados em ordem crescente (padrão).
     */
    void sort();
    
    /**
     * Ordena os elementos na estrutura de dados na ordem especificada.
     * 
     * @param order a ordem de ordenação (ASC para crescente, DESC para decrescente)
     */
    void sort(SortOrder order);
}

