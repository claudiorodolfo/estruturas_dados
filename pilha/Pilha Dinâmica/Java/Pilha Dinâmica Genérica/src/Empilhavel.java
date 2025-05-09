//Executar todos os comandos a partir da pasta "Pilha Dinâmica Genérica"

//Gerar documentação:
//javadoc -d doc src/*.java

//compilar um código-fonte específico: 
//javac src/Empilhavel.java -d bin
//ou
//compilar todos os códigos-fonte: 
//javac src/*.java -d bin
//executar o programa: 
//java -cp .;bin PilhaDinamicaGenericaMainCLI

//Para os testes unitários com junit:
//compilar classe de teste: 
//javac -cp .;bin;lib/junit-4.13.2.jar -d test test/PilhaDinamicaGenericaTest.java
//executar testes: 
//java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore PilhaDinamicaGenericaTest

//Gerar binário
//Gerar .jar
//jar cvfe build/Main.jar PilhaDinamicaGenericaMainCLI -C bin .
//Executar .jar
//java -jar build/Main.jar

//PS: para sistemas tipo Linux trocar ; ou : na separação dos itens do classpath
/**
 * Esta interface indica as operações que uma classe que deseja 
 * ter o comportamento de ser Empilhável precisa.
 * 
 * @author Oliveira, C. R. S.
 * @version 1.1
 * @since 2025-05-01
 */
public interface Empilhavel<T> {
	void empilhar(T dado);
	T desempilhar();
	T espiar();
	void atualizar(T dado);
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}