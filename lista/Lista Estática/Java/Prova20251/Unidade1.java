//Questão 1

public interface ControleReservasAereas {
    /**
     * Cria uma nova reserva. recebe um passageiro (cpf, nome) e as informações 
     * do voo, a classe da passage (econômica, executiva, primeira classe) e retorna a 
     * reserva criada.
     */
    Reserva criaReserva(Passageiro passageiro, String origem, String destino, 
            LocalDate dataVoo, LocalTime horarioVoo, ClassePassagem classePassagem);

    /**
     * Adiciona uma nova reserva à lista. Recebe uma Reserva 
     * contendo os dados da passagem e retorna true se a reserva 
     * foi adicionada com sucesso; false se a lista estiver cheia.
     */
    boolean adicionarReserva(Reserva reserva);

    /**
     * Remove uma reserva da lista pelo cpf do passageiro. Retorna
     * true se a reserva foi encontrada e removida; false caso 
     * contrário.
     */
    boolean removerReserva(Passageiro passageiro);

    /**
     * Atualiza uma reserva da lista pelo cpf do passageiro. Retorna
     * true se a reserva foi encontrada e atualizada; false caso 
     * contrário.
     */    
    boolean atualizarReserva(Passageiro passageiro, Reserva novaReserva);

    /**
     * Busca uma reserva pelo cpf do passageiro. Retorna a Reserva
     * correspondente, ou null se não encontrado.
     */
    Reserva buscarReserva(Passageiro Passageiro);

    /**
     * Busca uma reserva pelo destino e retorna as Reservas
     * correspondentes.
     */    
    Reserva[] buscarReservasPorDestino(String destino);

    /**
     * Busca reservas por classe da passagem, e retorna as Reservas
     * correspondentes.
     */
    Reserva[] buscarReservasPorClasse(ClassePassagem classe);
    
    /**
     * Lista todas as reservas cadastradas. Retorna um array 
     * contendo todas as reservas ativas.
     */
    Reserva[] listarReservas();

    /**
     * Retorna o número total de reservas cadastradas. Retorna a 
     * quantidade atual de reservas na lista.
     */
    int quantidadeReservas();
}

//Questão 2
public class Questao2 {
    public String decToBin(String data) {
        int dividendo = Integer.parseInt(data);
        if (dividendo == 0) {return "0"; }
    
        Empilhavel pilha = new PilhaEstatica(32);
        while (dividendo != 0) {
            int resto = dividendo % 2;
            int quociente = dividendo / 2;
            dividendo = quociente;
            pilha.empilhar(resto);
        }
        return pilha.imprimir();
    }
    
    public static void main(String[] args) {
        Questao2 q2 = new Questao2();
        System.out.println(q2.decToBin("10"));
        System.out.println(q2.decToBin("255"));
        System.out.println(q2.decToBin("35"));
    }
}

//Questão 3
/*
a) Incorreta. A expressão correta seria: Ins = (Ins + 1) % N; A 
forma (Ins MOD N)+1 pode fazer Ins ultrapassar o limite superior 
do array se Ins já for N-1. Por exemplo, (5 MOD 6) + 1 = 6, o que 
está fora do intervalo válido 0..5.

b) Correta. Para deleção, caso a fila não esteja vazia, atribuímos
a Prim o valor de ((Prim+1) MOD N), pois, após remover o elemento 
da posição Prim, devemos avançar o índice Prim para a próxima 
posição, usando a indexação circular: Prim = (Prim + 1) % N;

c) Incorreta. Mesmo que Prim=Ins realmente indique corretamente 
quando a fila está vazia, podemos concluir errôneamenteque que a 
fila está vazia, quando na verdade ela estiver cheia, porque as 
condições são equivalentes, gerando assim ambiguidade.

d) Incorreta. Prim = ((Ins + 1) MOD N) não indica quando a fila está cheia, 
pois quando a fila está cheia Prim=Ins. Prim = ((Ins + 1) MOD N) indicaria 
quando faltasse apenas uma posiçao livre antes que a fila se torne cheia. 
*/

//Questão 4
public class Questao4 {

    public enum Order {ASCENDING, DESCENDING};
    void sort(Order by, int[] data) {
        for (int i = 0; i < data.length-1; i++) {
            for (j = 0; j < (data.length-1)-i; j++) {
                if ((by == Order.ASCENDING && data[i] > data[i + 1]) ||
                        (by == Order.DESCENDING && data[i] < data[i + 1])) {
                    val temp = dados[i];
                    dados[i] = dados[i+1];
                    dados[i+1] = temp;
                } 
            }
        }
    }
    
    public static void main(String[] args) {
        Questao4 q4 = new Questao4();
        int[] dados = {5, 7, 2, 6, 1, 4, 3, 0, 9, 8};

        q4.sort(Order.ASCENDING, dados);
        for (int num : dados) {System.out.print(num + " ");}

        q4.sort(Order.DESCENDING, dados);
        for (int num : dados) {System.out.print(num + " ");} 
    }
}
