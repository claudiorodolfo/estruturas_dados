//QUESTÃO 1
fun occurrences(data: Any?): Array<Int> {
	// Primeira passagem para contar o número de ocorrências
	var qtdeOcorrencias = 0
	for (i in 0 until quantidade) {
		val aux = dados[(posicaoInicial+i)%dados.size]
		if (aux == data) 
			qtdeOcorrencias++
	}
	// Cria um array fixo para armazenar as posições das ocorrências
	val arrayPosicoes = IntArray(qtdeOcorrencias) {0}
	var posicao = 0
	// Segunda passagem para armazenar as posições das ocorrências
	for (i in 0 until quantidade) {
		val aux = dados[(posicaoInicial+i)%dados.size]
		if (aux == data) {
			arrayPosicoes[posicao] = i
			posicao++
		}
	}
	return arrayPosicoes
}

//Questão 2
fun decToHex(data: String): String {
	var dividendo = data.toInt()
	//coloca os restos da divisões sucessivas por 16, numa pilha
	while (dividendo != 0) {
		val resto = dividendo % 16
		val quociente = dividendo / 16
		dividendo = quociente
		empilhar(resto)
	}
	
	var resultado = ""
	val DigHexadecimal = "0123456789ABCDEF"
	//em vez de retornar o valor em decimal, retorna a letra correspondente
	while(!estaVazia()) {
		resultado += DigHexadecimal[desempilhar()]
	}
	return resultado
}

//Questão 3
fun reverse(): Array<Any?> {
	val arrayInvertido: Array<Any?> = ArrayOfNulls(quantidade)
	for (i in 0 until quantidade) {
		val dado = dados[(ponteiroInicio+i)%dados.size]
		arrayInvertido[quantidade-1-i] = dado
	}
	return arrayInvertido
}