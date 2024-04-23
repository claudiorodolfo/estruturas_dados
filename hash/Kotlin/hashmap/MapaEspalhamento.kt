public class MapaEspalhamento implements Espalhavel{

	private Object[] tabelaEspalhamento;
	//Controla quantos elementos há na estrutura Mapa Espalhamento (HashMap)
	private int quantidade;
	//controla quantas listas terá a tabela de dispersão/espalhamento
	private int tamanhoTabela;
	
	public MapaEspalhamento(int tamanhoTabela) {
		quantidade = 0;
		this.tamanhoTabela = tamanhoTabela;
		//inicializa a tabela
		tabelaEspalhamento = new Object[tamanhoTabela];
		for (int i = 0; i < tabelaEspalhamento.length; i++) { 
			//os itens da tabela poderiam ser outra ED, mas optou-se pela ED Lista
			tabelaEspalhamento[i] = new ListaEstaticaCircular(10);
		}
	}
	
	//funcao que espalha os elementos "chave,valor" para cairem em listas diferentes
	//baseado no primeiro caracter da chave
	private int funcaoEspalhamento(String chave){ 
		char letraInicial = chave.toLowerCase().charAt(0);
		return letraInicial % tamanhoTabela; 
	}

	//outro exemplo de função de espalhamento que usa todos os caracteres da chave
	private int funcaoEspalhamento2(String chave){
		int total = 0;
		for (int i = 0; i < chave.length(); i++) { 
			char letra = chave.toLowerCase().charAt(i);
			total += letra;
		}
		return total % tamanhoTabela; 
	}
	
	//put(), armazena um par de "chave,objeto" especificado
	public void adicionar(Mapa mapa) {
		int indice = funcaoEspalhamento(mapa.getChave()); 
		Listavel listaTemporaria = (Listavel) tabelaEspalhamento[indice]; 
		//se existe a chave atualiza o mapa, senão existir insere o mapa
		if (contemChave(mapa.getChave())) {
			for (int i = 0; i < listaTemporaria.getQuantidade(); i++) {
				Mapa elementoLista = (Mapa) listaTemporaria.selecionarUm(i);
				String chaveLista = elementoLista.getChave();
			
				if (chaveLista.equals(mapa.getChave())) {
					listaTemporaria.atualizar(i, mapa);
					break;
				}
			}			
		}
		else {
			listaTemporaria.anexar(mapa);
			quantidade++;
		}
		
	}
	
	//remove(), remove o objeto associado a chave especificada
	public Object remover(String chave) {
		Object dadoAuxiliar = null;
		if (!estaVazio()) {
			if (contemChave(chave)) {
				//obtem o indice da tabela de espalhamento que a chave pertence
				int indice = funcaoEspalhamento(chave); 
				//Seleciona um item da tabela (esse item é uma lista)
				Listavel listaTemporaria = (Listavel) tabelaEspalhamento[indice];
				//para cada elemento da lista é verificado se a chave informada é 
				//igual a chave do referido elemento

				for (int i = 0; i < listaTemporaria.getQuantidade(); i++) {
					Mapa elementoLista = (Mapa) listaTemporaria.selecionarUm(i);
					String chaveLista = elementoLista.getChave();
			
					if (chave.equals(chaveLista)) {
						dadoAuxiliar = elementoLista.getDado();
						listaTemporaria.apagar(i);
						break;
					}
				}
			}
			else {
				System.err.println("Chave não existente.");
			}
		}
		else {
				System.err.println("A estrutura de dados está vazia.");
		}
		return dadoAuxiliar;
	}	
	
	//containsKey(), consulta se uma determinada chave existe na tabela
	public boolean contemChave(String chave) {
		boolean chaveEncontrada = false;
		if (!estaVazio()) {
			//obtem o indice da tabela de espalhamento que a chave pertence
			int indice = funcaoEspalhamento(chave); 
			//Seleciona um item da tabela (esse item é uma lista)
			Listavel listaTemporaria = (Listavel) tabelaEspalhamento[indice];
			//para cada elemento da lista é verificado se a chave informada é 
			//igual a chave do referido elemento

			for (int i = 0; i < listaTemporaria.getQuantidade(); i++) {
				Mapa elementoLista = (Mapa) listaTemporaria.selecionarUm(i);
				String chaveLista = elementoLista.getChave();
			
				if (chave.equals(chaveLista)) {
					chaveEncontrada = true;
					break;
				}
			}		
		} 
		else {
				System.err.println("A estrutura de dados está vazia.");
		}
		return chaveEncontrada;
	}		

	//get(), retorna o objeto  associado a chave especificada
	public Object buscar(String chave) {
		Object dadoAuxiliar = null;
		if (!estaVazio()) {
			if (contemChave(chave)) {
				//obtem o indice da tabela de espalhamento que a chave pertence
				int indice = funcaoEspalhamento(chave); 
				//Seleciona um item da tabela (esse item é uma lista)
				Listavel listaTemporaria = (Listavel) tabelaEspalhamento[indice];
				//para cada elemento da lista é verificado se a chave informada é 
				//igual a chave do referido elemento

				for (int i = 0; i < listaTemporaria.getQuantidade(); i++) {
					Mapa elementoLista = (Mapa) listaTemporaria.selecionarUm(i);
					String chaveLista = elementoLista.getChave();
			
					if (chave.equals(chaveLista)) {
						dadoAuxiliar = elementoLista.getDado();
						break;
					}
				}
			}
			else {
				System.err.println("Chave não existente.");
			}			
		}
		else {
				System.err.println("A estrutura de dados está vazia");
		}
		return dadoAuxiliar;
	}
	
	//size(), retorna o número de elementos da estrutura
	public int tamanho() {
		return quantidade;
	}
	
	public boolean estaVazio() {
		return (quantidade == 0);
	}
	
	public String imprimir() {
		String resultado = "[";
		for (int i = 0; i < tabelaEspalhamento.length; i++) {
			Listavel listaTemporaria = (Listavel) tabelaEspalhamento[i]; 
			resultado += listaTemporaria.imprimir();
		}
		return resultado + "]";	
	}
}