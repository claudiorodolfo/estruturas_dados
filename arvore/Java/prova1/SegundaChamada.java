// Acessar o diretório dos fontes:
// cd arvore/Java/prova1
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out prova.SegundaChamada
package prova;

public class SegundaChamada {
					
    void main() {
		SegundaChamada prova = new SegundaChamada();
		ABP<Integer> arvore1 = new ABP<>();
		AVL<Integer> arvore2 = new AVL<>();
        int dados1[] = {97,89,23,76,91,43,88,68,26,56};
        int dados2[] = {50,42,98,82,23,34,81,28,15,17};
		
		//Questão 1
		IO.println("");		
		IO.println("Questao 1");							
		prova.resolveQ1(dados1, arvore1);
		IO.println("");
		//Questão 2
		IO.println("");
		IO.println("Questao 2");		
		prova.resolveQ2(dados2, arvore2);
		IO.println("");		

    }	

	private void resolveQ1(int dados[], ABP<Integer> arvore1) {
		arvore1.limpar();
		for (int dado : dados) {
			arvore1.inserir(dado);
		}
		String resultado = arvore1.imprimirPreOrdem();
		//remove os colchetes no inicio e fim
		resultado = resultado.substring(1,resultado.length()-1);
		//explode a string pela virgula
		String[] valores = resultado.split(",");
		//apaga o segundo
		arvore1.apagar(Integer.parseInt(valores[1]));
		//apaga o sexto
		arvore1.apagar(Integer.parseInt(valores[5]));

		IO.println("Pos-Ordem: "+ arvore1.imprimirPosOrdem());	
	}
	
	private void resolveQ2(int dados[], AVL<Integer> arvore2) {
		arvore2.limpar();
		for (int dado : dados) {
			arvore2.inserir(dado);
		}
		IO.println("Pos-Ordem:"+arvore2.imprimirPosOrdem());
	}
}