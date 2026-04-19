package dao.livro;

import model.Livro;
import dao.LivroDAO;
import repository.Listavel;
import repository.dinamicas.lista.ListaDinamica;
import java.time.LocalDate;

public class LivroDAOListaDinamica implements LivroDAO {

    private Listavel listaLivros = new ListaDinamica(100);

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
        Listavel listaRemovidos = new ListaDinamica(100);

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

        Livro maisCaro = (Livro) listaLivros.selecionar(0);
        for (int i = 1; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual.getPreco() > maisCaro.getPreco()) {
                maisCaro = atual;
            }
        }
        return maisCaro;
    }

    @Override
    public void clearAllLivros() {
        listaLivros = new ListaDinamica(100);
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
}
