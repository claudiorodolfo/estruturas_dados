public class Produto implements Comparable<Produto> {
    private String nome;
    private Long codigoBarras; // Chave

    public Produto(String nome, long codigoBarras) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
    }

    public String getNome() { return nome; }
    public long getCodigoBarras() { return codigoBarras; }

    @Override
    public int compareTo(Produto outro) {
        return this.codigoBarras.compareTo(outro.codigoBarras);
    }

    @Override
    public String toString() {
        return nome + " (" + codigoBarras + ")";
    }
}