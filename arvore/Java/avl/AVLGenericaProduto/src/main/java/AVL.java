/**
 * Implementação de uma Árvore Binária de Busca Auto-Balanceada (AVL).
 * <p>
 * Garante que a altura da árvore permaneça logarítmica após inserções e remoções,
 * realizando rotações e rebalanceamentos automáticos.
 * </p>
 * @param <T> Tipo dos elementos armazenados, que deve ser comparável.
 */
public class AVL<T extends Comparable<T>> implements Arborizavel<T> {

    private NoTriplo<T> raiz;

    /**
     * Cria uma árvore AVL vazia.
     */
    public AVL() {
        raiz = null;
    }

    /**
     * Retorna o nó raiz da árvore AVL.
     * @return Nó raiz ou null se a árvore estiver vazia.
     */
    @Override    
    public NoTriplo<T> getRaiz() {
        return raiz;
    }

    /**
     * Remove todos os elementos da árvore AVL.
     */
    @Override
    public void limpar() {
        raiz = null;
    }
    
    //métodos AVL
    /**
     * Calcula o fator de balanceamento de um nó AVL.
     * @param nodo Nó a ser avaliado.
     * @return Diferença entre altura da subárvore esquerda e direita.
     */
    private int balanceamento(NoTriplo<T> nodo) {
        //se um noFolha tem altura zero, então 
        //a ausencia de nó (null), tem altura -1
        int alturaEsquerda = nodo.getEsquerda() != null ? nodo.getEsquerda().getAltura(): -1;
        int alturaDireita = nodo.getDireita() != null ? nodo.getDireita().getAltura(): -1;

        return alturaEsquerda - alturaDireita;
    }

    /**
     * Atualiza a altura de um nó com base nas alturas dos filhos.
     * @param nodo Nó a ser atualizado.
     */
    private void atualizaAltura(NoTriplo<T> nodo) {
        //se um noFolha tem altura zero, então
        //a ausencia de nó (null), tem altura -1        
        int alturaEsquerda = nodo.getEsquerda() != null ? nodo.getEsquerda().getAltura(): -1;
        int alturaDireita = nodo.getDireita() != null ? nodo.getDireita().getAltura(): -1;
        //noFolha tem altura zero = 1 + (-1)
        nodo.setAltura(1 + 
                Math.max(alturaEsquerda, alturaDireita));
    }

    /**
     * Realiza rotação simples à direita ao redor de um nó.
     * @param y Nó em torno do qual a rotação será feita.
     * @return Nova raiz da subárvore após rotação.
     */
    private NoTriplo<T> rotacaoDireita(NoTriplo<T> y) {
		//           T0     |           T0
		//           |      |           | 
		//  ANTES    y      |   DEPOIS  x
		//          / \     |          / \
		//         x  T3    |         T1  y
		//        / \	    |            / \
		//       T1  T2     |           T2 T3
        // T1 e T3 não sofrem alteração, 
        // por isso não aparecem no balanceamento
        NoTriplo<T> T0 = y.getGenitor();
        NoTriplo<T> x  = y.getEsquerda();
        NoTriplo<T> T2 = x.getDireita();

        //corrige ponteiros, descendo e subindo
        //[T0-->x], [T0<--x]
        if (T0 != null)
            if (y.equals(T0.getEsquerda()))
                T0.setEsquerda(x);
            else
                T0.setDireita(x);

        x.setGenitor(T0);

        //[x-->y], [x<--y]
        x.setDireita(y);
        y.setGenitor(x);   

        //[y-->T2], [y<--T2]
        y.setEsquerda(T2);        
        if (T2 != null)
            T2.setGenitor(y);

        atualizaAltura(y);
        atualizaAltura(x);
        //nova raiz é x
        return x;
    }

    /**
     * Realiza rotação simples à esquerda ao redor de um nó.
     * @param y Nó em torno do qual a rotação será feita.
     * @return Nova raiz da subárvore após rotação.
     */
    private NoTriplo<T> rotacaoEsquerda(NoTriplo<T> y) {
		//         T0       |             T0
		//         |        |             | 
		//  ANTES  y        |   DEPOIS    x
		//        / \       |            / \
		//       T3  x      |           y  T1
		//          / \     |          / \  
		//         T2  T1   |         T3  T2
        // T1 e T3 não sofrem alteração, 
        // por isso não aparecem no balanceamento
        NoTriplo<T> T0 = y.getGenitor();        
        NoTriplo<T> x  = y.getDireita();
        NoTriplo<T> T2 = x.getEsquerda();

        //corrige ponteiros, descendo e subindo
        //[T0-->x], [T0<--x]
        if (T0 != null)        
            if (y.equals(T0.getEsquerda()))
                T0.setEsquerda(x);
            else
                T0.setDireita(x);
                
        x.setGenitor(T0);
        
        //[x-->y], [x<--y]
        x.setEsquerda(y);
        y.setGenitor(x); 

        //[y-->T2], [y<--T2]
        y.setDireita(T2);        
        if (T2 != null)
            T2.setGenitor(y);

        // Atualiza as alturas
        atualizaAltura(y);
        atualizaAltura(x);
        // Retorna a nova raiz
        return x;
    }

