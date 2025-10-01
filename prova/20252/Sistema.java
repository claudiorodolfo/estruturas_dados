// Cliente
class Client {
    int clientCPF;
    String name;
    public Client(int clientCPF, String name) {
        this.clientCPF = clientCPF;
        this.name = name;
    }
}

 // Pedido
class Order {
    int clientCPF;
    double value;
    public Order(int clientCPF, double value) {
        this.clientCPF = clientCPF;
        this.value = value;
    }
}

public class Sistema {
    private Client[] clientes;
    private Order[] pedidos;

    public Sistema(ArrayList clientes, ArrayList pedidos) {
        this.clientes = (Client[]) clientes.selectAll();
        this.pedidos = (Order[]) pedidos.selectAll();
    }

    //////////////////////
    // Só precisavam implementar este método na prova.
    // Coloquei toda a estrutura para facilitar a compreensão.
    public float masterDetail(String name) {
        // procura CPF do cliente com nome “name”
        Integer cpfBusca = null;
        for (Client c: clientes)
            if (c.name.equals(name)) {
                cpfBusca = c.clientCPF;
                break;
            }

        float soma = 0.0;
        if (cpfBusca != null) {
            for (Order o : pedidos)
                if (o.clientCPF == cpfBusca)
                    soma += o.value;
        } 
        return soma;
    }
    //////////////////////
    
    public static void main(String[] args) {
        ArrayList clientes = new ArrayList(10);
        ArrayList pedidos = new ArrayList(10);

        clientes.append(new Client(123, "J"));
        clientes.append(new Client(156, "M"));
        clientes.append(new Client(246, "A"));

        pedidos.append(new Order(123, 100.0));
        pedidos.append(new Order(156, 1000.5));
        pedidos.append(new Order(156, 200.0));
        pedidos.append(new Order(123, 300.3));
        pedidos.append(new Order(123, 10.2));

        Sistema sys = new Sistema(clientes, pedidos);
        System.out.println("Total para J: " + sys.masterDetail("J")); // espera 410.5
        System.out.println("Total para M: " + sys.masterDetail("M")); // espera 1200.5
        System.out.println("Total para A: " + sys.masterDetail("A")); // espera 0.0
    }
}