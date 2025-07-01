import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste para a Árvore Binária de Pesquisa (ABP).
 */
public class ABPTest {
    private Arborizavel<Integer> abp;

    @Before
    public void setUp() {
        abp = new ABP<>();
    }

    @Test
    public void testInserirEExiste() {
        abp.inserir(10);
        abp.inserir(5);
        abp.inserir(15);
        assertTrue(abp.existe(10));
        assertTrue(abp.existe(5));
        assertTrue(abp.existe(15));
        assertFalse(abp.existe(20));
    }

    @Test
    public void testApagarFolha() {
        abp.inserir(10);
        abp.inserir(5);
        abp.inserir(15);
        abp.apagar(5);
        assertFalse(abp.existe(5));
        assertTrue(abp.existe(10));
        assertTrue(abp.existe(15));
    }

    @Test
    public void testApagarComUmFilho() {
        abp.inserir(10);
        abp.inserir(5);
        abp.inserir(2);
        abp.apagar(5);
        assertFalse(abp.existe(5));
        assertTrue(abp.existe(2));
        assertTrue(abp.existe(10));
    }

    @Test
    public void testApagarComDoisFilhos() {
        abp.inserir(10);
        abp.inserir(5);
        abp.inserir(15);
        abp.inserir(12);
        abp.inserir(18);
        abp.apagar(15);
        assertFalse(abp.existe(15));
        assertTrue(abp.existe(12));
        assertTrue(abp.existe(18));
    }

    @Test
    public void testLimpar() {
        abp.inserir(10);
        abp.inserir(5);
        abp.limpar();
        assertFalse(abp.existe(10));
        assertFalse(abp.existe(5));
    }

    @Test
    public void testImprimirPreOrdem() {
        abp.inserir(4);
        abp.inserir(2);
        abp.inserir(6);
        abp.inserir(1);
        abp.inserir(3);
        abp.inserir(5);
        abp.inserir(7);
        String esperado = "[1,2,3,4,5,6,7]";
        assertEquals(esperado, abp.imprimirPreOrdem());
    }

    @Test
    public void testImprimirEmOrdem() {
        abp.inserir(4);
        abp.inserir(2);
        abp.inserir(6);
        abp.inserir(1);
        abp.inserir(3);
        abp.inserir(5);
        abp.inserir(7);
        String esperado = "[1,2,3,4,5,6,7]";
        assertEquals(esperado, abp.imprimirEmOrdem());
    }

    @Test
    public void testImprimirPosOrdem() {
        abp.inserir(4);
        abp.inserir(2);
        abp.inserir(6);
        abp.inserir(1);
        abp.inserir(3);
        abp.inserir(5);
        abp.inserir(7);
        String esperado = "[1,2,3,4,5,6,7]";
        assertEquals(esperado, abp.imprimirPosOrdem());
    }
} 