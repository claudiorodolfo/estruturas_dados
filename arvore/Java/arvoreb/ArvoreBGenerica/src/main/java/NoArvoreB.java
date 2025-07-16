import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó de Árvore B.
 * Cada nó pode conter múltiplas chaves e múltiplos filhos.
 * @param <T> Tipo dos dados armazenados no nó.
 */
public class NoArvoreB<T extends Comparable<T>> {
    private List<T> chaves;
    private List<NoArvoreB<T>> ponteirosFilhos;
    private int ordem;

    public NoArvoreB(int ordem) {
        this.ordem = ordem;
        chaves = new ArrayList<>();
        ponteirosFilhos = new ArrayList<>();
    }

    public List<T> getChaves() { return chaves; }
    public void setChaves(List<T> chaves) { this.chaves = chaves; }
    public List<NoArvoreB<T>> getFilhos() { return ponteirosFilhos; }
    public void setFilhos(List<NoArvoreB<T>> ponteirosFilhos) { this.ponteirosFilhos = ponteirosFilhos; }
    public int getOrdem() { return ordem; }
    public int getNumeroChaves() { return chaves.size(); }
    public boolean isCheio() { return chaves.size() == ordem - 1; }
    public boolean isFolha() { return ponteirosFilhos.isEmpty(); }

    /**
     * Insere um valor em um nó que não está cheio.
     */
    public void inserirNaoCheio(T valor) {
        int i = chaves.size() - 1;
        if (isFolha()) {
            //Cria um "espaço em branco" no final da lista de chaves, pois vamos reorganizá-la com o novo valor.
            chaves.add(null); // espaço para nova chave
            //Enquanto a nova chave for menor do que as que já estão no nó, movemos as chaves maiores para a direita.
            //Isso abre espaço na posição correta para a nova chave, mantendo a lista ordenada.
            while (i >= 0 && valor.compareTo(chaves.get(i)) < 0) {
                chaves.set(i + 1, chaves.get(i));
                i--;
            }
            // insere o valor na posição correta
            chaves.set(i + 1, valor);
        } else {    //no nao é folha
            //Localiza a posição do filho que deve receber esse valor.
            while (i >= 0 && valor.compareTo(chaves.get(i)) < 0) {
                i--;
            }
            //i++ porque queremos o ponteiro para a subárvore após a última chave menor do que valor.
            i++;
            //Se o filho está cheio, divida-o antes de descer
            if (ponteirosFilhos.get(i).isCheio()) {
                //chamamos dividirFilho(i) para dividir o filho cheio em dois e promover uma chave ao nó atual.
                dividirFilho(i);
                //Após dividir, pode ser que a chave promovida fique antes ou depois de valor.
                //Se valor for maior que a chave promovida, seguimos para o novo filho à direita (i++).
                if (valor.compareTo(chaves.get(i)) > 0) {
                    i++;
                }
            }
            //Agora chamamos recursivamente inserirNaoCheio no filho apropriado, que com certeza não está cheio (pois foi dividido se estivesse cheio).
            ponteirosFilhos.get(i).inserirNaoCheio(valor);
        }
    }

    /**
     * Divide o filho i deste nó.
     */
    public void dividirFilho(int i) {
        //y é o filho que será dividido (porque está cheio).
        NoArvoreB<T> y = ponteirosFilhos.get(i);
        //z é o novo nó que será criado com metade dos dados de y.
        NoArvoreB<T> z = new NoArvoreB<>(ordem);
        //t é o ponto de divisão. 
        //É o número mínimo de chaves por nó, baseado na ordem da árvore.
        int t = ordem / 2;
        //Essa parte move do filho y para o novo nó z 
        //as t - 1 chaves que estão depois da chave do meio.
        for (int j = 0; j < t - 1; j++) {
            z.chaves.add(y.chaves.remove(t));
        }
        //Se y não for folha, então ele tem ponteiros para filhos. 
        //Assim, o novo nó z também precisa receber os filhos correspondentes à parte de chaves que ele recebeu.
        if (!y.isFolha()) {
            for (int j = 0; j < t; j++) {
                z.ponteirosFilhos.add(y.ponteirosFilhos.remove(t));
            }
        }
        //A chave do meio de y (posição t - 1) é removida de y e inserida neste nó (o pai) na posição i.
        //Essa chave "sobe" na árvore.
        chaves.add(i, y.chaves.remove(t - 1));
        //O novo nó z é adicionado como filho à direita de y, ou seja, imediatamente após ele.
        ponteirosFilhos.add(i + 1, z);
    }

    /**
     * Busca um valor neste nó ou nos filhos.
     */
    public NoArvoreB<T> buscar(T valor) {
        int i = 0;
        while (i < chaves.size() && valor.compareTo(chaves.get(i)) > 0) {
            i++;
        }
        if (i < chaves.size() && valor.compareTo(chaves.get(i)) == 0) {
            return this;
        }
        if (isFolha()) {
            return null;
        }
        return ponteirosFilhos.get(i).buscar(valor);
    }

    /**
     * Percurso para impressão
     */

    public void emOrdem(StringBuilder sb) {
        for (int i = 0; i < chaves.size(); i++) {
            if (!isFolha()) {
                ponteirosFilhos.get(i).emOrdem(sb);
            }
            sb.append(chaves.get(i)).append(" ");
        }
        if (!isFolha()) {
            ponteirosFilhos.get(chaves.size()).emOrdem(sb);
        }
    }

    public void exibir() {
        for (int i = 0; i < chaves.size(); i++) {
            if (!isFolha()) {
                ponteirosFilhos.get(i).exibir();
            }
            System.out.print(chaves.get(i) + " ");
        }
        if (!isFolha()) {
            ponteirosFilhos.get(chaves.size()).exibir();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < chaves.size(); i++) {
            sb.append(chaves.get(i));
            if (i < chaves.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
} 