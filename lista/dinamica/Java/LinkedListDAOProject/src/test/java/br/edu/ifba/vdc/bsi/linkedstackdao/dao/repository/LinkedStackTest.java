package br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

/**
 * Testes unitários para a classe LinkedStack.
 * Testa todos os métodos e comportamentos da pilha dinâmica.
 */
public class LinkedStackTest {

    private LinkedStack<String> stack;
    private LinkedStack<Integer> intStack;

    @Before
    public void setUp() {
        stack = new LinkedStack<>();
        intStack = new LinkedStack<>(3);
    }

    @Test
    public void testDefaultConstructor() {
        LinkedStack<String> defaultStack = new LinkedStack<>();
        assertTrue(defaultStack.isEmpty());
        assertFalse(defaultStack.isFull());
    }

    @Test
    public void testConstructorWithCapacity() {
        LinkedStack<String> customStack = new LinkedStack<>(5);
        assertTrue(customStack.isEmpty());
        assertFalse(customStack.isFull());
    }

    @Test
    public void testPushAndPeek() {
        String element = "test";
        stack.push(element);
        
        assertFalse(stack.isEmpty());
        assertEquals(element, stack.peek());
    }

    @Test
    public void testPushAndPop() {
        String element = "test";
        stack.push(element);
        
        String popped = stack.pop();
        assertEquals(element, popped);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testMultiplePushAndPop() {
        stack.push("first");
        stack.push("second");
        stack.push("third");
        
        assertEquals("third", stack.pop());
        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeekDoesNotRemove() {
        stack.push("test");
        stack.push("another");
        
        assertEquals("another", stack.peek());
        assertEquals("another", stack.peek()); // Should still be there
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testUpdate() {
        stack.push("original");
        stack.update("updated");
        
        assertEquals("updated", stack.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        
        stack.push("test");
        assertFalse(stack.isEmpty());
        
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(intStack.isFull());
        
        intStack.push(1);
        assertFalse(intStack.isFull());
        
        intStack.push(2);
        assertFalse(intStack.isFull());
        
        intStack.push(3);
        assertTrue(intStack.isFull());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeekOnEmptyStack() {
        stack.peek();
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateOnEmptyStack() {
        stack.update("test");
    }

    @Test(expected = NoSuchElementException.class)
    public void testPushOnFullStack() {
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        intStack.push(4); // Should throw exception
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", stack.toString());
    }

    @Test
    public void testToStringSingleElement() {
        stack.push("single");
        assertEquals("[single]", stack.toString());
    }

    @Test
    public void testToStringMultipleElements() {
        stack.push("first");
        stack.push("second");
        stack.push("third");
        assertEquals("[third,second,first]", stack.toString());
    }

    @Test
    public void testStackOrder() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        
        // Stack should be LIFO (Last In, First Out)
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
    }

    @Test
    public void testStackWithNullValues() {
        stack.push(null);
        assertNull(stack.peek());
        assertNull(stack.pop());
    }

    @Test
    public void testLargeStack() {
        LinkedStack<Integer> largeStack = new LinkedStack<>(1000);
        
        // Push 1000 elements
        for (int i = 0; i < 1000; i++) {
            largeStack.push(i);
        }
        
        assertTrue(largeStack.isFull());
        assertEquals(Integer.valueOf(999), largeStack.peek());
        
        // Pop all elements
        for (int i = 999; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), largeStack.pop());
        }
        
        assertTrue(largeStack.isEmpty());
    }

    @Test
    public void testStackWithDifferentTypes() {
        LinkedStack<Double> doubleStack = new LinkedStack<>(2);
        doubleStack.push(3.14);
        doubleStack.push(2.71);
        
        assertEquals(Double.valueOf(2.71), doubleStack.peek());
        assertEquals(Double.valueOf(2.71), doubleStack.pop());
        assertEquals(Double.valueOf(3.14), doubleStack.pop());
    }
}
