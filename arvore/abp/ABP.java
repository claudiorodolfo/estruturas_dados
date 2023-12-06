public class ABP<T> implements Arborizavel<T> {

    private NoTriplo<T> raiz;

    public ABP() {
        raiz = null;
    }

    @Override    
    public NoTriplo<T> getRaiz() {
        return raiz;
    }

    @Override
    public void limpar() {
        raiz = null;
    }

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
                        //insiro o dado aq
                        noAuxiliar.setEsquerda(novoNo);
                        novoNo.setGenitor(noAuxiliar);
                        break;
                    }
                } else {
                    //preciso ir para a direita
                    if (noAuxiliar.getDireita() != null) {
                        noAuxiliar = noAuxiliar.getDireita();
                    } else {
                        //insiro o dado q
                        noAuxiliar.setDireita(novoNo);
                        novoNo.setGenitor(noAuxiliar);
                        break;
                    }
                }
            }
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

        return dado;
    }    

    private NoTriplo<T> buscar(T dado) {
        NoTriplo<T> noAuxiliar = raiz;
        while (noAuxiliar != null) {
            if (dado.equals(noAuxiliar.getDado())) {
                return noAuxiliar;
            } else {
                if ((Integer) dado < (Integer) noAuxiliar.getDado())
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

        // Remove o menor a direita (que agora contém o dado original)
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
                if ((Integer) dado < (Integer) noAuxiliar.getDado())
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