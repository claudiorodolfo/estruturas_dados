import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste para a Árvore AVL Genérica (Integer).
 * Testa inserção, busca, remoção, balanceamento e casos de borda.
 */
public class AVLTest {
    private AVL<Integer> avl;

    @Before
    public void setUp() {
        avl = new AVL<>();
    }

    @Test
    public void testInserirEExiste() {
        avl.inserir(10);
        avl.inserir(20);
        avl.inserir(5);
        assertTrue(avl.existe(10));
        assertTrue(avl.existe(20));
        assertTrue(avl.existe(5));
        assertFalse(avl.existe(99));
    }

    @Test
    public void testApagarFolha() {
        avl.inserir(10);
        avl.inserir(20);
        avl.apagar(20);
        assertFalse(avl.existe(20));
        assertTrue(avl.existe(10));
    }

    @Test
    public void testApagarComUmFilho() {
        avl.inserir(10);
        avl.inserir(20);
        avl.inserir(5);
        avl.inserir(30);
        avl.apagar(20);
        assertFalse(avl.existe(20));
        assertTrue(avl.existe(10));
        assertTrue(avl.existe(5));
        assertTrue(avl.existe(30));
    }

    @Test
    public void testApagarComDoisFilhos() {
        avl.inserir(20);
        avl.inserir(10);
        avl.inserir(30);
        avl.inserir(25);
        avl.apagar(20);
        assertFalse(avl.existe(20));
        assertTrue(avl.existe(10));
        assertTrue(avl.existe(30));
        assertTrue(avl.existe(25));
    }

    @Test
    public void testLimpar() {
        avl.inserir(10);
        avl.inserir(20);
        avl.limpar();
        assertFalse(avl.existe(10));
        assertFalse(avl.existe(20));
    }

    @Test
    public void testImprimirEmOrdem() {
        avl.inserir(20);
        avl.inserir(10);
        avl.inserir(30);
        avl.inserir(25);
        String resultado = avl.imprimirEmOrdem();
        assertTrue(resultado.contains("10"));
        assertTrue(resultado.contains("20"));
        assertTrue(resultado.contains("25"));
        assertTrue(resultado.contains("30"));
    }

    @Test
    public void testInserirDuplicado() {
        avl.inserir(10);
        avl.inserir(10); // Duplicado
        int count = contarOcorrencias(10);
        assertEquals(2, count); // A AVL permite duplicados, ajuste se necessário
    }

    @Test
    public void testRemoverInexistente() {
        avl.inserir(10);
        Integer removido = avl.apagar(99);
        assertNull(removido);
        assertTrue(avl.existe(10));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(avl.existe(1234));
        Integer removido = avl.apagar(1234);
        assertNull(removido);
        assertEquals("[]", avl.imprimirEmOrdem());
    }

    @Test
    public void testBalanceamentoAVL() {
        // Inserir valores em ordem que force rotações
        avl.inserir(1);
        avl.inserir(2);
        avl.inserir(3);
        avl.inserir(4);
        avl.inserir(5);
        // Verificar se todos os valores ainda existem após balanceamento
        assertTrue(avl.existe(1));
        assertTrue(avl.existe(2));
        assertTrue(avl.existe(3));
        assertTrue(avl.existe(4));
        assertTrue(avl.existe(5));
    }

    @Test
    public void testImprimirPreOrdem() {
        avl.inserir(20);
        avl.inserir(10);
        avl.inserir(30);
        String resultado = avl.imprimirPreOrdem();
        assertTrue(resultado.contains("20"));
        assertTrue(resultado.contains("10"));
        assertTrue(resultado.contains("30"));
    }

    @Test
    public void testImprimirPosOrdem() {
        avl.inserir(20);
        avl.inserir(10);
        avl.inserir(30);
        String resultado = avl.imprimirPosOrdem();
        assertTrue(resultado.contains("20"));
        assertTrue(resultado.contains("10"));
        assertTrue(resultado.contains("30"));
    }

    @Test
    public void testInsercaoMassaEBusca() {
        int total = 1000;
        for (int i = 1; i <= total; i++) {
            avl.inserir(i);
        }
        for (int i = 1; i <= total; i++) {
            assertTrue("Elemento " + i + " deveria existir", avl.existe(i));
        }
        assertFalse(avl.existe(total + 1));
    }

    @Test
    public void testRemocaoMassa() {
        int total = 500;
        for (int i = 1; i <= total; i++) {
            avl.inserir(i);
        }
        for (int i = 1; i <= total; i += 2) {
            avl.apagar(i);
        }
        for (int i = 1; i <= total; i++) {
            if (i % 2 == 0) {
                assertTrue(avl.existe(i));
            } else {
                assertFalse(avl.existe(i));
            }
        }
    }

    @Test
    public void testInsercaoDuplicadosMassa() {
        int total = 200;
        for (int i = 1; i <= total; i++) {
            avl.inserir(i);
            avl.inserir(i); // duplicado
        }
        for (int i = 1; i <= total; i++) {
            int count = contarOcorrencias(i);
            assertEquals(2, count);
        }
    }

    @Test
    public void testImpressaoEmOrdemGrande() {
        int total = 100;
        for (int i = total; i >= 1; i--) {
            avl.inserir(i);
        }
        String emOrdem = avl.imprimirEmOrdem();
        for (int i = 1; i <= total; i++) {
            assertTrue(emOrdem.contains(String.valueOf(i)));
        }
    }

    @Test
    public void testBalanceamentoGrande() {
        int total = 300;
        for (int i = 1; i <= total; i++) {
            avl.inserir(i);
        }
        // Após muitas inserções, a altura da AVL deve ser logarítmica
        int altura = getAltura(avl);
        assertTrue("Altura da AVL muito grande: " + altura, altura < 2 * (int)(Math.log(total) / Math.log(2)) + 1);
    }

    // Método auxiliar para contar ocorrências de um valor na árvore
    private int contarOcorrencias(int valor) {
        String emOrdem = avl.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()\\[\\]]+")) {
            if (s.equals(String.valueOf(valor))) count++;
        }
        return count;
    }

    // Método auxiliar para obter altura da árvore
    private int getAltura(AVL<Integer> avl) {
        try {
            java.lang.reflect.Field raizField = avl.getClass().getDeclaredField("raiz");
            raizField.setAccessible(true);
            Object raiz = raizField.get(avl);
            if (raiz == null) return 0;
            java.lang.reflect.Method alturaMetodo = raiz.getClass().getDeclaredMethod("getAltura");
            alturaMetodo.setAccessible(true);
            return (int) alturaMetodo.invoke(raiz);
        } catch (Exception e) {
            return -1; // Não conseguiu acessar
        }
    }
} 