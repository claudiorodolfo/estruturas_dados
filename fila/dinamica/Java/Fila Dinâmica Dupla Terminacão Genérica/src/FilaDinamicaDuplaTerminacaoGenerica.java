import java.util.NoSuchElementException;

/**
 * Implementação de uma fila dinâmica dupla terminacão genérica.
 * Esta classe implementa uma fila que pode armazenar elementos de qualquer tipo,
 * permitindo operações tanto no início quanto no final da fila.
 *
 * @param <T> o tipo dos elementos armazenados na fila
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 */
public class FilaDinamicaDuplaTerminacaoGenerica<T> implements Enfileiravel<T> {

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
	public FilaDinamicaDuplaTerminacaoGenerica() {
		this(10);
	}

	/**
	 * Construtor que cria uma fila com capacidade personalizada.
	 *
	 * @param tamanho a capacidade máxima da fila
	 */
	public FilaDinamicaDuplaTerminacaoGenerica(int tamanho) {
		ponteiroInicio = null;
		ponteiroFim = null;
		quantidade = 0;
		this.tamanho = tamanho;
	}

	/**
	 * Adiciona um elemento ao início da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	@Override
	public void enfileirarInicio(T dado) {
		if(estaCheia()) {
			throw new NoSuchElementException("Fila Cheia!");
		}
		NoDuplo<T> noTemporario = new NoDuplo<>();
		noTemporario.setDado(dado);
		if (!estaVazia()) {
			ponteiroInicio.setAnterior(noTemporario);
		} else {
			ponteiroFim = noTemporario;
		}
		noTemporario.setProximo(ponteiroInicio);
		ponteiroInicio = noTemporario;
		quantidade++;
	}

	/**
	 * Adiciona um elemento ao final da fila.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws java.util.NoSuchElementException se a fila estiver cheia
	 */
	@Override
	public T desenfileirarFim() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		T dadoFim = ponteiroFim.getDado();
		ponteiroFim = ponteiroFim.getAnterior();
		quantidade--;
		if (!estaVazia()) {
			ponteiroFim.setProximo(null);
		} else {
			ponteiroInicio = null;
		}
		return dadoFim;	
	}

	/**
	 * Retorna o elemento do final da fila sem removê-lo.
	 *
	 * @return o elemento do final
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */
	@Override
	public T tras() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		return ponteiroFim.getDado();
	}


	/**
	 * Retorna uma representação em string da fila do final para o início.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a fila do final para o início
	 */
	@Override
	public String imprimirDeTrasPraFrente() {
		String resultado = "[";
		NoDuplo<T> noAuxiliar = ponteiroFim;
		for (int i = 0; i < quantidade; i++) {
			resultado += noAuxiliar.getDado();
			if (i != quantidade-1) {
				resultado += ",";
			}
			noAuxiliar = noAuxiliar.getAnterior();
		}
		return resultado + "]";
	}

	/**
	 * Atualiza o elemento do final da fila.
	 *
	 * @param dado o novo elemento
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
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
	 * Retorna o elemento do inicio da fila sem removê-lo.
	 *
	 * @return o elemento do inicio
	 * @throws java.util.NoSuchElementException se a fila estiver vazia
	 */	
	@Override
	public T frente() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		return ponteiroInicio.getDado();
	}

	/**
	 * Atualiza o elemento do inicio da fila.
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

	/**
	 * Verifica se a fila está cheia.
	 *
	 * @return true se a fila estiver cheia, false caso contrário
	 */
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
	 * Retorna uma representação em string da fila do início para o final.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a fila do início para o final
	 */
	@Override
	public String imprimirDeFrentePraTras() {
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