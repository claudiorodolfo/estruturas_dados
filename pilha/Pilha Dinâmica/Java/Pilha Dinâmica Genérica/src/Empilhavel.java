//Executar todos os comandos dentro da pasta "Pilha Dinâmica Genérica"

//javadoc -d doc src/*.java

//compilar fonte: javac src/Empilhavel.java -d bin
//ou
//compilar fonte: javac src/*.java -d bin

//executar fonte: java -cp .;bin PilhaDinamicaGenericaMainCLI

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