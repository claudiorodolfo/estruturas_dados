public class Paciente implements Priorizavel {
    private String nome;
    private int idade;
    private long prioridade;

    public Paciente(String nome, int idade, long prioridade) {
        this.nome = nome;
        this.idade = idade;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public long getPrioridade() {
        return prioridade;
    }

    @Override
    public void setPrioridade(long prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("\n{\n");
        resultado.append("\t\"nome\": \"").append(nome).append("\",\n");
        resultado.append("\t\"idade\": \"").append(idade).append("\",\n");
        resultado.append("\t\"prioridade\": \"").append(prioridadeOriginal()).append("\"\n");
        resultado.append("\t\"prioridadeCalculada\": \"").append(prioridade).append("\"");
        resultado.append("\n}");
        return resultado.toString();
    }

    private int prioridadeOriginal() {
        String prioridadeString = Long.toString(prioridade);
        int tamanhoString = prioridadeString.length();
        int corte = tamanhoString - 14;

        if (corte <= 0) {
            return 0;
        }

        String digitosIniciais = prioridadeString.substring(0, corte);
        try {
            return Integer.parseInt(digitosIniciais);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
