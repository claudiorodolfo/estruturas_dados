public class AVL<T> implements Arborizavel<T> {

    private NoTriplo<T> raiz;

    public AVL() {
        raiz = null;
    }

    @Override    
    public NoTriplo<T> getRaiz() {
        return raiz;
    }

    //métodos AVL
    private int balanceamento(NoTriplo<T> nodo) {
        //se um noFolha tem altura zero, então 
        //a ausencia de nó (null), tem altura -1
        int alturaEsquerda = nodo.getEsquerda() != null ? nodo.getEsquerda().getAltura(): -1;
        int alturaDireita = nodo.getDireita() != null ? nodo.getDireita().getAltura(): -1;

        return alturaEsquerda - alturaDireita;
    }

    private void atualizaAltura(NoTriplo<T> nodo) {
        //se um noFolha tem altura zero, então
        //a ausencia de nó (null), tem altura -1        
        int alturaEsquerda = nodo.getEsquerda() != null ? nodo.getEsquerda().getAltura(): -1;
        int alturaDireita = nodo.getDireita() != null ? nodo.getDireita().getAltura(): -1;
        //noFolha tem altura zero = 1 + (-1)
        nodo.setAltura(1 + 
                Math.max(alturaEsquerda, alturaDireita));
    }

    // Método para rotacionar à direita ao redor de um nó
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

    //rotacionar à esquerda ao redor de um nó
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

    //rebalancear a árvore após inserção ou remoção
    private void rebalancear(T dado, NoTriplo<T> noAuxiliar) {
        //NoTriplo<T> referencia = noAuxiliar;
        while (noAuxiliar != null) {

            atualizaAltura(noAuxiliar);
            int desnivel = balanceamento(noAuxiliar);
             
            // Caso 1: Rotação à direita
            if (desnivel > 1 && (Integer) dado < (Integer) noAuxiliar.getEsquerda().getDado()) {
                noAuxiliar = rotacaoDireita(noAuxiliar);
            }
            // Caso 2: Rotação à esquerda
            else if (desnivel < -1 && (Integer) dado > (Integer) noAuxiliar.getDireita().getDado()) {
                noAuxiliar = rotacaoEsquerda(noAuxiliar);
            }
            // Caso 3: Rotação dupla a direita
            //Rotação à esquerda-direita
            else if (desnivel > 1 && (Integer) dado > (Integer) noAuxiliar.getEsquerda().getDado()) {
                noAuxiliar.setEsquerda(rotacaoEsquerda(noAuxiliar.getEsquerda()));
                noAuxiliar = rotacaoDireita(noAuxiliar);
            }
            // Caso 4: Rotação dupla a esquerda
            //Rotação à direita-esquerda
            else if (desnivel < -1 && (Integer) dado < (Integer) noAuxiliar.getDireita().getDado()) {
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

    //inserir
    @Override
    public void inserir(T dado) {
        NoTriplo<T> novoNo = new NoTriplo<>();
        novoNo.setDado(dado);
        if (raiz == null) {
            raiz = novoNo;
        } else {
            NoTriplo<T> noAuxiliar = raiz;
            while (noAuxiliar != null) {
                if ((Integer) dado < (Integer) noAuxiliar.getDado()) {
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

    //apagar
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

        //rebalancear arvore
        rebalancear(dado, noAuxiliar);        
        
        return dado;
    }    

    private NoTriplo<T> buscar(T dado) {
        NoTriplo<T> noAuxiliar = raiz;
        while (noAuxiliar != null) {
            if (dado.equals(noAuxiliar.getDado())) {
                return noAuxiliar;
            } else {
                if ((Integer) dado < (Integer) raiz.getDado())
                    noAuxiliar = noAuxiliar.getEsquerda();
                else
                    noAuxiliar = noAuxiliar.getDireita();
            }
        }
        return null;
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

            nodo.setGenitor(null);                
        }
    }

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

    private NoTriplo<T> encontraMenorDireita(NoTriplo<T> nodo) {
        NoTriplo<T> sucessor = nodo.getDireita();
        while (sucessor.getEsquerda() != null)
            sucessor = sucessor.getEsquerda();

        return sucessor;
    }  

    private NoTriplo<T> encontraMaiorEsquerda(NoTriplo<T> nodo) {
        NoTriplo<T> sucessor = nodo.getEsquerda();
        while (sucessor.getDireita() != null)
            sucessor = sucessor.getDireita();

        return sucessor;
    } 

    //existe
    @Override
    public boolean existe(T dado) {
        boolean retorno = false;
        NoTriplo<T> noAuxiliar = raiz;
        while (noAuxiliar != null) {
            if (dado.equals(noAuxiliar.getDado())) {
                retorno = true;
                break;
            } else {
                if ((Integer) dado < (Integer) raiz.getDado())
                    noAuxiliar = noAuxiliar.getEsquerda();
                else
                    noAuxiliar = noAuxiliar.getDireita();
            }
        }
        return retorno;
    }
    
    //imprimir
    @Override
    public String imprimirPreOrdem() {
        return formataSaida(imprimirPreOrdemRec(raiz));
    }

    @Override
    public String imprimirEmOrdem() {
        return formataSaida(imprimirEmOrdemRec(raiz));
    }

    @Override
    public String imprimirPosOrdem() {
        return formataSaida(imprimirPosOrdemRec(raiz));
    }

    private String imprimirPreOrdemRec(NoTriplo<T> raiz) {
        String resultado = "";
        if (raiz != null) {
            resultado = raiz.getDado() + " " + 
                imprimirPreOrdemRec(raiz.getEsquerda()) +  " " +
                imprimirPreOrdemRec(raiz.getDireita());
        }
        return resultado;
    }

    private String imprimirEmOrdemRec(NoTriplo<T> raiz) {
        String resultado = "";        
        if (raiz != null) {
            resultado = imprimirEmOrdemRec(raiz.getEsquerda()) + " " + 
            raiz.getDado() + " " +
            imprimirEmOrdemRec(raiz.getDireita());
        }
        return resultado;       
    }

    private String imprimirPosOrdemRec(NoTriplo<T> raiz) {
        String resultado = "";        
        if (raiz != null) {
            resultado = imprimirPosOrdemRec(raiz.getEsquerda()) + " " +
                imprimirPosOrdemRec(raiz.getDireita()) +  " " +
                raiz.getDado();
        }
        return resultado;            
    }

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