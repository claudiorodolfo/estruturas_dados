// Acessar o diretório dos fontes:
// cd "pilha/estatica/Java/Pilha Invertida"
// Compilar e enviar os .class para a pasta out:
// javac -d out br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out br.edu.ifba.vdc.bsi.ed.PilhaEstaticaInvertidaPrincipal
package br.edu.ifba.vdc.bsi.ed;

public class PilhaEstaticaInvertidaPrincipal {
	void main() {
		Empilhavel pilha = new PilhaEstaticaInvertida(7);
		pilha.empilhar("Instituto");
		pilha.empilhar("Federal");
		IO.println("Espiar:" + pilha.espiar());		
		pilha.empilhar("de");
		pilha.empilhar("Educação");
		pilha.empilhar("Ciência");
		pilha.empilhar("e");		
		IO.println("Espiar:" + pilha.espiar());		
		Object conteudo = pilha.desempilhar();
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