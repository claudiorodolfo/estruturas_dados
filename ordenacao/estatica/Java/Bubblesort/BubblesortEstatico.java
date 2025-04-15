public class BubblesortEstatico implements Ordenavel {

	private Object dados[];
	
	public BubblesortEstatico(Object[] dados) {
		this.dados = dados;
	}
	
	@Override
	public void ordenar() {
		ordenarCrescente();
	}
	
	public void ordenarCrescente() {
        for (int i = 0; i < dados.length-1; i++) {
            for (int j = 0; j < (dados.length-1)-i; j++) {
                if ((Integer) dados[j] > (Integer)dados[j+1]){
                    trocar(j, j+1);
                }
            }
        }
    }

	@Override
	public void ordenarDecrescente() {
        for (int i = 0; i < dados.length-1; i++) {
            for (int j = 0; j < (dados.length-1)-i; j++) {
                if ((Integer) dados[j] < (Integer)dados[j+1]){
                    trocar(j, j+1);
                }
            }
        }
    }
	
	private void trocar(int a, int b) {
		Object aux = dados[a];
		dados[a] = dados[b];
		dados[b] = aux;	
	}
}