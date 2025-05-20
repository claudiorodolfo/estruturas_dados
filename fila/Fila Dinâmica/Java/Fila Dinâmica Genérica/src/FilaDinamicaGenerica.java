import java.util.NoSuchElementException;

/**
 * Implementação de uma fila dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma fila que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na fila
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 */
public class FilaDinamicaGenerica<T> implements Enfileiravel<T> {

	/** Quantidade atual de elementos na fila */
	private int quantidade;
	
	/** Tamanho máximo da fila */
	private int tamanho;
	
	/** Ponteiro para o primeiro nó da fila */
	private NoDuplo<T> ponteiroInicio;
	
	/** Ponteiro para o último nó da fila */
	private NoDuplo<T> ponteiroFim;

	/**
	 * Construtor padrão que cria uma fila com capacidade para 10 elementos.
	 */
	public FilaDinamicaGenerica() {
		this(10);
	}

	/**
	 * Construtor que cria uma fila com capacidade personalizada.
	 *
	 * @param tamanho a capacidade máxima da fila
	 */
	public FilaDinamicaGenerica(int tamanho) {
		ponteiroInicio = null;
		ponteiroFim = null;
		quantidade = 0;
		this.tamanho = tamanho;
	}

	/**
	 * Adiciona um elemento ao início da fila.
	 *
	 * @throws java.util.UnsupportedOperationException operação não suportada para a fila simples
	 */
	@Override
	public void enfileirarInicio(T dado) {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	/**
	 * Remove e retorna o elemento do final da fila.
	 *
	 * @throws java.util.UnsupportedOperationException operação não suportada para a fila simples
	 */
	@Override
	public T desenfileirarFim() {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	/**
	 * Retorna o elemento do final da fila sem removê-lo.
	 *
	 * @throws java.util.UnsupportedOperationException operação não suportada para a fila simples
	 */
	@Override
	public T tras() {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	/**
	 * Retorna uma representação em string da fila do final para o início.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @throws java.util.UnsupportedOperationException operação não suportada para a fila simples
	 */
	@Override
	public String imprimirDeTrasPraFrente() {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	/**
	 * Adiciona um elemento ao final da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	@Override
	public void enfileirarFim(T dado) {
		if(estaCheia()) {
			throw new NoSuchElementException("Fila Cheia!");
		}			
		NoDuplo<T> noTemporario = new NoDuplo<>();
		noTemporario.setDado(dado);
		if (!estaVazia()) {
			ponteiroFim.setProximo(noTemporario);
		} else  {
			ponteiroInicio = noTemporario;
		}
		noTemporario.setAnterior(ponteiroFim);
		ponteiroFim = noTemporario;
		quantidade++;
	}

	/**
	 * Remove e retorna o elemento do início da fila.
	 *
	 * @return o elemento removido do início
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public T desenfileirarInicio() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		T dadoInicio = ponteiroInicio.getDado();
		ponteiroInicio = ponteiroInicio.getProximo();
		quantidade--;
		if (!estaVazia()) {
			ponteiroInicio.setAnterior(null);
		} else {
			ponteiroFim = null;
		}
		return dadoInicio;		
	}

	/**
	 * Retorna o elemento do início da fila sem removê-lo.
	 *
	 * @return o elemento do início
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public T frente() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		T dadoInicio = ponteiroInicio.getDado();
		return dadoInicio;
	}

	/**
	 * Atualiza o elemento do início da fila.
	 *
	 * @param dado o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public void atualizarInicio(T dado) {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		ponteiroInicio.setDado(dado);
	}

	/**
	 * Atualiza o elemento do final da fila.
	 *
	 * @param dado o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public void atualizarFim(T dado) {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		ponteiroFim.setDado(dado);
	}

	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	/**
	 * Verifica se a fila está vazia.
	 *
	 * @return true se a fila estiver vazia, false caso contrário
	 */
	@Override
	public boolean estaVazia() {
		return (quantidade == 0);
	}
	
	/**
	 * Retorna uma representação em string da fila.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 * O elemento do início será o primeiro elemento na string.
	 *
	 * @return string representando a fila
	 */
	@Override
	public String imprimirDeFrentePraTras(){
		String resultado = "[";
		NoDuplo<T> noAuxiliar = ponteiroInicio;
		for (int i = 0; i < quantidade; i++) {
			resultado += noAuxiliar.getDado();
			if (i != quantidade-1) {
				resultado +=  ",";
			}
			noAuxiliar = noAuxiliar.getProximo();
		}
		return resultado + "]";
	}
}