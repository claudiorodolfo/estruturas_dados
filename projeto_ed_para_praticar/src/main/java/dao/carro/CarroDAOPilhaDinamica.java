package dao.carro;

import model.Carro;
import dao.CarroDAO;
import repository.Empilhavel;
import repository.dinamicas.pilha.PilhaDinamica;

public class CarroDAOPilhaDinamica {//implements CarroDAO {
 
    private Empilhavel pilhaCarros = new PilhaDinamica(100);
}
