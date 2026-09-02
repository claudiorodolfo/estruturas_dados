/**
 * Classe que demonstra o uso da lista dinâmica genérica através de uma interface de linha de comando.
 * Esta classe implementa um menu interativo que permite ao usuário testar todas as operações
 * disponíveis na lista dinâmica.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ListaDinamicaGenericaMainCLI {

	/** Lista dinâmica que será manipulada */
	private static ListaDinamicaGenerica<String> lista;

	/**
	 * Método principal que inicia o programa.
	 */
	void main() {
		lista = new ListaDinamicaGenerica<>(5);
		executar();
	}

	/**
	 * Executa o loop principal do programa, exibindo o menu e processando as opções do usuário.
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
		IO.println("\n=== MENU ===");
		IO.println("1) Anexar");
		IO.println("2) Inserir");
		IO.println("3) Selecionar");
		IO.println("4) Selecionar Todos");
		IO.println("5) Atualizar");
		IO.println("6) Apagar");
		IO.println("7) Imprimir");
		IO.println("8) Está Vazia?");
		IO.println("9) Está Cheia?");
		IO.println("0) Sair");
	}

	/**
	 * Lê a opção escolhida pelo usuário.
	 *
	 * @return a opção escolhida
	 */
	private static int lerOpcao() {
		try {
			return Integer.parseInt(IO.readln("Escolha uma opção: ").trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Processa a opção escolhida pelo usuário, executando a operação correspondente.
	 *
	 * @param opcao a opção escolhida pelo usuário
	 */
	private static void processarOpcao(int opcao) {
		switch (opcao) {
      case 1 -> anexar();
      case 2 -> inserir();
      case 3 -> selecionar();
      case 4 -> selecionarTodos();
      case 5 -> atualizar();
      case 6 -> apagar();
      case 7 -> imprimir();
      case 8 -> estaVazia();
      case 9 -> estaCheia();
      case 0 -> IO.println("Programa finalizado!");
      default -> IO.println("Opção inválida!");
  }
	}

	/**
	 * Adiciona um elemento ao final da lista.
	 * Solicita ao usuário o elemento a ser adicionado.
	 */
	private static void anexar() {
		String elemento = IO.readln("Digite o elemento a ser anexado: ");
		try {
			lista.anexar(elemento);
			IO.println("Elemento anexado com sucesso!");
		} catch (Exception e) {
			IO.println("Erro ao anexar: " + e.getMessage());
		}
	}

	/**
	 * Insere um elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição e o elemento a ser inserido.
	 */
	private static void inserir() {
		int posicao = Integer.parseInt(IO.readln("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ").trim());
		String elemento = IO.readln("Digite o elemento a ser inserido: ");
		try {
			lista.inserir(posicao, elemento);
			IO.println("Elemento inserido com sucesso!");
		} catch (Exception e) {
			IO.println("Erro ao inserir: " + e.getMessage());
		}
	}

	/**
	 * Seleciona e exibe o elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição do elemento.
	 */
	private static void selecionar() {
		int posicao = Integer.parseInt(IO.readln("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ").trim());
		try {
			String elemento = lista.selecionar(posicao);
			IO.println("Elemento na posição " + posicao + ": " + elemento);
		} catch (Exception e) {
			IO.println("Erro ao selecionar: " + e.getMessage());
		}
	}

	/**
	 * Exibe todos os elementos da lista.
	 */
	private static void selecionarTodos() {
		try {
			String[] elementos = lista.selecionarTodos();
			IO.println("Elementos da lista:");
			for (int i = 0; i < elementos.length; i++) {
				IO.println(i + ": " + elementos[i]);
			}
		} catch (Exception e) {
			IO.println("Erro ao selecionar todos: " + e.getMessage());
		}
	}

	/**
	 * Atualiza o elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição e o novo elemento.
	 */
	private static void atualizar() {
		int posicao = Integer.parseInt(IO.readln("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ").trim());
		String elemento = IO.readln("Digite o novo elemento: ");
		try {
			lista.atualizar(posicao, elemento);
			IO.println("Elemento atualizado com sucesso!");
		} catch (Exception e) {
			IO.println("Erro ao atualizar: " + e.getMessage());
		}
	}

	/**
	 * Remove e exibe o elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição do elemento a ser removido.
	 */
	private static void apagar() {
		int posicao = Integer.parseInt(IO.readln("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ").trim());
		try {
			String elemento = lista.apagar(posicao);
			IO.println("Elemento removido: " + elemento);
		} catch (Exception e) {
			IO.println("Erro ao apagar: " + e.getMessage());
		}
	}

	/**
	 * Exibe a representação em string da lista.
	 */
	private static void imprimir() {
		IO.println("Lista: " + lista.imprimir());
	}

	/**
	 * Verifica e exibe se a lista está vazia.
	 */
	private static void estaVazia() {
		IO.println("A lista está vazia? " + lista.estaVazia());
	}

	/**
	 * Verifica e exibe se a lista está cheia.
	 */
	private static void estaCheia() {
		IO.println("A lista está cheia? " + lista.estaCheia());
	}
}
