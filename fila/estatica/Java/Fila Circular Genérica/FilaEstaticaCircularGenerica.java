public class FilaEstaticaCircularGenerica<T> implements EnfileiravelGenerica<T> {

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
		if (!estaCheia()){
			ponteiroFim = avancar(ponteiroFim);
			dados[ponteiroFim] = dado;
			//não deixar ponteiroInicio esquecido, caso a estrutura esteja na 1ª inserção
			if (estaVazia())
				ponteiroInicio = ponteiroFim;
			
			quantidade++;
		} else {
			System.err.println("Queue is full!");
		}
	}
	
	@Override	
	public T desenfileirar(){
		T dadoInicio = null;
		if (!estaVazia()){
			dadoInicio = dados[ponteiroInicio];
			ponteiroInicio = avancar(ponteiroInicio);
			quantidade--;
		} else {
			System.err.println("Queue is empty!");
		}
		return dadoInicio;	
	}
	
	@Override	
	public T frente() {
		T dadoInicio = null;
		if (!estaVazia())
			dadoInicio = dados[ponteiroInicio];
		else
			System.err.println("Fila Vazia!");		

		return dadoInicio;
	}

	@Override
	public void atualizarInicio(T dado) {
		if (!estaVazia()){
			dados[ponteiroInicio] = dado;
		} else {
			System.err.println("Queue is empty!");
		}
	}	
	
	@Override
	public void atualizarFim(T dado) {
  		if (!estaVazia()){
			dados[ponteiroFim] = dado;
		} else {
			System.err.println("Queue is empty!");
		}
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
		String retorno = "[";
		int ponteiroAux = ponteiroInicio;
		for (int i = 0; i < quantidade; i++) {			
			if (i == quantidade - 1) 
				retorno += dados[ponteiroAux];
			else
				retorno += dados[ponteiroAux] + ",";
			
			ponteiroAux = avancar(ponteiroAux); 
		}
		return retorno + "]";		
	}
	
	private int avancar(int ponteiro) {
		return (ponteiro+1)%dados.length;
	}	
}