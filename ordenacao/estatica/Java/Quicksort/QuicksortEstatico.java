public class QuicksortEstatico implements Ordenavel {

	private Object dados[];
	
	public QuicksortEstatico(Object[] dados) {
		this.dados = dados;
	}
	
	@Override
	public void ordenar() {
		quicksort(0, dados.length - 1);
    }

	private void quicksort(int esquerda, int direita) {
		if (esquerda < direita) {
            int meio = particao(esquerda, direita);
            
            quicksort(esquerda, meio - 1);
            quicksort(meio + 1, direita);
        }
    }	

	private int particao(int esquerda, int direita) {
        int pivo = (Integer) dados[esquerda];
        int indicePivo = esquerda;

        for (int j = esquerda + 1; j <= direita; j++) {
            if ((Integer)dados[j] < pivo) {
                indicePivo++;	
				trocar(indicePivo, j);
            }
        }

        trocar(esquerda, indicePivo);
        return indicePivo;
	}
	
	private void trocar(int a, int b) {
		Object aux = dados[a];
		dados[a] = dados[b];
		dados[b] = aux;	
	}
	
	@Override	
	public String imprimir() {
		String resultado = "[";
		for (int i = 0; i < dados.length; i++) {
			if (i == dados.length - 1) {
				resultado += dados[i];
			} else {
				resultado += dados[i] + ",";				
			}
		}
		return resultado + "]";
	}
}