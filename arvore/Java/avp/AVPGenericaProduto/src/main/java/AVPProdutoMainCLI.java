import java.util.Scanner;

/**
 * Interface de linha de comando para manipular uma árvore vermelho e preto de produtos.
 * Permite inserir, buscar, remover e imprimir produtos com validação de dados.
 *
 * Menu interativo para testar operações básicas e avançadas.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class AVPProdutoMainCLI {
    /**
     * Método principal. Inicia o menu interativo para manipulação da árvore vermelho e preto de produtos.
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVP<Produto> avp = new AVP<>();
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
                        avp.inserir(p);
                        System.out.println("Produto inserido: " + p);
                        break;
                    case 2:
                        long codigoBusca = lerLong(scanner, "Código de barras do produto a buscar: ");
                        Produto busca = new Produto("Produto temporário", codigoBusca);
                        Produto encontrado = buscarProduto(avp, busca);
                        if (encontrado != null) {
                            System.out.println("Produto encontrado: " + encontrado);
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 3:
                        long codigoRemover = lerLong(scanner, "Código de barras do produto a remover: ");
                        Produto remover = new Produto("Produto temporário", codigoRemover);
                        Produto removido = avp.apagar(remover);
                        if (removido != null) {
                            System.out.println("Produto removido: " + removido);
                        } else {
                            System.out.println("Produto não encontrado para remoção.");
                        }
                        break;
                    case 4:
                        System.out.println("Produtos em ordem:");
                        System.out.println(avp.imprimirEmOrdem());
                        break;
                    case 5:
                        System.out.println("Produtos em pré-ordem:");
                        System.out.println(avp.imprimirPreOrdem());
                        break;
                    case 6:
                        System.out.println("Produtos em pós-ordem:");
                        System.out.println(avp.imprimirPosOrdem());
                        break;
                    case 7:
                        avp.limpar();
                        System.out.println("Árvore vermelho e preto de produtos limpa!");
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
     * Exibe o menu principal de opções para o usuário.
     */
    private static void exibirMenu() {
        System.out.println("\n==== Árvore Vermelho e Preto de Produtos ====");
        System.out.println("1. Inserir produto");
        System.out.println("2. Buscar produto");
        System.out.println("3. Remover produto");
        System.out.println("4. Imprimir produtos em ordem");
        System.out.println("5. Imprimir produtos em pré-ordem");
        System.out.println("6. Imprimir produtos em pós-ordem");
        System.out.println("7. Limpar árvore");
        System.out.println("0. Sair");
    }

    /**
     * Busca o produto real na árvore, retornando o objeto completo se encontrado.
     *
     * @param avp Árvore vermelho e preto de produtos
     * @param busca Produto com código de barras para buscar
     * @return Produto encontrado ou null se não encontrado
     */
    private static Produto buscarProduto(AVP<Produto> avp, Produto busca) {
        NoTriplo<Produto> no = avp.getRaiz();
        while (no != null) {
            int cmp = busca.compareTo(no.getDado());
            if (cmp == 0) return no.getDado();
            no = (cmp < 0) ? no.getEsquerda() : no.getDireita();
        }
        return null;
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