//Executar todos os comandos dentro da pasta "Lista Dinâmica Genérica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.13.2.jar -d test test/ListaDinamicaGenericaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ListaDinamicaGenericaTest

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;


public class ListaDinamicaGenericaTest {

    @Test
    public void testListaComDiferentesTipos() {
        Listavel<Integer> listaInt = new ListaDinamicaGenerica<>(2);
        listaInt.anexar(1);
        listaInt.anexar(2);
        assertEquals(Integer.valueOf(1), listaInt.selecionar(0));
        
        Listavel<Double> listaDouble = new ListaDinamicaGenerica<>(2);
        listaDouble.anexar(1.5);
        listaDouble.anexar(2.5);
        assertEquals(Double.valueOf(1.5), listaDouble.selecionar(0));
    }

    @Test
    public void testConstrutorPadrao() {
        Listavel<String> lista = new ListaDinamicaGenerica<>();
        // Deve permitir 10 elementos
        for (int i = 0; i < 10; i++) {
            lista.anexar("Elemento" + i);
        }
        assertTrue(lista.estaCheia());
        try {
            lista.anexar("Extra");
            fail("Deveria ter lançado exceção de lista cheia!");
        } catch (OverflowException e) {
            assertEquals("Fila Cheia!", e.getMessage());
        }
    }

    @Test
    public void testConstrutorComTamanhoPersonalizado() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(5);
        // Deve permitir 5 elementos
        for (int i = 0; i < 5; i++) {
            lista.anexar("Elemento" + i);
        }
        assertTrue(lista.estaCheia());
        try {
            lista.anexar("Extra");
            fail("Deveria ter lançado exceção de lista cheia!");
        } catch (OverflowException e) {
            assertEquals("Fila Cheia!", e.getMessage());
        }
    }

    @Test
    public void testAnexar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("A", lista.selecionar(0));
        assertEquals("B", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    @Test
    public void testInserir() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.inserir(0, "A");
        lista.inserir(1, "C");
        lista.inserir(1, "B");
        assertEquals("A", lista.selecionar(0));
        assertEquals("B", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    @Test
    public void testInserirPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        try {
            lista.inserir(1, "A");
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    @Test
    public void testSelecionar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("A", lista.selecionar(0));
        assertEquals("B", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    @Test
    public void testSelecionarPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        try {
            lista.selecionar(1);
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    @Test
    public void testSelecionarTodos() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        Object[] elementos = lista.selecionarTodos();
        assertEquals(3, elementos.length);
        assertEquals("A", elementos[0]);
        assertEquals("B", elementos[1]);
        assertEquals("C", elementos[2]);
    }

    @Test
    public void testSelecionarTodosVazia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        try {
            lista.selecionarTodos();
            fail("Deveria ter lançado exceção de lista vazia!");
        } catch (UnderflowException e) {
            assertEquals("Lista Vazia!", e.getMessage());
        }
    }

    @Test
    public void testAtualizar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        lista.atualizar(1, "X");
        assertEquals("A", lista.selecionar(0));
        assertEquals("X", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    @Test
    public void testAtualizarPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        try {
            lista.atualizar(1, "X");
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    @Test
    public void testApagar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("B", lista.apagar(1));
        assertEquals("A", lista.selecionar(0));
        assertEquals("C", lista.selecionar(1));
    }

    @Test
    public void testApagarPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        try {
            lista.apagar(1);
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    @Test
    public void testEstaCheia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(2);
        assertFalse(lista.estaCheia());
        lista.anexar("A");
        assertFalse(lista.estaCheia());
        lista.anexar("B");
        assertTrue(lista.estaCheia());
    }

    @Test
    public void testEstaVazia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(2);
        assertTrue(lista.estaVazia());
        lista.anexar("A");
        assertFalse(lista.estaVazia());
        lista.apagar(0);
        assertTrue(lista.estaVazia());
    }

    @Test
    public void testImprimir() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("[A,B,C]", lista.imprimir());
    }

    @Test
    public void testImprimirVazia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(2);
        assertEquals("[]", lista.imprimir());
    }

    @Test
    public void testOperacoesCombinadas() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(5);
        lista.anexar("A");
        lista.inserir(1, "B");
        lista.anexar("C");
        lista.atualizar(1, "X");
        assertEquals("A", lista.selecionar(0));
        assertEquals("X", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
        assertEquals("[A,X,C]", lista.imprimir());
    }
} 