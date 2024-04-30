public class SelectionsortDinamico implements Ordenavel {

	private NoDuplo ponteiroInicio;
	private NoDuplo ponteiroFim;
	private int quantidade;
	
	public SelectionsortDinamico() {
		quantidade = 0;
	}

	public void anexar(Object dado) {
		NoDuplo noTemporario = new NoDuplo();
		noTemporario.setDado(dado);
		if (quantidade == 0) {
			ponteiroInicio = noTemporario;
		} else  {
			ponteiroFim.setProximo(noTemporario);
		}
		noTemporario.setAnterior(ponteiroFim);
		ponteiroFim = noTemporario;
		quantidade++;
	}
	
	@Override
	public void ordenar() {
		NoDuplo indiceI = ponteiroInicio;
        for (int i = 0; i < quantidade; i++) {
			NoDuplo indiceMenor = indiceI;
			NoDuplo indiceJ = indiceI.getProximo();
            for (int j = i+1; j < quantidade; j++) {
                if ((Integer) indiceJ.getDado() < (Integer) indiceMenor.getDado()) {
                    indiceMenor = indiceJ;
                }
				indiceJ = indiceJ.getProximo();
            }
			trocar(indiceI, indiceMenor);
			indiceI = indiceI.getProximo();
        }
    }
	
	private void trocar(NoDuplo a, NoDuplo b) {
		Object aux = a.getDado();
		a.setDado(b.getDado());
		b.setDado(aux);
	}
	
	@Override
	public String imprimir() {
		String resultado = "[";
		NoDuplo ponteiroAuxiliar = ponteiroInicio;		
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