import java.util.Scanner;

public class ABPProdutoMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ABP<Produto> abp = new ABP<>();
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
                        abp.inserir(p);
                        System.out.println("Produto inserido: " + p);
                        break;
                    case 2:
                        long codigoBusca = lerLong(scanner, "Código de barras do produto a buscar: ");
                        Produto busca = new Produto("Produto temporário", codigoBusca);
                        Produto encontrado = buscarProduto(abp, busca);
                        if (encontrado != null) {
                            System.out.println("Produto encontrado: " + encontrado);
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 3:
                        long codigoRemover = lerLong(scanner, "Código de barras do produto a remover: ");
                        Produto remover = new Produto("Produto temporário", codigoRemover);
                        Produto removido = abp.apagar(remover);
                        if (removido != null) {
                            System.out.println("Produto removido: " + removido);
                        } else {
                            System.out.println("Produto não encontrado para remoção.");
                        }
                        break;
                    case 4:
                        System.out.println("Produtos em ordem:");
                        System.out.println(abp.imprimirEmOrdem());
                        break;
                    case 5:
                        abp.limpar();
                        System.out.println("Árvore de produtos limpa!");
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

    private static void exibirMenu() {
        System.out.println("\n==== Árvore Binária de Produtos ====");
        System.out.println("1. Inserir produto");
        System.out.println("2. Buscar produto");
        System.out.println("3. Remover produto");
        System.out.println("4. Imprimir produtos em ordem");
        System.out.println("5. Limpar árvore");
        System.out.println("0. Sair");
    }

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

    // Busca o produto real na árvore, retornando o objeto completo se encontrado
    private static Produto buscarProduto(ABP<Produto> abp, Produto busca) {
        NoTriplo<Produto> no = abp.getRaiz();
        while (no != null) {
            int cmp = busca.compareTo(no.getDado());
            if (cmp == 0) return no.getDado();
            no = (cmp < 0) ? no.getEsquerda() : no.getDireita();
        }
        return null;
    }
}