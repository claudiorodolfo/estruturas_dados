fun main() {
	//matricula como string para facilitar a iteração
	print("Digite o número de matrícula: ")
	val matricula = readLine() ?: ""
	//pode ser inteiro pois só é usado no cálculo
	print("Digite o número aleatório: ")
	val aleatorio = readLine()!!.toInt()
	
	// Calcula a soma dos dígitos da matricula
	var soma = 0
	//cada dígito da string é um caracter (Char)
	for (digito in matricula) {
		soma += digito.toString().toInt()
	}

	soma += aleatorio
	
	println("Questão 1: ${(soma*1)%6}")
	println("Questão 2: ${(soma*2)%20}")
	println("Questão 3: ${(soma*3)%20}")
}