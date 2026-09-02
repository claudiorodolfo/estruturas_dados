package br.edu.ifba.vdc.bsi.linkedstackdao.app;

import br.edu.ifba.vdc.bsi.linkedstackdao.dao.BookDAO;
import br.edu.ifba.vdc.bsi.linkedstackdao.dao.BookDAOLinkedStack;
import br.edu.ifba.vdc.bsi.linkedstackdao.model.Book;
import java.time.LocalDate;

public class BookService {

    public static BookDAO getDBBook() {
        return new BookDAOLinkedStack();
    }
    
    void main() {
        BookDAO dbAccessor = BookService.getDBBook();
        
        IO.println("=== Sistema de Gerenciamento de Livros ===");
        IO.println("Usando implementação LinkedStack");
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1 -> adicionarLivro(dbAccessor);
                case 2 -> buscarLivroPorId(dbAccessor);
                case 3 -> atualizarLivro(dbAccessor);
                case 4 -> deletarLivro(dbAccessor);
                case 5 -> buscarLivrosPorAutor(dbAccessor);
                case 6 -> buscarLivroPorIsbn(dbAccessor);
                case 7 -> buscarLivrosMaisCaros(dbAccessor);
                case 8 -> buscarLivrosMaisBaratos(dbAccessor);
                case 9 -> listarTodosLivros(dbAccessor);
                case 10 -> imprimirLivros(dbAccessor);
                case 11 -> mostrarTotalLivros(dbAccessor);
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
        IO.println("10. Imprimir Livros (toString)");
        IO.println("11. Mostrar Total de Livros");
        IO.println("0.  Sair");
        
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(IO.readln("Escolha uma opção: ").trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void adicionarLivro(BookDAO dbAccessor) {
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
            dbAccessor.addBook(livro);
            
            IO.println("Livro adicionado com sucesso!");
            
        } catch (Exception e) {
            IO.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }
    
    private static void buscarLivroPorId(BookDAO dbAccessor) {
        IO.println("\n=== BUSCAR LIVRO POR ID ===");
        try {
            Long id = Long.parseLong(IO.readln("Digite o ID do livro: ").trim());
            Book livro = dbAccessor.getBook(id);
            
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
    
    private static void atualizarLivro(BookDAO dbAccessor) {
        IO.println("\n=== ATUALIZAR LIVRO ===");
        try {
            Long id = Long.parseLong(IO.readln("Digite o ID do livro a ser atualizado: ").trim());
            Book livroExistente = dbAccessor.getBook(id);
            
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
            dbAccessor.updateBook(livroAtualizado);
            
            IO.println("Livro atualizado com sucesso!");
            
        } catch (Exception e) {
            IO.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }
    
    private static void deletarLivro(BookDAO dbAccessor) {
        IO.println("\n=== DELETAR LIVRO ===");
        try {
            Long id = Long.parseLong(IO.readln("Digite o ID do livro a ser deletado: ").trim());
            Book livro = dbAccessor.deleteBook(id);
            
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
    
    private static void buscarLivrosPorAutor(BookDAO dbAccessor) {
        IO.println("\n=== BUSCAR LIVROS POR AUTOR ===");
        try {
            String autor = IO.readln("Digite o nome do autor: ");
            Book[] livros = dbAccessor.getBooksByAuthor(autor);
            
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
    
    private static void buscarLivroPorIsbn(BookDAO dbAccessor) {
        IO.println("\n=== BUSCAR LIVRO POR ISBN ===");
        try {
            String isbn = IO.readln("Digite o ISBN: ");
            Book livro = dbAccessor.getBookByIsbn(isbn);
            
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
    
    private static void buscarLivrosMaisCaros(BookDAO dbAccessor) {
        IO.println("\n=== BUSCAR LIVRO MAIS CARO ===");        
        try {
            Book livro = dbAccessor.getMostExpensiveBook();
            
            if (livro != null) {
                IO.println("Livro mais caro:");
                IO.println(livro.toString());
            } else {
                IO.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livros: " + e.getMessage());
        }
    }
    
    private static void buscarLivrosMaisBaratos(BookDAO dbAccessor) {
        IO.println("\n=== BUSCAR LIVRO MAIS BARATO ===");       
        try {
            Book livro = dbAccessor.getCheapestBook();
            
            if (livro != null) {
                IO.println("Livro mais barato:");
                IO.println(livro.toString());
            } else {
                IO.println("Nenhum livro encontrado!");
            }
        } catch (Exception e) {
            IO.println("Erro ao buscar livro: " + e.getMessage());
        }
    }  
    
    private static void listarTodosLivros(BookDAO dbAccessor) {
        IO.println("\n=== LISTAR TODOS OS LIVROS ===");
        
        try {
            Book[] livros = dbAccessor.getAllBooks();
            
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
    
    private static void imprimirLivros(BookDAO dbAccessor) {
        IO.println("\n=== IMPRIMIR LIVROS (toString) ===");
        
        try {
            String resultado = dbAccessor.printBooks();
            IO.println("Resultado:");
            IO.println(resultado);
        } catch (Exception e) {
            IO.println("Erro ao imprimir livros: " + e.getMessage());
        }
    }
    
    private static void mostrarTotalLivros(BookDAO dbAccessor) {
        IO.println("\n=== TOTAL DE LIVROS ===");
        
        try {
            int total = dbAccessor.getTotalBooks();
            IO.println("Total de livros: " + total);
        } catch (Exception e) {
            IO.println("Erro ao contar livros: " + e.getMessage());
        }
    }
}
