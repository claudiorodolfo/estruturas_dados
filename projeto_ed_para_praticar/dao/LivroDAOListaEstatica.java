package dao;

import model.Livro;
import repository.lista.Listavel;
import repository.lista.estatica.ListaEstatica;

public class LivroDAOListaEstatica implements LivroDAO {

    private Listavel listaLivros = new ListaEstatica(100);
}
