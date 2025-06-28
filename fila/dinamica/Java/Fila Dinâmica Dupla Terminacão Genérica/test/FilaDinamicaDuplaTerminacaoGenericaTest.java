//Executar todos os comandos dentro da pasta "Fila Dinâmica Genérica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.13.2.jar -d test test/FilaDinamicaDuplaTerminacaoGenericaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FilaDinamicaDuplaTerminacaoGenericaTest
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilaDinamicaDuplaTerminacaoGenericaTest {


  @Test
  public void testFilaComDiferentesTipos() {
    Enfileiravel<Integer> filaInt = new FilaDinamicaDuplaTerminacaoGenerica<>(2);
    filaInt.enfileirarFim(1);
    filaInt.enfileirarFim(2);
    assertEquals(Integer.valueOf(1), filaInt.frente());
    
    Enfileiravel<Double> filaDouble = new FilaDinamicaDuplaTerminacaoGenerica<>(2);
    filaDouble.enfileirarFim(1.5);
    filaDouble.enfileirarFim(2.5);
    assertEquals(Double.valueOf(1.5), filaDouble.frente());
  }

  @Test
  public void testConstrutorPadrao() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>();
    // Deve permitir 10 elementos
    for (int i = 0; i < 10; i++) {
        fila.enfileirarFim("Elemento" + i);
    }
    assertTrue(fila.estaCheia());
    try {
        fila.enfileirarFim("Extra");
        fail("Deveria ter lançado exceção de fila cheia!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Cheia!", e.getMessage());
    }
  }

  @Test
  public void testConstrutorComTamanhoPersonalizado() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(5);
    // Deve permitir 5 elementos
    for (int i = 0; i < 5; i++) {
        fila.enfileirarFim("Elemento" + i);
    }
    assertTrue(fila.estaCheia());
    try {
        fila.enfileirarFim("Extra");
        fail("Deveria ter lançado exceção de fila cheia!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Cheia!", e.getMessage());
    }
  }

  @Test
  public void testEnfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarFim("Instituto");  
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testEnfileirarMultiplosElementos() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
      fila.enfileirarFim("A");
      fila.enfileirarFim("B");
      fila.enfileirarFim("C");
      assertEquals("A", fila.frente());
      assertEquals("[A,B,C]", fila.imprimirDeFrentePraTras());
  }

  @Test
  public void testDesenfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarFim("Instituto");
    String conteudo = fila.desenfileirarInicio();
    assertEquals("Instituto", conteudo);
  }

  @Test
  public void testDesenfileirarMultiplosElementos() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("A");
    fila.enfileirarFim("B");
    fila.enfileirarFim("C");
    assertEquals("A", fila.desenfileirarInicio());
    assertEquals("B", fila.desenfileirarInicio());
    assertEquals("C", fila.frente());
  }

  @Test
  public void testFrente() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    assertEquals("Instituto", fila.frente());
  }

  @Test
  public void testAtualizarInicio() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarFim("Instituto");
    fila.atualizarInicio("Universidade");
    assertEquals("Universidade", fila.frente());
  }

  @Test
  public void testAtualizarInicioComMultiplosElementos() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("Primeiro");
    fila.enfileirarFim("Segundo");
    fila.enfileirarFim("Terceiro");
    fila.atualizarInicio("Novo");
    assertEquals("Novo", fila.frente());
    fila.desenfileirarInicio();
    assertEquals("Segundo", fila.frente());
  }

  @Test
  public void testAtualizarFim() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarFim("Instituto");
    fila.atualizarInicio("Universidade");
    assertEquals("Universidade", fila.frente());
  }

  @Test
  public void testAtualizarFimComMultiplosElementos() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("Primeiro");
    fila.enfileirarFim("Segundo");
    fila.enfileirarFim("Terceiro");
    fila.atualizarFim("Novo");
    assertEquals("Primeiro", fila.frente());
    fila.desenfileirarInicio();
    fila.desenfileirarInicio();
    assertEquals("Novo", fila.frente());
  }

  @Test
  public void testImprimirDeFrentePraTras() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(5);
    fila.enfileirarFim("Instituto");
    fila.enfileirarFim("Federal");
    fila.enfileirarFim("de");
    String resultado = fila.imprimirDeFrentePraTras();
    assertTrue(resultado.contains("Instituto"));
    assertTrue(resultado.contains("Federal"));
    assertTrue(resultado.contains("de"));
  }

  @Test
  public void testImprimirDeFrentePraTrasVaziaFormatacaoVazia() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(2);
    assertEquals("[]", fila.imprimirDeFrentePraTras());
  }
  
  @Test
  public void testImprimirDeFrentePraTrasFormatacao() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("A");
    fila.enfileirarFim("B");
    fila.enfileirarFim("C");
    assertEquals("[A,B,C]", fila.imprimirDeFrentePraTras());
  }

  @Test
  public void testEstaCheia() {
      Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(2);
      assertFalse(fila.estaCheia());
      fila.enfileirarFim("Primeiro");
      assertFalse(fila.estaCheia());
      fila.enfileirarFim("Segundo");
      assertTrue(fila.estaCheia());
  }
  
  @Test
  public void testEstaVazia() {
      Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(2);
      assertTrue(fila.estaVazia());
      fila.enfileirarFim("Primeiro");
      assertFalse(fila.estaVazia());
      fila.desenfileirarInicio();
      assertTrue(fila.estaVazia());
  }

  @Test
  public void testFilaCheiaEnfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
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
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
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
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
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
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
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
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
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
  public void testLimpezaFila() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(2);
    fila.enfileirarFim("A");
    fila.enfileirarFim("B");
    fila.desenfileirarInicio();
    fila.desenfileirarInicio();
    assertTrue(fila.estaVazia());
    try {
        fila.frente();
        fail("Deveria ter lançado exceção de fila vazia!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
    }
  }

  @Test
  public void testEnfileirarInicio() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarInicio("A");
    fila.enfileirarInicio("B");
    fila.enfileirarInicio("C");
    assertEquals("C", fila.frente());
    assertEquals("[C,B,A]", fila.imprimirDeFrentePraTras());
  }

  @Test
  public void testEnfileirarInicioCheia() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarInicio("A");
    try {
        fila.enfileirarInicio("B");
        fail("Deveria ter lançado exceção de fila cheia!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Cheia!", e.getMessage());
    }
  }

  @Test
  public void testDesenfileirarFim() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("A");
    fila.enfileirarFim("B");
    fila.enfileirarFim("C");
    assertEquals("C", fila.desenfileirarFim());
    assertEquals("B", fila.desenfileirarFim());
    assertEquals("A", fila.desenfileirarFim());
  }

  @Test
  public void testDesenfileirarFimVazia() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarFim("A");
    fila.desenfileirarFim();
    try {
        fila.desenfileirarFim();
        fail("Deveria ter lançado exceção de fila vazia!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
    }
  }

  @Test
  public void testTras() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("A");
    fila.enfileirarFim("B");
    fila.enfileirarFim("C");
    assertEquals("C", fila.tras());
  }

  @Test
  public void testTrasVazia() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    fila.enfileirarFim("A");
    fila.desenfileirarFim();
    try {
        fila.tras();
        fail("Deveria ter lançado exceção de fila vazia!");
    } catch (NoSuchElementException e) {
        assertEquals("Fila Vazia!", e.getMessage());
    }
  }

  @Test
  public void testImprimirDeTrasPraFrente() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(3);
    fila.enfileirarFim("A");
    fila.enfileirarFim("B");
    fila.enfileirarFim("C");
    assertEquals("[C,B,A]", fila.imprimirDeTrasPraFrente());
  }

  @Test
  public void testImprimirDeTrasPraFrenteVazia() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(1);
    assertEquals("[]", fila.imprimirDeTrasPraFrente());
  }

  @Test
  public void testOperacoesCombinadas() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(4);
    fila.enfileirarInicio("A");
    fila.enfileirarFim("B");
    fila.enfileirarInicio("C");
    fila.enfileirarFim("D");
    assertEquals("C", fila.frente());
    assertEquals("D", fila.tras());
    assertEquals("[C,A,B,D]", fila.imprimirDeFrentePraTras());
    assertEquals("[D,B,A,C]", fila.imprimirDeTrasPraFrente());
  }

  @Test
  public void testOperacoesCombinadasDesenfileirar() {
    Enfileiravel<String> fila = new FilaDinamicaDuplaTerminacaoGenerica<>(4);
    fila.enfileirarInicio("A");
    fila.enfileirarFim("B");
    fila.enfileirarInicio("C");
    fila.enfileirarFim("D");
    assertEquals("C", fila.desenfileirarInicio());
    assertEquals("D", fila.desenfileirarFim());
    assertEquals("A", fila.desenfileirarInicio());
    assertEquals("B", fila.desenfileirarFim());
    assertTrue(fila.estaVazia());
  }
}