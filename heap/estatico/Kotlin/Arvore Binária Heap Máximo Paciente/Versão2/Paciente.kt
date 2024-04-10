data class Paciente(val nome: String, val idade: Int, val prioridade: Long) {

    override fun toString(): String {
        var resultado = "\n{\n"
        resultado += "\t\"nome\": \"$nome\",\n" 
        resultado += "\t\"idade\": \"$idade\",\n"
        resultado += "\t\"prioridade\": \"${prioridadeOriginal()}\"\n"
        resultado += "\t\"prioridadeCalculada\": \"$prioridade\"\n"
        return "$resultado}"
    }

    private fun zerosEsquerda(valor: String): String {
        val zeros = "0".repeat(14 - valor.length)
        return zeros + valor
    }

    private fun prioridadeOriginal():Int {
        val prioridadeString = zerosEsquerda(prioridade.toString())
        val tamanhoString = prioridadeString.length
        val digitosIniciais = prioridadeString.substring(0, tamanhoString-14)
        return digitosIniciais.toIntOrNull() ?: 0
    }
}