import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó da árvore B+ (NoBMais).
 * 
 * @param <C> Tipo da chave
 * @param <V> Tipo do valor
 */
public class NoBMais<C extends Comparable<C>, V> {
    private List<C> chaves;
    private List<V> valores;
    private List<NoBMais<C, V>> filhos;
    private NoBMais<C, V> proximo; // Para nós folha (lista encadeada)
    private boolean folha;

    public NoBMais(boolean folha) {
        this.folha = folha;
        this.chaves = new ArrayList<>();
        this.valores = new ArrayList<>();
        this.filhos = new ArrayList<>();
        this.proximo = null;
    }
    public List<C> getChaves() { return chaves; }
    public void setChaves(List<C> chaves) { this.chaves = chaves; }
    public List<V> getValores() { return valores; }
    public void setValores(List<V> valores) { this.valores = valores; }
    public List<NoBMais<C, V>> getFilhos() { return filhos; }
    public void setFilhos(List<NoBMais<C, V>> filhos) { this.filhos = filhos; }
    public NoBMais<C, V> getProximo() { return proximo; }
    public void setProximo(NoBMais<C, V> proximo) { this.proximo = proximo; }
    public boolean isFolha() { return folha; }
    public void setFolha(boolean folha) { this.folha = folha; }
    public int getQuantidadeChaves() { return chaves.size(); }
    public boolean estaCheio(int ordem) { return chaves.size() >= ordem; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NoBMais[");
        for (int i = 0; i < chaves.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(chaves.get(i));
            if (folha && i < valores.size()) {
                sb.append(":").append(valores.get(i));
            }
        }
        sb.append("]");
        return sb.toString();
    }
} 