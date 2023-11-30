public class ListaEstaticaCircular implements Listavel {
	
	private int ponteiroInicio, ponteiroFim;
	private Object[] dados;
	private int quantidade;
	
	public ListaEstaticaCircular() {
		this(10);
	}
	
	public ListaEstaticaCircular(int tamanho) {
		quantidade = 0;
		ponteiroInicio = -1;
		ponteiroFim = -1;
		dados = new Object[tamanho];
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	//funciona como o estaCheia de FilaEstaticaCircular
	public boolean estaCheia(){
		return (quantidade == dados.length);
	}
	
	//funciona como o estaVazia de FilaEstaticaCircular
	public boolean estaVazia(){
		return (quantidade == 0);
	}
	
	//funciona como o imprimir de FilaEstaticaCircular
	public String imprimir(){
		String resultado = "[";
		
		int i = ponteiroInicio+1;
		for (int auxQtde = 0; auxQtde != quantidade; auxQtde++) {

			//patch para que a lista funcione de forma circular
			if (i == dados.length) {
				i = 0;
			}
			//fim do patch	
			
			if (i == ponteiroFim)
				resultado += dados[i];
			else
				resultado += dados[i] + ",";

			i++;
		}
		return resultado + "]";		
	}
	
	//funciona como o enfileirar de FilaEstaticaCircular
	public void anexar(Object elemento) {
		if (!estaCheia()) {
			//patch para que a fila funcione de forma circular
			if (ponteiroFim == dados.length-1) {
				ponteiroFim = -1;
			}
			quantidade++;
			//fim do patch
			ponteiroFim++;
			dados[ponteiroFim] = elemento;
		} else {
			System.err.println("Lista Cheia!");
		}				
	}

	public Object selecionarUm(int posicao) {
		Object elementoTemp = null;
		if (!estaVazia()) {
			//verificando se o índice/posição é válido 
			if ((posicao >= 0) && (posicao < quantidade)) {
				//mapeamento DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o elemento está no array
				//Instrução comum a maioria dos métodos
				int pontoManipulacao = ((ponteiroInicio+1)+posicao) % dados.length;
				elementoTemp = dados[pontoManipulacao];
			} else {
				System.err.println("Indice Invalido!");	
			}
		} else {
			System.err.println("Lista Vazia!");		
		}
		return elementoTemp;
	}
	
	public void atualizar(int posicao, Object novoElemento) {
		if (!estaVazia()) {
			//verificando se o índice/posição é válido
			if ((posicao >= 0) && (posicao < quantidade)) {
				//mapeamento DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o elemento está no array
				//Instrução comum a maioria dos métodos
				int pontoManipulacao = ((ponteiroInicio+1)+posicao) % dados.length;
				dados[pontoManipulacao] = novoElemento;
			} else {
				System.err.println("Indice Inválido!");	
			}
		} else {
			System.err.println("Lista Vazia!");
		}
	}
	
	public void inserir(int posicao, Object elemento) {
		if (!estaCheia()) {
			//verificar se a posicao informada é valida
			if ((posicao >= 0) && (posicao <= quantidade)) {
				//mapeamento DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o elemento está no array
				//Instrução comum a maioria dos métodos
				int pontoManipulacao = ((ponteiroInicio+1)+posicao) % dados.length;
				for (int i = (ponteiroFim+1)%dados.length; i!= pontoManipulacao; i--) {
					int atual = i;
					int anterior = i-1;
					//patch (correção) para que a lista funcione circular
					if(i == 0) {
						i = dados.length;
						//corrijo o anterior para não ser -1, 
						//uma vez que o atual = 0
						anterior = i-1;						
					} 
					//fim do patch
					dados[atual] = dados[anterior];
				}
				dados[pontoManipulacao] = elemento;
				quantidade++;
				//patch (correção) para que a lista funcione circular
				if (ponteiroFim == dados.length-1) {
					ponteiroFim = -1;
				}
				ponteiroFim++;
				//fim do patch
			} else {
				System.err.println("Indice Inválido");
			}
		} else {
			System.err.println("Lista Cheia!");
		}
	}
	
	public Object apagar(int posicao) {
		Object elementoTemp = null;
		if (!estaVazia()) {
			//verificar se a posicao informada é valida
			if ((posicao >= 0) && (posicao < quantidade)) {
				//mapeamento DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o elemento está no array
				//Instrução comum a maioria dos métodos
				int pontoManipulacao = ((ponteiroInicio+1)+posicao) % dados.length;
				elementoTemp = dados[pontoManipulacao];
				for (int i = pontoManipulacao; i != ponteiroFim ; i++) {
					int atual = i;
					int proximo = i+1;
					//patch (correção) para que a lista funcione circular
					if (i == dados.length-1) {
						//corrijo o proximo para não ser dados.length, 
						//uma vez que o atual é dados.length-1
						proximo = 0;
						i = -1;
					}
					//fim do patch
					dados[atual] = dados[proximo];
				}
				quantidade--;
				ponteiroFim--;
				//patch (correção) para que a lista funcione circular
				if (ponteiroFim == -1) {
					ponteiroFim = dados.length - 1;
				}
				//fim do patch
			} else {
				System.err.println("Indice Inválido!");
			}
		} else {
			System.err.println("Lista Vazia!");
		}
		return elementoTemp;
	}
}