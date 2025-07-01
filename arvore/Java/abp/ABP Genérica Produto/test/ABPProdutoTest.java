import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ABPProdutoTest {
    private ABP<Produto> abp;

    @Before
    public void setUp() {
        abp = new ABP<>();
    }

    @Test
    public void testInserirEExiste() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        assertTrue(abp.existe(new Produto("", 1001L)));
        assertTrue(abp.existe(new Produto("", 1002L)));
        assertTrue(abp.existe(new Produto("", 1003L)));
        assertFalse(abp.existe(new Produto("", 9999L)));
    }

    @Test
    public void testApagarFolha() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.apagar(new Produto("", 1002L));
        assertFalse(abp.existe(new Produto("", 1002L)));
        assertTrue(abp.existe(new Produto("", 1001L)));
    }

    @Test
    public void testApagarComUmFilho() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        abp.apagar(new Produto("", 1002L));
        assertFalse(abp.existe(new Produto("", 1002L)));
        assertTrue(abp.existe(new Produto("", 1001L)));
        assertTrue(abp.existe(new Produto("", 1003L)));
    }

    @Test
    public void testApagarComDoisFilhos() {
        Produto p1 = new Produto("Café", 1002L);
        Produto p2 = new Produto("Leite", 1001L);
        Produto p3 = new Produto("Açúcar", 1003L);
        Produto p4 = new Produto("Biscoito", 1004L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        abp.inserir(p4);
        abp.apagar(new Produto("", 1002L));
        assertFalse(abp.existe(new Produto("", 1002L)));
        assertTrue(abp.existe(new Produto("", 1001L)));
        assertTrue(abp.existe(new Produto("", 1003L)));
        assertTrue(abp.existe(new Produto("", 1004L)));
    }

    @Test
    public void testLimpar() {
        abp.inserir(new Produto("Café", 1001L));
        abp.inserir(new Produto("Leite", 1002L));
        abp.limpar();
        assertFalse(abp.existe(new Produto("", 1001L)));
        assertFalse(abp.existe(new Produto("", 1002L)));
    }

    @Test
    public void testImprimirEmOrdem() {
        abp.inserir(new Produto("Café", 1002L));
        abp.inserir(new Produto("Leite", 1001L));
        abp.inserir(new Produto("Açúcar", 1003L));
        abp.inserir(new Produto("Biscoito", 1004L));
        String esperado = "Leite (1001) Café (1002) Açúcar (1003) Biscoito (1004)";
        String resultado = abp.imprimirEmOrdem().replaceAll("\s+", " ").trim();
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
        assertTrue(resultado.contains("Biscoito (1004)"));
    }
}
