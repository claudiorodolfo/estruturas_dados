public class FilaEstaticaDuplaTerminacaoPrincipal {
	void main() {
		DuplamenteEnfileiravel fila = new FilaEstaticaDuplaTerminacao();
		fila.enfileirarInicio("A");
		IO.println(fila.imprimirDeFrentePraTras()); //[A]
		IO.println(fila.tras()); //A
		IO.println(fila.frente()); //A	
		fila.enfileirarInicio("B");
		fila.desenfileirarFim();
		fila.enfileirarFim("C");
		fila.atualizarInicio("Y");
		fila.desenfileirarInicio();
		fila.enfileirarInicio("D"); 
		IO.println(fila.imprimirDeTrasPraFrente()); //[C,D]
		fila.frente(); 
		fila.desenfileirarFim();
		IO.println(fila.tras()); //D
		fila.enfileirarInicio("E");
		fila.atualizarFim("R");
		IO.println(fila.imprimirDeTrasPraFrente());//[R,E]
		fila.enfileirarFim("F");
		fila.desenfileirarInicio();
		fila.enfileirarFim("G");
		IO.println(fila.tras()); //G
		fila.enfileirarInicio("H");
		fila.desenfileirarFim();
		fila.enfileirarFim("I");
		IO.println(fila.imprimirDeFrentePraTras()); //[H,R,F,I]
		fila.enfileirarInicio("J");
		fila.enfileirarFim("K");
		fila.enfileirarFim("L");
		IO.println(fila.frente()); //J
		fila.desenfileirarInicio();
		IO.println(fila.imprimirDeFrentePraTras()); //[H,R,F,I,K,L]
	}
}
