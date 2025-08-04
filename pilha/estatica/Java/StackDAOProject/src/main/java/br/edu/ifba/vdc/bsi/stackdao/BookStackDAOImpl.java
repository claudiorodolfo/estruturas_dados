package br.edu.ifba.vdc.bsi.stackdao;

public class BookStackDAOImpl implements BookStackDAO {
    private Book[] books = new Book[10];
    private int topPointer = -1;
    
    @Override
    public void push(Book book) {
        if (!isFull()) {
            System.out.println("Pilha cheia!");
            return;
        }
        topPointer++; 
        books[topPointer] = book;
    }

    @Override
    public Book pop() {
        if (!isEmpty()) {
            System.out.println("Pilha vazia!");
            return null;
        }
        Book temp = books[topPointer];
        topPointer--;
        return temp;

    }

    @Override
    public void update(Book book) {
        if (!isEmpty()) {
            System.out.println("Pilha vazia!");
            return;
        }
        books[topPointer] = book;
    }

    @Override
    public Book peek() {
        if (!isEmpty()) {
            System.out.println("Pilha vazia!");
            return null;
        }
        return books[topPointer];
    }

    @Override
    public boolean isEmpty() { 
        return topPointer == -1; 
    }

    @Override
    public int size() { 
        return topPointer + 1; 
    }

    @Override
    public boolean isFull() {
        return topPointer == books.length - 1;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = topPointer; i >= 0; i--) {
            result += books[i];
            if (i != 0)
                result += ",";
        }
        return "[" + result + "]";
    }
}