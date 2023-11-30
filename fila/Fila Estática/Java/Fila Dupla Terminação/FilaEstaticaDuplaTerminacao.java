public class FilaEstaticaDuplaTerminacao implements DuplamenteEnfileiravel {
	
	private int ponteiroInicio;
	private int ponteiroFim;
	private Object[] dados;
	private int quantidade;
	
	public FilaEstaticaDuplaTerminacao(int tamanho) {
		ponteiroInicio = 0;
		ponteiroFim = -1;
		quantidade = 0;		
		dados = new Object[tamanho];
	}

	public FilaEstaticaDuplaTerminacao() {
		this(10);
	}

	//funciona como o enfileirar de FilaEstaticaCircular
	@Override
	public void enfileirarFim(Object dado) {
		if (!estaCheia()) {
			ponteiroFim++;

			if (ponteiroFim == dados.length) {
				ponteiroFim = 0;
			}
			quantidade++;
	
			dados[ponteiroFim] = dado;
		} else {
			System.err.println("Fila Cheia!");
		}			
	}
	
	//funciona como o desenfileirar de FilaEstaticaCircular
	@Override	
	public Object desenfileirarInicio() {
		Object dadoInicio = null;	
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio];
			ponteiroInicio++;	

			if (ponteiroInicio == dados.length) {
				ponteiroInicio = 0;
			}
			quantidade--;
					
		} else {
			System.err.println("Fila Vazia!");
		}
		return dadoInicio;			
	}
	
	//funciona como o espiar de FilaEstaticaCircular
	@Override
	public Object espiarInicio() {
		Object dadoInicio = null;
		if (!estaVazia()) {
			dadoInicio = dados[ponteiroInicio];
		} else {
			System.err.println("Fila Vazia!");		
		}
		return dadoInicio;
	}
	
	@Override
	public void enfileirarInicio(Object dado) {
		if (!estaCheia()) {
			ponteiroInicio--;
			if (ponteiroInicio == -1) {
				ponteiroInicio = dados.length - 1;
			}
			dados[ponteiroInicio] = dado;
			//não deixar ponteiroFim esquecido, caso a estrutura esteja na 1ª inserção
			if (estaVazia()) {
				ponteiroFim = ponteiroInicio;
			}
			quantidade++;
		} else {
			System.err.println("Fila Cheia!");
		}
	}
		
	@Override
	public Object desenfileirarFim() {
		Object dadoFim = null;	
		if (!estaVazia()) {
			dadoFim = dados[ponteiroFim];
			ponteiroFim--;

			if (ponteiroFim == -1) {
				ponteiroFim = dados.length - 1;
			}
			quantidade--;

		} else {
			System.err.println("Fila Vazia!");
		}
		return dadoFim;		
	}
			
	@Override
	public Object espiarFim() {
		Object dadoFim = null;	
		if (!estaVazia()) {
			dadoFim = dados[ponteiroFim];
		} else {
			System.err.println("Fila Vazia!");		
		}
		return dadoFim;
	}

	//funciona como o estaCheia de FilaEstaticaCircular	
	@Override
	public boolean estaCheia() {
		return (quantidade == dados.length);
	}

	//funciona como o estaVazia de FilaEstaticaCircular
	@Override
	public boolean estaVazia() {
		return (quantidade == 0);	
	}
	
	//funciona como o imprimir de FilaEstaticaCircular
	@Override
	public String imprimir() {
		String resultado = "[";
		for (int i = 0, ponteiroAux = ponteiroInicio; i < quantidade; i++, ponteiroAux++) {
			if (i == quantidade - 1) {
				resultado += dados[ponteiroAux % dados.length];
			} else {
				resultado += dados[ponteiroAux % dados.length] + ",";		
			}
		}
		return resultado + "]";		
	}
}