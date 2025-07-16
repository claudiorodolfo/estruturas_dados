import java.util.Scanner;

/**
 * Interface de linha de comando (CLI) para interagir com uma Árvore B.
 * Esta classe fornece uma interface interativa para testar e demonstrar
 * as funcionalidades da Árvore B, permitindo inserção, remoção, busca e
 * visualização dos elementos.
 * 
 * <p><strong>Funcionalidades disponíveis:</strong></p>
 * <ul>
 *   <li><strong>Configuração:</strong> Definir a ordem da árvore B</li>
 *   <li><strong>Inserção:</strong> Adicionar novos elementos</li>
 *   <li><strong>Remoção:</strong> Remover elementos existentes</li>
 *   <li><strong>Busca:</strong> Verificar se elementos existem</li>
 *   <li><strong>Visualização:</strong> Imprimir elementos em ordem</li>
 * </ul>
 * 
 * <p><strong>Exemplo de uso:</strong></p>
 * <pre>{@code
 * // Executar o programa
 * java ArvoreBMainCLI
 * 
 * // Seguir as instruções no console:
 * // 1. Informar a ordem da árvore (ex: 3)
 * // 2. Escolher operações do menu
 * // 3. Inserir elementos (ex: 10, 5, 15)
 * // 4. Testar busca e remoção
 * }</pre>
 * 
 * <p><strong>Menu de operações:</strong></p>
 * <ol>
 *   <li><strong>Inserir chave:</strong> Adiciona um novo elemento à árvore</li>
 *   <li><strong>Remover chave:</strong> Remove um elemento da árvore</li>
 *   <li><strong>Buscar chave:</strong> Verifica se um elemento existe</li>
 *   <li><strong>Imprimir árvore:</strong> Mostra todos os elementos em ordem</li>
 *   <li><strong>Sair:</strong> Encerra o programa</li>
 * </ol>
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 * @see ArvoreB
 * @see NoArvoreB
 */
public class ArvoreBMainCLI {
        
    /**
     * Método principal que inicia a interface de linha de comando.
     * Este método configura a árvore B com a ordem especificada pelo usuário
     * e apresenta um menu interativo para operações na árvore.
     * 
     * <p><strong>Fluxo do programa:</strong></p>
     * <ol>
     *   <li>Solicita a ordem da árvore B ao usuário</li>
     *   <li>Valida a ordem (mínimo 2)</li>
     *   <li>Cria uma árvore B com a ordem especificada</li>
     *   <li>Apresenta menu de operações</li>
     *   <li>Processa as escolhas do usuário até sair</li>
     * </ol>
     * 
     * <p><strong>Tratamento de erros:</strong></p>
     * <ul>
     *   <li>Validação da ordem da árvore</li>
     *   <li>Tratamento de entrada inválida</li>
     *   <li>Feedback claro para o usuário</li>
     * </ul>
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita e valida a ordem da árvore
        System.out.print("Informe a ordem da árvore B (t >= 2): ");
        int ordem = scanner.nextInt();

        if (ordem < 2) {
            System.out.println("Ordem inválida. Deve ser no mínimo 2.");
            return;
        }

        // Cria a árvore B com a ordem especificada
        ArvoreB<Integer> arvore = new ArvoreB<>(ordem);
        System.out.println("Árvore B criada com ordem " + ordem + ".");

        // Loop principal do menu
        while (true) {
            exibirMenu();
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    inserirChave(scanner, arvore);
                    break;
                case 2:
                    removerChave(scanner, arvore);
                    break;
                case 3:
                    buscarChave(scanner, arvore);
                    break;
                case 4:
                    imprimirArvore(arvore);
                    break;
                case 0:
                    encerrarPrograma(scanner);
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    /**
     * Exibe o menu principal de operações.
     * Apresenta todas as opções disponíveis para o usuário.
     */
    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Inserir chave");
        System.out.println("2 - Remover chave");
        System.out.println("3 - Buscar chave");
        System.out.println("4 - Imprimir árvore em ordem");
        System.out.println("0 - Sair");
    }

    /**
     * Realiza a operação de inserção de uma chave.
     * Solicita ao usuário a chave a ser inserida e executa a operação.
     * 
     * @param scanner Scanner para leitura da entrada do usuário.
     * @param arvore A árvore B onde a chave será inserida.
     */
    private static void inserirChave(Scanner scanner, ArvoreB<Integer> arvore) {
        System.out.print("Chave para inserir: ");
        int chaveInserir = scanner.nextInt();
        arvore.inserir(chaveInserir);
        System.out.println("Chave " + chaveInserir + " inserida com sucesso.");
    }

    /**
     * Realiza a operação de remoção de uma chave.
     * Solicita ao usuário a chave a ser removida e executa a operação.
     * 
     * @param scanner Scanner para leitura da entrada do usuário.
     * @param arvore A árvore B de onde a chave será removida.
     */
    private static void removerChave(Scanner scanner, ArvoreB<Integer> arvore) {
        System.out.print("Chave para remover: ");
        int chaveRemover = scanner.nextInt();
        Integer resultado = arvore.apagar(chaveRemover);
        if (resultado != null) {
            System.out.println("Chave " + chaveRemover + " removida com sucesso.");
        } else {
            System.out.println("Chave " + chaveRemover + " não encontrada na árvore.");
        }
    }

    /**
     * Realiza a operação de busca de uma chave.
     * Solicita ao usuário a chave a ser buscada e informa o resultado.
     * 
     * @param scanner Scanner para leitura da entrada do usuário.
     * @param arvore A árvore B onde a chave será buscada.
     */
    private static void buscarChave(Scanner scanner, ArvoreB<Integer> arvore) {
        System.out.print("Chave para buscar: ");
        int chaveBuscar = scanner.nextInt();
        NoArvoreB<Integer> resultado = arvore.buscar(chaveBuscar);
        if (resultado != null) {
            System.out.println("Chave " + chaveBuscar + " encontrada no nó com chaves: " + resultado.chaves);
        } else {
            System.out.println("Chave " + chaveBuscar + " não encontrada na árvore.");
        }
    }

    /**
     * Exibe todos os elementos da árvore em ordem crescente.
     * Utiliza o método imprimirEmOrdem() para obter a representação ordenada.
     * 
     * @param arvore A árvore B a ser exibida.
     */
    private static void imprimirArvore(ArvoreB<Integer> arvore) {
        System.out.println("Árvore em ordem:");
        String elementos = arvore.imprimirEmOrdem();
        if (elementos.trim().isEmpty()) {
            System.out.println("Árvore vazia.");
        } else {
            System.out.println(elementos);
        }
    }

    /**
     * Encerra o programa de forma adequada.
     * Fecha o scanner e exibe mensagem de despedida.
     * 
     * @param scanner Scanner a ser fechado.
     */
    private static void encerrarPrograma(Scanner scanner) {
        System.out.println("Encerrando programa...");
        scanner.close();
    }
}
