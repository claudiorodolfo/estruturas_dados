public class Tipo2 {
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
    public int contarNaoFolhaPares() {
        return contarNaoFolhaParesRecursivo(raiz);
    }

    private int contarNaoFolhaParesRecursivo(NoTriplo<Integer> nodo) {
        if (nodo == null)  //caso base
            return 0;

        boolean naoFolha = (nodo.getEsquerda() != null || nodo.getDireita() != null);
        boolean par = (nodo.getDado() % 2 == 0);

        int contador = 0;

        if (naoFolha && par)
            contador++;

        return (contador + 
                contarNaoFolhaParesRecursivo(nodo.getEsquerda()) +
                contarNaoFolhaParesRecursivo(nodo.getDireita()));
    }

    ///////////////////Questão 3
    //Após inserir 72, 32, 54 e 49: 
    //[32,49,54,72]

    //Após inserir 24: 
    //        [49]
    //       /    \
    //[24,32]      [54,72]

    //Após inserir 62 e 82: 
    //        [49]
    //       /    \
    //[24,32]      [54,62,72,82]

    //Após inserir 52: 
    //        [49,62]
    //       /   |    \
    //[24,32] [52,54]  [72,82]

    //Após inserir 12, 67, 57 e 50: 
    //              [49,62]
    //          /      |      \
    //[12,24,32] [50,52,54,57] [67,72,82]

    //Após inserir 51: 
    //             [49,52,62]
    //          /    /    \     \
    //[12,24,32] [50,51] [54,57] [67,72,82]

    //Após inserir 42: 
    //                [49,52,62]
    //             /    /    \     \
    //[12,24,32,42] [50,51] [54,57] [67,72,82]

    //Após inserir 27: 
    //               [27,49,52,62]
    //        /       /    |   \     \
    //[12,24] [32,42] [50,51] [54,57] [67,72,82]

    //Após apagar 12: 
    //                [49,52,62]
    //             /    /    \     \
    //[24,27,32,42] [50,51] [54,57] [67,72,82]

    //Após apagar 54: 
    //                [49,52,67]
    //             /    /    \     \
    //[24,27,32,42] [50,51] [57,62] [72,82]

    //Após inserir 47: 
    //             [32,49,52,67]
    //       /       /   |   \       \
    //[24,27] [42,47] [50,51] [57,62] [72,82]

    //Após inserir 33 e 40: 
    //                [32,49,52,67]
    //       /          /   |      \       \
    //[24,27] [33,40,42,47] [50,51] [57,62] [72,82]

    //Após inserir 44: 
    //                      [49]
    //                     /    \
    //                 [32,42][52,67]
    //       /       /       /\      \       \
    //[24,27] [33,40] [44,47] [50,51] [57,62] [72,82]

    ///////////////////Questão 4
    //Após inserir 72, 32, 54 e 49: 
    //[32,49,54,72]

    //Após inserir 24:  
    //       [49]
    //      /    \
    //[24,32]->[49,54,72]

    //Após inserir 62:  
    //       [49]
    //      /    \
    //[24,32]->[49,54,62,72]

    //Após inserir 82: 
    //        [49,62]
    //       /   |     \
    //[24,32]->[49,54]->[62,72,82]

    //Após inserir 52, 12, 67, 57: 
    //               [49,62]
    //           /      |       \
    //[12,24,32]->[49,52,54,57]->[62,67,72,82]

    //Após inserir 50: 
    //               [49,52,62]
    //          /      /    \         \
    //[12,24,32]->[49,50]->[52,54,57]->[62,67,72,82]

    //Após inserir 51 e 42: 
    //               [49,52,62]
    //          /      /    \         \
    //[12,24,32,42]->[49,50,51]->[52,54,57]->[62,67,72,82]

    //Após inserir 27: 
    //               [27,49,52,62]
    //       /         /     |        \           \
    //[12,24]->[27,32,42]->[49,50,51]->[52,54,57]->[62,67,72,82]

    //Após apagar 12: 
    //               [32,49,52,62]
    //       /         /     |     \           \
    //[24,27]->[32,42]->[49,50,51]->[52,54,57]->[62,67,72,82]

    //Após apagar 54: 
    //               [32,49,52,62]
    //       /         /     |     \        \
    //[24,27]->[32,42]->[49,50,51]->[52,57]->[62,67,72,82]

    //Após inserir 47 e 33: 
    //                       [32,49,52,62]
    //       /              /      |     \        \
    //[24,27]->[32,33,42,47]->[49,50,51]->[52,57]->[62,67,72,82]

    //Após inserir 40: 
    //                           [49]
    //                          /    \
    //                      [32,40][52,62]
    //       /         /          /\           \         \
    //[24,27]->[32,33]->[40,42,47]->[49,50,51]->[52,57]->[62,67,72,82]

    //Após inserir 44: 
    //                              [49]
    //                             /    \
    //                         [32,40][52,62]
    //       /         /             /\           \         \
    //[24,27]->[32,33]->[40,42,44,47]->[49,50,51]->[52,57]->[62,67,72,82]
}
