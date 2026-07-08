public class Questao1 {
    //Tipo 1
    public NoTriplo rotacaoDireita(NoTriplo b) {
        // T1 e T3 não sofrem alteração
        NoTriplo T0 = b.getGenitor();
        NoTriplo a  = b.getEsquerda();
        NoTriplo T2 = a.getDireita();

        //corrige ponteiros, descendo e subindo
        //[T0-->a], [T0<--a]
        if (T0 != null)
            if (b.equals(T0.getEsquerda()))
                T0.setEsquerda(a);
            else
                T0.setDireita(a);

        x.setGenitor(T0);

        //[a-->b], [a<--b]
        a.setDireita(b);
        b.setGenitor(a);   

        //[b-->T2], [b<--T2]
        y.setEsquerda(T2);        
        if (T2 != null)
            T2.setGenitor(b);

        atualizaAltura(b);
        atualizaAltura(a);
        //nova raiz é a
        return a;
    }
    //Tipo 2
    public NoTriplo rotacaoEsquerda(NoTriplo b) {
        // T1 e T3 não sofrem alteração
        NoTriplo<T> T0 = b.getGenitor();        
        NoTriplo<T> c  = b.getDireita();
        NoTriplo<T> T2 = c.getEsquerda();

        //corrige ponteiros, descendo e subindo
        //[T0-->c], [T0<--c]
        if (T0 != null)        
            if (b.equals(T0.getEsquerda()))
                T0.setEsquerda(c);
            else
                T0.setDireita(c);
                
        x.setGenitor(T0);
        
        //[c-->b], [c<--b]
        c.setEsquerda(b);
        b.setGenitor(c); 

        //[b-->T2], [b<--T2]
        y.setDireita(T2);        
        if (T2 != null)
            T2.setGenitor(b);

        // Atualiza as alturas
        atualizaAltura(b);
        atualizaAltura(c);
        // Retorna a nova raiz
        return c;        
    }

    //Tipo 3
    public int calcularAltura(NoTriplo raiz) {
        if(raiz == null)
            return -1;
    
        int hEsquerda = calcularAltura(raiz.getEsquerda());
        int hDireita = calcularAltura(raiz.getDireita());
    
        return Math.max(hEsquerda, hDireita) + 1;
    }
}
