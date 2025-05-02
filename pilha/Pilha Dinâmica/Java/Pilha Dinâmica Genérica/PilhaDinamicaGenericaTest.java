import org.junit.Test;
import static org.junit.Assert.*;

public class PilhaDinamicaGenericaTest {

  @Test
  public void testEmpilhar() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
    pilha.empilhar("Instituto");
    assertEquals("Instituto", pilha.espiar());
  }

  @Test
  public void testDesempilhar() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
    pilha.empilhar("Instituto");
    String conteudo = pilha.desempilhar();
    assertEquals("Instituto", conteudo);
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
  public void testPilhaCheia() {
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(2);
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
    Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(2);
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