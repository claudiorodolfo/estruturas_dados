// Acessar o diretório dos fontes:
// cd "fila/dinamica/Java/Fila Dinâmica Dupla Terminacão Genérica/src"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.FilaDinamicaDuplaTerminacaoGenericaMainCLI
package br.edu.ifba.vdc.bsi.ed;

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
    IO.println("""
      === FILA DUPLA DINÂMICA GENÉRICA CLI ===
      0. SAIR
      1. Enfileirar no Inicio
      2. Enfileirar no Fim
      3. Desenfileirar do Inicio
      4. Desenfileirar do Fim
      5. Frente da Fila
      6. Fundo da Fila
      7. Atualizar no Inicio
      8. Atualizar no Fim
      9. Imprimir de Frente para Tras
      10. Imprimir de Tras para Frente
      """);
  }
}
