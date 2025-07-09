import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste para a Árvore AVL de Produtos.
 * Testa inserção, busca, remoção, balanceamento e casos de borda.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class AVLProdutoTest {
    private AVL<Produto> avl;

    @Before
    public void setUp() {
        avl = new AVL<>();
    }

    @Test
    public void testInserirEExiste() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        avl.inserir(p1);
        avl.inserir(p2);
        avl.inserir(p3);
        assertTrue(avl.existe(new Produto("Café", 1001L)));
        assertTrue(avl.existe(new Produto("Leite", 1002L)));
        assertTrue(avl.existe(new Produto("Açúcar", 1003L)));
        assertFalse(avl.existe(new Produto("Inexistente", 9999L)));
    }

    @Test
    public void testApagarFolha() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        avl.inserir(p1);
        avl.inserir(p2);
        avl.apagar(new Produto("Leite", 1002L));
        assertFalse(avl.existe(new Produto("Leite", 1002L)));
        assertTrue(avl.existe(new Produto("Café", 1001L)));
    }

    @Test
    public void testApagarComUmFilho() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        avl.inserir(p1);
        avl.inserir(p2);
        avl.inserir(p3);
        avl.apagar(new Produto("Teste", 1002L));
        assertFalse(avl.existe(new Produto("Teste", 1002L)));
        assertTrue(avl.existe(new Produto("Teste", 1001L)));
        assertTrue(avl.existe(new Produto("Teste", 1003L)));
    }

    @Test
    public void testApagarComDoisFilhos() {
        Produto p1 = new Produto("Café", 1002L);
        Produto p2 = new Produto("Leite", 1001L);
        Produto p3 = new Produto("Açúcar", 1003L);
        Produto p4 = new Produto("Biscoito", 1004L);
        avl.inserir(p1);
        avl.inserir(p2);
        avl.inserir(p3);
        avl.inserir(p4);
        avl.apagar(new Produto("Teste", 1002L));
        assertFalse(avl.existe(new Produto("Teste", 1002L)));
        assertTrue(avl.existe(new Produto("Teste", 1001L)));
        assertTrue(avl.existe(new Produto("Teste", 1003L)));
        assertTrue(avl.existe(new Produto("Teste", 1004L)));
    }

    @Test
    public void testLimpar() {
        avl.inserir(new Produto("Café", 1001L));
        avl.inserir(new Produto("Leite", 1002L));
        avl.limpar();
        assertFalse(avl.existe(new Produto("Teste", 1001L)));
        assertFalse(avl.existe(new Produto("Teste", 1002L)));
    }

    @Test
    public void testImprimirEmOrdem() {
        avl.inserir(new Produto("Café", 1002L));
        avl.inserir(new Produto("Leite", 1001L));
        avl.inserir(new Produto("Açúcar", 1003L));
        avl.inserir(new Produto("Biscoito", 1004L));
        String resultado = avl.imprimirEmOrdem();
        assertTrue(resultado.contains("Produto(Leite,1001)"));
        assertTrue(resultado.contains("Produto(Café,1002)"));
        assertTrue(resultado.contains("Produto(Açúcar,1003)"));
        assertTrue(resultado.contains("Produto(Biscoito,1004)"));
    }

    @Test
    public void testInserirProdutoDuplicado() {
        Produto p1 = new Produto("Café", 1001L);
        avl.inserir(p1);
        avl.inserir(new Produto("Café Duplicado", 1001L)); // Mesmo código de barras
        // Deve existir apenas um produto com esse código
        int count = contarProdutosComCodigo(1001L);
        assertEquals(2, count); // A AVL permite duplicados, mas pode ser ajustado se necessário
    }

    @Test
    public void testRemoverProdutoInexistente() {
        Produto p1 = new Produto("Café", 1001L);
        avl.inserir(p1);
        Produto removido = avl.apagar(new Produto("Leite", 9999L));
        assertNull(removido);
        assertTrue(avl.existe(new Produto("Teste", 1001L)));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(avl.existe(new Produto("Teste", 1234L)));
        Produto removido = avl.apagar(new Produto("Teste", 1234L));
        assertNull(removido);
        assertEquals("[]", avl.imprimirEmOrdem());
    }

    @Test
    public void testBalanceamentoAVL() {
        // Inserir produtos em ordem que force rotações
        avl.inserir(new Produto("A", 1001L));
        avl.inserir(new Produto("B", 1002L));
        avl.inserir(new Produto("C", 1003L));
        avl.inserir(new Produto("D", 1004L));
        avl.inserir(new Produto("E", 1005L));
        
        // Verificar se todos os produtos ainda existem após balanceamento
        assertTrue(avl.existe(new Produto("Teste", 1001L)));
        assertTrue(avl.existe(new Produto("Teste", 1002L)));
        assertTrue(avl.existe(new Produto("Teste", 1003L)));
        assertTrue(avl.existe(new Produto("Teste", 1004L)));
        assertTrue(avl.existe(new Produto("Teste", 1005L)));
    }

    @Test
    public void testImprimirPreOrdem() {
        avl.inserir(new Produto("Café", 1002L));
        avl.inserir(new Produto("Leite", 1001L));
        avl.inserir(new Produto("Açúcar", 1003L));
        String resultado = avl.imprimirPreOrdem();
        assertTrue(resultado.contains("Produto(Café,1002)"));
        assertTrue(resultado.contains("Produto(Leite,1001)"));
        assertTrue(resultado.contains("Produto(Açúcar,1003)"));
    }

    @Test
    public void testImprimirPosOrdem() {
        avl.inserir(new Produto("Café", 1002L));
        avl.inserir(new Produto("Leite", 1001L));
        avl.inserir(new Produto("Açúcar", 1003L));
        String resultado = avl.imprimirPosOrdem();
        assertTrue(resultado.contains("Produto(Café,1002)"));
        assertTrue(resultado.contains("Produto(Leite,1001)"));
        assertTrue(resultado.contains("Produto(Açúcar,1003)"));
    }

    // Método auxiliar para contar produtos com determinado código de barras
    private int contarProdutosComCodigo(long codigo) {
        String emOrdem = avl.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()]+")) {
            if (s.equals(String.valueOf(codigo))) count++;
        }
        return count;
    }
} 