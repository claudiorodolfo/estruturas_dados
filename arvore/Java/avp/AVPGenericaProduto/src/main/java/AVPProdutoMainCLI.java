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
     */
    void main() {
        AVP<Produto> avp = new AVP<>();
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
                        avp.inserir(p);
                        IO.println("Produto inserido: " + p);
                    }
                    case 2 -> {
                        long codigoBusca = lerLong("Código de barras do produto a buscar: ");
                        Produto busca = new Produto("Produto temporário", codigoBusca);
                        Produto encontrado = buscarProduto(avp, busca);
                        if (encontrado != null) {
                            IO.println("Produto encontrado: " + encontrado);
                        } else {
                            IO.println("Produto não encontrado.");
                        }
                    }
                    case 3 -> {
                        long codigoRemover = lerLong("Código de barras do produto a remover: ");
                        Produto remover = new Produto("Produto temporário", codigoRemover);
                        Produto removido = avp.apagar(remover);
                        if (removido != null) {
                            IO.println("Produto removido: " + removido);
                        } else {
                            IO.println("Produto não encontrado para remoção.");
                        }
                    }
                    case 4 -> {
                        IO.println("Produtos em ordem:");
                        IO.println(avp.imprimirEmOrdem());
                    }
                    case 5 -> {
                        IO.println("Produtos em pré-ordem:");
                        IO.println(avp.imprimirPreOrdem());
                    }
                    case 6 -> {
                        IO.println("Produtos em pós-ordem:");
                        IO.println(avp.imprimirPosOrdem());
                    }
                    case 7 -> {
                        avp.limpar();
                        IO.println("Árvore vermelho e preto de produtos limpa!");
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
     * Exibe o menu principal de opções para o usuário.
     */
    private static void exibirMenu() {
        IO.println("\n==== Árvore Vermelho e Preto de Produtos ====");
        IO.println("1. Inserir produto");
        IO.println("2. Buscar produto");
        IO.println("3. Remover produto");
        IO.println("4. Imprimir produtos em ordem");
        IO.println("5. Imprimir produtos em pré-ordem");
        IO.println("6. Imprimir produtos em pós-ordem");
        IO.println("7. Limpar árvore");
        IO.println("0. Sair");
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
