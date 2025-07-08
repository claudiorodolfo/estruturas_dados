/**
 * Representa um produto com nome e código de barras.
 * Esta classe implementa Comparable para permitir ordenação por código de barras.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class Produto implements Comparable<Produto> {
    /**
     * Nome do produto.
     */
    private String nome;
    
    /**
     * Código de barras do produto (chave única).
     */
    private Long codigoBarras;

    /**
     * Construtor que cria um produto apenas com código de barras.
     * O nome será null.
     * 
     * @param codigoBarras Código de barras do produto
     * @throws IllegalArgumentException se codigoBarras for negativo ou zero
     */
    public Produto(long codigoBarras) {
        if (codigoBarras <= 0) {
            throw new IllegalArgumentException("Código de barras deve ser positivo.");
        }
        this.codigoBarras = codigoBarras;
    }

    /**
     * Construtor que cria um produto com nome e código de barras.
     * 
     * @param nome Nome do produto
     * @param codigoBarras Código de barras do produto
     * @throws IllegalArgumentException se nome for nulo/vazio ou codigoBarras for negativo ou zero
     */
    public Produto(String nome, long codigoBarras) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio.");
        }
        if (codigoBarras <= 0) {
            throw new IllegalArgumentException("Código de barras deve ser positivo.");
        }
        this.nome = nome;
        this.codigoBarras = codigoBarras;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return Nome do produto
     */
    public String getNome() { 
        return nome; 
    }
    
    /**
     * Retorna o código de barras do produto.
     * 
     * @return Código de barras do produto
     */
    public long getCodigoBarras() { 
        return codigoBarras; 
    }

    /**
     * Compara este produto com outro baseado no código de barras.
     * 
     * @param outro Produto a ser comparado
     * @return Valor negativo se este produto tem código menor,
     *         zero se os códigos são iguais,
     *         valor positivo se este produto tem código maior
     */
    @Override
    public int compareTo(Produto outro) {
        return this.codigoBarras.compareTo(outro.codigoBarras);
    }

    /**
     * Retorna uma representação em string do produto.
     * 
     * @return String no formato "Produto(nome,codigoBarras)"
     */
    @Override
    public String toString() {
        return "Produto(" + nome + "," + codigoBarras + ")";
    }
} 