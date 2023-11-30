public class FilaEstaticaDuplaTerminacaoPrincipal {
	public static void main(String[] args) {
		DuplamenteEnfileiravel fila = new FilaEstaticaDuplaTerminacao();
		fila.enfileirarInicio("A");
		System.out.println(fila.imprimir()); //[A]
		System.out.println(fila.espiarFim()); //A
		System.out.println(fila.espiarInicio()); //A	
		fila.enfileirarInicio("B");
		fila.desenfileirarFim();
		fila.enfileirarFim("C");
		fila.desenfileirarInicio();
		fila.enfileirarInicio("D"); 
		System.out.println(fila.imprimir()); //[D,C]
		fila.espiarInicio(); 
		fila.desenfileirarFim();
		System.out.println(fila.espiarFim()); //D
		fila.enfileirarInicio("E");
		System.out.println(fila.imprimir());//[E,D]
		fila.enfileirarFim("F");
		fila.desenfileirarInicio();
		fila.enfileirarFim("G");
		System.out.println(fila.espiarFim()); //G
		fila.enfileirarInicio("H");
		fila.desenfileirarFim();
		fila.enfileirarFim("I");
		System.out.println(fila.imprimir()); //[H,D,F,I]
		fila.enfileirarInicio("J");
		fila.enfileirarFim("K");
		fila.enfileirarFim("L");
		System.out.println(fila.espiarInicio()); //J
		fila.desenfileirarInicio();
		System.out.println(fila.imprimir()); //[H,D,F,I,K,L]
	}
}
