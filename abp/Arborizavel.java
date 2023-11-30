public interface Arborizavel<T> {

    NoTriplo<T> getRaiz();
    void inserir(T dado);
    void apagar(T dado);
    boolean existe(T dado);
    NoTriplo<T> buscar(T dado);     
    String imprimirPreOrdem(NoTriplo<T> raiz);
    String imprimirEmOrdem(NoTriplo<T> raiz);
    String imprimirPosOrdem(NoTriplo<T> raiz);
}