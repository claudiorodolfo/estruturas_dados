/**
 * Classe que representa um produto com nome e código de barras.
 * Implementa Comparable para permitir ordenação por código de barras.
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
     * Código de barras do produto.
     */
    private Long codigoBarras;
    
    /**
     * Construtor padrão.
     */
    public Produto() {
        this.nome = "";
        this.codigoBarras = 0L;
    }
    
    /**
     * Construtor com parâmetros.
     * @param nome Nome do produto.
     * @param codigoBarras Código de barras do produto.
     */
    public Produto(String nome, Long codigoBarras) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (codigoBarras == null || codigoBarras <= 0) {
            throw new IllegalArgumentException("Código de barras deve ser um número positivo");
        }
        
        this.nome = nome.trim();
        this.codigoBarras = codigoBarras;
    }
    
    /**
     * Retorna o nome do produto.
     * @return Nome do produto.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Define o nome do produto.
     * @param nome Novo nome do produto.
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        this.nome = nome.trim();
    }
    
    /**
     * Retorna o código de barras do produto.
     * @return Código de barras do produto.
     */
    public Long getCodigoBarras() {
        return codigoBarras;
    }
    
    /**
     * Define o código de barras do produto.
     * @param codigoBarras Novo código de barras do produto.
     */
    public void setCodigoBarras(Long codigoBarras) {
        if (codigoBarras == null || codigoBarras <= 0) {
            throw new IllegalArgumentException("Código de barras deve ser um número positivo");
        }
        this.codigoBarras = codigoBarras;
    }
    
    /**
     * Compara este produto com outro baseado no código de barras.
     * @param outro Produto a ser comparado.
     * @return Valor negativo se este produto tem código menor,
     *         zero se são iguais, valor positivo se este produto tem código maior.
     */
    @Override
    public int compareTo(Produto outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Produto não pode ser null");
        }
        return this.codigoBarras.compareTo(outro.codigoBarras);
    }
    
    /**
     * Verifica se este produto é igual a outro objeto.
     * @param obj Objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Produto outro = (Produto) obj;
        return codigoBarras.equals(outro.codigoBarras);
    }
    
    /**
     * Retorna o hash code do produto.
     * @return Hash code baseado no código de barras.
     */
    @Override
    public int hashCode() {
        return codigoBarras.hashCode();
    }
    
    /**
     * Retorna uma representação em string do produto.
     * @return String com nome e código de barras do produto.
     */
    @Override
    public String toString() {
        return nome + " (" + codigoBarras + ")";
    }
} 