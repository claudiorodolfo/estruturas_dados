//Executar todos os comandos dentro da pasta "Pilha Dinâmica Genérica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.13.2.jar -d test test/PilhaDinamicaGenericaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore PilhaDinamicaGenericaTest
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class PilhaDinamicaGenericaTest {

  @Test
  public void testEmpilhar() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");  
    assertEquals("Federal", pilha.espiar());
  }

  @Test
  public void testDesempilhar() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    String conteudo = pilha.desempilhar();
    assertEquals("Federal", conteudo);
  }

  @Test
  public void testEspiar() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    assertEquals("Federal", pilha.espiar());
  }

  @Test
  public void testAtualizar() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    pilha.atualizar("Municipal");
    assertEquals("Municipal", pilha.espiar());
  }

  @Test
  public void testImprimir() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
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
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(1);
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
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(1);
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
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(1);
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
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(1);
    pilha.empilhar("Instituto");
    pilha.desempilhar();
    try {
        pilha.atualizar("Universidade"); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Pilha Vazia!", e.getMessage());
	}
  }
}