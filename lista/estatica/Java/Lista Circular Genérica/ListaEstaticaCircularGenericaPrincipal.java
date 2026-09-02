// Acessar o diretório dos fontes:
// cd "lista/estatica/Java/Lista Circular Genérica"
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out ListaEstaticaCircularGenericaPrincipal
public class ListaEstaticaCircularGenericaPrincipal {
	void main() {
		Listavel<Character> lista = new ListaEstaticaCircularGenerica<>();
		lista.anexar('A');
		lista.anexar('B');
		lista.inserir(2,'C');
		IO.println(lista.imprimir()); //[A,B,C]
		lista.anexar('D');
		lista.apagar(0);
		lista.inserir(0,'E');
		lista.inserir(2,'F');		
		IO.println(lista.imprimir()); //[E,B,F,C,D]
		IO.println(lista.selecionar(1)); //B
		lista.atualizar(1,'G'); 
		IO.println(lista.imprimir()); //[E,G,F,C,D]
	}
}