package br.edu.ifba.vdc.bsi.linkeddequedao.dao;

import br.edu.ifba.vdc.bsi.linkeddequedao.dao.repository.LinkedDEQue;
import br.edu.ifba.vdc.bsi.linkeddequedao.dao.repository.DEQueable;
import br.edu.ifba.vdc.bsi.linkeddequedao.model.Book;

import java.time.LocalDate;

/**
 * Implementação do DAO (Data Access Object) para gerenciamento de livros
 * utilizando uma estrutura de dados do tipo fila com dupla terminação (DEQue).
 * 
 * Esta classe implementa todas as operações CRUD (Create, Read, Update, Delete)
 * e operações de consulta específicas para livros, mantendo os dados em uma
 * estrutura de fila que preserva a ordem FIFO (First In, First Out).
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-10-20
 * @see BookDAO
 * @see Book
 * @see DEQueable
 * @see LinkedDEQue
 */
public class BookDAOLinkedDEQue implements BookDAO {

    /**
     * Fila principal para armazenamento dos livros.
     * Utiliza uma implementação baseada em fila com capacidade inicial de 20 elementos.
     */
    private DEQueable<Book> queueBooks = new LinkedDEQue<>(20);

    // Operações básicas CRUD
    /**
     * Adiciona um novo livro à fila.
     * 
     * O livro é armazenado na fila.
     * 
     * @param book o livro a ser adicionado (não pode ser null)
     * @throws IllegalArgumentException se o livro for null
     */
    @Override
    public void addBook(Book book) {
        queueBooks.enqueue(book);
    }

    /**
     * Retorna todos os livros da fila em um array.
     * 
     * @return array contendo todos os livros da fila
     */
    @Override
    public Book[] getAllBooks() {        
        return queueToArray(queueBooks);
    }
    
    /**
     * Atualiza um livro existente na fila.
     * 
     * Esta operação localiza o livro pelo ID e substitui pela nova versão.
     * A fila é reconstruída mantendo a ordem original dos outros livros.
     * 
     * @param newBook o livro atualizado (deve ter o mesmo ID do livro original)
     */
    @Override
    public void updateBook(Book newBook) {
        // Para atualizar um livro específico, precisamos reconstruir a fila
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
 
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            if (book.getId() == newBook.getId()) {
                tempQueueBooks.enqueue(newBook);
                break;
            } else {
                tempQueueBooks.enqueue(book);
            }
        }

        // Reenfileirar o restante da fila
        while (!queueBooks.isEmpty()) {
            tempQueueBooks.enqueue(queueBooks.dequeue());
        }

