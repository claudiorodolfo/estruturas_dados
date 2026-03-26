package dao;

import model.Livro;
import java.time.LocalDate;

public interface LivroDAO {
    // Operações básicas CRUD
    void addLivro(Livro livro);
    Livro getLivroPorId(long id);
    Livro[] getAllLivros();
    void updateLivro(Livro newLivro);
    Livro deleteLivro(long id);
    
    // Operações de consulta específicas para livros
    Livro[] getLivrosPorAutor(String autor);
    Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao);
    Livro[] getLivrosPorTitulo(String titulo);
    Livro[] deleteLivrosPorTitulo(String titulo);
    Livro getLivroPorIsbn(String isbn);
    Livro[] getLivrosPorFaixaPreco(double minPreco, double maxPreco);
    Livro[] getLivrosPorFaixaData(LocalDate minDate, LocalDate maxDate);

    // Operações de análise e estatísticas
    Livro getLivroMaisCaro();
    Livro getLivroMaisBarato();
    Livro getLivroMaisNovo(); 
    Livro getLivroMaisAntigo();

    // Operações de relatório e estatísticas
    String printLivros();
    int getTotalLivros();
    double getPrecoMedio();
    
    // Operações de gerenciamento
    boolean isLivroDisponivel(long id);
    
    // Operações de backup e restore
    void clearAllLivros();
    String toStringArray(Livro[] livros);
}