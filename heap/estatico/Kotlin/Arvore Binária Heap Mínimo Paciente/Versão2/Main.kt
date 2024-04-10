enum class Prioridade { ALTA, MEDIA, BAIXA }

var contadores = arrayOf(0, 0, 0) //todos os contadores

fun zerosEsquerda(valor: String): String {
    val zeros = "0".repeat(13 - valor.length)
    return zeros + valor
}

fun novaPrioridade(prioridade: Prioridade) : Long {
    var contador = contadores[prioridade.ordinal] //contador específico
    contador = contador.inc()
    contadores[prioridade.ordinal] = contador   //incremento do contador específico
    var contadorString = contador.toString()
    contadorString = zerosEsquerda(contadorString)
    val posicao = prioridade.ordinal //0, 1 ou 2
    var novaPrioridade = posicao.toString() + contadorString
    return novaPrioridade.toLong()
}

fun main() {

    var heapPacientes: HeapingPaciente = HeapMinimoPaciente(10)
    heapPacientes.inserir(Paciente("João", 30, novaPrioridade(Prioridade.ALTA)))
    heapPacientes.inserir(Paciente("Maria", 25, novaPrioridade(Prioridade.MEDIA)))
    heapPacientes.inserir(Paciente("Pedro", 40, novaPrioridade(Prioridade.BAIXA)))
    heapPacientes.inserir(Paciente("Ana", 35, novaPrioridade(Prioridade.MEDIA)))
    println("Todos os Pacientes: ${heapPacientes.imprimir()}")

    var pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: João 
    pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: Maria
    pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: Ana
    pacienteAtendido = heapPacientes.extrair()
    println("Paciente atendido: $pacienteAtendido")  //Saída: Pedro   
}