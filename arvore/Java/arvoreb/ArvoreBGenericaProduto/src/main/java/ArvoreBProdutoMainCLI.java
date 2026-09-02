/**
 * Interface de linha de comando para manipular uma árvore B de produtos.
 * Permite inserir, buscar, remover e imprimir produtos com validação de dados.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreBProdutoMainCLI {
    void main() {
        ArvoreB<Produto> arvoreB = new ArvoreB<>();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> {
                        String nome = IO.readln("Nome do produto: ");
                        long codigo = lerLong("Código de barras: ");
                        Produto p = new Produto(nome, codigo);
                        arvoreB.inserir(p);
                        IO.println("Produto inserido: " + p);
                    }
                    case 2 -> {
                        long codigoBusca = lerLong("Código de barras do produto a buscar: ");
                        Produto busca = new Produto("Produto temporário", codigoBusca);
                        if (arvoreB.existe(busca)) {
                            IO.println("Produto encontrado com código: " + codigoBusca);
                        } else {
                            IO.println("Produto não encontrado.");
                        }
                    }
                    case 3 -> {
                        long codigoRemover = lerLong("Código de barras do produto a remover: ");
                        Produto remover = new Produto("Produto temporário", codigoRemover);
                        Produto removido = arvoreB.apagar(remover);
                        if (removido != null) {
                            IO.println("Produto removido: " + removido);
                        } else {
                            IO.println("Produto não encontrado para remoção.");
                        }
                    }
                    case 4 -> {
                        IO.println("Produtos em ordem:");
                        IO.println(arvoreB.imprimirEmOrdem());
                    }
                    case 5 -> {
                        arvoreB.limpar();
                        IO.println("Árvore B de produtos limpa!");
                    }
                    case 0 -> IO.println("Saindo...");
                    default -> IO.println("Opção inválida!");
                }
            } catch (IllegalArgumentException e) {
                IO.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                IO.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Exibe o menu principal de opções.
     */
    private static void exibirMenu() {
        IO.println("\n==== Árvore B de Produtos ====");
        IO.println("1. Inserir produto");
        IO.println("2. Buscar produto");
        IO.println("3. Remover produto");
        IO.println("4. Imprimir produtos em ordem");
        IO.println("5. Limpar árvore");
        IO.println("0. Sair");
    }

    /**
     * Lê um valor inteiro do usuário com tratamento de erro.
     * 
     * @param msg Mensagem a ser exibida
     * @return Valor inteiro lido
     */
    private static int lerInt(String msg) {
        while (true) {
            try {
                return Integer.parseInt(IO.readln(msg).trim());
            } catch (NumberFormatException e) {
                IO.println("Valor inválido. Tente novamente.");
            }
        }
    }

    /**
     * Lê um valor long do usuário com tratamento de erro.
     * 
     * @param msg Mensagem a ser exibida
     * @return Valor long lido
     */
    private static long lerLong(String msg) {
        while (true) {
            try {
                return Long.parseLong(IO.readln(msg).trim());
            } catch (NumberFormatException e) {
                IO.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
