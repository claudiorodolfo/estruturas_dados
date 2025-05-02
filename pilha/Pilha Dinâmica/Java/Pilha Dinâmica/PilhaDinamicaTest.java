import org.junit.Test;
import org.junit.Assert.*;

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
  public void testPilhaCheia() {
    Empilhavel pilha = new PilhaDinamica(2);
    pilha.empilhar("Instituto");
    pilha.empilhar("Federal");
    try {
      pilha.empilhar("de");
      fail("Pilha deveria estar cheia");
    } catch (Exception e) {
      // esperado
    }
  }

  @Test
  public void testPilhaVazia() {
    Empilhavel pilha = new PilhaDinamica(2);
    pilha.empilhar("Instituto");
    pilha.desempilhar();
    try {
      pilha.desempilhar();
      fail("Pilha deveria estar vazia");
    } catch (Exception e) {
      // esperado
    }
  } 
}