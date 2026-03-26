package dao;

import model.Carro;
import repository.lista.Listavel;
import repository.lista.estatica.ListaEstatica;

public class CarroDAOListaEstatica implements CarroDAO {
   
    private Listavel listaCarros = new ListaEstatica(100);
}
