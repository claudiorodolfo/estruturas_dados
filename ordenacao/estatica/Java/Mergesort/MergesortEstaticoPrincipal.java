import java.util.Random;

public class MergesortEstaticoPrincipal {

    public static void main(String[] args){
        Random gerador = new Random();
        Integer numeros[] = new Integer[20];
		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < numeros.length; i++) {
			//numeros aleatórios entre [10..200] incluindo-os
            numeros[i] = gerador.nextInt(190) + 10;
		}
		
		Ordenavel sort = new MergesortEstatico(numeros);
		System.out.println(sort.imprimir());
		sort.ordenar();
		System.out.println(sort.imprimir());
	}
}