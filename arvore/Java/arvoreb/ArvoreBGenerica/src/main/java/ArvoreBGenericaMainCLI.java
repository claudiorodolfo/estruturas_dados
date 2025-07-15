package arvoreb;

import java.util.Scanner;

/**
 * Interface de linha de comando para manipular uma árvore B genérica.
 * Permite inserir, buscar, remover e imprimir elementos de qualquer tipo Comparable.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreBGenericaMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreB<String> arvoreB = new ArvoreB<>();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt(scanner, "Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o elemento a inserir: ");
                        String elemento = scanner.nextLine();
                        if (elemento.trim().isEmpty()) {
                            System.out.println("Erro: Elemento não pode ser vazio.");
                            break;
                        }
                        arvoreB.inserir(elemento);
                        System.out.println("Elemento inserido: " + elemento);
                        break;
                    case 2:
                        System.out.print("Digite o elemento a buscar: ");
                        String busca = scanner.nextLine();
                        if (busca.trim().isEmpty()) {
                            System.out.println("Erro: Elemento não pode ser vazio.");
                            break;
                        }
                        if (arvoreB.existe(busca)) {
                            System.out.println("Elemento encontrado: " + busca);
                        } else {
                            System.out.println("Elemento não encontrado.");
                        }
                        break;
                    case 3:
                        System.out.print("Digite o elemento a remover: ");
                        String remover = scanner.nextLine();
                        if (remover.trim().isEmpty()) {
                            System.out.println("Erro: Elemento não pode ser vazio.");
                            break;
                        }
                        String removido = arvoreB.apagar(remover);
                        if (removido != null) {
                            System.out.println("Elemento removido: " + removido);
                        } else {
                            System.out.println("Elemento não encontrado para remoção.");
                        }
                        break;
                    case 4:
                        System.out.println("Elementos em ordem:");
                        System.out.println(arvoreB.imprimirEmOrdem());
                        break;
                    case 5:
                        System.out.println("Elementos em pré-ordem:");
                        System.out.println(arvoreB.imprimirPreOrdem());
                        break;
                    case 6:
                        System.out.println("Elementos em pós-ordem:");
                        System.out.println(arvoreB.imprimirPosOrdem());
                        break;
                    case 7:
                        arvoreB.limpar();
                        System.out.println("Árvore B genérica limpa!");
                        break;
                    case 8:
                        testarComInteiros();
                        break;
                    case 9:
                        testarComDecimais();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);
        scanner.close();
    }

    /**
     * Exibe o menu principal de opções.
     */
    private static void exibirMenu() {
        System.out.println("\n==== Árvore B Genérica ====");
        System.out.println("1. Inserir elemento (String)");
        System.out.println("2. Buscar elemento");
        System.out.println("3. Remover elemento");
        System.out.println("4. Imprimir elementos em ordem");
        System.out.println("5. Imprimir elementos em pré-ordem");
        System.out.println("6. Imprimir elementos em pós-ordem");
        System.out.println("7. Limpar árvore");
        System.out.println("8. Testar com inteiros");
        System.out.println("9. Testar com decimais");
        System.out.println("0. Sair");
    }

    /**
     * Testa a árvore com elementos inteiros.
     */
    private static void testarComInteiros() {
        System.out.println("\n=== Teste com Inteiros ===");
        ArvoreB<Integer> arvoreBInt = new ArvoreB<>();
        
        // Inserir alguns inteiros
        arvoreBInt.inserir(50);
        arvoreBInt.inserir(30);
        arvoreBInt.inserir(70);
        arvoreBInt.inserir(20);
        arvoreBInt.inserir(40);
        arvoreBInt.inserir(60);
        arvoreBInt.inserir(80);
        
        System.out.println("Elementos em ordem: " + arvoreBInt.imprimirEmOrdem());
        System.out.println("Elementos em pré-ordem: " + arvoreBInt.imprimirPreOrdem());
        System.out.println("Elementos em pós-ordem: " + arvoreBInt.imprimirPosOrdem());
        
        // Testar busca
        System.out.println("Existe 30? " + arvoreBInt.existe(30));
        System.out.println("Existe 90? " + arvoreBInt.existe(90));
        
        // Testar remoção
        Integer removido = arvoreBInt.apagar(30);
        System.out.println("Removido: " + removido);
        System.out.println("Após remoção: " + arvoreBInt.imprimirEmOrdem());
    }

    /**
     * Testa a árvore com elementos decimais.
     */
    private static void testarComDecimais() {
        System.out.println("\n=== Teste com Decimais ===");
        ArvoreB<Double> arvoreBDouble = new ArvoreB<>();
        
        // Inserir alguns decimais
        arvoreBDouble.inserir(3.14);
        arvoreBDouble.inserir(2.71);
        arvoreBDouble.inserir(1.41);
        arvoreBDouble.inserir(2.23);
        arvoreBDouble.inserir(1.73);
        
        System.out.println("Elementos em ordem: " + arvoreBDouble.imprimirEmOrdem());
        System.out.println("Elementos em pré-ordem: " + arvoreBDouble.imprimirPreOrdem());
        System.out.println("Elementos em pós-ordem: " + arvoreBDouble.imprimirPosOrdem());
        
        // Testar busca
        System.out.println("Existe 3.14? " + arvoreBDouble.existe(3.14));
        System.out.println("Existe 2.5? " + arvoreBDouble.existe(2.5));
        
        // Testar remoção
        Double removido = arvoreBDouble.apagar(2.71);
        System.out.println("Removido: " + removido);
        System.out.println("Após remoção: " + arvoreBDouble.imprimirEmOrdem());
    }

    /**
     * Lê um valor inteiro do usuário com tratamento de erro.
     * 
     * @param scanner Scanner para leitura
     * @param msg Mensagem a ser exibida
     * @return Valor inteiro lido
     */
    private static int lerInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
} 