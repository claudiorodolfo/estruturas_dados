package dao.carro;

import model.Carro;
import dao.CarroDAO;
import repository.Empilhavel;
import repository.estaticas.pilha.PilhaEstatica;

public class CarroDAOPilhaEstatica {//implements CarroDAO {
 
    private Empilhavel pilhaCarros = new PilhaEstatica(100);
}
