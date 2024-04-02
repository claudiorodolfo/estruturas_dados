fun main() {
  var heap: Amontoavel = ArvoreBinariaHeapMinimo(10)

	heap.inserir(4)
	heap.inserir(8)
	heap.inserir(2)
	heap.inserir(7)
	heap.inserir(1)
	println(heap.imprimir())	//[1,2,4,7,8]
	println(heap.extrair())	//1
	println(heap.obter())	//2
	println(heap.obter())	//2
	println(heap.imprimir()) //[2,7,4,8]
}