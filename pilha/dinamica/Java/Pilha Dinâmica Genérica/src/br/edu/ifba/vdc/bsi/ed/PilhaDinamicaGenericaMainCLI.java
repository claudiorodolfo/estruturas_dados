// Acessar o diretório dos fontes:
// cd "pilha/dinamica/Java/Pilha Dinâmica Genérica/src"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.PilhaDinamicaGenericaMainCLI
package br.edu.ifba.vdc.bsi.ed;

import java.util.NoSuchElementException;

/**
 * Classe principal que demonstra o uso da Pilha Dinâmica Genérica através de uma interface de linha de comando.
 * Esta classe implementa um menu interativo que permite ao usuário testar todas as operações
 * disponíveis na implementação da pilha.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-06-04
 */
public class PilhaDinamicaGenericaMainCLI {
  void main() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(7);
    int opcao;

    do {
      exibirMenu();
	  try {
        opcao = Integer.parseInt(IO.readln("Escolha uma opção (0-5): ").trim());

        switch (opcao) {
            case 0 -> IO.println("Saindo da Pilha. Até mais!");
            case 1 -> {
                String valor = IO.readln("Digite o valor: ");
                try {			
                pilha.empilhar(valor);
                } catch(NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 2 -> {
                String confirmacao = IO.readln("Deseja desempilhar[s/n]? ").trim().toLowerCase();
                if (confirmacao.equals("s")) {
                try {
                pilha.desempilhar();
                } catch(NoSuchElementException e) {
                IO.println(e.getMessage());
                }
                }
            }
            case 3 -> {
                try {
                IO.println("Topo: " + pilha.espiar());
                } catch(NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 4 -> {
                String novoValor = IO.readln("Digite o valor: ");
                try {
                pilha.atualizar(novoValor);
                } catch(NoSuchElementException e) {
                IO.println(e.getMessage());
                }
            }
            case 5 -> IO.println(pilha.imprimir());
            default -> IO.println("Opção inválida. Tente novamente.");
        }
      } catch (NumberFormatException e) {
        IO.println("Entrada inválida. Tente novamente.");
        opcao = -1; // Para não sair do loop
      }		
    } while (opcao != 0);
  }

  public static void exibirMenu() {
    IO.println("=== PILHA DINÂMICA GENÉRICA CLI ===");
    IO.println("0. SAIR");
    IO.println("1. Empilhar");
    IO.println("2. Desempilhar");
    IO.println("3. Espiar");
    IO.println("4. Atualizar");
    IO.println("5. Imprimir");
  }
}
