fun main() {
	//elementos que serão introduzidos na ED
	val c1 = Cliente("Claudio Rodolfo", 30)
	val c2 = Cliente("Maria Isabel", 12)
	val c3 = Cliente("Antonio Carlos", 23)
	val c4 = Cliente("João Pedro", 21)
	val c5 = Cliente("Maria Eduarda", 53)
	val c6 = Cliente("Carlos Eduardo", 56)
		
	//popula a Estrutura MapaEspalhamento
	var hashmap: Espalhavel = MapaEspalhamento(26)
	hashmap.adicionar(Mapa("clro", c1))
	hashmap.adicionar(Mapa("maria", c2))
	hashmap.adicionar(Mapa("toin", c3))
	hashmap.adicionar(Mapa("jao", c4))
	hashmap.adicionar(Mapa("madu", c5))
	hashmap.adicionar(Mapa("carlos", c6))
	
	//chave para testes
	var chave = "clro";	//Cliente("Claudio Rodolfo", 30)
	val clienteTemp = hashmap.buscar(chave) as Cliente
	println("a idade de $chave é: ${clienteTemp.idade}")
		
	//verifica se há elemento com a chave maria
	chave = "maria"	//Cliente("Maria Isabel", 12)
	if (hashmap.contemChave(chave))
		println("contem a chave $chave,\nreferenciando o objeto ${hashmap.buscar(chave)}")
	else
		println("nao contem a chave $chave")

	//verifica se há elemento com a chave jorge
	chave = "jorge"	//Cliente não existente
	if (hashmap.contemChave(chave))
		println("contem a chave $chave,\nreferenciando o objeto ${hashmap.buscar(chave)}")
	else
		println("nao contem a chave $chave")
		
	//remove o elemento referenciado pela chave maria
	hashmap.remover("maria")

	//verifica se há elemento com a chave maria
	chave = "maria"	//Cliente("Maria Isabel", 12)
	if (hashmap.contemChave(chave))
		println("contem a chave $chave,\nreferenciando o objeto ${hashmap.buscar(chave)}")
	else
		println("não contem a chave $chave")
	
}