import java.util.NoSuchElementException;

/**
 * Implementação de uma árvore binária heap máximo.
 * Esta classe implementa uma estrutura de dados heap usando um array para armazenar os elementos.
 * Em um heap máximo, o elemento pai é sempre maior ou igual aos seus filhos.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ArvoreBinariaHeapMaximo<T> implements Amontoavel<T> {
    /** Array que armazena os elementos do heap */
    private T[] dados;
    
    /** Índice do último elemento inserido no heap */
    private int ponteiroFim;

    /**
     * Construtor padrão que inicializa o heap com tamanho 10.
     */
    public ArvoreBinariaHeapMaximo() {
        this(10);
    }

    /**
     * Construtor que inicializa o heap com o tamanho especificado.
     * 
     * @param tamanho o tamanho inicial do heap
     */
    public ArvoreBinariaHeapMaximo(int tamanho) {
        dados = (T[]) new Object[tamanho];
        ponteiroFim = -1;
    }

    @Override
    public T obterRaiz() {
        if (estaVazia()) {
            throw new NoSuchElementException("Elemento não encontrado!");
        }       
        return dados[0];
    }
	
	@Override
    public void inserir(T dado) {
        if (estaCheia()) {
            throw new OverflowException("Heap Cheia!");
        }
		ponteiroFim++;
		dados[ponteiroFim] = dado;
		ajustarAcima(ponteiroFim);
    }



    /**
     * Ajusta a estrutura do heap para cima após uma inserção.
     * 
     * @param indice índice do elemento a ser ajustado
     */
    private void ajustarAcima(int indice) {
        ajustarAcimaIterativo(indice);
        //ajustarAcimaRecursivo(indice);        
    }

    /**
     * Implementação iterativa do ajuste para cima.
     * 
     * @param indice índice do elemento a ser ajustado
     */
    private void ajustarAcimaIterativo(int indice) {
        int pai = indicePai(indice);
        while (indice > 0 && (Long) dados[indice] > (Long) dados[pai]) {
            trocar(indice, pai);
            indice = pai;
            pai = indicePai(indice);
        }
    }

    /**
     * Implementação recursiva do ajuste para cima.
     * 
     * @param indice índice do elemento a ser ajustado
     */
    private void ajustarAcimaRecursivo(int indice) {
        int pai = indicePai(indice);
        if (indice > 0 && (Long) dados[indice] > (Long) dados[pai]) {
            trocar(indice, pai);
            ajustarAcimaRecursivo(pai);
        }
    }

    @Override
    public T extrair() {
        if (estaVazia()) {
            throw new UnderflowException("Heap Vazia!");
        }
		T dadoRaiz = dados[0];
		dados[0] = dados[ponteiroFim];
		ponteiroFim--;
		ajustarAbaixo(0);

        return dadoRaiz;
    }
	
    /**
     * Ajusta a estrutura do heap para baixo após uma remoção.
     * 
     * @param pai índice do elemento pai a ser ajustado
     */
    private void ajustarAbaixo(int pai) {
        // ajustarAbaixoIterativo(pai);
        ajustarAbaixoRecursivo(pai);
    }

    /**
     * Implementação iterativa do ajuste para baixo.
     * 
     * @param pai índice do elemento pai a ser ajustado
     */
    private void ajustarAbaixoIterativo(int pai) {
        boolean ajustado = false;

        while (!ajustado) {
            int filhoEsquerdo = indiceFilhoEsquerdo(pai);
            int filhoDireito = indiceFilhoDireito(pai);
            int maior = pai;
    
        //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
        if (filhoEsquerdo <= ponteiroFim) { 
            if ((Long)dados[filhoEsquerdo] > (Long) dados[maior]) {
                maior = filhoEsquerdo;
            }
        }

        //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
        if (filhoDireito <= ponteiroFim) {  
            if ((Long)dados[filhoDireito] > (Long)dados[maior]) {
                maior = filhoDireito;
            }
        }
    
            // Se o maior não for o pai, troca e continua descendo
            if (maior != pai) {
                trocar(pai, maior);
                pai = maior; // desce para a posição onde o maior estava
            } else {
                ajustado = true; // heap está ajustado, sai do loop
            }
        }
    }

    /**
     * Implementação recursiva do ajuste para baixo.
     * 
     * @param pai índice do elemento pai a ser ajustado
     */
    private void ajustarAbaixoRecursivo(int pai) {
        int filhoEsquerdo = indiceFilhoEsquerdo(pai);
        int filhoDireito = indiceFilhoDireito(pai);
        int maior = pai;    

        //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
        if (filhoEsquerdo <= ponteiroFim) { 
            if ((Long)dados[filhoEsquerdo] > (Long)dados[maior]) {
                maior = filhoEsquerdo;
            }
        }

        //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
        if (filhoDireito <= ponteiroFim) {  
            if ((Long)dados[filhoDireito] > (Long)dados[maior]) {
                maior = filhoDireito;
            }
        }

        if (maior != pai) {
            trocar(pai, maior);
            ajustarAbaixoRecursivo(maior);
        }
    }
    
    /**
     * Troca dois elementos de posição no array.
     * 
     * @param i índice do primeiro elemento
     * @param j índice do segundo elemento
     */
    private void trocar(int i, int j) {
        T temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    /**
     * Calcula o índice do pai de um elemento.
     * 
     * @param filho índice do elemento filho
     * @return índice do elemento pai
     */
    private int indicePai(int filho) {
        return (filho - 1) / 2;
    }
    /**
     * Calcula o índice do filho esquerdo de um elemento.
     * 
     * @param pai índice do elemento pai
     * @return índice do filho esquerdo
     */
    private int indiceFilhoEsquerdo(int pai) {
        return 2 * pai + 1;
    }

    /**
     * Calcula o índice do filho direito de um elemento.
     * 
     * @param pai índice do elemento pai
     * @return índice do filho direito
     */
    private int indiceFilhoDireito(int pai) {
        return (2 * pai + 1) + 1;
    }

	/**
	 * Verifica se a heap está vazia.
	 *
	 * @return true se a heap estiver vazia, false caso contrário
	 */
	@Override
    public boolean estaVazia() {
        return (ponteiroFim == -1);
    }

	/**
	 * Verifica se a heap está cheia.
	 *
	 * @return true se a heap estiver cheia, false caso contrário
	 */
	@Override
    public boolean estaCheia() {
        return (ponteiroFim == dados.length-1);
    }

	/**
	 * Retorna uma representação em string da estrutura de dados heap.
	 * Os elementos são separados por vírgula e delimitados por colchetes.
	 *
	 * @return string representando a estrutura de dados heap
	 */
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