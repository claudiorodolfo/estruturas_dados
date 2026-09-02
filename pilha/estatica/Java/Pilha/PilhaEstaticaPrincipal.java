//Para compilar os arquivos. javac *.java
//Para rodar o programa. java PilhaEstaticaPrincipal
public class PilhaEstaticaPrincipal {
	void main() {
		Empilhavel pilha = new PilhaEstatica(7);
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