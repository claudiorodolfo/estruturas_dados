package br.edu.ifba.vdc.bsi.arraystackdao.dao.repository;

public class ArrayStack<T> implements Stackable<T> {
    private T[] stack;
    private int topPointer;

    public ArrayStack() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int tamanho) {
        stack = (T[]) new Object[tamanho];
        topPointer = -1;
    }
    
    @Override
    public void push(T book) {
        if (isFull()) {
            IO.println("Pilha cheia!");
            return;
        }
        topPointer++; 
        stack[topPointer] = book;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            IO.println("Pilha vazia!");
            return null;
        }
        T temp = stack[topPointer];
        topPointer--;
        return temp;

    }

    @Override
    public T peek() {
        if (isEmpty()) {
         IO.println("Pilha vazia!");
            return null;
        }
        return stack[topPointer];
    }
    
    private boolean isEmpty() { 
        return topPointer == -1; 
    }

    private boolean isFull() {
        return topPointer == stack.length - 1;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = topPointer; i >= 0; i--) {
            result += stack[i];
            if (i != 0)
                result += ",\n";
        }
        return "[\n" + result + "\n]";
    }
}