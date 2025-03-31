public class FilaEstaticaCircular implements Enfileiravel {

	private int ponteiroInicio;	//cabeca = head
	private int ponteiroFim;	//cauda  = tail
	private int quantidade;
	private Object[] dados;
	
	public FilaEstaticaCircular(int tamanho) {
		ponteiroInicio = 0;
		ponteiroFim = -1;
		quantidade = 0;		
		dados = new Object[tamanho];
	}

	public FilaEstaticaCircular() {
		this(10);
	}	

	@Override	
	public void enfileirar(Object dado){
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
	public Object desenfileirar(){
		Object dadoInicio = null;
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
	public Object frente() {
		Object dadoInicio = null;
		if (!estaVazia())
			dadoInicio = dados[ponteiroInicio];
		else
			System.err.println("Fila Vazia!");		

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