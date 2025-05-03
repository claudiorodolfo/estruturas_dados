//Executar todos os comandos dentro da pasta "Pilha Dinâmica"

//javadoc -d doc src/*.java

//compilar fonte: javac src/Empilhavel.java -d bin
//ou
//compilar fonte: javac src/*.java -d ../bin

//executar fonte: java -cp .;bin PilhaDinamicaMainCLI

/**
 * Esta interface indica as operações que uma classe que deseja 
 * ter o comportamento de ser Empilhável precisa.
 * 
 * @author Oliveira, C. R. S.
 * @version 1.1
 * @since 2025-05-01
 */
public interface Empilhavel {
	void empilhar(Object dado);
	Object desempilhar();
	Object espiar();
	void atualizar(Object dado);
	
	boolean estaCheia();
	boolean estaVazia();
	String imprimir();
}