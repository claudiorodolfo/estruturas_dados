public class ArvoreBinariaHeapGenericoMaximo<T> implements Amontoavel<T> {
    private T[] dados;
    private int ponteiroFim;

	@SuppressWarnings("unchecked")    
    public ArvoreBinariaHeapGenericoMaximo(int tamanho) {
		dados = (T[]) new Object[tamanho];
        ponteiroFim = -1;
    }

    public ArvoreBinariaHeapGenericoMaximo() {
		this(10);
    }

	@Override
    public void inserir(T dado) {
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

    private void ajustarAbaixo(int pai) {    
        int filhoEsquerdo = 2 * pai + 1;
        int filhoDireito = 2 * pai + 2;
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
	
    private void trocar(int i, int j) {
        T temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    private int indicePai(int filho) {
        return (int)((filho-1) / 2);
    }

	@Override
    public T extrair() {
		T dadoRaiz = null;
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

    @Override
    public T obterRaiz() {
        T raizAux = null;
        if (!estaVazia()) {
            raizAux = dados[0];
        } else {
			System.err.println("Heap is Empty!");
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