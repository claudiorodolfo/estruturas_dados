// Acessar o diretório dos fontes:
// cd lista/dinamica/Hibrido
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.ListaDinamicaPrincipal
package br.edu.ifba.vdc.bsi.ed;

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