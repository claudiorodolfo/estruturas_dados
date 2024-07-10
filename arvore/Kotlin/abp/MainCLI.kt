fun main() {
    //Testar entrada nesta ordem: 4 2 6 1 3 5 7
    //Pre Ordem: [4,2,1,3,6,5,7] 
    //Em Ordem:  [1,2,3,4,5,6,7]
    //Pos Ordem: [1,3,2,5,7,6,4]
    var arvore: Arborizavel = ABP()
    do {
        exibirMenu()
        val opcao = readLine()!!.toInt()
        when(opcao) {
            0 -> println("Saindo da ABP. Ate mais!")
            1 -> {
                print("Digite o valor : ")
                val valor = readLine()
                arvore.inserir(valor)
            }
            2 -> {
                print("Digite o valor : ")
                val valor = readLine()             
                arvore.apagar(valor)
            }
            3 -> {
                print("Digite o valor : ")
                val valor = readLine()
                println(arvore.existe(valor))
            }
            4 -> {
                exibirMenuImprimir()
                val opcaoImprimir = readLine()!!.toInt()
                when(opcaoImprimir) {
                    1 -> println("\nPre-Ordem: ${arvore.imprimirPreOrdem()}\n")
                    2 -> println("\nEm-Ordem: ${arvore.imprimirEmOrdem()}\n")                        
                    3 -> println("\nPos-Ordem: ${arvore.imprimirPosOrdem()}\n");
                    else -> println("Opcao invalida.");
                }
            }
            5 -> {                    
                arvore.limpar()
                println("Arvore Limpa!")
            }
            else -> {
                print("Opcao inválida. ")
                println("Tente novamente.")
            }
        }
    } while (opcao != 0)
}

fun exibirMenu() {
    println("=== Arvore Binaria de Pesquisa ===")
    println("0. SAIR")
    println("1. Inserir")
    println("2. Apagar")
    println("3. Existe?")      
    println("4. Imprimir")
    println("5. Limpar")    
    print("Escolha uma opcao (0-5): ")
}   

fun exibirMenuImprimir() {
    println("1. Imprimir Pre-Ordem")
    println("2. Imprimir Em-Ordem")
    println("3. Imprimir Pos-Ordem")               
    print("Escolha uma opcao (1-3): ")
}