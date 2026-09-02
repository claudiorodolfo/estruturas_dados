package br.edu.ifba.vdc.bsi.ed;

/**
 * Interface de linha de comando para manipular uma árvore vermelho e preto genérica.
 * Permite inserir, buscar, remover e imprimir elementos de qualquer tipo Comparable.
 *
 * Menu interativo para testar operações básicas e avançadas.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class AVPGenericaMainCLI {
    /**
     * Método principal. Inicia o menu interativo para manipulação da árvore vermelho e preto.
     */
    void main() {
        AVP<String> avp = new AVP<>();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> {
                        String elemento = IO.readln("Digite o elemento a inserir: ");
                        if (elemento.trim().isEmpty()) {
                        IO.println("Erro: Elemento não pode ser vazio.");

                        }
                        avp.inserir(elemento);
                        IO.println("Elemento inserido: " + elemento);
                    }
                    case 2 -> {
                        String busca = IO.readln("Digite o elemento a buscar: ");
                        if (busca.trim().isEmpty()) {
                        IO.println("Erro: Elemento não pode ser vazio.");

                        }
                        if (avp.existe(busca)) {
                            IO.println("Elemento encontrado: " + busca);
                        } else {
                            IO.println("Elemento não encontrado.");
                        }
                    }
                    case 3 -> {
                        String remover = IO.readln("Digite o elemento a remover: ");
                        if (remover.trim().isEmpty()) {
                        IO.println("Erro: Elemento não pode ser vazio.");

                        }
                        String removido = avp.apagar(remover);
                        if (removido != null) {
                            IO.println("Elemento removido: " + removido);
                        } else {
                            IO.println("Elemento não encontrado para remoção.");
                        }
                    }
                    case 4 -> {
                        IO.println("Elementos em ordem:");
                        IO.println(avp.imprimirEmOrdem());
                    }
                    case 5 -> {
                        IO.println("Elementos em pré-ordem:");
                        IO.println(avp.imprimirPreOrdem());
                    }
                    case 6 -> {
                        IO.println("Elementos em pós-ordem:");
                        IO.println(avp.imprimirPosOrdem());
                    }
                    case 7 -> {
                        avp.limpar();
                        IO.println("Árvore vermelho e preto genérica limpa!");
                    }
                    case 8 -> testarComInteiros();
                    case 9 -> testarComDecimais();
                    case 0 -> IO.println("Saindo...");
                    default -> IO.println("Opção inválida!");
                }
            } catch (Exception e) {
                IO.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Exibe o menu principal de opções para o usuário.
     */
    private static void exibirMenu() {
        IO.println("\n==== Árvore Vermelho e Preto Genérica ====");
        IO.println("1. Inserir elemento (String)");
        IO.println("2. Buscar elemento");
        IO.println("3. Remover elemento");
        IO.println("4. Imprimir elementos em ordem");
        IO.println("5. Imprimir elementos em pré-ordem");
        IO.println("6. Imprimir elementos em pós-ordem");
        IO.println("7. Limpar árvore");
        IO.println("8. Testar com inteiros");
        IO.println("9. Testar com decimais");
        IO.println("0. Sair");
    }

    /**
     * Testa a árvore com elementos inteiros, exibindo operações básicas.
     */
    private static void testarComInteiros() {
        IO.println("\n=== Teste com Inteiros ===");
        AVP<Integer> avpInt = new AVP<>();
        
        // Inserir alguns inteiros
        avpInt.inserir(50);
        avpInt.inserir(30);
        avpInt.inserir(70);
        avpInt.inserir(20);
        avpInt.inserir(40);
        avpInt.inserir(60);
        avpInt.inserir(80);
        
        IO.println("Elementos em ordem: " + avpInt.imprimirEmOrdem());
        IO.println("Elementos em pré-ordem: " + avpInt.imprimirPreOrdem());
        IO.println("Elementos em pós-ordem: " + avpInt.imprimirPosOrdem());
        
        // Testar busca
        IO.println("Existe 30? " + avpInt.existe(30));
        IO.println("Existe 90? " + avpInt.existe(90));
        
        // Testar remoção
        Integer removido = avpInt.apagar(30);
        IO.println("Removido: " + removido);
        IO.println("Após remoção: " + avpInt.imprimirEmOrdem());
    }

    /**
     * Testa a árvore com elementos decimais, exibindo operações básicas.
     */
    private static void testarComDecimais() {
        IO.println("\n=== Teste com Decimais ===");
        AVP<Double> avpDouble = new AVP<>();
        
        // Inserir alguns decimais
        avpDouble.inserir(3.14);
        avpDouble.inserir(2.71);
        avpDouble.inserir(1.41);
        avpDouble.inserir(2.23);
        avpDouble.inserir(1.73);
        
        IO.println("Elementos em ordem: " + avpDouble.imprimirEmOrdem());
        IO.println("Elementos em pré-ordem: " + avpDouble.imprimirPreOrdem());
        IO.println("Elementos em pós-ordem: " + avpDouble.imprimirPosOrdem());
        
        // Testar busca
        IO.println("Existe 3.14? " + avpDouble.existe(3.14));
        IO.println("Existe 2.5? " + avpDouble.existe(2.5));
        
        // Testar remoção
        Double removido = avpDouble.apagar(2.71);
        IO.println("Removido: " + removido);
        IO.println("Após remoção: " + avpDouble.imprimirEmOrdem());
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
}
