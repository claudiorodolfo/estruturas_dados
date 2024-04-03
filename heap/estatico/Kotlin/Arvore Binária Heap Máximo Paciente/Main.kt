fun main() {
	var heapPacientes: HeapifiablePaciente = HeapMaximoPaciente(10)
    heapPacientes.inserir(Paciente("João", 30, 3))
    heapPacientes.inserir(Paciente("Maria", 25, 1))
    heapPacientes.inserir(Paciente("Pedro", 40, 2))
    heapPacientes.inserir(Paciente("Ana", 35, 4))
	println(heapPacientes.imprimir()) // Saída: [Paciente(nome=Ana, idade=35, prioridade=4), Paciente(nome=Maria, idade=25, prioridade=1), Paciente(nome=Pedro, idade=40, prioridade=2), Paciente(nome=João, idade=30, prioridade=3)]
	val pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido") // Saída: Paciente atendido: Paciente(nome=Ana, idade=35, prioridade=4)
    println(heapPacientes.imprimir()) // Saída: [Paciente(nome=João, idade=30, prioridade=3), Paciente(nome=Maria, idade=25, prioridade=1), Paciente(nome=Pedro, idade=40, prioridade=2)]
}