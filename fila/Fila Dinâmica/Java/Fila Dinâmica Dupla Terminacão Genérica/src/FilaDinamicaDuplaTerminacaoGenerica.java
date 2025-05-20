import java.util.NoSuchElementException;

public class FilaDinamicaDuplaTerminacaoGenerica<T> implements Enfileiravel<T> {

	private int quantidade;
	private int tamanho;
	private NoDuplo<T> ponteiroInicio;
	private NoDuplo<T> ponteiroFim;

	public FilaDinamicaDuplaTerminacaoGenerica(int tamanho) {
		quantidade = 0;
		this.tamanho = tamanho;
		ponteiroInicio = null;
		ponteiroFim = null;
	}

	public FilaDinamicaDuplaTerminacaoGenerica() {
		this(10);
	}

	@Override
	public void enfileirarInicio(T dado) {
		if(estaCheia()) {
			throw new NoSuchElementException("Fila Cheia!");
		}			
		NoDuplo<T> noTemporario = new NoDuplo<>();
		noTemporario.setDado(dado);
		if (!estaVazia()) {
			ponteiroInicio.setAnterior(noTemporario);
		} else  {
			ponteiroFim = noTemporario;
		}
		noTemporario.setProximo(ponteiroInicio);
		ponteiroInicio = noTemporario;
		quantidade++;
	}

	@Override
	public T desenfileirarFim() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");
		}
		T dadoFim = ponteiroFim.getDado();
		ponteiroFim = ponteiroFim.getAnterior();
		quantidade--;
		if (!estaVazia()) {
			ponteiroFim.setProximo(null);
		} else {
			ponteiroInicio = null;
		}
		return dadoFim;	
	}

	@Override
	public T tras() {
		if(estaVazia()) {
			throw new NoSuchElementException("Fila Vazia!");			
		}
		T dadoFim = ponteiroFim.getDado();
		return dadoFim;
	}

	@Override
	public String imprimirDeTrasPraFrente() {
		String resultado = "[";
		NoDuplo<T> noAuxiliar = ponteiroFim;
		for (int i = 0; i < quantidade; i++) {
			resultado += noAuxiliar.getDado();
			if (i != quantidade-1) {
				resultado += ",";
			}
			noAuxiliar = noAuxiliar.getAnterior();
		}
		return resultado + "]";
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
	public String imprimirDeFrentePraTras() {
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