/**
 * Implementação de uma Árvore Vermelho e Preto (AVP) genérica.
 * Mantém as propriedades da árvore vermelho e preto durante inserção e remoção.
 * <p>
 * Esta estrutura garante altura logarítmica e operações eficientes de busca, inserção e remoção.
 * </p>
 *
 * @param <T> Tipo dos dados armazenados na árvore. Deve implementar Comparable.
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since July 1, 2025
 */
public class AVP<T extends Comparable<T>> implements Arborizavel<T> {

    private NoTriplo<T> raiz;

    /**
     * Cria uma árvore vermelho e preto vazia.
     */
    public AVP() {
        raiz = null;
    }

    /**
     * Retorna o nó raiz da árvore.
     * @return Nó raiz ou null se a árvore estiver vazia.
     */
    @Override
    public NoTriplo<T> getRaiz() {
        return raiz;
    }

    /**
     * Remove todos os elementos da árvore, tornando-a vazia.
     */
    @Override
    public void limpar() {
        raiz = null;
    }

    /**
     * Insere um novo elemento na árvore.
     * @param dado Elemento a ser inserido.
     * <p>
     * Não insere duplicados: se o elemento já existir, a operação é ignorada.
     * </p>
     */
    @Override
    public void inserir(T dado) {
        if (buscar(dado) != null) {
            return; // Não insere duplicados
        }
        NoTriplo<T> novoNo = new NoTriplo<>();
        novoNo.setDado(dado);
        novoNo.setCor(Cor.VERMELHO); // Novos nós são sempre vermelhos

        if (raiz == null) {
            raiz = novoNo;
            raiz.setCor(Cor.PRETO); // Raiz sempre preta
        } else {
            inserirRecursivo(raiz, novoNo);
            balancearAposInserir(novoNo);
        }
    }

    /**
     * Insere um nó na posição correta da árvore.
     * @param atual Nó atual durante a recursão.
     * @param novoNo Novo nó a ser inserido.
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
     * Balanceia a árvore após inserção para manter as propriedades AVP.
     * @param no Nó recém-inserido que pode ter causado violação das propriedades.
     */
    private void balancearAposInserir(NoTriplo<T> no) {
        NoTriplo<T> pai = no.getGenitor();
        
        // Caso 1: Nó é a raiz
        if (pai == null) {
            no.setCor(Cor.PRETO);
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
            pai.setCor(Cor.PRETO);
            tio.setCor(Cor.PRETO);
            avo.setCor(Cor.VERMELHO);
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
            
            pai.setCor(Cor.PRETO);
            avo.setCor(Cor.VERMELHO);
        }
    }

