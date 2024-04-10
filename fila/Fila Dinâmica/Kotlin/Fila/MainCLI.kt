fun main() {
    var fila: Enfileiravel = FilaDinamica(7)
    do {
        exibirMenu()
        print("Escolha uma opcao (0-5): ")
        val opcao = readLine()?.toIntOrNull()
        
        when(opcao) {
            0 -> {
                print("Saindo da Fila. ") 
                println("Ate mais!")
            }
            1 -> {
                print("Digite o valor: ")
                val valor = readLine()?.toIntOrNull()
                fila.enfileirar(valor)
            }
            2 -> {
                print("Deseja desenfileirar[s/n]? ")
                val confirmacao= readLine()
                if (confirmacao == "s" || 
                        confirmacao == "S")
                    fila.desenfileirar()                   
            }
            3 -> println("Frente: ${fila.frente()}")
            4 -> {
                print("Digite o valor: ")
                val valor = readLine()?.toIntOrNull()
                fila.atualizar(valor)
            }
            5 -> println(fila.imprimir())
            else -> {
                print("Opcao inválida. ")
                println("Tente novamente.")
            }
        }
    } while (opcao != 0) 
}

fun exibirMenu() {
    println("=== FILA DINÂMICA CLI ===")
    println("0. SAIR")
    println("1. Enfileirar")
    println("2. Desenfileirar")
    println("3. Frente")
    println("4. Atualizar")
    println("5. Imprimir")               
}