    /**
     * Rebalanceia a árvore após inserção ou remoção.
     * @param dado Valor inserido/removido.
     * @param noAuxiliar Nó a partir do qual o rebalanceamento é feito.
     */
    private void rebalancear(T dado, NoTriplo<T> noAuxiliar) {
        //NoTriplo<T> referencia = noAuxiliar;
        while (noAuxiliar != null) {

            atualizaAltura(noAuxiliar);
            int desnivel = balanceamento(noAuxiliar);
             
            // Caso 1: Rotação à direita
            if (desnivel > 1 && dado.compareTo(noAuxiliar.getEsquerda().getDado()) < 0) {
                noAuxiliar = rotacaoDireita(noAuxiliar);
            }
            // Caso 2: Rotação à esquerda
            else if (desnivel < -1 && dado.compareTo(noAuxiliar.getDireita().getDado()) > 0) {
                noAuxiliar = rotacaoEsquerda(noAuxiliar);
            }
            // Caso 3: Rotação dupla a direita
            //Rotação à esquerda-direita
            else if (desnivel > 1 && dado.compareTo(noAuxiliar.getEsquerda().getDado()) > 0) {
                noAuxiliar.setEsquerda(rotacaoEsquerda(noAuxiliar.getEsquerda()));
                noAuxiliar = rotacaoDireita(noAuxiliar);
            }
            // Caso 4: Rotação dupla a esquerda
            //Rotação à direita-esquerda
            else if (desnivel < -1 && dado.compareTo(noAuxiliar.getDireita().getDado()) < 0) {
                noAuxiliar.setDireita(rotacaoDireita(noAuxiliar.getDireita()));
                noAuxiliar = rotacaoEsquerda(noAuxiliar);
            }

            if (noAuxiliar.getGenitor() != null) {
                if (noAuxiliar.equals(noAuxiliar.getGenitor().getEsquerda())) {
                    noAuxiliar.getGenitor().setEsquerda(noAuxiliar);
                } else {
                    noAuxiliar.getGenitor().setDireita(noAuxiliar);
                }
            } else {
                raiz = noAuxiliar;
            }

            noAuxiliar = noAuxiliar.getGenitor();

        }            
    }
    //fim métodos AVL

    /**
     * Insere um novo elemento na árvore AVL.
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
            while (noAuxiliar != null) {
                if (dado.compareTo(noAuxiliar.getDado()) < 0) {
                    //preciso ir para a esquerda
                    if (noAuxiliar.getEsquerda() != null) {
                        noAuxiliar = noAuxiliar.getEsquerda();
                    } else {
                        //insiro o dado aqui
                        noAuxiliar.setEsquerda(novoNo);
                        novoNo.setGenitor(noAuxiliar);
                        break;
                    }
                } else {
                    //preciso ir para a direita
                    if (noAuxiliar.getDireita() != null) {
                        noAuxiliar = noAuxiliar.getDireita();
                    } else {
                        //insiro o dado aqui
                        noAuxiliar.setDireita(novoNo);
                        novoNo.setGenitor(noAuxiliar);
                        break;
                    }
                }
            }
            //rebalancear arvore
            rebalancear(dado, novoNo);
        }
    }

    /**
     * Remove um elemento da árvore AVL.
     * @param dado Elemento a ser removido.
     * @return Elemento removido, ou null se não encontrado.
     */
    @Override
    public T apagar(T dado) {
        NoTriplo<T> noAuxiliar = buscar(dado);
        // Nó não encontrado na árvore
        if (noAuxiliar == null)   
            return null;

        // Guardar referência ao pai antes da remoção
        NoTriplo<T> pai = noAuxiliar.getGenitor();

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

        //rebalancear arvore a partir do pai do nó removido
        if (pai != null) {
            rebalancear(dado, pai);
        }
        
        return dado;
    }    

    /**
     * Busca um nó pelo valor na árvore.
     * @param dado Valor a ser buscado.
     * @return Nó correspondente ou null se não encontrado.
     */
    private NoTriplo<T> buscar(T dado) {
        NoTriplo<T> noAuxiliar = raiz;
        while (noAuxiliar != null) {
            if (dado.equals(noAuxiliar.getDado())) {
                return noAuxiliar;
            } else {
                if (dado.compareTo(noAuxiliar.getDado()) < 0)
                    noAuxiliar = noAuxiliar.getEsquerda();
                else
                    noAuxiliar = noAuxiliar.getDireita();
            }
        }
        return null;
    }

    /**
     * Remove um nó folha da árvore.
     * @param nodo Nó folha a ser removido.
     */
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

