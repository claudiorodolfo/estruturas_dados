//Executar todos os comandos dentro da pasta "Pilha Dinâmica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.10.jar -d test test/PilhaDinamicaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.10.jar org.junit.runner.JUnitCore PilhaDinamicaTest
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class PilhaDinamicaTest {

  @Test
  public void testEmpilhar() {
    Empilhavel pilha = new PilhaDinamica(5);
    pilha.empilhar("Instituto");
    String conteudo = (String) pilha.espiar();
    assertEquals("Instituto", conteudo);
  }

  @Test
  public void testDesempilhar() {
    Empilhavel pilha = new PilhaDinamica(5);
    pilha.empilhar("Instituto");
    String conteudo = (String) pilha.desempilhar();
    assertEquals("Instituto", conteudo);
  }

  @Test
  public void testEspiar() {
    Empilhavel pilha = new PilhaDinamica(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    String conteudo = (String) pilha.espiar(); 
    assertEquals("Federal", conteudo);
  }

  @Test
  public void testAtualizar() {
    Empilhavel pilha = new PilhaDinamica(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    pilha.atualizar("Municipal");
    String conteudo = (String) pilha.espiar();
    assertEquals("Municipal", conteudo);
  }

  @Test
  public void testImprimir() {
    Empilhavel pilha = new PilhaDinamica(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    pilha.empilhar("de");
    String resultado = pilha.imprimir();
    assertTrue(resultado.contains("Instituto"));
    assertTrue(resultado.contains("Federal"));
    assertTrue(resultado.contains("de"));
  }

  @Test
  public void testPilhaCheiaEmpilhar() {
    Empilhavel pilha = new PilhaDinamica(1);
    pilha.empilhar("Instituto");
    try {
        pilha.empilhar("Federal");// deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um overflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Pilha Cheia!", e.getMessage());
	}
  }
  
  @Test
  public void testPilhaVaziaDesempilhar() {
    Empilhavel pilha = new PilhaDinamica(1);
    pilha.empilhar("Instituto");
    pilha.desempilhar();
    try {
        pilha.desempilhar(); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Pilha Vazia!", e.getMessage());
	}
  } 

  @Test
  public void testPilhaVaziaEspiar() {
    Empilhavel pilha = new PilhaDinamica(1);
    pilha.empilhar("Instituto");
    pilha.desempilhar();
    try {
        pilha.espiar(); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Pilha Vazia!", e.getMessage());
	}
  }

  @Test
  public void testPilhaVaziaAtualizar() {
    Empilhavel pilha = new PilhaDinamica(1);
    pilha.empilhar("Instituto");
    pilha.desempilhar();
    try {
        pilha.atualizar("Federal"); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Pilha Vazia!", e.getMessage());
	}
  }
}