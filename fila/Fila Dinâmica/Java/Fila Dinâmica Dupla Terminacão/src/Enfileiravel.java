//Executar todos os comandos a partir da pasta "Fila Dinâmica Genérica"

//Gerar documentação:
//javadoc -d doc src/*.java

//compilar um código-fonte específico: 
//javac src/Enfileiravel.java -d bin
//ou
//compilar todos os códigos-fonte: 
//javac src/*.java -d bin
//executar o programa: 
//java -cp .;bin FilaDinamicaDuplaTerminacaoGenericaMainCLI

//Para os testes unitários com junit:
//compilar classe de teste: 
//javac -cp .;bin;lib/junit-4.13.2.jar -d test test/FilaDinamicaDuplaTerminacaoGenericaTest.java
//executar testes: 
//java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FilaDinamicaDuplaTerminacaoGenericaTest

//Gerar binário
//Gerar .jar
//jar cvfe build/Main.jar FilaDinamicaDuplaTerminacaoGenericaMainCLI -C bin .
//Executar .jar
//java -jar build/Main.jar

//PS: para sistemas tipo Linux trocar ; ou : na separação dos itens do classpath
/**
 * Esta interface indica as operações que uma classe que deseja 
 * ter o comportamento de ser Enfileirável precisa.
 * 
 * @author Oliveira, C. R. S.
 * @version 1.1
 * @since 2025-05-09
 */
public interface Enfileiravel<T> {
	void enfileirarInicio(T dado);
	void enfileirarFim(T dado);
	
	T frente();
	T tras();
	
	void atualizarInicio(T dado);
	void atualizarFim(T dado);

	T desenfileirarInicio();
	T desenfileirarFim();

	boolean estaCheia();	
	boolean estaVazia();
	String imprimirDeTrasPraFrente();
	String imprimirDeFrentePraTras();	
}