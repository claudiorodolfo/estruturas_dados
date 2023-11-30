public class ArvoreBinariaHeapMaximo implements Amontoavel {
    private Object[] dados;
    private int ponteiroFim;

    public ArvoreBinariaHeapMaximo(int tamanho) {
		dados = new Object[tamanho];
        ponteiroFim = -1;
    }

    public ArvoreBinariaHeapMaximo() {
		this(10);
    }

    @Override
    public Object obterRaiz() {
        Object raizAux = null;
        if (!estaVazia()) {
            raizAux = dados[0];
        } else {
			System.err.println("Heap is Empty!");
		}        
        return raizAux;
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
            if ((Integer) dados[indice] > (Integer) dados[indicePai(indice)]) {
                trocar(indice, indicePai(indice));
                indice = indicePai(indice);
            } else {
                break;
            }
        }
    }

    private void trocar(int i, int j) {
        Object temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }
	
    private int indicePai(int filho) {
        return (int)((filho-1) / 2);
    }
	
	@Override
    public Object extrair() {
		Object dadoRaiz = null;
        if (!estaVazia()) {
			dadoRaiz = dados[0];
			dados[0] = dados[ponteiroFim];
			ponteiroFim--;
			ajustarAbaixo(0);
        } else {
			System.err.println("Heap is Empty!");
		}
        return dadoRaiz;
    }
	
    private void ajustarAbaixo(int pai) {    
        int filhoEsquerdo = 2 * pai + 1;
        int filhoDireito = (2 * pai + 1) + 1;
        int maior = pai;    
        if (filhoEsquerdo <= ponteiroFim) { //está dentro dos valores válidos do array
            if ((Integer) dados[filhoEsquerdo] > (Integer) dados[maior]) {
                maior = filhoEsquerdo;
            }
        }

        if (filhoDireito <= ponteiroFim) {  //está dentro dos valores válidos do array
            if ((Integer) dados[filhoDireito] > (Integer) dados[maior]) {
                maior = filhoDireito;
            }
        }

        if (maior != pai) {
            trocar(pai, maior);
            ajustarAbaixo(maior);
        }
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