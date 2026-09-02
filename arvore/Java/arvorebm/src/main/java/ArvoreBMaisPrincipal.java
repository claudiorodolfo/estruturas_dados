package com.estruturasdados;

/**
 * Classe principal com exemplo de uso da árvore B+ (ArvoreBMais).
 */
public class ArvoreBMaisPrincipal {
    
    void main() {
        ArvoreBMais<Integer, String> arvore = new ArvoreBMais<>(3); // Ordem 3
        
        IO.println("""
            === Árvore B+ (B Mais) ===
            Comandos:
            1 - Inserir chave-valor
            2 - Buscar por chave
            3 - Remover por chave
            4 - Mostrar todos os valores
            5 - Mostrar árvore
            6 - Limpar árvore
            0 - Sair
            """);
        
        int opcao;
        do {
            opcao = Integer.parseInt(IO.readln("\nEscolha uma opção: ").trim());
            
            switch (opcao) {
                case 1 -> {
                    int chave = Integer.parseInt(IO.readln("Chave (inteiro): ").trim());
                    String valor = IO.readln("Valor (string): ");
                    arvore.inserir(chave, valor);
                    IO.println("Inserido: " + chave + " -> " + valor);
                }
                case 2 -> {
                    int chaveBusca = Integer.parseInt(IO.readln("Chave a buscar: ").trim());
                    String resultado = arvore.buscar(chaveBusca);
                    if (resultado != null) {
                        IO.println("Encontrado: " + chaveBusca + " -> " + resultado);
                    } else {
                        IO.println("Chave não encontrada: " + chaveBusca);
                    }
                }
                case 3 -> {
                    int chaveRemover = Integer.parseInt(IO.readln("Chave a remover: ").trim());
                    String removido = arvore.remover(chaveRemover);
                    if (removido != null) {
                        IO.println("Removido: " + chaveRemover + " -> " + removido);
                    } else {
                        IO.println("Chave não encontrada para remoção: " + chaveRemover);
                    }
                }
                case 4 -> {
                    String[] todosValores = arvore.obterTodosOsValores().toArray(new String[0]);
                    IO.println("Todos os valores em ordem:");
                    for (String val : todosValores) {
                        IO.print(val + " ");
                    }
                    IO.println();
                }
                case 5 -> {
                    IO.println("Estrutura da árvore:");
                    IO.println(arvore);
                }
                case 6 -> {
                    arvore.limpar();
                    IO.println("Árvore limpa!");
                }
                case 0 -> IO.println("Saindo...");
                default -> IO.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
