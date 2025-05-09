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
	public void enfileirarInicio(T dado) {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	@Override
	public T desenfileirarFim() {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	@Override
	public T tras() {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	@Override
	public String imprimirDeTrasPraFrente() {
		throw new UnsupportedOperationException("Operação não suportada!");
	}

	@Override
	public void enfileirarFim(T dado) {
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
	public T desenfileirarInicio() {
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
	public T frente() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		T dadoInicio = ponteiroInicio.getDado();
		return dadoInicio;
	}

	@Override
	public void atualizarInicio(T dado) {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		ponteiroInicio.setDado(dado);
	}

	@Override
	public void atualizarFim(T dado) {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		ponteiroFim.setDado(dado);
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
	public String imprimirDeFrentePraTras(){
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