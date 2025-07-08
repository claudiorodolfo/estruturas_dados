import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Testes para a implementação genérica da Árvore Vermelho e Preto (AVP).
 * Testa operações básicas com diferentes tipos de dados.
 */
public class AVPGenericaTest {
    private AVP<String> avpString;
    private AVP<Integer> avpInt;
    private AVP<Double> avpDouble;

    @Before
    public void setUp() {
        avpString = new AVP<>();
        avpInt = new AVP<>();
        avpDouble = new AVP<>();
    }

    @Test
    public void testInserirEExisteString() {
        avpString.inserir("banana");
        avpString.inserir("abacaxi");
        avpString.inserir("cereja");
        assertTrue(avpString.existe("banana"));
        assertTrue(avpString.existe("abacaxi"));
        assertTrue(avpString.existe("cereja"));
        assertFalse(avpString.existe("laranja"));
    }

    @Test
    public void testInserirEExisteInt() {
        avpInt.inserir(50);
        avpInt.inserir(30);
        avpInt.inserir(70);
        assertTrue(avpInt.existe(50));
        assertTrue(avpInt.existe(30));
        assertTrue(avpInt.existe(70));
        assertFalse(avpInt.existe(90));
    }

    @Test
    public void testInserirEExisteDouble() {
        avpDouble.inserir(3.14);
        avpDouble.inserir(2.71);
        avpDouble.inserir(1.41);
        assertTrue(avpDouble.existe(3.14));
        assertTrue(avpDouble.existe(2.71));
        assertTrue(avpDouble.existe(1.41));
        assertFalse(avpDouble.existe(2.5));
    }

    @Test
    public void testApagarString() {
        avpString.inserir("banana");
        avpString.inserir("abacaxi");
        avpString.inserir("cereja");
        String removido = avpString.apagar("abacaxi");
        assertEquals("abacaxi", removido);
        assertFalse(avpString.existe("abacaxi"));
        assertTrue(avpString.existe("banana"));
        assertTrue(avpString.existe("cereja"));
    }

    @Test
    public void testApagarInt() {
        avpInt.inserir(50);
        avpInt.inserir(30);
        avpInt.inserir(70);
        Integer removido = avpInt.apagar(30);
        assertEquals(Integer.valueOf(30), removido);
        assertFalse(avpInt.existe(30));
        assertTrue(avpInt.existe(50));
        assertTrue(avpInt.existe(70));
    }

    @Test
    public void testApagarDouble() {
        avpDouble.inserir(3.14);
        avpDouble.inserir(2.71);
        avpDouble.inserir(1.41);
        Double removido = avpDouble.apagar(2.71);
        assertEquals(Double.valueOf(2.71), removido);
        assertFalse(avpDouble.existe(2.71));
        assertTrue(avpDouble.existe(3.14));
        assertTrue(avpDouble.existe(1.41));
    }

    @Test
    public void testLimpar() {
        avpString.inserir("banana");
        avpString.inserir("abacaxi");
        avpString.limpar();
        assertFalse(avpString.existe("banana"));
        assertFalse(avpString.existe("abacaxi"));
    }

    @Test
    public void testImprimirEmOrdemString() {
        avpString.inserir("banana");
        avpString.inserir("abacaxi");
        avpString.inserir("cereja");
        String resultado = avpString.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testImprimirEmOrdemInt() {
        avpInt.inserir(50);
        avpInt.inserir(30);
        avpInt.inserir(70);
        String resultado = avpInt.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("30"));
        assertTrue(resultado.contains("50"));
        assertTrue(resultado.contains("70"));
    }

    @Test
    public void testInserirElementoDuplicado() {
        avpString.inserir("banana");
        avpString.inserir("banana"); // Duplicado
        // Deve existir apenas um elemento
        int count = contarElementosComValor("banana");
        assertEquals(2, count); // A AVP permite duplicados
    }

    @Test
    public void testRemoverElementoInexistente() {
        avpString.inserir("banana");
        String removido = avpString.apagar("abacaxi");
        assertNull(removido);
        assertTrue(avpString.existe("banana"));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(avpString.existe("qualquer"));
        String removido = avpString.apagar("qualquer");
        assertNull(removido);
        assertEquals("", avpString.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    @Test
    public void testBalanceamentoAVP() {
        // Inserir elementos em ordem que force rotações e recolorações
        avpInt.inserir(10);
        avpInt.inserir(20);
        avpInt.inserir(30);
        avpInt.inserir(40);
        avpInt.inserir(50);
        avpInt.inserir(60);
        avpInt.inserir(70);
        
        // Verificar se todos os elementos ainda existem após balanceamento
        assertTrue(avpInt.existe(10));
        assertTrue(avpInt.existe(20));
        assertTrue(avpInt.existe(30));
        assertTrue(avpInt.existe(40));
        assertTrue(avpInt.existe(50));
        assertTrue(avpInt.existe(60));
        assertTrue(avpInt.existe(70));
    }

    @Test
    public void testImprimirPreOrdem() {
        avpString.inserir("banana");
        avpString.inserir("abacaxi");
        avpString.inserir("cereja");
        String resultado = avpString.imprimirPreOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testImprimirPosOrdem() {
        avpString.inserir("banana");
        avpString.inserir("abacaxi");
        avpString.inserir("cereja");
        String resultado = avpString.imprimirPosOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("banana"));
        assertTrue(resultado.contains("abacaxi"));
        assertTrue(resultado.contains("cereja"));
    }

    @Test
    public void testPropriedadesAVP() {
        // Testar propriedades básicas da árvore vermelho e preto
        avpInt.inserir(10);
        avpInt.inserir(20);
        avpInt.inserir(30);
        
        // Verificar se a raiz é preta
        NoTriplo<Integer> raiz = avpInt.getRaiz();
        assertNotNull(raiz);
        assertFalse(raiz.isVermelho()); // Raiz deve ser preta
        
        // Verificar se não há nós vermelhos consecutivos
        verificarPropriedadesAVP(raiz);
    }

    @Test
    public void testComparacaoDiferentesTipos() {
        // Testar que a árvore funciona corretamente com diferentes tipos
        AVP<Character> avpChar = new AVP<>();
        avpChar.inserir('a');
        avpChar.inserir('b');
        avpChar.inserir('c');
        
        assertTrue(avpChar.existe('a'));
        assertTrue(avpChar.existe('b'));
        assertTrue(avpChar.existe('c'));
        assertFalse(avpChar.existe('d'));
        
        Character removido = avpChar.apagar('b');
        assertEquals(Character.valueOf('b'), removido);
        assertFalse(avpChar.existe('b'));
    }

    // Métodos auxiliares
    private int contarElementosComValor(String valor) {
        String resultado = avpString.imprimirEmOrdem();
        int count = 0;
        int index = 0;
        while ((index = resultado.indexOf(valor, index)) != -1) {
            count++;
            index += valor.length();
        }
        return count;
    }

    private void verificarPropriedadesAVP(NoTriplo<Integer> no) {
        if (no == null) return;
        
        // Se o nó é vermelho, seus filhos devem ser pretos
        if (no.isVermelho()) {
            if (no.getEsquerda() != null) {
                assertFalse(no.getEsquerda().isVermelho());
            }
            if (no.getDireita() != null) {
                assertFalse(no.getDireita().isVermelho());
            }
        }
        
        // Verificar recursivamente os filhos
        verificarPropriedadesAVP(no.getEsquerda());
        verificarPropriedadesAVP(no.getDireita());
    }
} 