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
        Produto p1 = new Produto("Café", 10010L);
        Produto p2 = new Produto("Leite", 10005L);
        Produto p3 = new Produto("Açúcar", 10015L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        assertTrue(abp.existe(new Produto( 10010L)));
        assertTrue(abp.existe(new Produto( 10005L)));
        assertTrue(abp.existe(new Produto( 10015L)));
        assertFalse(abp.existe(new Produto( 10020L)));
    }

    @Test
    public void testApagarFolha() {
        Produto p1 = new Produto("Café", 10010L);
        Produto p2 = new Produto("Leite", 10005L);
        Produto p3 = new Produto("Açúcar", 10015L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        abp.apagar(new Produto( 10005L));
        assertFalse(abp.existe(new Produto( 10005L)));
        assertTrue(abp.existe(new Produto( 10010L)));
        assertTrue(abp.existe(new Produto( 10015L)));
    }

    @Test
    public void testApagarComUmFilho() {
        Produto p1 = new Produto("Café", 10010L);
        Produto p2 = new Produto("Leite", 10005L);
        Produto p3 = new Produto("Arroz", 10002L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        abp.apagar(new Produto( 10005L));
        assertFalse(abp.existe(new Produto( 10005L)));
        assertTrue(abp.existe(new Produto( 10002L)));
        assertTrue(abp.existe(new Produto( 10010L)));
    }

    @Test
    public void testApagarComDoisFilhos() {
        Produto p1 = new Produto("Café", 10010L);
        Produto p2 = new Produto("Leite", 10005L);
        Produto p3 = new Produto("Açúcar", 10015L);
        Produto p4 = new Produto("Biscoito", 10012L);
        Produto p5 = new Produto("Pão", 10018L);
        abp.inserir(p1);
        abp.inserir(p2);
        abp.inserir(p3);
        abp.inserir(p4);
        abp.inserir(p5);
        abp.apagar(new Produto( 10015L));
        assertFalse(abp.existe(new Produto( 10015L)));
        assertTrue(abp.existe(new Produto( 10012L)));
        assertTrue(abp.existe(new Produto( 10018L)));
    }

    @Test
    public void testLimpar() {
        abp.inserir(new Produto("Café", 10010L));
        abp.inserir(new Produto("Leite", 10005L));
        abp.limpar();
        assertFalse(abp.existe(new Produto( 10010L)));
        assertFalse(abp.existe(new Produto( 10005L)));
    }

    @Test
    public void testImprimirEmOrdem() {
        abp.inserir(new Produto("Carne", 10004L));
        abp.inserir(new Produto("Arroz", 10002L));
        abp.inserir(new Produto("Queijo", 10006L));
        abp.inserir(new Produto("Laranja", 10001L));
        abp.inserir(new Produto("Ovos", 10003L));
        abp.inserir(new Produto("Leite", 10005L));
        abp.inserir(new Produto("Manga", 10007L));
        String esperado = "["+
                "Produto(Laranja,10001)"+"," +
                "Produto(Arroz,10002)"+"," +
                "Produto(Ovos,10003)"+"," +
                "Produto(Carne,10004)"+"," +
                "Produto(Leite,10005)"+"," +
                "Produto(Queijo,10006)"+"," +
                "Produto(Manga,10007)"+
            "]";
        String resultado = abp.imprimirEmOrdem();
        assertEquals(esperado, resultado);

        assertTrue(resultado.contains("Produto(Queijo,10006)"));
        assertTrue(resultado.contains("Produto(Manga,10007)"));
        assertTrue(resultado.contains("Produto(Carne,10004)"));
        assertTrue(resultado.contains("Produto(Laranja,10001)"));
    }
}
