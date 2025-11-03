package br.edu.ifba.vdc.bsi.linkedlistdao.dao;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list.LinkedList;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list.Listable;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Book;

import java.time.LocalDate;

/**
 * Implementação do DAO (Data Access Object) para gerenciamento de livros
 * utilizando uma estrutura de dados do tipo lista dinâmica (LinkedList).
 * 
 * Esta classe implementa todas as operações CRUD (Create, Read, Update, Delete)
 * e operações de consulta específicas para livros, mantendo os dados em uma
 * estrutura de lista que permite acesso por índice e inserção em posições específicas.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-10-22
 * @see BookDAO
 * @see Book
 * @see Listable
 * @see LinkedList
 */
public class BookDAOLinkedList implements BookDAO {

    /**
     * Lista principal para armazenamento dos livros.
     * Utiliza uma implementação baseada em lista dinâmica com capacidade inicial de 20 elementos.
     */
    private Listable<Book> listBooks = new LinkedList<>(20);

    // Operações básicas CRUD
    /**
     * Adiciona um novo livro à lista.
     * 
     * O livro é armazenado no final da lista.
     * 
     * @param book o livro a ser adicionado (não pode ser null e nem ter id já existente)
     * @throws IllegalArgumentException se o livro for null ou se já existir um livro com o mesmo id
     */
    @Override
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book não pode ser null");
        }
        long id = book.getId();
        int idx = findIndexById(id);
        if (idx >= 0) {
            throw new IllegalArgumentException("Já existe um livro com id = " + id);
        }
        listBooks.append(book);
    }

    /**
     * Retorna todos os livros da lista em um array.
     * 
     * @return array contendo todos os livros da lista
     */
    @Override
    public Book[] getAllBooks() {        
        return listBooks.selectAll();
    }
    
    /**
     * Atualiza um livro existente na lista.
     * 
     * Esta operação localiza o livro pelo ID e substitui pela nova versão.
     * A lista mantém a ordem original dos outros livros.
     * 
     * @param newBook o livro atualizado (deve ter o mesmo ID do livro original)
     */
    @Override
    public void updateBook(Book newBook) {        
        if (newBook == null) {
            throw new IllegalArgumentException("novo livro não pode ser nulo");
        }
        long targetId = newBook.getId();
        int idx = findIndexById(targetId);
        if (idx < 0) {
            throw new IllegalArgumentException("Livro com id = " + targetId + " não encontrado para atualização");
        }
        listBooks.update(idx, newBook);
    }
    
    /**
     * Remove um livro da lista pelo seu ID.
     * 
     * Esta operação localiza o livro pelo ID e remove-o da lista,
     * mantendo a ordem dos outros livros.
     * 
     * @param id o ID do livro a ser removido
     * @return o livro removido ou null se não for encontrado
     */
    @Override
    public Book deleteBook(long id) {
        int idx = findIndexById(id);
        if (idx >= 0) {
            return listBooks.delete(idx);
        }
        return null;
    }
    
    // Operações de consulta específicas para livros
    /**
     * Busca um livro pelo seu ID sem alterar a lista.
     * 
     * Esta operação percorre todos os livros da lista
     * e retorna o primeiro livro encontrado com o ID especificado.
     * 
     * @param id o ID do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBookById(long id) {
        int idx = findIndexById(id);
        if (idx >= 0) {
            return listBooks.select(idx);
        }
        return null;
    }

    /**
     * Busca todos os livros de um autor específico.
     * 
     * A busca é feita de forma case-insensitive, comparando o autor
     * de cada livro com o parâmetro fornecido.
     * 
     * @param author o nome do autor a ser buscado
     * @return array com todos os livros do autor especificado
     */
    @Override
    public Book[] getBooksByAuthor(String author) {
        if (author == null) {
            return new Book[0];
        }
        Listable<Book> resultListBooks = new LinkedList<>(20);
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            if (book.getAuthor() != null 
                && book.getAuthor().equalsIgnoreCase(author)) {
                resultListBooks.append(book);
            }
        }
        return listToArray(resultListBooks);
    }

    /**
     * Busca todos os livros publicados em uma data específica.
     * 
     * A comparação é feita usando o método equals() da classe LocalDate,
     * considerando apenas livros com data de publicação não nula.
     * 
     * @param date a data de publicação a ser buscada
     * @return array com todos os livros publicados na data especificada
     */
    @Override
    public Book[] getBooksByPublicationDate(LocalDate date) {
        if (date == null) {
            return new Book[0];
        }
        Listable<Book> resultListBooks = new LinkedList<>(20);
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            if (book.getPublicationDate() != null 
                && book.getPublicationDate().equals(date)) {
                resultListBooks.append(book);
            }
        }
        return listToArray(resultListBooks);
    }

    /**
     * Busca todos os livros com um título específico.
     * 
     * A busca é feita de forma case-insensitive, comparando o título
     * de cada livro com o parâmetro fornecido.
     * 
     * @param title o título a ser buscado
     * @return array com todos os livros que possuem o título especificado
     */
    @Override
    public Book[] getBooksByTitle(String title) {
        if (title == null) {
            return new Book[0];
        }
        Listable<Book> resultListBooks = new LinkedList<>(20);
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            if (book.getTitle() != null 
                && book.getTitle().equalsIgnoreCase(title)) {
                resultListBooks.append(book);
            }
        }
        return listToArray(resultListBooks);
    }

    /**
     * Busca um livro pelo seu ISBN.
     * 
     * A busca é feita comparando o ISBN de cada livro com o parâmetro fornecido.
     * Apenas livros com ISBN não nulo são considerados na busca.
     * 
     * @param isbn o ISBN do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBookByIsbn(String isbn) {
        if (isbn == null) {
            return null;
        }
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            if (book.getIsbn() != null && book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Busca todos os livros dentro de uma faixa de preço.
     * 
     * A busca inclui livros com preço maior ou igual ao preço mínimo
     * e menor ou igual ao preço máximo especificados.
     * 
     * @param minPrice o preço mínimo (inclusivo)
     * @param maxPrice o preço máximo (inclusivo)
     * @return array com todos os livros dentro da faixa de preço especificada
     */
    @Override
    public Book[] getBooksByPriceRange(double minPrice, double maxPrice) {
        Listable<Book> resultListBooks = new LinkedList<>(20);
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            Double price = book.getPrice();
            if (price != null && price >= minPrice && price <= maxPrice) {
                resultListBooks.append(book);
            }
        }
        return listToArray(resultListBooks);
    }

    /**
     * Busca todos os livros publicados dentro de um intervalo de datas.
     * 
     * A busca inclui livros com data de publicação maior ou igual à data mínima
     * e menor ou igual à data máxima especificadas. Apenas livros com data não nula são considerados.
     * 
     * @param minDate a data mínima (inclusiva)
     * @param maxDate a data máxima (inclusiva)
     * @return array com todos os livros publicados no intervalo de datas especificado
     */
    @Override
    public Book[] getBooksByDateRange(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null || maxDate == null) {
            return new Book[0];
        }
        Listable<Book> resultListBooks = new LinkedList<>(20);
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            LocalDate pub = book.getPublicationDate();
            if (pub != null 
                && !pub.isBefore(minDate) 
                && !pub.isAfter(maxDate)) {
                resultListBooks.append(book);
            }
        }
        return listToArray(resultListBooks);
    }

    // Operações de análise e estatísticas
    /**
     * Encontra o livro mais caro da lista.
     * 
     * Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o maior preço.
     * 
     * @return o livro mais caro ou null se a lista estiver vazia
     */
    @Override
    public Book getMostExpensiveBook() {
        Book resultBook = null;
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            Double price = book.getPrice();
            if (price != null) {
                if (resultBook == null || price > resultBook.getPrice()) {
                    resultBook = book;
                }
            }
        }
        return resultBook;
    }

    /**
     * Encontra o livro mais barato da lista.
     * 
     * Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o menor preço.
     * 
     * @return o livro mais barato ou null se a lista estiver vazia
     */
    @Override
    public Book getCheapestBook() {
        Book resultBook = null;
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            Double price = book.getPrice();
            if (price != null) {
                if (resultBook == null || price < resultBook.getPrice()) {
                    resultBook = book;
                }
            }
        }
        return resultBook;
    }

    /**
     * Encontra o livro mais recente da lista.
     * 
     * Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais recente. Apenas livros com data não nula são considerados.
     * 
     * @return o livro mais recente ou null se não houver livros com data válida
     */
    @Override
    public Book getNewestBook() {
        Book resultBook = null;
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            LocalDate pub = book.getPublicationDate();
            if (pub != null) {
                if (resultBook == null || pub.isAfter(resultBook.getPublicationDate())) {
                    resultBook = book;
                }
            }
        }
        return resultBook;
    }

    /**
     * Encontra o livro mais antigo da lista.
     * 
     * Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais antiga. Apenas livros com data não nula são considerados.
     * 
     * @return o livro mais antigo ou null se não houver livros com data válida
     */
    @Override
    public Book getOldestBook() {
        Book resultBook = null;
        for (int i = 0; i < listBooks.size(); i++) {
            Book book = listBooks.select(i);
            LocalDate pub = book.getPublicationDate();
            if (pub != null) {
                if (resultBook == null || pub.isBefore(resultBook.getPublicationDate())) {
                    resultBook = book;
                }
            }
        }
        return resultBook;
    }
    
    // Operações de relatório
    /**
     * Retorna uma representação em string de todos os livros da lista.
     * 
     * Utiliza o método print() da lista para gerar a representação
     * de todos os livros armazenados.
     * 
     * @return string representando todos os livros da lista
     */
    @Override
    public String printBooks() {
        return listBooks.print();
    }

    /**
     * Retorna o número total de livros na lista.
     * 
     * Esta operação conta todos os elementos da lista sem removê-los,
     * preservando a estrutura original.
     * 
     * @return o número total de livros na lista
     */
    @Override
    public int getTotalBooks() {
        return listBooks.size();
    }

    /**
     * Calcula o preço médio de todos os livros na lista.
     * 
     * Esta operação percorre todos os livros, soma seus preços
     * e divide pelo número total de livros.
     * 
     * @return o preço médio dos livros ou 0.0 se a lista estiver vazia
     */
    @Override
    public double getAveragePrice() {
        if (listBooks.isEmpty()) {
            return 0.0;
        }
        double totalPrice = 0.0;
        int count = 0;
        for (int i = 0; i < listBooks.size(); i++) {
            Double price = listBooks.select(i).getPrice();
            if (price != null) {
                totalPrice += price;
                count++;
            }
        }
        return count > 0 ? totalPrice / count : 0.0;
    }

    // Operações de gerenciamento
    /**
     * Verifica se um livro está disponível na lista.
     * 
     * Esta operação verifica se existe um livro com o ID especificado
     * na lista, sem removê-lo.
     * 
     * @param id o ID do livro a ser verificado
     * @return true se o livro estiver disponível, false caso contrário
     */
    @Override
    public boolean isBookAvailable(long id) {
        return getBookById(id) != null;
    }

    /**
     * Remove todos os livros da lista.
     * 
     * Esta operação esvazia completamente a lista, removendo
     * todos os livros armazenados. A operação é irreversível.
     */
    @Override
    public void clearAllBooks() {
        listBooks.clear();
    }
    
    /**
     * Localiza o índice de um livro pelo seu ID.
     *
     * @param id o ID do livro
     * @return o índice se encontrado, ou -1 se não encontrado
     */
    private int findIndexById(long id) {
        for (int i = 0; i < listBooks.size(); i++) {
            if (listBooks.select(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converte uma lista para array.
     * 
     * @param list a lista a ser convertida
     * @return array com os elementos da lista
     */
    private Book[] listToArray(Listable<Book> list) {
        return list.selectAll();
    }

    /**
     * Converte um array para lista.
     * 
     * @param array o array a ser convertido
     * @return lista com os elementos do array
     */
    private Listable<Book> arrayToList(Book[] array) {
        Listable<Book> resultListBooks = new LinkedList<>(20);
        for (Book book: array) {
            resultListBooks.append(book);
        }

        return resultListBooks;
    }
}