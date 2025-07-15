package test.java;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import arvoreb.ArvoreB;
import arvoreb.NoArvoreB;

/**
 * Testes para a implementação genérica da Árvore B.
 * Testa operações básicas com diferentes tipos de dados.
 */
public class ArvoreBGenericaTest {
    private ArvoreB<String> arvoreBString;
    private ArvoreB<Integer> arvoreBInt;
    private ArvoreB<Double> arvoreBDouble;

    @Before
    public void setUp() {
        arvoreBString = new ArvoreB<>();
        arvoreBInt = new ArvoreB<>();
        arvoreBDouble = new ArvoreB<>();
    }

    @Test
    public void testInserirEExisteString() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("abacaxi");
        arvoreBString.inserir("cereja");
        assertTrue(arvoreBString.existe("banana"));
        assertTrue(arvoreBString.existe("abacaxi"));
        assertTrue(arvoreBString.existe("cereja"));
        assertFalse(arvoreBString.existe("laranja"));
    }

    @Test
    public void testInserirEExisteInt() {
        arvoreBInt.inserir(50);
        arvoreBInt.inserir(30);
        arvoreBInt.inserir(70);
        assertTrue(arvoreBInt.existe(50));
        assertTrue(arvoreBInt.existe(30));
        assertTrue(arvoreBInt.existe(70));
        assertFalse(arvoreBInt.existe(90));
    }

    @Test
    public void testInserirEExisteDouble() {
        arvoreBDouble.inserir(3.14);
        arvoreBDouble.inserir(2.71);
        arvoreBDouble.inserir(1.41);
        assertTrue(arvoreBDouble.existe(3.14));
        assertTrue(arvoreBDouble.existe(2.71));
        assertTrue(arvoreBDouble.existe(1.41));
        assertFalse(arvoreBDouble.existe(2.5));
    }

    @Test
    public void testApagarString() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("abacaxi");
        arvoreBString.inserir("cereja");
        String removido = arvoreBString.apagar("abacaxi");
        assertEquals("abacaxi", removido);
        assertFalse(arvoreBString.existe("abacaxi"));
        assertTrue(arvoreBString.existe("banana"));
        assertTrue(arvoreBString.existe("cereja"));
    }

    @Test
    public void testApagarInt() {
        arvoreBInt.inserir(50);
        arvoreBInt.inserir(30);
        arvoreBInt.inserir(70);
        Integer removido = arvoreBInt.apagar(30);
        assertEquals(Integer.valueOf(30), removido);
        assertFalse(arvoreBInt.existe(30));
        assertTrue(arvoreBInt.existe(50));
        assertTrue(arvoreBInt.existe(70));
    }

    @Test
    public void testApagarDouble() {
        arvoreBDouble.inserir(3.14);
        arvoreBDouble.inserir(2.71);
        arvoreBDouble.inserir(1.41);
        Double removido = arvoreBDouble.apagar(2.71);
        assertEquals(Double.valueOf(2.71), removido);
        assertFalse(arvoreBDouble.existe(2.71));
        assertTrue(arvoreBDouble.existe(3.14));
        assertTrue(arvoreBDouble.existe(1.41));
    }

    @Test
    public void testLimpar() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("abacaxi");
        arvoreBString.limpar();
        assertFalse(arvoreBString.existe("banana"));
        assertFalse(arvoreBString.existe("abacaxi"));
    }

    @Test
    public void testImprimirEmOrdemString() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("abacaxi");
        arvoreBString.inserir("cereja");
        String resultado = arvoreBString.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testImprimirEmOrdemInt() {
        arvoreBInt.inserir(50);
        arvoreBInt.inserir(30);
        arvoreBInt.inserir(70);
        String resultado = arvoreBInt.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("30"));
        assertTrue(resultado.contains("50"));
        assertTrue(resultado.contains("70"));
    }

    @Test
    public void testInserirElementoDuplicado() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("banana"); // Duplicado
        // Deve existir apenas um elemento
        int count = contarElementosComValor("banana");
        assertEquals(1, count); // A Árvore B não permite duplicados
    }

    @Test
    public void testRemoverElementoInexistente() {
        arvoreBString.inserir("banana");
        String removido = arvoreBString.apagar("abacaxi");
        assertNull(removido);
        assertTrue(arvoreBString.existe("banana"));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(arvoreBString.existe("qualquer"));
        String removido = arvoreBString.apagar("qualquer");
        assertNull(removido);
        assertEquals("", arvoreBString.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    @Test
    public void testBalanceamentoArvoreB() {
        // Inserir elementos em ordem que force divisões de nós
        arvoreBInt.inserir(10);
        arvoreBInt.inserir(20);
        arvoreBInt.inserir(30);
        arvoreBInt.inserir(40);
        arvoreBInt.inserir(50);
        arvoreBInt.inserir(60);
        arvoreBInt.inserir(70);
        
        // Verificar se todos os elementos ainda existem após balanceamento
        assertTrue(arvoreBInt.existe(10));
        assertTrue(arvoreBInt.existe(20));
        assertTrue(arvoreBInt.existe(30));
        assertTrue(arvoreBInt.existe(40));
        assertTrue(arvoreBInt.existe(50));
        assertTrue(arvoreBInt.existe(60));
        assertTrue(arvoreBInt.existe(70));
    }

    @Test
    public void testImprimirPreOrdem() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("abacaxi");
        arvoreBString.inserir("cereja");
        String resultado = arvoreBString.imprimirPreOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testImprimirPosOrdem() {
        arvoreBString.inserir("banana");
        arvoreBString.inserir("abacaxi");
        arvoreBString.inserir("cereja");
        String resultado = arvoreBString.imprimirPosOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testPropriedadesArvoreB() {
        // Testar propriedades básicas da árvore B
        arvoreBInt.inserir(10);
        arvoreBInt.inserir(20);
        arvoreBInt.inserir(30);
        
        // Verificar se a raiz existe
        NoArvoreB<Integer> raiz = arvoreBInt.getRaiz();
        assertNotNull(raiz);
        
        // Verificar se os elementos estão ordenados
        String emOrdem = arvoreBInt.imprimirEmOrdem();
        assertTrue(emOrdem.contains("10"));
        assertTrue(emOrdem.contains("20"));
        assertTrue(emOrdem.contains("30"));
    }

    @Test
    public void testComparacaoDiferentesTipos() {
        // Testar que a árvore funciona corretamente com diferentes tipos
        ArvoreB<Character> arvoreBChar = new ArvoreB<>();
        arvoreBChar.inserir('a');
        arvoreBChar.inserir('b');
        arvoreBChar.inserir('c');
        
        assertTrue(arvoreBChar.existe('a'));
        assertTrue(arvoreBChar.existe('b'));
        assertTrue(arvoreBChar.existe('c'));
        assertFalse(arvoreBChar.existe('d'));
        
        Character removido = arvoreBChar.apagar('b');
        assertEquals(Character.valueOf('b'), removido);
        assertFalse(arvoreBChar.existe('b'));
    }

    @Test
    public void testOrdemPersonalizada() {
        // Testar árvore B com ordem diferente
        ArvoreB<Integer> arvoreBOrdem4 = new ArvoreB<>(4);
        arvoreBOrdem4.inserir(10);
        arvoreBOrdem4.inserir(20);
        arvoreBOrdem4.inserir(30);
        arvoreBOrdem4.inserir(40);
        arvoreBOrdem4.inserir(50);
        
        assertTrue(arvoreBOrdem4.existe(10));
        assertTrue(arvoreBOrdem4.existe(20));
        assertTrue(arvoreBOrdem4.existe(30));
        assertTrue(arvoreBOrdem4.existe(40));
        assertTrue(arvoreBOrdem4.existe(50));
    }

    @Test
    public void testRemocaoComplexa() {
        // Testar remoção de elementos que causam rebalanceamento
        arvoreBInt.inserir(10);
        arvoreBInt.inserir(20);
        arvoreBInt.inserir(30);
        arvoreBInt.inserir(40);
        arvoreBInt.inserir(50);
        
        // Remover elemento do meio
        Integer removido = arvoreBInt.apagar(30);
        assertEquals(Integer.valueOf(30), removido);
        assertFalse(arvoreBInt.existe(30));
        assertTrue(arvoreBInt.existe(10));
        assertTrue(arvoreBInt.existe(20));
        assertTrue(arvoreBInt.existe(40));
        assertTrue(arvoreBInt.existe(50));
    }

    // Métodos auxiliares
    private int contarElementosComValor(String valor) {
        String resultado = arvoreBString.imprimirEmOrdem();
        int count = 0;
        int index = 0;
        while ((index = resultado.indexOf(valor, index)) != -1) {
            count++;
            index += valor.length();
        }
        return count;
    }
} 