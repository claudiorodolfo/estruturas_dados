import java.util.Random;

public class MergesortEstaticoPrincipal {

    void main(){
        Random gerador = new Random();
        Integer numeros[] = new Integer[20];
		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < numeros.length; i++) {
			//numeros aleatórios entre [10..200] incluindo-os
            numeros[i] = gerador.nextInt(190) + 10;
		}
		
		Ordenavel sort = new MergesortEstatico(numeros);
		IO.println(sort.imprimir());
		sort.ordenar();
		IO.println(sort.imprimir());
	}
}