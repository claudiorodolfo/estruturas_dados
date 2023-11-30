public class FilaDinamicaDuplaTerminacao implements DuplamenteEnfileiravel {
	
	private int quantidade;
	private int tamanho;
	private NoDuplo ponteiroInicio;
	private NoDuplo ponteiroFim;
	
	public FilaDinamicaDuplaTerminacao(int tamanho) {
		quantidade = 0;
		this.tamanho = tamanho;
		ponteiroInicio = null;
		ponteiroFim = null;
	}

	public FilaDinamicaDuplaTerminacao() {
		this(10);
	}

	//funciona como o enfileirar de FilaDinamica
	@Override
	public void enfileirarFim(Object dado) {
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
	
	//funciona como o desenfileirar de FilaDinamica
	@Override	
	public Object desenfileirarInicio() {
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
	
	//funciona como o espiar de FilaDinamica
	@Override
	public Object espiarInicio() {
		Object dadoInicio = null;
		if(!estaVazia()) {
			dadoInicio = ponteiroInicio.getDado();
		} else {
			System.out.println("Fila Vazia!");			
		}
		return dadoInicio;
	}
	
	@Override
	public void enfileirarInicio(Object dado) {
		if(!estaCheia()) {
			NoDuplo noTemporario = new NoDuplo();
			noTemporario.setDado(dado);
			if (!estaVazia()) {
				ponteiroInicio.setAnterior(noTemporario);
			} else  {
				ponteiroFim = noTemporario;
			}
			noTemporario.setProximo(ponteiroInicio);
			ponteiroInicio = noTemporario;
			quantidade++;
		} else {
			System.out.println("Fila Cheia!");
		}	
	}
		
	@Override
	public Object desenfileirarFim() {
		Object dadoFim = null;
		if(!estaVazia()) {
			dadoFim = ponteiroFim.getDado();
			ponteiroFim = ponteiroFim.getAnterior();
			quantidade--;
			if (!estaVazia()) {
				ponteiroFim.setProximo(null);
			} else {
				ponteiroInicio = null;
			}
		} else {
			System.out.println("Fila Vazia!");			
		}
		return dadoFim;		
	}
			
	@Override
	public Object espiarFim() {
		Object dadoFim = null;
		if(!estaVazia()) {
			dadoFim = ponteiroFim.getDado();
		} else {
			System.out.println("Fila Vazia!");			
		}
		return dadoFim;
	}

	//funciona como o estaCheia de FilaDinamica	
	@Override
	public boolean estaCheia() {
		return (quantidade == tamanho);
	}

	//funciona como o estaVazia de FilaDinamica
	@Override
	public boolean estaVazia(){
		return (quantidade == 0);
	}
	
	//funciona como o imprimir de FilaDinamica
	@Override
	public String imprimir() {
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