package br.edu.ifba.vdc.bsi.stackdao.dao.repository;

import br.edu.ifba.vdc.bsi.stackdao.model.Book;

public class BookArrayStack implements BookStackable {
    private Book[] books;
    private int topPointer;

    public BookArrayStack() {
        this(10);
    }

    public BookArrayStack(int tamanho) {
        books = new Book[tamanho];
        topPointer = -1;
    }
    
    @Override
    public void push(Book book) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
            return;
        }
        topPointer++; 
        books[topPointer] = book;
    }

    @Override
    public Book pop() {
        if (isEmpty()) {
            System.out.println("Pilha vazia!");
            return null;
        }
        Book temp = books[topPointer];
        topPointer--;
        return temp;

    }

    @Override
    public Book peek() {
        if (isEmpty()) {
         System.out.println("Pilha vazia!");
            return null;
        }
        return books[topPointer];
    }
    
    private boolean isEmpty() { 
        return topPointer == -1; 
    }

    private boolean isFull() {
        return topPointer == books.length - 1;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = topPointer; i >= 0; i--) {
            result += books[i];
            if (i != 0)
                result += ",\n";
        }
        return "[\n" + result + "\n]";
    }
}