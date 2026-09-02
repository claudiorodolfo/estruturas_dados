import java.util.Random;

public class BubblesortDinamicoPrincipal {

    void main(){
        Random gerador = new Random();
		int tamanho = 20;
		BubblesortDinamico sort = new BubblesortDinamico();

		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < tamanho; i++) {
			//numeros aleatórios entre [10..200] incluindo-os
           Integer numero = gerador.nextInt(190) + 10;
		   sort.anexar(numero);
		}

		IO.println(sort.imprimir());
		sort.ordenar();
		IO.println(sort.imprimir());
	}
}