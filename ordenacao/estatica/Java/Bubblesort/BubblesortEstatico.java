public class BubblesortEstatico implements Ordenavel {

	private Object dados[];
	
	public BubblesortEstatico(Object[] dados) {
		this.dados = dados;
	}
	
	@Override
	public void ordenar() {
        for (int i = 0; i < dados.length-1; i++) {
            for (int j = 0; j < (dados.length-1)-i; j++) {
                if ((Integer) dados[j] > (Integer)dados[j+1]){
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