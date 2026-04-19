package dao.livro;

import dao.LivroDAO;
import model.Livro;
import repository.Listavel;
import repository.estaticas.lista.ListaEstatica;
import java.time.LocalDate;

public class LivroDAOListaEstatica implements LivroDAO {

    private Listavel listaLivros = new ListaEstatica(100);

    @Override
    public void addLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("livro nao pode ser nulo.");
        }
        listaLivros.anexar(livro);
    }

    @Override
    public Livro getLivroPorId(long id) {
        Livro retorno = null;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual.getId() == id) {
                retorno = atual;
                break;
            }
        }
        return retorno;
    }

    @Override
    public Livro[] getAllLivros() {
        Livro[] arrayRetorno = new Livro[listaLivros.tamanho()];
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            arrayRetorno[i] = (Livro) listaLivros.selecionar(i);
        }
        return arrayRetorno;
    }

    @Override
    public Livro deleteLivro(long id) {
        Livro retorno = null;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual.getId() == id) {
                retorno = (Livro) listaLivros.apagar(i);
                break;
            }
        }
        return retorno;
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Listavel listaRemovidos = new ListaEstatica(100);

        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual.getTitulo().equalsIgnoreCase(titulo)) {
                listaRemovidos.anexar(listaLivros.apagar(i));
                i--;
            }
        }

        Livro[] arrayRetorno = new Livro[listaRemovidos.tamanho()];
        for (int i = 0; i < listaRemovidos.tamanho(); i++) {
            arrayRetorno[i] = (Livro) listaRemovidos.selecionar(i);
        }

        return arrayRetorno;
    }

    @Override
    public Livro getLivroMaisCaro() {
        if (listaLivros.estaVazia()) {
            return null;
        }

        Livro maisCaro = null;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getPreco() != null) {
                if (maisCaro == null || maisCaro.getPreco() == null
                        || atual.getPreco().doubleValue() > maisCaro.getPreco().doubleValue()) {
                    maisCaro = atual;
                }
            }
        }
        return maisCaro;
    }

    @Override
    public void updateLivro(Livro newLivro) {
        if (newLivro == null || newLivro.getId() == null) {
            return;
        }
        long id = newLivro.getId().longValue();
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getId() != null && atual.getId().longValue() == id) {
                listaLivros.atualizar(newLivro, i);
                break;
            }
        }
    }

    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        Listavel listaRetorno = new ListaEstatica(100);
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getAutor() != null && autor != null
                    && atual.getAutor().equalsIgnoreCase(autor)) {
                listaRetorno.anexar(atual);
            }
        }
        return listaParaArray(listaRetorno);
    }

    @Override
    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao) {
        Listavel listaRetorno = new ListaEstatica(100);
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getDataPublicacao() != null && dataPublicacao != null
                    && atual.getDataPublicacao().equals(dataPublicacao)) {
                listaRetorno.anexar(atual);
            }
        }
        return listaParaArray(listaRetorno);
    }

    @Override
    public Livro[] getLivrosPorTitulo(String titulo) {
        Listavel listaRetorno = new ListaEstatica(100);
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getTitulo() != null && titulo != null
                    && atual.getTitulo().equalsIgnoreCase(titulo)) {
                listaRetorno.anexar(atual);
            }
        }
        return listaParaArray(listaRetorno);
    }

    @Override
    public Livro getLivroPorIsbn(String isbn) {
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getIsbn() != null && isbn != null
                    && atual.getIsbn().equalsIgnoreCase(isbn)) {
                return atual;
            }
        }
        return null;
    }

    @Override
    public Livro[] getLivrosPorFaixaPreco(double minPreco, double maxPreco) {
        double min = Math.min(minPreco, maxPreco);
        double max = Math.max(minPreco, maxPreco);
        Listavel listaRetorno = new ListaEstatica(100);
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getPreco() != null) {
                double preco = atual.getPreco().doubleValue();
                if (preco >= min && preco <= max) {
                    listaRetorno.anexar(atual);
                }
            }
        }
        return listaParaArray(listaRetorno);
    }

    @Override
    public Livro[] getLivrosPorFaixaData(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null || maxDate == null) {
            return new Livro[0];
        }
        LocalDate min = (minDate.isBefore(maxDate) || minDate.isEqual(maxDate)) ? minDate : maxDate;
        LocalDate max = (min == minDate) ? maxDate : minDate;
        Listavel listaRetorno = new ListaEstatica(100);
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getDataPublicacao() != null) {
                LocalDate d = atual.getDataPublicacao();
                boolean geMin = d.isAfter(min) || d.isEqual(min);
                boolean leMax = d.isBefore(max) || d.isEqual(max);
                if (geMin && leMax) {
                    listaRetorno.anexar(atual);
                }
            }
        }
        return listaParaArray(listaRetorno);
    }

    @Override
    public Livro getLivroMaisBarato() {
        if (listaLivros.estaVazia()) {
            return null;
        }
        Livro melhor = null;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getPreco() != null) {
                if (melhor == null || melhor.getPreco() == null
                        || atual.getPreco().doubleValue() < melhor.getPreco().doubleValue()) {
                    melhor = atual;
                }
            }
        }
        return melhor;
    }

    @Override
    public Livro getLivroMaisNovo() {
        if (listaLivros.estaVazia()) {
            return null;
        }
        Livro melhor = null;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getDataPublicacao() != null) {
                if (melhor == null || melhor.getDataPublicacao() == null
                        || atual.getDataPublicacao().isAfter(melhor.getDataPublicacao())) {
                    melhor = atual;
                }
            }
        }
        return melhor;
    }

    @Override
    public Livro getLivroMaisAntigo() {
        if (listaLivros.estaVazia()) {
            return null;
        }
        Livro melhor = null;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getDataPublicacao() != null) {
                if (melhor == null || melhor.getDataPublicacao() == null
                        || atual.getDataPublicacao().isBefore(melhor.getDataPublicacao())) {
                    melhor = atual;
                }
            }
        }
        return melhor;
    }

    @Override
    public String printLivros() {
        String resultado = "[\n";
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            if (i > 0) {
                resultado += ",\n";
            }
            resultado += listaLivros.selecionar(i);
        }
        return resultado + "\n]";
    }

    @Override
    public int getTotalLivros() {
        return listaLivros.tamanho();
    }

    @Override
    public double getPrecoMedio() {
        double soma = 0.0;
        int qtd = 0;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual != null && atual.getPreco() != null) {
                soma += atual.getPreco().doubleValue();
                qtd++;
            }
        }
        return qtd == 0 ? 0.0 : (soma / qtd);
    }

    @Override
    public boolean isLivroDisponivel(long id) {
        return getLivroPorId(id) != null;
    }

    @Override
    public void clearAllLivros() {
        listaLivros = new ListaEstatica(100);
    }

    @Override
    public String toStringArray(Livro[] livros) {
        if (livros == null) {
            return "null";
        }
        String s = "[";
        for (int i = 0; i < livros.length; i++) {
            s += livros[i];
            if (i != livros.length - 1) {
                s += ",";
            }
        }
        return s + "]";
    }

    private static Livro[] listaParaArray(Listavel lista) {
        Livro[] array = new Livro[lista.tamanho()];
        for (int i = 0; i < lista.tamanho(); i++) {
            array[i] = (Livro) lista.selecionar(i);
        }
        return array;
    }
}
