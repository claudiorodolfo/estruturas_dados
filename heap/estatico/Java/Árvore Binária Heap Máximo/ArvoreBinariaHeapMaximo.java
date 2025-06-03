import java.util.NoSuchElementException;

public class ArvoreBinariaHeapMaximo implements Amontoavel {
    private Long[] dados;
    private int ponteiroFim;

    public ArvoreBinariaHeapMaximo(int tamanho) {
		dados = new Long[tamanho];
        ponteiroFim = -1;
    }

    public ArvoreBinariaHeapMaximo() {
		this(10);
    }

    @Override
    public Long obterRaiz() {
        if (estaVazia()) {
            throw new NoSuchElementException("Elemento não encontrado!");
        }       
        return dados[0];
    }
	
	@Override
    public void inserir(Long dado) {
        if (estaCheia()) {
            throw new OverflowException("Heap Cheia!");
        }
		ponteiroFim++;
		dados[ponteiroFim] = dado;
		ajustarAcima(ponteiroFim);
    }

    private void ajustarAcima(int indice) {
        ajustarAcimaIterativo(indice);
        //ajustarAcimaRecursivo(indice);        
    }
    private void ajustarAcimaIterativo(int indice) {
        while (indice > 0) {
            if (dados[indice] > dados[indicePai(indice)]) {
                trocar(indice, indicePai(indice));
                indice = indicePai(indice);
            } else {
                break;
            }
        }
    }

    private void ajustarAcimaRecursivo(int indice) {
    }
    
    private void trocar(int i, int j) {
        Long temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }
	
    private int indicePai(int filho) {
        return (int)((filho-1) / 2);
    }
	
	@Override
    public Long extrair() {
        if (estaVazia()) {
            throw new OverflowException("Heap Vazia!");
        }
		Long dadoRaiz = dados[0];
		dados[0] = dados[ponteiroFim];
		ponteiroFim--;
		ajustarAbaixo(0);

        return dadoRaiz;
    }
	
    private void ajustarAbaixo(int pai) { 
        //ajustarAbaixoIterativo(pai);
        ajustarAbaixoRecursivo(pai);
    }

    private void ajustarAbaixoIterativo(int pai) { 
    }

    private void ajustarAbaixoRecursivo(int pai) {    
        int filhoEsquerdo = indiceFilhoEsquerdo(pai);
        int filhoDireito = indiceFilhoDireito(pai);
        int maior = pai;    
        if (filhoEsquerdo <= ponteiroFim) { //está dentro dos valores válidos do array
            if ( dados[filhoEsquerdo] > dados[maior]) {
                maior = filhoEsquerdo;
            }
        }

        if (filhoDireito <= ponteiroFim) {  //está dentro dos valores válidos do array
            if (dados[filhoDireito] > dados[maior]) {
                maior = filhoDireito;
            }
        }

        if (maior != pai) {
            trocar(pai, maior);
            ajustarAbaixoRecursivo(maior);
        }
    }
   
    private int indiceFilhoDireito(int pai) {
        return (2 * pai + 1) + 1;
    }
  
    private int indiceFilhoEsquerdo(int pai) {
        return 2 * pai + 1;
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
            resultado += dados[i];
            if (i != ponteiroFim) {
			    resultado += ",";
			}
        }
		return resultado += "]";
    }
}