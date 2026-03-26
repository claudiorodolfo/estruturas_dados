package dao;

import model.Carro;
import repository.pilha.Empilhavel;
import repository.pilha.estatica.PilhaEstatica;

public class CarroDAOPilhaEstatica implements CarroDAO {
 
    private Empilhavel pilhaCarros = new PilhaEstatica(100);
}
