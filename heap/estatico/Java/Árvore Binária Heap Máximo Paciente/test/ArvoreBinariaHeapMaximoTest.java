import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes para a implementação de Árvore Binária Heap Máximo com Pacientes.
 * Esta classe contém testes unitários para verificar o funcionamento
 * correto de todas as operações do heap com objetos do tipo Paciente.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ArvoreBinariaHeapMaximoTest {
    
    private Amontoavel<Paciente> heap;
    private Paciente p1, p2, p3, p4, p5;
    
    @Before
    public void setUp() {
        // Este método será executado antes de cada @Test
        heap = new ArvoreBinariaHeapMaximo<>(5);
        
        // Criando pacientes com diferentes prioridades
        p1 = new Paciente("João", 30, 1);  // Prioridade 1 (mais alta)
        p2 = new Paciente("Maria", 25, 2); // Prioridade 2
        p3 = new Paciente("Pedro", 40, 3); // Prioridade 3
        p4 = new Paciente("Ana", 35, 4);   // Prioridade 4
        p5 = new Paciente("Lucas", 28, 5); // Prioridade 5 (mais baixa)
    }
    
    /**
     * Testa a inserção de pacientes no heap.
     * Verifica se os pacientes são inseridos corretamente e se a propriedade
     * de heap máximo é mantida baseada na prioridade.
     */
    @Test
    public void testInserir() {
        heap.inserir(p3);
        heap.inserir(p1);
        heap.inserir(p4);
        heap.inserir(p2);
        
        assertEquals("[Paciente{nome='João', idade=30, prioridade=1}, " +
                    "Paciente{nome='Maria', idade=25, prioridade=2}, " +
                    "Paciente{nome='Pedro', idade=40, prioridade=3}, " +
                    "Paciente{nome='Ana', idade=35, prioridade=4}]", 
                    heap.imprimir());
    }
    
    /**
     * Testa a extração do paciente com maior prioridade do heap.
     * Verifica se o paciente correto é extraído e se a propriedade
     * de heap máximo é mantida após a extração.
     */
    @Test
    public void testExtrair() {
        heap.inserir(p3);
        heap.inserir(p1);
        heap.inserir(p4);
        heap.inserir(p2);
        
        assertEquals(p1, heap.extrair());
        assertEquals("[Paciente{nome='Maria', idade=25, prioridade=2}, " +
                    "Paciente{nome='Pedro', idade=40, prioridade=3}, " +
                    "Paciente{nome='Ana', idade=35, prioridade=4}]", 
                    heap.imprimir());
    }
    
    /**
     * Testa a obtenção do paciente com maior prioridade sem removê-lo.
     * Verifica se o paciente correto é retornado e se o heap
     * permanece inalterado.
     */
    @Test
    public void testObterRaiz() {
        heap.inserir(p3);
        heap.inserir(p1);
        heap.inserir(p4);
        
        assertEquals(p1, heap.obterRaiz());
        assertEquals("[Paciente{nome='João', idade=30, prioridade=1}, " +
                    "Paciente{nome='Pedro', idade=40, prioridade=3}, " +
                    "Paciente{nome='Ana', idade=35, prioridade=4}]", 
                    heap.imprimir());
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
        heap.inserir(p1);
        heap.inserir(p2);
        heap.inserir(p3);
        heap.inserir(p4);
        heap.inserir(p5);
        heap.inserir(new Paciente("Extra", 20, 6)); // Deve lançar exceção
    }
    
    /**
     * Testa a verificação de heap vazio.
     * Verifica se o método estaVazia() retorna o valor correto.
     */
    @Test
    public void testEstaVazia() {
        assertTrue(heap.estaVazia());
        heap.inserir(p1);
        assertFalse(heap.estaVazia());
    }
    
    /**
     * Testa a verificação de heap cheio.
     * Verifica se o método estaCheia() retorna o valor correto.
     */
    @Test
    public void testEstaCheia() {
        assertFalse(heap.estaCheia());
        heap.inserir(p1);
        heap.inserir(p2);
        heap.inserir(p3);
        heap.inserir(p4);
        heap.inserir(p5);
        assertTrue(heap.estaCheia());
    }
    
    /**
     * Testa a impressão do heap.
     * Verifica se a representação em string está correta.
     */
    @Test
    public void testImprimir() {
        assertEquals("[]", heap.imprimir());
        heap.inserir(p1);
        heap.inserir(p2);
        heap.inserir(p3);
        assertEquals("[Paciente{nome='João', idade=30, prioridade=1}, " +
                    "Paciente{nome='Maria', idade=25, prioridade=2}, " +
                    "Paciente{nome='Pedro', idade=40, prioridade=3}]", 
                    heap.imprimir());
    }
    
    /**
     * Testa a manutenção da propriedade de heap máximo após múltiplas operações.
     * Verifica se o heap mantém a propriedade após inserções e extrações,
     * priorizando pacientes com menor número de prioridade.
     */
    @Test
    public void testPropriedadeHeapMaximo() {
        heap.inserir(p3);
        heap.inserir(p1);
        heap.inserir(p4);
        heap.inserir(p2);
        heap.inserir(p5);
        
        assertEquals(p1, heap.extrair()); // Prioridade 1
        assertEquals(p2, heap.extrair()); // Prioridade 2
        assertEquals(p3, heap.extrair()); // Prioridade 3
        assertEquals(p4, heap.extrair()); // Prioridade 4
        assertEquals(p5, heap.extrair()); // Prioridade 5
    }
}