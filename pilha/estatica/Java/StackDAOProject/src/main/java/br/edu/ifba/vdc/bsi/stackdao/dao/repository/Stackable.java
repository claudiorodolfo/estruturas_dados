package br.edu.ifba.vdc.bsi.stackdao.dao.repository;

public interface Stackable<T> {
    void push(T book);
    T pop();
    T peek();
}
