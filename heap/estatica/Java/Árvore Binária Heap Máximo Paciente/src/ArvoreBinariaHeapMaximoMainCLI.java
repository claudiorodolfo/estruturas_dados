import java.util.NoSuchElementException;

/**
 * Classe que implementa uma interface de linha de comando (CLI) para manipular
 * uma árvore binária heap máximo. Permite ao usuário realizar todas as operações
 * disponíveis no heap através de um menu interativo.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ArvoreBinariaHeapMaximoMainCLI {
    
    /** Heap que será manipulada */
    private static Amontoavel<Paciente> heap;
    
    /**
     * Método principal que inicia o programa.
     */
    void main() {
        heap = new ArvoreBinariaHeapMaximo<>(Paciente.class, 10);
        executar();
    }
    
    /**
     * Executa o loop principal do programa, exibindo o menu e processando
     * as opções do usuário.
     */
    private static void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(IO.readln("Escolha uma opção: ").trim());
            processarOpcao(opcao);
        } while (opcao != 0);
    }
    
    /**
     * Exibe o menu de opções disponíveis para o usuário.
     */
    private static void exibirMenu() {
        IO.println("\n=== MENU HEAP MÁXIMO ===");
        IO.println("1) Inserir");
        IO.println("2) Extrair");
        IO.println("3) Obter Raiz");
        IO.println("4) Imprimir");
        IO.println("5) Está Vazia?");
        IO.println("6) Está Cheia?");
        IO.println("0) Sair");
    }
    
    /**
     * Processa a opção escolhida pelo usuário, executando a operação
     * correspondente no heap.
     * 
     * @param opcao a opção escolhida pelo usuário
     */
    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> inserir();
            case 2 -> extrair();
            case 3 -> obterRaiz();
            case 4 -> imprimir();
            case 5 -> estaVazia();
            case 6 -> estaCheia();
            case 0 -> IO.println("Programa finalizado!");
            default -> IO.println("Opção inválida!");
        }
    }
    
    /**
     * Insere um elemento no heap.
     * Solicita ao usuário o elemento a ser inserido.
     */
    private static void inserir() {
        String nome = IO.readln("Digite o nome do paciente: ").trim();
        int idade = Integer.parseInt(IO.readln("Digite a idade do paciente: ").trim());
        long prioridade = Long.parseLong(IO.readln("Digite a prioridade do paciente: ").trim());
        Paciente p = new Paciente(nome, idade, prioridade);
        try {
            heap.inserir(p);
            IO.println("Paciente inserido com sucesso!");
        } catch (OverflowException e) {
            IO.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Extrai o elemento máximo do heap.
     * Exibe o elemento extraído.
     */
    private static void extrair() {
        try {
            Paciente p = heap.extrair();
            IO.println("Paciente: " + p);
        } catch (UnderflowException e) {
            IO.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Obtém o elemento raiz do heap sem removê-lo.
     * Exibe o elemento raiz.
     */
    private static void obterRaiz() {
        try {
            Paciente p = heap.obterRaiz();
            IO.println("Paciente raiz: " + p);
        } catch (NoSuchElementException e) {
            IO.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Exibe a representação em string do heap.
     */
    private static void imprimir() {
        IO.println("Heap: " + heap.imprimir());
    }
    
    /**
     * Verifica e exibe se o heap está vazio.
     */
    private static void estaVazia() {
        IO.println("O heap está vazio? " + heap.estaVazia());
    }
    
    /**
     * Verifica e exibe se o heap está cheio.
     */
    private static void estaCheia() {
        IO.println("O heap está cheio? " + heap.estaCheia());
    }
}
