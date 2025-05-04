public class ArvoreBinariaHeapMinimo implements Amontoavel {
    private Object[] dados;
    private int ponteiroFim;

    public ArvoreBinariaHeapMinimo(int tamanho) {
		dados = new Object[tamanho];
        ponteiroFim = -1;
    }

    public ArvoreBinariaHeapMinimo() {
		this(10);
    }

	@Override
    public void inserir(Object dado) {
        if (!estaCheia()) {
			ponteiroFim++;
			dados[ponteiroFim] = dado;
			ajustarAcima(ponteiroFim);
        } else {
			System.err.println("Heap is full!");
		}
    }

    private void ajustarAcima(int indice) {
        while (indice > 0) {
            if ((Integer) dados[indice] < (Integer) dados[indicePai(indice)]) {
                trocar(indice, indicePai(indice));
                indice = indicePai(indice);
            } else {
                break;
            }
        }
    }

    private void ajustarAbaixo(int pai) {
        int filhoEsquerdo = indiceFilhoEsquerdo(pai);
        int filhoDireito = indiceFilhoDireito(pai);
        int menor = pai;

        if (filhoEsquerdo <= ponteiroFim) { //está dentro dos valores válidos do array
            if ((Integer) dados[filhoEsquerdo] < (Integer) dados[menor]) {
                menor = filhoEsquerdo;
            }
        }

        if (filhoDireito <= ponteiroFim) { //está dentro dos valores válidos do array
            if ((Integer) dados[filhoDireito] < (Integer) dados[menor]) {
                menor = filhoDireito;
            }
        }

        if (menor != pai) {
            trocar(pai, menor);
            ajustarAbaixo(menor);
        }
    }
	
    private void trocar(int i, int j) {
        Object temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    private int indiceFilhoDireito(int pai) {
        return 2 * pai + 2;
    }
  
    private int indiceFilhoEsquerdo(int pai) {
        return 2 * pai + 1;
    }
        
    private int indicePai(int filho) {
        return (int) (filho / 2);
    }

	@Override
    public Object extrair() {
		Object raiz = null;
        if (!estaVazia()) {
			raiz = dados[0];
			dados[0] = dados[ponteiroFim];
			ponteiroFim--;
			ajustarAbaixo(0);
        } else {
			System.err.println("Heap is Empty!");
		}
        return raiz;
    }

    @Override
    public Object obterRaiz() {
        Object dadoRaiz = null;
        if (!estaVazia()) {
            dadoRaiz = dados[0];
        }
        return dadoRaiz;
    }
        
	@Override
    public boolean estaVazia() {
        return (ponteiroFim == -1);
    }

	@Override
    public boolean estaCheia() {
        return (ponteiroFim == dados.length-1);
    }

	@Override	
    public String imprimir() {
		String resultado = "[";
        for (int i = 0; i <= ponteiroFim; i++) {
			if (i == ponteiroFim) {
				resultado += dados[i];
			} else {
			    resultado += dados[i] + ",";
			}
        }
		return resultado += "]";
    }
}