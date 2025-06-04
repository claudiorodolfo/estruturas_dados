import java.util.Scanner;

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

	/** Scanner para leitura de entrada do usuário */
	private static Scanner scanner;
	
	/** Lista dinâmica que será manipulada */
	private static ListaDinamicaGenerica<String> lista;

	/**
	 * Método principal que inicia o programa.
	 *
	 * @param args argumentos da linha de comando (não utilizados)
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
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
		System.out.println("\n=== MENU ===");
		System.out.println("1) Anexar");
		System.out.println("2) Inserir");
		System.out.println("3) Selecionar");
		System.out.println("4) Selecionar Todos");
		System.out.println("5) Atualizar");
		System.out.println("6) Apagar");
		System.out.println("7) Imprimir");
		System.out.println("8) Está Vazia?");
		System.out.println("9) Está Cheia?");
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
	 * Processa a opção escolhida pelo usuário, executando a operação correspondente.
	 *
	 * @param opcao a opção escolhida pelo usuário
	 */
	private static void processarOpcao(int opcao) {
		switch (opcao) {
			case 1:
				anexar();
				break;
			case 2:
				inserir();
				break;
			case 3:
				selecionar();
				break;
			case 4:
				selecionarTodos();
				break;
			case 5:
				atualizar();
				break;
			case 6:
				apagar();
				break;
			case 7:
				imprimir();
				break;
			case 8:
				estaVazia();
				break;
			case 9:
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
	 * Adiciona um elemento ao final da lista.
	 * Solicita ao usuário o elemento a ser adicionado.
	 */
	private static void anexar() {
		System.out.print("Digite o elemento a ser anexado: ");
		scanner.nextLine(); // Limpa o buffer
		String elemento = scanner.nextLine();
		try {
			lista.anexar(elemento);
			System.out.println("Elemento anexado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao anexar: " + e.getMessage());
		}
	}

	/**
	 * Insere um elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição e o elemento a ser inserido.
	 */
	private static void inserir() {
		System.out.print("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ");
		int posicao = scanner.nextInt();
		System.out.print("Digite o elemento a ser inserido: ");
		scanner.nextLine(); // Limpa o buffer
		String elemento = scanner.nextLine();
		try {
			lista.inserir(posicao, elemento);
			System.out.println("Elemento inserido com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir: " + e.getMessage());
		}
	}

	/**
	 * Seleciona e exibe o elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição do elemento.
	 */
	private static void selecionar() {
		System.out.print("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ");
		int posicao = scanner.nextInt();
		try {
			String elemento = lista.selecionar(posicao);
			System.out.println("Elemento na posição " + posicao + ": " + elemento);
		} catch (Exception e) {
			System.out.println("Erro ao selecionar: " + e.getMessage());
		}
	}

	/**
	 * Exibe todos os elementos da lista.
	 */
	private static void selecionarTodos() {
		try {
			String[] elementos = lista.selecionarTodos();
			System.out.println("Elementos da lista:");
			for (int i = 0; i < elementos.length; i++) {
				System.out.println(i + ": " + elementos[i]);
			}
		} catch (Exception e) {
			System.out.println("Erro ao selecionar todos: " + e.getMessage());
		}
	}

	/**
	 * Atualiza o elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição e o novo elemento.
	 */
	private static void atualizar() {
		System.out.print("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ");
		int posicao = scanner.nextInt();
		System.out.print("Digite o novo elemento: ");
		scanner.nextLine(); // Limpa o buffer
		String elemento = scanner.nextLine();
		try {
			lista.atualizar(posicao, elemento);
			System.out.println("Elemento atualizado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar: " + e.getMessage());
		}
	}

	/**
	 * Remove e exibe o elemento em uma posição específica da lista.
	 * Solicita ao usuário a posição do elemento a ser removido.
	 */
	private static void apagar() {
		System.out.print("Digite a posição (0-" + (lista.estaVazia() ? "0" : (lista.selecionarTodos().length - 1)) + "): ");
		int posicao = scanner.nextInt();
		try {
			String elemento = lista.apagar(posicao);
			System.out.println("Elemento removido: " + elemento);
		} catch (Exception e) {
			System.out.println("Erro ao apagar: " + e.getMessage());
		}
	}

	/**
	 * Exibe a representação em string da lista.
	 */
	private static void imprimir() {
		System.out.println("Lista: " + lista.imprimir());
	}

	/**
	 * Verifica e exibe se a lista está vazia.
	 */
	private static void estaVazia() {
		System.out.println("A lista está vazia? " + lista.estaVazia());
	}

	/**
	 * Verifica e exibe se a lista está cheia.
	 */
	private static void estaCheia() {
		System.out.println("A lista está cheia? " + lista.estaCheia());
	}
} 