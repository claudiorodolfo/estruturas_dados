public class ListaDinamicaGenerica<T> implements Listavel<T> {

	private int quantidade;
	private int tamanho;
	private NoDuplo<T> ponteiroInicio;
	private NoDuplo<T> ponteiroFim;

	public ListaDinamicaGenerica() {
		this(10);
	}

	public ListaDinamicaGenerica(int tamanho) {
		ponteiroInicio = null;
		ponteiroFim = null;
		quantidade = 0;
		this.tamanho = tamanho;

	}

	/**
	 * Adiciona um dado ao fim da lista.
	 * 
	 * @param dado: o dado a ser inserido na lista.
	 * @throws OverflowException: Caso a lista esteja cheia.
	 */
	@Override
	public void anexar(T dado) {
		if (estaCheia()) {
			throw new OverflowException("Fila Cheia!");
		}
		NoDuplo<T> noTemporario = new NoDuplo<>();
		noTemporario.setDado(dado);
		if (!estaVazia()) {
			ponteiroFim.setProximo(noTemporario);
		} else {
			ponteiroInicio = noTemporario;
		}
		noTemporario.setAnterior(ponteiroFim);
		ponteiroFim = noTemporario;
		quantidade++;

	}

	/**
	 * Insere um dado numa posicao específica da lista.
	 * 
	 * @param posicao: posição que o dado será inserido.
	 * @param dado:    dado a ser inserido na lista.
	 * @throws OverflowException:         Caso a lista esteja cheia.
	 * @throws IndexOutOfBoundsException: Caso o indice seja invalido.
	 */
	@Override
	public void inserir(int posicao, T dado) {
		if (estaCheia()) {
			throw new OverflowException("Lista Cheia!");
		}
		if (!(posicao >= 0 && posicao <= quantidade)) {
			throw new IndexOutOfBoundsException("Indice Invalido!");
		}
		NoDuplo<T> noTemporario = new NoDuplo<>();
		noTemporario.setDado(dado);
		////////////////////////////////
		// Codigo de posicionamento do ponteiro auxiliar, no nodo
		// que serah feita alguma operacao. Lembrando que nesse metodo
		// auxiliar ira parar no nodo subsequente ao nodo que devera
		// ser inserido
		NoDuplo<T> ponteiroAnterior = null;
		NoDuplo<T> ponteiroProximo = ponteiroInicio;

		for (int i = 0; i < posicao; i++) {
			ponteiroAnterior = ponteiroProximo;
			ponteiroProximo = ponteiroProximo.getProximo();
		}

		if (ponteiroAnterior != null) {
			ponteiroAnterior.setProximo(noTemporario);
			// se o anterior é nulo é pq a insercao está sendo no inicio
		} else {
			ponteiroInicio = noTemporario;
		}

		if (ponteiroProximo != null) {
			ponteiroProximo.setAnterior(noTemporario);
			// se o proximo é nulo é pq a insercao está sendo no fim (append)
		} else {
			ponteiroFim = noTemporario;
		}

		noTemporario.setAnterior(ponteiroAnterior);
		noTemporario.setProximo(ponteiroProximo);

		quantidade++;
	}

	/**
	 * Seleciona o dado que está numa posicao logica informada.
	 * 
	 * @param posicao: uma posição de um objeto da lista.
	 * @return: o objeto da posição indicada.
	 * @throws UnderflowException:        Caso a lista esteja vazia.
	 * @throws IndexOutOfBoundsException: Caso o índice seja inválido.
	 */
	@Override
	public T selecionar(int posicao) {
		if (estaVazia()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (!(posicao >= 0 && posicao < quantidade)) {
			throw new IndexOutOfBoundsException("Indice Invalido!");
		}
		////////////////////////////////
		// Codigo de posicionamento do ponteiro auxiliar, no nodo
		// que será feita alguma operação. Esse codigo é o mesmo
		// para os metodos update, delete e select
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < posicao; i++) {
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		///////////////////////////////
		return ponteiroAuxiliar.getDado();
	}

	/**
	 * Seleciona todos os dado da ED.
	 * 
	 * @return: Retorna todos os objetos da Lista.
	 * @throws UnderflowException: Caso a lista esteja vazia.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] selecionarTodos() {
		if (estaVazia()) {
			throw new UnderflowException("Lista Vazia!");
		}
		T[] dadosTemporario = (T[]) new Object[quantidade];
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < quantidade; i++) {
			dadosTemporario[i] = ponteiroAuxiliar.getDado();
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		return dadosTemporario;
	}

	/**
	 * Atualiza o dado de uma posicao logica informada por um novo.
	 * 
	 * @param posicao:  posição que o dado será atualizado.
	 * @param novoDado: novo dado a ser inserido na lista.
	 * @throws UnderflowException:        Caso a lista esteja vazia.
	 * @throws IndexOutOfBoundsException: Caso o índice seja inválido.
	 */
	@Override
	public void atualizar(int posicao, T novoDado) {
		if (estaVazia()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (!(posicao >= 0 && posicao < quantidade)) {
			throw new IndexOutOfBoundsException("Indice Invalido!");
		}

		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < posicao; i++) {
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		ponteiroAuxiliar.setDado(novoDado);
	}

	/**
	 * Apaga o dado de uma posicao logica informada.
	 * 
	 * @param posicao: posição que o dado será apagado.
	 * @return: dado apagado.
	 * @throws UnderflowException:        Caso a lista esteja vazia.
	 * @throws IndexOutOfBoundsException: Caso o índice seja inválido.
	 */
	@Override
	public T apagar(int posicao) {
		if (estaVazia()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (!(posicao >= 0 && posicao < quantidade)) {
			throw new IndexOutOfBoundsException("Indice Invalido!");
		}

		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < posicao; i++) {
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}

		NoDuplo<T> ponteiroAnterior = ponteiroAuxiliar.getAnterior();
		NoDuplo<T> ponteiroProximo = ponteiroAuxiliar.getProximo();

		if (ponteiroAnterior != null) {
			ponteiroAnterior.setProximo(ponteiroProximo);
			// remocao do inicio, joga o ponteiro de inicio para o proximo nodo.
		} else {
			ponteiroInicio = ponteiroInicio.getProximo();
		}
		if (ponteiroProximo != null) {
			ponteiroProximo.setAnterior(ponteiroAnterior);
			// remocao do fim, joga o ponteiro de fim para o nodo anterior.
		} else {
			ponteiroFim = ponteiroFim.getAnterior();
		}

		quantidade--;
		return ponteiroAuxiliar.getDado();
	}

	/**
	 * Informa se a lista está cheia.
	 * 
	 * @return: true se a lista está cheia, false caso contrário.
	 */
	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	/**
	 * Informa se a lista está vazia.
	 * 
	 * @return: true se a lista está vazia, false caso contrário.
	 */
	@Override
	public boolean estaVazia() {
		return (quantidade == 0);
	}

	/**
	 * Imprime o conteúdo da lista.
	 * 
	 * @return: string com todos os dados da lista separados por "," e delimitados
	 *          por ´[´ e ´]´.
	 */
	@Override
	public String imprimir() {
		String resultado = "[";
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < quantidade; i++) {
			resultado += ponteiroAuxiliar.getDado();
			if (i != quantidade - 1) {
				resultado += ",";
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		return resultado + "]";
	}
}