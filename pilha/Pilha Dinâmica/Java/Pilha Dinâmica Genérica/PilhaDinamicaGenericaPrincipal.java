public class PilhaDinamicaGenericaPrincipal {
	public static void main(String args[]) {
		Empilhavel<String> pilha = new PilhaDinamicaGenerica<>(5);
		pilha.empilhar("Instituto");
		pilha.empilhar("Federal");
		System.out.println("Espiar:" + pilha.topo());		//Federal	
		pilha.empilhar("de");
		pilha.empilhar("Educação");
		pilha.empilhar("Ciência");
		pilha.empilhar("e");	//Pilha Cheia!	
		System.out.println("Espiar:" + pilha.topo());	//Ciência	
		String conteudo = pilha.desempilhar();
		pilha.desempilhar();
		pilha.empilhar("Tecnologia");
		pilha.empilhar("da");
		pilha.empilhar("Bahia");	//Pilha Cheia!	
		pilha.empilhar(pilha.desempilhar());
		pilha.empilhar(conteudo);	//Pilha Cheia!	
		System.out.println("Pilha="+pilha.imprimir());	//[da,Tecnologia,de,Federal,Instituto]
	}
}