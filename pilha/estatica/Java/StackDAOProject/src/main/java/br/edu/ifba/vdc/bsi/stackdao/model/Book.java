package br.edu.ifba.vdc.bsi.stackdao.model;

import java.time.LocalDate;

public class Book {
    private final Long id;            // obrigatório, imutável
    private String title;             // obrigatório
    private String author;
    private LocalDate publicationDate;
    private String isbn;
    private Double price;

    // Construtor mínimo – apenas campos obrigatórios.
    public Book(Long id, String title) {
        required(id);
        required(title);
        this.id = id;   
        this.title = title;
    }

    // Construtor completo – todos os campos.
    public Book(Long id,
                String title,
                String author,
                LocalDate publicationDate,
                String isbn,
                Double price) {
        this(id, title);
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.price = price;
    }

    private void required(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("campo obrigatório não pode ser nulo!");
    }

    // Getter para id (imutável)
    public Long getId() {
        return id;
    }

    // Getter e setter para title (obrigatório)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        required(title);
        this.title = title;
    }

    // Getter e setter para author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter e setter para publicationDate
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    // Getter e setter para isbn
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter e setter para price
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book {");
        sb.append("\n\tid=").append(id);
        sb.append(", \n\ttitle='").append(title).append('\'');
        sb.append(", \n\tauthor=").append(author == null ? "null" : "'" + author + "'");
        sb.append(", \n\tpublicationDate=").append(publicationDate == null
                ? "null"
                : publicationDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        sb.append(", \n\tisbn=").append(isbn == null ? "null" : "'" + isbn + "'");
        String priceStr = price == null
                ? "null"
                : java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("pt", "BR")).format(price);
        sb.append(", \n\tprice=").append(priceStr);
        sb.append("}");
        return sb.toString();
    }
}