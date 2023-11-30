public class FilaDinamica implements Enfileiravel {

	private int quantidade;
	private int tamanho;
	private NoDuplo ponteiroInicio;
	private NoDuplo ponteiroFim;

	public FilaDinamica(int tamanho) {
		quantidade = 0;
		this.tamanho = tamanho;
		ponteiroInicio = null;
		ponteiroFim = null;
	}

	public FilaDinamica() {
		this(10);
	}

	@Override
	public void enfileirar(Object dado){
		if(!estaCheia()) {
			NoDuplo noTemporario = new NoDuplo();
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
			System.out.println("Fila Cheia!");
		}
	}

	@Override
	public Object desenfileirar(){
		Object dadoInicio = null;
		if(!estaVazia()) {
			dadoInicio = ponteiroInicio.getDado();
			ponteiroInicio = ponteiroInicio.getProximo();
			quantidade--;
			if (!estaVazia()) {
				ponteiroInicio.setAnterior(null);
			} else {
				ponteiroFim = null;
			}
		} else {
			System.out.println("Fila Vazia!");			
		}
		return dadoInicio;		
	}

	@Override
	public Object espiar(){
		Object dadoInicio = null;
		if(!estaVazia()) {
			dadoInicio = ponteiroInicio.getDado();
		} else {
			System.out.println("Fila Vazia!");			
		}
		return dadoInicio;
	}

	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	@Override
	public boolean estaVazia() {
		return (quantidade == 0);
	}

	@Override
	public String imprimir(){
		String resultado = "[";
		NoDuplo noAuxiliar = ponteiroInicio;
		for (int i = 0; i < quantidade; i++) {
			if (i == quantidade-1) {
				resultado += noAuxiliar.getDado();
			} else {
				resultado += noAuxiliar.getDado() + ",";
			}
			noAuxiliar = noAuxiliar.getProximo();
		}
		return resultado + "]";
	}
}