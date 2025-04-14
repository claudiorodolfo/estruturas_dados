public class ListaEstaticaCircular implements Listavel {
	
	private int ponteiroInicio; 
	private int ponteiroFim;
	private Object[] dados;
	private int quantidade;
	
	public ListaEstaticaCircular() {
		this(10);
	}
	
	public ListaEstaticaCircular(int tamanho) {
		ponteiroInicio = 0;
		ponteiroFim = -1;
		quantidade = 0;		
		dados = new Object[tamanho];
	}

	//DE endereçamento lógico (informado pelo usuário)
	//PARA endereçamento físico (onde o dado está no array)
	private int mapeamento(int logica) {
		return (logica + ponteiroInicio)%dados.length;
	}
	
	private int avancar(int ponteiro) {
		return (ponteiro+1)%dados.length;
	}
	
	private int retroceder(int ponteiro) {
		return ((ponteiro-1)+dados.length)%dados.length;
	}		

	//funciona como o estaCheia de FilaEstaticaCircular
	@Override
	public boolean estaCheia() {
		return (quantidade == dados.length);
	}
	
	//funciona como o estaVazia de FilaEstaticaCircular
	@Override
	public boolean estaVazia() {
		return (quantidade == 0);
	}
	
	//funciona como o imprimir de FilaEstaticaCircular
	@Override
	public String imprimir(){
		String retorno = "[";
		int ponteiroAux = ponteiroInicio;
		for (int i = 0; i < quantidade; i++) {			
			retorno += dados[ponteiroAux];
			if (i != quantidade - 1) 
				retorno += ",";
			
			ponteiroAux = avancar(ponteiroAux); 
		}
		return retorno + "]";
	}
	
	//funciona como o enfileirar de FilaEstaticaCircular
	@Override
	public void anexar(Object dado) {
		if (!estaCheia()) {
			ponteiroFim = avancar(ponteiroFim);
			dados[ponteiroFim] = dado;			
			quantidade++;
		} else {
			System.err.println("Lista Cheia!");
		}			
	}

	@Override
	public Object[] selecionarTodos() {
		Object[] dadosAux = null;
		if (!estaVazia()) {
			dadosAux = new Object[quantidade];
			int ponteiroAux = ponteiroInicio;
			for(int i = 0; i < quantidade; i++) {
				dadosAux[i] = dados[ponteiroAux];
				ponteiroAux = avancar(ponteiroAux);
			}
		} else {
			System.err.println("List is empty!");
		}
		return dadosAux;
	}

	@Override
	public Object selecionar (int posicao) {
		Object dadoAux = null;
		if (!estaVazia()) {
			//verificando se o índice/posição é válido 
			if ((posicao >= 0) && (posicao < quantidade)) {				
				int posicaoFisica = mapeamento(posicao);
				dadoAux = dados[posicaoFisica];
			} else {
				System.err.println("Indice Invalido!");	
			}
		} else {
			System.err.println("Lista Vazia!");		
		}
		return dadoAux;
	}

	@Override
	public void atualizar(int posicao, Object novoDado) {
		if (!estaVazia()) {
			//verificando se o índice/posição é válido
			if ((posicao >= 0) && (posicao < quantidade)) {
				int posicaoFisica = mapeamento(posicao);
				dados[posicaoFisica] = novoDado;
			} else {
				System.err.println("Indice Invalido!");	
			}
		} else {
			System.err.println("Lista Vazia!");
		}
	}

	@Override
	public void inserir(int posicao, Object dado) {
		if (!estaCheia()) {
			if (posicao >= 0 && posicao <= quantidade) {
				int posicaoFisica = mapeamento(posicao);
				int x = ponteiroFim;
				int y = avancar(x);
				for (int i = 0; i < quantidade-posicao;i++) {
					dados[x] = dados[y];
					x = retroceder(x);
					y = retroceder(y);
				}
				dados[posicaoFisica] = dado;
				quantidade++;
				avancar(ponteiroFim);
			} else {
				System.err.println("Invalid Index!");
			}
		} else {
			System.err.println("List is empty!");
		}
	}
	
	@Override
	public Object apagar(int posicao) {
		Object dadoAux = null;
		if (!estaVazia()) {
			if (posicao >= 0 && posicao < quantidade) {
				int posicaoFisica = mapeamento(posicao);
				dadoAux = dados[posicaoFisica];
				int x = posicaoFisica;
				int y = avancar(x);
				for (int i = 0; i < quantidade-posicao-1;i++) {	
					dados[x] = dados[y];
					x = avancar(x);
					y = avancar(y);					
				}
				quantidade--;
				ponteiroFim = retroceder(ponteiroFim);
			} else {
				System.err.println("Invalid Index!");
			}
		} else {
			System.err.println("List is empty!");
		}
		return dadoAux;
	}
}