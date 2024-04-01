fun main() {
    var pilha = PilhaDinamica(7)
    do {
        exibirMenu()
        print("Escolha uma opcao (0-4): ")
        val opcao = readLine()?.toIntOrNull()
        
        when(opcao) {
            0 -> {
                print("Saindo da Pilha. ") 
                println("Ate mais!")
            }
            1 -> {
                print("Digite o valor: ")
                val valor = readLine()?.toIntOrNull()
                pilha.empilhar(valor)
            }
            2 -> {
                print("Deseja desempilhar[s/n]? ")
                val confirmacao= readLine()
                if (confirmacao == "s" || 
                        confirmacao == "S")
                    pilha.desempilhar()                   
            }
            3 -> println("Topo: ${pilha.espiar()}")
            4 -> println(pilha.imprimir())
            else -> {
                print("Opcao inválida. ")
                println("Tente novamente.")
            }
        }
    } while (opcao != 0) 
}

fun exibirMenu() {
    println("=== PILHA DINÂMICA CLI ===")
    println("0. SAIR")
    println("1. Empilhar")
    println("2. Desempilhar")
    println("3. Topo")
    println("4. Imprimir")               
}