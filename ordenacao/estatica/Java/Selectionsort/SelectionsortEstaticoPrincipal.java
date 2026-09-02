// Acessar o diretório dos fontes:
// cd ordenacao/estatica/Java/Selectionsort
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out SelectionsortEstaticoPrincipal
import java.util.Random;

public class SelectionsortEstaticoPrincipal {

	private Integer dados[] = new Integer[20];
	
    void main(){
		SelectionsortEstaticoPrincipal principal = new SelectionsortEstaticoPrincipal();
		
		principal.preencheDadosAleatorios();     
		
		Ordenavel sort = new SelectionsortEstatico(principal.dados);
		IO.println(principal.imprimir());
		sort.ordenar();
		IO.println(principal.imprimir());
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