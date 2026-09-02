// Acessar o diretório dos fontes:
// cd "fila/dinamica/Java/Fila Dinâmica Dupla Terminacão Genérica/src"
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out FilaDinamicaDuplaTerminacaoGenericaMainCLI
import java.util.NoSuchElementException;

public class FilaDinamicaDuplaTerminacaoGenericaMainCLI {
  void main() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(7);
    int opcao;

    do {
      exibirMenu();
      try {
        opcao = Integer.parseInt(IO.readln("Escolha uma opção (0-6): ").trim());

        switch (opcao) {
            case 0 -> IO.println("Saindo da Fila. Até mais!");
            case 1 -> {
                String valor = IO.readln("Digite o valor: ");
                try {
                fila.enfileirarInicio(valor);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 2 -> {
                valor = IO.readln("Digite o valor: ");
                try {
                fila.enfileirarFim(valor);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 3 -> {
                String confirmacao = IO.readln("Deseja desenfileirar[s/n]? ").trim().toLowerCase();
                if (confirmacao.equals("s")) {
                try {
                fila.desenfileirarInicio();
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
                }
            }
            case 4 -> {
                confirmacao = IO.readln("Deseja desenfileirar[s/n]? ").trim().toLowerCase();
                if (confirmacao.equals("s")) {
                try {
                fila.desenfileirarFim();
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
                }
            }
            case 5 -> {
                try {
                IO.println("Frente: " + fila.frente());
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 6 -> {
                try {
                IO.println("Fundo: " + fila.tras());
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 7 -> {
                String novoValor = IO.readln("Digite o valor: ");
                try {
                fila.atualizarInicio(novoValor);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 8 -> {
                novoValor = IO.readln("Digite o valor: ");
                try {
                fila.atualizarFim(novoValor);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 9 -> IO.println(fila.imprimirDeFrentePraTras());
            case 10 -> IO.println(fila.imprimirDeTrasPraFrente());
            default -> IO.println("Opção inválida. Tente novamente.");
        }
      } catch (NumberFormatException e) {
        IO.println("Entrada inválida. Tente novamente.");
        opcao = -1; // Para não sair do loop
      }
    } while (opcao != 0);
  }

  public static void exibirMenu() {
    IO.println("=== FILA DUPLA DINÂMICA GENÉRICA CLI ===");
    IO.println("0. SAIR");
    IO.println("1. Enfileirar no Inicio");
    IO.println("2. Enfileirar no Fim");
    IO.println("3. Desenfileirar do Inicio");
    IO.println("4. Desenfileirar do Fim");
    IO.println("5. Frente da Fila");
    IO.println("6. Fundo da Fila");
    IO.println("7. Atualizar no Inicio");
    IO.println("8. Atualizar no Fim");
    IO.println("9. Imprimir de Frente para Tras");
    IO.println("10. Imprimir de Tras para Frente");
  }
}
