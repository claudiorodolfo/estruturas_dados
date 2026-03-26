package dao;

import model.Livro;
import repository.pilha.Empilhavel;
import repository.pilha.estatica.PilhaEstatica;
import java.time.LocalDate;

public class LivroDAOPilhaEstatica implements LivroDAO {
    
    private Empilhavel pilhaLivros = new PilhaEstatica(100);
           
    @Override
    public void addLivro(Livro livro) {
        pilhaLivros.empilhar(livro);
    }  
    
    @Override
    public Livro getLivroPorId(long id) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;
        
        // Desempilhar até encontrar
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo.getId() != null && livroTopo.getId().longValue() == id) {
                livroRetorno = livroTopo;
                break;
            }
        }        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }   

        return livroRetorno;
    }
    
    @Override
    public Livro[] getAllLivros() {
        Livro[] arrayLivrosRetorno = new Livro[getTotalLivros()];
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        
        // Desempilhar e coletar em array
        int indice = 0;
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            arrayLivrosRetorno[indice++] = livroTopo;
            pilhaAuxiliar.empilhar(livroTopo);
        }
        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }
        
        return arrayLivrosRetorno;
    }
    
    @Override
    public void updateLivro(Livro newLivro) {
        if (newLivro == null || newLivro.getId() == null) {
            return;
        }

        long id = newLivro.getId().longValue();
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        boolean atualizado = false;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            if (!atualizado && livroTopo != null && livroTopo.getId() != null && livroTopo.getId().longValue() == id) {
                pilhaAuxiliar.empilhar(newLivro);
                atualizado = true;
            } else {
                pilhaAuxiliar.empilhar(livroTopo);
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }
    }

    @Override
    public Livro deleteLivro(long id) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;
        
        // Desempilhar e remover se encontrar
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            if (livroTopo != null && livroTopo.getId() != null && livroTopo.getId().longValue() == id) {
                livroRetorno = livroTopo;
                // Não empilhar o removido
            } else {
                pilhaAuxiliar.empilhar(livroTopo);
            }
        }
        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }
        
        return livroRetorno;
    }
    
    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);
     
        // Desempilhar e filtrar
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getAutor() != null && autor != null
                    && livroTopo.getAutor().equalsIgnoreCase(autor)) {
                pilhaRetorno.empilhar(livroTopo);
            }
        }
        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }
        
        // Converter pilha de resultados para array
        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getDataPublicacao() != null && dataPublicacao != null
                    && livroTopo.getDataPublicacao().equals(dataPublicacao)) {
                pilhaRetorno.empilhar(livroTopo);
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] getLivrosPorTitulo(String titulo) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getTitulo() != null && titulo != null
                    && livroTopo.getTitulo().equalsIgnoreCase(titulo)) {
                pilhaRetorno.empilhar(livroTopo);
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRemovidos = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            if (livroTopo != null && livroTopo.getTitulo() != null && titulo != null
                    && livroTopo.getTitulo().equalsIgnoreCase(titulo)) {
                pilhaRemovidos.empilhar(livroTopo);
            } else {
                pilhaAuxiliar.empilhar(livroTopo);
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRemovidos);
    }

    @Override
    public Livro getLivroPorIsbn(String isbn) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro retorno = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (retorno == null && livroTopo != null && livroTopo.getIsbn() != null && isbn != null
                    && livroTopo.getIsbn().equalsIgnoreCase(isbn)) {
                retorno = livroTopo;
                break;
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return retorno;
    }

    @Override
    public Livro[] getLivrosPorFaixaPreco(double minPreco, double maxPreco) {
        double min = Math.min(minPreco, maxPreco);
        double max = Math.max(minPreco, maxPreco);

        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getPreco() != null) {
                double preco = livroTopo.getPreco().doubleValue();
                if (preco >= min && preco <= max) {
                    pilhaRetorno.empilhar(livroTopo);
                }
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] getLivrosPorFaixaData(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null || maxDate == null) {
            return new Livro[0];
        }
        LocalDate min = (minDate.isBefore(maxDate) || minDate.isEqual(maxDate)) ? minDate : maxDate;
        LocalDate max = (min == minDate) ? maxDate : minDate;

        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getDataPublicacao() != null) {
                LocalDate d = livroTopo.getDataPublicacao();
                boolean geMin = d.isAfter(min) || d.isEqual(min);
                boolean leMax = d.isBefore(max) || d.isEqual(max);
                if (geMin && leMax) {
                    pilhaRetorno.empilhar(livroTopo);
                }
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro getLivroMaisCaro() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro melhor = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getPreco() != null) {
                if (melhor == null || melhor.getPreco() == null
                        || livroTopo.getPreco().doubleValue() > melhor.getPreco().doubleValue()) {
                    melhor = livroTopo;
                }
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return melhor;
    }

    @Override
    public Livro getLivroMaisBarato() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro melhor = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getPreco() != null) {
                if (melhor == null || melhor.getPreco() == null
                        || livroTopo.getPreco().doubleValue() < melhor.getPreco().doubleValue()) {
                    melhor = livroTopo;
                }
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return melhor;
    }

    @Override
    public Livro getLivroMaisNovo() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro melhor = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getDataPublicacao() != null) {
                if (melhor == null || melhor.getDataPublicacao() == null
                        || livroTopo.getDataPublicacao().isAfter(melhor.getDataPublicacao())) {
                    melhor = livroTopo;
                }
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return melhor;
    }

    @Override
    public Livro getLivroMaisAntigo() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro melhor = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getDataPublicacao() != null) {
                if (melhor == null || melhor.getDataPublicacao() == null
                        || livroTopo.getDataPublicacao().isBefore(melhor.getDataPublicacao())) {
                    melhor = livroTopo;
                }
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return melhor;
    }

    @Override
    public String printLivros() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        String resultado = "[\n";
        boolean primeiro = true;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (!primeiro) {
                resultado += ",\n";
            }
            resultado += livroTopo;
            primeiro = false;
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return resultado + "\n]";
    }

    @Override
    public int getTotalLivros() {
        return contarLivros();
    }

    @Override
    public double getPrecoMedio() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        double soma = 0.0;
        int qtd = 0;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo != null && livroTopo.getPreco() != null) {
                soma += livroTopo.getPreco().doubleValue();
                qtd++;
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return qtd == 0 ? 0.0 : (soma / qtd);
    }

    @Override
    public boolean isLivroDisponivel(long id) {
        return getLivroPorId(id) != null;
    }

    @Override
    public void clearAllLivros() {
        pilhaLivros = new PilhaEstatica(100);
    }

    private int contarLivros() {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        int contador = 0;

        while (!pilhaLivros.estaVazia()) {
            pilhaAuxiliar.empilhar(pilhaLivros.desempilhar());
            contador++;
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return contador;
    }

    private int contarPilha(Empilhavel pilha) {
        if (pilha == null) {
            return 0;
        }
        Empilhavel aux = new PilhaEstatica(100);
        int contador = 0;
        while (!pilha.estaVazia()) {
            aux.empilhar(pilha.desempilhar());
            contador++;
        }
        while (!aux.estaVazia()) {
            pilha.empilhar(aux.desempilhar());
        }
        return contador;
    }

    private Livro[] pilhaParaArray(Empilhavel pilha) {
        if (pilha == null) {
            return new Livro[0];
        }

        int tamanho = contarPilha(pilha);
        Livro[] array = new Livro[tamanho];
        Empilhavel aux = new PilhaEstatica(100);

        int i = 0;
        while (!pilha.estaVazia()) {
            Livro topo = (Livro) pilha.desempilhar();
            array[i++] = topo;
            aux.empilhar(topo);
        }

        while (!aux.estaVazia()) {
            pilha.empilhar(aux.desempilhar());
        }

        return array;
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
