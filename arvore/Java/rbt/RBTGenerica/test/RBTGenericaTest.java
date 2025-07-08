import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste para a Árvore Vermelho e Preto Genérica.
 * Testa inserção, busca, remoção, balanceamento e casos de borda com diferentes tipos.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class RBTGenericaTest {
    private RBT<String> rbtString;
    private RBT<Integer> rbtInt;
    private RBT<Double> rbtDouble;

    @Before
    public void setUp() {
        rbtString = new RBT<>();
        rbtInt = new RBT<>();
        rbtDouble = new RBT<>();
    }

    @Test
    public void testInserirEExisteString() {
        rbtString.inserir("banana");
        rbtString.inserir("abacaxi");
        rbtString.inserir("cereja");
        assertTrue(rbtString.existe("banana"));
        assertTrue(rbtString.existe("abacaxi"));
        assertTrue(rbtString.existe("cereja"));
        assertFalse(rbtString.existe("laranja"));
    }

    @Test
    public void testInserirEExisteInt() {
        rbtInt.inserir(50);
        rbtInt.inserir(30);
        rbtInt.inserir(70);
        assertTrue(rbtInt.existe(50));
        assertTrue(rbtInt.existe(30));
        assertTrue(rbtInt.existe(70));
        assertFalse(rbtInt.existe(90));
    }

    @Test
    public void testInserirEExisteDouble() {
        rbtDouble.inserir(3.14);
        rbtDouble.inserir(2.71);
        rbtDouble.inserir(1.41);
        assertTrue(rbtDouble.existe(3.14));
        assertTrue(rbtDouble.existe(2.71));
        assertTrue(rbtDouble.existe(1.41));
        assertFalse(rbtDouble.existe(2.5));
    }

    @Test
    public void testApagarString() {
        rbtString.inserir("banana");
        rbtString.inserir("abacaxi");
        rbtString.inserir("cereja");
        String removido = rbtString.apagar("abacaxi");
        assertEquals("abacaxi", removido);
        assertFalse(rbtString.existe("abacaxi"));
        assertTrue(rbtString.existe("banana"));
        assertTrue(rbtString.existe("cereja"));
    }

    @Test
    public void testApagarInt() {
        rbtInt.inserir(50);
        rbtInt.inserir(30);
        rbtInt.inserir(70);
        Integer removido = rbtInt.apagar(30);
        assertEquals(Integer.valueOf(30), removido);
        assertFalse(rbtInt.existe(30));
        assertTrue(rbtInt.existe(50));
        assertTrue(rbtInt.existe(70));
    }

    @Test
    public void testApagarDouble() {
        rbtDouble.inserir(3.14);
        rbtDouble.inserir(2.71);
        rbtDouble.inserir(1.41);
        Double removido = rbtDouble.apagar(2.71);
        assertEquals(Double.valueOf(2.71), removido);
        assertFalse(rbtDouble.existe(2.71));
        assertTrue(rbtDouble.existe(3.14));
        assertTrue(rbtDouble.existe(1.41));
    }

    @Test
    public void testLimpar() {
        rbtString.inserir("banana");
        rbtString.inserir("abacaxi");
        rbtString.limpar();
        assertFalse(rbtString.existe("banana"));
        assertFalse(rbtString.existe("abacaxi"));
    }

    @Test
    public void testImprimirEmOrdemString() {
        rbtString.inserir("banana");
        rbtString.inserir("abacaxi");
        rbtString.inserir("cereja");
        String resultado = rbtString.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testImprimirEmOrdemInt() {
        rbtInt.inserir(50);
        rbtInt.inserir(30);
        rbtInt.inserir(70);
        String resultado = rbtInt.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("30"));
        assertTrue(resultado.contains("50"));
        assertTrue(resultado.contains("70"));
    }

    @Test
    public void testInserirElementoDuplicado() {
        rbtString.inserir("banana");
        rbtString.inserir("banana"); // Duplicado
        // Deve existir apenas um elemento
        int count = contarElementosComValor("banana");
        assertEquals(2, count); // A RBT permite duplicados
    }

    @Test
    public void testRemoverElementoInexistente() {
        rbtString.inserir("banana");
        String removido = rbtString.apagar("abacaxi");
        assertNull(removido);
        assertTrue(rbtString.existe("banana"));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(rbtString.existe("qualquer"));
        String removido = rbtString.apagar("qualquer");
        assertNull(removido);
        assertEquals("", rbtString.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    @Test
    public void testBalanceamentoRBT() {
        // Inserir elementos em ordem que force rotações e recolorações
        rbtInt.inserir(10);
        rbtInt.inserir(20);
        rbtInt.inserir(30);
        rbtInt.inserir(40);
        rbtInt.inserir(50);
        rbtInt.inserir(60);
        rbtInt.inserir(70);
        
        // Verificar se todos os elementos ainda existem após balanceamento
        assertTrue(rbtInt.existe(10));
        assertTrue(rbtInt.existe(20));
        assertTrue(rbtInt.existe(30));
        assertTrue(rbtInt.existe(40));
        assertTrue(rbtInt.existe(50));
        assertTrue(rbtInt.existe(60));
        assertTrue(rbtInt.existe(70));
    }

    @Test
    public void testImprimirPreOrdem() {
        rbtString.inserir("banana");
        rbtString.inserir("abacaxi");
        rbtString.inserir("cereja");
        String resultado = rbtString.imprimirPreOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testImprimirPosOrdem() {
        rbtString.inserir("banana");
        rbtString.inserir("abacaxi");
        rbtString.inserir("cereja");
        String resultado = rbtString.imprimirPosOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testPropriedadesRBT() {
        // Testar propriedades básicas da árvore vermelho e preto
        rbtInt.inserir(10);
        rbtInt.inserir(20);
        rbtInt.inserir(30);
        
        // Verificar se a raiz é preta
        NoTriplo<Integer> raiz = rbtInt.getRaiz();
        assertNotNull(raiz);
        assertFalse(raiz.isVermelho()); // Raiz deve ser preta
        
        // Verificar se não há nós vermelhos consecutivos
        verificarPropriedadesRBT(raiz);
    }

    @Test
    public void testComparacaoDiferentesTipos() {
        // Testar que a árvore funciona corretamente com diferentes tipos
        RBT<Character> rbtChar = new RBT<>();
        rbtChar.inserir('a');
        rbtChar.inserir('b');
        rbtChar.inserir('c');
        
        assertTrue(rbtChar.existe('a'));
        assertTrue(rbtChar.existe('b'));
        assertTrue(rbtChar.existe('c'));
        assertFalse(rbtChar.existe('d'));
        
        Character removido = rbtChar.apagar('b');
        assertEquals(Character.valueOf('b'), removido);
        assertFalse(rbtChar.existe('b'));
    }

    @Test
    public void testElementosNegativos() {
        rbtInt.inserir(-10);
        rbtInt.inserir(-20);
        rbtInt.inserir(-30);
        
        assertTrue(rbtInt.existe(-10));
        assertTrue(rbtInt.existe(-20));
        assertTrue(rbtInt.existe(-30));
        
        Integer removido = rbtInt.apagar(-20);
        assertEquals(Integer.valueOf(-20), removido);
        assertFalse(rbtInt.existe(-20));
    }

    @Test
    public void testElementosDecimaisNegativos() {
        rbtDouble.inserir(-3.14);
        rbtDouble.inserir(-2.71);
        rbtDouble.inserir(-1.41);
        
        assertTrue(rbtDouble.existe(-3.14));
        assertTrue(rbtDouble.existe(-2.71));
        assertTrue(rbtDouble.existe(-1.41));
        
        Double removido = rbtDouble.apagar(-2.71);
        assertEquals(Double.valueOf(-2.71), removido);
        assertFalse(rbtDouble.existe(-2.71));
    }

    /**
     * Verifica as propriedades da árvore vermelho e preto.
     */
    private void verificarPropriedadesRBT(NoTriplo<?> no) {
        if (no == null) return;
        
        // Verificar se não há nós vermelhos consecutivos
        if (no.isVermelho()) {
            if (no.getEsquerda() != null) {
                assertFalse(no.getEsquerda().isVermelho());
            }
            if (no.getDireita() != null) {
                assertFalse(no.getDireita().isVermelho());
            }
        }
        
        // Verificar propriedades recursivamente
        verificarPropriedadesRBT(no.getEsquerda());
        verificarPropriedadesRBT(no.getDireita());
    }

    // Método auxiliar para contar elementos com determinado valor
    private int contarElementosComValor(String valor) {
        String emOrdem = rbtString.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()]+")) {
            if (s.equals(valor)) count++;
        }
        return count;
    }
} 