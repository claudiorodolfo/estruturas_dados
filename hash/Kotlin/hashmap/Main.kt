fun main() {
	//elementos que serão introduzidos na ED
	c1 = Cliente("Claudio Rodolfo", 30)
	c2 = Cliente("Maria Isabel", 12)
	c3 = Cliente("Antonio Carlos", 23)
	c4 = Cliente("João Pedro", 21)
	c5 = Cliente("Maria Eduarda", 53)
	c6 = Cliente("Carlos Eduardo", 56)
		
	//popula a Estrutura MapaEspalhamento
	hashmap: Espalhavel = MapaEspalhamento(26)
	hashmap.adicionar(new Mapa("clro", c1))
	hashmap.adicionar(new Mapa("maria", c2))
	hashmap.adicionar(new Mapa("toin", c3))
	hashmap.adicionar(new Mapa("jao", c4))
	hashmap.adicionar(new Mapa("carlos", c5))
		
	//chave para testes
	chave = "clro";
	clienteTemp = (Cliente) hashmap.buscar(chave)
	println("a idade de $chave é: ${clienteTemp.getIdade()}")
		
	//verifica se há elemento com a chave maria
	chave = "maria"
	if (hashmap.contemChave(chave))
		println("contem a chave $chave")
	else
		println("nao contem a chave $chave")

	//verifica se há elemento com a chave jorge
	chave = "jorge"
	if (hashmap.contemChave(chave))
		println("contem a chave $chave")
	else
		println("nao contem a chave $chave")
		
	//remove o elemento referenciado pela chave maria
	hashmap.remover("maria")
	chave = "maria"
	if (hashmap.contemChave(chave))
		println("contem a chave $chave")
	else
		println("não contem a chave $chave")		
	}
}