package br.edu.ifba.vdc.bsi.sortedlinkedlist;

import br.edu.ifba.vdc.bsi.sortedlinkedlist.list.sorting.SortedLinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortedLinkedListTest {

    @Test
    public void testAppendAndSort() {
        SortedLinkedList list = new SortedLinkedList();
        
        // Adiciona elementos desordenados
        list.append(5);
        list.append(2);
        list.append(8);
        list.append(1);
        list.append(9);
        list.append(3);
        
        // Verifica que não está ordenado
        assertEquals(2, list.select(1));
        assertEquals(8, list.select(2));
        
        // Ordena
        list.sort();
        
        // Verifica que está ordenado
        assertEquals(1, list.select(0));
        assertEquals(2, list.select(1));
        assertEquals(3, list.select(2));
        assertEquals(5, list.select(3));
        assertEquals(8, list.select(4));
        assertEquals(9, list.select(5));
    }
    
    @Test
    public void testEmptyList() {
        SortedLinkedList list = new SortedLinkedList();
        assertTrue(list.isEmpty());
        list.sort(); // Não deve lançar exceção
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testSingleElement() {
        SortedLinkedList list = new SortedLinkedList();
        list.append(42);
        list.sort();
        assertEquals(42, list.select(0));
    }
    
    @Test
    public void testAlreadySorted() {
        SortedLinkedList list = new SortedLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.sort();
        assertEquals(1, list.select(0));
        assertEquals(2, list.select(1));
        assertEquals(3, list.select(2));
        assertEquals(4, list.select(3));
    }
    
    @Test
    public void testReverseOrder() {
        SortedLinkedList list = new SortedLinkedList();
        list.append(5);
        list.append(4);
        list.append(3);
        list.append(2);
        list.append(1);
        list.sort();
        assertEquals(1, list.select(0));
        assertEquals(2, list.select(1));
        assertEquals(3, list.select(2));
        assertEquals(4, list.select(3));
        assertEquals(5, list.select(4));
    }
    
    @Test
    public void testPrint() {
        SortedLinkedList list = new SortedLinkedList();
        list.append(3);
        list.append(1);
        list.append(2);
        list.sort();
        String result = list.print();
        assertEquals("[1,2,3]", result);
    }
}

