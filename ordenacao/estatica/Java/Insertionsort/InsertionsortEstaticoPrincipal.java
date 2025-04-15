import java.util.Random;

public class InsertionsortEstaticoPrincipal {

	private Integer dados[] = new Integer[20];
	
    public static void main(String[] args){
		InsertionsortEstaticoPrincipal principal = new InsertionsortEstaticoPrincipal();
		
		principal.preencheDadosAleatorios();     
		
		Ordenavel sort = new InsertionsortEstatico(principal.dados);
		System.out.println(principal.imprimir());
		sort.ordenar();
		System.out.println(principal.imprimir());
	}
	
	public void preencheDadosAleatorios() {
	     Random gerador = new Random();
        
		// Preenchendo o vetor com números aleatórios
        for (int i = 0; i < dados.length; i++) {
			//numeros aleatórios no intervalo: [10, 200[
            dados[i] = gerador.nextInt(190) + 10;
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