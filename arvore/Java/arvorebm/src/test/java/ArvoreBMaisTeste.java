package com.estruturasdados;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Testes unitários para a árvore B+ (ArvoreBMais).
 */
public class ArvoreBMaisTeste {
    
    private ArvoreBMais<Integer, String> arvore;
    
    @Before
    public void setUp() {
        arvore = new ArvoreBMais<>(3); // Ordem 3
    }
    
    @Test
    public void testInserirEBuscar() {
        arvore.inserir(10, "dez");
        arvore.inserir(5, "cinco");
        arvore.inserir(15, "quinze");
        
        assertEquals("dez", arvore.buscar(10));
        assertEquals("cinco", arvore.buscar(5));
        assertEquals("quinze", arvore.buscar(15));
        assertNull(arvore.buscar(20));
    }
    
    @Test
    public void testInserirMultiplosValores() {
        arvore.inserir(1, "um");
        arvore.inserir(2, "dois");
        arvore.inserir(3, "três");
        arvore.inserir(4, "quatro");
        arvore.inserir(5, "cinco");
        
        assertEquals("um", arvore.buscar(1));
        assertEquals("dois", arvore.buscar(2));
        assertEquals("três", arvore.buscar(3));
        assertEquals("quatro", arvore.buscar(4));
        assertEquals("cinco", arvore.buscar(5));
    }
    
    @Test
    public void testRemover() {
        arvore.inserir(10, "dez");
        arvore.inserir(5, "cinco");
        arvore.inserir(15, "quinze");
        
        assertEquals("cinco", arvore.remover(5));
        assertNull(arvore.buscar(5));
        assertEquals("dez", arvore.buscar(10));
        assertEquals("quinze", arvore.buscar(15));
    }
    
    @Test
    public void testRemoverInexistente() {
        arvore.inserir(10, "dez");
        assertNull(arvore.remover(20));
        assertEquals("dez", arvore.buscar(10));
    }
    
    @Test
    public void testObterTodosOsValores() {
        arvore.inserir(3, "três");
        arvore.inserir(1, "um");
        arvore.inserir(5, "cinco");
        arvore.inserir(2, "dois");
        arvore.inserir(4, "quatro");
        
        List<String> valores = arvore.obterTodosOsValores();
        assertEquals(5, valores.size());
        assertEquals("três", valores.get(2));
        assertEquals("um", valores.get(0));
        assertEquals("dois", valores.get(1));
        assertEquals("quatro", valores.get(3));
        assertEquals("cinco", valores.get(4));
    }
    
    @Test
    public void testTamanho() {
        assertEquals(0, arvore.tamanho());
        arvore.inserir(1, "um");
        assertEquals(1, arvore.tamanho());
        arvore.inserir(2, "dois");
        assertEquals(2, arvore.tamanho());
        arvore.remover(1);
        assertEquals(1, arvore.tamanho());
    }
    
    @Test
    public void testEstaVazia() {
        assertTrue(arvore.estaVazia());
        arvore.inserir(1, "um");
        assertFalse(arvore.estaVazia());
        arvore.remover(1);
        assertTrue(arvore.estaVazia());
    }
    
    @Test
    public void testLimpar() {
        arvore.inserir(1, "um");
        arvore.inserir(2, "dois");
        arvore.inserir(3, "três");
        assertFalse(arvore.estaVazia());
        assertEquals(3, arvore.tamanho());
        arvore.limpar();
        assertTrue(arvore.estaVazia());
        assertEquals(0, arvore.tamanho());
        assertNull(arvore.buscar(1));
    }
    
    @Test
    public void testToString() {
        arvore.inserir(1, "um");
        arvore.inserir(2, "dois");
        String resultado = arvore.toString();
        assertTrue(resultado.contains("ArvoreBMais"));
        assertTrue(resultado.contains("um"));
        assertTrue(resultado.contains("dois"));
    }
} 