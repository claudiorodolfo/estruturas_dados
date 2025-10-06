package br.edu.ifba.vdc.bsi.arraystackdao.dao.repository;

public interface Stackable<T> {
    void push(T book);
    T pop();
    T peek();
}
