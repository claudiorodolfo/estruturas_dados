import java.util.NoSuchElementException;

/**
 * Implementação de uma pilha dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma pilha que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na pilha
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 */
public class PilhaDinamicaGenerica<T> implements Empilhavel<T> {

	/** Quantidade atual de elementos na pilha */
	private int quantidade;
	
	/** Tamanho máximo da pilha */
	private int tamanho;
	
	/** Ponteiro para o topo da pilha */
	private NoDuplo<T> ponteiroTopo;

	/**
	 * Construtor padrão que cria uma pilha com capacidade para 10 elementos.
	 */
	public PilhaDinamicaGenerica() {
		this(10);
	}

	/**
	 * Construtor que cria uma pilha com capacidade personalizada.
	 *
	 * @param tamanho a capacidade máxima da pilha
	 */
	public PilhaDinamicaGenerica(int tamanho) {
		ponteiroTopo = null;
		quantidade = 0;
		this.tamanho = tamanho;
	}

	/**
	 * Adiciona um elemento ao topo da pilha.
	 *
	 * @param dado o elemento a ser adicionado
	 * @throws NoSuchElementException se a pilha estiver cheia
	 */
	@Override
	public void empilhar(T dado) {
		if (estaCheia()) {
			throw new NoSuchElementException("Pilha Cheia!");
		}
		NoDuplo<T> noTemporario = new NoDuplo<T>();
		noTemporario.setDado(dado);
		noTemporario.setAnterior(ponteiroTopo);
		if (!estaVazia()) {
			ponteiroTopo.setProximo(noTemporario);
		}
		ponteiroTopo = noTemporario;
		quantidade++;		
	}
	
	/**
	 * Remove e retorna o elemento do topo da pilha.
	 *
	 * @return o elemento removido do topo
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
	@Override
	public T desempilhar() {
		if (estaVazia()) {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		T dadoTopo = ponteiroTopo.getDado();
		ponteiroTopo = ponteiroTopo.getAnterior();
		quantidade--;
		if (!estaVazia()) {
			ponteiroTopo.setProximo(null);
		}
		return dadoTopo;
	}

	/**
	 * Retorna o elemento do topo da pilha sem removê-lo.
	 *
	 * @return o elemento do topo
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
	@Override
	public T espiar() {
		if (estaVazia()) {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		return ponteiroTopo.getDado();
	}

	/**
	 * Retorna um array contendo todos os elementos da pilha.
	 * O elemento do topo será o último elemento do array.
	 *
	 * @return array com todos os elementos da pilha
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
	@Override
	public void atualizar(T novoDado) {
		if (estaVazia()) {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		ponteiroTopo.setDado(novoDado);
	}

	/**
	 * Verifica se a pilha está cheia.
	 *
	 * @return true se a pilha estiver cheia, false caso contrário
	 */
	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	/**
	 * Verifica se a pilha está vazia.
	 *
	 * @return true se a pilha estiver vazia, false caso contrário
	 */
	@Override
	public boolean estaVazia() {
		return (quantidade == 0);
	}

	/**
	 * Retorna uma representação em string da pilha.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 * O elemento do topo será o último elemento na string.
	 *
	 * @return string representando a pilha
	 */
	@Override
	public String imprimir() {
		String resultado = "[";
		NoDuplo<T> ponteiroAuxiliar = ponteiroTopo;
		for (int i = 0; i < quantidade; i++) {
			resultado += ponteiroAuxiliar.getDado();
			if (i != quantidade - 1) {
				resultado += ",";
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
		}
		return resultado + "]";
	}
}
