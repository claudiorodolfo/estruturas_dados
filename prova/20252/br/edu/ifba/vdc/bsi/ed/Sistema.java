// Acessar o diretório dos fontes:
// cd prova/20252
// Compilar e enviar os .class para a pasta bin:
// javac --release 25 -d bin br/edu/ifba/vdc/bsi/ed/*.java
// Executar a partir desta pasta, usando bin no classpath:
// java -cp bin br.edu.ifba.vdc.bsi.ed.Sistema
// Cliente
package br.edu.ifba.vdc.bsi.ed;

record Client(int clientCPF, String name) {}

 // Pedido
record Order(int clientCPF, float value) {}

public class Sistema {
    private Client[] clientes;
    private Order[] pedidos;

    public Sistema(Client[] clientes, Order[] pedidos) {
        this.clientes = clientes;
        this.pedidos = pedidos;
    }

    //////////////////////
    // Só precisavam implementar este método na prova.
    // Coloquei toda a estrutura para facilitar a compreensão.
    public float masterDetail(String name) {
        // procura CPF do cliente com nome “name”
        Integer cpfBusca = null;
        for (Client c: clientes)
            if (c.name().equals(name)) {
                cpfBusca = c.clientCPF();
                break;
            }

        float soma = 0.0f;
        if (cpfBusca != null) {
            for (Order o : pedidos)
                if (o.clientCPF() == cpfBusca)
                    soma += o.value();
        } 
        return soma;
    }
    //////////////////////
    
    void main() {
        Client[] clientes = {
            new Client(123, "J"),
            new Client(156, "M"),
            new Client(246, "A")
        };
        Order[] pedidos = {
            new Order(123, 100.0f),
            new Order(156, 1000.5f),
            new Order(156, 200.0f),
            new Order(123, 300.3f),
            new Order(123, 10.2f)
        };

        Sistema sys = new Sistema(clientes, pedidos);
        IO.println("Total para J: " + sys.masterDetail("J")); // espera 410.5
        IO.println("Total para M: " + sys.masterDetail("M")); // espera 1200.5
        IO.println("Total para A: " + sys.masterDetail("A")); // espera 0.0
    }
}
