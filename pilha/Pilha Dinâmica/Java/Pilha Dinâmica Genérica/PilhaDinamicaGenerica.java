public class PilhaDinamicaGenerica<T> implements Empilhavel<T> {
	private int tamanho;
	private int quantidade;
	private NoDuplo<T> ponteiroTopo;
	
	public PilhaDinamicaGenerica(int tamanho) {
		this.tamanho = tamanho;
		quantidade = 0;
		ponteiroTopo = null;
	}

	public PilhaDinamicaGenerica() {
		this(10);
	}

	@Override
	public void empilhar(T dado) {
		if (!estaCheia()) {
			NoDuplo<T> noTemporario = new NoDuplo<>();
			noTemporario.setDado(dado);
			noTemporario.setAnterior(ponteiroTopo);
			if (!estaVazia()) {
				ponteiroTopo.setProximo(noTemporario);
			}
			ponteiroTopo = noTemporario;
			quantidade++;
		} else {
			System.err.println("Pilha Cheia!");
		}
	}
	
	@Override
	public T desempilhar() {
		T dadoTopo = null;
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo.getDado();
			ponteiroTopo = ponteiroTopo.getAnterior();
			quantidade--;
			if (!estaVazia()) {
				ponteiroTopo.setProximo(null);
			}
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;
	}
	
	@Override
	public T topo() {
		T dadoTopo = null;
		if (!estaVazia()) {
			dadoTopo = ponteiroTopo.getDado();
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;
	}
	
	@Override
	public boolean estaVazia() {
		return (quantidade == 0);
	}
	
	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}
	
	@Override
	public String imprimir() {
		NoDuplo<T> ponteiroAuxiliar = ponteiroTopo;
		String resultado = "[";
		for (int i = quantidade - 1; i >= 0; i--) {
			if (i == 0) {
				resultado += ponteiroAuxiliar.getDado();
			} else {
				resultado += ponteiroAuxiliar.getDado() + ",";
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
		}
		return resultado + "]";
	}
}
