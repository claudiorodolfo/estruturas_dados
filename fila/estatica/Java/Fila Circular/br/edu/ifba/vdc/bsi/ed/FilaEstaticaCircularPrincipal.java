// Acessar o diretório dos fontes:
// cd "fila/estatica/Java/Fila Circular"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.FilaEstaticaCircularPrincipal
package br.edu.ifba.vdc.bsi.ed;

public class FilaEstaticaCircularPrincipal {
	void main() {
		Enfileiravel fila = new FilaEstaticaCircular();
		fila.enfileirar("A");
		IO.println(fila.frente());		
		fila.enfileirar("B");
		fila.enfileirar("C");
		fila.atualizarInicio("H");
		fila.enfileirar("D");
		fila.desenfileirar();
		fila.enfileirar("E");
		Object conteudo = fila.desenfileirar();
		fila.enfileirar("F");
		fila.atualizarFim("J");
		IO.println(fila.frente());
		IO.println(fila.frente());		
		fila.enfileirar("G");
		fila.enfileirar(conteudo);
		fila.enfileirar("I");
		fila.atualizarInicio("K");
		fila.enfileirar("J");
		fila.enfileirar("K");					
		IO.println("Fila: " + fila.imprimir());
	}
}