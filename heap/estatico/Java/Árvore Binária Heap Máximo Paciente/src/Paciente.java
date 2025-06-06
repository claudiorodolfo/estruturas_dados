public class Paciente implements Priorizavel {
    private String nome;
    private int idade;
    private long prioridade;
    private long prioridadeInterna;

    public Paciente(String nome, int idade, long prioridade) {
        this.nome = nome;
        this.idade = idade;
        this.prioridade = prioridade;
        this.prioridadeInterna = prioridade * 1000000000000L + System.currentTimeMillis();
    }

    @Override
    public long getPrioridade() {
        return prioridade;
    }

    @Override
    public void setPrioridade(long prioridade) {
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public long getPrioridadeOriginal() {
        return prioridade;
    }

    @Override
    public String toString() {
        return String.format("""
            {
                "nome": "%s",
                "idade": %d,
                "prioridade": %d,
                "prioridadeCalculada": %d
            }
            """, nome, idade, prioridade, prioridadeInterna);
    }
    
}