package repository.fila;
    
//Dequeable
public interface DuplamenteEnfileiravel extends Enfileiravel {
    public void enfileirarInicio(Object objeto);//C - enqueueFront
    public Object tras();                       //R - rear
    public Object desenfileirarFim();           //D - dequeueRear
    public String imprimirTrasPraFrente();      //printRearToFront
}