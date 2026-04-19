package dao.carro;

import dao.CarroDAO;
import model.Carro;
import repository.Listavel;
import repository.dinamicas.lista.ListaDinamica;

public class CarroDAOListaDinamica {//implements CarroDAO {

    private Listavel listaCarros = new ListaDinamica(100);

}
