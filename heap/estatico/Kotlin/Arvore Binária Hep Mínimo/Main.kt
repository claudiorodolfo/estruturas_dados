fun main() {
	var heap: Amontoavel = ArvoreBinariaHeapMinimo(10)
	heap.inserir(4)
  	heap.inserir(8)
  	heap.inserir(2)
	heap.inserir(7)
	heap.atualizar(6)	
	heap.inserir(1)
	println(heap.imprimir())//[1,4,6,8,7]
	println(heap.extrair())	//1
	println(heap.obter())	//4
	println(heap.obter())	//4
	println(heap.imprimir())//[4,7,6,8]
}