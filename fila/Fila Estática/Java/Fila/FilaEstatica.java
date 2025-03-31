public class FilaEstatica implements Enfileiravel {
	//variaveis de instância/globais
	private int ponteiroInicio;	//cabeca = head
	private int ponteiroFim;	//cauda  = tail
	private Object[] dados;

	//construtores
	public FilaEstatica(int tamanho) {
		ponteiroInicio = 0;
		ponteiroFim = -1;
		dados = new Object[tamanho];
	}

	public FilaEstatica() {
		this(10);
	}

	//métodos principais
	@Override
	public void enfileirar(Object dado){
		if (!estaCheia()) {
			ponteiroFim++;
			dados[ponteiroFim] = dado;
		} else {
			System.err.println("Fila Cheia!");
		}
	}

	@Override	
	public Object desenfileirar(){
		Object dadoInicio = null;
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio];
			ponteiroInicio++;
		} else {
			System.err.println("Fila Vazia!");
		}
		return dadoInicio;		
	}
	
	@Override	
	public Object frente(){
		Object dadoInicio = null;
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio];
		} else {
			System.err.println("Fila Vazia!");		
		}
		return dadoInicio;
	}

	@Override
	public void atualizarInicio(Object dado) {
		if (!estaVazia()){
			dados[ponteiroInicio] = dado;
		} else {
			System.err.println("Queue is empty!");
		}
	}	
	
	@Override
	public void atualizarFim(Object dado) {
  		if (!estaVazia()){
			dados[ponteiroFim] = dado;
		} else {
			System.err.println("Queue is empty!");
		}
	}
	
	//métodos auxiliares	
	@Override	
	public boolean estaCheia(){
		return (ponteiroFim == dados.length - 1);
	}

	@Override	
	public boolean estaVazia(){
		return (ponteiroInicio == ponteiroFim + 1);
	}

	@Override	
	public String imprimir(){
		String resultado = "[";
		for (int i = ponteiroInicio; i <= ponteiroFim; i++) {
			if (i == ponteiroFim)
				resultado += dados[i];
			else
				resultado += dados[i] + ",";
		}
		return resultado + "]";		
	}
}