package br.edu.ifba.vdc.bsi.ed;

/**
 * Classe principal de interface de linha de comando (CLI) para manipulação de uma árvore AVL de inteiros.
 * Permite inserir, remover, buscar, imprimir e limpar a árvore via menu interativo.
 */
public class AVLPrincipalCLI {
    /**
     * Método principal. Inicia o menu interativo para manipulação da árvore AVL.
     */
    void main() {
        //Testar entrada nesta ordem:
        //4 2 6 1 3 5 7
        //Pre Ordem: [4,2,1,3,6,5,7]
        //Em Ordem:  [1,2,3,4,5,6,7]
        //Pos Ordem: [1,3,2,5,7,6,4]
        int opcao, valor;
        Arborizavel<Integer> arvore = new AVL<>();
        do {
            exibirMenu();
            opcao = Integer.parseInt(IO.readln("Escolha uma opcao (0-5): ").trim());
            switch (opcao) {
                case 0 -> IO.println("Saindo da AVL. Ate mais!");
                case 1 -> {
                    valor = Integer.parseInt(IO.readln("Digite o valor : ").trim());
                    arvore.inserir(valor);
                }
                case 2 -> {
                    valor = Integer.parseInt(IO.readln("Digite o valor : ").trim());
                    arvore.apagar(valor);
                }
                case 3 -> {
                    valor = Integer.parseInt(IO.readln("Digite o valor : ").trim());
                    IO.println(arvore.existe(valor));
                }
                case 4 -> {
                    exibirMenuImprimir();
                    int opcaoImprimir = Integer.parseInt(IO.readln("Escolha uma opcao (1-3): ").trim());
                    switch (opcaoImprimir) {
                        case 1 -> {
                            IO.println("");
                            IO.println("Pre-Ordem: " + arvore.imprimirPreOrdem());
                            IO.println("");
                        }
                        case 2 -> {
                            IO.println("");
                            IO.println("Em-Ordem: " + arvore.imprimirEmOrdem());
                            IO.println("");
                        }
                        case 3 -> {
                            IO.println("");
                            IO.println("Pos-Ordem: " + arvore.imprimirPosOrdem());
                            IO.println("");
                        }
                        default -> IO.println("Opcao invalida.");
                    }
                }
                case 5 -> {
                    arvore.limpar();
                    IO.println("Arvore Limpa!");
                }
                default -> IO.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    /**
     * Exibe o menu principal de opções para o usuário.
     */
    private static void exibirMenu() {
        IO.println("=== Arvore Binaria AVL ===");
        IO.println("0. SAIR");
        IO.println("1. Inserir");
        IO.println("2. Apagar");
        IO.println("3. Existe?");
        IO.println("4. Imprimir");
        IO.println("5. Limpar");
    }

    /**
     * Exibe o menu de opções de impressão para o usuário.
     */
    private static void exibirMenuImprimir() {
        IO.println("1. Imprimir Pre-Ordem");
        IO.println("2. Imprimir Em-Ordem");
        IO.println("3. Imprimir Pos-Ordem");
    }
}
