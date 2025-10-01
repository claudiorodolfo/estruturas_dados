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
    
    public static void main(String[] args) {
        BinaryHeap filaPacientes = new BinaryHeap();

        filaPacientes.enqueue(new Patient("João", 30, 1));
        filaPacientes.enqueue(new Patient("Pedro", 40, 2));
        filaPacientes.enqueue(new Patient("Lucas", 28, 3));
        filaPacientes.enqueue(new Patient("Maria", 25, 2));
        filaPacientes.enqueue(new Patient("Ana", 35, 3));

        System.out.println("Atendendo pacientes por ordem de prioridade:");
        while (!filaPacientes.isEmpty()) {
            Patient p = (Patient) filaPacientes.dequeue();
            System.out.println("Paciente:" + p.name);
        }
    }
}
