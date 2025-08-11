package br.edu.ifba.vdc.bsi.stackdao.dao;

import br.edu.ifba.vdc.bsi.stackdao.dao.repository.BookArrayStack;
import br.edu.ifba.vdc.bsi.stackdao.dao.repository.BookStackable;
import br.edu.ifba.vdc.bsi.stackdao.model.Book;

public class BookDAOArrayStack implements BookDAO {

    private BookStackable stack = new BookArrayStack(20);

    @Override
    public void addBook(Book book) {
        stack.push(book);
    }
  
    @Override
    public Book getBook(Long id){
        return stack.peek();
    }

    @Override
    public void updateBook(Book book) {
        stack.pop();
        stack.push(book);
    }
    
    @Override
    public Book deleteBook(Long id) {
        return stack.pop();
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
        return stack.toString();
    }
}