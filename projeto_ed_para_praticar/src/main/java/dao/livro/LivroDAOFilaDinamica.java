package dao.livro;

import model.Livro;
import dao.LivroDAO;
import repository.Enfileiravel;
import repository.estaticas.fila.FilaEstatica;
import java.time.LocalDate;

public class LivroDAOFilaEstatica implements LivroDAO {
    
    private Enfileiravel filaLivros = new FilaEstatica(100);

    @Override
    public void addLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("livro nao pode ser nulo.");
        }
        filaLivros.enfileirar(livro);
    }

    @Override
    public Livro getLivroPorId(long id) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Livro retorno = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual.getId() == id) {
                retorno = atual;
                break;
            }
        }

        while (!filaLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaLivros.desenfileirar());
        }
        filaLivros = filaAuxiliar;

        return retorno;
    }

    @Override
    public Livro[] getAllLivros() {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaLivros.desenfileirar());
            quantidade++;
        }
        filaLivros = filaAuxiliar;
        filaAuxiliar.limpar();

        Livro[] retorno = new Livro[quantidade];
        int i = 0;
        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            retorno[i++] = atual;
            filaAuxiliar.enfileirar(atual);
        }
        filaLivros = filaAuxiliar;
        return retorno;
    }

    @Override
    public Livro deleteLivro(long id) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Livro retorno = null;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            if (atual.getId() == id) {
                retorno = atual;
                break;
            } else {
                filaAuxiliar.enfileirar(atual);
            }
        }

        while (!filaLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaLivros.desenfileirar());
        }
        filaLivros = filaAuxiliar;

        return retorno;
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Enfileiravel filaRemovidos = new FilaEstatica(100);
        int quantidadeRemovidos = 0;

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            if (atual.getTitulo().equalsIgnoreCase(titulo)) {
                filaRemovidos.enfileirar(atual);
                quantidadeRemovidos++;
            } else {
                filaAuxiliar.enfileirar(atual);
            }
        }

        filaLivros = filaAuxiliar;

        Livro[] arrayRetorno = new Livro[quantidadeRemovidos];
        int i = 0;
        while (!filaRemovidos.estaVazia()) {
            arrayRetorno[i++] = (Livro) filaRemovidos.desenfileirar();
        }

        return arrayRetorno;
    }

    @Override
    public Livro getLivroMaisCaro() {
        if (filaLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Livro maisCaro = (Livro) filaLivros.desenfileirar();
        filaAuxiliar.enfileirar(maisCaro);

        while (!filaLivros.estaVazia()) {
            Livro atual = (Livro) filaLivros.desenfileirar();
            filaAuxiliar.enfileirar(atual);
            if (atual.getPreco() > maisCaro.getPreco()) {
                maisCaro = atual;
            }
        }
        filaLivros = filaAuxiliar;

        return maisCaro;
    }

    @Override
    public void clearAllLivros() {
        filaLivros = new FilaEstatica(100);
    }

    @Override
    public String toStringArray(Livro[] livros) {
        if (livros == null) {
            return "null";
        }
        String s = "[";
        for (int i = 0; i < livros.length; i++) {
            s += livros[i];
            if (i != livros.length - 1) {
                s += ",";
            }
        }
        return s + "]";
    }
}
