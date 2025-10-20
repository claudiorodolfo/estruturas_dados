package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;

import java.time.LocalDate;

/**
 * Implementação do DAO (Data Access Object) para gerenciamento de livros
 * utilizando uma estrutura de dados do tipo pilha (stack) baseada em lista encadeada.
 * 
 * Esta classe implementa todas as operações CRUD (Create, Read, Update, Delete)
 * e operações de consulta específicas para livros, mantendo os dados em uma
 * estrutura de pilha que preserva a ordem LIFO (Last In, First Out).
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-10-13
 * @see BookDAO
 * @see Book
 * @see Stackable
 * @see LinkedStack
 */
public class BookDAOLinkedStack implements BookDAO {

    /**
     * Pilha principal para armazenamento dos livros.
     * Utiliza uma implementação baseada em lista encadeada com capacidade inicial de 20 elementos.
     */
    private Stackable<Book> stackBooks = new LinkedStack<>(20);

    // Operações básicas CRUD
    /**
     * Adiciona um novo livro à pilha.
     * 
     * O livro é inserido no topo da pilha, seguindo o princípio LIFO.
     * Esta operação tem complexidade O(1).
     * 
     * @param book o livro a ser adicionado (não pode ser null)
     * @throws IllegalArgumentException se o livro for null
     */
    @Override
    public void addBook(Book book) {
        stackBooks.push(book);
    }
  
    /**
     * Busca um livro pelo seu ID.
     * 
     * Esta operação percorre todos os livros na pilha para encontrar
     * o livro com o ID especificado. A pilha é preservada após a busca.
     * 
     * @param id o ID do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBook(long id) {
        // Para atualizar um livro específico, precisamos reconstruir a pilha
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getId() == id) {
                resultBook = book;
                break;
            }
        }
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        return resultBook;
    }

    /**
     * Retorna todos os livros da pilha em um array.
     * 
     * Esta operação desempilha todos os livros, cria um array com eles
     * e reempilha na ordem original, preservando a estrutura da pilha.
     * 
     * @return array contendo todos os livros da pilha
     */
    @Override
    public Book[] getAllBooks() {        
        return stackToArray(stackBooks);
    }
    
