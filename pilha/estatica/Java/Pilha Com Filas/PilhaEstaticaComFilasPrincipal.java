// Acessar o diretório dos fontes:
// cd "pilha/estatica/Java/Pilha Com Filas"
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out PilhaEstaticaComFilasPrincipal
public class PilhaEstaticaComFilasPrincipal {
	void main() {
		Empilhavel pilha = new PilhaEstaticaComFilas(7);
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