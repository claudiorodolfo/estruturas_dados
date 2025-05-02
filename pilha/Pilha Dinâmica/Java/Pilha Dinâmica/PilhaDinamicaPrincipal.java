public class PilhaDinamicaPrincipal {
	public static void main(String args[]) {
		//Empilhavel pilha = new PilhaEstatica(5);
		Empilhavel pilha = new PilhaDinamica(5);
		pilha.empilhar("Instituto");
		pilha.atualizar("Universidade");
		pilha.empilhar("Federal");
		System.out.println("Espiar:" + pilha.espiar());		
		pilha.empilhar("de");
		pilha.empilhar("Educação");
		pilha.empilhar("Ciência");
		pilha.empilhar("e");		
		System.out.println("Espiar:" + pilha.espiar());		
		Object conteudo = pilha.desempilhar();
		pilha.desempilhar();
		pilha.empilhar("Tecnologia");
		pilha.empilhar("da");
		pilha.empilhar("Bahia");	
		pilha.atualizar("São Paulo");	
		pilha.empilhar(pilha.desempilhar());
		pilha.empilhar(conteudo);
		System.out.println("Pilha="+pilha.imprimir());
	}
}