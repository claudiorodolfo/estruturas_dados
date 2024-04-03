fun main() {
  var heap: Amontoavel = ArvoreBinariaHeapMaximo(10)

	heap.inserir(4)
  	heap.inserir(8)
  	heap.inserir(2)
	heap.inserir(7)
	heap.inserir(1)
	println(heap.imprimir())//[8,7,2,4,1]
	println(heap.extrair())	//8
	println(heap.obter())	//7
	println(heap.obter())	//7
	println(heap.imprimir()) //[7,4,2,1]
}