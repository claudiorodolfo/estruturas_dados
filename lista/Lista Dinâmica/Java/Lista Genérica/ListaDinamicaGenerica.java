public class ListaDinamicaGenerica<T> implements Listavel<T> {

	private int quantidade;
	private int tamanho;
	private NoDuplo<T> ponteiroInicio;
	private NoDuplo<T> ponteiroFim;

	public ListaDinamicaGenerica() {
		this(10);
	}

	public ListaDinamicaGenerica(int tamanho) {
		quantidade = 0;
		this.tamanho = tamanho;
		ponteiroInicio = null;
		ponteiroFim = null;
	}

	/*
	 * Adiciona um dado ao fim da lista.
	 * @Params: Recebe o dado a ser inserido na lista.
	 * @Return: Não retorna nada.
	 * @Exception: Caso se tente inserir um dado na lista e ela já está cheia.
	 */
	@Override
	public void anexar(T dado) throws OverflowException {
		if(!estaCheia()) {
			NoDuplo<T> noTemporario = new NoDuplo<>();
			noTemporario.setDado(dado);
			if (!estaVazia()) {
				ponteiroFim.setProximo(noTemporario);
			} else  {
				ponteiroInicio = noTemporario;
			}
			noTemporario.setAnterior(ponteiroFim);
			ponteiroFim = noTemporario;
			quantidade++;
		} else {
			throw new OverflowException("Fila Cheia!");
		}
	}

	/*
	 * Insere um dado numa posicao específica da lista.
	 * @Params: Recebe o dado a ser inserido na lista e a sua posição.
	 * @Return: Não retorna nada.
	 * @Exception: Caso tente inserir um dado na lista e ela já está cheia.
	 */
	@Override	
	public void inserir(int posicao, T dado) throws OverflowException {
		if(!estaCheia()) {
			if((posicao >= 0) && (posicao <= quantidade)) {
				NoDuplo<T> noTemporario = new NoDuplo<>();
				noTemporario.setDado(dado);
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que serah feita alguma operacao. Lembrando que nesse metodo
				//auxiliar ira parar no nodo subsequente ao nodo que devera 
				//ser inserido				
				NoDuplo<T> ponteiroAnterior = null;
				NoDuplo<T> ponteiroProximo = ponteiroInicio;

				for (int i = 0; i < posicao; i++) {
					ponteiroAnterior = ponteiroProximo;
					ponteiroProximo = ponteiroProximo.getProximo();
				}

				if (ponteiroAnterior != null) {
					ponteiroAnterior.setProximo(noTemporario);
				//se o anterior é nulo é pq a insercao está sendo no inicio
				} else {
					ponteiroInicio = noTemporario;
				}

				if (ponteiroProximo != null) {
					ponteiroProximo.setAnterior(noTemporario);
				//se o proximo é nulo é pq a insercao está sendo no fim (append)
				} else {
					ponteiroFim = noTemporario;
				}
				
				noTemporario.setAnterior(ponteiroAnterior);
				noTemporario.setProximo(ponteiroProximo);

				quantidade++;
	        } else {
            	System.err.println("Indice Invalido!");
            }
		} else {
			throw new OverflowException("Lista Cheia!");
		}
	}

	/*
	 * Seleciona o dado que está numa posicao logica informada.
	 * @Params: Recebe uma posição de um objeto da lista.
	 * @Return: Retorna o objeto da posição indicada.
	 * @Exception: Não gera exceção.
	 */
	@Override
	public T selecionar(int posicao) {
		T dadoTemporario = null;
		if (!estaVazia()) {
			if ((posicao >= 0) && (posicao < quantidade)) {
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que será feita alguma operação. Esse codigo é o mesmo
				//para os metodos update, delete e select
				NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
				for (int i = 0; i < posicao; i++) {
					ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
				}
				///////////////////////////////
				dadoTemporario = ponteiroAuxiliar.getDado();
            } else {
               System.err.println("Indice Invalido!");
            }
		} else {
			System.err.println("Lista Vazia!");	
		}
		return dadoTemporario;
	}

	/*
	 * Seleciona todos os dado da ED.
	 * @Params: não recebe nada.
	 * @Return: Retorna todos os objetos da Lista.
	 * @Exception: Não gera exceção.
	 */
	@SuppressWarnings("unchecked")	
	@Override
	public T[] selecionarTodos() {
		T[] dadosTemporario = (T[]) new Object[quantidade];
		if (!estaVazia()) {

			NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
			for (int i = 0; i < quantidade; i++) {
				ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
				dadosTemporario[i] = ponteiroAuxiliar.getDado();				
			}
		} else {
			System.err.println("Lista Vazia!");	
		}
		return dadosTemporario;
	}

	/*
	 * Atualiza o dado de uma posicao logica informada por um novo.
	 * @Params: Recebe uma posição de um objeto da lista, e o novo objeto.
	 * @Return: Não retorno nada.
	 * @Exception: Não gera exceção.
	 */	
	@Override
	public void atualizar(int posicao, T novoDado) {
		if (!estaVazia()) {
			if ((posicao >= 0) && (posicao < quantidade)) {
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que serah feita alguma operacao. Esse codigo eh o mesmo
				//para os metodos update, delete e select
				NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
				for (int i = 0; i < posicao; i++) {
					ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
				}
				///////////////////////////////
				ponteiroAuxiliar.setDado(novoDado);
            } else {
               System.err.println("Indice Invalido!");
            }
		} else {
			System.err.println("Lista Vazia!");
		}
	}
	
	/*
	 * Apaga o dado de uma posicao logica informada.
	 * @Params: Recebe uma posição de um objeto da lista.
	 * @Return: O objeto apagado.
	 * @Exception: Caso tente-se apagar um dado de uma lista vazia.
	 */	 
	@Override
	public T apagar(int posicao) throws UnderflowException {
		T dadoTemporario = null;
		if (!estaVazia()) {
			if ((posicao >=0) && (posicao < quantidade)) {
				////////////////////////////////
				//Codigo de posicionamento do ponteiro auxiliar, no nodo
				//que serah feita alguma operacao. Esse codigo eh o mesmo
				//para os metodos update, delete e select
				NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
				for (int i = 0; i < posicao; i++) {
					ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
				}
				///////////////////////////////
				dadoTemporario = ponteiroAuxiliar.getDado();

				NoDuplo<T> ponteiroAnterior = ponteiroAuxiliar.getAnterior();
				NoDuplo<T> ponteiroProximo = ponteiroAuxiliar.getProximo();

				if (ponteiroAnterior != null) {
					ponteiroAnterior.setProximo(ponteiroProximo);
				//remocao do inicio, joga o ponteiro de inicio para o proximo nodo.
				} else {
					ponteiroInicio = ponteiroInicio.getProximo();
				}
				if (ponteiroProximo != null) {
					ponteiroProximo.setAnterior(ponteiroAnterior);
				//remocao do fim, joga o ponteiro de fim para o nodo anterior.
				} else {
					ponteiroFim = ponteiroFim.getAnterior();
				}

				quantidade--;
            } else {
               System.err.println("Indice Invalido!!");
            } 
		} else {
			throw new UnderflowException("Lista Vazia!");	
		}	
		return dadoTemporario;	
	}

	/*
	 * Informa se a lista está cheia.
	 * @Params: Não recebe nada.
	 * @Return: Um valor lógico informando se a lista está cheia ou não.
	 * @Exception: Não gera exceção.
	 */	
	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	/*
	 * Informa se a lista está vazia.
	 * @Params: Não recebe nada.
	 * @Return: Um valor lógico informando se a lista está vazia ou não.
	 * @Exception: Não gera exceção.
	 */	
	@Override
	 public boolean estaVazia() {
		return (quantidade == 0);
	}

	/*
	 * Imprime o conteúdo da lista.
	 * @Params: Não recebe nada.
	 * @Return: Uma string com todos os dados da lista separados por ",".
	 * @Exception: Não gera exceção.
	 */	
	@Override
	public String imprimir() {
		String resultado = "[";
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;		
		for (int i = 0; i < quantidade; i++) {
		if (i == quantidade-1) {
				resultado += ponteiroAuxiliar.getDado();
			} else {
				resultado += ponteiroAuxiliar.getDado() + ",";
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		return resultado + "]";
	}
}