// Acessar o diretório dos fontes:
// cd ordenacao/estatica/Java/Bubblesort
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.BubblesortEstaticoPrincipal
package br.edu.ifba.vdc.bsi.ed;

import java.util.Random;

public class BubblesortEstaticoPrincipal {

	private Integer dados[] = new Integer[200];
    
	void main(){
		BubblesortEstaticoPrincipal principal = new BubblesortEstaticoPrincipal();
		
		principal.preencheDadosAleatorios();
		
		Ordenavel sort = new BubblesortEstatico(principal.dados);
		IO.println(principal.imprimir());
		long tempoInicioMili = System.currentTimeMillis();
		long tempoInicioNano = System.nanoTime();
		
		sort.ordenar();
		
		long tempoFimNano = System.nanoTime();
		long tempoFimMili = System.currentTimeMillis();		
		IO.println(principal.imprimir());
		IO.println("Tempo em ms:" + (tempoFimMili - tempoInicioMili));
		IO.println("Tempo em ns:" + (tempoFimNano - tempoInicioNano));		
	}
	
	public void preencheDadosAleatorios() {
	     Random gerador = new Random();
        
		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < dados.length; i++) {
			//numeros aleatórios no intervalo: [0, 400[
            dados[i] = gerador.nextInt(dados.length * 2);
		}
	}
	
	public String imprimir() {
		String resultado = "";
		for (int i = 0; i < dados.length; i++) {
			resultado += dados[i];
			if (i != dados.length - 1)
				resultado += ",";				
		}
		return "[" + resultado + "]";
	}
}