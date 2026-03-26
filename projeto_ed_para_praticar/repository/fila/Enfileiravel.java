package repository.fila;

//Queueable
public interface Enfileiravel {
     //Métodos principais
    void enfileirar(Object dado);           //C - enqueue
    Object frente();                        //R - front
    void atualizarInicio(Object novoDado);  //U - updateFront
    void atualizarFim(Object novoDado);     //U - updateRear
    Object desenfileirar();                 //D - dequeue
    
    //Métodos auxiliares
    boolean estaVazia();                 //isEmpty
    boolean estaCheia();                 //isFull
    String imprimir();                   //print
}