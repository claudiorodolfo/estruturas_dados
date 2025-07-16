import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArvoreBGenericaTest {
    private ArvoreBGenerica<Integer> arvore;

    @BeforeEach
    public void setup() {
        arvore = new ArvoreBGenerica<>(2); // ordem 2, árvore B mínima
    }

    @Test
    public void testInsercaoEBusca() {
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(5);

        NoArvoreB<Integer> no = arvore.buscar(10);
        assertNotNull(no);
        assertTrue(no.chaves.contains(10));

        NoArvoreB<Integer> noNaoExistente = arvore.buscar(100);
        assertNull(noNaoExistente);
    }

    @Test
    public void testRemocaoFolha() {
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(5);

        arvore.remover(20);
        assertNull(arvore.buscar(20));
        assertNotNull(arvore.buscar(10));
        assertNotNull(arvore.buscar(5));
    }

    @Test
    public void testRemocaoInterna() {
        // Insere vários para garantir nós internos
        int[] valores = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int v : valores) {
            arvore.inserir(v);
        }

        // Remove uma chave interna (com filhos)
        arvore.remover(6);
        assertNull(arvore.buscar(6));
        assertNotNull(arvore.buscar(7));
        assertNotNull(arvore.buscar(5));
    }

    @Test
    public void testInsercaoRemocaoComplexa() {
        for (int i = 1; i <= 20; i++) {
            arvore.inserir(i);
        }

        for (int i = 1; i <= 20; i += 2) {
            arvore.remover(i);
        }

        for (int i = 1; i <= 20; i++) {
            NoArvoreB<Integer> no = arvore.buscar(i);
            if (i % 2 == 1) {
                assertNull(no, "Esperava chave removida: " + i);
            } else {
                assertNotNull(no, "Esperava chave presente: " + i);
            }
        }
    }
}
