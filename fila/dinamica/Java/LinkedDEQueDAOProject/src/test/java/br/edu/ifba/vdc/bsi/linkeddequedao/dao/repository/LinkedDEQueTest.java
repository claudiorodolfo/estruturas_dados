package br.edu.ifba.vdc.bsi.linkeddequedao.dao.repository;

import br.edu.ifba.vdc.bsi.linkeddequedao.dao.repository.LinkedDEQue;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

/**
 * Testes unitários para a classe LinkedDEQue.
 * Testa todos os métodos e comportamentos da fila com dupla terminação.
 */
public class LinkedDEQueTest {

    private LinkedDEQue<String> queue;
    private LinkedDEQue<Integer> intQueue;

    @Before
    public void setUp() {
        queue = new LinkedDEQue<>();
        intQueue = new LinkedDEQue<>(3);
    }

    @Test
    public void testDefaultConstructor() {
        LinkedDEQue<String> defaultQueue = new LinkedDEQue<>();
        assertTrue(defaultQueue.isEmpty());
        assertFalse(defaultQueue.isFull());
    }

    @Test
    public void testConstructorWithCapacity() {
        LinkedDEQue<String> customQueue = new LinkedDEQue<>(5);
        assertTrue(customQueue.isEmpty());
        assertFalse(customQueue.isFull());
    }

    @Test
    public void testEnqueueAndFront() {
        String element = "test";
        queue.enqueue(element);
        
        assertFalse(queue.isEmpty());
        assertEquals(element, queue.front());
    }

    @Test
    public void testEnqueueAndDequeue() {
        String element = "test";
        queue.enqueue(element);
        
        String dequeued = queue.dequeue();
        assertEquals(element, dequeued);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testBeginEnqueueAndFront() {
        String element = "test";
        queue.beginEnqueue(element);
        
        assertFalse(queue.isEmpty());
        assertEquals(element, queue.front());
    }

    @Test
    public void testBeginEnqueueAndDequeue() {
        String element = "test";
        queue.beginEnqueue(element);
        
        String dequeued = queue.dequeue();
        assertEquals(element, dequeued);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testMultipleEnqueueAndDequeue() {
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        
        assertEquals("first", queue.dequeue());
        assertEquals("second", queue.dequeue());
        assertEquals("third", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testFrontDoesNotRemove() {
        queue.enqueue("test");
        queue.enqueue("another");
        
        assertEquals("test", queue.front());
        assertEquals("test", queue.front()); // Should still be there
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testRear() {
        queue.enqueue("first");
        queue.enqueue("second");
        
        assertEquals("second", queue.rear());
    }

    @Test
    public void testBeginUpdate() {
        queue.enqueue("original");
        queue.beginUpdate("updated");
        
        assertEquals("updated", queue.front());
    }

    @Test
    public void testEndUpdate() {
        queue.enqueue("first");
        queue.enqueue("original");
        queue.endUpdate("updated");
        
        assertEquals("updated", queue.rear());
    }

    @Test
    public void testEndDequeue() {
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        
        assertEquals("third", queue.endDequeue());
        assertEquals("second", queue.endDequeue());
        assertEquals("first", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        
        queue.enqueue("test");
        assertFalse(queue.isEmpty());
        
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(intQueue.isFull());
        
        intQueue.enqueue(1);
        assertFalse(intQueue.isFull());
        
        intQueue.enqueue(2);
        assertFalse(intQueue.isFull());
        
        intQueue.enqueue(3);
        assertTrue(intQueue.isFull());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueOnEmptyQueue() {
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testEndDequeueOnEmptyQueue() {
        queue.endDequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testFrontOnEmptyQueue() {
        queue.front();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRearOnEmptyQueue() {
        queue.rear();
    }

    @Test(expected = NoSuchElementException.class)
    public void testBeginUpdateOnEmptyQueue() {
        queue.beginUpdate("test");
    }

    @Test(expected = NoSuchElementException.class)
    public void testEndUpdateOnEmptyQueue() {
        queue.endUpdate("test");
    }

    @Test(expected = NoSuchElementException.class)
    public void testEnqueueOnFullQueue() {
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        intQueue.enqueue(4); // Should throw exception
    }

    @Test(expected = NoSuchElementException.class)
    public void testBeginEnqueueOnFullQueue() {
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        intQueue.beginEnqueue(4); // Should throw exception
    }

    @Test
    public void testPrintEmpty() {
        assertEquals("[]", queue.print());
    }

    @Test
    public void testPrintSingleElement() {
        queue.enqueue("single");
        assertEquals("[single]", queue.print());
    }

    @Test
    public void testPrintMultipleElements() {
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        assertEquals("[first,second,third]", queue.print());
    }

    @Test
    public void testPrintEndToBeginEmpty() {
        assertEquals("[]", queue.printEndToBegin());
    }

    @Test
    public void testPrintEndToBeginSingleElement() {
        queue.enqueue("single");
        assertEquals("[single]", queue.printEndToBegin());
    }

    @Test
    public void testPrintEndToBeginMultipleElements() {
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        assertEquals("[third,second,first]", queue.printEndToBegin());
    }

    @Test
    public void testQueueOrder() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        
        // Queue should be FIFO (First In, First Out)
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
    }

    @Test
    public void testQueueWithNullValues() {
        queue.enqueue(null);
        assertNull(queue.front());
        assertNull(queue.dequeue());
    }

    @Test
    public void testLargeQueue() {
        LinkedDEQue<Integer> largeQueue = new LinkedDEQue<>(1000);
        
        // Enqueue 1000 elements
        for (int i = 0; i < 1000; i++) {
            largeQueue.enqueue(i);
        }
        
        assertTrue(largeQueue.isFull());
        assertEquals(Integer.valueOf(0), largeQueue.front());
        assertEquals(Integer.valueOf(999), largeQueue.rear());
        
        // Dequeue all elements
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.valueOf(i), largeQueue.dequeue());
        }
        
        assertTrue(largeQueue.isEmpty());
    }

    @Test
    public void testQueueWithDifferentTypes() {
        LinkedDEQue<Double> doubleQueue = new LinkedDEQue<>(2);
        doubleQueue.enqueue(3.14);
        doubleQueue.enqueue(2.71);
        
        assertEquals(Double.valueOf(3.14), doubleQueue.front());
        assertEquals(Double.valueOf(2.71), doubleQueue.rear());
        assertEquals(Double.valueOf(3.14), doubleQueue.dequeue());
        assertEquals(Double.valueOf(2.71), doubleQueue.dequeue());
    }

    @Test
    public void testMixedOperations() {
        // Test mixed enqueue operations
        queue.beginEnqueue("first");
        queue.enqueue("second");
        queue.beginEnqueue("third");
        
        assertEquals("third", queue.front());
        assertEquals("second", queue.rear());
        
        // Test mixed dequeue operations
        assertEquals("third", queue.dequeue());
        assertEquals("second", queue.endDequeue());
        assertEquals("first", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testMixedUpdateOperations() {
        queue.enqueue("original1");
        queue.enqueue("original2");
        
        queue.beginUpdate("updated1");
        queue.endUpdate("updated2");
        
        assertEquals("updated1", queue.front());
        assertEquals("updated2", queue.rear());
    }
}
