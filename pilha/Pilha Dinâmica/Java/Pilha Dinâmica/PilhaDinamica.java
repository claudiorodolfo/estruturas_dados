public class PilhaDinamica implements Empilhavel {
	private int tamanho;
	private int quantidade;
	private NoDuplo ponteiroTopo;
	
	public PilhaDinamica(int tamanho) {
		this.tamanho = tamanho;
		quantidade = 0;
		ponteiroTopo = null;
	}

	public PilhaDinamica() {
		this(10);
	}

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
			System.err.println("Pilha Cheia!");
		}
	}
	
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
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;
	}
	
	@Override
	public Object topo() {
		Object dadoTopo = null;
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
		NoDuplo ponteiroAuxiliar = ponteiroTopo;
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
