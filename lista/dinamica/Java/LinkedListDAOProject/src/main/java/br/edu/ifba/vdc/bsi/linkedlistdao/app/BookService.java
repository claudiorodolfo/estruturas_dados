package br.edu.ifba.vdc.bsi.linkedlistdao.app;

import br.edu.ifba.vdc.bsi.linkedlistdao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.BookDAOLinkedList;
import br.edu.ifba.vdc.bsi.linkedlistdao.dao.BookDAOSQLite;
import br.edu.ifba.vdc.bsi.linkedlistdao.model.Book;
import java.time.LocalDate;
import java.util.Scanner;

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
            case SQLITE:
                return new BookDAOSQLite();
            case LINKEDLIST:
                return new BookDAOLinkedList();
            default:
                throw new IllegalArgumentException("Tipo de implementação inválido: " + type);
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO repositoryAccessor = BookService.getRepositoryBook(RepositoryType.SQLITE);
        
        System.out.println("=== Sistema de Gerenciamento de Livros ===");
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcao = lerOpcao(scanner);
            
            switch (opcao) {
                case 1:
                    adicionarLivro(scanner, repositoryAccessor);
                    break;
                case 2:
                    buscarLivroPorId(scanner, repositoryAccessor);
                    break;
                case 3:
                    atualizarLivro(scanner, repositoryAccessor);
                    break;
                case 4:
                    deletarLivro(scanner, repositoryAccessor);
                    break;
                case 5:
                    buscarLivrosPorAutor(scanner, repositoryAccessor);
                    break;
                case 6:
                    buscarLivroPorIsbn(scanner, repositoryAccessor);
                    break;
                case 7:
                    buscarLivrosMaisCaros(repositoryAccessor);
                    break;
                case 8:
                    buscarLivrosMaisBaratos(repositoryAccessor);
                    break;
                case 9:
                    listarTodosLivros(repositoryAccessor);
                    break;
                case 10:
                    imprimirLivros(repositoryAccessor);
                    break;
                case 11:
                    mostrarTotalLivros(repositoryAccessor);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            if (continuar) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Exibe o menu principal do sistema com todas as opções disponíveis.
     * O menu apresenta as operações possíveis numeradas de 0 a 11.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1.  Adicionar Livro");
        System.out.println("2.  Buscar Livro por ID");
        System.out.println("3.  Atualizar Livro");
        System.out.println("4.  Deletar Livro");
        System.out.println("5.  Buscar Livros por Autor");
        System.out.println("6.  Buscar Livro por ISBN");
        System.out.println("7.  Buscar Livro Mais Caro");
        System.out.println("8.  Buscar Livro Mais Barato");
        System.out.println("9. Listar Todos os Livros");
        System.out.println("10. Imprimir Livros");
        System.out.println("11. Mostrar Total de Livros");
        System.out.println("0.  Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Lê e valida a opção escolhida pelo usuário no menu.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @return o número da opção escolhida, ou -1 se a entrada for inválida
     */
    private static int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Adiciona um novo livro ao repositório.
     * Solicita ao usuário os dados do livro (ID, título, autor, data de publicação,
     * ISBN e preço) e os persiste no repositório.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void adicionarLivro(Scanner scanner, BookDAO repositoryAccessor) {
        System.out.println("\n=== ADICIONAR LIVRO ===");
        
        try {
            System.out.print("ID: ");
            Long id = Long.parseLong(scanner.nextLine());
            
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            
            System.out.print("Data de Publicação (yyyy-mm-dd): ");
            String dataStr = scanner.nextLine();
            LocalDate dataPublicacao = LocalDate.parse(dataStr);
            
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            
            System.out.print("Preço: ");
            Double preco = Double.parseDouble(scanner.nextLine());
            
            Book livro = new Book(id, titulo, autor, dataPublicacao, isbn, preco);
            repositoryAccessor.addBook(livro);
            
            System.out.println("Livro adicionado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }
    
    /**
     * Busca um livro no repositório pelo seu ID.
     * Solicita o ID do livro ao usuário e exibe as informações do livro encontrado,
     * ou uma mensagem indicando que o livro não foi encontrado.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivroPorId(Scanner scanner, BookDAO repositoryAccessor) {
        System.out.println("\n=== BUSCAR LIVRO POR ID ===");
        System.out.print("Digite o ID do livro: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Book livro = repositoryAccessor.getBookById(id);
            
            if (livro != null) {
                System.out.println("Livro encontrado:");
                System.out.println(livro.toString());
            } else {
                System.out.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza os dados de um livro existente no repositório.
     * Solicita o ID do livro a ser atualizado e, se encontrado, solicita os novos
     * dados (título, autor, data de publicação, ISBN e preço) para atualização.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void atualizarLivro(Scanner scanner, BookDAO repositoryAccessor) {
        System.out.println("\n=== ATUALIZAR LIVRO ===");
        System.out.print("Digite o ID do livro a ser atualizado: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Book livroExistente = repositoryAccessor.getBookById(id);
            
            if (livroExistente == null) {
                System.out.println("Livro não encontrado!");
                return;
            }
            
            System.out.println("Livro atual: " + livroExistente.toString());
            System.out.println("\nDigite os novos dados:");
            
            System.out.print("Novo título: ");
            String novoTitulo = scanner.nextLine();
            
            System.out.print("Novo autor: ");
            String novoAutor = scanner.nextLine();
            
            System.out.print("Nova data de publicação (yyyy-mm-dd): ");
            String novaDataStr = scanner.nextLine();
            LocalDate novaDataPublicacao = LocalDate.parse(novaDataStr);
            
            System.out.print("Novo ISBN: ");
            String novoIsbn = scanner.nextLine();
            
            System.out.print("Novo preço: ");
            Double novoPreco = Double.parseDouble(scanner.nextLine());
            
            Book livroAtualizado = new Book(id, novoTitulo, novoAutor, novaDataPublicacao, novoIsbn, novoPreco);
            repositoryAccessor.updateBook(livroAtualizado);
            
            System.out.println("Livro atualizado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }
    
    /**
     * Remove um livro do repositório pelo seu ID.
     * Solicita o ID do livro a ser deletado e, se encontrado, remove-o do repositório
     * e exibe as informações do livro deletado.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void deletarLivro(Scanner scanner, BookDAO repositoryAccessor) {
        System.out.println("\n=== DELETAR LIVRO ===");
        System.out.print("Digite o ID do livro a ser deletado: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Book livro = repositoryAccessor.deleteBook(id);
            
            if (livro != null) {
                System.out.println("Livro deletado com sucesso:");
                System.out.println(livro.toString());
            } else {
                System.out.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar livro: " + e.getMessage());
        }
    }
    
    /**
     * Busca todos os livros de um determinado autor no repositório.
     * Solicita o nome do autor ao usuário e exibe todos os livros encontrados
     * para esse autor, ou uma mensagem indicando que nenhum livro foi encontrado.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivrosPorAutor(Scanner scanner, BookDAO repositoryAccessor) {
        System.out.println("\n=== BUSCAR LIVROS POR AUTOR ===");
        System.out.print("Digite o nome do autor: ");
        
        try {
            String autor = scanner.nextLine();
            Book[] livros = repositoryAccessor.getBooksByAuthor(autor);
            
            if (livros != null && livros.length > 0) {
                System.out.println("Livros encontrados:");
                for (Book livro : livros) {
                    System.out.println(livro.toString());
                }
            } else {
                System.out.println("Nenhum livro encontrado para este autor!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
    }
    
    /**
     * Busca um livro no repositório pelo seu ISBN.
     * Solicita o ISBN do livro ao usuário e exibe as informações do livro encontrado,
     * ou uma mensagem indicando que o livro não foi encontrado.
     * 
     * @param scanner o objeto Scanner para leitura da entrada do usuário
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void buscarLivroPorIsbn(Scanner scanner, BookDAO repositoryAccessor) {
        System.out.println("\n=== BUSCAR LIVRO POR ISBN ===");
        System.out.print("Digite o ISBN: ");
        
        try {
            String isbn = scanner.nextLine();
            Book livro = repositoryAccessor.getBookByIsbn(isbn);
            
            if (livro != null) {
                System.out.println("Livro encontrado:");
                System.out.println(livro.toString());
            } else {
                System.out.println("Livro não encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
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
        System.out.println("\n=== BUSCAR LIVRO MAIS CAROS ===");        
        try {
            Book livro = repositoryAccessor.getMostExpensiveBook();
            
            if (livro != null) {
                System.out.println("Livro mais caros:");
                System.out.println(livro.toString());
            } else {
                System.out.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
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
        System.out.println("\n=== BUSCAR LIVROS MAIS BARATOS ===");       
        try {
            Book livro = repositoryAccessor.getCheapestBook();
            
            if (livro != null) {
                System.out.println("Livro mais baratos:");
                System.out.println(livro.toString());
            } else {
                System.out.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
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
        System.out.println("\n=== LISTAR TODOS OS LIVROS ===");
        
        try {
            Book[] livros = repositoryAccessor.getAllBooks();
            
            if (livros != null && livros.length > 0) {
                System.out.println("Todos os livros:");
                for (Book livro : livros) {
                    System.out.println(livro.toString());
                }
            } else {
                System.out.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
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
        System.out.println("\n=== IMPRIMIR LIVROS (toString) ===");
        
        try {
            String resultado = repositoryAccessor.printBooks();
            System.out.println("Resultado:");
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro ao imprimir livros: " + e.getMessage());
        }
    }
    
    /**
     * Exibe o total de livros armazenados no repositório.
     * Obtém a contagem total de livros e a exibe no console.
     * 
     * @param repositoryAccessor o objeto BookDAO para acesso ao repositório
     */
    private static void mostrarTotalLivros(BookDAO repositoryAccessor) {
        System.out.println("\n=== TOTAL DE LIVROS ===");
        
        try {
            int total = repositoryAccessor.getTotalBooks();
            System.out.println("Total de livros: " + total);
        } catch (Exception e) {
            System.out.println("Erro ao contar livros: " + e.getMessage());
        }
    }
}
