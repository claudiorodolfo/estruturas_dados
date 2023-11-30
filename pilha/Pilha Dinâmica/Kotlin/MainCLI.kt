import java.util.Scanner

fun main(args: Array<String>) {
    var pilha = PilhaDinamica(7)

    val scanner = Scanner(System.`in`)
    do {
        exibirMenu()
        print("Escolha uma opcao (0-4): ")
        val opcao = scanner.nextInt()
        when(opcao) {
            0 -> {
                print("Saindo da Pilha. ") 
                println("Ate mais!")
            }
            1 -> {
                print("Digite o valor: ")
                val valor = scanner.nextInt()
                pilha.empilhar(valor)
            }
            2 -> {
                print("Deseja desempilhar[s/n]? ")
                val confirmacao = scanner.next()
                if (confirmacao == "s" || 
                        confirmacao == "S")
                    pilha.desempilhar()                   
            }
            3 -> println("Topo: ${pilha.topo()}")
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