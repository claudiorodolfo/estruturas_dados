public class ArvoreBinariaHeapGenericoMaximo<T> implements Amontoavel<T> {

	private NoTriplo<T> ponteiroRaiz;
	private NoTriplo<T> ponteiroFim;
	private int quantidade;
    private int tamanho;

    public ArvoreBinariaHeapGenericoMaximo(int tamanho) {
	    ponteiroRaiz = null;
	    ponteiroFim = null;       
		quantidade = 0;        
        this.tamanho = tamanho;      
    }

    public ArvoreBinariaHeapGenericoMaximo() {
		this(10);
    }

	@Override
    public void inserir(T dado) {
        if (!estaCheia()) {
            NoTriplo<T> novoNo = new NoTriplo<>();
            novoNo.setDado(dado);

            if (!estaVazia()) {
                NoTriplo<T> noInserido = inserirRecursivo(ponteiroRaiz, novoNo);
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

    private NoTriplo<T> inserirRecursivo(NoTriplo<T> noAtual, NoTriplo<T> novoNo) {
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

    private void ajustarAcima(NoTriplo<T> no) {
        while (no.getGenitor() != null) {
            if ((Integer) no.getDado() > (Integer)(no.getGenitor()).getDado()) {
                trocar(no, no.getGenitor());
                no = no.getGenitor();
            } else {
                break;
            }
        }
    }
	
	private void trocar(NoTriplo<T> a, NoTriplo<T> b) {
		T aux = a.getDado();
		a.setDado(b.getDado());
		b.setDado(aux);
	}

	@Override
    public T extrair() {
		T dadoRaiz = null;
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

    private T removerUltimoNo() {
        T dadoUltimo = ponteiroFim.getDado();
        NoTriplo<T> ponteiroAux = ponteiroFim;
        
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

    private void ajustarAbaixo(NoTriplo<T> no) {
        while (true) {
            NoTriplo<T> maiorFilho = obterMaiorFilho(no);

            if (maiorFilho == null || (Integer) no.getDado() >= (Integer) maiorFilho.getDado()) {
                break;
            }

            trocar(no, maiorFilho);
            no = maiorFilho;
        }
    }

    private NoTriplo<T> obterMaiorFilho(NoTriplo<T> no) {
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
    public T obterRaiz() {
        T raizAux = null;
        if (!estaVazia()) {
            raizAux = ponteiroRaiz.getDado();
        } else {
			System.err.println("Heap is Empty!");
		}        
        return raizAux;
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