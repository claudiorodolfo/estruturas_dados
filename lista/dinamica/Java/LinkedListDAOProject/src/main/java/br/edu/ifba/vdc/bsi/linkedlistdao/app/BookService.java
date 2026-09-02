package br.edu.ifba.vdc.bsi.linkedlistdao.app;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.BookDAOLinkedList;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.BookDAOSQLite;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Book;
import java.time.LocalDate;

/**
 * Classe principal que fornece serviços de gerenciamento de livros.
 * Implementa um sistema de menu interativo via console para realizar operações
 * CRUD (Create, Read, Update, Delete) e consultas sobre livros.
 * 
 * <p>Esta classe suporta diferentes tipos de repositórios através do enum
 * {@link RepositoryType}, permitindo alternar entre implementações SQLite
 * e LinkedList para armazenamento de dados.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-11-06
 * @see BookDAO
 * @see Book
 * @see RepositoryType
 * @see BookDAOLinkedList
 * @see BookDAOSQLite
 */
public class BookService {

    /**
     * Enumeração que define os tipos de repositório disponíveis para
     * armazenamento de livros.
     */
    public enum RepositoryType {
        /** Repositório baseado em SQLite (banco de dados) */
        SQLITE,
        /** Repositório baseado em LinkedList (estrutura de dados em memória) */
        LINKEDLIST
    }

    /**
     * Obtém uma instância de BookDAO baseada no tipo de repositório especificado.
     * 
     * @param type o tipo de repositório a ser instanciado
     * @return uma instância de BookDAO correspondente ao tipo especificado
     * @throws IllegalArgumentException se o tipo de repositório for inválido
     */
    public static BookDAO getRepositoryBook(RepositoryType type) {
        switch (type) {
            case SQLITE -> return new BookDAOSQLite();
            case LINKEDLIST -> return new BookDAOLinkedList();
            default -> throw new IllegalArgumentException("Tipo de implementação inválido: " + type);
        }
    }
    
    /**
     * Método principal que inicia o sistema de gerenciamento de livros.
     * Exibe um menu interativo permitindo ao usuário realizar diversas operações
     * sobre livros, como adicionar, buscar, atualizar, deletar e listar livros.
     * 
     * <p>O sistema utiliza por padrão o repositório SQLite para armazenamento.
     * O loop principal continua até que o usuário escolha a opção de sair (0).
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    void main() {
        BookDAO repositoryAccessor = BookService.getRepositoryBook(RepositoryType.SQLITE);
        
        IO.println("=== Sistema de Gerenciamento de Livros ===");
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1 -> adicionarLivro(repositoryAccessor);
                case 2 -> buscarLivroPorId(repositoryAccessor);
                case 3 -> atualizarLivro(repositoryAccessor);
                case 4 -> deletarLivro(repositoryAccessor);
                case 5 -> buscarLivrosPorAutor(repositoryAccessor);
                case 6 -> buscarLivroPorIsbn(repositoryAccessor);
                case 7 -> buscarLivrosMaisCaros(repositoryAccessor);
                case 8 -> buscarLivrosMaisBaratos(repositoryAccessor);
                case 9 -> listarTodosLivros(repositoryAccessor);
                case 10 -> imprimirLivros(repositoryAccessor);
                case 11 -> mostrarTotalLivros(repositoryAccessor);
                case 0 -> {
                    continuar = false;
                    IO.println("Saindo do sistema...");
                }
                default -> IO.println("Opção inválida! Tente novamente.");
            }
            
            if (continuar) {
                IO.readln("\nPressione Enter para continuar...");
            }
        }
    }
    
    /**
     * Exibe o menu principal do sistema com todas as opções disponíveis.
     * O menu apresenta as operações possíveis numeradas de 0 a 11.
     */
    private static void mostrarMenu() {
        IO.println("\n=== MENU PRINCIPAL ===");
        IO.println("1.  Adicionar Livro");
        IO.println("2.  Buscar Livro por ID");
        IO.println("3.  Atualizar Livro");
        IO.println("4.  Deletar Livro");
        IO.println("5.  Buscar Livros por Autor");
        IO.println("6.  Buscar Livro por ISBN");
        IO.println("7.  Buscar Livro Mais Caro");
        IO.println("8.  Buscar Livro Mais Barato");
        IO.println("9. Listar Todos os Livros");
        IO.println("10. Imprimir Livros");
        IO.println("11. Mostrar Total de Livros");
        IO.println("0.  Sair");
        
    }
    
