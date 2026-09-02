// Acessar o diretório dos fontes:
// cd "pilha/estatica/Java/Pilha Dupla"
// Compilar e enviar os .class para a pasta out:
// javac -d out br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out br.edu.ifba.vdc.bsi.ed.PilhaEstaticaDuplaPrincipal
package br.edu.ifba.vdc.bsi.ed;

public class PilhaEstaticaDuplaPrincipal {
	void main() {
		EmpilhavelDupla pilha = new PilhaEstaticaDupla(14);		
		pilha.empilhar1("Instituto");
		pilha.empilhar1("Federal");
		IO.println("Espiar:" + pilha.espiar1());	
		pilha.empilhar2("Universidade");
		pilha.empilhar2("Estadual");	
		pilha.empilhar1("de");
		pilha.empilhar1("Educação");
		IO.println("Espiar:" + pilha.espiar2());		
		pilha.empilhar2("do");		
		pilha.empilhar1("Ciência");
		pilha.empilhar2("Rio");
		pilha.empilhar2("Grande");		
		pilha.empilhar1("e");		
		IO.println("Espiar:" + pilha.espiar1());		
		Object conteudo = pilha.desempilhar1();
		pilha.desempilhar1();
		pilha.empilhar2("do");				
		pilha.empilhar1("Tecnologia");
		pilha.empilhar1("da");
		IO.println("Espiar:" + pilha.espiar2());		
		conteudo = pilha.desempilhar2();		
		pilha.empilhar1("Bahia");
		pilha.atualizar1("São Paulo");		
		pilha.empilhar1(pilha.desempilhar1());
		pilha.desempilhar2();
		pilha.empilhar2("Sul");
		pilha.atualizar2("Norte");
		pilha.empilhar1(conteudo);
		pilha.empilhar2(pilha.desempilhar2());
		pilha.empilhar2(conteudo);		
		IO.println("Pilha1="+pilha.imprimir1());
		IO.println("Pilha2="+pilha.imprimir2());			
	}
}