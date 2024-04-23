//Universally Unique Identifier (UUID) 
//é uma sequencia de 128 bits usada para
//identificação em sistemas computacionais
import java.util.UUID

fun main() {
	//elementos que serão introduzidos na ED
	val c1 = Cliente("Claudio Rodolfo", 30)
	val c2 = Cliente("Maria Isabel", 12)
	val c3 = Cliente("Antonio Carlos", 23)
	val c4 = Cliente("João Pedro", 21)
	val c5 = Cliente("Maria Eduarda", 53)
	val c6 = Cliente("Carlos Eduardo", 56)
		
	//popula a Estrutura MapaEspalhamento
	var hashmap: Espalhavel = MapaEspalhamento(2)

	val k1 = UUID.randomUUID()
	hashmap.adicionar(Mapa(k1, c1))

	val k2 = UUID.randomUUID()
	hashmap.adicionar(Mapa(k2, c2))

	val k3 = UUID.randomUUID()
	hashmap.adicionar(Mapa(k3, c3))

	val k4 = UUID.randomUUID()
	hashmap.adicionar(Mapa(k4, c4))

	val k5 = UUID.randomUUID()
	hashmap.adicionar(Mapa(k5, c5))

	val k6 = UUID.randomUUID()
	hashmap.adicionar(Mapa(k6, c6))
	
	//chave para testes
	var chave = k1;	//Cliente("Claudio Rodolfo", 30)
	val clienteTemp = hashmap.buscar(chave) as Cliente
	println("a idade de $chave é: ${clienteTemp.idade}")
		
	//verifica se há elemento com a chave k2
	chave = k2	//Cliente("Maria Isabel", 12)
	if (hashmap.contemChave(chave))
		println("contem a chave $chave,\nreferenciando o objeto ${hashmap.buscar(chave)}")
	else
		println("nao contem a chave $chave")

	//verifica se há elemento com uma chave qualquer
	chave = UUID.randomUUID()	//Cliente não existente
	if (hashmap.contemChave(chave))
		println("contem a chave $chave,\nreferenciando o objeto ${hashmap.buscar(chave)}")
	else
		println("nao contem a chave $chave")
		
	//remove o elemento referenciado pela chave k2
	hashmap.remover(k2)

	//verifica se há elemento com a chave k2
	chave = k2	//Cliente("Maria Isabel", 12)
	if (hashmap.contemChave(chave))
		println("contem a chave $chave,\nreferenciando o objeto ${hashmap.buscar(chave)}")
	else
		println("não contem a chave $chave")	

}