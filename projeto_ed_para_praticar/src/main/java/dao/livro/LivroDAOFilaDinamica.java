package dao.livro;

import model.Livro;
import dao.LivroDAO;
import repository.Enfileiravel;
import repository.dinamicas.fila.FilaDinamica;
import java.time.LocalDate;

public class LivroDAOFilaDinamica implements LivroDAO {

    private Enfileiravel filaLivros = new FilaDinamica(100);

    @Override
    public void addLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("livro nao pode ser nulo.");
        }
        filaLivros.enfileirar(livro);
    }

    @Override
    public Livro getLivroPorId(long id) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro retorno = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getId() != null && atual.getId().longValue() == id) {
                retorno = atual;
                break;
            }
        }

        while (!filaLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaLivros.desenfileirar());
        }
        filaLivros = filaAuxiliar;

        return retorno;
    }

    @Override
    public Livro[] getAllLivros() {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        int quantidade = 0;

        while (!filaLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaLivros.desenfileirar());
            quantidade++;
        }
        filaLivros = filaAuxiliar;
        filaAuxiliar.limpar();

        Livro[] retorno = new Livro[quantidade];
        int i = 0;
        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            retorno[i++] = atual;
            filaAuxiliar.enfileirar(atual);
        }
        filaLivros = filaAuxiliar;
        return retorno;
    }

    @Override
    public void updateLivro(Livro newLivro) {
        if (newLivro == null || newLivro.getId() == null) {
            return;
        }
        long id = newLivro.getId().longValue();
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        boolean atualizado = false;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            if (!atualizado && atual != null && atual.getId() != null && atual.getId().longValue() == id) {
                filaAuxiliar.enfileirar(newLivro);
                atualizado = true;
            } else {
                filaAuxiliar.enfileirar(atual);
            }
        }
        filaLivros = filaAuxiliar;
    }

    @Override
    public Livro deleteLivro(long id) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro retorno = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            if (atual != null && atual.getId() != null && atual.getId().longValue() == id) {
                retorno = atual;
                break;
            } else {
                filaAuxiliar.enfileirar(atual);
            }
        }

        while (!filaLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaLivros.desenfileirar());
        }
        filaLivros = filaAuxiliar;

        return retorno;
    }

    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Enfileiravel filaRetorno = new FilaDinamica(100);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getAutor() != null && autor != null
                    && atual.getAutor().equalsIgnoreCase(autor)) {
                filaRetorno.enfileirar(atual);
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return filaParaArray(filaRetorno);
    }

    @Override
    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Enfileiravel filaRetorno = new FilaDinamica(100);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getDataPublicacao() != null && dataPublicacao != null
                    && atual.getDataPublicacao().equals(dataPublicacao)) {
                filaRetorno.enfileirar(atual);
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return filaParaArray(filaRetorno);
    }

    @Override
    public Livro[] getLivrosPorTitulo(String titulo) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Enfileiravel filaRetorno = new FilaDinamica(100);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getTitulo() != null && titulo != null
                    && atual.getTitulo().equalsIgnoreCase(titulo)) {
                filaRetorno.enfileirar(atual);
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return filaParaArray(filaRetorno);
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Enfileiravel filaRemovidos = new FilaDinamica(100);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            if (atual != null && atual.getTitulo() != null && titulo != null
                    && atual.getTitulo().equalsIgnoreCase(titulo)) {
                filaRemovidos.enfileirar(atual);
            } else {
                filaAuxiliar.enfileirar(atual);
            }
        }

        filaLivros = filaAuxiliar;

        return filaParaArray(filaRemovidos);
    }

    @Override
    public Livro getLivroPorIsbn(String isbn) {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro retorno = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (retorno == null && atual != null && atual.getIsbn() != null && isbn != null
                    && atual.getIsbn().equalsIgnoreCase(isbn)) {
                retorno = atual;
                break;
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return retorno;
    }

    @Override
    public Livro[] getLivrosPorFaixaPreco(double minPreco, double maxPreco) {
        double min = Math.min(minPreco, maxPreco);
        double max = Math.max(minPreco, maxPreco);

        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Enfileiravel filaRetorno = new FilaDinamica(100);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getPreco() != null) {
                double preco = atual.getPreco().doubleValue();
                if (preco >= min && preco <= max) {
                    filaRetorno.enfileirar(atual);
                }
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return filaParaArray(filaRetorno);
    }

    @Override
    public Livro[] getLivrosPorFaixaData(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null || maxDate == null) {
            return new Livro[0];
        }
        LocalDate min = (minDate.isBefore(maxDate) || minDate.isEqual(maxDate)) ? minDate : maxDate;
        LocalDate max = (min == minDate) ? maxDate : minDate;

        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Enfileiravel filaRetorno = new FilaDinamica(100);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getDataPublicacao() != null) {
                LocalDate d = atual.getDataPublicacao();
                boolean geMin = d.isAfter(min) || d.isEqual(min);
                boolean leMax = d.isBefore(max) || d.isEqual(max);
                if (geMin && leMax) {
                    filaRetorno.enfileirar(atual);
                }
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return filaParaArray(filaRetorno);
    }

    @Override
    public Livro getLivroMaisCaro() {
        if (filaLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro melhor = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getPreco() != null) {
                if (melhor == null || melhor.getPreco() == null
                        || atual.getPreco().doubleValue() > melhor.getPreco().doubleValue()) {
                    melhor = atual;
                }
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return melhor;
    }

    @Override
    public Livro getLivroMaisBarato() {
        if (filaLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro melhor = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getPreco() != null) {
                if (melhor == null || melhor.getPreco() == null
                        || atual.getPreco().doubleValue() < melhor.getPreco().doubleValue()) {
                    melhor = atual;
                }
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return melhor;
    }

    @Override
    public Livro getLivroMaisNovo() {
        if (filaLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro melhor = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getDataPublicacao() != null) {
                if (melhor == null || melhor.getDataPublicacao() == null
                        || atual.getDataPublicacao().isAfter(melhor.getDataPublicacao())) {
                    melhor = atual;
                }
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return melhor;
    }

    @Override
    public Livro getLivroMaisAntigo() {
        if (filaLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        Livro melhor = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getDataPublicacao() != null) {
                if (melhor == null || melhor.getDataPublicacao() == null
                        || atual.getDataPublicacao().isBefore(melhor.getDataPublicacao())) {
                    melhor = atual;
                }
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return melhor;
    }

    @Override
    public String printLivros() {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        String resultado = "[\n";
        boolean primeiro = true;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (!primeiro) {
                resultado += ",\n";
            }
            resultado += atual;
            primeiro = false;
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return resultado + "\n]";
    }

    @Override
    public int getTotalLivros() {
        return contarLivros(filaLivros);
    }

    @Override
    public double getPrecoMedio() {
        Enfileiravel filaAuxiliar = new FilaDinamica(100);
        double soma = 0.0;
        int qtd = 0;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual != null && atual.getPreco() != null) {
                soma += atual.getPreco().doubleValue();
                qtd++;
            }
        }

        while (!filaAuxiliar.estaVazia()) {
            filaLivros.enfileirar(filaAuxiliar.desenfileirar());
        }

        return qtd == 0 ? 0.0 : (soma / qtd);
    }

    @Override
    public boolean isLivroDisponivel(long id) {
        return getLivroPorId(id) != null;
    }

    @Override
    public void clearAllLivros() {
        filaLivros = new FilaDinamica(100);
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

    private int contarLivros(Enfileiravel fila) {
        if (fila == null) {
            return 0;
        }
        Enfileiravel aux = new FilaDinamica(100);
        int contador = 0;
        while (!fila.estaVazia()) {
            aux.enfileirar(fila.desenfileirar());
            contador++;
        }
        while (!aux.estaVazia()) {
            fila.enfileirar(aux.desenfileirar());
        }
        return contador;
    }

    private Livro[] filaParaArray(Enfileiravel fila) {
        if (fila == null) {
            return new Livro[0];
        }

        int tamanho = contarLivros(fila);
        Livro[] array = new Livro[tamanho];
        Enfileiravel aux = new FilaDinamica(100);

        int i = 0;
        while (!fila.estaVazia()) {
            Livro atual = (Livro) fila.desenfileirar();
            array[i++] = atual;
            aux.enfileirar(atual);
        }

        while (!aux.estaVazia()) {
            fila.enfileirar(aux.desenfileirar());
        }

        return array;
    }
}
