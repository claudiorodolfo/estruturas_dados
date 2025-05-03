import java.util.NoSuchElementException;

/**
 * Esta classe implementa uma pilha dinâmica, 
 * que segue o princípio LIFO (Last In, First Out).
 * 
 * @author Oliveira, C. R. S.
 * @version 1.1
 * @since 2025-05-01
 */
public class PilhaDinamica implements Empilhavel {
	private int tamanho;
	private int quantidade;
	private NoDuplo ponteiroTopo;

	/**
 	* Construtor que recebe um tamanho máximo.
	*
 	* @param tamanho, indica o tamanho máximo que a pilha pode ter
 	*/	
	public PilhaDinamica(int tamanho) {
		this.tamanho = tamanho;
		quantidade = 0;
		ponteiroTopo = null;
	}

	/**
 	* Construtor vazio.
 	* 
 	*/	
	public PilhaDinamica() {
		this(10);
	}

	/**
 	* Empilha um elemento na pilha.
 	* 
 	* @param dado, o dado a ser empilhado
 	* @throws NoSuchElementException se a pilha estiver cheia
 	*/
	@Override
	public void empilhar(Object dado) {
		if (!estaCheia()) {
			NoDuplo noTemporario = new NoDuplo();
			noTemporario.setDado(dado);
			noTemporario.setAnterior(ponteiroTopo);
			if (!estaVazia()) {
				ponteiroTopo.setProximo(noTemporario);
			}
			ponteiroTopo = noTemporario;
			quantidade++;
		} else {
			throw new NoSuchElementException("Pilha Cheia!");
		}
	}

	/**
 	* Desempilha o topo da pilha.
 	* 
	* @return retorna o elemento desempilhado
 	* @throws NoSuchElementException se a pilha estiver vazia
 	*/	
	@Override
	public Object desempilhar() {
		Object dadoTopo = null;
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo.getDado();
			ponteiroTopo = ponteiroTopo.getAnterior();
			quantidade--;
			if (!estaVazia()) {
				ponteiroTopo.setProximo(null);
			}
		} else {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		return dadoTopo;
	}
	
	/**
 	* Espia o topo da pilha.
 	* 
	* @return retorna o elemento do topo
 	* @throws NoSuchElementException se a pilha estiver vazia
 	*/
	@Override
	public Object espiar() {
		Object dadoTopo = null;
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo.getDado();
		} else {
			throw new NoSuchElementException("Pilha Vazia!");
		}
		return dadoTopo;
	}

	/**
 	* Atualiza o topo da pilha.
 	* 
	* @param novoDado, elemento a substituir o elemento do topo
 	* @throws NoSuchElementException se a pilha estiver vazia
 	*/	
	@Override
	public void atualizar(Object novoDado) {
		if (!estaVazia()) {
			ponteiroTopo.setDado(novoDado);
		} else {
			throw new NoSuchElementException("Pilha Vazia!");
		}
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
 	* Verifica se a pilha está cheia.
 	* 
	* @return true se a pilha estiver cheia, false caso contrário
 	*/		
	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	/**
 	* Texto referente aos elementos da pilha para serem impressos.
 	* 
	* @return um texto com os elementos separados por ",", delimitados por colchetes
 	*/		
	@Override
	public String imprimir() {
		NoDuplo ponteiroAuxiliar = ponteiroTopo;
		String resultado = "[";
		for (int i = quantidade - 1; i >= 0; i--) {
			resultado += ponteiroAuxiliar.getDado();
			if (i != 0) {
				resultado += ",";
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
		}
		return resultado + "]";
	}
}
