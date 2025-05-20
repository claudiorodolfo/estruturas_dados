//Executar todos os comandos dentro da pasta "Fila Dinâmica Genérica"
//compilar fonte: javac src/Enfileiravel.java -d bin
//ou
//compilar fonte: javac src/*.java -d bin

//executar fonte: java -cp .;bin FilaDinamicaDuplaTerminacaoGenericaMainCLI

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FilaDinamicaDuplaTerminacaoGenericaMainCLI {
  public static void main(String[] args) {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(7);
    Scanner scanner = new Scanner(System.in);
    int opcao;

    do {
      exibirMenu();
      System.out.print("Escolha uma opção (0-6): ");
      try {
        opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o newline

        switch (opcao) {
          case 0:
            System.out.println("Saindo da Fila. Até mais!");
            break;
          case 1:
            System.out.print("Digite o valor: ");
            String valor = scanner.nextLine();
            try {
              fila.enfileirarInicio(valor);
            } catch (NoSuchElementException e) {
              System.err.println(e.getMessage());
            }
            break;
          case 2:
            System.out.print("Digite o valor: ");
            valor = scanner.nextLine();
            try {
              fila.enfileirarFim(valor);
            } catch (NoSuchElementException e) {
              System.err.println(e.getMessage());
            }
            break;
          case 3:
            System.out.print("Deseja desenfileirar[s/n]? ");
            String confirmacao = scanner.nextLine().trim().toLowerCase();
            if (confirmacao.equals("s")) {
              try {
                fila.desenfileirarInicio();
              } catch (NoSuchElementException e) {
                System.err.println(e.getMessage());
              }
            }
            break;
          case 4:
            System.out.print("Deseja desenfileirar[s/n]? ");
            confirmacao = scanner.nextLine().trim().toLowerCase();
            if (confirmacao.equals("s")) {
              try {
                fila.desenfileirarFim();
              } catch (NoSuchElementException e) {
                System.err.println(e.getMessage());
              }
            }
            break;
          case 5:
            try {
              System.out.println("Frente: " + fila.frente());
            } catch (NoSuchElementException e) {
              System.err.println(e.getMessage());
            }
            break;
            case 6:
            try {
              System.out.println("Fundo: " + fila.tras());
            } catch (NoSuchElementException e) {
              System.err.println(e.getMessage());
            }
            break;
          case 7:
            System.out.print("Digite o valor: ");
            String novoValor = scanner.nextLine();
            try {
              fila.atualizarInicio(novoValor);
            } catch (NoSuchElementException e) {
              System.err.println(e.getMessage());
            }
            break;
            case 8:
            System.out.print("Digite o valor: ");
            novoValor = scanner.nextLine();
            try {
              fila.atualizarFim(novoValor);
            } catch (NoSuchElementException e) {
              System.err.println(e.getMessage());
            }
            break;
          case 9:
            System.out.println(fila.imprimirDeFrentePraTras());
            break;
            case 10:
            System.out.println(fila.imprimirDeTrasPraFrente());
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
    System.out.println("=== FILA DUPLA DINÂMICA GENÉRICA CLI ===");
    System.out.println("0. SAIR");
    System.out.println("1. Enfileirar no Inicio");
    System.out.println("2. Enfileirar no Fim");
    System.out.println("3. Desenfileirar do Inicio");
    System.out.println("4. Desenfileirar do Fim");
    System.out.println("5. Frente da Fila");
    System.out.println("6. Fundo da Fila");
    System.out.println("7. Atualizar no Inicio");
    System.out.println("8. Atualizar no Fim");
    System.out.println("9. Imprimir de Frente para Tras");
    System.out.println("10. Imprimir de Tras para Frente");
  }
}