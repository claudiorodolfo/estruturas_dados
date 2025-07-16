import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Testes JUnit para a implementação de Árvore B Genérica.
 * Testa todas as funcionalidades principais da árvore B.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ArvoreBGenericaTest {
    
    private ArvoreB<Integer> arvore;
    
    /**
     * Configuração inicial para cada teste.
     * Cria uma nova árvore B de ordem 3 antes de cada teste.
     */
    @Before
    public void setUp() {
        arvore = new ArvoreB<>(3);
    }
    
    /**
     * Limpeza após cada teste.
     */
    @After
    public void tearDown() {
        arvore = null;
    }
    
    /**
     * Testa a inserção básica de elementos.
     */
    @Test
    public void testInsercaoBasica() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        
        assertTrue("Elemento 10 deve existir", arvore.existe(10));
        assertTrue("Elemento 5 deve existir", arvore.existe(5));
        assertTrue("Elemento 15 deve existir", arvore.existe(15));
        assertFalse("Elemento 20 não deve existir", arvore.existe(20));
        
        String resultado = arvore.imprimirEmOrdem();
        assertEquals("Elementos devem estar em ordem", "5 10 15 ", resultado);
    }
    
    /**
     * Testa a inserção com divisão de nós.
     */
    @Test
    public void testInsercaoComDivisao() {
        // Inserir elementos suficientes para causar divisão
        for (int i = 1; i <= 10; i++) {
            arvore.inserir(i);
        }
        
        // Verificar se todos os elementos foram inseridos
        for (int i = 1; i <= 10; i++) {
            assertTrue("Elemento " + i + " deve existir", arvore.existe(i));
        }
        
        String resultado = arvore.imprimirEmOrdem();
        String esperado = "1 2 3 4 5 6 7 8 9 10 ";
        assertEquals("Elementos devem estar em ordem após divisão", esperado, resultado);
    }
    
    /**
     * Testa a busca de elementos.
     */
    @Test
    public void testBusca() {
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        
        // Testar busca de elementos existentes
        NoArvoreB<Integer> resultado = arvore.buscar(10);
        assertNotNull("Busca de 10 deve retornar um nó", resultado);
        assertTrue("Nó deve conter o elemento 10", resultado.chaves.contains(10));
        
        // Testar busca de elementos inexistentes
        resultado = arvore.buscar(100);
        assertNull("Busca de 100 deve retornar null", resultado);
    }
    
    /**
     * Testa a remoção de elementos.
     */
    @Test
    public void testRemocao() {
        // Inserir elementos
        for (int i = 1; i <= 10; i++) {
            arvore.inserir(i);
        }
        
        // Remover elemento
        Integer removido = arvore.apagar(5);
        assertEquals("Elemento removido deve ser 5", Integer.valueOf(5), removido);
        
        // Verificar se foi removido
        assertFalse("Elemento 5 não deve mais existir", arvore.existe(5));
        
        // Verificar se outros elementos ainda existem
        assertTrue("Elemento 1 deve ainda existir", arvore.existe(1));
        assertTrue("Elemento 10 deve ainda existir", arvore.existe(10));
        
        String resultado = arvore.imprimirEmOrdem();
        String esperado = "1 2 3 4 6 7 8 9 10 ";
        assertEquals("Elementos devem estar em ordem após remoção", esperado, resultado);
    }
    
    /**
     * Testa a remoção de múltiplos elementos.
     */
    @Test
    public void testRemocaoMultipla() {
        // Inserir elementos
        for (int i = 1; i <= 15; i++) {
            arvore.inserir(i);
        }
        
        // Remover vários elementos
        arvore.apagar(5);
        arvore.apagar(10);
        arvore.apagar(15);
        
        // Verificar se foram removidos
        assertFalse("Elemento 5 não deve existir", arvore.existe(5));
        assertFalse("Elemento 10 não deve existir", arvore.existe(10));
        assertFalse("Elemento 15 não deve existir", arvore.existe(15));
        
        // Verificar se outros elementos ainda existem
        assertTrue("Elemento 1 deve existir", arvore.existe(1));
        assertTrue("Elemento 7 deve existir", arvore.existe(7));
        assertTrue("Elemento 14 deve existir", arvore.existe(14));
    }
    
    /**
     * Testa a limpeza da árvore.
     */
    @Test
    public void testLimpar() {
        // Inserir elementos
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        
        // Limpar árvore
        arvore.limpar();
        
        // Verificar se está vazia
        assertFalse("Elemento 10 não deve existir após limpeza", arvore.existe(10));
        assertFalse("Elemento 5 não deve existir após limpeza", arvore.existe(5));
        assertFalse("Elemento 15 não deve existir após limpeza", arvore.existe(15));
        
        String resultado = arvore.imprimirEmOrdem();
        assertEquals("Árvore deve estar vazia", "", resultado);
    }
    
    /**
     * Testa diferentes ordens de árvore.
     */
    @Test
    public void testDiferentesOrdens() {
        // Testar árvore de ordem 4
        ArvoreB<Integer> arvore4 = new ArvoreB<>(4);
        for (int i = 1; i <= 10; i++) {
            arvore4.inserir(i);
        }
        
        String resultado4 = arvore4.imprimirEmOrdem();
        String esperado4 = "1 2 3 4 5 6 7 8 9 10 ";
        assertEquals("Árvore ordem 4 deve manter elementos ordenados", esperado4, resultado4);
        
        // Testar árvore de ordem 6
        ArvoreB<Integer> arvore6 = new ArvoreB<>(6);
        for (int i = 1; i <= 10; i++) {
            arvore6.inserir(i);
        }
        
        String resultado6 = arvore6.imprimirEmOrdem();
        String esperado6 = "1 2 3 4 5 6 7 8 9 10 ";
        assertEquals("Árvore ordem 6 deve manter elementos ordenados", esperado6, resultado6);
    }
    
    /**
     * Testa a inserção de elementos duplicados.
     */
    @Test
    public void testElementosDuplicados() {
        arvore.inserir(10);
        arvore.inserir(10); // Tentar inserir duplicado
        
        // Verificar se o elemento existe
        assertTrue("Elemento 10 deve existir", arvore.existe(10));
        
        // A implementação atual permite duplicados, então verificamos se ambos foram inseridos
        String resultado = arvore.imprimirEmOrdem();
        assertEquals("Deve haver dois elementos 10", "10 10 ", resultado);
    }
    
    /**
     * Testa a remoção de elemento inexistente.
     */
    @Test
    public void testRemocaoElementoInexistente() {
        arvore.inserir(10);
        arvore.inserir(5);
        
        // Tentar remover elemento inexistente
        Integer removido = arvore.apagar(100);
        // A implementação atual retorna o valor mesmo quando não existe
        assertEquals("Remoção de elemento inexistente deve retornar o valor", Integer.valueOf(100), removido);
        
        // Verificar se elementos originais ainda existem
        assertTrue("Elemento 10 deve ainda existir", arvore.existe(10));
        assertTrue("Elemento 5 deve ainda existir", arvore.existe(5));
    }
    
    /**
     * Testa a obtenção da raiz.
     */
    @Test
    public void testGetRaiz() {
        assertNotNull("Raiz deve existir mesmo em árvore vazia", arvore.getRaiz());
        
        arvore.inserir(10);
        NoArvoreB<Integer> raiz = arvore.getRaiz();
        assertNotNull("Raiz deve existir após inserção", raiz);
        assertTrue("Raiz deve conter o elemento inserido", raiz.chaves.contains(10));
    }
    
    /**
     * Testa a impressão de árvore vazia.
     */
    @Test
    public void testImpressaoArvoreVazia() {
        String resultado = arvore.imprimirEmOrdem();
        assertEquals("Árvore vazia deve retornar string vazia", "", resultado);
    }
} 