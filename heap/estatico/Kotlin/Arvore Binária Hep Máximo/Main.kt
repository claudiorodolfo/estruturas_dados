fun main() {
  var heap: Amontoavel = ArvoreBinariaHeapMaximo(10)
	heap.inserir(4)
  	heap.inserir(8)
  	heap.inserir(2)
	heap.inserir(7)
	heap.atualizar(6)
	heap.inserir(1)
	println(heap.imprimir())//[7,6,2,4,1]
	println(heap.extrair())	//7
	println(heap.obter())	//6
	println(heap.obter())	//6
	println(heap.imprimir())//[6,4,2,1]
}