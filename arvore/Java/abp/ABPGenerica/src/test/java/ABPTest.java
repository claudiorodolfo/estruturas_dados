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
        String esperado = "[4,2,1,3,6,5,7]";
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
        String esperado = "[1,3,2,5,7,6,4]";
        assertEquals(esperado, abp.imprimirPosOrdem());
    }

    @Test
    public void testInsercaoMassaEBusca() {
        int total = 1000;
        for (int i = 1; i <= total; i++) {
            abp.inserir(i);
        }
        for (int i = 1; i <= total; i++) {
            assertTrue("Elemento " + i + " deveria existir", abp.existe(i));
        }
        assertFalse(abp.existe(total + 1));
    }

    @Test
    public void testRemocaoMassa() {
        int total = 500;
        for (int i = 1; i <= total; i++) {
            abp.inserir(i);
        }
        for (int i = 1; i <= total; i += 2) {
            abp.apagar(i);
        }
        for (int i = 1; i <= total; i++) {
            if (i % 2 == 0) {
                assertTrue(abp.existe(i));
            } else {
                assertFalse(abp.existe(i));
            }
        }
    }

    @Test
    public void testInsercaoDuplicadosMassa() {
        int total = 200;
        for (int i = 1; i <= total; i++) {
            abp.inserir(i);
            abp.inserir(i); // duplicado
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
            abp.inserir(i);
        }
        String emOrdem = abp.imprimirEmOrdem();
        for (int i = 1; i <= total; i++) {
            assertTrue(emOrdem.contains(String.valueOf(i)));
        }
    }

    // Método auxiliar para contar ocorrências de um valor na árvore
    private int contarOcorrencias(int valor) {
        String emOrdem = abp.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()\\[\\]]+")) {
            if (s.equals(String.valueOf(valor))) count++;
        }
        return count;
    }
} 