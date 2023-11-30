public interface Arborizavel<T> {

    NoTriplo<T> getRaiz();
    void inserir(T dado);
    NoTriplo<T> apagar(NoTriplo<T> raiz, T dado);
    boolean existe(T dado);
    NoTriplo<T> buscar(T dado);     
    String imprimirPreOrdem(NoTriplo<T> raiz);
    String imprimirEmOrdem(NoTriplo<T> raiz);
    String imprimirPosOrdem(NoTriplo<T> raiz);
}