data class Paciente(val nome: String, val idade: Int, val prioridade: Int) {

    fun imprimir() String {
        return "Paciente(nome=$nome, idade=$idade, prioridade=$prioridade)"
    }    
}