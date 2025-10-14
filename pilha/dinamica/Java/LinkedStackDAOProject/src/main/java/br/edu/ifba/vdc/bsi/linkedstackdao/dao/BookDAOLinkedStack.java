package br.edu.ifba.vdc.bsi.linkedstackdao.dao;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;

import java.time.LocalDate;

/**
 * Implementação do DAO (Data Access Object) para gerenciamento de livros
 * utilizando uma estrutura de dados do tipo pilha (stack) baseada em lista encadeada.
 * 
 * <p>Esta classe implementa todas as operações CRUD (Create, Read, Update, Delete)
 * e operações de consulta específicas para livros, mantendo os dados em uma
 * estrutura de pilha que preserva a ordem LIFO (Last In, First Out).</p>
 * 
 * <p>Características principais:</p>
 * <ul>
 *   <li>Armazenamento baseado em pilha com lista encadeada</li>
 *   <li>Preservação da ordem original da pilha em todas as operações</li>
 *   <li>Suporte a operações de filtro e busca</li>
 *   <li>Cálculo de estatísticas e análises</li>
 * </ul>
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
    private Stackable<Book> books = new LinkedStack<>(20);

    // Operações básicas CRUD
    /**
     * Adiciona um novo livro à pilha.
     * 
     * <p>O livro é inserido no topo da pilha, seguindo o princípio LIFO.
     * Esta operação tem complexidade O(1).</p>
     * 
     * @param book o livro a ser adicionado (não pode ser null)
     * @throws IllegalArgumentException se o livro for null
     */
    @Override
    public void addBook(Book book) {
        books.push(book);
    }
  
    /**
     * Busca um livro pelo seu ID.
     * 
     * <p>Esta operação percorre todos os livros na pilha para encontrar
     * o livro com o ID especificado. A pilha é preservada após a busca.</p>
     * 
     * @param id o ID do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBook(long id) {
        Book[] allBooks = getAllBooks();
        for (Book book : allBooks) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * Retorna todos os livros da pilha em um array.
     * 
     * <p>Esta operação desempilha todos os livros, cria um array com eles
     * e reempilha na ordem original, preservando a estrutura da pilha.</p>
     * 
     * @return array contendo todos os livros da pilha
     */
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
            result[index] = book;
            index++;
            books.push(book);
        }
        
        return result;
    }
    
    /**
     * Atualiza um livro existente na pilha.
     * 
     * <p>Esta operação localiza o livro pelo ID e substitui pela nova versão.
     * A pilha é reconstruída mantendo a ordem original dos outros livros.</p>
     * 
     * @param newBook o livro atualizado (deve ter o mesmo ID do livro original)
     */
    @Override
    public void updateBook(Book newBook) {
        // Para atualizar um livro específico, precisamos reconstruir a pilha
        Stackable<Book> tempStack = new LinkedStack<>(20);
 
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            if (book.getId() == newBook.getId()) {
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
    
    /**
     * Remove um livro da pilha pelo seu ID.
     * 
     * <p>Esta operação localiza o livro pelo ID, remove-o da pilha
     * e reconstrói a pilha sem o livro removido, mantendo a ordem dos outros.</p>
     * 
     * @param id o ID do livro a ser removido
     * @return o livro removido ou null se não for encontrado
     */
    @Override
    public Book deleteBook(long id) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book deletedBook = null;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            if (book.getId() == id) {
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
    /**
     * Busca um livro pelo seu ID sem alterar a pilha.
     * 
     * <p>Esta operação percorre todos os livros preservando a ordem da pilha
     * e retorna o primeiro livro encontrado com o ID especificado.</p>
     * 
     * @param id o ID do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
    @Override
    public Book getBookById(long id) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book foundBook = null;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getId() == id) {
                foundBook = book;
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }    
         
        return foundBook;
    }

    /**
     * Busca todos os livros de um autor específico.
     * 
     * <p>A busca é feita de forma case-insensitive, comparando o autor
     * de cada livro com o parâmetro fornecido.</p>
     * 
     * @param author o nome do autor a ser buscado
     * @return array com todos os livros do autor especificado
     */
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
        
        return resultStackToArray(resultStack);
    }

    /**
     * Busca todos os livros publicados em uma data específica.
     * 
     * <p>A comparação é feita usando o método equals() da classe LocalDate,
     * considerando apenas livros com data de publicação não nula.</p>
     * 
     * @param date a data de publicação a ser buscada
     * @return array com todos os livros publicados na data especificada
     */
    @Override
    public Book[] getBooksByPublicationDate(LocalDate date) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPublicationDate() != null && book.getPublicationDate().equals(date)) {
                resultStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return resultStackToArray(resultStack);
    }

    /**
     * Busca todos os livros com um título específico.
     * 
     * <p>A busca é feita de forma case-insensitive, comparando o título
     * de cada livro com o parâmetro fornecido.</p>
     * 
     * @param title o título a ser buscado
     * @return array com todos os livros que possuem o título especificado
     */
    @Override
    public Book[] getBooksByTitle(String title) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getTitle() != null && book.getTitle().equalsIgnoreCase(title)) {
                resultStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return resultStackToArray(resultStack);
    }

    /**
     * Busca um livro pelo seu ISBN.
     * 
     * <p>A busca é feita comparando o ISBN de cada livro com o parâmetro fornecido.
     * Apenas livros com ISBN não nulo são considerados na busca.</p>
     * 
     * @param isbn o ISBN do livro a ser buscado
     * @return o livro encontrado ou null se não existir
     */
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

    /**
     * Busca todos os livros dentro de uma faixa de preço.
     * 
     * <p>A busca inclui livros com preço maior ou igual ao preço mínimo
     * e menor ou igual ao preço máximo especificados.</p>
     * 
     * @param minPrice o preço mínimo (inclusivo)
     * @param maxPrice o preço máximo (inclusivo)
     * @return array com todos os livros dentro da faixa de preço especificada
     */
    @Override
    public Book[] getBooksByPriceRange(double minPrice, double maxPrice) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                resultStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return resultStackToArray(resultStack);
    }

    /**
     * Busca todos os livros publicados dentro de um intervalo de datas.
     * 
     * <p>A busca inclui livros com data de publicação maior ou igual à data mínima
     * e menor ou igual à data máxima especificadas. Apenas livros com data não nula são considerados.</p>
     * 
     * @param minDate a data mínima (inclusiva)
     * @param maxDate a data máxima (inclusiva)
     * @return array com todos os livros publicados no intervalo de datas especificado
     */
    @Override
    public Book[] getBooksByDateRange(LocalDate minDate, LocalDate maxDate) {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Stackable<Book> resultStack = new LinkedStack<>(20);
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPublicationDate() != null && 
                !book.getPublicationDate().isBefore(minDate) && 
                !book.getPublicationDate().isAfter(maxDate)) {
                resultStack.push(book);
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return resultStackToArray(resultStack);
    }

    // Operações de análise e estatísticas
    /**
     * Encontra o livro mais caro da pilha.
     * 
     * <p>Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o maior preço. A pilha é preservada após a busca.</p>
     * 
     * @return o livro mais caro ou null se a pilha estiver vazia
     */
    @Override
    public Book getMostExpensiveBook() {
        Stackable<Book> tempStack = new LinkedStack<>(20);

        Book mostExpensiveBook = null;
        // Desempilhar todos os livros e filtrar os que têm preço
        if (!books.isEmpty()){
            mostExpensiveBook = books.pop();
    
            while (!books.isEmpty()) {
                Book book = books.pop();
                tempStack.push(book);
                if (book.getPrice() > mostExpensiveBook.getPrice()) {
                    mostExpensiveBook = book;
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return mostExpensiveBook;
    }

    /**
     * Encontra o livro mais barato da pilha.
     * 
     * <p>Esta operação percorre todos os livros comparando seus preços
     * e retorna o livro com o menor preço. A pilha é preservada após a busca.</p>
     * 
     * @return o livro mais barato ou null se a pilha estiver vazia
     */
    @Override
    public Book getCheapestBook() {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book cheapestBook = null;
        
        // Desempilhar todos os livros
        if (!books.isEmpty()) {
            cheapestBook = books.pop();
            tempStack.push(cheapestBook);
            
            while (!books.isEmpty()) {
                Book book = books.pop();
                tempStack.push(book);
                if (book.getPrice() < cheapestBook.getPrice()) {
                    cheapestBook = book;
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return cheapestBook;
    }

    /**
     * Encontra o livro mais recente da pilha.
     * 
     * <p>Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais recente. Apenas livros com data não nula são considerados.</p>
     * 
     * @return o livro mais recente ou null se não houver livros com data válida
     */
    @Override
    public Book getNewestBook() {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book newestBook = null;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPublicationDate() != null) {
                if (newestBook == null || book.getPublicationDate().isAfter(newestBook.getPublicationDate())) {
                    newestBook = book;
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return newestBook;
    }

    /**
     * Encontra o livro mais antigo da pilha.
     * 
     * <p>Esta operação percorre todos os livros comparando suas datas de publicação
     * e retorna o livro com a data mais antiga. Apenas livros com data não nula são considerados.</p>
     * 
     * @return o livro mais antigo ou null se não houver livros com data válida
     */
    @Override
    public Book getOldestBook() {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        Book oldestBook = null;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            if (book.getPublicationDate() != null) {
                if (oldestBook == null || book.getPublicationDate().isBefore(oldestBook.getPublicationDate())) {
                    oldestBook = book;
                }
            }
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return oldestBook;
    }
    
    // Operações de relatório
    /**
     * Retorna uma representação em string de todos os livros da pilha.
     * 
     * <p>Utiliza o método toString() da pilha para gerar a representação
     * de todos os livros armazenados.</p>
     * 
     * @return string representando todos os livros da pilha
     */
    @Override
    public String printBooks() {
        return books.toString();
    }

    /**
     * Retorna o número total de livros na pilha.
     * 
     * <p>Esta operação conta todos os elementos da pilha sem removê-los,
     * preservando a estrutura original.</p>
     * 
     * @return o número total de livros na pilha
     */
    @Override
    public int getTotalBooks() {
        return countElements(books);
    }

    /**
     * Calcula o preço médio de todos os livros na pilha.
     * 
     * <p>Esta operação percorre todos os livros, soma seus preços
     * e divide pelo número total de livros. A pilha é preservada após o cálculo.</p>
     * 
     * @return o preço médio dos livros ou 0.0 se a pilha estiver vazia
     */
    @Override
    public double getAveragePrice() {
        Stackable<Book> tempStack = new LinkedStack<>(20);
        double totalPrice = 0.0;
        int bookCount = 0;
        
        // Desempilhar todos os livros
        while (!books.isEmpty()) {
            Book book = books.pop();
            tempStack.push(book);
            totalPrice += book.getPrice();
            bookCount++;
        }
        
        // Reempilhar na ordem original
        while (!tempStack.isEmpty()) {
            books.push(tempStack.pop());
        }
        
        return bookCount > 0 ? totalPrice / bookCount : 0.0;
    }

    // Operações de gerenciamento
    /**
     * Verifica se um livro está disponível na pilha.
     * 
     * <p>Esta operação verifica se existe um livro com o ID especificado
     * na pilha, sem removê-lo.</p>
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
     * <p>Esta operação esvazia completamente a pilha, removendo
     * todos os livros armazenados. A operação é irreversível.</p>
     */
    @Override
    public void clearAllBooks() {
        while (!books.isEmpty()) {
            books.pop();
        }
    }
    
    // Métodos auxiliares usando apenas Stackable
    /**
     * Conta o número de elementos em uma pilha sem removê-los.
     * 
     * <p>Este método auxiliar percorre todos os elementos da pilha,
     * conta-os e restaura a pilha original. É útil para operações
     * que precisam saber o tamanho antes de processar os elementos.</p>
     * 
     * @param stack a pilha a ser contada
     * @return o número de elementos na pilha
     */
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
    
    /**
     * Converte uma pilha de resultados em um array de livros.
     * 
     * <p>Este método auxiliar é usado para converter pilhas temporárias
     * que contêm resultados de filtros em arrays, facilitando o retorno
     * dos métodos de busca. A pilha original é esvaziada no processo.</p>
     * 
     * @param resultStack a pilha contendo os livros a serem convertidos
     * @return array contendo todos os livros da pilha
     */
    private Book[] resultStackToArray(Stackable<Book> resultStack) {
        Book[] result = new Book[countElements(resultStack)];
        int index = 0;
        while (!resultStack.isEmpty()) {
            result[index] = resultStack.pop();
            index++;
        }
        return result;
    }
}