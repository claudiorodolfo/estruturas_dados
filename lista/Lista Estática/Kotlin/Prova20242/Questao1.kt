//******************************Questão 1 *********************************
//Prova Tipo 1
// Interface para o controle de reservas de hotéis
interface ControleReservas {
    /**
     * Cria uma nova reserva. Recebe Identificador único do cliente, Data de entrada no hotel, Data de saída do hotel, Lista de serviços adicionais solicitados e Status(Pendente, Confirmada, Cancelada). Retorna a Reserva criada.
     */
    fun criaReserva(idCliente: String, dataEntrada: DateTime, dataSaida: DateTime, servicosAdicionais: List<String>, status: String): Reserva
    /**
     * Adiciona uma nova reserva à lista de reservas. Recebe a reserva a ser adicionada.
     */
    fun adicionarReserva(reserva: Reserva)

    /**
     * Remove uma reserva da lista de reservas, dado o identificador do cliente. Recebe Identificador único do cliente cuja reserva deve ser removida. Retorna true se a remoção foi bem-sucedida, false caso contrário.
     */
    fun removerReserva(idCliente: String): Boolean

    /**
     * Consulta uma reserva específica com base no identificador do cliente. Recebe Identificador único do cliente. Retorna a reserva correspondente ou null se não for encontrada.
     */
    fun consultarReserva(idCliente: String): Reserva?

    /**
     * Atualiza uma reserva existente. Recebe Identificador único do cliente e Dados atualizados da reserva. Retorna true se a atualização foi bem-sucedida, false caso contrário.
     */
    fun atualizarReserva(idCliente: String, novaReserva: Reserva): Boolean

    /**
     * Cancela a reserva de um cliente alterando seu status para "Cancelada". Recebe Identificador do cliente. Retorna true se a reserva foi cancelada com sucesso, false caso contrário.
     */
    fun cancelarReserva(idCliente: String): Boolean    
    /**
     * Retorna todas as reservas atuais. Retorna uma Lista de todas as reservas registradas.
     */
    fun listarReservas(): List<Reserva>

    /**
     * Lista reservas por status (Pendente, Confirmada, Cancelada). Recebe Status desejado das reservas. Retorna uma lista de reservas com o status desejado.
     */
    fun listarReservasPorStatus(status: String): List<Reserva>

    /**
     * Lista as reservas de um determinado cliente com base no nome. Recebe Nome do cliente. Retorna uma lista de reservas associadas ao cliente.
     */
    fun listarReservasPorCliente(nomeCliente: String): List<Reserva>    
}

//Prova Tipo 2
// Interface para o organizador de receitas culinárias
interface OrganizadorReceitas {
    /**
     * Cria uma nova receita. Recebe Nome da receita, Lista de ingredientes da receita, Instruções de preparo da receita e Categoria da receita (favorita, testada, etc.). Retorna a Receita criada.
     */
    fun criaReceita(nome: String, ingredientes: List<String>, modoPreparo: String, categoria: Categoria): Receita
	 
    /**
     * Adiciona uma nova receita à lista de receitas. Recebe a Receita a ser adicionada.
     */
    fun adicionarReceita(receita: Receita)

    /**
     * Remove uma receita da lista com base no nome da receita. Recebe Nome da receita a ser removida. Retorna true se a remoção foi bem-sucedida, false caso contrário.
     */
    fun removerReceita(nome: String): Boolean

    /**
     * Atualiza uma receita existente com base no nome. Recebe Nome da receita a ser atualizada e Nova receita que substituirá a anterior. Retorna true se a atualização foi bem-sucedida, false caso contrário.
     */
    fun atualizarReceita(nome: String, novaReceita: Receita): Boolean

    /**
     * Lista todas as receitas do organizador. Retorna uma lista de todas as receitas.
     */
    fun listarReceitas(): List<Receita>

    /**
     * Marca uma receita como favorita. Recebe Nome da receita a ser marcada como favorita. Retorna true se a operação foi bem-sucedida, false caso contrário.
     */
    fun marcarComoFavorita(nome: String): Boolean

    /**
     * Marca uma receita como testada. Recebe Nome da receita a ser marcada como testada. Retorna true se a operação foi bem-sucedida, false caso contrário.
     */
    fun marcarComoTestada(nome: String): Boolean

    /**
     * Busca receitas por categoria. Recebe Categoria a ser filtrada (favorita, testada, etc.). Retorna Lista de receitas filtradas pela categoria.
     */
    fun buscarPorCategoria(categoria: Categoria): List<Receita>

    /**
     * Busca uma receita pelo nome. Recebe Nome da receita. Retorna a receita correspondente ou null se não for encontrada.
     */
    fun buscarReceita(nome: String): Receita?
}

