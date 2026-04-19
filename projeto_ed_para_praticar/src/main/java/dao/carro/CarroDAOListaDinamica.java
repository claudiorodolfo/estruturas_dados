package dao.carro;

import model.Carro;
import dao.CarroDAO;
import repository.Listavel;
import repository.dinamicas.lista.ListaDinamica;

public class CarroDAOListaDinamica implements CarroDAO {
   
    private Listavel listaCarros = new ListaDinamica(100);
}
