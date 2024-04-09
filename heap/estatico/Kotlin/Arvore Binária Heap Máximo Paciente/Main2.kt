fun main() {
    var heapPacientes: HeapifiablePaciente = HeapMaximoPaciente(10)
    heapPacientes.inserir(Paciente("João", 30, 3))
    heapPacientes.inserir(Paciente("Maria", 25, 2))
    heapPacientes.inserir(Paciente("Pedro", 40, 1))
    heapPacientes.inserir(Paciente("Ana", 35, 2))

    var pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: João  
    pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: Ana
    pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: Maria
    pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: Pedro
    
}
