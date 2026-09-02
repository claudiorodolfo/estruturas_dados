// Acessar o diretório dos fontes:
// cd ordenacao/dinamica/Java/Bubblesort
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.BubblesortDinamicoPrincipal
package br.edu.ifba.vdc.bsi.ed;

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