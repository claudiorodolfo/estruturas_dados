public class FilaDinamicaPrincipal {
	public static void main(String[] args) {
		//Enfileiravel fila = new FilaEstatica();
		Enfileiravel fila = new FilaDinamica();
		fila.enfileirar("A");
		System.out.println(fila.espiar());		
		fila.enfileirar("B");
		fila.enfileirar("C");
		fila.enfileirar("D");
		fila.desenfileirar();
		fila.enfileirar("E");
		Object conteudo = fila.desenfileirar();
		fila.enfileirar("F");
		System.out.println(fila.espiar());
		System.out.println(fila.espiar());		
		fila.enfileirar("G");
		fila.enfileirar(conteudo);
		fila.enfileirar("I");
		fila.enfileirar("J");
		fila.enfileirar("K");					
		System.out.println("Fila: " + fila.imprimir());
	}
}