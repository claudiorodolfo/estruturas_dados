import java.util.NoSuchElementException;

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
		if(estaCheia()) {
			throw new NoSuchElementException("Fila Cheia!");
		}
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
	}

	@Override
	public T desenfileirar(){
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		T dadoInicio = ponteiroInicio.getDado();
		ponteiroInicio = ponteiroInicio.getProximo();
		quantidade--;
		if (!estaVazia()) {
			ponteiroInicio.setAnterior(null);
		} else {
			ponteiroFim = null;
		}
		return dadoInicio;		
	}

	@Override
	public void atualizar(T novoDado){
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		ponteiroInicio.setDado(novoDado);
	}

	@Override
	public T frente(){
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		T dadoInicio = ponteiroInicio.getDado();
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
			resultado += noAuxiliar.getDado();
			if (i != quantidade-1) {
				resultado +=  ",";
			}
			noAuxiliar = noAuxiliar.getProximo();
		}
		return resultado + "]";
	}
}