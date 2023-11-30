import java.util.Random;

public class BubblesortEstaticoPrincipal {

    public static void main(String[] args){
        Random gerador = new Random();
        Integer numeros[] = new Integer[200];
		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = gerador.nextInt(numeros.length*2);
		}
		
		Ordenavel sort = new BubblesortEstatico(numeros);
		System.out.println(sort.imprimir());
		long tempoInicioMili = System.currentTimeMillis();
		long tempoInicioNano = System.nanoTime();
		sort.ordenar();
		long tempoFimNano = System.nanoTime();
		long tempoFimMili = System.currentTimeMillis();		
		System.out.println(sort.imprimir());
		System.out.println("Tempo em ms:" + (tempoFimMili - tempoInicioMili));
		System.out.println("Tempo em ns:" + (tempoFimNano - tempoInicioNano));		
	}
}