//Pra rodar
//cd "estruturas_dados\arvore\"
//javac abp/*.java
//java abp/ABPPrincipalCLI
package abp;

import java.util.Scanner;

public class ABPPrincipalCLI {
    public static void main(String args[]) {
        //Testar entrada nesta ordem: 4 2 6 1 3 5 7
        //Pre Ordem: [4,2,1,3,6,5,7] 
        //Em Ordem:  [1,2,3,4,5,6,7]
        //Pos Ordem: [1,3,2,5,7,6,4]
        Scanner scanner = new Scanner(System.in);
        int opcao, valor;
        Arborizavel<Integer> arvore = new ABP<>();
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo da ABP. Ate mais!");
                    break;                
                case 1:
                    System.out.print("Digite o valor : ");
                    valor = scanner.nextInt();
                    arvore.inserir(valor);
                    break;
                case 2:
                    System.out.print("Digite o valor : ");
                    valor = scanner.nextInt();                
                    arvore.apagar(valor);
                    break;
                case 3:
                    System.out.print("Digite o valor : ");
                    valor = scanner.nextInt();                
                    System.out.println(arvore.existe(valor));
                    break;
                case 4:
                    exibirMenuImprimir();
                    int opcaoImprimir = scanner.nextInt();
                    switch (opcaoImprimir) {
                        case 1:
                            System.out.println("");
                            System.out.println("Pre-Ordem: " + arvore.imprimirPreOrdem());
                            System.out.println("");                           
                        break;
                        case 2:
                            System.out.println("");
                            System.out.println("Em-Ordem: " + arvore.imprimirEmOrdem());
                            System.out.println("");                           
                        break;
                        case 3:
                            System.out.println("");                
                            System.out.println("Pos-Ordem: " + arvore.imprimirPosOrdem());
                            System.out.println("");                            
                        break;                                               
                        default:
                            System.out.println("Opcao invalida.");
                    }
                    break; 
                case 5:                    
                    arvore.limpar();
                    System.out.println("Arvore Limpa!");
                    break;
                    default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== Arvore Binaria de Pesquisa ===");
        System.out.println("0. SAIR");
        System.out.println("1. Inserir");
        System.out.println("2. Apagar");
        System.out.println("3. Existe?");         
        System.out.println("4. Imprimir");
        System.out.println("5. Limpar");         
        System.out.print("Escolha uma opcao (0-5): ");
    }   

    private static void exibirMenuImprimir() {
        System.out.println("1. Imprimir Pre-Ordem");
        System.out.println("2. Imprimir Em-Ordem");
        System.out.println("3. Imprimir Pos-Ordem");                
        System.out.print("Escolha uma opcao (1-3): ");
    }         
}