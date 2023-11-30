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
            if ((Integer) dados[indice] < (Integer) dados[indiceGenitor(indice)]) {
                troca(indice, indiceGenitor(indice));
                indice = indiceGenitor(indice);
            } else {
                break;
            }
        }
    }

    private void ajustarAbaixo(int genitor) {
        int filhoEsquerdo = 2 * genitor + 1;
        int filhoDireito = 2 * genitor + 2;
        int menor = genitor;

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

        if (menor != genitor) {
            troca(genitor, menor);
            ajustarAbaixo(menor);
        }
    }
	
    private void troca(int i, int j) {
        Object temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    private int indiceGenitor(int filho) {
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
        Object raizAux = null;
        if (!estaVazia()) {
            raizAux = dados[0];
        }
        return raizAux;
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