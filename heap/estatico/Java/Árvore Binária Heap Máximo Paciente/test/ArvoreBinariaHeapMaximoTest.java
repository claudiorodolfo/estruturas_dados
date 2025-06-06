import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes para a implementação de Árvore Binária Heap Máximo com Pacientes.
 * Esta classe contém testes unitários para verificar o funcionamento
 * correto de todas as operações do heap com objetos do tipo Paciente.
 * Em um heap máximo, pacientes com maior número de prioridade têm precedência.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ArvoreBinariaHeapMaximoTest {
    
    private Amontoavel<Paciente> heap;
    private Paciente pJoao, pMaria, pPedro, pAna, pLucas;
    
    @Before
    public void setUp() {
        // Este método será executado antes de cada @Test
        heap = new ArvoreBinariaHeapMaximo<>(5);
        
        // Criando pacientes com diferentes prioridades
        pJoao = new Paciente("João", 30, 1);
        pMaria = new Paciente("Maria", 25, 2);
        pPedro = new Paciente("Pedro", 40, 2);
        pAna = new Paciente("Ana", 35, 3);
        pLucas = new Paciente("Lucas", 28, 3);
    }
    
    /**
     * Testa a inserção de pacientes no heap.
     * Verifica se os pacientes são inseridos corretamente e se a propriedade
     * de heap máximo é mantida baseada na prioridade (maior número = maior prioridade).
     */
    @Test
    public void testInserir() {
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        heap.inserir(pMaria);        
        assertEquals("[Paciente{nome='Lucas', idade=28, prioridade=3}," +
                    "Paciente{nome='Maria', idade=25, prioridade=2}," +
                    "Paciente{nome='Pedro', idade=40, prioridade=2}," +
                    "Paciente{nome='Joao', idade=30, prioridade=1}]", 
                    heap.imprimir());
    }

   /**
     * Testa a extração do paciente com maior prioridade do heap.
     * Verifica se o paciente correto é extraído e se a propriedade
     * de heap máximo é mantida após a extração.
     */
    @Test
    public void testExtrair() {
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        heap.inserir(pMaria); 
        
        assertEquals(pLucas, heap.extrair());
        assertEquals("[Paciente{nome='Pedro', idade=40, prioridade=2}," +
                    "Paciente{nome='Maria', idade=25, prioridade=2}," +             
                    "Paciente{nome='Joao', idade=30, prioridade=1}]",
                    heap.imprimir());
    }

    /**
     * Testa a obtenção do paciente com maior prioridade sem removê-lo.
     * Verifica se o paciente correto é retornado e se o heap
     * permanece inalterado.
     */
    @Test
    public void testObterRaiz() {
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        
        assertEquals(pLucas, heap.obterRaiz()); 
        assertEquals("[Paciente{nome='Lucas', idade=28, prioridade=3}," +
                    "Paciente{nome='João', idade=30, prioridade=1}," +
                    "Paciente{nome='Pedro', idade=40, prioridade=2}]",           
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
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        heap.inserir(pMaria);  
        heap.inserir(pAna);  
        heap.inserir(new Paciente("Extra", 20, 6)); // Deve lançar exceção
    }

    /**
     * Testa a verificação de heap vazio.
     * Verifica se o método estaVazia() retorna o valor correto.
     */
    @Test
    public void testEstaVazia() {
        assertTrue(heap.estaVazia());
        heap.inserir(pJoao);
        assertFalse(heap.estaVazia());
    }
    
    /**
     * Testa a verificação de heap cheio.
     * Verifica se o método estaCheia() retorna o valor correto.
     */
    @Test
    public void testEstaCheia() {
        assertFalse(heap.estaCheia());
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        heap.inserir(pMaria);  
        heap.inserir(pAna);  
        assertTrue(heap.estaCheia());
    }

    /**
     * Testa a impressão do heap.
     * Verifica se a representação em string está correta.
     */
    @Test
    public void testImprimir() {
        assertEquals("[]", heap.imprimir());
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        assertEquals("[Paciente{nome='Lucas', idade=28, prioridade=3}," +
                    "Paciente{nome='João', idade=30, prioridade=1}," +
                    "Paciente{nome='Pedro', idade=40, prioridade=2}]",           
                    heap.imprimir());
    }
    
    /**
     * Testa a manutenção da propriedade de heap máximo após múltiplas operações.
     * Verifica se o heap mantém a propriedade após inserções e extrações,
     * priorizando pacientes com maior número de prioridade.
     */
    @Test
    public void testPropriedadeHeapMaximo() {
        heap.inserir(pJoao);
        heap.inserir(pPedro);
        heap.inserir(pLucas);
        heap.inserir(pMaria);  
        heap.inserir(pAna);  
        
        assertEquals(pLucas, heap.extrair()); // Prioridade (mais alta)
        assertEquals(pAna, heap.extrair());
        assertEquals(pPedro, heap.extrair());
        assertEquals(pMaria, heap.extrair());
        assertEquals(pJoao, heap.extrair()); // Prioridade (mais baixa)
    }
}