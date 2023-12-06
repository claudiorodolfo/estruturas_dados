import java.util.Scanner;

public class Prova {

    public static void main(String args[]) {
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
		System.out.println("Questao 1");		
		q1 = regra1(matricula, deslocamento);
		System.out.print(q1+":");					
		imprimeDados(dados[q1]);
		resolveQ1(dados[q1]);

		//Questão 2
		System.out.println("Questao 2");		
		q2 = regra2(matricula, deslocamento);
		System.out.print(q2+":");			
		imprimeDados(dados[q2]);
		resolveQ2(dados[q2]);
		
		//Questão 3	
		System.out.println("Questao 3");		
		q3 = regra3(matricula, deslocamento);
		System.out.print(q3+":");			
		imprimeDados(dados[q3]);
		resolveQ3(dados[q3]);

		//Questão 4
		System.out.println("Questao 4");
		q4 = regra4(matricula, deslocamento);
		System.out.print(q4+":");				
		imprimeDados(dados[q4]);
		resolveQ4(dados[q4]);		
    }
	
	//("soma dos números da matrícula" * 5 + deslocamento) % 20	
	public static int regra1(String matricula, int deslocamento) {
		int soma = 0;
		for (char letra : matricula.toCharArray()) {
			int numero = Integer.parseInt(letra + "");			
			soma += numero;
		}
		return (soma * 5 + deslocamento) % 20;
	}
	
	//"multiplicação dos números da matrícula maiores que 2" * 7 + deslocamento) % 20 	
	private static int regra2(String matricula, int deslocamento) {
		int produto = 1;
		for (char letra : matricula.toCharArray()) {
			int numero = Integer.parseInt(letra + "");
			if (numero > 2) 
				produto *= numero;
		}
		return (produto * 7 + deslocamento) % 20;
	}

	//("soma dos últimos 3 dígitos da matrícula" * 11 + deslocamento) % 20 
	private static int regra3(String matricula, int deslocamento) {
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
	private static int regra4(String matricula, int deslocamento) {
		int produto = 1;
		for (char letra : matricula.toCharArray()) {
			int numero = Integer.parseInt(letra + "");
			if (numero != 0)
				produto *= numero;
		}
		return (produto * 2 + deslocamento) % 20;
	}	
	
	private static void imprimeDados(int dados[]) {
		for (int valor : dados)
			System.out.print(valor + " ");
		System.out.println("");
	}
	
	private static void resolveQ1(int dados[]) {
		for (int dado : dados) {
	//		abp.inserir(dado);
		}
	//	System.out.println(abp.imprimirPosOrdem());
	}

	private static void resolveQ2(int dados[]) {
		for (int dado : dados) {
	//		abp.inserir(dado);
		}
	//String resultado = abp.imprimirPosOrdem();
		//quebrar string
		//int valor1 = obtem o terceiro elemento
		//int valor2 = obtem o setimo elemento
		//abp.apagar(valor1);
		//abp.apagar(valor2);
		//String resultado = abp.imprimirPreOrdem();
		//quebrar string
		//imprimir só o quinto elemento;	
	}

	private static void resolveQ3(int dados[]) {
		for (int dado : dados) {
	//		avl.inserir(dado);
		}
	//	System.out.println(avl.getRaiz());
	}
	
	private static void resolveQ4(int dados[]) {
		for (int dado : dados) {
		//	avl.inserir(dado);
		}
		//String resultado = avl.imprimirPreOrdem();
		//quebrar string
		//imprimir só o sexto elemento;
	}
}