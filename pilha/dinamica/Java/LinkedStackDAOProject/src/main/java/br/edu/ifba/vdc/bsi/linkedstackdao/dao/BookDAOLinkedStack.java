package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;

public class BookDAOLinkedStack implements BookDAO {

    private Stackable<Book> books = new LinkedStack<>(20);

    // Operações básicas CRUD
    @Override
    public void addBook(Book book) {
        books.push(book);
    }
  
    @Override
    public Book getBook(Long id) {
        Book[] allBooks = getAllBooks();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void updateBook(Book newBook) {
        // Para atualizar um livro específico, precisamos reconstruir a pilha
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            if (book.getId().equals(newBook.getId())) {
                tempStack.push(newBook);
            } else {
                tempStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
    }
    
    @Override
    public Book deleteBook(Long id) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book deletedBook = null;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            if (book.getId().equals(id)) {
                deletedBook = book;
            } else {
                tempStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return deletedBook;
    }
    
    // Operações de consulta específicas para livros
    @Override
    public Book[] getBooksByAuthor(String author) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getAuthor() != null && book.getAuthor().equalsIgnoreCase(author)) {
                resultStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        // Converter resultado para array
        Book[] result = new Book[countElements(resultStack)];
        int index = 0;
        while (!resultStack.isEmpty()) {
            result[index++] = resultStack.pop();
        }
        
        return result;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book foundBook = null;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getIsbn() != null && book.getIsbn().equals(isbn)) {
                foundBook = book;
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return foundBook;
    }
    
    // Operações de análise e estatísticas
    @Override
    public Book[] getMostExpensiveBooks(int limit) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> booksWithPrice = new LinkedStack<>(20);
        
        // Desempilhar todos os livros e filtrar os que têm preço
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPrice() != null) {
                booksWithPrice.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        // Ordenar por preço (mais caros primeiro) usando bubble sort
        Book[] booksArray = stackToArray(booksWithPrice);
        bubbleSortByPriceDesc(booksArray);
        
        // Retornar apenas o limite solicitado
        int resultSize = Math.min(limit, booksArray.length);
        Book[] result = new Book[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = booksArray[i];
        }
        
        return result;
    }

    @Override
    public Book[] getCheapestBooks(int limit) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> booksWithPrice = new LinkedStack<>(20);
        
        // Desempilhar todos os livros e filtrar os que têm preço
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPrice() != null) {
                booksWithPrice.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        // Ordenar por preço (mais baratos primeiro) usando bubble sort
        Book[] booksArray = stackToArray(booksWithPrice);
        bubbleSortByPriceAsc(booksArray);
        
        // Retornar apenas o limite solicitado
        int resultSize = Math.min(limit, booksArray.length);
        Book[] result = new Book[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = booksArray[i];
        }
        
        return result;
    }
    
    // Operações de ordenação específicas
    @Override
    public Book[] sortBooksByTitle() {
        Book[] allBooks = getAllBooks();
        bubbleSortByTitle(allBooks);
        return allBooks;
    }

    @Override
    public Book[] sortBooksByAuthor() {
        Book[] allBooks = getAllBooks();
        bubbleSortByAuthor(allBooks);
        return allBooks;
    }
    
    // Operações de relatório
    @Override
    public Book[] getAllBooks() {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            tempStack.push(books.pop());
        }
        
        // Reempilhar na ordem original e criar array
        Book[] result = new Book[countElements(tempStack)];
        int index = 0;
        while (!tempStack.isEmpty()) {
            Book book = tempStack.pop();
            result[index++] = book;
            books.push(book);
        }
        
        return result;
    }

    @Override
    public String printBooks() {
        return books.toString();
    }

    @Override
    public int getTotalBooks() {
        return countElements(books);
    }
    
    // Métodos auxiliares usando apenas Stackable
    private int countElements(Stackable<Book> stack) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        int count = 0;
        
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
            count++;
        }
        
        // Restaurar a pilha original
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        
        return count;
    }
    
    private Book[] stackToArray(Stackable<Book> stack) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book[] array = new Book[countElements(stack)];
        int index = 0;
        
        while (!stack.isEmpty()) {
            Book book = stack.pop();
            array[index++] = book;
            tempStack.push(book);
        }
        
        // Restaurar a pilha original
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        
        return array;
    }
    
    // Algoritmos de ordenação usando apenas arrays (sem List)
    private void bubbleSortByPriceDesc(Book[] books) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books[j].getPrice() < books[j + 1].getPrice()) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByPriceAsc(Book[] books) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books[j].getPrice() > books[j + 1].getPrice()) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByTitle(Book[] books) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
    
    private void bubbleSortByAuthor(Book[] books) {
        int n = books.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String author1 = books[j].getAuthor() != null ? books[j].getAuthor() : "";
                String author2 = books[j + 1].getAuthor() != null ? books[j + 1].getAuthor() : "";
                if (author1.compareToIgnoreCase(author2) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
}