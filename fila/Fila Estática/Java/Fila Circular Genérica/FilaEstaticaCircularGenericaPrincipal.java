public class FilaEstaticaCircularGenericaPrincipal {
	public static void main(String[] args) {
		EnfileiravelGenerica<Character> fila = new FilaEstaticaCircularGenerica<>();
		fila.enfileirar('A');
		System.out.println(fila.frente());		
		fila.enfileirar('B');
		fila.enfileirar('C');
		fila.atualizarInicio('Y');
		fila.enfileirar('D');
		fila.desenfileirar();
		fila.enfileirar('E');
		char conteudo = fila.desenfileirar();
		fila.enfileirar('F');
		System.out.println(fila.frente());
		System.out.println(fila.frente());		
		fila.enfileirar('G');
		fila.enfileirar(conteudo);
		fila.enfileirar('I');
		fila.enfileirar('J');
		fila.atualizarFim('K');
		fila.enfileirar('K');					
		System.out.println("Fila: " + fila.imprimir());
	}
}