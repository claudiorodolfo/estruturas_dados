// Acessar o diretório dos fontes:
// cd prova/20252
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out Questao2
public class Questao2 {
    String[] alunosA;
    String[] alunosB;
    String[] alunosC;

    public Questao2(String[] A, String[] B, String[] C) {
        alunosA = A;
        alunosB = B;
        alunosC = C;
    }

    //////////////////////
    // Só precisavam implementar estes métodos na prova.
    // Coloquei toda a estrutura para facilitar a compreensão.
    private boolean contains(String nome, String[] alunos) {
        for(String a: alunos) 
            if (a.equals(nome))
                return true;
        
        return false;
    }

    private boolean contains(String nome, String[] alunos, int limite) {
        for (int i = 0; i < limite; i++)
            if (alunos[i].equals(nome))
                return true;
        return false;
    }

    private String[] copiar(String[] original, int tamanho) {
        String[] copia = new String[tamanho];
        for (int i = 0; i < tamanho; i++)
            copia[i] = original[i];
        return copia;
    }

    /** a) alunos que fazem esportiva e cultural e não extensão */
    public String[] culturaEEsporteSemExtensao() {
        String[] result = new String[alunosB.length];
        int n = 0;
        for (String aluno : alunosB) {
            if (contains(aluno, alunosA) && !contains(aluno, alunosC)) {
                result[n++] = aluno;
            }
        }
        return copiar(result, n);
    }

    /** b) alunos que fazem somente extensão */
    public String[] somenteExtensao() {
        String[] result = new String[alunosC.length];
        int n = 0;
        for (String aluno : alunosC) {
            if (!contains(aluno, alunosA) && !contains(aluno, alunosB)) {
                result[n++] = aluno;
            }
        }
        return copiar(result, n);
    }

    /** c) alunos que fazem cultural ou extensão, mas não fazem esportiva */
    public String[] culturaOuExtensaoSemEsporte() {
        String[] result = new String[alunosA.length + alunosC.length];
        int n = 0;
        // incluir os de A que não estão em B
        for (String aluno : alunosA) {
            if (!contains(aluno, alunosB) && !contains(aluno, result, n)) {
                result[n++] = aluno;
            }
        }
        // incluir os de C que não estão em B, e que ainda não foram incluídos
        for (String aluno : alunosC) {
            if (!contains(aluno, alunosB) && !contains(aluno, result, n)) {
                result[n++] = aluno;
            }
        }
        return copiar(result, n);
    }
    //////////////////////
    
    void main() {
        String[] culturaA = {"Alice", "Bob", "Carol", "David"};
        String[] esporteB = {"Bob", "David", "Eve", "Frank"};
        String[] extensaoC = {"Carol", "David", "Frank", "Gael"};

        Questao2 q2 = new Questao2(culturaA, esporteB, extensaoC);
        IO.println("a) Esporte + Cultura, sem Extensão: " + java.util.Arrays.toString(q2.culturaEEsporteSemExtensao()));     // Bob
        IO.println("b) Somente Extensão: " + java.util.Arrays.toString(q2.somenteExtensao()));                               // Gael
        IO.println("c) Cultura ou Extensão, sem Esporte: " + java.util.Arrays.toString(q2.culturaOuExtensaoSemEsporte()));   // Alice, Caro, Gael
    }
}
