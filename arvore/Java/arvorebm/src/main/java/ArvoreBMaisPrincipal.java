package com.estruturasdados;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal com exemplo de uso da árvore B+ (ArvoreBMais).
 */
public class ArvoreBMaisPrincipal {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBMais<Integer, String> arvore = new ArvoreBMais<>(3); // Ordem 3
        
        System.out.println("=== Árvore B+ (B Mais) ===");
        System.out.println("Comandos:");
        System.out.println("1 - Inserir chave-valor");
        System.out.println("2 - Buscar por chave");
        System.out.println("3 - Remover por chave");
        System.out.println("4 - Mostrar todos os valores");
        System.out.println("5 - Mostrar árvore");
        System.out.println("6 - Limpar árvore");
        System.out.println("0 - Sair");
        
        int opcao;
        do {
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Chave (inteiro): ");
                    int chave = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha
                    System.out.print("Valor (string): ");
                    String valor = scanner.nextLine();
                    arvore.inserir(chave, valor);
                    System.out.println("Inserido: " + chave + " -> " + valor);
                    break;
                case 2:
                    System.out.print("Chave a buscar: ");
                    int chaveBusca = scanner.nextInt();
                    String resultado = arvore.buscar(chaveBusca);
                    if (resultado != null) {
                        System.out.println("Encontrado: " + chaveBusca + " -> " + resultado);
                    } else {
                        System.out.println("Chave não encontrada: " + chaveBusca);
                    }
                    break;
                case 3:
                    System.out.print("Chave a remover: ");
                    int chaveRemover = scanner.nextInt();
                    String removido = arvore.remover(chaveRemover);
                    if (removido != null) {
                        System.out.println("Removido: " + chaveRemover + " -> " + removido);
                    } else {
                        System.out.println("Chave não encontrada para remoção: " + chaveRemover);
                    }
                    break;
                case 4:
                    List<String> todosValores = arvore.obterTodosOsValores();
                    System.out.println("Todos os valores em ordem:");
                    for (String val : todosValores) {
                        System.out.print(val + " ");
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Estrutura da árvore:");
                    System.out.println(arvore);
                    break;
                case 6:
                    arvore.limpar();
                    System.out.println("Árvore limpa!");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
} 