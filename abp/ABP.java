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

    @Override
    public String imprimirPreOrdem(NoTriplo<T> raiz) {
        String resultado = "";
        if (raiz != null) {
            resultado = raiz.getDado() + " " + 
                imprimirPreOrdem(raiz.getEsquerda()) +  " " +
                imprimirPreOrdem(raiz.getDireita());
        }
        return resultado;
    }

    @Override
    public String imprimirEmOrdem(NoTriplo<T> raiz) {
        String resultado = "";        
        if (raiz != null) {
            resultado = imprimirEmOrdem(raiz.getEsquerda()) + " " + 
            raiz.getDado() + " " +
            imprimirEmOrdem(raiz.getDireita());
        }
        return resultado;       
    }

    @Override
    public String imprimirPosOrdem(NoTriplo<T> raiz) {
        String resultado = "";        
        if (raiz != null) {
            resultado = imprimirPosOrdem(raiz.getEsquerda()) + " " +
                imprimirPosOrdem(raiz.getDireita()) +  " " +
                raiz.getDado();
        }
        return resultado;            
    }

    @Override
    public NoTriplo<T> apagar(NoTriplo<T> raiz, T dado) {
        NoTriplo<T> noAuxiliar = null;
        if (raiz != null) {
            if (dado.equals(raiz.getDado())) {
                //remover
            } else {
                if ((Integer) dado < (Integer) raiz.getDado())
                    raiz.setEsquerda(apagar(raiz.getEsquerda(), dado));
                else
                    raiz.setDireita(apagar(raiz.getDireita(), dado));
            }
        }
        return noAuxiliar;
    }

    @Override
    public boolean existe(T dado) {
        return false;
    }

    @Override
    public NoTriplo<T> buscar(T dado) {
        return null;
    }  
}