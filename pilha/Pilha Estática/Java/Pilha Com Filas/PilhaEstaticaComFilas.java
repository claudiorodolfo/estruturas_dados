public class PilhaEstaticaComFilas implements Empilhavel {
	
	private Enfileiravel f1;
	private Enfileiravel f2;

	public PilhaEstaticaComFilas(int tamanho) {
		f1 = new FilaEstaticaCircular(tamanho);
		f2 = new FilaEstaticaCircular(tamanho);
	}
	
	public PilhaEstaticaComFilas() {
		this(10);
	}
	
	@Override
	public void empilhar(Object dado) {
		if (!estaCheia()) {	
			//joga todo mundo pra fila 2
			while(!f1.estaVazia()) {
				f2.enfileirar(f1.desenfileirar());
			}
			//enfileira o dado
			f1.enfileirar(dado);
			//enfileira os dados pre-existentes
			while(!f2.estaVazia()) {
				f1.enfileirar(f2.desenfileirar());
			}
		} else {
			System.err.println("Pilha Cheia!");
		}			
	}

	@Override
	public void atualizar(Object dado) {
		if (!estaVazia()) {	
			f1.atualizarInicio(dado);
		} else {
			System.err.println("Pilha Vazia!");
		}			
	}
	
	@Override	
	public Object desempilhar() {
		Object dadoAux = null;
		if (!estaVazia()) {
			dadoAux = f1.desenfileirar();	
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoAux;
	}
	
	@Override	
	public Object espiar() {
		Object dadoAux = null;
		if (!estaVazia()) {		
			return f1.frente();	
		} else {
			System.err.println("Pilha Vazia!");
		}
		return dadoAux;			
	}
	
	@Override
	public boolean estaCheia() {
		return f1.estaCheia();
	}
		
	@Override
	public boolean estaVazia() {
		return f1.estaVazia();
	}
		
	@Override
	public String imprimir() {
		return f1.imprimir();
	}
}