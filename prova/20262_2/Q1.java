public class Q1<T> {
    //TIPO 1
	/**
	 * Retorna os dados desde o início da lista até a posição lógica informada,
	 * sem incluir o elemento dessa posição. A primeira posição lógica da lista é 0.
	 * O intervalo é exclusivo: com a lista [A, B, C, D], {@code selecionarParteA(2)}
	 * devolve [A, B]. A posição pode ser igual à quantidade de elementos, caso em
	 * que o retorno contém a lista inteira.
	 *
	 * @param posicao a posição lógica exclusiva até onde os dados serão selecionados
	 * @return array com os elementos do início da lista até a posição informada
	 * @throws UnderflowException se a lista estiver vazia
	 * @throws IndexOutOfBoundsException se a posição for inválida
	 */
	public T[] selecionarParteA(int posicao) {
		if (estaVazia()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (!(posicao >= 0 && posicao <= quantidade)) {
			throw new IndexOutOfBoundsException("Indice Invalido!");
		}

		T[] dadosTemporario = (T[]) new Object[posicao];
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < posicao; i++) {
			dadosTemporario[i] = ponteiroAuxiliar.getDado();
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		return dadosTemporario;
	}

	//TIPO 2
	/**
	 * Retorna os dados a partir da posição lógica informada, incluindo o
	 * elemento dessa posição, até o fim da lista. A primeira posição lógica
	 * da lista é 0. Com a lista [A, B, C, D], {@code selecionarParteB(2)}
	 * devolve [C, D].
	 *
	 * @param posicao a posição lógica a partir da qual os dados serão selecionados, inclusive
	 * @return array com os elementos da posição informada até o fim da lista
	 * @throws UnderflowException se a lista estiver vazia
	 * @throws IndexOutOfBoundsException se a posição for inválida
	 */
	public T[] selecionarParteB(int posicao) {
		if (estaVazia()) {
			throw new UnderflowException("Lista Vazia!");
		}
		if (!(posicao >= 0 && posicao < quantidade)) {
			throw new IndexOutOfBoundsException("Indice Invalido!");
		}

		int tamanhoParte = quantidade - posicao;
		T[] dadosTemporario = (T[]) new Object[tamanhoParte];
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		for (int i = 0; i < posicao; i++) {
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		for (int i = 0; i < tamanhoParte; i++) {
			dadosTemporario[i] = ponteiroAuxiliar.getDado();
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
		}
		return dadosTemporario;
	}
}
