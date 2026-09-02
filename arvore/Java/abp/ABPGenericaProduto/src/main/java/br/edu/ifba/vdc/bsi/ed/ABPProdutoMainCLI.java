package br.edu.ifba.vdc.bsi.ed;

public class ABPProdutoMainCLI {
    void main() {
        ABP<Produto> abp = new ABP<>();
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
                        abp.inserir(p);
                        IO.println("Produto inserido: " + p);
                    }
                    case 2 -> {
                        long codigoBusca = lerLong("Código de barras do produto a buscar: ");
                        Produto busca = new Produto("Produto temporário", codigoBusca);
                        Produto encontrado = buscarProduto(abp, busca);
                        if (encontrado != null) {
                            IO.println("Produto encontrado: " + encontrado);
                        } else {
                            IO.println("Produto não encontrado.");
                        }
                    }
                    case 3 -> {
                        long codigoRemover = lerLong("Código de barras do produto a remover: ");
                        Produto remover = new Produto("Produto temporário", codigoRemover);
                        Produto removido = abp.apagar(remover);
                        if (removido != null) {
                            IO.println("Produto removido: " + removido);
                        } else {
                            IO.println("Produto não encontrado para remoção.");
                        }
                    }
                    case 4 -> {
                        IO.println("Produtos em ordem:");
                        IO.println(abp.imprimirEmOrdem());
                    }
                    case 5 -> {
                        abp.limpar();
                        IO.println("Árvore de produtos limpa!");
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

    private static void exibirMenu() {
        IO.println("""
            
            ==== Árvore Binária de Produtos ====
            1. Inserir produto
            2. Buscar produto
            3. Remover produto
            4. Imprimir produtos em ordem
            5. Limpar árvore
            0. Sair
            """);
    }

    private static int lerInt(String msg) {
        while (true) {
            try {
                return Integer.parseInt(IO.readln(msg).trim());
            } catch (NumberFormatException e) {
                IO.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static long lerLong(String msg) {
        while (true) {
            try {
                return Long.parseLong(IO.readln(msg).trim());
            } catch (NumberFormatException e) {
                IO.println("Valor inválido. Tente novamente.");
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
