fun main() {
    var lista: Listavel = ListaDinamica()
    do {
        exibirMenu()
        print("Escolha uma opcao (0-7): ")
        val opcao = readLine()?.toIntOrNull()
        
        when(opcao) {
            0 -> {
                print("Saindo da Lista. ") 
                println("Ate mais!")
            }
			1 -> {
                print("Digite a posição: ")
                val posicao = readLine()?.toIntOrNull()			
                print("Digite o valor: ")
                val valor = readLine()?.toIntOrNull()
                lista.anexar(posicao, valor)			
			}
            2 -> {
                print("Digite o valor: ")
                val valor = readLine()?.toIntOrNull()
                lista.anexar(valor)
            }
            3 -> {
                print("Digite a posição: ")
                val posicao = readLine()?.toIntOrNull()				
                print("Deseja apagar[s/n]? ")
                val confirmacao= readLine()
                if (confirmacao == "s" || 
                        confirmacao == "S")
                    lista.apagar(posicao)                   
            }
            4 -> {}
            5 -> {}		
            6 -> {
                print("Digite o valor: ")
                val valor = readLine()?.toIntOrNull()
                lista.atualizar(valor)
            }
            7 -> println(lista.imprimir())
            else -> {
                print("Opcao inválida. ")
                println("Tente novamente.")
            }
        }
    } while (opcao != 0) 
}

fun exibirMenu() {
    println("=== LISTA DINÂMICA CLI ===")
    println("0. SAIR")
    println("1. Inserir")
    println("2. Anexar")	
    println("3. Apagar")
    println("4. Selecionar Um")
	println("5. Selecionar Todos")
    println("6. Atualizar")
    println("7. Imprimir")               
}