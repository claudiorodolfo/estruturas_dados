package dao.carro;

import dao.CarroDAO;
import model.Carro;
import repository.Listavel;
import repository.estaticas.lista.ListaEstatica;

public class CarroDAOListaEstatica {//implements CarroDAO {
   
    private Listavel listaCarros = new ListaEstatica(100);
}
