import java.util.Scanner;

/**
 * Interface de linha de comando para manipular uma árvore B de produtos.
 * Permite inserir, buscar, remover e imprimir produtos com validação de dados.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreBProdutoMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreB<Produto> arvoreB = new ArvoreB<>();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt(scanner, "Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome do produto: ");
                        String nome = scanner.nextLine();
                        long codigo = lerLong(scanner, "Código de barras: ");
                        Produto p = new Produto(nome, codigo);
                        arvoreB.inserir(p);
                        System.out.println("Produto inserido: " + p);
                        break;
                    case 2:
                        long codigoBusca = lerLong(scanner, "Código de barras do produto a buscar: ");
                        Produto busca = new Produto("Produto temporário", codigoBusca);
                        if (arvoreB.existe(busca)) {
                            System.out.println("Produto encontrado com código: " + codigoBusca);
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 3:
                        long codigoRemover = lerLong(scanner, "Código de barras do produto a remover: ");
                        Produto remover = new Produto("Produto temporário", codigoRemover);
                        Produto removido = arvoreB.apagar(remover);
                        if (removido != null) {
                            System.out.println("Produto removido: " + removido);
                        } else {
                            System.out.println("Produto não encontrado para remoção.");
                        }
                        break;
                    case 4:
                        System.out.println("Produtos em ordem:");
                        System.out.println(arvoreB.imprimirEmOrdem());
                        break;
                    case 5:
                        arvoreB.limpar();
                        System.out.println("Árvore B de produtos limpa!");
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
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
        System.out.println("\n==== Árvore B de Produtos ====");
        System.out.println("1. Inserir produto");
        System.out.println("2. Buscar produto");
        System.out.println("3. Remover produto");
        System.out.println("4. Imprimir produtos em ordem");
        System.out.println("5. Limpar árvore");
        System.out.println("0. Sair");
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

    /**
     * Lê um valor long do usuário com tratamento de erro.
     * 
     * @param scanner Scanner para leitura
     * @param msg Mensagem a ser exibida
     * @return Valor long lido
     */
    private static long lerLong(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
} 