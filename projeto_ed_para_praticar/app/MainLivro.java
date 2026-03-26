package app;

import dao.*;
import model.Livro;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainLivro {
    public static void main(String[] args) {
        LivroDAO dao = new LivroDAOPilhaEstatica();
        //LivroDAOFilaEstatica dao = new LivroDAOFilaEstatica();
        //LivroDAOListaEstatica dao = new LivroDAOListaEstatica();
        Scanner sc = new Scanner(System.in);

        boolean sair = false;
        while (!sair) {
            printMenu();
            int opcao = readInt(sc, "Escolha uma opção: ");

            switch (opcao) {
                case 1: { // carregar exemplos
                    carregarExemplos(dao);
                    System.out.println("Exemplos carregados.");
                    break;
                }
                case 2: { // adicionar
                    Livro livro = lerLivro(sc, "Cadastro de livro");
                    dao.addLivro(livro);
                    System.out.println("Livro cadastrado: " + livro);
                    break;
                }
                case 3: { // listar
                    System.out.println("Livros: " + dao.printLivros());
                    break;
                }
                case 4: { // total
                    System.out.println("Total de livros: " + dao.getTotalLivros());
                    break;
                }
                case 5: { // buscar por id
                    long id = readLong(sc, "ID: ");
                    System.out.println("Resultado: " + dao.getLivroPorId(id));
                    break;
                }
                case 6: { // buscar por ISBN
                    String isbn = readNonEmptyString(sc, "ISBN: ");
                    System.out.println("Resultado: " + dao.getLivroPorIsbn(isbn));
                    break;
                }
                case 7: { // buscar por autor
                    String autor = readNonEmptyString(sc, "Autor: ");
                    System.out.println("Resultados: " + dao.toStringArray(dao.getLivrosPorAutor(autor)));
                    break;
                }
                case 8: { // buscar por titulo
                    String titulo = readNonEmptyString(sc, "Título: ");
                    System.out.println("Resultados: " + dao.toStringArray(dao.getLivrosPorTitulo(titulo)));
                    break;
                }
                case 9: { // buscar por data exata
                    LocalDate data = readLocalDate(sc, "Data de publicação (yyyy-MM-dd): ");
                    System.out.println("Resultados: " + dao.toStringArray(dao.getLivrosPorDataPublicacao(data)));
                    break;
                }
                case 10: { // buscar por faixa de preço
                    double min = readDouble(sc, "Preço mínimo: ");
                    double max = readDouble(sc, "Preço máximo: ");
                    System.out.println("Resultados: " + dao.toStringArray(dao.getLivrosPorFaixaPreco(min, max)));
                    break;
                }
                case 11: { // buscar por faixa de datas
                    LocalDate min = readLocalDate(sc, "Data mínima (yyyy-MM-dd): ");
                    LocalDate max = readLocalDate(sc, "Data máxima (yyyy-MM-dd): ");
                    System.out.println("Resultados: " + dao.toStringArray(dao.getLivrosPorFaixaData(min, max)));
                    break;
                }
                case 12: { // atualizar
                    long id = readLong(sc, "ID do livro para atualizar: ");
                    Livro existente = dao.getLivroPorId(id);
                    if (existente == null) {
                        System.out.println("Não encontrado.");
                        break;
                    }
                    Livro atualizado = lerLivro(sc, "Atualização (ID " + id + ")", id);
                    dao.updateLivro(atualizado);
                    System.out.println("Atualizado: " + dao.getLivroPorId(id));
                    break;
                }
                case 13: { // remover por id
                    long id = readLong(sc, "ID para remover: ");
                    System.out.println("Removido: " + dao.deleteLivro(id));
                    break;
                }
                case 14: { // remover por titulo
                    String titulo = readNonEmptyString(sc, "Título para remover: ");
                    System.out.println("Removidos: " + dao.toStringArray(dao.deleteLivrosPorTitulo(titulo)));
                    break;
                }
                case 15: { // estatísticas
                    System.out.println("Total: " + dao.getTotalLivros());
                    System.out.println("Preço médio: " + dao.getPrecoMedio());
                    System.out.println("Mais caro: " + dao.getLivroMaisCaro());
                    System.out.println("Mais barato: " + dao.getLivroMaisBarato());
                    System.out.println("Mais novo: " + dao.getLivroMaisNovo());
                    System.out.println("Mais antigo: " + dao.getLivroMaisAntigo());
                    break;
                }
                case 16: { // limpar
                    dao.clearAllLivros();
                    System.out.println("Todos os livros foram removidos.");
                    break;
                }
                case 0: {
                    sair = true;
                    System.out.println("Saindo...");
                    break;
                }
                default:
                    System.out.println("Opção inválida.");
            }

            if (!sair) {
                System.out.println();
            }
        }
    }

    private static void printMenu() {
        System.out.println("==== MENU LIVROS (PILHA ESTÁTICA) ====");
        System.out.println("1) Carregar exemplos");
        System.out.println("2) Cadastrar livro");
        System.out.println("3) Listar livros");
        System.out.println("4) Total de livros");
        System.out.println("5) Buscar por ID");
        System.out.println("6) Buscar por ISBN");
        System.out.println("7) Buscar por autor");
        System.out.println("8) Buscar por título");
        System.out.println("9) Buscar por data de publicação (exata)");
        System.out.println("10) Buscar por faixa de preço");
        System.out.println("11) Buscar por faixa de datas");
        System.out.println("12) Atualizar livro");
        System.out.println("13) Remover por ID");
        System.out.println("14) Remover por título");
        System.out.println("15) Estatísticas");
        System.out.println("16) Limpar todos");
        System.out.println("0) Sair");
    }

    private static void carregarExemplos(LivroDAO dao) {
        dao.addLivro(new Livro(1L, "Estruturas de Dados", "Fulano", LocalDate.of(2020, 1, 10), "ISBN-001", 79.90));
        dao.addLivro(new Livro(2L, "Algoritmos", "Beltrano", LocalDate.of(2018, 5, 2), "ISBN-002", 59.50));
        dao.addLivro(new Livro(3L, "Estruturas de Dados", "Fulano", LocalDate.of(2022, 7, 15), "ISBN-003", 99.99));
    }

    private static Livro lerLivro(Scanner sc, String tituloTela) {
        System.out.println("--- " + tituloTela + " ---");
        long id = readLong(sc, "ID (número): ");
        return lerLivro(sc, tituloTela, id);
    }

    private static Livro lerLivro(Scanner sc, String tituloTela, long idFixo) {
        System.out.println("--- " + tituloTela + " ---");
        String titulo = readNonEmptyString(sc, "Título: ");
        String autor = readOptionalString(sc, "Autor (opcional): ");
        LocalDate data = readOptionalLocalDate(sc, "Data de publicação (yyyy-MM-dd, opcional): ");
        String isbn = readOptionalString(sc, "ISBN (opcional): ");
        Double preco = readOptionalDouble(sc, "Preço (opcional): ");

        // Evita NullPointer no toString() atual do Livro caso preço fique nulo.
        if (preco == null) {
            preco = 0.0;
        }

        return new Livro(idFixo, titulo, autor, data, isbn, preco);
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Informe um número inteiro válido.");
            }
        }
    }

    private static long readLong(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                System.out.println("Informe um número válido.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Informe um número válido (ex: 10.50).");
            }
        }
    }

    private static Double readOptionalDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                return null;
            }
            s = s.replace(",", ".");
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Informe um número válido ou deixe em branco.");
            }
        }
    }

    private static String readNonEmptyString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Campo obrigatório.");
        }
    }

    private static String readOptionalString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        return s.isEmpty() ? null : s;
    }

    private static LocalDate readLocalDate(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato yyyy-MM-dd.");
            }
        }
    }

    private static LocalDate readOptionalLocalDate(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use yyyy-MM-dd ou deixe em branco.");
            }
        }
    }
}

