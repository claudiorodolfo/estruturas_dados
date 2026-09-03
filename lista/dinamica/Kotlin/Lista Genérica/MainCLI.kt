fun main() {
    var lista: Listavel<String> = ListaDinamica()
    do {
        exibirMenu()
        print("Escolha uma opcao (0-7): ")
        val opcao = readln().toInt()
        
        when(opcao) {
            0 -> {
                print("Saindo da Lista. ") 
                println("Ate mais!")
            }
			1 -> {
                print("Digite a posição: ")
                val posicao = readln().toInt()
                print("Digite o valor: ")
                val valor = readlnOrNull()
                lista.inserir(posicao, valor)			
			}
            2 -> {
                print("Digite o valor: ")
                val valor = readlnOrNull()
                lista.anexar(valor)
            }
            3 -> {
                print("Digite a posição: ")
                val posicao = readln().toInt()
                print("Deseja apagar[s/n]? ")
                val confirmacao= readlnOrNull() ?: "n"
                if (confirmacao == "s" || 
                        confirmacao == "S")
                    lista.apagar(posicao)                   
            }
            4 -> {
                print("Digite a posição: ")
                val posicao = readln().toInt()
                lista.selecionar(posicao) 
            }
            5 -> lista.selecionarTodos()
            6 -> {
                print("Digite a posicao: ")
                val posicao = readln().toInt()
                print("Digite o valor: ")
                val valor = readlnOrNull()
                lista.atualizar(posicao, valor)
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