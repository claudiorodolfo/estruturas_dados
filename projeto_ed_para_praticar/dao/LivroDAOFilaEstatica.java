package dao;

import model.Livro;
import repository.fila.Enfileiravel;
import repository.fila.estatica.FilaEstatica;

public class LivroDAOFilaEstatica implements LivroDAO {
    
    private Enfileiravel filaLivros = new FilaEstatica(100);
}
