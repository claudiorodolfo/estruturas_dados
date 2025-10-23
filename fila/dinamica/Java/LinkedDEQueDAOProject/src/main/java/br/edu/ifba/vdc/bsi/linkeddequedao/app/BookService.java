package br.edu.ifba.vdc.bsi.linkeddequedao.app;

import br.edu.ifba.vdc.bsi.linkeddequedao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkeddequedao.dao.BookDAOLinkedDEQue;
import br.edu.ifba.vdc.bsi.linkeddequedao.model.Book;
import java.time.LocalDate;
import java.util.Scanner;

public class BookService {

    public static BookDAO getDBBook() {
        return new BookDAOLinkedDEQue();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO dbAccessor = BookService.getDBBook();
        
        System.out.println("=== Sistema de Gerenciamento de Livros ===");
        System.out.println("Usando implementação LinkedDEQue");
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcao = lerOpcao(scanner);
            
            switch (opcao) {
                case 1:
                    adicionarLivro(scanner, dbAccessor);
                    break;
                case 2:
                    buscarLivroPorId(scanner, dbAccessor);
                    break;
                case 3:
                    atualizarLivro(scanner, dbAccessor);
                    break;
                case 4:
                    deletarLivro(scanner, dbAccessor);
                    break;
                case 5:
                    buscarLivrosPorAutor(scanner, dbAccessor);
                    break;
                case 6:
                    buscarLivroPorIsbn(scanner, dbAccessor);
                    break;
                case 7:
                    buscarLivrosMaisCaros(scanner, dbAccessor);
                    break;
                case 8:
                    buscarLivrosMaisBaratos(scanner, dbAccessor);
                    break;
                case 9:
                    listarTodosLivros(dbAccessor);
                    break;
                case 10:
                    imprimirLivros(dbAccessor);
                    break;
                case 11:
                    mostrarTotalLivros(dbAccessor);
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
    
    private static int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void adicionarLivro(Scanner scanner, BookDAO dbAccessor) {
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
            dbAccessor.addBook(livro);
            
            System.out.println("Livro adicionado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }
    
    private static void buscarLivroPorId(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== BUSCAR LIVRO POR ID ===");
        System.out.print("Digite o ID do livro: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Book livro = dbAccessor.getBookById(id);
            
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
    
    private static void atualizarLivro(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== ATUALIZAR LIVRO ===");
        System.out.print("Digite o ID do livro a ser atualizado: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Book livroExistente = dbAccessor.getBookById(id);
            
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
            dbAccessor.updateBook(livroAtualizado);
            
            System.out.println("Livro atualizado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }
    
    private static void deletarLivro(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== DELETAR LIVRO ===");
        System.out.print("Digite o ID do livro a ser deletado: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Book livro = dbAccessor.deleteBook(id);
            
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
    
    private static void buscarLivrosPorAutor(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== BUSCAR LIVROS POR AUTOR ===");
        System.out.print("Digite o nome do autor: ");
        
        try {
            String autor = scanner.nextLine();
            Book[] livros = dbAccessor.getBooksByAuthor(autor);
            
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
    
    private static void buscarLivroPorIsbn(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== BUSCAR LIVRO POR ISBN ===");
        System.out.print("Digite o ISBN: ");
        
        try {
            String isbn = scanner.nextLine();
            Book livro = dbAccessor.getBookByIsbn(isbn);
            
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
    
    private static void buscarLivrosMaisCaros(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== BUSCAR LIVRO MAIS CAROS ===");        
        try {
            Book livro = dbAccessor.getMostExpensiveBook();
            
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
    
    private static void buscarLivrosMaisBaratos(Scanner scanner, BookDAO dbAccessor) {
        System.out.println("\n=== BUSCAR LIVROS MAIS BARATOS ===");       
        try {
            Book livro = dbAccessor.getCheapestBook();
            
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
    
    private static void listarTodosLivros(BookDAO dbAccessor) {
        System.out.println("\n=== LISTAR TODOS OS LIVROS ===");
        
        try {
            Book[] livros = dbAccessor.getAllBooks();
            
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
    
    private static void imprimirLivros(BookDAO dbAccessor) {
        System.out.println("\n=== IMPRIMIR LIVROS (toString) ===");
        
        try {
            String resultado = dbAccessor.printBooks();
            System.out.println("Resultado:");
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro ao imprimir livros: " + e.getMessage());
        }
    }
    
    private static void mostrarTotalLivros(BookDAO dbAccessor) {
        System.out.println("\n=== TOTAL DE LIVROS ===");
        
        try {
            int total = dbAccessor.getTotalBooks();
            System.out.println("Total de livros: " + total);
        } catch (Exception e) {
            System.out.println("Erro ao contar livros: " + e.getMessage());
        }
    }
}
