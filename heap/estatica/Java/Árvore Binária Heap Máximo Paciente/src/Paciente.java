public class Paciente implements Priorizavel {
    private String nome;
    private int idade;
    private long prioridade;
    private long prioridadeInterna;

    public Paciente(String nome, int idade, long prioridade) {
        this.nome = nome;
        this.idade = idade;
        this.prioridade = prioridade;
        long inversoTimestamp = 1_000_000_000_000L - System.nanoTime();
        this.prioridadeInterna = prioridade * 1_000_000_000_000L + inversoTimestamp;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public long getPrioridade() {
        return prioridadeInterna;
    }

    @Override
    public String toString() {
        return String.format("Paciente{nome='%s', idade=%d, prioridade=%d}", nome, idade, prioridade);
        //return String.format("Paciente{nome='%s', idade=%d, prioridade=%d, prioridadeInterna=%d}", nome, idade, prioridade, prioridadeInterna);
    }
}