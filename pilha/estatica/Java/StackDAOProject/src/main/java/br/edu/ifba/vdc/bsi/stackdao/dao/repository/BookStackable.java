package br.edu.ifba.vdc.bsi.stackdao.dao.repository;

import br.edu.ifba.vdc.bsi.stackdao.model.Book;
public interface BookStackable {
    void push(Book book);
    Book pop();
    Book peek();
}