            nodo.setGenitor(null);                
        }
    }

    /**
     * Remove um nó com um único filho.
     * @param nodo Nó a ser removido.
     */
    private void apagarComUmFilho(NoTriplo<T> nodo) {
        NoTriplo<T> avo = nodo.getGenitor();
        NoTriplo<T> neto = (nodo.getEsquerda() != null ? nodo.getEsquerda() : nodo.getDireita());        
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

    /**
     * Remove um nó com dois filhos, substituindo pelo sucessor.
     * @param nodo Nó a ser removido.
     */
    private void apagarComDoisFilhos(NoTriplo<T> nodo) {
        //sucessor pode ser o menor a direita ou o maior a esquerda
        NoTriplo<T> sucessor = encontraMenorDireita(nodo);
        //NoTriplo<T> sucessor = encontraMaiorEsquerda(nodo);
        
        //troca conteúdo do nó com o menor nó a direita
        T temp = nodo.getDado();
        nodo.setDado(sucessor.getDado());
        sucessor.setDado(temp);

        //remove o menor a direita (que agora contém o dado original)
        if (sucessor.getEsquerda() == null && 
        sucessor.getDireita() == null) {
            apagarNoFolha(sucessor);
        } else {
            apagarComUmFilho(sucessor);
        }
    } 

    /**
     * Encontra o menor nó à direita de um dado nó.
     * @param nodo Nó de referência.
     * @return Menor nó à direita.
     */
    private NoTriplo<T> encontraMenorDireita(NoTriplo<T> nodo) {
        NoTriplo<T> sucessor = nodo.getDireita();
        while (sucessor.getEsquerda() != null)
            sucessor = sucessor.getEsquerda();

        return sucessor;
    }  

    /**
     * Encontra o maior nó à esquerda de um dado nó.
     * @param nodo Nó de referência.
     * @return Maior nó à esquerda.
     */
    private NoTriplo<T> encontraMaiorEsquerda(NoTriplo<T> nodo) {
        NoTriplo<T> sucessor = nodo.getEsquerda();
        while (sucessor.getDireita() != null)
            sucessor = sucessor.getDireita();

        return sucessor;
    } 

    /**
     * Verifica se um elemento existe na árvore AVL.
     * @param dado Elemento a ser buscado.
     * @return true se existe, false caso contrário.
     */
    @Override
    public boolean existe(T dado) {
        boolean retorno = false;
        NoTriplo<T> noAuxiliar = raiz;
        while (noAuxiliar != null) {
            if (dado.equals(noAuxiliar.getDado())) {
                retorno = true;
                break;
            } else {
                if (dado.compareTo(noAuxiliar.getDado()) < 0)
                    noAuxiliar = noAuxiliar.getEsquerda();
                else
                    noAuxiliar = noAuxiliar.getDireita();
            }
        }
        return retorno;
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

    /**
     * Auxiliar para imprimir elementos em pré-ordem recursivamente.
     * @param raiz Nó atual.
     * @return String com elementos em pré-ordem.
     */
    private String imprimirPreOrdemRec(NoTriplo<T> raiz) {
        String resultado = "";
        if (raiz != null) {
            resultado = raiz.getDado() + " " + 
                imprimirPreOrdemRec(raiz.getEsquerda()) +  " " +
                imprimirPreOrdemRec(raiz.getDireita());
        }
        return resultado;
    }

    /**
     * Auxiliar para imprimir elementos em ordem recursivamente.
     * @param raiz Nó atual.
     * @return String com elementos em ordem.
     */
    private String imprimirEmOrdemRec(NoTriplo<T> raiz) {
        String resultado = "";        
        if (raiz != null) {
            resultado = imprimirEmOrdemRec(raiz.getEsquerda()) + " " + 
            raiz.getDado() + " " +
            imprimirEmOrdemRec(raiz.getDireita());
        }
        return resultado;       
    }

    /**
     * Auxiliar para imprimir elementos em pós-ordem recursivamente.
     * @param raiz Nó atual.
     * @return String com elementos em pós-ordem.
     */
    private String imprimirPosOrdemRec(NoTriplo<T> raiz) {
        String resultado = "";        
        if (raiz != null) {
            resultado = imprimirPosOrdemRec(raiz.getEsquerda()) + " " +
                imprimirPosOrdemRec(raiz.getDireita()) +  " " +
                raiz.getDado();
        }
        return resultado;            
    }

    /**
     * Formata a saída dos métodos de impressão, removendo espaços extras e ajustando separadores.
     * @param msg String a ser formatada.
     * @return String formatada.
     */
    private String formataSaida(String msg) {
        String resultado;
        do {
            resultado = msg;
            msg = msg.replace("  ", " "); //remove excesso de espaços
        } while (!msg.equals(resultado));
        msg = msg.trim(); //remove espaços em branco do inicio e fim, se existir
        msg = msg.replace(" ", ","); //troca espaço por vírgula
        return "[" + msg + "]";
    }    
}