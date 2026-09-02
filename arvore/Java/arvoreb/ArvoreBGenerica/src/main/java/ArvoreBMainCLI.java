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
 * @see PaginaArvoreB
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
     */
    void main() {
        // Solicita e valida a ordem da árvore
        int ordem = Integer.parseInt(IO.readln("Informe a ordem da árvore B (t >= 2): ").trim());

        if (ordem < 2) {
            IO.println("Ordem inválida. Deve ser no mínimo 2.");
            return;
        }

        // Cria a árvore B com a ordem especificada
        ArvoreB<Integer> arvore = new ArvoreB<>(ordem);
        IO.println("Árvore B criada com ordem " + ordem + ".");

        // Loop principal do menu
        while (true) {
            exibirMenu();
            int opcao = Integer.parseInt(IO.readln("Escolha: ").trim());

            switch (opcao) {
                case 1 -> inserirChave(arvore);
                case 2 -> removerChave(arvore);
                case 3 -> buscarChave(arvore);
                case 4 -> imprimirArvore(arvore);
                case 0 -> {
                    encerrarPrograma();
                    return;
                }
                default -> IO.println("Opção inválida.");
            }
        }
    }

    /**
     * Exibe o menu principal de operações.
     * Apresenta todas as opções disponíveis para o usuário.
     */
    private static void exibirMenu() {
        IO.println("\nMenu:");
        IO.println("1 - Inserir chave");
        IO.println("2 - Remover chave");
        IO.println("3 - Buscar chave");
        IO.println("4 - Imprimir árvore em ordem");
        IO.println("0 - Sair");
    }

    /**
     * Realiza a operação de inserção de uma chave.
     * Solicita ao usuário a chave a ser inserida e executa a operação.
     * 
     * @param arvore A árvore B onde a chave será inserida.
     */
    private static void inserirChave(ArvoreB<Integer> arvore) {
        int chaveInserir = Integer.parseInt(IO.readln("Chave para inserir: ").trim());
        arvore.inserir(chaveInserir);
        IO.println("Chave " + chaveInserir + " inserida com sucesso.");
    }

    /**
     * Realiza a operação de remoção de uma chave.
     * Solicita ao usuário a chave a ser removida e executa a operação.
     * 
     * @param arvore A árvore B de onde a chave será removida.
     */
    private static void removerChave(ArvoreB<Integer> arvore) {
        int chaveRemover = Integer.parseInt(IO.readln("Chave para remover: ").trim());
        Integer resultado = arvore.apagar(chaveRemover);
        if (resultado != null) {
            IO.println("Chave " + chaveRemover + " removida com sucesso.");
        } else {
            IO.println("Chave " + chaveRemover + " não encontrada na árvore.");
        }
    }

    /**
     * Realiza a operação de busca de uma chave.
     * Solicita ao usuário a chave a ser buscada e informa o resultado.
     * 
     * @param arvore A árvore B onde a chave será buscada.
     */
    private static void buscarChave(ArvoreB<Integer> arvore) {
        int chaveBuscar = Integer.parseInt(IO.readln("Chave para buscar: ").trim());
        PaginaArvoreB<Integer> resultado = arvore.buscar(chaveBuscar);
        if (resultado != null) {
            IO.println("Chave " + chaveBuscar + " encontrada no nó com chaves: " + resultado.chaves);
        } else {
            IO.println("Chave " + chaveBuscar + " não encontrada na árvore.");
        }
    }

    /**
     * Exibe todos os elementos da árvore em ordem crescente.
     * Utiliza o método imprimirEmOrdem() para obter a representação ordenada.
     * 
     * @param arvore A árvore B a ser exibida.
     */
    private static void imprimirArvore(ArvoreB<Integer> arvore) {
        IO.println("Árvore em ordem:");
        String elementos = arvore.imprimirEmOrdem();
        if (elementos.trim().isEmpty()) {
            IO.println("Árvore vazia.");
        } else {
            IO.println(elementos);
        }
    }

    /**
     * Encerra o programa de forma adequada.
     * Exibe mensagem de despedida.
     */
    private static void encerrarPrograma() {
        IO.println("Encerrando programa...");
    }
}
