//Executar todos os comandos dentro da pasta "Pilha Dinâmica Genérica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.13.2.jar -d test test/PilhaDinamicaGenericaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore PilhaDinamicaGenericaTest
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class PilhaDinamicaGenericaTest {

    @Test
    public void testPilhaComDiferentesTipos() {
        Empilhavel<Integer> pilhaInt = new PilhaDinamicaGenerica<>(2);
        pilhaInt.empilhar(1);
        pilhaInt.empilhar(2);
        assertEquals(Integer.valueOf(2), pilhaInt.espiar());
        
        Empilhavel<Double> pilhaDouble = new PilhaDinamicaGenerica<>(2);
        pilhaDouble.empilhar(1.5);
        pilhaDouble.empilhar(2.5);
        assertEquals(Double.valueOf(2.5), pilhaDouble.espiar());
    }

    @Test
    public void testConstrutorPadrao() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>();
        // Deve permitir 10 elementos
        for (int i = 0; i < 10; i++) {
            pilha.empilhar("Elemento" + i);
        }
        assertTrue(pilha.estaCheia());
        try {
            pilha.empilhar("Extra");
            fail("Deveria ter lançado exceção de pilha cheia!");
        } catch (NoSuchElementException e) {
            assertEquals("Pilha Cheia!", e.getMessage());
        }
    }

    @Test
    public void testConstrutorComTamanhoPersonalizado() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
        // Deve permitir 5 elementos
        for (int i = 0; i < 5; i++) {
            pilha.empilhar("Elemento" + i);
        }
        assertTrue(pilha.estaCheia());
        try {
            pilha.empilhar("Extra");
            fail("Deveria ter lançado exceção de pilha cheia!");
        } catch (NoSuchElementException e) {
            assertEquals("Pilha Cheia!", e.getMessage());
        }
    }

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
    public void testDesempilharMultiplosElementos() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(3);
        pilha.empilhar("A");
        pilha.empilhar("B");
        pilha.empilhar("C");
        assertEquals("C", pilha.desempilhar());
        assertEquals("B", pilha.desempilhar());
        assertEquals("A", pilha.espiar());
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
        pilha.atualizar("Universidade");
        assertEquals("Universidade", pilha.espiar());
    }

    @Test
    public void testAtualizarComMultiplosElementos() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(3);
        pilha.empilhar("Primeiro");
        pilha.empilhar("Segundo");
        pilha.empilhar("Terceiro");
        pilha.atualizar("Novo");
        assertEquals("Novo", pilha.espiar());
        pilha.desempilhar();
        assertEquals("Segundo", pilha.espiar());
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
    public void testImprimirVaziaFormatacaoVazia() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(2);
        assertEquals("[]", pilha.imprimir());
    }
    
    @Test
    public void testImprimirFormatacao() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(3);
        pilha.empilhar("A");
        pilha.empilhar("B");
        pilha.empilhar("C");
        assertEquals("[C,B,A]", pilha.imprimir());
    }

    @Test
    public void testEstaCheia() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(2);
        assertFalse(pilha.estaCheia());
        pilha.empilhar("Primeiro");
        assertFalse(pilha.estaCheia());
        pilha.empilhar("Segundo");
        assertTrue(pilha.estaCheia());
    }
    
    @Test
    public void testEstaVazia() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(2);
        assertTrue(pilha.estaVazia());
        pilha.empilhar("Primeiro");
        assertFalse(pilha.estaVazia());
        pilha.desempilhar();
        assertTrue(pilha.estaVazia());
    }

    @Test
    public void testPilhaCheiaEmpilhar() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(1);
        pilha.empilhar("Instituto");
        try {
            pilha.empilhar("Federal"); // deve lançar exceção
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
            fail("Deveria ter acontecido um underflow!");
        } catch (NoSuchElementException e) {
            assertEquals("Pilha Vazia!", e.getMessage());
        }
    }

    @Test
    public void testLimpezaPilha() {
        Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(2);
        pilha.empilhar("A");
        pilha.empilhar("B");
        pilha.desempilhar();
        pilha.desempilhar();
        assertTrue(pilha.estaVazia());
        try {
            pilha.espiar();
            fail("Deveria ter lançado exceção de pilha vazia!");
        } catch (NoSuchElementException e) {
            assertEquals("Pilha Vazia!", e.getMessage());
        }
    }
}