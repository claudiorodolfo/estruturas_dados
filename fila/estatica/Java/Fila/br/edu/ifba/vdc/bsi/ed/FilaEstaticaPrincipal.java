// Acessar o diretório dos fontes:
// cd fila/estatica/Java/Fila
// Compilar e enviar os .class para a pasta out:
// javac -d out br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out br.edu.ifba.vdc.bsi.ed.FilaEstaticaPrincipal
package br.edu.ifba.vdc.bsi.ed;

public class FilaEstaticaPrincipal {
	void main() {
		Enfileiravel fila = new FilaEstatica();
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