// Acessar o diretório dos fontes:
// cd "pilha/estatica/Java/Pilha Genérica"
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.PilhaEstaticaGenericaPrincipal
package br.edu.ifba.vdc.bsi.ed;

public class PilhaEstaticaGenericaPrincipal {
	void main() {
		Empilhavel<String> pilha = new PilhaEstaticaGenerica<>(7);
		pilha.empilhar("Instituto");
		pilha.empilhar("Federal");
		IO.println("Espiar:" + pilha.espiar());		
		pilha.empilhar("de");
		pilha.empilhar("Educação");
		pilha.empilhar("Ciência");
		pilha.empilhar("e");		
		IO.println("Espiar:" + pilha.espiar());		
		String conteudo = pilha.desempilhar();
		pilha.desempilhar();
		pilha.empilhar("Tecnologia");
		pilha.empilhar("da");
		pilha.empilhar("Bahia");		
		pilha.atualizar("São Paulo");		
		pilha.empilhar(pilha.desempilhar());
		pilha.empilhar(conteudo);
		IO.println("Pilha="+pilha.imprimir());
	}
}