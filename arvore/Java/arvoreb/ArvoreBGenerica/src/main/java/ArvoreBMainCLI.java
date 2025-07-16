import java.util.Scanner;

public class ArvoreBMainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe a ordem da árvore B (t >= 2): ");
        int ordem = scanner.nextInt();

        if (ordem < 2) {
            System.out.println("Ordem inválida. Deve ser no mínimo 2.");
            return;
        }

        ArvoreB<Integer> arvore = new ArvoreB<>(ordem);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Inserir chave");
            System.out.println("2 - Remover chave");
            System.out.println("3 - Buscar chave");
            System.out.println("4 - Imprimir árvore em ordem");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Chave para inserir: ");
                    int chaveInserir = scanner.nextInt();
                    arvore.inserir(chaveInserir);
                    System.out.println("Chave inserida.");
                    break;
                case 2:
                    System.out.print("Chave para remover: ");
                    int chaveRemover = scanner.nextInt();
                    arvore.remover(chaveRemover);
                    System.out.println("Chave removida (se existente).");
                    break;
                case 3:
                    System.out.print("Chave para buscar: ");
                    int chaveBuscar = scanner.nextInt();
                    NoArvoreB<Integer> resultado = arvore.buscar(chaveBuscar);
                    if (resultado != null) {
                        System.out.println("Chave encontrada no nó com chaves: " + resultado.chaves);
                    } else {
                        System.out.println("Chave não encontrada.");
                    }
                    break;
                case 4:
                    System.out.println("Árvore em ordem:");
                    arvore.imprimirEmOrdem();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
