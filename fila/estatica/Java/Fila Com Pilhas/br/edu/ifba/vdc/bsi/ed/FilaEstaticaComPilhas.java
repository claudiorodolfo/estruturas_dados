package br.edu.ifba.vdc.bsi.ed;

public class FilaEstaticaComPilhas implements Enfileiravel {

	private Empilhavel p1;
	private Empilhavel p2;
	
	public FilaEstaticaComPilhas() {
		this(10);
	}
	
	public FilaEstaticaComPilhas(int tamanho) {
		p1 = new PilhaEstatica(tamanho);
		p2 = new PilhaEstatica(tamanho);
	}
	
	@Override
	public void enfileirar(Object dado) {
		if (!estaCheia()) {
			//já colocar os elementos como numa fila.. FIFO
			while(!p1.estaVazia()) {
				p2.empilhar(p1.desempilhar());
			}
			
			p1.empilhar(dado);

			while(!p2.estaVazia()) {
				p1.empilhar(p2.desempilhar());
			}			
		} else {
			IO.println("Fila Cheia!");
		}
	}
	
	@Override	
	public Object desenfileirar() {
		Object dadoInicio = null;	
		if (!estaVazia()) {
			dadoInicio = p1.desempilhar();
		} else {
			IO.println("Fila Vazia!");
		}
		return dadoInicio;
	}

	@Override
	public void atualizarInicio(Object dado) {
	}

	@Override
	public void atualizarFim(Object dado) {
	}
	
	@Override	
	public Object frente() {
		Object dadoInicio = null;
		if (!estaVazia()) {
			dadoInicio = p1.espiar();
		} else {
			IO.println("Fila Vazia!");		
		}
		return dadoInicio;
	}
	
	@Override	
	public boolean estaCheia() {
		return p1.estaCheia();
	}
	
	@Override	
	public boolean estaVazia() {
		return p1.estaVazia();
	}
	
	@Override	
	public String imprimir() {
		return p1.imprimir();	
	}
}