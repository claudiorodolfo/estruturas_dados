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
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");  
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testDesenfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    String conteudo = fila.desenfileirarInicio();
    assertEquals("Instituto", conteudo);
  }

  @Test
  public void testFrente() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testAtualizarInicio() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    fila.atualizarInicio("Universidade");
    assertEquals("Universidade", fila.frente());
  }

  @Test
  public void testAtualizarFim() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    fila.atualizarFim("Municipal");
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testImprimirDeFrentePraTras() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    fila.enfileirarFim("de");
    String resultado = fila.imprimirDeTrasPraFrente();
    assertTrue(resultado.contains("Instituto"));
    assertTrue(resultado.contains("Federal"));
    assertTrue(resultado.contains("de"));
  }

  @Test
  public void testFilaCheiaEnfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirarFim("Instituto");
    try {
        fila.enfileirarFim("Federal");// deve lançar exceção
		    //Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um overflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Cheia!", e.getMessage());
	  }
  }
  
  @Test
  public void testFilaVaziaDesenfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirarFim("Instituto");
    fila.desenfileirarInicio();
    try {
        fila.desenfileirarInicio(); // deve lançar exceção
		    //Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	  }
  } 

  @Test
  public void testFilaVaziaFrente() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirarFim("Instituto");
    fila.desenfileirarInicio();
    try {
        fila.frente(); // deve lançar exceção
		    //Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	  }
  }

  @Test
  public void testFilaVaziaAtualizarInicio() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirarFim("Instituto");
    fila.desenfileirarInicio();
    try {
        fila.atualizarInicio("Universidade"); // deve lançar exceção
		    //Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	  }
  }

  @Test
  public void testFilaVaziaAtualizarFim() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    fila.enfileirarFim("Instituto");
    fila.desenfileirarInicio();
    try {
        fila.atualizarFim("Universidade"); // deve lançar exceção
		    //Se chegou aqui, a exceção não foi lançada!
        fail("Deveria ter acontecido um underflow!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
	  }
  }
  
	@Test
	public void testOperacaoNaoSuportadaEnfileirarInicio() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    try {
      fila.enfileirarInicio();
		  //Se chegou aqui, a exceção não foi lançada!
      fail("Deveria ter lançado uma exceção!");
    } catch (NoSuchElementException e) {
      assertEquals("Operação não suportada!", e.getMessage());
	  }
	}

	@Test
	public void testOperacaoNaoSuportadaDesenfileirarFim() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    try {
      fila.desenfileirarFim();
		  //Se chegou aqui, a exceção não foi lançada!
      fail("Deveria ter lançado uma exceção!");
    } catch (NoSuchElementException e) {
      assertEquals("Operação não suportada!", e.getMessage());
	  }
	}

	@Test
	public void testOperacaoNaoSuportadaTras() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    try {
      fila.tras();
		  //Se chegou aqui, a exceção não foi lançada!
      fail("Deveria ter lançado uma exceção!");
    } catch (NoSuchElementException e) {
      assertEquals("Operação não suportada!", e.getMessage());
	  }
	}

	@Test
	public void testOperacaoNaoSuportadaImprimirDeTrasPraFrente() {
    Enfileiravel<String> fila = new FilaDinamicaGenerica<>(1);
    try {
      fila.imprimirDeTrasPraFrente();
		  //Se chegou aqui, a exceção não foi lançada!
      fail("Deveria ter lançado uma exceção!");
    } catch (NoSuchElementException e) {
      assertEquals("Operação não suportada!", e.getMessage());
	  }
	}
}