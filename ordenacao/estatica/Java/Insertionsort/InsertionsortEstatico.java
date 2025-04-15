public class InsertionsortEstatico implements Ordenavel {

	private Object dados[];
	
	public InsertionsortEstatico(Object[] dados) {
		this.dados = dados;
	}
	
	/*
	@Override
	public void ordenarCrescente() {		
		for (int i = 1; i < dados.length; i++) {
			for (int j = i-1; j >= 0; j--) {
				if((Integer)dados[j+1] < (Integer)dados[j])
					trocar(j, j+1);
				else
					break;
			}
		}
	}
	
	@Override
	public void ordenarDecrescente() {		
		for (int i = 1; i < dados.length; i++) {
			for (int j = i-1; j >= 0; j--) {
				if((Integer)dados[j+1] > (Integer)dados[j])
					trocar(j, j+1);
				else
					break;
			}
		}
	}	
	*/

	@Override
	public void ordenar() {
        ordenarCrescente();
    }
	
	@Override
	public void ordenarCrescente() {
        for (int i = 1; i < dados.length; i++) {
			int j = i;
			while ((j > 0) && ((Integer)dados[j] < (Integer)dados[j-1])) {
				trocar(j, j-1);	
				j--;
			}
        }
    }

	@Override
	public void ordenarDecrescente() {
        for (int i = 1; i < dados.length; i++) {
			int j = i;
			while ((j > 0) && ((Integer)dados[j] > (Integer)dados[j-1])) {
				trocar(j, j-1);	
				j--;
			}
        }
    }
	
	private void trocar(int a, int b) {
		Object aux = dados[a];
		dados[a] = dados[b];
		dados[b] = aux;	
	}
}