fun main() {
	val heapPacientes = MaxHeapPacientes()
    heapPacientes.inserir(Paciente("João", 30, 3))
    heapPacientes.inserir(Paciente("Maria", 25, 1))
    heapPacientes.inserir(Paciente("Pedro", 40, 2))
    heapPacientes.inserir(Paciente("Ana", 35, 4))
    heapPacientes.imprimirHeap() // Saída: [Paciente(nome=Ana, idade=35, prioridade=4), Paciente(nome=Maria, idade=25, prioridade=1), Paciente(nome=Pedro, idade=40, prioridade=2), Paciente(nome=João, idade=30, prioridade=3)]

    val pacienteAtendido = heapPacientes.atenderPaciente()
    println("Paciente atendido: $pacienteAtendido") // Saída: Paciente atendido: Paciente(nome=Ana, idade=35, prioridade=4)
    heapPacientes.imprimirHeap() // Saída: [Paciente(nome=João, idade=30, prioridade=3), Paciente(nome=Maria, idade=25, prioridade=1), Paciente(nome=Pedro, idade=40, prioridade=2)]


	

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