        //restaurar a fila original
        queueBooks = tempQueueBooks;
    }
    
    /**
     * Remove um livro da fila pelo seu ID.
     * 
     * Esta operação localiza o livro pelo ID, remove-o da fila
     * e reconstrói a fila sem o livro removido, mantendo a ordem dos outros.
     * 
     * @param id o ID do livro a ser removido
     * @return o livro removido ou null se não for encontrado
     */
    @Override
    public Book deleteBook(Long id) {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            if (book.getId() == id) {
                resultBook = book;
                break;
            } else {
                tempQueueBooks.enqueue(book);
            }
        }
        
        // Reenfileirar o restante da fila
        while (!queueBooks.isEmpty()) {
            tempQueueBooks.enqueue(queueBooks.dequeue());
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;

        return resultBook;
    }
    
    // Operações de consulta específicas para livros
    /**
     * Busca um livro pelo seu ID sem alterar a fila.
     * 
     * Esta operação percorre todos os livros preservando a ordem da fila
     * e retorna o primeiro livro encontrado com o ID especificado.
     * 
     * @param id o ID do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBookById(Long id) {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getId() == id) {
                resultBook = book;
                break;
            }
        }
        
        // Reenfileirar o restante da fila
        while (!queueBooks.isEmpty()) {
            tempQueueBooks.enqueue(queueBooks.dequeue());
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;   
         
        return resultBook;
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
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        DEQueable<Book> resultQueueBooks = new LinkedDEQue<>(20);
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getAuthor() != null && book.getAuthor().equalsIgnoreCase(author)) {
                resultQueueBooks.enqueue(book);
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return queueToArray(resultQueueBooks);
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
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        DEQueable<Book> resultQueueBooks = new LinkedDEQue<>(20);
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPublicationDate() != null && book.getPublicationDate().equals(date)) {
                resultQueueBooks.enqueue(book);
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return queueToArray(resultQueueBooks);
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
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        DEQueable<Book> resultQueueBooks = new LinkedDEQue<>(20);
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getTitle() != null && book.getTitle().equalsIgnoreCase(title)) {
                resultQueueBooks.enqueue(book);
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return queueToArray(resultQueueBooks);
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
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getIsbn() != null && book.getIsbn().equals(isbn)) {
                resultBook = book;
                break;
            }
        }
        
        // Reenfileirar o restante da fila
        while (!queueBooks.isEmpty()) {
            tempQueueBooks.enqueue(queueBooks.dequeue());
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return resultBook;
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
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        DEQueable<Book> resultQueueBooks = new LinkedDEQue<>(20);
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                resultQueueBooks.enqueue(book);
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return queueToArray(resultQueueBooks);
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
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        DEQueable<Book> resultQueueBooks = new LinkedDEQue<>(20);
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPublicationDate() != null && 
                !book.getPublicationDate().isBefore(minDate) && 
                !book.getPublicationDate().isAfter(maxDate)) {
                resultQueueBooks.enqueue(book);
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return queueToArray(resultQueueBooks);
    }

    // Operações de análise e estatísticas
    /**
     * Encontra o livro mais caro da fila.
     * 
     * Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o maior preço. A fila é preservada após a busca.
     * 
     * @return o livro mais caro ou null se a fila estiver vazia
     */
    @Override
    public Book getMostExpensiveBook() {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;

        // Desenfileirar todos os livros e filtrar os que têm preço
        if (!queueBooks.isEmpty()){
            Book book  = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPrice() != null) {
                resultBook = book;
            }

            while (!queueBooks.isEmpty()) {
                book = queueBooks.dequeue();
                tempQueueBooks.enqueue(book);
                if (book.getPrice() != null) {
                    if (resultBook != null 
                            && book.getPrice() > resultBook.getPrice()) {
                        resultBook = book;
                    }
                }
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return resultBook;
    }

    /**
     * Encontra o livro mais barato da fila.
     * 
     * Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o menor preço. A fila é preservada após a busca.
     * 
     * @return o livro mais barato ou null se a fila estiver vazia
     */
    @Override
    public Book getCheapestBook() {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;

        // Desenfileirar todos os livros e filtrar os que têm preço
        if (!queueBooks.isEmpty()){
            Book book  = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPrice() != null) {
                resultBook = book;
            }
        
            // Desenfileirar todos os livros           
            while (!queueBooks.isEmpty()) {
                book = queueBooks.dequeue();
                tempQueueBooks.enqueue(book);
                if (book.getPrice() != null) {
                    if (resultBook != null 
                            && book.getPrice() < resultBook.getPrice()) {
                        resultBook = book;
                    }
                }
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return resultBook;
    }

    /**
     * Encontra o livro mais recente da fila.
     * 
     * Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais recente. Apenas livros com data não nula são considerados.
     * 
     * @return o livro mais recente ou null se não houver livros com data válida
     */
    @Override
    public Book getNewestBook() {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;
        
        // Desenfileirar todos os livros e filtrar os que têm data de publicação
        if (!queueBooks.isEmpty()){
            Book book  = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPublicationDate() != null) {
                resultBook = book;
            }
            // Desenfileirar todos os livros           
            while (!queueBooks.isEmpty()) {
                book = queueBooks.dequeue();
                tempQueueBooks.enqueue(book);
                if (book.getPublicationDate() != null) {
                    if (resultBook != null 
                            && book.getPublicationDate().isBefore(resultBook.getPublicationDate())) {
                        resultBook = book;
                    }
                }
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return resultBook;
    }

    /**
     * Encontra o livro mais antigo da fila.
     * 
     * Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais antiga. Apenas livros com data não nula são considerados.
     * 
     * @return o livro mais antigo ou null se não houver livros com data válida
     */
    @Override
    public Book getOldestBook() {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        Book resultBook = null;
        
        // Desenfileirar todos os livros e filtrar os que têm data de publicação
        if (!queueBooks.isEmpty()){
            Book book  = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            if (book.getPublicationDate() != null) {
                resultBook = book;
            }
            // Reenfileirar todos os livros           
            while (!queueBooks.isEmpty()) {
                book = queueBooks.dequeue();
                tempQueueBooks.enqueue(book);
                if (book.getPublicationDate() != null) {
                    if (resultBook != null 
                            && book.getPublicationDate().isAfter(resultBook.getPublicationDate())) {
                        resultBook = book;
                    }
                }
            }
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return resultBook;
    }
    
    // Operações de relatório
    /**
     * Retorna uma representação em string de todos os livros da fila.
     * 
     * Utiliza o método toString() da fila para gerar a representação
     * de todos os livros armazenados.
     * 
     * @return string representando todos os livros da fila
     */
    @Override
    public String printBooks() {
        return queueBooks.print();
    }

    /**
     * Retorna o número total de livros na fila.
     * 
     * Esta operação conta todos os elementos da fila sem removê-los,
     * preservando a estrutura original.
     * 
     * @return o número total de livros na fila
     */
    @Override
    public int getTotalBooks() {
        return countElements(queueBooks);
    }

    /**
     * Calcula o preço médio de todos os livros na fila.
     * 
     * Esta operação percorre todos os livros, soma seus preços
     * e divide pelo número total de livros. A fila é preservada após o cálculo.
     * 
     * @return o preço médio dos livros ou 0.0 se a fila estiver vazia
     */
    @Override
    public double getAveragePrice() {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        double totalPrice = 0.0;
        int bookCount = 0;
        
        // Desenfileirar todos os livros
        while (!queueBooks.isEmpty()) {
            Book book = queueBooks.dequeue();
            tempQueueBooks.enqueue(book);
            totalPrice += book.getPrice();
            bookCount++;
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return bookCount > 0 ? totalPrice / bookCount : 0.0;
    }

    // Operações de gerenciamento
    /**
     * Verifica se um livro está disponível na fila.
     * 
     * Esta operação verifica se existe um livro com o ID especificado
     * na fila, sem removê-lo.
     * 
     * @param id o ID do livro a ser verificado
     * @return true se o livro estiver disponível, false caso contrário
     */
    @Override
    public boolean isBookAvailable(Long id) {
        return getBookById(id) != null;
    }

    /**
     * Remove todos os livros da fila.
     * 
     * Esta operação esvazia completamente a fila, removendo
     * todos os livros armazenados. A operação é irreversível.
     */
    @Override
    public void clearAllBooks() {
        while (!queueBooks.isEmpty()) {
            queueBooks.dequeue();
        }
    }
    
    // Métodos auxiliares usando apenas DEQueable
    /**
     * Conta o número de elementos em uma fila sem removê-los.
     * 
     * Este método auxiliar percorre todos os elementos da fila,
     * conta-os e restaura a fila original. É útil para operações
     * que precisam saber o tamanho antes de processar os elementos.
     * 
     * @param queue a fila a ser contada
     * @return o número de elementos na fila
     */
    private int countElements(DEQueable<Book> queue) {
        DEQueable<Book> tempQueueBooks = new LinkedDEQue<>(20);
        int count = 0;
        
        while (!queue.isEmpty()) {
            tempQueueBooks.enqueue(queue.dequeue());
            count++;
        }
        
        //restaurar a fila original
        queueBooks = tempQueueBooks;
        
        return count;
    }
    
    /**
     * Converte uma fila de resultados em um array de livros.
     * 
     * Este método auxiliar é usado para converter filas temporárias
     * que contêm resultados de filtros em arrays, facilitando o retorno
     * dos métodos de busca. A fila original é esvaziada no processo.
     * 
     * @param resultQueue a fila contendo os livros a serem convertidos
     * @return array contendo todos os livros da fila
     */
    private Book[] queueToArray(DEQueable<Book> queue) {
        Book[] resultArrayBooks = new Book[countElements(queue)];
        int index = 0;
        while (!queue.isEmpty()) {
            resultArrayBooks[index] = queue.dequeue();
            index++;
        }
        return resultArrayBooks;
    }

    /**
     * Converte um array de livros em uma fila (queue).
     *
     * Este método auxiliar é usado para converter arrays que contêm
     * livros em uma fila, facilitando operações de enfileiramento.
     * A fila resultante terá os livros do array enfileirados na ordem
     * do array (o primeiro elemento do array ficará no fundo da fila).
     *
     * @param books o array contendo os livros a serem enfileirados
     * @return fila contendo todos os livros do array na ordem dada
     */
    private DEQueable<Book> arrayToQueue(Book[] books) {
        DEQueable<Book> resultQueueBooks = new LinkedDEQue<>(20);  // ou a implementação concreta do seu DEQueable
        for (Book book : books) {
            resultQueueBooks.enqueue(book);
        }
        return resultQueueBooks;
    }
}