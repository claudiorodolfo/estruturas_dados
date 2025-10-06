package br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Testes unitários para a classe DoubleNode.
 * Testa todos os métodos getter e setter da classe DoubleNode.
 */
public class DoubleNodeTest {

    private DoubleNode<String> node;
    private DoubleNode<Integer> intNode;
    private final String TEST_DATA = "test data";
    private final Integer TEST_INT = 42;

    @Before
    public void setUp() {
        node = new DoubleNode<>();
        intNode = new DoubleNode<>();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(node.getData());
        assertNull(node.getPrevious());
        assertNull(node.getNext());
    }

    @Test
    public void testSetAndGetData() {
        node.setData(TEST_DATA);
        assertEquals(TEST_DATA, node.getData());
    }

    @Test
    public void testSetAndGetDataWithNull() {
        node.setData(null);
        assertNull(node.getData());
    }

    @Test
    public void testSetAndGetDataWithInteger() {
        intNode.setData(TEST_INT);
        assertEquals(TEST_INT, intNode.getData());
    }

    @Test
    public void testSetAndGetPrevious() {
        DoubleNode<String> previousNode = new DoubleNode<>();
        previousNode.setData("previous");
        
        node.setPrevious(previousNode);
        assertEquals(previousNode, node.getPrevious());
        assertEquals("previous", node.getPrevious().getData());
    }

    @Test
    public void testSetAndGetPreviousWithNull() {
        node.setPrevious(null);
        assertNull(node.getPrevious());
    }

    @Test
    public void testSetAndGetNext() {
        DoubleNode<String> nextNode = new DoubleNode<>();
        nextNode.setData("next");
        
        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
        assertEquals("next", node.getNext().getData());
    }

    @Test
    public void testSetAndGetNextWithNull() {
        node.setNext(null);
        assertNull(node.getNext());
    }

    @Test
    public void testChainedNodes() {
        DoubleNode<String> node1 = new DoubleNode<>();
        DoubleNode<String> node2 = new DoubleNode<>();
        DoubleNode<String> node3 = new DoubleNode<>();
        
        node1.setData("first");
        node2.setData("second");
        node3.setData("third");
        
        // Chain: node1 -> node2 -> node3
        node1.setNext(node2);
        node2.setPrevious(node1);
        node2.setNext(node3);
        node3.setPrevious(node2);
        
        // Test forward traversal
        assertEquals("first", node1.getData());
        assertEquals("second", node1.getNext().getData());
        assertEquals("third", node1.getNext().getNext().getData());
        
        // Test backward traversal
        assertEquals("third", node3.getData());
        assertEquals("second", node3.getPrevious().getData());
        assertEquals("first", node3.getPrevious().getPrevious().getData());
    }

    @Test
    public void testCircularReference() {
        DoubleNode<String> node1 = new DoubleNode<>();
        DoubleNode<String> node2 = new DoubleNode<>();
        
        node1.setData("first");
        node2.setData("second");
        
        // Create circular reference
        node1.setNext(node2);
        node2.setPrevious(node1);
        node2.setNext(node1);
        node1.setPrevious(node2);
        
        // Test circular traversal
        assertEquals("first", node1.getData());
        assertEquals("second", node1.getNext().getData());
        assertEquals("first", node1.getNext().getNext().getData());
        assertEquals("second", node1.getPrevious().getData());
        assertEquals("first", node1.getPrevious().getPrevious().getData());
    }

    @Test
    public void testNodeWithDifferentTypes() {
        DoubleNode<String> stringNode = new DoubleNode<>();
        DoubleNode<Integer> intNode = new DoubleNode<>();
        DoubleNode<Double> doubleNode = new DoubleNode<>();
        
        stringNode.setData("string");
        intNode.setData(123);
        doubleNode.setData(3.14);
        
        assertEquals("string", stringNode.getData());
        assertEquals(Integer.valueOf(123), intNode.getData());
        assertEquals(Double.valueOf(3.14), doubleNode.getData());
    }

    @Test
    public void testNodeWithNullData() {
        node.setData(null);
        assertNull(node.getData());
        
        node.setPrevious(null);
        node.setNext(null);
        
        assertNull(node.getPrevious());
        assertNull(node.getNext());
    }

    @Test
    public void testNodeIsolation() {
        DoubleNode<String> node1 = new DoubleNode<>();
        DoubleNode<String> node2 = new DoubleNode<>();
        
        node1.setData("data1");
        node2.setData("data2");
        
        // Initially isolated
        assertNull(node1.getPrevious());
        assertNull(node1.getNext());
        assertNull(node2.getPrevious());
        assertNull(node2.getNext());
        
        // Connect them
        node1.setNext(node2);
        node2.setPrevious(node1);
        
        // Test connection
        assertEquals(node2, node1.getNext());
        assertEquals(node1, node2.getPrevious());
        
        // Test data integrity
        assertEquals("data1", node1.getData());
        assertEquals("data2", node2.getData());
    }
}
