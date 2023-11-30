public class ListaEstaticaCircularPrincipal {
	public static void main(String[] args) {
		Listavel lista = new ListaEstaticaCircular();
		lista.anexar("A");
		lista.anexar("B");
		lista.inserir(2,"C");
		System.out.println(lista.imprimir()); //[A,B,C]
		lista.anexar("D");
		lista.apagar(0);
		lista.inserir(0,"E");
		lista.inserir(2,"F");		
		System.out.println(lista.imprimir()); //[E,B,F,C,D]
		System.out.println(lista.selecionar(1)); //B
		lista.atualizar(1,"G"); 
		System.out.println(lista.imprimir()); //[E,G,F,C,D]
	}
}