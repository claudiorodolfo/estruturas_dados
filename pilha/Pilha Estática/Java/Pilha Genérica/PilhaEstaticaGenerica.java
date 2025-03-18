public class PilhaEstaticaGenerica<T> implements Empilhavel<T> {
	
	private int ponteiroTopo;
	private T[] dados;

	@SuppressWarnings("unchecked")
	public PilhaEstaticaGenerica(int tamanho) {
		ponteiroTopo = -1;
		dados = (T[]) new Object[tamanho];
	}
	
	public PilhaEstaticaGenerica() {
		this(10);
	}

	@Override	
	public void empilhar(T dado) {
		if(!estaCheia()) {
			ponteiroTopo++;
			dados[ponteiroTopo] = dado;
		} else {
			System.err.println("Pilha Cheia!");
		}
	}

	@Override	
	public void atualizar(T dado) {
		if(!estaVazia()) {
			dados[ponteiroTopo] = dado;
		} else {
			System.err.println("Pilha Vazia!");
		}
	}
	
	@Override
	public T desempilhar() {
		T dadoTopo = null;
		if(!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]; 
			ponteiroTopo--;
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;
	}

	@Override	
	public T espiar() {
		T dadoTopo = null;
		if(!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]; 
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;	
	}

	@Override	
	public boolean estaCheia() {
		return (ponteiroTopo == dados.length - 1);
	}

	@Override	
	public boolean estaVazia() {
		return (ponteiroTopo == -1);
	}

	@Override	
	public String imprimir() {
		String resultado = "[";
		for(int i = ponteiroTopo; i >= 0; i--) {
			if (i == 0) {
				resultado += dados[i];
			}
			else {
				resultado += dados[i] + ",";
			}
		}
		return resultado + "]";
	}
}