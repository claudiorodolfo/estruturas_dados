//Executar todos os comandos dentro da pasta "Fila Dinâmica Genérica"
//compilar fonte: javac src/*.java -d bin

//compilar teste: javac -cp .;bin;lib/junit-4.13.2.jar -d test test/FilaDinamicaDuplaTerminacaoGenericaTest.java
//executar teste: java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FilaDinamicaDuplaTerminacaoGenericaTest
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes para a implementação de Árvore Binária Heap Máximo.
 * Esta classe contém testes unitários para verificar o funcionamento
 * correto de todas as operações do heap.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ArvoreBinariaHeapMaximoTest {
   
    private Amontoavel heap;

    @Before
    public void setUp() {
        // Este método será executado antes de cada @Test
        heap = new ArvoreBinariaHeapMaximo(5);
    }

    /**
     * Testa a inserção de elementos no heap.
     * Ve ifica se os elementos são inseridos corretamente e se a propriedade
     * de heap máximo é mantida.
     */

    @Test
    public void testInserir() {
        heap.inserir(5L);
        heap.inserir(10L);
        heap.inserir(3L);
        heap.inserir(8L);
        
        assertEquals("[10,8,3,5]", heap.imprimir());
    }
    
    /**
     * Testa a extração do elemento máximo do heap.
     * Verifica se o elemento correto é extraído e se a propriedade
     * de heap máximo é mantida após a extração.
     */
    @Test
    public void testExtrair() {
        heap.inserir(5L);
        heap.inserir(10L);
        heap.inserir(3L);
        heap.inserir(8L);
        
        assertEquals(Long.valueOf(10), heap.extrair());
        assertEquals("[8,5,3]", heap.imprimir());
    }
    
    /**
     * Testa a obtenção da raiz do heap sem removê-la.
     * Verifica se o elemento correto é retornado e se o heap
     * permanece inalterado.
     */
    @Test
    public void testObterRaiz() {
        heap.inserir(5L);
        heap.inserir(10L);
        heap.inserir(3L);
        
        assertEquals(Long.valueOf(10), heap.obterRaiz());
        assertEquals("[10,5,3]", heap.imprimir());
    }
    
    /**
     * Testa o comportamento do heap quando está vazio.
     * Verifica se as operações lançam as exceções apropriadas.
     */
    @Test(expected = UnderflowException.class)
    public void testHeapVazia() {
        heap.extrair();
    }
    
    /**
     * Testa o comportamento do heap quando está cheio.
     * Verifica se a inserção lança a exceção apropriada.
     */
    @Test(expected = OverflowException.class)
    public void testHeapCheia() {
        for (int i = 0; i < 4; i++) {
            heap.inserir(Long.valueOf(i));
        }
    }
    
    /**
     * Testa a verificação de heap vazio.
     * Verifica se o método estaVazia() retorna o valor correto.
     */
    @Test
    public void testEstaVazia() {
        assertTrue(heap.estaVazia());
        heap.inserir(5L);
        assertFalse(heap.estaVazia());
    }
    
    /**
     * Testa a verificação de heap cheio.
     * Verifica se o método estaCheia() retorna o valor correto.
     */
    @Test
    public void testEstaCheia() {
        assertFalse(heap.estaCheia());
        for (int i = 0; i < 5; i++) {
            heap.inserir(Long.valueOf(i));
        }
        assertTrue(heap.estaCheia());
    }
    
    /**
     * Testa a impressão do heap.
     * Verifica se a representação em string está correta.
     */
    @Test
    public void testImprimir() {
        assertEquals("[]", heap.imprimir());
        heap.inserir(5L);
        heap.inserir(10L);
        heap.inserir(3L);
        assertEquals("[10,5,3]", heap.imprimir());
    }
    
    /**
     * Testa a manutenção da propriedade de heap máximo após múltiplas operações.
     * Verifica se o heap mantém a propriedade após inserções e extrações.
     */
    @Test
    public void testPropriedadeHeapMaximo() {
        heap.inserir(5L);
        heap.inserir(10L);
        heap.inserir(3L);
        heap.inserir(8L);
        heap.inserir(1L);
        
        assertEquals(Long.valueOf(10), heap.extrair());
        assertEquals(Long.valueOf(8), heap.extrair());
        assertEquals(Long.valueOf(5), heap.extrair());
        assertEquals(Long.valueOf(3), heap.extrair());
        assertEquals(Long.valueOf(1), heap.extrair());
    }
}