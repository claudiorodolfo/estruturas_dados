public class MapaEspalhamentoPrincipal {

	public static void main(String args[]) {
		//elementos que serão introduzidos na ED
		Cliente c1 = new Cliente("Claudio Rodolfo", 30);
		Cliente c2 = new Cliente("Maria Isabel", 12);
		Cliente c3 = new Cliente("Antonio Carlos", 23);
		Cliente c4 = new Cliente("João Pedro", 21);
		Cliente c5 = new Cliente("Maria Eduarda", 53);
		Cliente c6 = new Cliente("Carlos Eduardo", 56);
		
		//popula a Estrutura Conjunto Espalhamento
		Espalhavel hashmap = new MapaEspalhamento(26);
		hashmap.adicionar(new Mapa("clro", c1));
		hashmap.adicionar(new Mapa("maria", c2));
		hashmap.adicionar(new Mapa("toin", c3));
		hashmap.adicionar(new Mapa("jao", c4));
		hashmap.adicionar(new Mapa("carlos", c5));
		
		//chave para testes
		String chave = "clro";
		Cliente clienteTemp = (Cliente) hashmap.buscar(chave);
		System.out.println("a idade de "+ chave +" eh:"+ clienteTemp.getIdade());
		
		//verifica se há elemento com a chave maria
		chave = "maria";
		if (hashmap.contemChave(chave))
			System.out.println("contem a chave " + chave);
		else
			System.out.println("nao contem a chave " + chave);

		//verifica se há elemento com a chave jorge
		chave = "jorge";
		if (hashmap.contemChave(chave))
			System.out.println("contem a chave " + chave);
		else
			System.out.println("nao contem a chave " + chave);
		
		//remove o elemento referenciado pela chave maria
		hashmap.remover("maria");
		chave = "maria";
		if (hashmap.contemChave(chave))
			System.out.println("contem a chave " + chave);
		else
			System.out.println("não contem a chave " + chave);
		
	}
}