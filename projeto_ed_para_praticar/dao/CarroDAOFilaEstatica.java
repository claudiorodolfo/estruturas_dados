package dao;

import model.Carro;
import repository.fila.Enfileiravel;
import repository.fila.estatica.FilaEstatica;

public class CarroDAOFilaEstatica implements CarroDAO {
 
    private Enfileiravel filaCarros = new FilaEstatica(100);
}
