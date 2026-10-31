public class Q2<T> {
    //TIPO 1
	/**
	 * Insere o objeto novo imediatamente antes do objeto procurado.
	 * Se o objeto procurado não existir na lista, o novo objeto é inserido
	 * antes de todos os elementos existentes. A lista não contém objetos repetidos.
	 *
	 * @param procurado o objeto que servirá de referência para a inserção
	 * @param novo o objeto a ser inserido imediatamente antes do procurado
	 * @throws OverflowException se a lista estiver cheia
	 */
	public void inserirAntes(T procurado, T novo) {
		if (estaCheia()) {
			throw new OverflowException("Lista Cheia!");
		}

		int posicao = 0;
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		while (ponteiroAuxiliar != null) {
			T dado = ponteiroAuxiliar.getDado();
			if (dado == procurado || (dado != null && dado.equals(procurado))) {
				inserir(posicao, novo);
				return;
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
			posicao++;
		}

		inserir(0, novo);
	}

	//TIPO 2
	/**
	 * Insere o objeto novo imediatamente após o objeto procurado.
	 * Se o objeto procurado não existir na lista, o novo objeto é inserido
	 * após todos os elementos existentes. A lista não contém objetos repetidos.
	 *
	 * @param procurado o objeto que servirá de referência para a inserção
	 * @param novo o objeto a ser inserido imediatamente após o procurado
	 * @throws OverflowException se a lista estiver cheia
	 */
	public void inserirDepois(T procurado, T novo) {
		if (estaCheia()) {
			throw new OverflowException("Lista Cheia!");
		}

		int posicao = 0;
		NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
		while (ponteiroAuxiliar != null) {
			T dado = ponteiroAuxiliar.getDado();
			if (dado == procurado || (dado != null && dado.equals(procurado))) {
				inserir(posicao + 1, novo);
				return;
			}
			ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
			posicao++;
		}

		anexar(novo);
	}
}
