package repository;

//Listable
public interface Listavel {
    //Métodos principais
    void inserir(Object objeto, int posicao);   //C - insert
    void anexar(Object objeto);                 //C - append
    Object selecionar(int posicao);             //R - select
    Object[] selecionarTodos();                 //R - selectAll
    void atualizar(Object objeto, int posicao); //U - update
    Object apagar(int posicao);                 //D - delete
    void limpar();                              //D - clear

    //Métodos auxiliares
    int tamanho();
    boolean estaVazia();
    boolean estaCheia();
    String imprimir();
}