    /**
     * Realiza rotação à esquerda em torno de um nó.
     * @param x Nó em torno do qual a rotação será feita.
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
     * Realiza rotação à direita em torno de um nó.
     * @param y Nó em torno do qual a rotação será feita.
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
     * Remove um nó da árvore, realizando balanceamento se necessário.
     * @param no Nó a ser removido.
     */
    private void removerNo(NoTriplo<T> no) {
        if (no.getEsquerda() != null && no.getDireita() != null) {
            // Nó com dois filhos: encontrar sucessor, copiar valor e remover sucessor
            NoTriplo<T> sucessor = no.getDireita();
            while (sucessor.getEsquerda() != null) {
                sucessor = sucessor.getEsquerda();
            }
            no.setDado(sucessor.getDado());
            removerNo(sucessor);
            return;
        }
        NoTriplo<T> filho = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();
        if (filho != null) {
            // Nó tem um filho
            if (no.getGenitor() == null) {
                raiz = filho;
                filho.setCor(Cor.PRETO);
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
     * Realiza balanceamento após remoção para manter as propriedades AVP.
     * @param no Nó a partir do qual o balanceamento é feito.
     */
    private void balancearAposRemover(NoTriplo<T> no) {
        while (no != raiz && no.isPreto()) {
            NoTriplo<T> pai = no.getGenitor();
            boolean noEsquerda = (no == pai.getEsquerda());
            NoTriplo<T> irmao = noEsquerda ? pai.getDireita() : pai.getEsquerda();

            if (irmao != null && irmao.isVermelho()) {
                irmao.setCor(Cor.PRETO);
                pai.setCor(Cor.VERMELHO);
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
                    irmao.setCor(Cor.VERMELHO);
                }
                no = pai;
            } else {
                if (noEsquerda) {
                    if (irmao.getDireita() == null || irmao.getDireita().isPreto()) {
                        if (irmao.getEsquerda() != null) {
                            irmao.getEsquerda().setCor(Cor.PRETO);
                        }
                        irmao.setCor(Cor.VERMELHO);
                        rotacaoDireita(irmao);
                        irmao = pai.getDireita();
                    }
                    if (irmao.getDireita() != null) {
                        irmao.getDireita().setCor(Cor.PRETO);
                    }
                } else {
                    if (irmao.getEsquerda() == null || irmao.getEsquerda().isPreto()) {
                        if (irmao.getDireita() != null) {
                            irmao.getDireita().setCor(Cor.PRETO);
                        }
                        irmao.setCor(Cor.VERMELHO);
                        rotacaoEsquerda(irmao);
                        irmao = pai.getEsquerda();
                    }
                    if (irmao.getEsquerda() != null) {
                        irmao.getEsquerda().setCor(Cor.PRETO);
                    }
                }
                
                irmao.setCor(pai.isVermelho() ? Cor.VERMELHO : Cor.PRETO);
                pai.setCor(Cor.PRETO);
                if (noEsquerda) {
                    rotacaoEsquerda(pai);
                } else {
                    rotacaoDireita(pai);
                }
                no = raiz;
            }
        }
        no.setCor(Cor.PRETO);
    }

    /**
     * Busca um nó pelo valor na árvore.
     * @param dado Valor a ser buscado.
     * @return Nó correspondente ou null se não encontrado.
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
     * @return Elementos em pré-ordem, separados por vírgula.
     */
    @Override
    public String imprimirPreOrdem() {
        return formataSaida(imprimirPreOrdemRec(raiz));
    }

    /**
     * Retorna uma String com os elementos em ordem.
     * @return Elementos em ordem, separados por vírgula.
     */
    @Override
    public String imprimirEmOrdem() {
        return formataSaida(imprimirEmOrdemRec(raiz));
    }

    /**
     * Retorna uma String com os elementos em pós-ordem.
     * @return Elementos em pós-ordem, separados por vírgula.
     */
    @Override
    public String imprimirPosOrdem() {
        return formataSaida(imprimirPosOrdemRec(raiz));
    }

    /**
     * Auxiliar para imprimir elementos em pré-ordem recursivamente.
     * @param no Nó atual.
     * @return String com elementos em pré-ordem.
     */
    private String imprimirPreOrdemRec(NoTriplo<T> no) {
        if (no == null) return "";
        return no.getDado() + " " + 
               imprimirPreOrdemRec(no.getEsquerda()) + " " +
               imprimirPreOrdemRec(no.getDireita());
    }

    /**
     * Auxiliar para imprimir elementos em ordem recursivamente.
     * @param no Nó atual.
     * @return String com elementos em ordem.
     */
    private String imprimirEmOrdemRec(NoTriplo<T> no) {
        if (no == null) return "";
        return imprimirEmOrdemRec(no.getEsquerda()) + " " +
               no.getDado() + " " +
               imprimirEmOrdemRec(no.getDireita());
    }

    /**
     * Auxiliar para imprimir elementos em pós-ordem recursivamente.
     * @param no Nó atual.
     * @return String com elementos em pós-ordem.
     */
    private String imprimirPosOrdemRec(NoTriplo<T> no) {
        if (no == null) return "";
        return imprimirPosOrdemRec(no.getEsquerda()) + " " +
               imprimirPosOrdemRec(no.getDireita()) + " " +
               no.getDado();
    }

    /**
     * Formata a saída dos métodos de impressão, removendo espaços extras e ajustando separadores.
     * @param msg String a ser formatada.
     * @return String formatada.
     */
    private String formataSaida(String msg) {
         // Substitui um ou mais espaços em branco por uma vírgula, após remover espaços das bordas
         msg = msg.trim().replaceAll("\\s+", ",");
         return "[" + msg + "]";
    }
} 