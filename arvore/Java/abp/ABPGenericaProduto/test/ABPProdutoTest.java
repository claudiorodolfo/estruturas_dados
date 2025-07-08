import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste para a Árvore Binária de Pesquisa (ABP) que armazena Produtos.
 */
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

    @Test
    public void testInserirProdutoDuplicado() {
        Produto p1 = new Produto("Café", 1001L);
        abp.inserir(p1);
        abp.inserir(new Produto("Café Duplicado", 1001L)); // Mesmo código de barras
        // Deve existir apenas um produto com esse código
        int count = contarProdutosComCodigo(1001L);
        assertEquals(2, count); // A ABP permite duplicados, mas pode ser ajustado se necessário
    }

    @Test
    public void testRemoverProdutoInexistente() {
        Produto p1 = new Produto("Café", 1001L);
        abp.inserir(p1);
        Produto removido = abp.apagar(new Produto("Leite", 9999L));
        assertNull(removido);
        assertTrue(abp.existe(new Produto("", 1001L)));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(abp.existe(new Produto("", 1234L)));
        Produto removido = abp.apagar(new Produto("", 1234L));
        assertNull(removido);
        assertEquals("", abp.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    // Método auxiliar para contar produtos com determinado código de barras
    private int contarProdutosComCodigo(long codigo) {
        String emOrdem = abp.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()]+")) {
            if (s.equals(String.valueOf(codigo))) count++;
        }
        return count;
    }
}
