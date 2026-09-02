// Acessar o diretório dos fontes:
// cd ordenacao/dinamica/Java/Selectionsort
// Compilar e enviar os .class para a pasta out:
// javac -d out br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out br.edu.ifba.vdc.bsi.ed.SelectionsortDinamicoPrincipal
package br.edu.ifba.vdc.bsi.ed;

import java.util.Random;

public class SelectionsortDinamicoPrincipal {

    void main(){
        Random gerador = new Random();
		int tamanho = 20;
		SelectionsortDinamico sort = new SelectionsortDinamico();

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