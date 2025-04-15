public class SelectionsortEstatico implements Ordenavel {

	private Object dados[];
	
	public SelectionsortEstatico(Object[] dados) {
		this.dados = dados;
	}
	
	@Override
	public void ordenar() {
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
	
	private void trocar(int a, int b) {
		Object aux = dados[a];
		dados[a] = dados[b];
		dados[b] = aux;	
	}
	
	@Override
	public String imprimir() {
		String resultado = "";
		for (int i = 0; i < dados.length; i++) {
			resultado += dados[i];
			if (i != dados.length - 1)
				resultado += ",";				
		}
		return "[" + resultado + "]";
	}
}