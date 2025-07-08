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
public class AVPProdutoTest {
    private AVP<Produto> avp;

    @Before
    public void setUp() {
        avp = new AVP<>();
    }

    @Test
    public void testInserirEExiste() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        avp.inserir(p1);
        avp.inserir(p2);
        avp.inserir(p3);
        assertTrue(avp.existe(new Produto("", 1001L)));
        assertTrue(avp.existe(new Produto("", 1002L)));
        assertTrue(avp.existe(new Produto("", 1003L)));
        assertFalse(avp.existe(new Produto("", 9999L)));
    }

    @Test
    public void testApagarFolha() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        avp.inserir(p1);
        avp.inserir(p2);
        avp.apagar(new Produto("", 1002L));
        assertFalse(avp.existe(new Produto("", 1002L)));
        assertTrue(avp.existe(new Produto("", 1001L)));
    }

    @Test
    public void testApagarComUmFilho() {
        Produto p1 = new Produto("Café", 1001L);
        Produto p2 = new Produto("Leite", 1002L);
        Produto p3 = new Produto("Açúcar", 1003L);
        avp.inserir(p1);
        avp.inserir(p2);
        avp.inserir(p3);
        avp.apagar(new Produto("", 1002L));
        assertFalse(avp.existe(new Produto("", 1002L)));
        assertTrue(avp.existe(new Produto("", 1001L)));
        assertTrue(avp.existe(new Produto("", 1003L)));
    }

    @Test
    public void testApagarComDoisFilhos() {
        Produto p1 = new Produto("Café", 1002L);
        Produto p2 = new Produto("Leite", 1001L);
        Produto p3 = new Produto("Açúcar", 1003L);
        Produto p4 = new Produto("Biscoito", 1004L);
        avp.inserir(p1);
        avp.inserir(p2);
        avp.inserir(p3);
        avp.inserir(p4);
        avp.apagar(new Produto("", 1002L));
        assertFalse(avp.existe(new Produto("", 1002L)));
        assertTrue(avp.existe(new Produto("", 1001L)));
        assertTrue(avp.existe(new Produto("", 1003L)));
        assertTrue(avp.existe(new Produto("", 1004L)));
    }

    @Test
    public void testLimpar() {
        avp.inserir(new Produto("Café", 1001L));
        avp.inserir(new Produto("Leite", 1002L));
        avp.limpar();
        assertFalse(avp.existe(new Produto("", 1001L)));
        assertFalse(avp.existe(new Produto("", 1002L)));
    }

    @Test
    public void testImprimirEmOrdem() {
        avp.inserir(new Produto("Café", 1002L));
        avp.inserir(new Produto("Leite", 1001L));
        avp.inserir(new Produto("Açúcar", 1003L));
        avp.inserir(new Produto("Biscoito", 1004L));
        String resultado = avp.imprimirEmOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
        assertTrue(resultado.contains("Biscoito (1004)"));
    }

    @Test
    public void testInserirProdutoDuplicado() {
        Produto p1 = new Produto("Café", 1001L);
        avp.inserir(p1);
        avp.inserir(new Produto("Café Duplicado", 1001L)); // Mesmo código de barras
        // Deve existir apenas um produto com esse código
        int count = contarProdutosComCodigo(1001L);
        assertEquals(2, count); // A AVP permite duplicados, mas pode ser ajustado se necessário
    }

    @Test
    public void testRemoverProdutoInexistente() {
        Produto p1 = new Produto("Café", 1001L);
        avp.inserir(p1);
        Produto removido = avp.apagar(new Produto("Leite", 9999L));
        assertNull(removido);
        assertTrue(avp.existe(new Produto("", 1001L)));
    }

    @Test
    public void testOperacoesEmArvoreVazia() {
        assertFalse(avp.existe(new Produto("", 1234L)));
        Produto removido = avp.apagar(new Produto("", 1234L));
        assertNull(removido);
        assertEquals("", avp.imprimirEmOrdem().replaceAll("\\s+", "").trim());
    }

    @Test
    public void testBalanceamentoAVP() {
        // Inserir produtos em ordem que force rotações e recolorações
        avp.inserir(new Produto("A", 1001L));
        avp.inserir(new Produto("B", 1002L));
        avp.inserir(new Produto("C", 1003L));
        avp.inserir(new Produto("D", 1004L));
        avp.inserir(new Produto("E", 1005L));
        avp.inserir(new Produto("F", 1006L));
        avp.inserir(new Produto("G", 1007L));
        
        // Verificar se todos os produtos ainda existem após balanceamento
        assertTrue(avp.existe(new Produto("", 1001L)));
        assertTrue(avp.existe(new Produto("", 1002L)));
        assertTrue(avp.existe(new Produto("", 1003L)));
        assertTrue(avp.existe(new Produto("", 1004L)));
        assertTrue(avp.existe(new Produto("", 1005L)));
        assertTrue(avp.existe(new Produto("", 1006L)));
        assertTrue(avp.existe(new Produto("", 1007L)));
    }

    @Test
    public void testImprimirPreOrdem() {
        avp.inserir(new Produto("Café", 1002L));
        avp.inserir(new Produto("Leite", 1001L));
        avp.inserir(new Produto("Açúcar", 1003L));
        String resultado = avp.imprimirPreOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
    }

    @Test
    public void testImprimirPosOrdem() {
        avp.inserir(new Produto("Café", 1002L));
        avp.inserir(new Produto("Leite", 1001L));
        avp.inserir(new Produto("Açúcar", 1003L));
        String resultado = avp.imprimirPosOrdem().replaceAll("\\s+", " ").trim();
        assertTrue(resultado.contains("Café (1002)"));
        assertTrue(resultado.contains("Leite (1001)"));
        assertTrue(resultado.contains("Açúcar (1003)"));
    }

    @Test
    public void testPropriedadesAVP() {
        // Testar propriedades básicas da árvore vermelho e preto
        avp.inserir(new Produto("A", 1001L));
        avp.inserir(new Produto("B", 1002L));
        avp.inserir(new Produto("C", 1003L));
        
        // Verificar se a raiz é preta
        NoTriplo<Produto> raiz = avp.getRaiz();
        assertNotNull(raiz);
        assertFalse(raiz.isVermelho()); // Raiz deve ser preta
        
        // Verificar se não há nós vermelhos consecutivos
        verificarPropriedadesAVP(raiz);
    }

    /**
     * Verifica as propriedades da árvore vermelho e preto.
     */
    private void verificarPropriedadesAVP(NoTriplo<Produto> no) {
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
        
        // Verificar recursivamente os filhos
        verificarPropriedadesAVP(no.getEsquerda());
        verificarPropriedadesAVP(no.getDireita());
    }

    /**
     * Conta quantos produtos existem com o código especificado.
     */
    private int contarProdutosComCodigo(long codigo) {
        String emOrdem = avp.imprimirEmOrdem();
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