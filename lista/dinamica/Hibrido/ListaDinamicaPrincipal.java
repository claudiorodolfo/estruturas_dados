public class ListaDinamicaPrincipal {
	void main() {
		Listavel lista = new ListaDinamica();
		lista.anexar("A");
		lista.anexar("B");
		lista.inserir(2,"C");
		IO.println(lista.imprimir()); //[A,B,C]
		lista.anexar("D");
		lista.apagar(0);
		lista.inserir(0,"E");
		lista.inserir(2,"F");		
		IO.println(lista.imprimir()); //[E,B,F,C,D]
		IO.println(lista.selecionar(1)); //B
		lista.atualizar(1,"G"); 
		IO.println(lista.imprimir()); //[E,G,F,C,D]
	}
}