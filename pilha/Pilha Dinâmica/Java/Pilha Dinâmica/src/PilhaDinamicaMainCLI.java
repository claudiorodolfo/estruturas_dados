//Executar todos os comandos dentro da pasta "Pilha Dinâmica"
//compilar fonte: javac src/Empilhavel.java -d bin
//ou
//compilar fonte: javac src/*.java -d bin

//executar fonte: java -cp .;bin PilhaDinamicaMainCLI

import java.util.InputMismatchException;
import java.util.Scanner;

public class PilhaDinamicaMainCLI {
  public static void main(String[] args) {
    Empilhavel pilha = new PilhaDinamica(7);
    Scanner scanner = new Scanner(System.in);
    int opcao;

    do {
      exibirMenu();
      System.out.print("Escolha uma opção (0-5): ");
      try {
        opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o newline

        switch (opcao) {
          case 0:
            System.out.println("Saindo da Pilha. Até mais!");
            break;
          case 1:
            System.out.print("Digite o valor: ");
            Integer valor = scanner.nextInt();
            scanner.nextLine(); // Consumir o newline
            pilha.empilhar(valor);
            break;
          case 2:
            System.out.print("Deseja desempilhar[s/n]? ");
            String confirmacao = scanner.nextLine().trim().toLowerCase();
            if (confirmacao.equals("s")) {
              pilha.desempilhar();
            }
            break;
          case 3:
            System.out.println("Topo: " + pilha.espiar());
            break;
          case 4:
            System.out.print("Digite o valor: ");
            Integer novoValor = scanner.nextInt();
            scanner.nextLine(); // Consumir o newline
            pilha.atualizar(novoValor);
            break;
          case 5:
            System.out.println(pilha.imprimir());
            break;
          default:
            System.out.println("Opção inválida. Tente novamente.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Tente novamente.");
        scanner.nextLine(); // Consumir a entrada inválida
        opcao = -1; // Para não sair do loop
      }
    } while (opcao != 0);

    scanner.close();
  }

  public static void exibirMenu() {
    System.out.println("=== PILHA DINÂMICA CLI ===");
    System.out.println("0. SAIR");
    System.out.println("1. Empilhar");
    System.out.println("2. Desempilhar");
    System.out.println("3. Espiar");
    System.out.println("4. Atualizar");
    System.out.println("5. Imprimir");
  }
}