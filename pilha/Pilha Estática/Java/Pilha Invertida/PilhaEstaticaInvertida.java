public class PilhaEstaticaInvertida implements Empilhavel {
	//variáveis de instância
	private int ponteiroTopo;
	private Object[] dados;
	
	//construtores
	public PilhaEstaticaInvertida() {
		this(10);
	}

	public PilhaEstaticaInvertida(int tamanho) {
		ponteiroTopo = tamanho;
		dados = new Object[tamanho];
	}
	
	@Override
	public void empilhar(Object dado) {
		if(!estaCheia()) {
			ponteiroTopo--;
			dados[ponteiroTopo] = dado;
		} else {
			System.err.println("Pilha Cheia!");
		}
	}

	@Override
	public Object desempilhar() {
		Object dadoTopo = null;
		if(!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]; 
			ponteiroTopo++;
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;
	}
	
	@Override
	public Object topo() {
		Object dadoTopo = null;
		if(!estaVazia()) {
			dadoTopo = dados[ponteiroTopo]; 
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoTopo;	
	}
	
	@Override
	public boolean estaCheia() {
		return (ponteiroTopo == 0);
	}
	
	@Override
	public boolean estaVazia() {
		return (ponteiroTopo == dados.length);
	}
	
	@Override
	public String imprimir() {
		String resultado = "[";
		for(int i = ponteiroTopo; i <= dados.length-1; i++) {
			if (i == dados.length-1) {
				resultado += dados[i];
			}
			else {
				resultado += dados[i] +  ",";
			}
		}
		return resultado + "]";
	}
}