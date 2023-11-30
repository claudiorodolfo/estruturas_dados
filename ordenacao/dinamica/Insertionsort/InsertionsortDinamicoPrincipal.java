import java.util.Random;

public class InsertionsortDinamicoPrincipal {

    public static void main(String[] args){
        Random gerador = new Random();
		int tamanho = 20;
		InsertionsortDinamico sort = new InsertionsortDinamico();

		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < tamanho; i++) {
			//numeros aleatórios entre [10..200] incluindo-os
           Integer numero = gerador.nextInt(190) + 10;
		   sort.anexar(numero);
		}
		

		System.out.println(sort.imprimir());
		sort.ordenar();
		System.out.println(sort.imprimir());
	}
}