package br.edu.ifba.vdc.bsi.arraystackdao.dao;

import br.edu.ifba.vdc.bsi.arraystackdao.dao.repository.ArrayStack;
import br.edu.ifba.vdc.bsi.arraystackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.arraystackdao.model.Book;

public class BookDAOArrayStack implements BookDAO {

    private Stackable books = new ArrayStack(20);

    @Override
    public void addBook(Book book) {
        books.push(book);
    }
  
    @Override
    public Book getBook(Long id){
        return (Book) books.peek();
    }

    @Override
    public void updateBook(Book book) {
        books.pop();
        books.push(book);
    }
    
    @Override
    public Book deleteBook(Long id) {
        return (Book) books.pop();
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