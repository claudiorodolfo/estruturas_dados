public class Tipo1 {
    ///////////////////Questão 1
    public NoDuplo<T> getInicio() {
        return ponteiroInicio;
    }

    public Lista<T> intercalacao(Lista<T> listaA, Lista<T> listaB) {
        NoDuplo<T> p1 = listaA.getInicio();
        NoDuplo<T> p2 = listaB.getInicio();
        Lista<T> resultado = new Lista<>();

        while (p1 != null && p2 != null) {
            if (p1.getDado().compareTo(p2.getDado()) <= 0) {
                resultado.anexar(p1.getDado());
                p1 = p1.getProximo();
            } else {
                resultado.anexar(p2.getDado());
                p2 = p2.getProximo();
            }
        }

        // Anexa o que sobrou de qualquer lista
        while (p1 != null) {
            resultado.anexar(p1.getDado());
            p1 = p1.getProximo();
        }
        while (p2 != null) {
            resultado.anexar(p2.getDado());
            p2 = p2.getProximo();
        }
        return resultado;
    }

    ///////////////////Questão 2
    public int contarNaoFolhaImpares() {
        return contarNaoFolhaImparesRecursivo(raiz);
    }

    private int contarNaoFolhaImparesRecursivo(NoTriplo<Integer> nodo) {
        if (nodo == null) //caso base
            return 0;

        boolean naoFolha = (nodo.getEsquerda() != null || nodo.getDireita() != null);
        boolean impar = (nodo.getDado() % 2 != 0);

        int contador = 0;

        if (naoFolha && impar)
            contador++;

        return (contador + 
                contarNaoFolhaImparesRecursivo(nodo.getEsquerda()) +
                contarNaoFolhaImparesRecursivo(nodo.getDireita()));
    }

    ///////////////////Questão 3
    //Após inserir 70, 30, 52 e 47: 
    //[30,47,52,70]

    //Após inserir 22: 
    //        [47]
    //       /    \
    //[22,30]      [52,70]

    //Após inserir 60 e 80: 
    //        [47]
    //       /    \
    //[22,30]      [52,60,70,80]

    //Após inserir 50: 
    //        [47,60]
    //       /   |   \
    //[22,30] [50,52] [70,80]

    //Após inserir 10, 65, 55 e 48: 
    //              [47,60]
    //          /      |      \
    //[10,22,30] [48,50,52,55] [65,70,80]

    //Após inserir 49: 
    //             [47,50,60]
    //          /    /    \     \
    //[10,22,30] [48,49] [52,55] [65,70,80]

    //Após inserir 40: 
    //              [47,50,60]
    //             /  /    \       \
    //[10,22,30,40] [48,49] [52,55] [65,70,80]

    //Após inserir 25: 
    //               [25,47,50,60]
    //       /         /   |   \     \
    //[10,22] [30,40] [48,49] [52,55] [65,70,80]

    //Após apagar 10: 
    //                [47,50,60]
    //             /    /    \     \
    //[22,25,30,40] [48,49] [52,55] [65,70,80]

    //Após apagar 52: 
    //                [47,50,65]
    //             /    /    \     \
    //[22,25,30,40] [48,49] [55,60] [70,80]

    //Após inserir 45: 
    //            [30,47,50,65]
    //       /      /   |    \       \
    //[22,25] [40,45] [48,49] [55,60] [70,80]

    //Após inserir 32 e 38: 
    //                [30,47,50,65]
    //       /          /   |      \       \
    //[22,25] [32,38,40,45] [48,49] [55,60] [70,80]

    //Após inserir 42: 
    //                      [47]
    //                     /    \
    //                 [30,40][50,65]
    //       /       /       /\      \       \
    //[22,25] [32,38] [42,45] [48,49] [55,60] [70,80]

    ///////////////////Questão 4
    //Após inserir 70, 30, 52 e 47: 
    //[30,47,52,70]

    //Após inserir 22:  
    //       [47]
    //      /    \
    //[22,30]->[47,52,70]

    //Após inserir 60:  
    //       [47]
    //      /    \
    //[22,30]->[47,52,60,70]

    //Após inserir 80: 
    //        [47,60]
    //       /   |     \
    //[22,30]->[47,52]->[60,70,80]

    //Após inserir 50, 10, 65, 55: 
    //               [47,60]
    //           /      |       \
    //[10,22,30]->[47,50,52,55]->[60,65,70,80]

    //Após inserir 48: 
    //               [47,50,60]
    //          /      /    \         \
    //[10,22,30]->[47,48]->[50,52,55]->[60,65,70,80]

    //Após inserir 49 e 40: 
    //               [47,50,60]
    //          /      /    \         \
    //[10,22,30,40]->[47,48,49]->[50,52,55]->[60,65,70,80]

    //Após inserir 25: 
    //               [25,47,50,60]
    //       /         /     |        \           \
    //[10,22]->[25,30,40]->[47,48,49]->[50,52,55]->[60,65,70,80]

    //Após apagar 10: 
    //               [30,47,50,60]
    //       /         /     |     \           \
    //[22,25]->[30,40]->[47,48,49]->[50,52,55]->[60,65,70,80]

    //Após apagar 52: 
    //               [30,47,50,60]
    //       /         /     |     \        \
    //[22,25]->[30,40]->[47,48,49]->[50,55]->[60,65,70,80]

    //Após inserir 45 e 32: 
    //                       [30,47,50,60]
    //       /              /      |     \        \
    //[22,25]->[30,32,40,45]->[47,48,49]->[50,55]->[60,65,70,80]

    //Após inserir 38: 
    //                           [47]
    //                          /    \
    //                      [30,38][50,60]
    //       /         /          /\           \         \
    //[22,25]->[30,32]->[38,40,45]->[47,48,49]->[50,55]->[60,65,70,80]

    //Após inserir 42: 
    //                              [47]
    //                             /    \
    //                         [30,38][50,60]
    //       /         /             /\           \         \
    //[22,25]->[30,32]->[38,40,42,45]->[47,48,49]->[50,55]->[60,65,70,80]
}
