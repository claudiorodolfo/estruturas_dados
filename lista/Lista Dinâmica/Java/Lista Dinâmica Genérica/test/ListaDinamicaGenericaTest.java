import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes para a implementação de ListaDinamicaGenerica.
 * Esta classe contém testes unitários para verificar o funcionamento
 * correto de todos os métodos da lista dinâmica genérica.
 * 
 * @author Claudio Rodolfo Sousa de Oliveira
 * @version 1.1
 */
public class ListaDinamicaGenericaTest {

    /**
     * Testa a funcionalidade da lista com diferentes tipos de dados.
     * Verifica se a lista funciona corretamente com tipos Integer e Double.
     */
    @Test
    public void testListaComDiferentesTipos() {
        Listavel<Integer> listaInt = new ListaDinamicaGenerica<>(2);
        listaInt.anexar(1);
        listaInt.anexar(2);
        assertEquals(Integer.valueOf(1), listaInt.selecionar(0));
        
        Listavel<Double> listaDouble = new ListaDinamicaGenerica<>(2);
        listaDouble.anexar(1.5);
        listaDouble.anexar(2.5);
        assertEquals(Double.valueOf(1.5), listaDouble.selecionar(0));
    }

    /**
     * Testa o construtor padrão da lista.
     * Verifica se a lista é criada com tamanho padrão de 10 elementos
     * e se lança exceção ao tentar inserir além da capacidade.
     */
    @Test
    public void testConstrutorPadrao() {
        Listavel<String> lista = new ListaDinamicaGenerica<>();
        // Deve permitir 10 elementos
        for (int i = 0; i < 10; i++) {
            lista.anexar("Elemento" + i);
        }
        assertTrue(lista.estaCheia());
        try {
            lista.anexar("Extra");
            fail("Deveria ter lançado exceção de lista cheia!");
        } catch (OverflowException e) {
            assertEquals("Lista Cheia!", e.getMessage());
        }
    }

    /**
     * Testa o construtor com tamanho personalizado.
     * Verifica se a lista é criada com o tamanho especificado
     * e se lança exceção ao tentar inserir além da capacidade.
     */
    @Test
    public void testConstrutorComTamanhoPersonalizado() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(5);
        // Deve permitir 5 elementos
        for (int i = 0; i < 5; i++) {
            lista.anexar("Elemento" + i);
        }
        assertTrue(lista.estaCheia());
        try {
            lista.anexar("Extra");
            fail("Deveria ter lançado exceção de lista cheia!");
        } catch (OverflowException e) {
            assertEquals("Lista Cheia!", e.getMessage());
        }
    }

    /**
     * Testa o método anexar.
     * Verifica se os elementos são inseridos corretamente no final da lista.
     */
    @Test
    public void testAnexar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("A", lista.selecionar(0));
        assertEquals("B", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    /**
     * Testa o método inserir.
     * Verifica se os elementos são inseridos corretamente em posições específicas.
     */
    @Test
    public void testInserir() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.inserir(0, "A");
        lista.inserir(1, "C");
        lista.inserir(1, "B");
        assertEquals("A", lista.selecionar(0));
        assertEquals("B", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    /**
     * Testa o método inserir com posição inválida.
     * Verifica se uma exceção é lançada ao tentar inserir em posição inválida.
     */
    @Test
    public void testInserirPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        try {
            lista.inserir(1, "A");
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    /**
     * Testa o método selecionar.
     * Verifica se os elementos são recuperados corretamente de suas posições.
     */
    @Test
    public void testSelecionar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("A", lista.selecionar(0));
        assertEquals("B", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    /**
     * Testa o método selecionar com posição inválida.
     * Verifica se uma exceção é lançada ao tentar selecionar posição inválida.
     */
    @Test
    public void testSelecionarPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        try {
            lista.selecionar(1);
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    /**
     * Testa o método selecionarTodos.
     * Verifica se todos os elementos são recuperados corretamente.
     */
    @Test
    public void testSelecionarTodos() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        Object[] elementos = lista.selecionarTodos();
        assertEquals(3, elementos.length);
        assertEquals("A", elementos[0]);
        assertEquals("B", elementos[1]);
        assertEquals("C", elementos[2]);
    }

    /**
     * Testa o método selecionarTodos em lista vazia.
     * Verifica se uma exceção é lançada ao tentar selecionar todos os elementos
     * de uma lista vazia.
     */
    @Test
    public void testSelecionarTodosVazia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        try {
            lista.selecionarTodos();
            fail("Deveria ter lançado exceção de lista vazia!");
        } catch (UnderflowException e) {
            assertEquals("Lista Vazia!", e.getMessage());
        }
    }

    /**
     * Testa o método atualizar.
     * Verifica se os elementos são atualizados corretamente em suas posições.
     */
    @Test
    public void testAtualizar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        lista.atualizar(1, "X");
        assertEquals("A", lista.selecionar(0));
        assertEquals("X", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
    }

    /**
     * Testa o método atualizar com posição inválida.
     * Verifica se uma exceção é lançada ao tentar atualizar posição inválida.
     */
    @Test
    public void testAtualizarPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        try {
            lista.atualizar(1, "X");
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    /**
     * Testa o método apagar.
     * Verifica se os elementos são removidos corretamente de suas posições.
     */
    @Test
    public void testApagar() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("B", lista.apagar(1));
        assertEquals("A", lista.selecionar(0));
        assertEquals("C", lista.selecionar(1));
    }

    /**
     * Testa o método apagar com posição inválida.
     * Verifica se uma exceção é lançada ao tentar apagar posição inválida.
     */
    @Test
    public void testApagarPosicaoInvalida() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        try {
            lista.apagar(1);
            fail("Deveria ter lançado exceção de índice inválido!");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Indice Invalido!", e.getMessage());
        }
    }

    /**
     * Testa o método estaCheia.
     * Verifica se o estado de cheia da lista é reportado corretamente.
     */
    @Test
    public void testEstaCheia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(2);
        assertFalse(lista.estaCheia());
        lista.anexar("A");
        assertFalse(lista.estaCheia());
        lista.anexar("B");
        assertTrue(lista.estaCheia());
    }

    /**
     * Testa o método estaVazia.
     * Verifica se o estado de vazia da lista é reportado corretamente.
     */
    @Test
    public void testEstaVazia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(2);
        assertTrue(lista.estaVazia());
        lista.anexar("A");
        assertFalse(lista.estaVazia());
        lista.apagar(0);
        assertTrue(lista.estaVazia());
    }

    /**
     * Testa o método imprimir.
     * Verifica se a representação em string da lista está correta.
     */
    @Test
    public void testImprimir() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(3);
        lista.anexar("A");
        lista.anexar("B");
        lista.anexar("C");
        assertEquals("[A,B,C]", lista.imprimir());
    }

    /**
     * Testa o método imprimir em lista vazia.
     * Verifica se a representação em string de uma lista vazia está correta.
     */
    @Test
    public void testImprimirVazia() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(2);
        assertEquals("[]", lista.imprimir());
    }

    /**
     * Testa operações combinadas na lista.
     * Verifica se múltiplas operações funcionam corretamente em sequência.
     */
    @Test
    public void testOperacoesCombinadas() {
        Listavel<String> lista = new ListaDinamicaGenerica<>(5);
        lista.anexar("A");
        lista.inserir(1, "B");
        lista.anexar("C");
        lista.atualizar(1, "X");
        assertEquals("A", lista.selecionar(0));
        assertEquals("X", lista.selecionar(1));
        assertEquals("C", lista.selecionar(2));
        assertEquals("[A,X,C]", lista.imprimir());
    }
} 