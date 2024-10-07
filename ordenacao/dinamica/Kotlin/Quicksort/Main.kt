fun  buscar(dado: T): NoTriplo<T> {
	var noAuxiliar = raiz
	var retorno: NoTriplo<T> = null
	while (noAuxiliar != null) {
		if (dado == noAuxiliar.dado) {
			retorno = noAuxiliar
			break
		} else {
			if (dado as Int < noAuxiliar.dado as Int)
				if (Auxiliar.esquerda != null)
					noAuxiliar = noAuxiliar.esquerda
				else
					break		
			else
				if (Auxiliar.direita != null)
					noAuxiliar = noAuxiliar.direita
				else
					break
			}
	}
	return retorno
}
    
	
	fun apagar(dado: T) : T {
        var noAuxiliar = buscar(dado)
        // Nó não encontrado na árvore
        if (noAuxiliar == null)   
            return null

        // Caso 1: Nó sem filhos
        if (noAuxiliar.esquerda == null &&
                noAuxiliar.direita == null)
            apagarNoFolha(noAuxiliar)
        // Caso 2: Nó com um filho
        else if (noAuxiliar.esquerda == null ||
                noAuxiliar.direita == null)
            apagarComUmFilho(noAuxiliar)
        // Caso 3: Nó com dois filhos
        else
            apagarComDoisFilhos(noAuxiliar)

        return noAuxiliar
    }    

	fun  apagarNoFolha(nodo: NoTriplo<T>) {
        val pai = nodo.genitor
        if (pai == null)
            raiz = null
        } else {
            if (nodo == pai.esquerda)
                //nodo é filho da esquerda
                pai.esquerda = null
            else
                //nodo é filho da direita
                pai.direita = null
        }
    }

    private void apagarComUmFilho(nodo: NoTriplo<T>) {
        val avo = nodo.genitor
        val neto = 
			if (nodo.esquerda != null) 
				nodo.esquerda
			else
				nodo.direita 
				      
        if (avo == null) {
            raiz = neto
            raiz.setGenitor(null);
        } else {
            neto.genitor == avo
            if (nodo = avo.esquerda) {
                avo.esquerda = neto
            } else {
                avo.direita = neto
            }
        }
    }

