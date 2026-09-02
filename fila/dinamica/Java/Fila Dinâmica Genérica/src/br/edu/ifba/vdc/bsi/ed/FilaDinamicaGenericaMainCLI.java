// Acessar o diretório dos fontes:
// cd "fila/dinamica/Java/Fila Dinâmica Genérica/src"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.FilaDinamicaGenericaMainCLI
package br.edu.ifba.vdc.bsi.ed;

import java.util.NoSuchElementException;

/**
 * Classe que demonstra o uso da fila dinâmica genérica através de uma interface de linha de comando.
 * Esta classe implementa um menu interativo que permite ao usuário testar todas as operações
 * disponíveis na fila dinâmica.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class FilaDinamicaGenericaMainCLI {

  void main() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(7);
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
                fila.enfileirarFim(valor);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 2 -> {
                String confirmacao = IO.readln("Deseja desenfileirar[s/n]? ").trim().toLowerCase();
                if (confirmacao.equals("s")) {
                try {
                fila.desenfileirarInicio();
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
                }
            }
            case 3 -> {
                try {
                IO.println("Frente: " + fila.frente());
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 4 -> {
                String novoValorInicio = IO.readln("Digite o valor: ");
                try {
                fila.atualizarInicio(novoValorInicio);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 5 -> {
                String novoValorFim = IO.readln("Digite o valor: ");
                try {
                fila.atualizarFim(novoValorFim);
                } catch (NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 6 -> IO.println(fila.imprimirDeFrentePraTras());
            default -> IO.println("Opção inválida. Tente novamente.");
        }
      } catch (NumberFormatException e) {
        IO.println("Entrada inválida. Tente novamente.");
        opcao = -1; // Para não sair do loop
      }
    } while (opcao != 0);
  }

  public static void exibirMenu() {
    IO.println("=== FILA DINÂMICA GENÉRICA CLI ===");
    IO.println("0. SAIR");
    IO.println("1. Enfileirar");
    IO.println("2. Desenfileirar");
    IO.println("3. Frente");
    IO.println("4. Atualizar no Inicio");
    IO.println("5. Atualizar no Fim");
    IO.println("6. Imprimir");

  }
}
