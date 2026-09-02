// Acessar o diretório dos fontes:
// cd ordenacao/estatica/Java/Quicksort
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.QuicksortEstaticoPrincipal
package br.edu.ifba.vdc.bsi.ed;

import java.util.Random;

public class QuicksortEstaticoPrincipal {

    void main(){
        Random gerador = new Random();
        Integer numeros[] = new Integer[20];
		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < numeros.length; i++) {
			//numeros aleatórios entre [10..200] incluindo-os
            numeros[i] = gerador.nextInt(190) + 10;
		}
		
		Ordenavel sort = new QuicksortEstatico(numeros);
		IO.println(sort.imprimir());
		sort.ordenar();
		IO.println(sort.imprimir());
	}
}