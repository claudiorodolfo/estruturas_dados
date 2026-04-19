package dao.carro;

import model.Carro;
import dao.CarroDAO;
import repository.Enfileiravel;
import repository.estaticas.fila.FilaEstatica;

public class CarroDAOFilaEstatica implements CarroDAO {
 
    private Enfileiravel filaCarros = new FilaEstatica(100);
}
