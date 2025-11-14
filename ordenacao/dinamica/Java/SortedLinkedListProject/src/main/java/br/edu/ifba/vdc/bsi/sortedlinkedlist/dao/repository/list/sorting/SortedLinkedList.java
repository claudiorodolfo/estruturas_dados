package br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting;

import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.LinkedList;

/**
 * Implementação dinâmica de algoritmos de ordenação
 * usando uma lista duplamente encadeada.
 */
public class SortedLinkedList extends LinkedList<Integer> implements Sortable {
    
    public SortedLinkedList() {
        super();
    }
    
    public SortedLinkedList(int capacity) {
        super(capacity);
    }

    @Override
    public void sort() {
        sort(SortOrder.ASC);
    }
    
    @Override
    public void sort(SortOrder order) {
        sort(order, SortAlgorithm.BUBBLE_SORT); // Algoritmo padrão
    }
    
    /**
     * Ordena os elementos na estrutura de dados na ordem especificada
     * usando o algoritmo de ordenação escolhido.
     * 
     * @param order a ordem de ordenação (ASC para crescente, DESC para decrescente)
     * @param algorithm o algoritmo de ordenação a ser utilizado
     */
    public void sort(SortOrder order, SortAlgorithm algorithm) {
        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(order);
                break;
            case INSERTION_SORT:
                insertionSort(order);
                break;
            case SELECTION_SORT:
                selectionSort(order);
                break;
            default:
                bubbleSort(order); // Fallback para bubble sort
        }
    }

    /**
     * Troca dois elementos de posição na lista.
     * 
     * @param index1 índice do primeiro elemento
     * @param index2 índice do segundo elemento
     * @throws IndexOutOfBoundsException se algum dos índices for inválido
     */
    private void swap(int index1, int index2) {
        if (index1 == index2) {
            return; // Não há necessidade de trocar se os índices são iguais
        }
        Integer temp = select(index1);
        update(index1, select(index2));
        update(index2, temp);
    }
    
    private void bubbleSort(SortOrder order) {
        if (size() <= 1) {
            return; // Já está ordenado ou vazio
        }
        for (int i = 0; i < (size() - 1); i++) {
            for (int j = 0; j < (size() - 1) - i; j++) {
                Integer current = select(j);
                Integer next = select(j + 1);
                if ((order == SortOrder.ASC && current > next) || (order == SortOrder.DESC && current < next)) {
                    swap(j, j + 1);
                }
            }
        }
    }

    private void insertionSort(SortOrder order) {
        if (size() <= 1) {
            return; // Já está ordenado ou vazio
        }
        for (int i = 1; i < size(); i++) {
            Integer key = select(i);
            int j = i - 1;
            while (j >= 0 && ((order == SortOrder.ASC && select(j) > key) || (order == SortOrder.DESC && select(j) < key))) {
                update(j + 1, select(j));
                j--;
            }
            update(j + 1, key);
        }
    }

    private void selectionSort(SortOrder order) {
        if (size() <= 1) {
            return; // Já está ordenado ou vazio
        }
        for (int i = 0; i < size() - 1; i++) {
            int bestIndex = i;
            for (int j = i + 1; j < size(); j++) {
                Integer current = select(j);
                Integer best = select(bestIndex);
                if ((order == SortOrder.ASC && current < best) || (order == SortOrder.DESC && current > best)) {
                    bestIndex = j;
                }
            }
            if (bestIndex != i) {
                swap(i, bestIndex);
            }
        }
    }
}