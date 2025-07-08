import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import src.ArvoreB;
import src.Produto;
import src.NoArvoreB;

/**
 * Classe de teste para a Árvore B de Produtos.
 * Testa inserção, busca, remoção, balanceamento e casos de borda.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreBProdutoTest {
    private ArvoreB<Produto> arvoreB;

    @Before
    public void setUp() {
        arvoreB = new ArvoreB<>();
    }

    @Test
    public void testInserirEExiste() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        arvoreB.inserir(p1);
        arvoreB.inserir(p2);
        arvoreB.inserir(p3);
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
        assertTrue(arvoreB.existe(new Produto("", 1002L)));
        assertTrue(arvoreB.existe(new Produto("", 1003L)));
        assertFalse(arvoreB.existe(new Produto("", 9999L)));
    }

    @Test
    public void testApagarProduto() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        arvoreB.inserir(p1);
        arvoreB.inserir(p2);
        arvoreB.apagar(new Produto("", 1002L));
        assertFalse(arvoreB.existe(new Produto("", 1002L)));
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
    }

    @Test
    public void testApagarComUmFilho() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        arvoreB.inserir(p1);
        arvoreB.inserir(p2);
        arvoreB.inserir(p3);
        arvoreB.apagar(new Produto("", 1002L));
        assertFalse(arvoreB.existe(new Produto("", 1002L)));
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
        assertTrue(arvoreB.existe(new Produto("", 1003L)));
    }

    @Test
    public void testApagarComDoisFilhos() {
        Produto p1 = new Produto("Café", 1002L);
        Produto p2 = new Produto("Leite", 1001L);
        Produto p3 = new Produto("Açúcar", 1003L);
        Produto p4 = new Produto("Biscoito", 1004L);
        arvoreB.inserir(p1);
        arvoreB.inserir(p2);
        arvoreB.inserir(p3);
        arvoreB.inserir(p4);
        arvoreB.apagar(new Produto("", 1002L));
        assertFalse(arvoreB.existe(new Produto("", 1002L)));
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
        assertTrue(arvoreB.existe(new Produto("", 1003L)));
        assertTrue(arvoreB.existe(new Produto("", 1004L)));
    }

    @Test
    public void testLimpar() {
        arvoreB.inserir(new Produto("Café", 1001L));
        arvoreB.inserir(new Produto("Leite", 1002L));
        arvoreB.limpar();
        assertFalse(arvoreB.existe(new Produto("", 1001L)));
        assertFalse(arvoreB.existe(new Produto("", 1002L)));
    }

    @Test
    public void testImprimirEmOrdem() {
        arvoreB.inserir(new Produto("Café", 1002L));
        arvoreB.inserir(new Produto("Leite", 1001L));
        arvoreB.inserir(new Produto("Açúcar", 1003L));
        arvoreB.inserir(new Produto("Biscoito", 1004L));
        String resultado = arvoreB.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
        assertTrue(resultado.contains("Biscoito (1004)"));
    }

    @Test
    public void testInserirProdutoDuplicado() {
        Produto p1 = new Produto("Café", 1001L);
        arvoreB.inserir(p1);
        arvoreB.inserir(new Produto("Café Duplicado", 1001L)); // Mesmo código de barras
        // Deve existir apenas um produto com esse código
        int count = contarProdutosComCodigo(1001L);
        assertEquals(1, count); // A Árvore B não permite duplicados
    }

    @Test
    public void testRemoverProdutoInexistente() {
        Produto p1 = new Produto("Café", 1001L);
        arvoreB.inserir(p1);
        Produto removido = arvoreB.apagar(new Produto("Leite", 9999L));
        assertNull(removido);
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(arvoreB.existe(new Produto("", 1234L)));
        Produto removido = arvoreB.apagar(new Produto("", 1234L));
        assertNull(removido);
        assertEquals("", arvoreB.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    @Test
    public void testBalanceamentoArvoreB() {
        // Inserir produtos em ordem que force divisões de nós
        arvoreB.inserir(new Produto("A", 1001L));
        arvoreB.inserir(new Produto("B", 1002L));
        arvoreB.inserir(new Produto("C", 1003L));
        arvoreB.inserir(new Produto("D", 1004L));
        arvoreB.inserir(new Produto("E", 1005L));
        arvoreB.inserir(new Produto("F", 1006L));
        arvoreB.inserir(new Produto("G", 1007L));
        
        // Verificar se todos os produtos ainda existem após balanceamento
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
        assertTrue(arvoreB.existe(new Produto("", 1002L)));
        assertTrue(arvoreB.existe(new Produto("", 1003L)));
        assertTrue(arvoreB.existe(new Produto("", 1004L)));
        assertTrue(arvoreB.existe(new Produto("", 1005L)));
        assertTrue(arvoreB.existe(new Produto("", 1006L)));
        assertTrue(arvoreB.existe(new Produto("", 1007L)));
    }

    @Test
    public void testImprimirPreOrdem() {
        arvoreB.inserir(new Produto("Café", 1002L));
        arvoreB.inserir(new Produto("Leite", 1001L));
        arvoreB.inserir(new Produto("Açúcar", 1003L));
        String resultado = arvoreB.imprimirPreOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
    }

    @Test
    public void testImprimirPosOrdem() {
        arvoreB.inserir(new Produto("Café", 1002L));
        arvoreB.inserir(new Produto("Leite", 1001L));
        arvoreB.inserir(new Produto("Açúcar", 1003L));
        String resultado = arvoreB.imprimirPosOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
    }

    @Test
    public void testPropriedadesArvoreB() {
        // Testar propriedades básicas da árvore B
        arvoreB.inserir(new Produto("A", 1001L));
        arvoreB.inserir(new Produto("B", 1002L));
        arvoreB.inserir(new Produto("C", 1003L));
        
        // Verificar se a raiz existe
        NoArvoreB<Produto> raiz = arvoreB.getRaiz();
        assertNotNull(raiz);
        
        // Verificar se os elementos estão ordenados
        String emOrdem = arvoreB.imprimirEmOrdem();
        assertTrue(emOrdem.contains("A (1001)"));
        assertTrue(emOrdem.contains("B (1002)"));
        assertTrue(emOrdem.contains("C (1003)"));
    }

    @Test
    public void testOrdemPersonalizada() {
        // Testar árvore B com ordem diferente
        ArvoreB<Produto> arvoreBOrdem4 = new ArvoreB<>(4);
        arvoreBOrdem4.inserir(new Produto("A", 1001L));
        arvoreBOrdem4.inserir(new Produto("B", 1002L));
        arvoreBOrdem4.inserir(new Produto("C", 1003L));
        arvoreBOrdem4.inserir(new Produto("D", 1004L));
        arvoreBOrdem4.inserir(new Produto("E", 1005L));
        
        assertTrue(arvoreBOrdem4.existe(new Produto("", 1001L)));
        assertTrue(arvoreBOrdem4.existe(new Produto("", 1002L)));
        assertTrue(arvoreBOrdem4.existe(new Produto("", 1003L)));
        assertTrue(arvoreBOrdem4.existe(new Produto("", 1004L)));
        assertTrue(arvoreBOrdem4.existe(new Produto("", 1005L)));
    }

    @Test
    public void testRemocaoComplexa() {
        // Testar remoção de produtos que causam rebalanceamento
        arvoreB.inserir(new Produto("A", 1001L));
        arvoreB.inserir(new Produto("B", 1002L));
        arvoreB.inserir(new Produto("C", 1003L));
        arvoreB.inserir(new Produto("D", 1004L));
        arvoreB.inserir(new Produto("E", 1005L));
        
        // Remover produto do meio
        Produto removido = arvoreB.apagar(new Produto("", 1003L));
        assertEquals("C (1003)", removido.toString());
        assertFalse(arvoreB.existe(new Produto("", 1003L)));
        assertTrue(arvoreB.existe(new Produto("", 1001L)));
        assertTrue(arvoreB.existe(new Produto("", 1002L)));
        assertTrue(arvoreB.existe(new Produto("", 1004L)));
        assertTrue(arvoreB.existe(new Produto("", 1005L)));
    }

    /**
     * Conta quantos produtos existem com o código especificado.
     */
    private int contarProdutosComCodigo(long codigo) {
        String emOrdem = arvoreB.imprimirEmOrdem();
        int count = 0;
        String codigoStr = "(" + codigo + ")";
        int index = 0;
        while ((index = emOrdem.indexOf(codigoStr, index)) != -1) {
            count++;
            index += codigoStr.length();
        }
        return count;
    }
} 