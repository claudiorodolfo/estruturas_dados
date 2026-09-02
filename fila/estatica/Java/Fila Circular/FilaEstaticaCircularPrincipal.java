public class FilaEstaticaCircularPrincipal {
	void main() {
		Enfileiravel fila = new FilaEstaticaCircular();
		fila.enfileirar("A");
		IO.println(fila.frente());		
		fila.enfileirar("B");
		fila.enfileirar("C");
		fila.atualizarInicio("H");
		fila.enfileirar("D");
		fila.desenfileirar();
		fila.enfileirar("E");
		Object conteudo = fila.desenfileirar();
		fila.enfileirar("F");
		fila.atualizarFim("J");
		IO.println(fila.frente());
		IO.println(fila.frente());		
		fila.enfileirar("G");
		fila.enfileirar(conteudo);
		fila.enfileirar("I");
		fila.atualizarInicio("K");
		fila.enfileirar("J");
		fila.enfileirar("K");					
		IO.println("Fila: " + fila.imprimir());
	}
}