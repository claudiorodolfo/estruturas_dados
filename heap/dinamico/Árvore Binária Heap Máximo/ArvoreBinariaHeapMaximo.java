public class ArvoreBinariaHeapMaximo implements Amontoavel {

	private NoTriplo ponteiroRaiz;
	private NoTriplo ponteiroFim;
	private int quantidade;
    private int tamanho;

    public ArvoreBinariaHeapMaximo(int tamanho) {
	    ponteiroRaiz = null;
	    ponteiroFim = null;       
		quantidade = 0;        
        this.tamanho = tamanho;      
    }

    public ArvoreBinariaHeapMaximo() {
		this(10);
    }

	@Override
    public Object obterRaiz() {
        Object raizAux = null;
        if (!estaVazia()) {
            raizAux = ponteiroRaiz.getDado();
        } else {
			System.err.println("Heap is Empty!");
		}        
        return raizAux;
    }

	@Override
    public void inserir(Object dado) {
        if (!estaCheia()) {
            NoTriplo novoNo = new NoTriplo();
            novoNo.setDado(dado);

            if (!estaVazia()) {
                NoTriplo noInserido = inserirRecursivo(ponteiroRaiz, novoNo);
                ajustarAcima(noInserido);
            } else {
                ponteiroRaiz = novoNo;
            }
            
            ponteiroFim = novoNo;
            quantidade++;
        } else {
            System.err.println("Heap is full!");
        }
    }

    private NoTriplo inserirRecursivo(NoTriplo noAtual, NoTriplo novoNo) {
        if (noAtual.getEsquerda() == null) {
            noAtual.setEsquerda(novoNo);
            novoNo.setGenitor(noAtual);
            return novoNo;
        } else if (noAtual.getDireita() == null) {
            noAtual.setDireita(novoNo);
            novoNo.setGenitor(noAtual);
            return novoNo;
        } else {
            // Escolher um lado (esquerda ou direita) para continuar a inserção
            if (Math.random() < 0.5) {
                return inserirRecursivo(noAtual.getEsquerda(), novoNo);
            } else {
                return inserirRecursivo(noAtual.getDireita(), novoNo);
            }
        }
    }

    private void ajustarAcima(NoTriplo no) {
        while (no.getGenitor() != null) {
            if ((Integer) no.getDado() > (Integer)(no.getGenitor()).getDado()) {
                trocar(no, no.getGenitor());
                no = no.getGenitor();
            } else {
                break;
            }
        }
    }
	
	private void trocar(NoTriplo a, NoTriplo b) {
		Object aux = a.getDado();
		a.setDado(b.getDado());
		b.setDado(aux);
	}

	@Override
    public Object extrair() {
		Object dadoRaiz = null;
        if (!estaVazia()) {
			dadoRaiz = ponteiroRaiz.getDado();
            ponteiroRaiz.setDado(removerUltimoNo());
            ajustarAbaixo(ponteiroRaiz);
            quantidade--;
        } else {
			System.err.println("Heap is Empty!");
		}
        return dadoRaiz;
    }

    private Object removerUltimoNo() {
        Object dadoUltimo = ponteiroFim.getDado();
        NoTriplo ponteiroAux = ponteiroFim;
        
        ponteiroFim = ponteiroFim.getGenitor();
        //se o ponteiroFim estiver na esquerda
        if (ponteiroAux.equals(ponteiroFim.getEsquerda())) {
            ponteiroFim.setEsquerda(null);
        //se o ponteiroFim estiver na direita
        } else {
            ponteiroFim.setDireita(null);
        }
        //seta o filho antes como null
        return dadoUltimo;
    }

    private void ajustarAbaixo(NoTriplo no) {
        while (true) {
            NoTriplo maiorFilho = obterMaiorFilho(no);

            if (maiorFilho == null || (Integer) no.getDado() >= (Integer) maiorFilho.getDado()) {
                break;
            }

            trocar(no, maiorFilho);
            no = maiorFilho;
        }
    }

    private NoTriplo obterMaiorFilho(NoTriplo no) {
        if (no.getEsquerda() == null && no.getDireita() == null) {
            return null;
        } else if (no.getDireita() == null) {
            return no.getEsquerda();
        } else if (no.getEsquerda() == null) {
            return no.getDireita();
        } else {
            return ((Integer) (no.getEsquerda()).getDado() > (Integer) no.getDireita().getDado()) ? no.getEsquerda() : no.getDireita();
        }
    }

	@Override
    public boolean estaVazia() {
        return (quantidade == 0);
    }

	@Override
    public boolean estaCheia() {
        return (quantidade == tamanho);
    }

	@Override	
    public String imprimir() {
        String resultado = "";
        resultado += imprimirRecursivo(ponteiroRaiz);
        return resultado;

    }

    private String imprimirRecursivo(NoTriplo no) {
        String resultado = "";
        if (no != null) {
            imprimirRecursivo(no.getEsquerda());
            resultado += no.getDado() + " ";
            imprimirRecursivo(no.getDireita());
        }
        return resultado;
    }
}