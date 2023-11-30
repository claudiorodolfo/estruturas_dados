public class InsertionsortDinamico implements Ordenavel {

	private NoDuplo ponteiroInicio;
	private NoDuplo ponteiroFim;
	private int quantidade;
	
	public InsertionsortDinamico() {
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
		NoDuplo indiceI = ponteiroInicio.getProximo();
		for (int i = 1; i < quantidade; i++) {
			NoDuplo indiceJ = indiceI;
			while (!(indiceJ.equals(ponteiroInicio)) && ((Integer) indiceJ.getDado() < (Integer)(indiceJ.getAnterior()).getDado())) {
				trocar(indiceJ, indiceJ.getAnterior());	
				indiceJ = indiceJ.getAnterior();
			}
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