enum class Prioridade { BAIXA, MEDIA, ALTA }

var contadores = arrayOf(10000000000000L, 10000000000000L, 10000000000000L) //todos os contadores

fun zerosEsquerda(valor: String): String {
    val zeros = "0".repeat(13 - valor.length)
    return zeros + valor
}

fun novaPrioridade(prioridade: Prioridade) : Long {
    val posicao = prioridade.ordinal //0, 1 ou 2
    contadores[posicao] = contadores[posicao] - 1
    var contadorString = contadores[posicao].toString()
    val novaPrioridade = posicao.toString() + zerosEsquerda(contadorString)
    return novaPrioridade.toLong()
    //Todo este código pode ser reduzido a estas 2 linhas, mas perdemos em legibilidade
    //contadores[prioridade.ordinal] = contadores[prioridade.ordinal] - 1
    //return ((prioridade.ordinal).toString() + zerosEsquerda(contadores[prioridade.ordinal].toString())).toLong()
}

fun main() {
    var heapPacientes: HeapingPaciente = HeapMaximoPaciente(10)
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