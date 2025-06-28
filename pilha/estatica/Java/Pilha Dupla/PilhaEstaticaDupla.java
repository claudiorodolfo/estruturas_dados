public class PilhaEstaticaDupla implements EmpilhavelDupla {
	//variáveis de instância
	private int ponteiroTopo1;
	private int ponteiroTopo2;
	private Object[] dados;

	public PilhaEstaticaDupla(int tamanho) {
		ponteiroTopo1 = -1;
		ponteiroTopo2 = tamanho;
		dados = new Object[tamanho];
	}
	
	public PilhaEstaticaDupla() {
		this(10);
	}

	@Override
	public void empilhar1(Object dado) {
		if(!estaCheia1()) {
			ponteiroTopo1++;
			dados[ponteiroTopo1] = dado;
		} else {
			System.err.println("Pilha1 Cheia!");
		}
	}

	@Override
	public void atualizar1(Object dado) {
		if(!estaVazia1()) {
			dados[ponteiroTopo1] = dado;
		} else {
			System.err.println("Pilha1 Vazia!");
		}
	}
	
	@Override
	public Object desempilhar1() {
		Object dadoTopo = null;
		if(!estaVazia1()) {
			dadoTopo = dados[ponteiroTopo1]; 
			ponteiroTopo1--;
		} else {
			System.err.println("Pilha1 Vazia!");
		}
		return dadoTopo;
	}
	
	@Override
	public Object espiar1() {
		Object dadoTopo = null;
		if(!estaVazia1()) {
			dadoTopo = dados[ponteiroTopo1]; 
		} else {
			System.err.println("Pilha1 Vazia!");
		}
		return dadoTopo;	
	}
	
	@Override
	public boolean estaCheia1() {
		return (ponteiroTopo1 == ponteiroTopo2-1);
	}
	
	@Override
	public boolean estaVazia1(){
		return (ponteiroTopo1 == -1);
	}
	
	@Override
	public String imprimir1(){
		String resultado = "[";
		for(int i = ponteiroTopo1; i >= 0; i--) {
			if (i == 0) {
				resultado += dados[i];
			}
			else {
				resultado += dados[i] + ",";
			}
		}
		return resultado + "]";
	}
	
	@Override
	public void empilhar2(Object dado) {
		if(!estaCheia2()) {
			ponteiroTopo2--;
			dados[ponteiroTopo2] = dado;
		} else {
			System.out.println("Pilha2 Cheia!");
		}
	}

	@Override
	public void atualizar2(Object dado) {
		if(!estaVazia2()) {
			dados[ponteiroTopo2] = dado;
		} else {
			System.out.println("Pilha2 Vazia!");
		}
	}
	
	@Override
	public Object desempilhar2() {
		Object dadoTopo = null;
		if(!estaVazia2()) {
			dadoTopo = dados[ponteiroTopo2]; 
			ponteiroTopo2++;
		} else {
			System.out.println("Pilha2 Vazia!");
		}
		return dadoTopo;
	}
	
	@Override
	public Object espiar2() {
		Object dadoTopo = null;
		if(!estaVazia2()) {
			dadoTopo = dados[ponteiroTopo2]; 
		} else {
			System.out.println("Pilha2 Vazia!");
		}
		return dadoTopo;	
	}
	
	@Override
	public boolean estaCheia2() {
		return estaCheia1();
	}
	
	@Override
	public boolean estaVazia2(){
		return (ponteiroTopo2 == dados.length);
	}
	
	@Override
	public String imprimir2(){
		String resultado = "[";
		for(int i = ponteiroTopo2; i <= dados.length-1; i++) {
			if (i == dados.length-1) {
				resultado += dados[i];
			}
			else {
				resultado += dados[i]+ ",";
			}
		}
		return resultado + "]";
	}
}