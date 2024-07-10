//Pra rodar
//cd "estruturas_dados\arvore\"
//javac Prova.java
//java Prova
import java.util.Scanner;
import avl.*;
import abp.*;

public class Prova {
					
    public static void main(String args[]) {
		Prova prova = new Prova();
		ABP<Integer> arvore1 = new ABP<>();
		AVL<Integer> arvore2 = new AVL<>();
		int dados[][] = {
			{26, 90, 89, 96, 45, 20, 17, 50, 12, 13},
			{54, 13,  8, 87, 32, 21, 90, 76, 28, 53},
			{71, 79, 19, 28, 80, 33, 58, 48, 62, 31},
			{45, 67, 39, 35, 65, 74, 97, 56, 80, 41},
			{65,  9, 52, 59, 34, 83, 18, 24, 27, 01},
			{12, 25, 71, 50, 80, 26, 86, 20, 98, 32},
			{14, 82,  7, 53, 60, 80, 91, 56, 76, 58},
			{66, 60, 73, 29, 43, 28,  1, 51, 21, 75},
			{ 3, 71, 95, 49, 31, 12, 87, 48,  9, 96},
			{ 7, 61, 52, 92, 23,  2, 44, 29, 41, 11},
			{39, 35, 78, 12, 63, 24, 90, 27, 53,  1},
			{25, 91, 43, 44, 89, 63, 39, 32, 84, 78},
			{91, 32, 71, 57, 21, 39,  9, 50, 18, 23},
			{68, 53, 64, 72, 26, 12, 93, 34, 50, 56},
			{ 5, 11, 10, 24, 65,  4, 88, 62, 63, 15},
			{78, 95, 38,  3, 19, 99,  4, 23, 92, 10},
			{35, 92, 51, 18, 61, 28, 52, 93, 86, 20},
			{93, 52, 47, 31, 85,  9, 73, 83, 33, 55},
			{71, 80, 67, 73,  3, 91, 41, 35, 34, 43},
			{31, 17, 79, 76, 95, 97, 66, 73, 41, 39}
		};
		int q1, q2, q3, q4;
				
		String matricula;
		int deslocamento;
    
        Scanner scanner = new Scanner(System.in);
		System.out.println("Informe o numero de matricula:");
		matricula = scanner.next();
		System.out.println("Informe o deslocamento escolhido pelo aluno:");	
		deslocamento = scanner.nextInt();
		
		//Questão 1
		System.out.println("");		
		System.out.println("Questao 1");		
		q1 = prova.regra1(matricula, deslocamento);
		System.out.print(q1+":");					
		prova.imprimeDados(dados[q1]);
		prova.resolveQ1(dados[q1], arvore1);
		System.out.println("");
		//Questão 2
		System.out.println("");
		System.out.println("Questao 2");		
		q2 = prova.regra2(matricula, deslocamento);
		System.out.print(q2+":");			
		prova.imprimeDados(dados[q2]);
		prova.resolveQ2(dados[q2], arvore1);
		System.out.println("");		
		//Questão 3
		System.out.println("");			
		System.out.println("Questao 3");		
		q3 = prova.regra3(matricula, deslocamento);
		System.out.print(q3+":");			
		prova.imprimeDados(dados[q3]);
		prova.resolveQ3(dados[q3], arvore2);
		System.out.println("");
		//Questão 4
		System.out.println("");		
		System.out.println("Questao 4");
		q4 = prova.regra4(matricula, deslocamento);
		System.out.print(q4+":");				
		prova.imprimeDados(dados[q4]);
		prova.resolveQ4(dados[q4], arvore2);
		System.out.println("");				
    }
	
	//("soma dos números da matrícula" * 5 + deslocamento) % 20	
	public int regra1(String matricula, int deslocamento) {
		int soma = 0;
		for (char letra : matricula.toCharArray()) {
			int numero = Integer.parseInt(letra + "");			
			soma += numero;
		}
		return (soma * 5 + deslocamento) % 20;
	}
	
	//"multiplicação dos números da matrícula maiores que 2" * 7 + deslocamento) % 20 	
	private int regra2(String matricula, int deslocamento) {
		int produto = 1;
		for (char letra : matricula.toCharArray()) {
			int numero = Integer.parseInt(letra + "");
			if (numero > 2) 
				produto *= numero;
		}
		return (produto * 7 + deslocamento) % 20;
	}

	//("soma dos últimos 3 dígitos da matrícula" * 11 + deslocamento) % 20 
	private int regra3(String matricula, int deslocamento) {
		int soma = 0;
		for (int i = 0; i < matricula.length(); i++) {
			if (i >= matricula.length() - 3) {
				int numero = Integer.parseInt(matricula.charAt(i) + "");
				soma += numero;
			}
		}
		return (soma * 11 + deslocamento) % 20;
	}

	//("multiplicação dos números da matrícula diferentes de zero" * 2 + deslocamento) % 20	
	private int regra4(String matricula, int deslocamento) {
		int produto = 1;
		for (char letra : matricula.toCharArray()) {
			int numero = Integer.parseInt(letra + "");
			if (numero != 0)
				produto *= numero;
		}
		return (produto * 2 + deslocamento) % 20;
	}	
	
	private void imprimeDados(int dados[]) {
		for (int valor : dados)
			System.out.print(valor + " ");
		System.out.println("");
	}
	
	private void resolveQ1(int dados[], ABP<Integer> arvore1) {
		arvore1.limpar();
		for (int dado : dados) {
			arvore1.inserir(dado);
		}
		System.out.println(arvore1.imprimirPosOrdem());
	}

	private void resolveQ2(int dados[], ABP<Integer> arvore1) {
		arvore1.limpar();
		for (int dado : dados) {
			arvore1.inserir(dado);
		}
		String resultado = arvore1.imprimirPosOrdem();
		//remove os colchetes no inicio e fim
		resultado = resultado.substring(1,resultado.length()-1);
		//explode a string pela virgula
		String[] valores = resultado.split(",");
		//apaga o terceiro
		arvore1.apagar(Integer.parseInt(valores[2]));
		//apaga o sétimo
		arvore1.apagar(Integer.parseInt(valores[6]));

		resultado = arvore1.imprimirPreOrdem();
		//remove os colchetes no inicio e fim
		resultado = resultado.substring(1,resultado.length()-1);		
		//explode a string pela virgula
		valores = resultado.split(",");
		System.out.println("Quinto elemento: "+ valores[4]);	
	}

	private void resolveQ3(int dados[], AVL<Integer> arvore2) {
		arvore2.limpar();
		for (int dado : dados) {
			arvore2.inserir(dado);
		}
		System.out.println("Raiz: "+arvore2.getRaiz().getDado());
	}
	
	private void resolveQ4(int dados[], AVL<Integer> arvore2) {
		arvore2.limpar();
		for (int dado : dados) {
			arvore2.inserir(dado);
		}
		String resultado = arvore2.imprimirPreOrdem();
		//remove os colchetes no inicio e fim
		resultado = resultado.substring(1,resultado.length()-1);
		//explode a string pela virgula
		String[] valores = resultado.split(",");
		System.out.println("Sexto elemento: "+valores[5]);
	}
}