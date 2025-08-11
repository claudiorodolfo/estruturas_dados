package br.edu.ifba.vdc.bsi.stackdao.app;

import br.edu.ifba.vdc.bsi.stackdao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.stackdao.dao.BookDAOArrayStack;
import br.edu.ifba.vdc.bsi.stackdao.model.Book;
import java.time.LocalDate;

public class BookService {

    public static BookDAO getDBBook() {
        return new BookDAOArrayStack();
    }
    public static void main(String[] args) {
        BookDAO dbAcessor = BookService.getDBBook();

        Book b1 = new Book(1L, "Dom Casmurro", "Machado de Assis",
                    LocalDate.of(1899, 1, 1), "978-85-359-0277-8", 29.90);
        Book b2 = new Book(2L, "O Cortiço", "Aluísio Azevedo",
                    LocalDate.of(1890, 1, 1), "978-85-359-0271-6", 24.90);

        dbAcessor.addBook(b1);
        dbAcessor.addBook(b2);
        Book readBook = dbAcessor.getBook(null);
        System.out.println("Topo da pilha: " + readBook.getTitle());

     //   Book deleteBook = dbAcessor.deleteBook(null);
     //   System.out.println("Desempilhei: " + deleteBook.getTitle());
     //   System.out.println(deleteBook.toString());
        System.out.println(dbAcessor.printBooks());
    }
    
}
