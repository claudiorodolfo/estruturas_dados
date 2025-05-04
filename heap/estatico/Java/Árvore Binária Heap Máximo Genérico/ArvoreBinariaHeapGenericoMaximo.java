import java.util.NoSuchElementException;

/**
 * Esta classe implementa uma binary heap, usada em fila de prioridades
 * 
 * @author Oliveira, C. R. S.
 * @version 1.1
 * @since 2025-05-02
 */
public class ArvoreBinariaHeapGenericoMaximo<T> implements Amontoavel<T> {
    private T[] dados;
    private int ponteiroFim;

    /**
 	* Construtor que recebe um tamanho máximo.
	*
 	* @param tamanho, indica o tamanho máximo que a pilha pode ter
 	*/
	@SuppressWarnings("unchecked")    
    public ArvoreBinariaHeapGenericoMaximo(int tamanho) {
		dados = (T[]) new Object[tamanho];
        ponteiroFim = -1;
    }

	/**
 	* Construtor vazio. Cria heap com 10 elementos por padrão.
 	* 
 	*/
    public ArvoreBinariaHeapGenericoMaximo() {
		this(10);
    }

    /**
 	* Insere um elemento na heap.
 	* 
 	* @param dado o valor a ser inserido
 	* @throws NoSuchElementException se a heap estiver cheia
 	*/
	@Override
    public void inserir(T dado) {
        if (estaCheia()) {
            throw new NoSuchElementException("Heap está Cheia!"); 
        } 
		ponteiroFim++;
		dados[ponteiroFim] = dado;
		ajustarAcima(ponteiroFim);
    }

    /**
 	* Ajusta o heap para que o pai seja maior que os filhos, subindo na árvore.
    * Ajuste do índice passado, até a raiz (se necessário).
 	* 
 	* @param indice é o índice do nó corrente, do qual, a partir dele, deseja-se ajustar a árvore.
 	*/    
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

    /**
 	* Ajusta o heap para que o pai seja maior que os filhos, descendo na árvore.
    * Ajuste do índice passado, até a base do heap (se necessário).
 	* 
 	* @param indice é o índice do nó corrente, do qual, a partir dele, deseja-se ajustar a árvore.
 	*/   
    private void ajustarAbaixo(int pai) {    
        int filhoEsquerdo = indiceFilhoEsquerdo(pai);
        int filhoDireito = indiceFilhoDireito(pai);
        int maior = pai;    
        if (filhoEsquerdo <= ponteiroFim) { //está dentro dos valores válidos do array?
            if ((Integer) dados[filhoEsquerdo] > (Integer) dados[maior]) {
                maior = filhoEsquerdo;
            }
        }

        if (filhoDireito <= ponteiroFim) {  //está dentro dos valores válidos do array?
            if ((Integer) dados[filhoDireito] > (Integer) dados[maior]) {
                maior = filhoDireito;
            }
        }

        if (maior != pai) {
            trocar(pai, maior);
            ajustarAbaixo(maior);
        }
    }
	
    /**
 	* Troca os valores de dois elementos.
 	* 
 	* @param i é o indice do primeiro elemento
 	* @param j é o indice do segundo elemento   
 	*/
    private void trocar(int i, int j) {
        T temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    /**
 	* Retorna o índice do pai de um determinado elemento.
 	* 
 	* @param filho o indice do filho do elemento desejado
 	* @return o indice pai, do índice do elemento informado   
 	*/    
    private int indicePai(int filho) {
        return (int)((filho-1) / 2);
    }

    /**
 	* Retorna o índice do filho esquerdo de um determinado elemento.
 	* 
 	* @param filho o indice do filho do elemento desejado
 	* @return o indice do filho esquerdo, do índice do elemento informado
 	*/    
    private int indiceFilhoEsquerdo(int pai) {
        return 2 * pai + 1;
    }

    /**
 	* Retorna o índice do filho direito de um determinado elemento.
 	* 
 	* @param filho o indice do filho do elemento desejado
 	* @return o indice do filho direito, do índice do elemento informado   
 	*/      
    private int indiceFilhoDireito(int pai) {
        return 2 * pai + 2;
    }

    /**
 	* Extrai a raiz da árvore heap. Colocando o último elemento no lugar
 	* 
 	* @return o elemento raiz   
 	*/ 
	@Override
    public T extrair() {
        if (estaVazia()) {
            throw new NoSuchElementException("Heap está Vazia!"); 
        }
		T dadoRaiz = dados[0];
		dados[0] = dados[ponteiroFim];
		ponteiroFim--;
		ajustarAbaixo(0);
        return dadoRaiz;
    }

    /**
 	* retorna a raiz da heap.
 	* 
 	* @return o conteúdo da raiz
 	* @throws NoSuchElementException se a heap estiver vazia
 	*/
    @Override
    public T obterRaiz() {
        if (estaVazia()) {
            throw new NoSuchElementException("Heap está Vazia!");
		}  
        T dadoRaiz = dados[0];      
        return dadoRaiz;
    }

	/**
 	* Verifica se a pilha está vazia.
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
 	* Texto referente aos elementos da heap para serem impressos.
 	* 
	* @return um texto com os elementos separados por ",", delimitados por colchetes
 	*/	
	@Override	
    public String imprimir() {
		String resultado = "[";
        for (int i = 0; i <= ponteiroFim; i++) {
            resultado += dados[i];
			if (i == ponteiroFim) {
			    resultado += ",";
			}
        }
		return resultado += "]";
    }
}