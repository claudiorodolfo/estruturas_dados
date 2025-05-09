public class FilaDinamicaGenerica<T> implements Enfileiravel<T> {

	private int quantidade;
	private int tamanho;
	private NoDuplo<T> ponteiroInicio;
	private NoDuplo<T> ponteiroFim;

	public FilaDinamicaGenerica(int tamanho) {
		quantidade = 0;
		this.tamanho = tamanho;
		ponteiroInicio = null;
		ponteiroFim = null;
	}

	public FilaDinamicaGenerica() {
		this(10);
	}

	@Override
	public void enfileirar(T dado){
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
			System.out.println("Fila Cheia!");
		}
	}

	@Override
	public T desenfileirar(){
		T dadoInicio = null;
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
	public T espiar(){
		T dadoInicio = null;
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
		NoDuplo<T> noAuxiliar = ponteiroInicio;
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