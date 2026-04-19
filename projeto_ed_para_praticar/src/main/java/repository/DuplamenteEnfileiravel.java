package repository;
    
//Dequeable
public interface DuplamenteEnfileiravel extends Enfileiravel {
     //Métodos principais
    public void enfileirarInicio(Object objeto);//C - enqueueFront
    public Object tras();                       //R - rear
    public Object desenfileirarFim();           //D - dequeueRear
    
    //Métodos auxiliares
    public String imprimirTrasPraFrente();      //printRearToFront
}