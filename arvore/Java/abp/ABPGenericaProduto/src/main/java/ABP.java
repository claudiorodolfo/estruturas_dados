/**
 * Implementação de uma Árvore Binária de Pesquisa (ABP).
 * 
 * @param <T> Tipo dos dados armazenados na árvore.
 * 
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class ABP<T extends Comparable<T>> implements Arborizavel<T> {

    private NoTriplo<T> raiz;

    /**
     * Cria uma árvore binária de pesquisa vazia.
     */
    public ABP() {
        raiz = null;
    }

    /**
     * Retorna o nó raiz da árvore.
     * @return Nó raiz.
     */
    @Override    
    public NoTriplo<T> getRaiz() {
        return raiz;
    }

    /**
     * Remove todos os elementos da árvore.
     */
    @Override
    public void limpar() {
        raiz = null;
    }

    /**
     * Insere um novo elemento na árvore.
     * @param dado Elemento a ser inserido.
     */
    @Override
    public void inserir(T dado) {
        NoTriplo<T> novoNo = new NoTriplo<>();
        novoNo.setDado(dado);

        if (raiz == null) {
            raiz = novoNo;
        } else {
            NoTriplo<T> noAuxiliar = raiz;
            while (true) {
                int comparacao = dado.compareTo(noAuxiliar.getDado());

                if (comparacao <= 0) {
                    //PRECISO IR PARA A ESQUERDA
                    //mas não tem esquerda para ir, então insiro o dado aq
                    if (noAuxiliar.getEsquerda() == null) {
                        noAuxiliar.setEsquerda(novoNo);
                        novoNo.setGenitor(noAuxiliar);
                        break;
                    }
                    //tem esquerda para ir, então vou para esquerda
                    noAuxiliar = noAuxiliar.getEsquerda();
                } else {
                    //PRECISO IR PARA A DIREITA
                    //mas não tem direita para ir, então insiro o dado aq
                    if (noAuxiliar.getDireita() == null) {
                        noAuxiliar.setDireita(novoNo);
                        novoNo.setGenitor(noAuxiliar);
                        break;
                    }
                    //tem direita para ir, então vou para direita
                    noAuxiliar = noAuxiliar.getDireita();
                }
            }
        }
    }

    /**
     * Remove um elemento da árvore.
     * @param dado Elemento a ser removido.
     * @return Elemento removido, ou null se não encontrado.
     */
    @Override
    public T apagar(T dado) {
        NoTriplo<T> noAuxiliar = buscar(dado);
        // Nó não encontrado na árvore
        if (noAuxiliar == null)   
            return null;

        // Caso 1: Nó sem filhos
        if (noAuxiliar.getEsquerda() == null &&
                noAuxiliar.getDireita() == null)
            apagarNoFolha(noAuxiliar);
        // Caso 2: Nó com um filho
        else if (noAuxiliar.getEsquerda() == null ||
                noAuxiliar.getDireita() == null)
            apagarComUmFilho(noAuxiliar);
        // Caso 3: Nó com dois filhos
        else
            apagarComDoisFilhos(noAuxiliar);

        return dado;
    }    

    private void apagarNoFolha(NoTriplo<T> nodo) {
        NoTriplo<T> pai = nodo.getGenitor();
        if (pai == null) {
            raiz = null;
        } else {
            if (nodo.equals(pai.getEsquerda()))
                //nodo é filho da esquerda
                pai.setEsquerda(null);
            else
                //nodo é filho da direita        
                pai.setDireita(null);
        }
    }

    private void apagarComUmFilho(NoTriplo<T> nodo) {
        NoTriplo<T> avo = nodo.getGenitor();
        NoTriplo<T> neto = ((nodo.getEsquerda() != null) ? nodo.getEsquerda() : nodo.getDireita());        
        if (avo == null) {
            raiz = neto;
            raiz.setGenitor(null);
        } else {
            neto.setGenitor(avo);
            if (nodo.equals(avo.getEsquerda())) {
                avo.setEsquerda(neto);
            } else {
                avo.setDireita(neto);
            }
        }
    }

    private void apagarComDoisFilhos(NoTriplo<T> nodo) {
        //sucessor pode ser o menor a direita ou o maior a esquerda
        NoTriplo<T> noSucessor = encontraMenorDireita(nodo);
        //NoTriplo<T> sucessor = encontraMaiorEsquerda(nodo);
        
        //copia o conteúdo do sucessor para o nodo
        nodo.setDado(noSucessor.getDado());

        // Remove o dado duplicado a direita
        if (noSucessor.getEsquerda() == null && 
        noSucessor.getDireita() == null) {
            apagarNoFolha(noSucessor);
        } else {
            apagarComUmFilho(noSucessor);
        }
    } 

    private NoTriplo<T> encontraMenorDireita(NoTriplo<T> nodo) {
        NoTriplo<T> noAuxiliar = nodo.getDireita();
        while (noAuxiliar.getEsquerda() != null)
            noAuxiliar = noAuxiliar.getEsquerda();

        return noAuxiliar;
    }  

    private NoTriplo<T> encontraMaiorEsquerda(NoTriplo<T> nodo) {
        NoTriplo<T> noAuxiliar = nodo.getEsquerda();
        while (noAuxiliar.getDireita() != null)
            noAuxiliar = noAuxiliar.getDireita();

        return noAuxiliar;
    } 

    /**
     * Verifica se um elemento existe na árvore.
     * @param dado Elemento a ser buscado.
     * @return true se o elemento existe, false caso contrário.
     */
    @Override
    public boolean existe(T dado) {
        return buscar(dado) != null;
    }

    private NoTriplo<T> buscar(T dado) {
        NoTriplo<T> noAuxiliar = raiz;
        while (noAuxiliar != null) {
            int comparacao = dado.compareTo(noAuxiliar.getDado());
            if (comparacao == 0) 
                return noAuxiliar;

                noAuxiliar = ((comparacao <= 0) ? noAuxiliar.getEsquerda() : noAuxiliar.getDireita());
        }
        return null;
    }

    /**
     * Retorna uma String com os elementos em pré-ordem.
     * @return Elementos em pré-ordem.
     */
    @Override
    public String imprimirPreOrdem() {
        return formataSaida(imprimirPreOrdemRec(raiz));
    }

    /**
     * Retorna uma String com os elementos em ordem.
     * @return Elementos em ordem.
     */
    @Override
    public String imprimirEmOrdem() {
        return formataSaida(imprimirEmOrdemRec(raiz));
    }

    /**
     * Retorna uma String com os elementos em pós-ordem.
     * @return Elementos em pós-ordem.
     */
    @Override
    public String imprimirPosOrdem() {
        return formataSaida(imprimirPosOrdemRec(raiz));
    }

    private String imprimirPreOrdemRec(NoTriplo<T> raizAtual) {
        if (raizAtual == null) return "";   //caso base
        return raizAtual.getDado() + " " + 
                imprimirPreOrdemRec(raizAtual.getEsquerda()) +  " " +
                imprimirPreOrdemRec(raizAtual.getDireita());
    }

    private String imprimirEmOrdemRec(NoTriplo<T> raizAtual) {
        if (raizAtual == null) return "";   //caso base
        return imprimirEmOrdemRec(raizAtual.getEsquerda()) + " " + 
                raizAtual.getDado() + " " +
                imprimirEmOrdemRec(raizAtual.getDireita());    
    }

    private String imprimirPosOrdemRec(NoTriplo<T> raizAtual) {
        if (raizAtual == null) return "";   //caso base
        return imprimirPosOrdemRec(raizAtual.getEsquerda()) + " " +
                imprimirPosOrdemRec(raizAtual.getDireita()) +  " " +
                raizAtual.getDado();           
    }

    private String formataSaida(String msg) {
        // Substitui um ou mais espaços em branco por uma vírgula, após remover espaços das bordas
        msg = msg.trim().replaceAll("\\s+", ",");
        return "[" + msg + "]";
    }
}