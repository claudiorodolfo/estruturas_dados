// Acessar o diretório dos fontes:
// cd "fila/estatica/Java/Fila Dupla Terminação"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin -sourcepath ".:../Fila Circular" br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.FilaEstaticaDuplaTerminacaoPrincipal
package br.edu.ifba.vdc.bsi.ed;

public class FilaEstaticaDuplaTerminacaoPrincipal {
	void main() {
		DuplamenteEnfileiravel fila = new FilaEstaticaDuplaTerminacao();
		fila.enfileirarInicio("A");
		IO.println(fila.imprimir()); //[A]
		IO.println(fila.tras()); //A
		IO.println(fila.frente()); //A	
		fila.enfileirarInicio("B");
		fila.desenfileirarFim();
		fila.enfileirar("C");
		fila.atualizarInicio("Y");
		fila.desenfileirar();
		fila.enfileirarInicio("D"); 
		IO.println(fila.imprimirDeTrasPraFrente()); //[C,D]
		fila.frente(); 
		fila.desenfileirarFim();
		IO.println(fila.tras()); //D
		fila.enfileirarInicio("E");
		fila.atualizarFim("R");
		IO.println(fila.imprimirDeTrasPraFrente());//[R,E]
		fila.enfileirar("F");
		fila.desenfileirar();
		fila.enfileirar("G");
		IO.println(fila.tras()); //G
		fila.enfileirarInicio("H");
		fila.desenfileirarFim();
		fila.enfileirar("I");
		IO.println(fila.imprimir()); //[H,R,F,I]
		fila.enfileirarInicio("J");
		fila.enfileirar("K");
		fila.enfileirar("L");
		IO.println(fila.frente()); //J
		fila.desenfileirar();
		IO.println(fila.imprimir()); //[H,R,F,I,K,L]
	}
}
