package br.edu.ifba.vdc.bsi.sortedlinkedlist.app;

import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortedLinkedList;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortOrder;
import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortAlgorithm;
import java.util.Scanner;

/**
 * Classe de aplicação interativa para testar os algoritmos de ordenação
 * da SortedLinkedList com diferentes ordens (ASC e DESC).
 */
public class SortedLinkedListApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static SortedLinkedList list = new SortedLinkedList();
    
    public static void main(String[] args) {
        System.out.println("=== APLICAÇÃO INTERATIVA - ALGORITMOS DE ORDENAÇÃO ===\n");
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("\n=== PROGRAMA ENCERRADO ===");
        scanner.close();
    }
    
    /**
     * Exibe o menu principal.
     */
    private static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Inserir valor na lista");
        System.out.println("2. Inserir múltiplos valores");
        System.out.println("3. Visualizar lista atual");
        System.out.println("4. Ordenar lista (selecionar algoritmo)");
        System.out.println("5. Limpar lista");
        System.out.println("6. Carregar lista de teste padrão");
        System.out.println("7. Executar testes pré-definidos");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Processa a opção escolhida pelo usuário.
     */
    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                inserirValor();
                break;
            case 2:
                inserirMultiplosValores();
                break;
            case 3:
                visualizarLista();
                break;
            case 4:
                ordenarLista();
                break;
            case 5:
                limparLista();
                break;
            case 6:
                carregarListaTeste();
                break;
            case 7:
                executarTestesPredefinidos();
                break;
            case 0:
                System.out.println("\nEncerrando programa...");
                break;
            default:
                System.out.println("\nOpção inválida! Tente novamente.");
        }
    }
    
    /**
     * Insere um único valor na lista.
     */
    private static void inserirValor() {
        System.out.println("\n--- Inserir Valor ---");
        int valor = lerInteiro("Digite o valor a ser inserido: ");
        list.append(valor);
        System.out.println("Valor " + valor + " inserido com sucesso!");
        System.out.println("Lista atual: " + list.print());
    }
    
    /**
     * Insere múltiplos valores na lista.
     */
    private static void inserirMultiplosValores() {
        System.out.println("\n--- Inserir Múltiplos Valores ---");
        System.out.println("Digite os valores separados por espaço (ex: 64 34 25 12 22):");
        scanner.nextLine(); // Limpar buffer
        String entrada = scanner.nextLine();
        
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
            System.out.println(contador + " valor(es) inserido(s) com sucesso!");
            System.out.println("Lista atual: " + list.print());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valores inválidos! Use apenas números inteiros separados por espaço.");
        }
    }
    
    /**
     * Visualiza a lista atual.
     */
    private static void visualizarLista() {
        System.out.println("\n--- Visualizar Lista ---");
        if (list.isEmpty()) {
            System.out.println("Lista vazia!");
        } else {
            System.out.println("Tamanho da lista: " + list.size());
            System.out.println("Elementos: " + list.print());
        }
    }
    
    /**
     * Ordena a lista permitindo ao usuário escolher o algoritmo e a ordem.
     */
    private static void ordenarLista() {
        System.out.println("\n--- Ordenar Lista ---");
        if (list.isEmpty()) {
            System.out.println("Lista vazia! Não há elementos para ordenar.");
            return;
        }
        
        System.out.println("Lista antes da ordenação: " + list.print());
        
        // Selecionar algoritmo
        System.out.println("\nEscolha o algoritmo de ordenação:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Selection Sort");
        
        int escolhaAlgoritmo = lerInteiro("Opção: ");
        SortAlgorithm algoritmo;
        
        switch (escolhaAlgoritmo) {
            case 2:
                algoritmo = SortAlgorithm.INSERTION_SORT;
                System.out.println("\nAlgoritmo selecionado: INSERTION SORT");
                break;
            case 3:
                algoritmo = SortAlgorithm.SELECTION_SORT;
                System.out.println("\nAlgoritmo selecionado: SELECTION SORT");
                break;
            default:
                algoritmo = SortAlgorithm.BUBBLE_SORT;
                System.out.println("\nAlgoritmo selecionado: BUBBLE SORT");
                break;
        }
        
        // Selecionar ordem
        System.out.println("\nEscolha a ordem de ordenação:");
        System.out.println("1. Crescente (ASC)");
        System.out.println("2. Decrescente (DESC)");
        
        int escolhaOrdem = lerInteiro("Opção: ");
        SortOrder ordem;
        
        if (escolhaOrdem == 2) {
            ordem = SortOrder.DESC;
            System.out.println("\nOrdenando em ordem DECRESCENTE...");
        } else {
            ordem = SortOrder.ASC;
            System.out.println("\nOrdenando em ordem CRESCENTE...");
        }
        
        list.sort(ordem, algoritmo);
        System.out.println("Lista após ordenação: " + list.print());
    }
    
    /**
     * Limpa a lista.
     */
    private static void limparLista() {
        System.out.println("\n--- Limpar Lista ---");
        if (list.isEmpty()) {
            System.out.println("Lista já está vazia!");
        } else {
            System.out.println("Lista antes: " + list.print());
            list.clear();
            System.out.println("Lista limpa com sucesso!");
        }
    }
    
    /**
     * Carrega uma lista de teste padrão.
     */
    private static void carregarListaTeste() {
        System.out.println("\n--- Carregar Lista de Teste ---");
        if (!list.isEmpty()) {
            System.out.print("A lista atual não está vazia. Deseja limpar antes? (s/n): ");
            scanner.nextLine(); // Limpar buffer
            String resposta = scanner.nextLine().trim().toLowerCase();
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
        
        System.out.println("Lista de teste carregada com sucesso!");
        System.out.println("Lista atual: " + list.print());
    }
    
    /**
     * Executa testes pré-definidos.
     */
    private static void executarTestesPredefinidos() {
        System.out.println("\n--- Executar Testes Pré-definidos ---");
        System.out.println("Escolha o teste:");
        System.out.println("1. Bubble Sort - Ordem Crescente");
        System.out.println("2. Bubble Sort - Ordem Decrescente");
        System.out.println("3. Insertion Sort - Ordem Crescente");
        System.out.println("4. Insertion Sort - Ordem Decrescente");
        System.out.println("5. Selection Sort - Ordem Crescente");
        System.out.println("6. Selection Sort - Ordem Decrescente");
        System.out.println("7. Lista vazia");
        System.out.println("8. Lista com um elemento");
        System.out.println("9. Lista já ordenada");
        System.out.println("10. Executar todos os testes");
        
        int escolha = lerInteiro("Opção: ");
        
        switch (escolha) {
            case 1:
                testBubbleSortAscending();
                break;
            case 2:
                testBubbleSortDescending();
                break;
            case 3:
                testInsertionSortAscending();
                break;
            case 4:
                testInsertionSortDescending();
                break;
            case 5:
                testSelectionSortAscending();
                break;
            case 6:
                testSelectionSortDescending();
                break;
            case 7:
                testEmptyList();
                break;
            case 8:
                testSingleElement();
                break;
            case 9:
                testAlreadySorted();
                break;
            case 10:
                executarTodosTestes();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    /**
     * Executa todos os testes pré-definidos.
     */
    private static void executarTodosTestes() {
        System.out.println("\n=== EXECUTANDO TODOS OS TESTES ===\n");
        testBubbleSortAscending();
        testBubbleSortDescending();
        testInsertionSortAscending();
        testInsertionSortDescending();
        testSelectionSortAscending();
        testSelectionSortDescending();
        testEmptyList();
        testSingleElement();
        testAlreadySorted();
        System.out.println("\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }
    
    /**
     * Lê um inteiro do teclado com tratamento de erro.
     */
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Erro: Digite um número inteiro válido!");
                scanner.next(); // Limpar entrada inválida
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
        System.out.println("--- Teste 1: Bubble Sort - Ordem Crescente (ASC) ---");
        SortedLinkedList list = createTestList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa Bubble Sort em ordem decrescente.
     */
    private static void testBubbleSortDescending() {
        System.out.println("--- Teste 2: Bubble Sort - Ordem Decrescente (DESC) ---");
        SortedLinkedList list = createTestList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa Insertion Sort em ordem crescente.
     */
    private static void testInsertionSortAscending() {
        System.out.println("--- Teste 3: Insertion Sort - Ordem Crescente (ASC) ---");
        SortedLinkedList list = createTestList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.INSERTION_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa Insertion Sort em ordem decrescente.
     */
    private static void testInsertionSortDescending() {
        System.out.println("--- Teste 4: Insertion Sort - Ordem Decrescente (DESC) ---");
        SortedLinkedList list = createTestList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.INSERTION_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa Selection Sort em ordem crescente.
     */
    private static void testSelectionSortAscending() {
        System.out.println("--- Teste 5: Selection Sort - Ordem Crescente (ASC) ---");
        SortedLinkedList list = createTestList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.SELECTION_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa Selection Sort em ordem decrescente.
     */
    private static void testSelectionSortDescending() {
        System.out.println("--- Teste 6: Selection Sort - Ordem Decrescente (DESC) ---");
        SortedLinkedList list = createTestList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.SELECTION_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa ordenação de lista vazia.
     */
    private static void testEmptyList() {
        System.out.println("--- Teste 7: Lista Vazia ---");
        SortedLinkedList list = new SortedLinkedList();
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada: " + list.print());
        System.out.println();
    }
    
    /**
     * Testa ordenação de lista com um único elemento.
     */
    private static void testSingleElement() {
        System.out.println("--- Teste 8: Lista com Um Elemento ---");
        SortedLinkedList list = new SortedLinkedList();
        list.append(42);
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada (ASC): " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada (DESC): " + list.print());
        System.out.println();
    }
    
    /**
     * Testa ordenação de lista já ordenada.
     */
    private static void testAlreadySorted() {
        System.out.println("--- Teste 9: Lista Já Ordenada ---");
        SortedLinkedList list = new SortedLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.ASC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada (ASC): " + list.print());
        
        list.clear();
        list.append(5);
        list.append(4);
        list.append(3);
        list.append(2);
        list.append(1);
        System.out.println("Lista original: " + list.print());
        list.sort(SortOrder.DESC, SortAlgorithm.BUBBLE_SORT);
        System.out.println("Lista ordenada (DESC): " + list.print());
        System.out.println();
    }
}