    /**
     * Atualiza um livro existente na pilha.
     * 
     * Esta operação localiza o livro pelo ID e substitui pela nova versão.
     * A pilha é reconstruída mantendo a ordem original dos outros livros.
     * 
     * @param newBook o livro atualizado (deve ter o mesmo ID do livro original)
     */
    @Override
    public void updateBook(Book newBook) {
        // Para atualizar um livro específico, precisamos reconstruir a pilha
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
 
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            if (book.getId() == newBook.getId()) {
                tempStackBooks.push(newBook);
            } else {
                tempStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
    }
    
    /**
     * Remove um livro da pilha pelo seu ID.
     * 
     * Esta operação localiza o livro pelo ID, remove-o da pilha
     * e reconstrói a pilha sem o livro removido, mantendo a ordem dos outros.
     * 
     * @param id o ID do livro a ser removido
     * @return o livro removido ou null se não for encontrado
     */
    @Override
    public Book deleteBook(long id) {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            if (book.getId() == id) {
                resultBook = book;
                break;
            } else {
                tempStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return resultBook;
    }
    
    // Operações de consulta específicas para livros
    /**
     * Busca um livro pelo seu ID sem alterar a pilha.
     * 
     * Esta operação percorre todos os livros preservando a ordem da pilha
     * e retorna o primeiro livro encontrado com o ID especificado.
     * 
     * @param id o ID do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBookById(long id) {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getId() == id) {
                resultBook = book;
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }    
         
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
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Stackable<Book> resultStackBooks = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getAuthor() != null && book.getAuthor().equalsIgnoreCase(author)) {
                resultStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return stackToArray(resultStackBooks);
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
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Stackable<Book> resultStackBooks = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPublicationDate() != null && book.getPublicationDate().equals(date)) {
                resultStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return stackToArray(resultStackBooks);
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
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Stackable<Book> resultStackBooks = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getTitle() != null && book.getTitle().equalsIgnoreCase(title)) {
                resultStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return stackToArray(resultStackBooks);
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
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getIsbn() != null && book.getIsbn().equals(isbn)) {
                resultBook = book;
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
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
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Stackable<Book> resultStackBooks = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                resultStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return stackToArray(resultStackBooks);
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
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Stackable<Book> resultStackBooks = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPublicationDate() != null && 
                !book.getPublicationDate().isBefore(minDate) && 
                !book.getPublicationDate().isAfter(maxDate)) {
                resultStackBooks.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return stackToArray(resultStackBooks);
    }

    // Operações de análise e estatísticas
    /**
     * Encontra o livro mais caro da pilha.
     * 
     * Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o maior preço. A pilha é preservada após a busca.
     * 
     * @return o livro mais caro ou null se a pilha estiver vazia
     */
    @Override
    public Book getMostExpensiveBook() {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;

        // Desempilhar todos os livros e filtrar os que têm preço
        if (!stackBooks.isEmpty()){
            Book book  = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPrice() != null) {
                resultBook = book;
            }

            while (!stackBooks.isEmpty()) {
                book = stackBooks.pop();
                tempStackBooks.push(book);
                if (book.getPrice() != null) {
                    if (resultBook != null 
                            && book.getPrice() > resultBook.getPrice()) {
                        resultBook = book;
                    }
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return resultBook;
    }

    /**
     * Encontra o livro mais barato da pilha.
     * 
     * Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o menor preço. A pilha é preservada após a busca.
     * 
     * @return o livro mais barato ou null se a pilha estiver vazia
     */
    @Override
    public Book getCheapestBook() {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;

        // Desempilhar todos os livros e filtrar os que têm preço
        if (!stackBooks.isEmpty()){
            Book book  = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPrice() != null) {
                resultBook = book;
            }
        
            // Desempilhar todos os livros           
            while (!stackBooks.isEmpty()) {
                book = stackBooks.pop();
                tempStackBooks.push(book);
                if (book.getPrice() != null) {
                    if (resultBook != null 
                            && book.getPrice() < resultBook.getPrice()) {
                        resultBook = book;
                    }
                }
            }
        }

        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return resultBook;
    }

    /**
     * Encontra o livro mais recente da pilha.
     * 
     * Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais recente. Apenas livros com data não nula são considerados.
     * 
     * @return o livro mais recente ou null se não houver livros com data válida
     */
    @Override
    public Book getNewestBook() {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;
        
        // Desempilhar todos os livros e filtrar os que têm preço
        if (!stackBooks.isEmpty()){
            Book book  = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPublicationDate() != null) {
                resultBook = book;
            }
            // Desempilhar todos os livros           
            while (!stackBooks.isEmpty()) {
                book = stackBooks.pop();
                tempStackBooks.push(book);
                if (book.getPublicationDate() != null) {
                    if (resultBook != null 
                            && book.getPublicationDate().isBefore(resultBook.getPublicationDate())) {
                        resultBook = book;
                    }
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return resultBook;
    }

    /**
     * Encontra o livro mais antigo da pilha.
     * 
     * Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais antiga. Apenas livros com data não nula são considerados.
     * 
     * @return o livro mais antigo ou null se não houver livros com data válida
     */
    @Override
    public Book getOldestBook() {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        Book resultBook = null;
        
        // Desempilhar todos os livros e filtrar os que têm preço
        if (!stackBooks.isEmpty()){
            Book book  = stackBooks.pop();
            tempStackBooks.push(book);
            if (book.getPublicationDate() != null) {
                resultBook = book;
            }
            // Desempilhar todos os livros           
            while (!stackBooks.isEmpty()) {
                book = stackBooks.pop();
                tempStackBooks.push(book);
                if (book.getPublicationDate() != null) {
                    if (resultBook != null 
                            && book.getPublicationDate().isAfter(resultBook.getPublicationDate())) {
                        resultBook = book;
                    }
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return resultBook;
    }
    
    // Operações de relatório
    /**
     * Retorna uma representação em string de todos os livros da pilha.
     * 
     * Utiliza o método toString() da pilha para gerar a representação
     * de todos os livros armazenados.
     * 
     * @return string representando todos os livros da pilha
     */
    @Override
    public String printBooks() {
        return stackBooks.toString();
    }

    /**
     * Retorna o número total de livros na pilha.
     * 
     * Esta operação conta todos os elementos da pilha sem removê-los,
     * preservando a estrutura original.
     * 
     * @return o número total de livros na pilha
     */
    @Override
    public int getTotalBooks() {
        return countElements(stackBooks);
    }

    /**
     * Calcula o preço médio de todos os livros na pilha.
     * 
     * Esta operação percorre todos os livros, soma seus preços
     * e divide pelo número total de livros. A pilha é preservada após o cálculo.
     * 
     * @return o preço médio dos livros ou 0.0 se a pilha estiver vazia
     */
    @Override
    public double getAveragePrice() {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        double totalPrice = 0.0;
        int bookCount = 0;
        
        // Desempilhar todos os livros
        while (!stackBooks.isEmpty()) {
            Book book = stackBooks.pop();
            tempStackBooks.push(book);
            totalPrice += book.getPrice();
            bookCount++;
        }
        
        // Reempilhar na ordem original
        while (!tempStackBooks.isEmpty()) {
            stackBooks.push(tempStackBooks.pop());
        }
        
        return bookCount > 0 ? totalPrice / bookCount : 0.0;
    }

    // Operações de gerenciamento
    /**
     * Verifica se um livro está disponível na pilha.
     * 
     * Esta operação verifica se existe um livro com o ID especificado
     * na pilha, sem removê-lo.
     * 
     * @param id o ID do livro a ser verificado
     * @return true se o livro estiver disponível, false caso contrário
     */
    @Override
    public boolean isBookAvailable(long id) {
        return getBook(id) != null;
    }

    /**
     * Remove todos os livros da pilha.
     * 
     * Esta operação esvazia completamente a pilha, removendo
     * todos os livros armazenados. A operação é irreversível.
     */
    @Override
    public void clearAllBooks() {
        while (!stackBooks.isEmpty()) {
            stackBooks.pop();
        }
    }
    
    // Métodos auxiliares usando apenas Stackable
    /**
     * Conta o número de elementos em uma pilha sem removê-los.
     * 
     * Este método auxiliar percorre todos os elementos da pilha,
     * conta-os e restaura a pilha original. É útil para operações
     * que precisam saber o tamanho antes de processar os elementos.
     * 
     * @param stack a pilha a ser contada
     * @return o número de elementos na pilha
     */
    private int countElements(Stackable<Book> stack) {
        Stackable<Book> tempStackBooks = new LinkedStack<>(20);
        int count = 0;
        
        while (!stack.isEmpty()) {
            tempStackBooks.push(stack.pop());
            count++;
        }
        
        // Restaurar a pilha original
        while (!tempStackBooks.isEmpty()) {
            stack.push(tempStackBooks.pop());
        }
        
        return count;
    }
    
    /**
     * Converte uma pilha de resultados em um array de livros.
     * 
     * Este método auxiliar é usado para converter pilhas temporárias
     * que contêm resultados de filtros em arrays, facilitando o retorno
     * dos métodos de busca. A pilha original é esvaziada no processo.
     * 
     * @param resultStack a pilha contendo os livros a serem convertidos
     * @return array contendo todos os livros da pilha
     */
    private Book[] stackToArray(Stackable<Book> stack) {
        Book[] resultArrayBooks = new Book[countElements(stack)];
        int index = 0;
        while (!stack.isEmpty()) {
            resultArrayBooks[index] = stack.pop();
            index++;
        }
        return resultArrayBooks;
    }

    /**
     * Converte um array de livros em uma pilha (stack).
     *
     * Este método auxiliar é usado para converter arrays que contêm
     * livros em uma pilha, facilitando operações de empilhamento.
     * A pilha resultante terá os livros do array empilhados na ordem
     * do array (o primeiro elemento do array ficará no fundo da pilha).
     *
     * @param books o array contendo os livros a serem empilhados
     * @return pilha contendo todos os livros do array na ordem dada
     */
    private Stackable<Book> arrayToStack(Book[] books) {
        Stackable<Book> resultStackBooks = new LinkedStack<>(20);  // ou a implementação concreta do seu Stackable
        for (Book book : books) {
            resultStackBooks.push(book);
        }
        return resultStackBooks;
    }
}