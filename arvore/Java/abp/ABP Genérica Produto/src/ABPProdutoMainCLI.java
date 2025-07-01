import java.util.Scanner;

public class ABPProdutoMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ABP<Produto> abp = new ABP<>();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt(scanner, "Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    long codigo = lerLong(scanner, "Código de barras: ");
                    Produto p = new Produto(nome, codigo);
                    abp.inserir(p);
                    System.out.println("Produto inserido!");
                    break;
                case 2:
                    long codigoBusca = lerLong(scanner, "Código de barras do produto a buscar: ");
                    Produto busca = new Produto("", codigoBusca);
                    boolean existe = abp.existe(busca);
                    System.out.println(existe ? "Produto encontrado!" : "Produto não encontrado.");
                    break;
                case 3:
                    long codigoRemover = lerLong(scanner, "Código de barras do produto a remover: ");
                    Produto remover = new Produto("", codigoRemover);
                    Produto removido = abp.apagar(remover);
                    System.out.println(removido != null ? "Produto removido!" : "Produto não encontrado para remoção.");
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
}