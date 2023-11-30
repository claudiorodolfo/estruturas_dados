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
	public String imprimir() {
		String resultado = "[";
		for (int i = 0, ponteiroAux = ponteiroInicio; i < quantidade; i++, ponteiroAux++) {
			if (i == quantidade - 1) {
				resultado += dados[ponteiroAux % dados.length];
			} else {
				resultado += dados[ponteiroAux % dados.length] + ",";		
			}
		}	
		return resultado + "]";
	}
	
	//funciona como o enfileirar de FilaEstaticaCircular
	@Override
	public void anexar(Object dado) {
		if (!estaCheia()) {
			ponteiroFim++;			
			if (ponteiroFim == dados.length) {
				ponteiroFim = 0;
			}
			quantidade++;
			dados[ponteiroFim] = dado;			
		} else {
			System.err.println("Lista Cheia!");
		}			
	}

	@Override
	public Object[] selecionarTodos() {
        Object[] dadosAux = null;
        if (!estaVazia()) {
            dadosAux = new Object[quantidade];
            for (int i = 0, ponteiroAux = ponteiroInicio; i < quantidade; i++, ponteiroAux++)  {
				if (ponteiroAux == dados.length) {
                    ponteiroAux = 0;
                }
				dadosAux[i] = dados[ponteiroAux];
            }
        }
        return dadosAux;
	}

	@Override
	public Object selecionar (int posicao) {
		Object dadoAux = null;
		if (!estaVazia()) {
			//verificando se o índice/posição é válido 
			if ((posicao >= 0) && (posicao < quantidade)) {
				//mapeamento:
				//DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o dado está no array)
				int posicaoFisica = (ponteiroInicio + posicao) % dados.length;
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
				//mapeamento:
				//DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o dado está no array)
				int posicaoFisica = (ponteiroInicio + posicao) % dados.length;
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
			//verificar se a posicao informada é valida
			if ((posicao >= 0) && (posicao <= quantidade)) {
				//mapeamento:
				//DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o dado está no array)
				int posicaoFisica = (ponteiroInicio + posicao) % dados.length;

				for (int i = ponteiroFim+1; i!= posicaoFisica; i--) {
					int anterior = i-1;

					if(i == dados.length) {
						i = 0;				
					} 
					int atual = i;

					dados[atual] = dados[anterior];
				}

				dados[posicaoFisica] = dado;
				ponteiroFim++;
				if (ponteiroFim == dados.length) {
					ponteiroFim = 0;
				}
				quantidade++;
			} else {
				System.err.println("Indice Invalido");
			}
		} else {
			System.err.println("Lista Cheia!");
		}
	}
	
	@Override
	public Object apagar(int posicao) {
		Object dadoAux = null;
		if (!estaVazia()) {
			//verificar se a posicao informada é valida
			if ((posicao >= 0) && (posicao < quantidade)) {
				//mapeamento:
				//DE endereçamento lógico (informado pelo usuário)
				//PARA endereçamento físico (onde o dado está no array)
				int posicaoFisica = (ponteiroInicio + posicao) % dados.length;
				dadoAux = dados[posicaoFisica];

				for (int i = posicaoFisica; i != ponteiroFim ; i++) {
					int atual = i;
					if (i == dados.length-1) {
						i = -1;					
					}
					int proximo = i+1;

					dados[atual] = dados[proximo];
				}
				ponteiroFim--;
				if (ponteiroFim == -1) {
					ponteiroFim = dados.length - 1;
				}
				quantidade--;
			} else {
				System.err.println("Indice Invalido!");
			}
		} else {
			System.err.println("Lista Vazia!");
		}
		return dadoAux;
	}
}