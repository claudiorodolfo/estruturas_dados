package br.edu.ifba.vdc.bsi.sortedlinkedlist.app;

import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortedLinkedList;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortOrder;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortAlgorithm;

/**
 * Classe de aplicação interativa para testar os algoritmos de ordenação
 * da SortedLinkedList com diferentes ordens (ASC e DESC).
 */
public class SortedLinkedListApp {
    
    private static SortedLinkedList list = new SortedLinkedList();
    
    void main() {
        IO.println("=== APLICAÇÃO INTERATIVA - ALGORITMOS DE ORDENAÇÃO ===\n");
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            processarOpcao(opcao);
        } while (opcao != 0);
        
        IO.println("\n=== PROGRAMA ENCERRADO ===");
    }
    
    /**
     * Exibe o menu principal.
     */
    private static void exibirMenu() {
        IO.println("\n" + "=".repeat(50));
        IO.println("MENU PRINCIPAL");
        IO.println("=".repeat(50));
        IO.println("1. Inserir valor na lista");
        IO.println("2. Inserir múltiplos valores");
        IO.println("3. Visualizar lista atual");
        IO.println("4. Ordenar lista (selecionar algoritmo)");
        IO.println("5. Limpar lista");
        IO.println("6. Carregar lista de teste padrão");
        IO.println("7. Executar testes pré-definidos");
        IO.println("0. Sair");
        IO.println("=".repeat(50));
    }
    
    /**
     * Processa a opção escolhida pelo usuário.
     */
    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> inserirValor();
            case 2 -> inserirMultiplosValores();
            case 3 -> visualizarLista();
            case 4 -> ordenarLista();
            case 5 -> limparLista();
            case 6 -> carregarListaTeste();
            case 7 -> executarTestesPredefinidos();
            case 0 -> IO.println("\nEncerrando programa...");
            default -> IO.println("\nOpção inválida! Tente novamente.");
        }
    }
    
    /**
     * Insere um único valor na lista.
     */
    private static void inserirValor() {
        IO.println("\n--- Inserir Valor ---");
        int valor = lerInteiro("Digite o valor a ser inserido: ");
        list.append(valor);
        IO.println("Valor " + valor + " inserido com sucesso!");
        IO.println("Lista atual: " + list.print());
    }
    
    /**
     * Insere múltiplos valores na lista.
     */
    private static void inserirMultiplosValores() {
        IO.println("\n--- Inserir Múltiplos Valores ---");
        IO.println("Digite os valores separados por espaço (ex: 64 34 25 12 22):");
        String entrada = IO.readln("");
        
        try {
            String[] valores = entrada.trim().split("\\s+");
            int contador = 0;
            for (String valorStr : valores) {
                if (!valorStr.isEmpty()) {
                    int valor = Integer.parseInt(valorStr);
                    list.append(valor);
                    contador++;
                }
            }
            IO.println(contador + " valor(es) inserido(s) com sucesso!");
            IO.println("Lista atual: " + list.print());
        } catch (NumberFormatException e) {
            IO.println("Erro: Valores inválidos! Use apenas números inteiros separados por espaço.");
        }
    }
    
    /**
     * Visualiza a lista atual.
     */
    private static void visualizarLista() {
        IO.println("\n--- Visualizar Lista ---");
        if (list.isEmpty()) {
            IO.println("Lista vazia!");
        } else {
            IO.println("Tamanho da lista: " + list.size());
            IO.println("Elementos: " + list.print());
        }
    }
    
    /**
     * Ordena a lista permitindo ao usuário escolher o algoritmo e a ordem.
     */
    private static void ordenarLista() {
        IO.println("\n--- Ordenar Lista ---");
        if (list.isEmpty()) {
            IO.println("Lista vazia! Não há elementos para ordenar.");
            return;
        }
        
        IO.println("Lista antes da ordenação: " + list.print());
        
        // Selecionar algoritmo
        IO.println("\nEscolha o algoritmo de ordenação:");
        IO.println("1. Bubble Sort");
        IO.println("2. Insertion Sort");
        IO.println("3. Selection Sort");
        
        int escolhaAlgoritmo = lerInteiro("Opção: ");
        SortAlgorithm algoritmo;
        
        switch (escolhaAlgoritmo) {
            case 2 -> {
                algoritmo = SortAlgorithm.INSERTION_SORT;
                IO.println("\nAlgoritmo selecionado: INSERTION SORT");
            }
            case 3 -> {
                algoritmo = SortAlgorithm.SELECTION_SORT;
                IO.println("\nAlgoritmo selecionado: SELECTION SORT");
            }
            default -> {
                algoritmo = SortAlgorithm.BUBBLE_SORT;
                IO.println("\nAlgoritmo selecionado: BUBBLE SORT");
            }
        }
        
        // Selecionar ordem
        IO.println("\nEscolha a ordem de ordenação:");
        IO.println("1. Crescente (ASC)");
        IO.println("2. Decrescente (DESC)");
        
        int escolhaOrdem = lerInteiro("Opção: ");
        SortOrder ordem;
        
        if (escolhaOrdem == 2) {
            ordem = SortOrder.DESC;
            IO.println("\nOrdenando em ordem DECRESCENTE...");
        } else {
            ordem = SortOrder.ASC;
            IO.println("\nOrdenando em ordem CRESCENTE...");
        }
        
        list.sort(ordem, algoritmo);
        IO.println("Lista após ordenação: " + list.print());
    }
    
    /**
     * Limpa a lista.
     */
    private static void limparLista() {
        IO.println("\n--- Limpar Lista ---");
        if (list.isEmpty()) {
            IO.println("Lista já está vazia!");
        } else {
            IO.println("Lista antes: " + list.print());
            list.clear();
            IO.println("Lista limpa com sucesso!");
        }
    }
    
    /**
     * Carrega uma lista de teste padrão.
     */
    private static void carregarListaTeste() {
        IO.println("\n--- Carregar Lista de Teste ---");
        if (!list.isEmpty()) {
            String resposta = IO.readln("A lista atual não está vazia. Deseja limpar antes? (s/n): ").trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                list.clear();
            }
        }
        
        list.append(64);
        list.append(34);
        list.append(25);
        list.append(12);
        list.append(22);
        list.append(11);
        list.append(90);
        list.append(5);
        
        IO.println("Lista de teste carregada com sucesso!");
        IO.println("Lista atual: " + list.print());
    }
    
    /**
     * Executa testes pré-definidos.
     */
    private static void executarTestesPredefinidos() {
        IO.println("\n--- Executar Testes Pré-definidos ---");
        IO.println("Escolha o teste:");
        IO.println("1. Bubble Sort - Ordem Crescente");
        IO.println("2. Bubble Sort - Ordem Decrescente");
        IO.println("3. Insertion Sort - Ordem Crescente");
        IO.println("4. Insertion Sort - Ordem Decrescente");
        IO.println("5. Selection Sort - Ordem Crescente");
        IO.println("6. Selection Sort - Ordem Decrescente");
        IO.println("7. Lista vazia");
        IO.println("8. Lista com um elemento");
        IO.println("9. Lista já ordenada");
        IO.println("10. Executar todos os testes");
        
        int escolha = lerInteiro("Opção: ");
        
        switch (escolha) {
            case 1 -> testBubbleSortAscending();
            case 2 -> testBubbleSortDescending();
            case 3 -> testInsertionSortAscending();
            case 4 -> testInsertionSortDescending();
            case 5 -> testSelectionSortAscending();
            case 6 -> testSelectionSortDescending();
            case 7 -> testEmptyList();
            case 8 -> testSingleElement();
            case 9 -> testAlreadySorted();
            case 10 -> executarTodosTestes();
            default -> IO.println("Opção inválida!");
        }
    }
    
    /**
     * Executa todos os testes pré-definidos.
     */
    private static void executarTodosTestes() {
        IO.println("\n=== EXECUTANDO TODOS OS TESTES ===\n");
        testBubbleSortAscending();
        testBubbleSortDescending();
        testInsertionSortAscending();
        testInsertionSortDescending();
        testSelectionSortAscending();
        testSelectionSortDescending();
        testEmptyList();
        testSingleElement();
        testAlreadySorted();
        IO.println("\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }
    
    /**
     * Lê um inteiro do teclado com tratamento de erro.
     */
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(IO.readln(mensagem).trim());
            } catch (NumberFormatException e) {
                IO.println("Erro: Digite um número inteiro válido!");
            }
        }
    }
    
    /**
     * Cria uma lista com valores de teste.
     */
    private static SortedLinkedList createTestList() {
        SortedLinkedList list = new SortedLinkedList(20);
        list.append(64);
        list.append(34);
        list.append(25);
        list.append(12);
        list.append(22);
        list.append(11);
        list.append(90);
        list.append(5);
        return list;
    }
    
    /**
     * Testa Bubble Sort em ordem crescente.
     */
    private static void testBubbleSortAscending() {
        IO.println("--- Teste 1: Bubble Sort - Ordem Crescente (ASC) ---");
        SortedLinkedList list = createTestList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa Bubble Sort em ordem decrescente.
     */
    private static void testBubbleSortDescending() {
        IO.println("--- Teste 2: Bubble Sort - Ordem Decrescente (DESC) ---");
        SortedLinkedList list = createTestList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa Insertion Sort em ordem crescente.
     */
    private static void testInsertionSortAscending() {
        IO.println("--- Teste 3: Insertion Sort - Ordem Crescente (ASC) ---");
        SortedLinkedList list = createTestList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.INSERTION_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa Insertion Sort em ordem decrescente.
     */
    private static void testInsertionSortDescending() {
        IO.println("--- Teste 4: Insertion Sort - Ordem Decrescente (DESC) ---");
        SortedLinkedList list = createTestList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.INSERTION_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa Selection Sort em ordem crescente.
     */
    private static void testSelectionSortAscending() {
        IO.println("--- Teste 5: Selection Sort - Ordem Crescente (ASC) ---");
        SortedLinkedList list = createTestList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.SELECTION_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa Selection Sort em ordem decrescente.
     */
    private static void testSelectionSortDescending() {
        IO.println("--- Teste 6: Selection Sort - Ordem Decrescente (DESC) ---");
        SortedLinkedList list = createTestList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.SELECTION_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa ordenação de lista vazia.
     */
    private static void testEmptyList() {
        IO.println("--- Teste 7: Lista Vazia ---");
        SortedLinkedList list = new SortedLinkedList();
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada: " + list.print());
        IO.println();
    }
    
    /**
     * Testa ordenação de lista com um único elemento.
     */
    private static void testSingleElement() {
        IO.println("--- Teste 8: Lista com Um Elemento ---");
        SortedLinkedList list = new SortedLinkedList();
        list.append(42);
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada (ASC): " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada (DESC): " + list.print());
        IO.println();
    }
    
    /**
     * Testa ordenação de lista já ordenada.
     */
    private static void testAlreadySorted() {
        IO.println("--- Teste 9: Lista Já Ordenada ---");
        SortedLinkedList list = new SortedLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada (ASC): " + list.print());
        
        list.clear();
        list.append(5);
        list.append(4);
        list.append(3);
        list.append(2);
        list.append(1);
        IO.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.BUBBLE_SORT);
        IO.println("Lista ordenada (DESC): " + list.print());
        IO.println();
    }
}
