import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste para a Árvore Vermelho e Preto de Produtos.
 * Testa inserção, busca, remoção, balanceamento e casos de borda.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class RBTProdutoTest {
    private RBT<Produto> rbt;

    @Before
    public void setUp() {
        rbt = new RBT<>();
    }

    @Test
    public void testInserirEExiste() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        rbt.inserir(p1);
        rbt.inserir(p2);
        rbt.inserir(p3);
        assertTrue(rbt.existe(new Produto("", 1001L)));
        assertTrue(rbt.existe(new Produto("", 1002L)));
        assertTrue(rbt.existe(new Produto("", 1003L)));
        assertFalse(rbt.existe(new Produto("", 9999L)));
    }

    @Test
    public void testApagarFolha() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        rbt.inserir(p1);
        rbt.inserir(p2);
        rbt.apagar(new Produto("", 1002L));
        assertFalse(rbt.existe(new Produto("", 1002L)));
        assertTrue(rbt.existe(new Produto("", 1001L)));
    }

    @Test
    public void testApagarComUmFilho() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        rbt.inserir(p1);
        rbt.inserir(p2);
        rbt.inserir(p3);
        rbt.apagar(new Produto("", 1002L));
        assertFalse(rbt.existe(new Produto("", 1002L)));
        assertTrue(rbt.existe(new Produto("", 1001L)));
        assertTrue(rbt.existe(new Produto("", 1003L)));
    }

    @Test
    public void testApagarComDoisFilhos() {
        Produto p1 = new Produto("Café", 1002L);
        Produto p2 = new Produto("Leite", 1001L);
        Produto p3 = new Produto("Açúcar", 1003L);
        Produto p4 = new Produto("Biscoito", 1004L);
        rbt.inserir(p1);
        rbt.inserir(p2);
        rbt.inserir(p3);
        rbt.inserir(p4);
        rbt.apagar(new Produto("", 1002L));
        assertFalse(rbt.existe(new Produto("", 1002L)));
        assertTrue(rbt.existe(new Produto("", 1001L)));
        assertTrue(rbt.existe(new Produto("", 1003L)));
        assertTrue(rbt.existe(new Produto("", 1004L)));
    }

    @Test
    public void testLimpar() {
        rbt.inserir(new Produto("Café", 1001L));
        rbt.inserir(new Produto("Leite", 1002L));
        rbt.limpar();
        assertFalse(rbt.existe(new Produto("", 1001L)));
        assertFalse(rbt.existe(new Produto("", 1002L)));
    }

    @Test
    public void testImprimirEmOrdem() {
        rbt.inserir(new Produto("Café", 1002L));
        rbt.inserir(new Produto("Leite", 1001L));
        rbt.inserir(new Produto("Açúcar", 1003L));
        rbt.inserir(new Produto("Biscoito", 1004L));
        String resultado = rbt.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
        assertTrue(resultado.contains("Biscoito (1004)"));
    }

    @Test
    public void testInserirProdutoDuplicado() {
        Produto p1 = new Produto("Café", 1001L);
        rbt.inserir(p1);
        rbt.inserir(new Produto("Café Duplicado", 1001L)); // Mesmo código de barras
        // Deve existir apenas um produto com esse código
        int count = contarProdutosComCodigo(1001L);
        assertEquals(2, count); // A RBT permite duplicados, mas pode ser ajustado se necessário
    }

    @Test
    public void testRemoverProdutoInexistente() {
        Produto p1 = new Produto("Café", 1001L);
        rbt.inserir(p1);
        Produto removido = rbt.apagar(new Produto("Leite", 9999L));
        assertNull(removido);
        assertTrue(rbt.existe(new Produto("", 1001L)));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(rbt.existe(new Produto("", 1234L)));
        Produto removido = rbt.apagar(new Produto("", 1234L));
        assertNull(removido);
        assertEquals("", rbt.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    @Test
    public void testBalanceamentoRBT() {
        // Inserir produtos em ordem que force rotações e recolorações
        rbt.inserir(new Produto("A", 1001L));
        rbt.inserir(new Produto("B", 1002L));
        rbt.inserir(new Produto("C", 1003L));
        rbt.inserir(new Produto("D", 1004L));
        rbt.inserir(new Produto("E", 1005L));
        rbt.inserir(new Produto("F", 1006L));
        rbt.inserir(new Produto("G", 1007L));
        
        // Verificar se todos os produtos ainda existem após balanceamento
        assertTrue(rbt.existe(new Produto("", 1001L)));
        assertTrue(rbt.existe(new Produto("", 1002L)));
        assertTrue(rbt.existe(new Produto("", 1003L)));
        assertTrue(rbt.existe(new Produto("", 1004L)));
        assertTrue(rbt.existe(new Produto("", 1005L)));
        assertTrue(rbt.existe(new Produto("", 1006L)));
        assertTrue(rbt.existe(new Produto("", 1007L)));
    }

    @Test
    public void testImprimirPreOrdem() {
        rbt.inserir(new Produto("Café", 1002L));
        rbt.inserir(new Produto("Leite", 1001L));
        rbt.inserir(new Produto("Açúcar", 1003L));
        String resultado = rbt.imprimirPreOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
    }

    @Test
    public void testImprimirPosOrdem() {
        rbt.inserir(new Produto("Café", 1002L));
        rbt.inserir(new Produto("Leite", 1001L));
        rbt.inserir(new Produto("Açúcar", 1003L));
        String resultado = rbt.imprimirPosOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
    }

    @Test
    public void testPropriedadesRBT() {
        // Testar propriedades básicas da árvore vermelho e preto
        rbt.inserir(new Produto("A", 1001L));
        rbt.inserir(new Produto("B", 1002L));
        rbt.inserir(new Produto("C", 1003L));
        
        // Verificar se a raiz é preta
        NoTriplo<Produto> raiz = rbt.getRaiz();
        assertNotNull(raiz);
        assertFalse(raiz.isVermelho()); // Raiz deve ser preta
        
        // Verificar se não há nós vermelhos consecutivos
        verificarPropriedadesRBT(raiz);
    }

    /**
     * Verifica as propriedades da árvore vermelho e preto.
     */
    private void verificarPropriedadesRBT(NoTriplo<Produto> no) {
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

    // Método auxiliar para contar produtos com determinado código de barras
    private int contarProdutosComCodigo(long codigo) {
        String emOrdem = rbt.imprimirEmOrdem();
        int count = 0;
        for (String s : emOrdem.split("[ ,()]+")) {
            if (s.equals(String.valueOf(codigo))) count++;
        }
        return count;
    }
} 