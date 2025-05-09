//Executar todos os comandos a partir da pasta "Fila Dinâmica Genérica"

//Para documentação:
//javadoc -d doc src/*.java

//compilar e executar o programa:
//compilar um código-fonte específico: 
//javac src/Enfileiravel.java -d bin
//ou
//compilar todos os arquivos-fontes: 
//javac src/*.java -d bin
//executar o programa: 
//java -cp .;bin FilaDinamicaGenericaMainCLI

//Para os testes unitários com junit:
//compilar classe de teste: 
//javac -cp .;bin;lib/junit-4.13.2.jar -d test test/FilaDinamicaGenericaTest.java
//executar testes: 
//java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FilaDinamicaGenericaTest

//Gerar binário
//Gerar .jar
//jar cvfe build/Main.jar FilaDinamicaGenericaMainCLI -C bin .
//Executar .jar
//java -jar build/Main.jar

//PS: para sistemas tipo Linux trocar ; ou : na separação dos itens do classpath


/**
 * Esta interface indica as operações que uma classe que deseja 
 * ter o comportamento de ser Enfileirável precisa.
 * 
 * @author Oliveira, C. R. S.
 * @version 1.1
 * @since 2025-05-06
 */
public interface Enfileiravel<T> {
	void enfileirar(T elemento);	//C
	T frente();						//R
	void atualizar(T elemento);		//U
	T desenfileirar();				//D

	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}