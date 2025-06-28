public class ListaEstaticaCircularGenerica<T> implements Listavel<T> {
	
	private int ponteiroInicio; 
	private int ponteiroFim;
	private T[] dados;
	private int quantidade;
	
	public ListaEstaticaCircularGenerica() {
		this(10);
	}

	@SuppressWarnings("unchecked")	
	public ListaEstaticaCircularGenerica(int tamanho) {
		ponteiroInicio = 0;
		ponteiroFim = -1;
		quantidade = 0;		
		dados = (T[]) new Object[tamanho];
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
	public void anexar(T dado) {
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

	@SuppressWarnings("unchecked")	
	@Override
	public T[] selecionarTodos() {
        T[] dadosAux = null;
        if (!estaVazia()) {
            dadosAux = (T[]) new Object[quantidade];
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
	public T selecionar (int posicao) {
		T dadoAux = null;
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
	public void atualizar(int posicao, T novoDado) {
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
	public void inserir(int posicao, T dado) {
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
	public T apagar(int posicao) {
		T dadoAux = null;
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