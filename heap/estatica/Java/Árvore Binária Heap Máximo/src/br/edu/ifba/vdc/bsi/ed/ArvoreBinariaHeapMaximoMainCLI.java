// Acessar o diretório dos fontes:
// cd "heap/estatica/Java/Árvore Binária Heap Máximo/src"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.ArvoreBinariaHeapMaximoMainCLI
package br.edu.ifba.vdc.bsi.ed;

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
    private static Amontoavel<Long> heap;
    
    /**
     * Método principal que inicia o programa.
     */
    void main() {
        heap = new ArvoreBinariaHeapMaximo<>(10);
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
        Long elemento = Long.parseLong(IO.readln("Digite o elemento a ser inserido: ").trim());
        try {
            heap.inserir(elemento);
            IO.println("Elemento inserido com sucesso!");
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
            Long elemento = heap.extrair();
            IO.println("Elemento extraído: " + elemento);
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
            Long elemento = heap.obterRaiz();
            IO.println("Elemento raiz: " + elemento);
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
