package dao.carro;

import model.Carro;
import dao.CarroDAO;
import repository.Enfileiravel;
import repository.dinamicas.fila.FilaDinamica;

public class CarroDAOFilaDinamica {//implements CarroDAO {
 
    private Enfileiravel filaCarros = new FilaDinamica(100);
}
