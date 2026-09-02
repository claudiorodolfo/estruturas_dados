package app;

import model.Livro;
import dao.LivroDAO;
import dao.livro.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MainLivro {
    void main() {
        LivroDAO dao = new LivroDAOPilhaEstatica();
        //LivroDAO dao = new LivroDAOPilhaDinamica();
        //LivroDAO dao = new LivroDAOFilaEstatica();
        //LivroDAO dao = new LivroDAOFilaDinamica();
        //LivroDAO dao = new LivroDAOListaEstatica();
        //LivroDAO dao = new LivroDAOListaDinamica();

        boolean sair = false;
        while (!sair) {
            printMenu();
            int opcao = Integer.parseInt(IO.readln("Escolha uma opção: ").trim());

            switch (opcao) {
                case 1 -> {
                    // carregar exemplos
                    carregarExemplos(dao);
                    IO.println("Exemplos carregados.");
                }
                case 2 -> {
                    // adicionar
                    Livro livro = lerLivro("Cadastro de livro");
                    dao.addLivro(livro);
                    IO.println("Livro cadastrado: " + livro);
                }
                case 3 -> {
                    // listar
                    IO.println("Livros: " + dao.printLivros());
                }
                case 4 -> {
                    // total
                    IO.println("Total de livros: " + dao.getTotalLivros());
                }
                case 5 -> {
                    // buscar por id
                    long id = Long.parseLong(IO.readln("ID: ").trim());
                    IO.println("Resultado: " + dao.getLivroPorId(id));
                }
                case 6 -> {
                    // buscar por ISBN
                    String isbn = readNonEmptyString("ISBN: ");
                    IO.println("Resultado: " + dao.getLivroPorIsbn(isbn));
                }
                case 7 -> {
                    // buscar por autor
                    String autor = readNonEmptyString("Autor: ");
                    IO.println("Resultados: " + dao.toStringArray(dao.getLivrosPorAutor(autor)));
                }
                case 8 -> {
                    // buscar por titulo
                    String titulo = readNonEmptyString("Título: ");
                    IO.println("Resultados: " + dao.toStringArray(dao.getLivrosPorTitulo(titulo)));
                }
                case 9 -> {
                    // buscar por data exata
                    LocalDate data = readLocalDate("Data de publicação (yyyy-MM-dd): ");
                    IO.println("Resultados: " + dao.toStringArray(dao.getLivrosPorDataPublicacao(data)));
                }
                case 10 -> {
                    // buscar por faixa de preço
                    double min = Double.parseDouble(IO.readln("Preço mínimo: ").trim().replace(",", "."));
                    double max = Double.parseDouble(IO.readln("Preço máximo: ").trim().replace(",", "."));
                    IO.println("Resultados: " + dao.toStringArray(dao.getLivrosPorFaixaPreco(min, max)));
                }
                case 11 -> {
                    // buscar por faixa de datas
                    LocalDate min = readLocalDate("Data mínima (yyyy-MM-dd): ");
                    LocalDate max = readLocalDate("Data máxima (yyyy-MM-dd): ");
                    IO.println("Resultados: " + dao.toStringArray(dao.getLivrosPorFaixaData(min, max)));
                }
                case 12 -> {
                    long id = Long.parseLong(IO.readln("ID do livro para atualizar: ").trim());
                    Livro existente = dao.getLivroPorId(id);
                    if (existente == null) {
                        IO.println("Não encontrado.");
                    } else {
                        Livro atualizado = lerLivro("Atualização (ID " + id + ")", id);
                        dao.updateLivro(atualizado);
                        IO.println("Atualizado: " + dao.getLivroPorId(id));
                    }
                }
                case 13 -> {
                    // remover por id
                    long id = Long.parseLong(IO.readln("ID para remover: ").trim());
                    IO.println("Removido: " + dao.deleteLivro(id));
                }
                case 14 -> {
                    // remover por titulo
                    String titulo = readNonEmptyString("Título para remover: ");
                    IO.println("Removidos: " + dao.toStringArray(dao.deleteLivrosPorTitulo(titulo)));
                }
                case 15 -> {
                    // estatísticas
                    IO.println("Total: " + dao.getTotalLivros());
                    IO.println("Preço médio: " + dao.getPrecoMedio());
                    IO.println("Mais caro: " + dao.getLivroMaisCaro());
                    IO.println("Mais barato: " + dao.getLivroMaisBarato());
                    IO.println("Mais novo: " + dao.getLivroMaisNovo());
                    IO.println("Mais antigo: " + dao.getLivroMaisAntigo());
                }
                case 16 -> {
                    // limpar
                    dao.clearAllLivros();
                    IO.println("Todos os livros foram removidos.");
                }
                case 0 -> {
                    sair = true;
                    IO.println("Saindo...");
                }
                default -> IO.println("Opção inválida.");
            }

            if (!sair) {
                IO.println("");
            }
        }
    }

    private static void printMenu() {
        IO.println("==== MENU LIVROS (PILHA ESTÁTICA) ====");
        IO.println("1) Carregar exemplos");
        IO.println("2) Cadastrar livro");
        IO.println("3) Listar livros");
        IO.println("4) Total de livros");
        IO.println("5) Buscar por ID");
        IO.println("6) Buscar por ISBN");
        IO.println("7) Buscar por autor");
        IO.println("8) Buscar por título");
        IO.println("9) Buscar por data de publicação (exata)");
        IO.println("10) Buscar por faixa de preço");
        IO.println("11) Buscar por faixa de datas");
        IO.println("12) Atualizar livro");
        IO.println("13) Remover por ID");
        IO.println("14) Remover por título");
        IO.println("15) Estatísticas");
        IO.println("16) Limpar todos");
        IO.println("0) Sair");
    }

    private static void carregarExemplos(LivroDAO dao) {
        dao.addLivro(new Livro(1L, "Estruturas de Dados", "Fulano", LocalDate.of(2020, 1, 10), "ISBN-001", 79.90));
        dao.addLivro(new Livro(2L, "Algoritmos", "Beltrano", LocalDate.of(2018, 5, 2), "ISBN-002", 59.50));
        dao.addLivro(new Livro(3L, "Estruturas de Dados", "Fulano", LocalDate.of(2022, 7, 15), "ISBN-003", 99.99));
    }

    private static Livro lerLivro(String tituloTela) {
        IO.println("--- " + tituloTela + " ---");
        long id = Long.parseLong(IO.readln("ID (número): ").trim());
        return lerLivro(tituloTela, id);
    }

    private static Livro lerLivro(String tituloTela, long idFixo) {
        IO.println("--- " + tituloTela + " ---");
        String titulo = readNonEmptyString("Título: ");
        String autor = readOptionalString("Autor (opcional): ");
        LocalDate data = readOptionalLocalDate("Data de publicação (yyyy-MM-dd, opcional): ");
        String isbn = readOptionalString("ISBN (opcional): ");
        Double preco = readOptionalDouble("Preço (opcional): ");

        // Evita NullPointer no toString() atual do Livro caso preço fique nulo.
        if (preco == null) {
            preco = 0.0;
        }

        return new Livro(idFixo, titulo, autor, data, isbn, preco);
    }

    private static Double readOptionalDouble(String prompt) {
        while (true) {
            String s = IO.readln(prompt).trim();
            if (s.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(s.replace(",", "."));
            } catch (NumberFormatException e) {
                IO.println("Informe um número válido ou deixe em branco.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            String s = IO.readln(prompt).trim();
            if (!s.isEmpty()) {
                return s;
            }
            IO.println("Campo obrigatório.");
        }
    }

    private static String readOptionalString(String prompt) {
        String s = IO.readln(prompt).trim();
        return s.isEmpty() ? null : s;
    }

    private static LocalDate readLocalDate(String prompt) {
        while (true) {
            String s = IO.readln(prompt).trim();
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                IO.println("Data inválida. Use o formato yyyy-MM-dd.");
            }
        }
    }

    private static LocalDate readOptionalLocalDate(String prompt) {
        while (true) {
            String s = IO.readln(prompt).trim();
            if (s.isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                IO.println("Data inválida. Use yyyy-MM-dd ou deixe em branco.");
            }
        }
    }
}