    /**
     * Lê e valida a opção escolhida pelo usuário no menu.
     *
     * @return o número da opção escolhida, ou -1 se a entrada for inválida
     */
    private static int lerOpcao() {
        try {
            return Integer.parseInt(IO.readln("Escolha uma opção: ").trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Adiciona um novo livro ao repositório.
     * Solicita ao usuário os dados do livro (ID, título, autor, data de publicação,
     * ISBN e preço) e os persiste no repositório.
     *
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void adicionarLivro(BookDAO repositoryAccessor) {
        IO.println("\n=== ADICIONAR LIVRO ===");
        
        try {
            Long id = Long.parseLong(IO.readln("ID: ").trim());
            
            String titulo = IO.readln("Título: ");
            
            String autor = IO.readln("Autor: ");
            
            String dataStr = IO.readln("Data de Publicação (yyyy-mm-dd): ");
            LocalDate dataPublicacao = LocalDate.parse(dataStr);
            
            String isbn = IO.readln("ISBN: ");
            
            Double preco = Double.parseDouble(IO.readln("Preço: ").trim());
            
            Book livro = new Book(id, titulo, autor, dataPublicacao, isbn, preco);
            repositoryAccessor.addBook(livro);
            
            IO.println("Livro adicionado com sucesso!");
            
        } catch (Exception e) {
            IO.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }
    
    /**
     * Busca um livro no repositório pelo seu ID.
     * Solicita o ID do livro ao usuário e exibe as informações do livro encontrado,
     * ou uma mensagem indicando que o livro não foi encontrado.
     *
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivroPorId(BookDAO repositoryAccessor) {
        IO.println("\n=== BUSCAR LIVRO POR ID ===");
        try {
            Long id = Long.parseLong(IO.readln("Digite o ID do livro: ").trim());
            Book livro = repositoryAccessor.getBookById(id);
            
            if (livro != null) {
                IO.println("Livro encontrado:");
                IO.println(livro.toString());
            } else {
                IO.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livro: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza os dados de um livro existente no repositório.
     * Solicita o ID do livro a ser atualizado e, se encontrado, solicita os novos
     * dados (título, autor, data de publicação, ISBN e preço) para atualização.
     *
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void atualizarLivro(BookDAO repositoryAccessor) {
        IO.println("\n=== ATUALIZAR LIVRO ===");
        try {
            Long id = Long.parseLong(IO.readln("Digite o ID do livro a ser atualizado: ").trim());
            Book livroExistente = repositoryAccessor.getBookById(id);
            
            if (livroExistente == null) {
                IO.println("Livro não encontrado!");
                return;
            }
            
            IO.println("Livro atual: " + livroExistente.toString());
            IO.println("\nDigite os novos dados:");
            
            String novoTitulo = IO.readln("Novo título: ");
            
            String novoAutor = IO.readln("Novo autor: ");
            
            String novaDataStr = IO.readln("Nova data de publicação (yyyy-mm-dd): ");
            LocalDate novaDataPublicacao = LocalDate.parse(novaDataStr);
            
            String novoIsbn = IO.readln("Novo ISBN: ");
            
            Double novoPreco = Double.parseDouble(IO.readln("Novo preço: ").trim());
            
            Book livroAtualizado = new Book(id, novoTitulo, novoAutor, novaDataPublicacao, novoIsbn, novoPreco);
            repositoryAccessor.updateBook(livroAtualizado);
            
            IO.println("Livro atualizado com sucesso!");
            
        } catch (Exception e) {
            IO.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }
    
    /**
     * Remove um livro do repositório pelo seu ID.
     * Solicita o ID do livro a ser deletado e, se encontrado, remove-o do repositório
     * e exibe as informações do livro deletado.
     *
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void deletarLivro(BookDAO repositoryAccessor) {
        IO.println("\n=== DELETAR LIVRO ===");
        try {
            Long id = Long.parseLong(IO.readln("Digite o ID do livro a ser deletado: ").trim());
            Book livro = repositoryAccessor.deleteBook(id);
            
            if (livro != null) {
                IO.println("Livro deletado com sucesso:");
                IO.println(livro.toString());
            } else {
                IO.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao deletar livro: " + e.getMessage());
        }
    }
    
    /**
     * Busca todos os livros de um determinado autor no repositório.
     * Solicita o nome do autor ao usuário e exibe todos os livros encontrados
     * para esse autor, ou uma mensagem indicando que nenhum livro foi encontrado.
     *
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivrosPorAutor(BookDAO repositoryAccessor) {
        IO.println("\n=== BUSCAR LIVROS POR AUTOR ===");
        try {
            String autor = IO.readln("Digite o nome do autor: ");
            Book[] livros = repositoryAccessor.getBooksByAuthor(autor);
            
            if (livros != null && livros.length > 0) {
                IO.println("Livros encontrados:");
                for (Book livro : livros) {
                    IO.println(livro.toString());
                }
            } else {
                IO.println("Nenhum livro encontrado para este autor!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livros: " + e.getMessage());
        }
    }
    
    /**
     * Busca um livro no repositório pelo seu ISBN.
     * Solicita o ISBN do livro ao usuário e exibe as informações do livro encontrado,
     * ou uma mensagem indicando que o livro não foi encontrado.
     *
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivroPorIsbn(BookDAO repositoryAccessor) {
        IO.println("\n=== BUSCAR LIVRO POR ISBN ===");
        try {
            String isbn = IO.readln("Digite o ISBN: ");
            Book livro = repositoryAccessor.getBookByIsbn(isbn);
            
            if (livro != null) {
                IO.println("Livro encontrado:");
                IO.println(livro.toString());
            } else {
                IO.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livro: " + e.getMessage());
        }
    }
    
    /**
     * Busca e exibe o livro mais caro do repositório.
     * Obtém o livro com o maior preço e exibe suas informações, ou uma mensagem
     * indicando que nenhum livro foi encontrado.
     * 
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivrosMaisCaros(BookDAO repositoryAccessor) {
        IO.println("\n=== BUSCAR LIVRO MAIS CAROS ===");        
        try {
            Book livro = repositoryAccessor.getMostExpensiveBook();
            
            if (livro != null) {
                IO.println("Livro mais caros:");
                IO.println(livro.toString());
            } else {
                IO.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livros: " + e.getMessage());
        }
    }
    
    /**
     * Busca e exibe o livro mais barato do repositório.
     * Obtém o livro com o menor preço e exibe suas informações, ou uma mensagem
     * indicando que nenhum livro foi encontrado.
     * 
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivrosMaisBaratos(BookDAO repositoryAccessor) {
        IO.println("\n=== BUSCAR LIVROS MAIS BARATOS ===");       
        try {
            Book livro = repositoryAccessor.getCheapestBook();
            
            if (livro != null) {
                IO.println("Livro mais baratos:");
                IO.println(livro.toString());
            } else {
                IO.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livro: " + e.getMessage());
        }
    }  
    
    /**
     * Lista todos os livros armazenados no repositório.
     * Exibe as informações de todos os livros cadastrados, ou uma mensagem
     * indicando que nenhum livro foi encontrado.
     * 
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void listarTodosLivros(BookDAO repositoryAccessor) {
        IO.println("\n=== LISTAR TODOS OS LIVROS ===");
        
        try {
            Book[] livros = repositoryAccessor.getAllBooks();
            
            if (livros != null && livros.length > 0) {
                IO.println("Todos os livros:");
                for (Book livro : livros) {
                    IO.println(livro.toString());
                }
            } else {
                IO.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao listar livros: " + e.getMessage());
        }
    }
    
    /**
     * Imprime uma representação em string de todos os livros do repositório.
     * Utiliza o método printBooks() do repositório para obter uma representação
     * formatada de todos os livros e a exibe no console.
     * 
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void imprimirLivros(BookDAO repositoryAccessor) {
        IO.println("\n=== IMPRIMIR LIVROS (toString) ===");
        
        try {
            String resultado = repositoryAccessor.printBooks();
            IO.println("Resultado:");
            IO.println(resultado);
        } catch (Exception e) {
            IO.println("Erro ao imprimir livros: " + e.getMessage());
        }
    }
    
    /**
     * Exibe o total de livros armazenados no repositório.
     * Obtém a contagem total de livros e a exibe no console.
     * 
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void mostrarTotalLivros(BookDAO repositoryAccessor) {
        IO.println("\n=== TOTAL DE LIVROS ===");
        
        try {
            int total = repositoryAccessor.getTotalBooks();
            IO.println("Total de livros: " + total);
        } catch (Exception e) {
            IO.println("Erro ao contar livros: " + e.getMessage());
        }
    }
}
