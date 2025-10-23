package br.edu.ifba.vdc.bsi.linkedlistdao.dao.repository.list;
/**
 * Exceção lançada quando uma operação tenta acessar ou remover elementos
 * de uma estrutura de dados que está vazia.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-05-01
 */
public class UnderflowException extends RuntimeException {

	/**
	 * Construtor que cria uma exceção com a mensagem especificada.
	 *
	 * @param mensagem a mensagem de erro
	 */
	public UnderflowException(String mensagem) {
        super(mensagem);
    }
}