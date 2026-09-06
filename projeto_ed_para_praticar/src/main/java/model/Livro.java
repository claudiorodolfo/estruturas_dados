package model;

import java.time.LocalDate;

public class Livro {
    private final Long id;            // obrigatório, imutável
    private String titulo;             // obrigatório
    private String autor;
    private LocalDate dataPublicacao;
    private String isbn;
    private Double preco;

    // Construtor mínimo – apenas campos obrigatórios.
    public Livro(Long id, String titulo) {
        obrigatorio(id, "id");
        obrigatorio(titulo, "titulo");
        this.id = id;   
        this.titulo = titulo;
    }

    // Construtor completo – todos os campos.
    public Livro(Long id,
                String titulo,
                String autor,
                LocalDate dataPublicacao,
                String isbn,
                Double preco) {
        this(id, titulo);
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.preco = preco;
    }

    private void obrigatorio(Object obj, String campo) {
        if (obj == null)
            throw new IllegalArgumentException(campo + " é obrigatório!");
    }

    private void obrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                campo + " é obrigatório."
            );
        }
    }

    // Getter para id (imutável)
    //Não tem setId porque é imutável
    public Long getId() {
        return id;
    }

    // Getter e setter para title (obrigatório)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        obrigatorio(titulo, "titulo");
        this.titulo = titulo;
    }

    // Getter e setter para author
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Getter e setter para publicationDate
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    // Getter e setter para isbn
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter e setter para price
    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        if (preco != null && preco < 0) {
            throw new IllegalArgumentException(
                "Preço não pode ser negativo."
            );
        }  
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", isbn='" + isbn + '\'' +
                ", preco=" + preco +
                '}';
    }
}