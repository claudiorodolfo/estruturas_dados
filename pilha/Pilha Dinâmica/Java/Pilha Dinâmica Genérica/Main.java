import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Empilhavel<String> pilha = new PilhaDinamica(7);
    Scanner scanner = new Scanner(System.in);
    int opcao;

    do {
      exibirMenu();
      System.out.print("Escolha uma opção (0-5): ");
      opcao = scanner.nextInt();
      scanner.nextLine(); // Consumir o newline

      switch (opcao) {
        case 0:
          System.out.println("Saindo da Pilha. Até mais!");
          break;
        case 1:
          System.out.print("Digite o valor: ");
          String valor = scanner.nextLine();
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
          String novoValor = scanner.nextLine();
          pilha.atualizar(novoValor);
          break;
        case 5:
          System.out.println(pilha.imprimir());
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
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