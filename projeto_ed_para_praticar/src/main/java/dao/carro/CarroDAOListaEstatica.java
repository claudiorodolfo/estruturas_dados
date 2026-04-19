package dao.carro;

import model.Carro;
import dao.CarroDAO;
import repository.Listavel;
import repository.estaticas.lista.ListaEstatica;

public class CarroDAOListaEstatica implements CarroDAO {
   
    private Listavel listaCarros = new ListaEstatica(100);
}
