public interface Espalhavel {
	//put(), armazena um par de objetos especificados
	public void adicionar(Mapa mapa);
	//remove(), remove o  objeto com a chave especificada
	public Object remover(String chave);
	//contains(), consulta se um determinado valor existe na tabela
	//public boolean contemValor(Object dado);
	//containsKey(), consulta se uma determinada chave existe na tabela
	public boolean contemChave(String chave);
	//get(), retorna o objeto  associado a chave especificada
	public Object buscar(String chave);
	//getAll(), retorna todos os objetos armazenados
	//public Object[] buscarTodos();
	//size(), retorna o número de elementos da estrutura
	public int tamanho();
	
	//informa se a estrutura de dados está vazia
	boolean estaVazio();
	//imprime o conteúdo da estrutura de dados
	String imprimir();
}