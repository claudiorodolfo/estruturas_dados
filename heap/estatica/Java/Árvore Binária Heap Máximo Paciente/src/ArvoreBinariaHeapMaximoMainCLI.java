import java.util.Scanner;
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
    
    /** Scanner para leitura de entrada do usuário */
    private static Scanner scanner;
    
    /** Heap que será manipulada */
    private static Amontoavel<Paciente> heap;
    
    /**
     * Método principal que inicia o programa.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
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
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }
    
    /**
     * Exibe o menu de opções disponíveis para o usuário.
     */
    private static void exibirMenu() {
        System.out.println("\n=== MENU HEAP MÁXIMO ===");
        System.out.println("1) Inserir");
        System.out.println("2) Extrair");
        System.out.println("3) Obter Raiz");
        System.out.println("4) Imprimir");
        System.out.println("5) Está Vazia?");
        System.out.println("6) Está Cheia?");
        System.out.println("0) Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Lê a opção escolhida pelo usuário.
     * 
     * @return a opção escolhida
     */
    private static int lerOpcao() {
        return scanner.nextInt();
    }
    
    /**
     * Processa a opção escolhida pelo usuário, executando a operação
     * correspondente no heap.
     * 
     * @param opcao a opção escolhida pelo usuário
     */
    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                inserir();
                break;
            case 2:
                extrair();
                break;
            case 3:
                obterRaiz();
                break;
            case 4:
                imprimir();
                break;
            case 5:
                estaVazia();
                break;
            case 6:
                estaCheia();
                break;
            case 0:
                System.out.println("Programa finalizado!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    /**
     * Insere um elemento no heap.
     * Solicita ao usuário o elemento a ser inserido.
     */
    private static void inserir() {
        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.next();
        System.out.print("Digite a idade do paciente: ");
        int idade = scanner.nextInt();
        System.out.print("Digite a prioridade do paciente: ");
        long prioridade = scanner.nextLong();
        Paciente p = new Paciente(nome, idade, prioridade);
        try {
            heap.inserir(p);
            System.out.println("Paciente inserido com sucesso!");
        } catch (OverflowException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Extrai o elemento máximo do heap.
     * Exibe o elemento extraído.
     */
    private static void extrair() {
        try {
            Paciente p = heap.extrair();
            System.out.println("Paciente: " + p);
        } catch (UnderflowException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Obtém o elemento raiz do heap sem removê-lo.
     * Exibe o elemento raiz.
     */
    private static void obterRaiz() {
        try {
            Paciente p = heap.obterRaiz();
            System.out.println("Paciente raiz: " + p);
        } catch (NoSuchElementException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Exibe a representação em string do heap.
     */
    private static void imprimir() {
        System.out.println("Heap: " + heap.imprimir());
    }
    
    /**
     * Verifica e exibe se o heap está vazio.
     */
    private static void estaVazia() {
        System.out.println("O heap está vazio? " + heap.estaVazia());
    }
    
    /**
     * Verifica e exibe se o heap está cheio.
     */
    private static void estaCheia() {
        System.out.println("O heap está cheio? " + heap.estaCheia());
    }
} 