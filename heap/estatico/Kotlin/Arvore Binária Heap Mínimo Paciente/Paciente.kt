data class Paciente(val nome: String, val idade: Int, val prioridade: Long) {

    override fun toString(): String {
        var resultado = "\n{\n"
        resultado += "\t\"nome\": \"$nome\",\n" 
        resultado += "\t\"idade\": \"$idade\",\n"
        resultado += "\t\"prioridade\": \"${prioridadeOriginal()}\"\n"
        resultado += "\t\"prioridadeCalculada\": \"$prioridade\""
        return "$resultado\n}"
    }

    private fun prioridadeOriginal():Int {
        val prioridadeString = prioridade.toString()
        val tamanhoString = prioridadeString.length
        val digitosIniciais = prioridadeString.substring(0, tamanhoString-14)
        return digitosIniciais.toIntOrNull() ?: 0
    }
}