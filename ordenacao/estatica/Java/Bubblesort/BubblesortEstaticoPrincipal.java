import java.util.Random;

public class BubblesortEstaticoPrincipal {

	private Integer dados[] = new Integer[200];
    
	public static void main(String[] args){
		BubblesortEstaticoPrincipal principal = new BubblesortEstaticoPrincipal();
		
		principal.preencheDadosAleatorios();
		
		Ordenavel sort = new BubblesortEstatico(principal.dados);
		System.out.println(principal.imprimir());
		long tempoInicioMili = System.currentTimeMillis();
		long tempoInicioNano = System.nanoTime();
		
		sort.ordenar();
		
		long tempoFimNano = System.nanoTime();
		long tempoFimMili = System.currentTimeMillis();		
		System.out.println(principal.imprimir());
		System.out.println("Tempo em ms:" + (tempoFimMili - tempoInicioMili));
		System.out.println("Tempo em ns:" + (tempoFimNano - tempoInicioNano));		
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