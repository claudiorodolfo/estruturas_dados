import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class OperacoesConjuntoTest {

    private OperacoesConjunto<Integer> ocInteiros;
    private OperacoesConjunto<String> ocStrings;
    private ListaDinamicaGenerica<Integer> listaA, listaB, listaVazia; 
    private ListaDinamicaGenerica<String> listaC, listaD;

    @Before
    public void setUp() {
        ocInteiros = new OperacoesConjunto<>();     
        ocStrings = new OperacoesConjunto<>();
        
        // Criando listas com inteiros
        listaA = new ListaDinamicaGenerica<>();
        listaA.anexar(1);
        listaA.anexar(2);
        listaA.anexar(3);
        
        listaB = new ListaDinamicaGenerica<>();
        listaB.anexar(2);
        listaB.anexar(4);
        
        listaVazia = new ListaDinamicaGenerica<>();
        
        // Criando listas com textos
        listaC = new ListaDinamicaGenerica<>();
        listaC.anexar("a");
        listaC.anexar("b");
        listaC.anexar("c");
        
        listaD = new ListaDinamicaGenerica<>();
        listaD.anexar("b");
        listaD.anexar("d");
    }

    /**
     * Testa a operação de diferença entre duas listas de inteiros.
     * A diferença A-B deve retornar elementos que estão em A mas não em B.
     */
    @Test
    public void testDifferenceInteiros() {
        Integer[] esperado = {1, 3};
        assertArrayEquals(esperado, ocInteiros.difference(listaA, listaB));
    }

    /**
     * Testa a operação de diferença entre duas listas de strings.
     * A diferença C-D deve retornar elementos que estão em C mas não em D.
     */
    @Test
    public void testDifferenceStrings() {
        String[] esperado = {"a", "c"};
        assertArrayEquals(esperado, ocStrings.difference(listaC, listaD));
    }

    /**
     * Testa a operação de união entre duas listas de inteiros.
     * A união deve conter todos os elementos de ambas as listas sem repetição.
     */
    @Test
    public void testUnionInteiros() {
        Integer[] esperado = {1, 2, 3, 4};
        assertArrayEquals(esperado, ocInteiros.union(listaA, listaB));
    }

    /**
     * Testa a operação de união entre duas listas de strings.
     * A união deve conter todos os elementos de ambas as listas sem repetição.
     */
    @Test
    public void testUnionStrings() {
        String[] esperado = {"a", "b", "c", "d"};
        assertArrayEquals(esperado, ocStrings.union(listaC, listaD));
    }

    /**
     * Testa a operação de interseção entre duas listas de inteiros.
     * A interseção deve conter apenas elementos presentes em ambas as listas.
     */
    @Test
    public void testIntersectionInteiros() {
        Integer[] esperado = {2};
        assertArrayEquals(esperado, ocInteiros.intersection(listaA, listaB));
    }

    /**
     * Testa a operação de interseção entre duas listas de strings.
     * A interseção deve conter apenas elementos presentes em ambas as listas.
     */
    @Test
    public void testIntersectionStrings() {
        String[] esperado = {"b"};
        assertArrayEquals(esperado, ocStrings.intersection(listaC, listaD));
    }

    /**
     * Testa operações com lista vazia.
     * Verifica o comportamento quando uma das listas está vazia.
     */
    @Test
    public void testOperacoesComListaVazia() {
        // União com lista vazia
        assertArrayEquals(listaA.selecionarTodos(), ocInteiros.union(listaA, listaVazia));
        
        // Diferença com lista vazia
        assertArrayEquals(listaA.selecionarTodos(), ocInteiros.difference(listaA, listaVazia));
        
        // Interseção com lista vazia
        assertArrayEquals(new Integer[]{}, ocInteiros.intersection(listaA, listaVazia));
    }

    /**
     * Testa operações com listas idênticas.
     * Verifica o comportamento quando as listas contêm os mesmos elementos.
     */
    @Test
    public void testOperacoesComListasIdenticas() {
        ListaDinamicaGenerica<Integer> listaCopia = new ListaDinamicaGenerica<>();
        listaCopia.anexar(1);
        listaCopia.anexar(2);
        listaCopia.anexar(3);

        // União de listas idênticas
        assertArrayEquals(listaA.selecionarTodos(), ocInteiros.union(listaA, listaCopia));
        
        // Diferença de listas idênticas
        assertArrayEquals(new Integer[]{}, ocInteiros.difference(listaA, listaCopia));
        
        // Interseção de listas idênticas
        assertArrayEquals(listaA.selecionarTodos(), ocInteiros.intersection(listaA, listaCopia));
    }

    /**
     * Testa o comportamento quando uma das listas é nula.
     * Deve lançar IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOperacoesComListaNula() {
        ocInteiros.union(listaA, null);
    }
}