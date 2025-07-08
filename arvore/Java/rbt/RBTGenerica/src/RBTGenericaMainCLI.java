import java.util.Scanner;

/**
 * Interface de linha de comando para manipular uma árvore vermelho e preto genérica.
 * Permite inserir, buscar, remover e imprimir elementos de qualquer tipo Comparable.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class RBTGenericaMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RBT<String> rbt = new RBT<>();
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
                        rbt.inserir(elemento);
                        System.out.println("Elemento inserido: " + elemento);
                        break;
                    case 2:
                        System.out.print("Digite o elemento a buscar: ");
                        String busca = scanner.nextLine();
                        if (busca.trim().isEmpty()) {
                            System.out.println("Erro: Elemento não pode ser vazio.");
                            break;
                        }
                        if (rbt.existe(busca)) {
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
                        String removido = rbt.apagar(remover);
                        if (removido != null) {
                            System.out.println("Elemento removido: " + removido);
                        } else {
                            System.out.println("Elemento não encontrado para remoção.");
                        }
                        break;
                    case 4:
                        System.out.println("Elementos em ordem:");
                        System.out.println(rbt.imprimirEmOrdem());
                        break;
                    case 5:
                        System.out.println("Elementos em pré-ordem:");
                        System.out.println(rbt.imprimirPreOrdem());
                        break;
                    case 6:
                        System.out.println("Elementos em pós-ordem:");
                        System.out.println(rbt.imprimirPosOrdem());
                        break;
                    case 7:
                        rbt.limpar();
                        System.out.println("Árvore vermelho e preto genérica limpa!");
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
        System.out.println("\n==== Árvore Vermelho e Preto Genérica ====");
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
        RBT<Integer> rbtInt = new RBT<>();
        
        // Inserir alguns inteiros
        rbtInt.inserir(50);
        rbtInt.inserir(30);
        rbtInt.inserir(70);
        rbtInt.inserir(20);
        rbtInt.inserir(40);
        rbtInt.inserir(60);
        rbtInt.inserir(80);
        
        System.out.println("Elementos em ordem: " + rbtInt.imprimirEmOrdem());
        System.out.println("Elementos em pré-ordem: " + rbtInt.imprimirPreOrdem());
        System.out.println("Elementos em pós-ordem: " + rbtInt.imprimirPosOrdem());
        
        // Testar busca
        System.out.println("Existe 30? " + rbtInt.existe(30));
        System.out.println("Existe 90? " + rbtInt.existe(90));
        
        // Testar remoção
        Integer removido = rbtInt.apagar(30);
        System.out.println("Removido: " + removido);
        System.out.println("Após remoção: " + rbtInt.imprimirEmOrdem());
    }

    /**
     * Testa a árvore com elementos decimais.
     */
    private static void testarComDecimais() {
        System.out.println("\n=== Teste com Decimais ===");
        RBT<Double> rbtDouble = new RBT<>();
        
        // Inserir alguns decimais
        rbtDouble.inserir(3.14);
        rbtDouble.inserir(2.71);
        rbtDouble.inserir(1.41);
        rbtDouble.inserir(2.23);
        rbtDouble.inserir(1.73);
        
        System.out.println("Elementos em ordem: " + rbtDouble.imprimirEmOrdem());
        System.out.println("Elementos em pré-ordem: " + rbtDouble.imprimirPreOrdem());
        System.out.println("Elementos em pós-ordem: " + rbtDouble.imprimirPosOrdem());
        
        // Testar busca
        System.out.println("Existe 3.14? " + rbtDouble.existe(3.14));
        System.out.println("Existe 2.5? " + rbtDouble.existe(2.5));
        
        // Testar remoção
        Double removido = rbtDouble.apagar(2.71);
        System.out.println("Removido: " + removido);
        System.out.println("Após remoção: " + rbtDouble.imprimirEmOrdem());
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