/**
 * Exceção lançada quando uma operação tenta inserir elementos
 * em uma estrutura de dados que já está cheia.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-05-01
 */
public class OverflowException extends RuntimeException {

	/**
	 * Construtor que cria uma exceção com a mensagem especificada.
	 *
	 * @param mensagem a mensagem de erro
	 */
	public OverflowException(String mensagem) {
        super(mensagem);
    }
}