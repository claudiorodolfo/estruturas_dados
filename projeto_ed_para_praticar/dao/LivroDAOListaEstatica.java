package dao;

import model.Livro;
import repository.lista.Listavel;
import repository.lista.estatica.ListaEstatica;

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

        Livro maisCaro = (Livro) listaLivros.selecionar(0);
        for (int i = 1; i < listaLivros.tamanho(); i++) {
            Livro atual = (Livro) listaLivros.selecionar(i);
            if (atual.getPreco() > maisCaro.getPreco()) {
                maisCaro = atual;
            }
        }
        return maisCaro;
    }
}
