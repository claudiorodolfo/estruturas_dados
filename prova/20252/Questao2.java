public class Questao2 {
    String[] alunosA;
    String[] alunosB;
    String[] alunosC;

    public Questao2(ArrayList A, ArrayList B, ArrayList C) {
        alunosA = (String[]) A.selectAll();
        alunosB = (String[]) B.selectAll();
        alunosC = (String[]) C.selectAll();
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

    /** a) alunos que fazem esportiva e cultural e não extensão */
    public String[] culturaEEsporteSemExtensao() {

        ArrayList result = new ArrayList();
        for (String aluno : alunosB) {
            if (contains(aluno, alunosA) && !contains(aluno, alunosC)) {
                result.append(aluno);
            }
        }
        return (String[]) result.selectAll();
    }

    /** b) alunos que fazem somente extensão */
    public String[] somenteExtensao() {

        ArrayList result = new ArrayList();
        for (String aluno : alunosC) {
            if (!contains(aluno, alunosA) && !contains(aluno, alunosB)) {
                result.append(aluno);
            }
        }
        return (String[]) result.selectAll();
    }

    /** c) alunos que fazem cultural ou extensão, mas não fazem esportiva */
    public String[] culturaOuExtensaoSemEsporte() {

        ArrayList result = new ArrayList();
        // incluir os de A que não estão em B
        for (String aluno : alunosA) {
            if (!contains(aluno, alunosB) && !contains(aluno, result)) {
                result.append(aluno);
            }
        }
        // incluir os de C que não estão em B, e que ainda não foram incluídos
        for (String aluno : alunosC) {
            if (!contains(aluno, alunosB) && !contains(aluno, result)) {
                result.append(aluno);
            }
        }
        return (String[]) result.selectAll();
    }
    //////////////////////
    
    public static void main(String[] args) {
        ArrayList culturaA = new ArrayList();
        culturaA.append("Alice");
        culturaA.append("Bob");
        culturaA.append("Carol");
        culturaA.append("David");

        ArrayList esporteB = new ArrayList();
        esporteB.append("Bob");
        esporteB.append("David");
        esporteB.append("Eve");
        esporteB.append("Frank");

        ArrayList extensaoC = new ArrayList();
        extensaoC.append("Carol");
        extensaoC.append("David");
        extensaoC.append("Frank");
        extensaoC.append("Gael");

        Questao2 q2 = new Questao2(culturaA, esporteB, extensaoC);
        System.out.println("a) Esporte + Cultura, sem Extensão: " + q2.culturaEEsporteSemExtensao());     // Bob
        System.out.println("b) Somente Extensão: " + q2.somenteExtensao());                               // Gael
        System.out.println("c) Cultura ou Extensão, sem Esporte: " + q2.culturaOuExtensaoSemEsporte());   // Alice, Caro, Gael
    }
}