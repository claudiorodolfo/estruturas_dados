public class FilaEstaticaCircularGenerica<T> implements Enfileiravel<T> {

	private int ponteiroInicio;
	private int ponteiroFim;
	private int quantidade;
	private T[] dados;
	
	@SuppressWarnings("unchecked")
	public FilaEstaticaCircularGenerica(int tamanho) {
		ponteiroInicio = 0;
		ponteiroFim = -1;
		quantidade = 0;		
		dados = (T[]) new Object[tamanho];
	}

	public FilaEstaticaCircularGenerica() {
		this(10);
	}	

	@Override	
	public void enfileirar(T dado){
		if (!estaCheia()) {
			ponteiroFim++;			
			//patch para que a fila funcione de forma circular
			if (ponteiroFim == dados.length) {
				ponteiroFim = 0;
			}
			quantidade++;
			//fim do patch
			dados[ponteiroFim] = dado;			
		} else {
			System.err.println("Fila Cheia!");
		}
	}
	
	@Override	
	public T desenfileirar(){
		T dadoInicio = null;	
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio];			
			ponteiroInicio++;
			//patch para que a fila funcione de forma circular
			if (ponteiroInicio == dados.length) {
				ponteiroInicio = 0;
			}
			quantidade--;
			//fim do patch			
		} else {
			System.err.println("Fila Vazia!");
		}
		return dadoInicio;
	}
	
	@Override	
	public T espiar() {
		T dadoInicio = null;
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio];
		} else {
			System.err.println("Fila Vazia!");		
		}
		return dadoInicio;
	}
	
	@Override	
	public boolean estaCheia(){
		return (quantidade == dados.length);
	}
	
	@Override	
	public boolean estaVazia(){
		return (quantidade == 0);
	}
	
	@Override
	public String imprimir(){
		String resultado = "[";
		for (int i = 0, ponteiroAux = ponteiroInicio; i < quantidade; i++, ponteiroAux++) {
			//if (ponteiroAux == dados.length) {
			//	ponteiroAux = 0;
			//}
			if (i == quantidade - 1) {
				resultado += dados[ponteiroAux % dados.length];
			} else {
				resultado += dados[ponteiroAux % dados.length] + ",";		
			}
		}
		return resultado + "]";		
	}
}