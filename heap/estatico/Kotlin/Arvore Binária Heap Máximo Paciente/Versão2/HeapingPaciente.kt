public interface HeapingPaciente {
	fun inserir(dado: Paciente)		//insert	C
	fun extrair(): Paciente?		//extract	D
	fun obter(): Paciente?			//get		R
	fun atualizar(dado: Paciente)	//update	U
	
	fun imprimir(): String
	fun estaVazia(): Boolean
	fun estaCheia(): Boolean
}