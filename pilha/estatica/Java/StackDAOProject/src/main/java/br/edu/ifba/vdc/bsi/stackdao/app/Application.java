package br.edu.ifba.vdc.bsi.stackdao.app;

import br.edu.ifba.vdc.bsi.stackdao.dao.BookStackDAO;
import br.edu.ifba.vdc.bsi.stackdao.dao.impl.BookStackDAOImpl;
import br.edu.ifba.vdc.bsi.stackdao.model.Book;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        try {
            // instancia o DAO de pilha
            BookStackDAO stackDao = new BookStackDAOImpl();

            // empilha alguns livros
            stackDao.push(new Book(1L, "Dom Casmurro", "Machado de Assis",
                                   LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90));
            stackDao.push(new Book(2L, "O Cortiço", "Aluísio Azevedo",
                                   LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 24.90));

            // espia e desempilha
            Book topo = stackDao.peek();
            System.out.println("Topo da pilha: " + topo.getTitle());

            Book desempilhado = stackDao.pop();
            System.out.println("Desempilhei: " + desempilhado.getTitle());

            System.out.println("Tamanho atual da pilha: " + stackDao.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}