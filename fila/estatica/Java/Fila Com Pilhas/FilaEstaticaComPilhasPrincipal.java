public class FilaEstaticaComPilhasPrincipal {
	
	void main() {
		Enfileiravel fila = new FilaEstaticaComPilhas();
		fila.enfileirar("A");
		IO.println(fila.frente());		
		fila.enfileirar("B");
		fila.enfileirar("C");
		fila.atualizarInicio("R");
		fila.enfileirar("D");
		fila.desenfileirar();
		fila.enfileirar("E");
		Object conteudo = fila.desenfileirar();
		fila.enfileirar("F");
		IO.println(fila.frente());
		IO.println(fila.frente());		
		fila.enfileirar("G");
		fila.enfileirar(conteudo);
		fila.enfileirar("I");
		fila.enfileirar("J");
		fila.atualizarFim("S");
		fila.enfileirar("K");					
		IO.println("Fila: " + fila.imprimir());
	}
}