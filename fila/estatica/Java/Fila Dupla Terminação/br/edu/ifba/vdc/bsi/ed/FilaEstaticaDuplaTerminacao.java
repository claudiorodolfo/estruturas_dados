package br.edu.ifba.vdc.bsi.ed;

public class FilaEstaticaDuplaTerminacao extends FilaEstaticaCircular implements DuplamenteEnfileiravel {
	
	public FilaEstaticaDuplaTerminacao(int tamanho) {
		super(tamanho);
	}

	public FilaEstaticaDuplaTerminacao() {
		super();
	}
	
	@Override
	public void enfileirarInicio(Object dado) {
		if (!estaCheia()){
			ponteiroInicio = retroceder(ponteiroInicio);
			if (estaVazia()) {
				ponteiroFim = ponteiroInicio;
			}
			dados[ponteiroInicio] = dado;
			quantidade++;
		} else {
			IO.println("Queue is full!");
		}
	}
	
	@Override
	public Object desenfileirarFim() {
		Object dadoFim = null;
		if (!estaVazia()){
			dadoFim = dados[ponteiroFim];
			ponteiroFim = retroceder(ponteiroFim);
			quantidade--;
		} else {
			IO.println("Queue is empty!");
		}
		return dadoFim;			
	}
	
	@Override
	public Object tras() {
		Object dadoFim = null;	
		if (!estaVazia()) {
			dadoFim = dados[ponteiroFim];
		} else {
			IO.println("Fila Vazia!");		
		}
		return dadoFim;
	}

	@Override
	public String imprimirDeTrasPraFrente() {
		String retorno = "";
		int ponteiroAux = ponteiroFim;
		for (int i = 0; i < quantidade; i++) {			
			retorno += dados[ponteiroAux];
			if (i != quantidade - 1) 
				retorno += ",";

			ponteiroAux = retroceder(ponteiroAux); 
		}
		return "[" + retorno + "]";		
	}
	
	private int retroceder(int ponteiro) {
		return ((ponteiro-1) + dados.length) % dados.length;
	}	
}