//Prova Tipo 3
// Interface para o controle de hábitos diários
interface ControladorHabitos {
    /**
     * Cria um novo hábito. Recebe Nome do hábito, Breve descrição do hábito e Data em que o hábito deve ser realizado. Retorna o Hábito criado.
     */
    fun criaHabito(nome: String, descricao: String, realizado: Boolean, data: DateTime): Habito

    /**
     * Adiciona um novo hábito à lista de hábitos. Recebe o Hábito a ser adicionado.
     */
    fun adicionarHabito(habito: Habito)

    /**
     * Remove um hábito da lista com base no nome.Recebe Nome do hábito a ser removido. Retorna true se a remoção foi bem-sucedida, false caso contrário.
     */
    fun removerHabito(nome: String): Boolean

    /**
     * Marca um hábito como realizado. Recebe Nome do hábito a ser marcado. Retorna true se o hábito foi marcado com sucesso, false caso contrário.
     */
    fun marcarHabitoRealizado(nome: String): Boolean

    /**
     * Atualiza os detalhes de um hábito existente. Recebe Nome do hábito a ser atualizado e Dados atualizados do hábito. Retorna true se a atualização foi bem-sucedida, false caso contrário.
     */
    fun atualizarHabito(nome: String, novoHabito: Habito): Boolean

    /**
     * Lista todos os hábitos registrados. Retorna uma lista de todos os hábitos.
     */
    fun listarHabitos(): List<Habito>

    /**
     * Filtra hábitos que foram marcados como realizados. Retorna uma Lista de hábitos que já foram realizados.
     */
    fun listarHabitosRealizados(): List<Habito>

    /**
     * Busca um hábito pelo nome. Recebe Nome do hábito. Retorna o hábito correspondente ou null se não for encontrado.
     */
    fun buscarHabito(nome: String): Habito?

    /**
     * Busca hábitos por data. Recebe Data em que os hábitos devem ser realizados. Retorna uma lista de hábitos para a data especificada.
     */
    fun buscarHabitosPorData(data: Date): List<Habito>
}

//Prova Tipo 4
// Interface para o catálogo de filmes e séries
interface CatalogoFilmesSeries {
    /**
     * Cria um novo filme ou série ao catálogo. Recebe Título do filme ou série, Tipo do conteúdo (se é "filme" ou "série"), Ano de lançamento, Avaliação e se já foi assistido. Retorna o filme ou série criada.
     */
    fun criaFilmeSerie(titulo: String, tipo: String, ano: Int, avaliacao: Double, assistido: Boolean): FilmeSerie
	
    /**
     * Adiciona um novo filme ou série ao catálogo. Recebe o Filme ou Série a ser adicionado.
     */
    fun adicionarItem(filmeSerie: FilmeSerie)

    /**
     * Remove um filme ou série do catálogo. Recebe Título do filme ou série a ser removido. Retorna true se a remoção for bem-sucedida, false caso contrário.
     */
    fun removerItem(titulo: String): Boolean

    /**
     * Marca um filme ou série como assistido(a). Recebe Título do filme ou série a ser marcado(a). Retorna true se o item foi marcado com sucesso, false caso contrário.
     */
    fun marcarComoAssistido(titulo: String): Boolean

    /**
     * Avalia um filme ou série. Recebe Título do filme ou série a ser avaliado(a) e Avaliação do conteúdo, entre 0.0 e 5.0. Retorna true se a avaliação foi bem-sucedida, false caso contrário.
     */
    fun avaliarItem(titulo: String, avaliacao: Double): Boolean

    /**
     * Lista todos os filmes e séries do catálogo. Retorna uma lista de todos os filmes e séries.
     */
    fun listarItens(): List<FilmeSerie>

    /**
     * Lista todos os filmes ou séries assistidos(as). Retorna uma lista de filmes ou séries que já foram assistidos(as).
     */
    fun listarAssistidos(): List<FilmeSerie>

    /**
     * Lista todos os filmes ou séries não assistidos(as). Retorna uma lista de filmes ou séries ainda não assistidos(as).
     */
    fun listarNaoAssistidos(): List<FilmeSerie>

    /**
     * Filtra filmes ou séries por classificação mínima. Recebe Avaliação mínima desejada. Retorna uma lista de filmes ou séries que atendem a avaliação mínima.
     */
    fun filtrarPorAvaliacao(avaliacao: Double): List<FilmeSerie>

    /**
     * Busca um filme ou série pelo título. Recebe Título do filme ou série. Retorna o filme ou série correspondente ou null se não for encontrado.
     */
    fun buscarItem(titulo: String): FilmeSerie?
}