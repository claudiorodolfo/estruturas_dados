package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;

public class BookDAOLinkedStack implements BookDAO {

    private Stackable<Book> books = new LinkedStack<>(20);

    @Override
    public void addBook(Book book) {
        books.push(book);
    }
  
    @Override
    public Book getBook(Long id){
        return books.peek();
    }

    @Override
    public void updateBook(Book book) {
        books.update(book);
    }
    
    @Override
    public Book deleteBook(Long id) {
        return books.pop();
    }

    @Override
    public Book[] sortBooks() {
        return null;
    }
    
    @Override
    public Book[] getAllBooks() {
        return null;
    }

    @Override
    public String printBooks() {
        return books.toString();
    }
}