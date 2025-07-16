/**
 * Enumeração para as cores dos nós da árvore vermelho e preto.
 * Utilizada para distinguir propriedades e regras de balanceamento.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public enum Cor {
    /**
     * Indica que o nó é vermelho (pode causar violações de propriedades se pai também for vermelho).
     */
    VERMELHO,
    /**
     * Indica que o nó é preto (garante propriedades de balanceamento da árvore).
     */
    PRETO
} 