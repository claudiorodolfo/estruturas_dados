public class PilhaEstatica implements Empilhavel {
	
	private int ponteiroTopo;
	private Object[] dados;

	public PilhaEstatica(int tamanho) {
		ponteiroTopo = -1;
		dados = new Object[tamanho];
	}
	
	public PilhaEstatica() {
		this(10);
	}

	@Override
	public void empilhar(Object elemento) {
		if(!estaCheia()) {
			ponteiroTopo++;
			dados[ponteiroTopo] = elemento;
		} else {
			System.err.println("Pilha Cheia!");
		}
	}
	
	@Override	
	public Object desempilhar() {
		Object elementoTopo = null;
		if(!estaVazia()) {
			elementoTopo = dados[ponteiroTopo]; 
			ponteiroTopo--;
		} else {
			System.err.println("Pilha Vazia!");
		}
		return elementoTopo;
	}
	
	@Override	
	public Object topo() {
		Object elementoTopo = null;
		if(!estaVazia()) {
			elementoTopo = dados[ponteiroTopo]; 
		} else {
			System.err.println("Pilha Vazia!");
		}
		return elementoTopo;	
	}
	
	@Override	
	public boolean estaCheia() {
		return (ponteiroTopo == dados.length-1);
	}
	
	@Override	
	public boolean estaVazia(){
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