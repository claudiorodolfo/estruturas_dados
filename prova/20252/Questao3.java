// Acessar o diretório dos fontes:
// cd prova/20252
// Compilar e enviar os .class para a pasta out:
// javac -d out *.java
// Executar a partir desta pasta, usando out no classpath:
// java -cp out Questao3
public class Questao3 {

    class Patient {
        String name;
        int age;
        int risk;
    
        public Patient(String name, int age, int risk) {
            this.name = name;
            this.age = age;
            this.risk = risk;
            setPriority(risk);
        }

        //////////////////////
        // Só precisavam implementar estes códigos na prova.
        // Coloquei toda a estrutura para facilitar a compreensão.
        long priority;
        static long counter = 0;

        public void setPriority(int risk) {
            this.priority = priorityAdjustment(risk);
        }
    
        public long priorityAdjustment(int risk) {
            int incremento = 999_999 - counter;
            counter++;
            return risk * 1_000_000 + incremento;
        }
        //////////////////////
    }
    
    void main(){
        BinaryHeap filaPacientes = new BinaryHeap();

        filaPacientes.enqueue(new Patient("João", 30, 1));
        filaPacientes.enqueue(new Patient("Pedro", 40, 2));
        filaPacientes.enqueue(new Patient("Lucas", 28, 3));
        filaPacientes.enqueue(new Patient("Maria", 25, 2));
        filaPacientes.enqueue(new Patient("Ana", 35, 3));

        IO.println("Atendendo pacientes por ordem de prioridade:");
        while (!filaPacientes.isEmpty()) {
            Patient p = (Patient) filaPacientes.dequeue();
            IO.println("Paciente:" + p.name);
        }
    }
}
