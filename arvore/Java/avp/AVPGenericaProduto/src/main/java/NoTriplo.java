/**
 * Representa um nó de árvore vermelho e preto com ponteiros para o genitor, esquerda e direita.
 * Inclui campo de cor para manter as propriedades da árvore vermelho e preto.
 * 
 * @param <T> Tipo do dado armazenado no nó.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class NoTriplo<T> {

    /**
     * Dado armazenado no nó.
     */
    private T dado;

    /**
     * Referência ao nó genitor (pai).
     */
    private NoTriplo<T> genitor;

    /**
     * Referência ao nó filho à esquerda.
     */
    private NoTriplo<T> esquerda;

    /**
     * Referência ao nó filho à direita.
     */
    private NoTriplo<T> direita;

    /**
     * Cor do nó (VERMELHO ou PRETO).
     */
    private Cor cor;

    /**
     * Construtor padrão. Cria um nó vermelho.
     */
    public NoTriplo() {
        this.cor = Cor.VERMELHO; // Novos nós são sempre vermelhos
    }

    /**
     * Retorna o dado armazenado.
     * @return Dado do nó.
     */
    public T getDado() {
        return dado;
    }

    /**
     * Define o dado do nó.
     * @param dado Novo valor.
     */
    public void setDado(T dado) {
        this.dado = dado;
    }

    /**
     * Retorna o genitor (pai) do nó.
     * @return Nó genitor.
     */
    public NoTriplo<T> getGenitor() {
        return genitor;
    }

    /**
     * Define o genitor (pai) do nó.
     * @param genitor Novo nó genitor.
     */
    public void setGenitor(NoTriplo<T> genitor) {
        this.genitor = genitor;
    }

    /**
     * Retorna o filho à esquerda.
     * @return Nó à esquerda.
     */
    public NoTriplo<T> getEsquerda() {
        return esquerda;
    }

    /**
     * Define o filho à esquerda.
     * @param esquerda Novo nó à esquerda.
     */
    public void setEsquerda(NoTriplo<T> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Retorna o filho à direita.
     * @return Nó à direita.
     */
    public NoTriplo<T> getDireita() {
        return direita;
    }

    /**
     * Define o filho à direita.
     * @param direita Novo nó à direita.
     */
    public void setDireita(NoTriplo<T> direita) {
        this.direita = direita;
    }

    /**
     * Retorna a cor do nó.
     * @return Cor do nó (VERMELHO ou PRETO).
     */
    public Cor getCor() {
        return cor;
    }

    /**
     * Define a cor do nó.
     * @param cor Nova cor do nó.
     */
    public void setCor(Cor cor) {
        this.cor = cor;
    }

    /**
     * Verifica se o nó é vermelho.
     * @return true se o nó é vermelho, false se é preto.
     */
    public boolean isVermelho() {
        return cor == Cor.VERMELHO;
    }

    /**
     * Verifica se o nó é preto.
     * @return true se o nó é preto, false se é vermelho.
     */
    public boolean isPreto() {
        return cor == Cor.PRETO;
    }

    /**
     * Retorna uma representação em string do nó.
     * @return String com o dado e a cor do nó.
     */
    @Override
    public String toString() {
        return dado + "(" + (cor == Cor.VERMELHO ? "V" : "P") + ")";
    }
} 