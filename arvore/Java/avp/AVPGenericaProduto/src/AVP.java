/**
 * Implementação de uma Árvore Vermelho e Preto (AVP).
 * Mantém as propriedades da árvore vermelho e preto durante inserção e remoção.
 * 
 * @param <T> Tipo dos dados armazenados na árvore.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class AVP<T extends Comparable<T>> implements Arborizavel<T> {

    private NoTriplo<T> raiz;
    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    /**
     * Cria uma árvore vermelho e preto vazia.
     */
    public RBT() {
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
        novoNo.setVermelho(VERMELHO); // Novos nós são sempre vermelhos

        if (raiz == null) {
            raiz = novoNo;
            raiz.setVermelho(PRETO); // Raiz sempre preta
        } else {
            inserirRecursivo(raiz, novoNo);
            balancearAposInserir(novoNo);
        }
    }

    /**
     * Insere um nó na posição correta da árvore.
     */
    private void inserirRecursivo(NoTriplo<T> atual, NoTriplo<T> novoNo) {
        int comparacao = novoNo.getDado().compareTo(atual.getDado());
        
        if (comparacao <= 0) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(novoNo);
                novoNo.setGenitor(atual);
            } else {
                inserirRecursivo(atual.getEsquerda(), novoNo);
            }
        } else {
            if (atual.getDireita() == null) {
                atual.setDireita(novoNo);
                novoNo.setGenitor(atual);
            } else {
                inserirRecursivo(atual.getDireita(), novoNo);
            }
        }
    }

    /**
     * Balanceia a árvore após inserção para manter as propriedades RBT.
     */
    private void balancearAposInserir(NoTriplo<T> no) {
        NoTriplo<T> pai = no.getGenitor();
        
        // Caso 1: Nó é a raiz
        if (pai == null) {
            no.setVermelho(PRETO);
            return;
        }

        // Caso 2: Pai é preto
        if (pai.isPreto()) {
            return;
        }

        // Caso 3: Pai é vermelho
        NoTriplo<T> avo = pai.getGenitor();
        NoTriplo<T> tio = (pai == avo.getEsquerda()) ? avo.getDireita() : avo.getEsquerda();

        if (tio != null && tio.isVermelho()) {
            // Caso 3a: Tio é vermelho
            pai.setVermelho(PRETO);
            tio.setVermelho(PRETO);
            avo.setVermelho(VERMELHO);
            balancearAposInserir(avo);
        } else {
            // Caso 3b: Tio é preto ou null
            if (pai == avo.getEsquerda() && no == pai.getDireita()) {
                // Rotação esquerda-direita
                rotacaoEsquerda(pai);
                no = pai;
                pai = no.getGenitor();
            } else if (pai == avo.getDireita() && no == pai.getEsquerda()) {
                // Rotação direita-esquerda
                rotacaoDireita(pai);
                no = pai;
                pai = no.getGenitor();
            }

            // Rotação simples
            if (pai == avo.getEsquerda()) {
                rotacaoDireita(avo);
            } else {
                rotacaoEsquerda(avo);
            }
            
            pai.setVermelho(PRETO);
            avo.setVermelho(VERMELHO);
        }
    }

    /**
     * Rotação à esquerda.
     */
    private void rotacaoEsquerda(NoTriplo<T> x) {
        NoTriplo<T> y = x.getDireita();
        x.setDireita(y.getEsquerda());
        
        if (y.getEsquerda() != null) {
            y.getEsquerda().setGenitor(x);
        }
        
        y.setGenitor(x.getGenitor());
        
        if (x.getGenitor() == null) {
            raiz = y;
        } else if (x == x.getGenitor().getEsquerda()) {
            x.getGenitor().setEsquerda(y);
        } else {
            x.getGenitor().setDireita(y);
        }
        
        y.setEsquerda(x);
        x.setGenitor(y);
    }

    /**
     * Rotação à direita.
     */
    private void rotacaoDireita(NoTriplo<T> y) {
        NoTriplo<T> x = y.getEsquerda();
        y.setEsquerda(x.getDireita());
        
        if (x.getDireita() != null) {
            x.getDireita().setGenitor(y);
        }
        
        x.setGenitor(y.getGenitor());
        
        if (y.getGenitor() == null) {
            raiz = x;
        } else if (y == y.getGenitor().getDireita()) {
            y.getGenitor().setDireita(x);
        } else {
            y.getGenitor().setEsquerda(x);
        }
        
        x.setDireita(y);
        y.setGenitor(x);
    }

    /**
     * Remove um elemento da árvore.
     * @param dado Elemento a ser removido.
     * @return Elemento removido, ou null se não encontrado.
     */
    @Override
    public T apagar(T dado) {
        NoTriplo<T> no = buscar(dado);
        if (no == null) {
            return null;
        }

        T valorRemovido = no.getDado();
        removerNo(no);
        return valorRemovido;
    }

    /**
     * Remove um nó da árvore.
     */
    private void removerNo(NoTriplo<T> no) {
        NoTriplo<T> filho = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();
        
        if (filho != null) {
            // Nó tem um filho
            if (no.getGenitor() == null) {
                raiz = filho;
                filho.setVermelho(PRETO);
            } else {
                if (no == no.getGenitor().getEsquerda()) {
                    no.getGenitor().setEsquerda(filho);
                } else {
                    no.getGenitor().setDireita(filho);
                }
                filho.setGenitor(no.getGenitor());
                
                if (no.isPreto()) {
                    balancearAposRemover(filho);
                }
            }
        } else {
            // Nó é folha
            if (no.getGenitor() == null) {
                raiz = null;
            } else {
                if (no.isPreto()) {
                    balancearAposRemover(no);
                }
                
                if (no.getGenitor() != null) {
                    if (no == no.getGenitor().getEsquerda()) {
                        no.getGenitor().setEsquerda(null);
                    } else {
                        no.getGenitor().setDireita(null);
                    }
                }
            }
        }
    }

    /**
     * Balanceia a árvore após remoção.
     */
    private void balancearAposRemover(NoTriplo<T> no) {
        while (no != raiz && no.isPreto()) {
            NoTriplo<T> pai = no.getGenitor();
            boolean noEsquerda = (no == pai.getEsquerda());
            NoTriplo<T> irmao = noEsquerda ? pai.getDireita() : pai.getEsquerda();

            if (irmao != null && irmao.isVermelho()) {
                irmao.setVermelho(PRETO);
                pai.setVermelho(VERMELHO);
                if (noEsquerda) {
                    rotacaoEsquerda(pai);
                } else {
                    rotacaoDireita(pai);
                }
                irmao = noEsquerda ? pai.getDireita() : pai.getEsquerda();
            }

            if (irmao == null || 
                (irmao.getEsquerda() == null || irmao.getEsquerda().isPreto()) &&
                (irmao.getDireita() == null || irmao.getDireita().isPreto())) {
                
                if (irmao != null) {
                    irmao.setVermelho(VERMELHO);
                }
                no = pai;
            } else {
                if (noEsquerda) {
                    if (irmao.getDireita() == null || irmao.getDireita().isPreto()) {
                        if (irmao.getEsquerda() != null) {
                            irmao.getEsquerda().setVermelho(PRETO);
                        }
                        irmao.setVermelho(VERMELHO);
                        rotacaoDireita(irmao);
                        irmao = pai.getDireita();
                    }
                    if (irmao.getDireita() != null) {
                        irmao.getDireita().setVermelho(PRETO);
                    }
                } else {
                    if (irmao.getEsquerda() == null || irmao.getEsquerda().isPreto()) {
                        if (irmao.getDireita() != null) {
                            irmao.getDireita().setVermelho(PRETO);
                        }
                        irmao.setVermelho(VERMELHO);
                        rotacaoEsquerda(irmao);
                        irmao = pai.getEsquerda();
                    }
                    if (irmao.getEsquerda() != null) {
                        irmao.getEsquerda().setVermelho(PRETO);
                    }
                }
                
                irmao.setVermelho(pai.isVermelho());
                pai.setVermelho(PRETO);
                if (noEsquerda) {
                    rotacaoEsquerda(pai);
                } else {
                    rotacaoDireita(pai);
                }
                no = raiz;
            }
        }
        no.setVermelho(PRETO);
    }

    /**
     * Busca um elemento na árvore.
     */
    private NoTriplo<T> buscar(T dado) {
        NoTriplo<T> atual = raiz;
        while (atual != null) {
            int comparacao = dado.compareTo(atual.getDado());
            if (comparacao == 0) {
                return atual;
            } else if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }
        return null;
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
     * Percorre a árvore em pré-ordem recursivamente.
     */
    private String imprimirPreOrdemRec(NoTriplo<T> no) {
        if (no == null) return "";
        return no.getDado() + " " + 
               imprimirPreOrdemRec(no.getEsquerda()) + " " +
               imprimirPreOrdemRec(no.getDireita());
    }

    /**
     * Percorre a árvore em ordem recursivamente.
     */
    private String imprimirEmOrdemRec(NoTriplo<T> no) {
        if (no == null) return "";
        return imprimirEmOrdemRec(no.getEsquerda()) + " " +
               no.getDado() + " " +
               imprimirEmOrdemRec(no.getDireita());
    }

    /**
     * Percorre a árvore em pós-ordem recursivamente.
     */
    private String imprimirPosOrdemRec(NoTriplo<T> no) {
        if (no == null) return "";
        return imprimirPosOrdemRec(no.getEsquerda()) + " " +
               imprimirPosOrdemRec(no.getDireita()) + " " +
               no.getDado();
    }

    /**
     * Formata a saída removendo espaços extras.
     */
    private String formataSaida(String msg) {
        return msg.replaceAll("\\s+", " ").trim();
    }
} 