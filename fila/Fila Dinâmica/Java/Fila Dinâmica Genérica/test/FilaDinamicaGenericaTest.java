//Executar todos os comandos dentro da pasta "Fila Dinâmica Genérica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.13.2.jar -d test test/FilaDinamicaGenericaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FilaDinamicaGenericaTest
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilaDinamicaGenericaTest {

  @Test
  public void testEnfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirar("Instituto");
    fila.enfileirar("Federal");
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testDesenfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirar("Instituto");
    fila.enfileirar("Federal");    
    String conteudo = fila.desenfileirar();
    assertEquals("Instituto", conteudo);
  }

  @Test
  public void testFrente() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirar("Instituto");
    fila.enfileirar("Federal");
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testAtualizar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirar("Instituto");
    fila.enfileirar("Federal");
    fila.atualizar("Universidade");
    assertEquals("Universidade", fila.frente());
  }

  @Test
  public void testImprimir() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirar("Instituto");
    fila.enfileirar("Federal");
    fila.enfileirar("de");
    String resultado = fila.imprimir();
    assertTrue(resultado.contains("Instituto"));
    assertTrue(resultado.contains("Federal"));
    assertTrue(resultado.contains("de"));
  }

  @Test
  public void testFilaCheiaEnfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirar("Instituto");
    try {
        fila.enfileirar("Federal");// deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um overflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Cheia!", e.getMessage());
	}
  }
  
  @Test
  public void testFilaVaziaDesenfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirar("Instituto");
    fila.desenfileirar();
    try {
        fila.desenfileirar(); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	}
  } 

  @Test
  public void testFilaVaziaFrente() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirar("Instituto");
    fila.desenfileirar();
    try {
        fila.frente(); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	}
  }

  @Test
  public void testFilaVaziaAtualizar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirar("Instituto");
    fila.desenfileirar();
    try {
        fila.atualizar("Federal"); // deve lançar exceção
		//Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	}
  }
}