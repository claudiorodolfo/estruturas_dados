public class SelectionsortEstatico implements Ordenavel {

	private Object dados[];
	
	public SelectionsortEstatico(Object[] dados) {
		this.dados = dados;
	}
	
	@Override
	public void ordenar() {
		ordenarCrescente();
	}
	
	@Override
	public void ordenarCrescente() {
        for (int i = 0; i < dados.length; i++) {
			int indiceMenor = i;
            for (int j = i+1; j < dados.length; j++) {
                if ((Integer) dados[j] < (Integer)dados[indiceMenor]){
                    indiceMenor = j;
                }
            }
			trocar(i, indiceMenor);
        }
    }

	@Override
	public void ordenarDecrescente() {
        for (int i = 0; i < dados.length; i++) {
			int indiceMaior = i;
            for (int j = i+1; j < dados.length; j++) {
                if ((Integer) dados[j] > (Integer)dados[indiceMaior]){
                    indiceMaior = j;
                }
            }
			trocar(i, indiceMaior);
        }
    }
	
	private void trocar(int a, int b) {
		Object aux = dados[a];
		dados[a] = dados[b];
		dados[b] = aux;	
	}
}