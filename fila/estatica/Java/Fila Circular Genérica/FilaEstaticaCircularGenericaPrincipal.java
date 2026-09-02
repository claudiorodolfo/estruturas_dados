public class FilaEstaticaCircularGenericaPrincipal {
	void main() {
		EnfileiravelGenerica<Character> fila = new FilaEstaticaCircularGenerica<>();
		fila.enfileirar('A');
		IO.println(fila.frente());		
		fila.enfileirar('B');
		fila.enfileirar('C');
		fila.atualizarInicio('Y');
		fila.enfileirar('D');
		fila.desenfileirar();
		fila.enfileirar('E');
		char conteudo = fila.desenfileirar();
		fila.enfileirar('F');
		IO.println(fila.frente());
		IO.println(fila.frente());		
		fila.enfileirar('G');
		fila.enfileirar(conteudo);
		fila.enfileirar('I');
		fila.enfileirar('J');
		fila.atualizarFim('K');
		fila.enfileirar('K');					
		IO.println("Fila: " + fila.imprimir());
	}
}