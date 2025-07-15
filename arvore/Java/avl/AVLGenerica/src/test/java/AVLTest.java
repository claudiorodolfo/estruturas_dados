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
        avl.apagar(20);
        assertFalse(avl.existe(20));
        assertTrue(avl.existe(10));
        assertTrue(avl.existe(5));
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

    // Método auxiliar para contar ocorrências de um valor na árvore
    private int contarOcorrencias(int valor) {
        String emOrdem = avl.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()\\[\\]]+")) {
            if (s.equals(String.valueOf(valor))) count++;
        }
        return count;
    }
} 