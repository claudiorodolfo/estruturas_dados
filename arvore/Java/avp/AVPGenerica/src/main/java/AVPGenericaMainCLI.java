import java.util.Scanner;

/**
 * Interface de linha de comando para manipular uma árvore vermelho e preto genérica.
 * Permite inserir, buscar, remover e imprimir elementos de qualquer tipo Comparable.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class AVPGenericaMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVP<String> avp = new AVP<>();
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
                        avp.inserir(elemento);
                        System.out.println("Elemento inserido: " + elemento);
                        break;
                    case 2:
                        System.out.print("Digite o elemento a buscar: ");
                        String busca = scanner.nextLine();
                        if (busca.trim().isEmpty()) {
                            System.out.println("Erro: Elemento não pode ser vazio.");
                            break;
                        }
                        if (avp.existe(busca)) {
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
                        String removido = avp.apagar(remover);
                        if (removido != null) {
                            System.out.println("Elemento removido: " + removido);
                        } else {
                            System.out.println("Elemento não encontrado para remoção.");
                        }
                        break;
                    case 4:
                        System.out.println("Elementos em ordem:");
                        System.out.println(avp.imprimirEmOrdem());
                        break;
                    case 5:
                        System.out.println("Elementos em pré-ordem:");
                        System.out.println(avp.imprimirPreOrdem());
                        break;
                    case 6:
                        System.out.println("Elementos em pós-ordem:");
                        System.out.println(avp.imprimirPosOrdem());
                        break;
                    case 7:
                        avp.limpar();
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
        AVP<Integer> avpInt = new AVP<>();
        
        // Inserir alguns inteiros
        avpInt.inserir(50);
        avpInt.inserir(30);
        avpInt.inserir(70);
        avpInt.inserir(20);
        avpInt.inserir(40);
        avpInt.inserir(60);
        avpInt.inserir(80);
        
        System.out.println("Elementos em ordem: " + avpInt.imprimirEmOrdem());
        System.out.println("Elementos em pré-ordem: " + avpInt.imprimirPreOrdem());
        System.out.println("Elementos em pós-ordem: " + avpInt.imprimirPosOrdem());
        
        // Testar busca
        System.out.println("Existe 30? " + avpInt.existe(30));
        System.out.println("Existe 90? " + avpInt.existe(90));
        
        // Testar remoção
        Integer removido = avpInt.apagar(30);
        System.out.println("Removido: " + removido);
        System.out.println("Após remoção: " + avpInt.imprimirEmOrdem());
    }

    /**
     * Testa a árvore com elementos decimais.
     */
    private static void testarComDecimais() {
        System.out.println("\n=== Teste com Decimais ===");
        AVP<Double> avpDouble = new AVP<>();
        
        // Inserir alguns decimais
        avpDouble.inserir(3.14);
        avpDouble.inserir(2.71);
        avpDouble.inserir(1.41);
        avpDouble.inserir(2.23);
        avpDouble.inserir(1.73);
        
        System.out.println("Elementos em ordem: " + avpDouble.imprimirEmOrdem());
        System.out.println("Elementos em pré-ordem: " + avpDouble.imprimirPreOrdem());
        System.out.println("Elementos em pós-ordem: " + avpDouble.imprimirPosOrdem());
        
        // Testar busca
        System.out.println("Existe 3.14? " + avpDouble.existe(3.14));
        System.out.println("Existe 2.5? " + avpDouble.existe(2.5));
        
        // Testar remoção
        Double removido = avpDouble.apagar(2.71);
        System.out.println("Removido: " + removido);
        System.out.println("Após remoção: " + avpDouble.imprimirEmOrdem());
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