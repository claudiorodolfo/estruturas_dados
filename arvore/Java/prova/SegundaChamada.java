package prova;
//Pra rodar
//cd "estruturas_dados\arvore\"
//javac SegundaChamada.java
//java SegundaChamada
import avl.*;
import abp.*;

public class SegundaChamada {
					
    public static void main(String args[]) {
		SegundaChamada prova = new SegundaChamada();
		ABP<Integer> arvore1 = new ABP<>();
		AVL<Integer> arvore2 = new AVL<>();
        int dados1[] = {97,89,23,76,91,43,88,68,26,56};
        int dados2[] = {50,42,98,82,23,34,81,28,15,17};
		
		//Questão 1
		System.out.println("");		
		System.out.println("Questao 1");							
		prova.resolveQ1(dados1, arvore1);
		System.out.println("");
		//Questão 2
		System.out.println("");
		System.out.println("Questao 2");		
		prova.resolveQ2(dados2, arvore2);
		System.out.println("");		

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

		System.out.println("Pos-Ordem: "+ arvore1.imprimirPosOrdem());	
	}
	
	private void resolveQ2(int dados[], AVL<Integer> arvore2) {
		arvore2.limpar();
		for (int dado : dados) {
			arvore2.inserir(dado);
		}
		System.out.println("Pos-Ordem:"+arvore2.imprimirPosOrdem());
	}
}