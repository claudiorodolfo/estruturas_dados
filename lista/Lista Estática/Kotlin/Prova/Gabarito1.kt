//Questão 00
interface GestaoContatos {
    // Adiciona um novo contato à lista de contatos.
    fun adicionarContato(nome: String, numero: String): Contato

    // Remove um contato existente da lista de contatos com base no nome.
    fun removerContato(nome: String): Contato?

    // Verifica se um contato com o nome especificado está na lista de contatos.
    fun verificarContato(nome: String): Boolean

    // Obtém o número de telefone de um contato com base no nome.
    fun obterNumeroContato(nome: String): String?

    // Lista todos os contatos na lista de contatos.
    fun listarContatos(): Array<Contato?>

	// Atualiza o número de telefone de um contato com base no nome.
    fun atualizarNumeroContato(c: Contato)

    // Obtém a quantidade total de contatos na lista de contatos.
    fun contarContatos(): Int

    // Limpa a lista de contatos, removendo todos os contatos.
    fun limparContatos(): Array<Contato?>

    // Verifica se a lista de contatos está vazia.
    fun listaVazia(): Boolean	
}

//Questão 01
interface CarrinhoDeCompras {
    // Adiciona um produto ao carrinho.
    fun adicionarProduto(nome: String, quantidade: Int, precoUnitario: Double): Produto

    // Remove um produto do carrinho com base no nome.
    fun removerProduto(nome: String): Produto?

    // Verifica se um produto está no carrinho com base no nome.
    fun verificarProduto(nome: String): Boolean

    // Obtém a quantidade de um produto no carrinho com base no nome.
    fun obterQuantidadeProduto(nome: String): Int

    // Lista todos os produtos no carrinho.
    fun listarProdutos(): Array<Produto?>

    // Calcula o valor total do carrinho.
    fun calcularTotal(): Double

    // Limpa o carrinho, removendo todos os produtos.
    fun limparCarrinho(): Array<Produto?>

    // Verifica se o carrinho está vazio.
    fun carrinhoVazio(): Boolean
}

//Questão 02
interface RegistroPacientes {
    // Adiciona um novo registro de paciente ao sistema.
    fun adicionarRegistro(nome: String, idade: Int, sexo: String, endereco: String): Paciente

    // Remove o registro de um paciente do sistema com base no nome.
    fun removerRegistro(nome: String): Paciente?

    // Verifica se um registro de paciente está no sistema com base no nome.
    fun verificarRegistro(nome: String): Boolean

    // Obtém a idade de um paciente com base no nome.
    fun obterIdadePaciente(nome: String): Int?

    // Obtém o sexo de um paciente com base no nome.
    fun obterSexoPaciente(nome: String): String?

    // Obtém o endereço de um paciente com base no nome.
    fun obterEnderecoPaciente(nome: String): String?

    // Lista todos os registros de pacientes no sistema.
    fun listarRegistros(): Array<Paciente?>

    // Conta o número total de registros de pacientes no sistema.
    fun contarRegistros(): Int

    // Limpa todos os registros de pacientes do sistema.
    fun limparRegistros(): Array<Paciente?>

    // Verifica se o sistema de registros de pacientes está vazio.
    fun sistemaVazio(): Boolean
}

//Questão 03
interface ControleTarefas {
    // Adiciona uma nova tarefa à lista de afazeres.
    fun adicionarTarefa(indice: Int, nome: String, horario: LocalDateTime): Tarefa

    // Remove uma tarefa da lista de afazeres com base no seu índice na lista.
    fun removerTarefa(indice: Int): Tarefa?

    // Marca uma tarefa como concluída com base no seu índice na lista.
    fun marcarTarefaConcluida(indice: Int)

    // Obtém uma tarefa da lista de afazeres com base no seu índice.
    fun obterTarefa(indice: Int): Tarefa

    // Lista todas as tarefas na lista de afazeres.
    fun listarTarefas(): Array<Tarefa?>

    // Conta o número total de tarefas na lista de afazeres.
    fun contarTarefas(): Int

    // Limpa a lista de afazeres, removendo todas as tarefas.
    fun limparTarefas(): Array<Tarefa?>

    // Verifica se a lista de afazeres está vazia.
    fun listaVazia(): Boolean
}

//Questão 04
interface ControleEncomendas {
    // Adiciona uma nova encomenda ao sistema.
    fun adicionarEncomenda(numero: Int, descricao: String, peso: Double): Encomenda

    // Remove uma encomenda do sistema com base no número da encomenda.
    fun removerEncomenda(numero: Int): Encomenda?

    // Verifica se uma encomenda está no sistema com base no número da encomenda.
    fun verificarEncomenda(numero: Int): Boolean

    // Obtém a descrição de uma encomenda com base no número da encomenda.
    fun obterDescricaoEncomenda(numero: Int): String?

    // Obtém o peso de uma encomenda com base no número da encomenda.
    fun obterPesoEncomenda(numero: Int): Double?

    // Lista todas as encomendas no sistema.
    fun listarEncomendas(): Array<Encomenda?>

    // Conta o número total de encomendas no sistema.
    fun contarEncomendas(): Int

    // Limpa todas as encomendas do sistema.
    fun limparEncomendas(): Array<Encomenda?>

    // Verifica se o sistema de encomendas está vazio.
    fun sistemaVazio(): Boolean
}

//Questão 05
interface CatalogoProdutos {
    // Adiciona um novo produto ao catálogo de produtos.
    fun adicionarProduto(nome: String, preco: Double, quantidadeEstoque: Int): Produto

    // Remove um produto do catálogo com base no nome.
    fun removerProduto(nome: String): Produto?

    // Verifica se um produto está no catálogo com base no nome.
    fun verificarProduto(nome: String): Boolean

    // Obtém o preço de um produto com base no nome.
    fun obterPrecoProduto(nome: String): Double?

    // Obtém a quantidade em estoque de um produto com base no nome.
    fun obterQuantidadeEstoqueProduto(p: Produto): Int?

    // Lista todos os itens no catálogo de produtos.
    fun listarProdutos(): Array<Produto?>

    // Conta o número total de itens no catálogo.
    fun contarProdutos(): Int

    // Limpa o catálogo, removendo todos os produtos.
    fun limparCatalogo(): Array<Produto?>

    // Verifica se o catálogo de produtos está vazio.
    fun catalogoVazio(): Boolean
}