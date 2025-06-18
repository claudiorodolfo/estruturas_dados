//Questão 1
public class VerificaBalanceamento {

    public boolean estaBemFormado(String s) {
        Empilhavel pilha = new PilhaEstatica(60);
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pilha.empilhar(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pilha.estaVazia()) return false;
                char t = pilha.desempilhar();
                if ((c == ')' && t != '(') ||
                    (c == '}' && t != '{') ||
                    (c == ']' && t != '['))
                    return false;
            }
        }
        return pilha.estaVazia();
    }

    public static void main(String[] args) {
        String s1 = "{(()[({})])}";
        String s2 = "([)]";
        VerificaBalanceamento vb = new VerificaBalanceamento();
        System.out.println(vb.estaBemFormado(s1)); // true
        System.out.println(vb.estaBemFormado(s2)); // false
    }
}


//Questão 2
public class OperacoesConjunto {

    // Método auxiliar para verificar se um elemento está presente em um array
    private boolean contem(int[] array, int valor, int limite) {
        for (int i = 0; i < limite; i++) {
            if (array[i] == valor) {
                return true;
            }
        }
        return false;
    }

    private int[] copiarArray(int[] original, int tamanho) {
        // Cria um novo array com elementos do array original, até o tamanho passado
        int[] copia = new int[tamanho];

        // Copia cada elemento do array original para o novo array
        for (int i = 0; i < tamanho; i++) {
            copia[i] = original[i];
        }
        return copia;
    }

    // Diferença: elementos de 'a' que não estão em 'b'
    public int[] difference(int[] listaA, int[] listaB) {
        int[] resultado = new int[listaA.length];
        int cont = 0;

        for (int i = 0; i < listaA.length; i++) {
            if (!contem(resultado, listaA[i], cont) && 
                    !contem(listaB, listaA[i], listaB.length)) {
                resultado[cont++] = listaA[i];
            }
        }

        return copiarArray(resultado, cont);
    }

    // União: todos os elementos de 'a' e 'b', sem duplicatas
    public int[] union(int[] listaA, int[] listaB) {
        int[] resultado = new int[listaA.length + listaB.length];
        int cont = 0;

        for (int i = 0; i < listaA.length; i++) {
            if (!contem(resultado, listaA[i], cont)) {
                resultado[cont++] = listaA[i];
            }
        }

        for (int i = 0; i < listaB.length; i++) {
            if (!contem(resultado, listaB[i], cont)) {
                resultado[cont++] = listaB[i];
            }
        }

        return copiarArray(resultado, cont);
    }

    // Interseção: elementos comuns entre 'a' e 'b'
    public int[] intersection(int[] listaA, int[] listaB) {
        int[] resultado = new int[Math.min(listaA.length, listaB.length)];
        int cont = 0;

        for (int i = 0; i < listaA.length; i++) {
            if (!contem(resultado, listaA[i], cont) && 
                    contem(listaB, listaA[i], listaB.length)) {
                resultado[cont++] = listaA[i];
            }
        }

        return copiarArray(resultado, cont);
    }

    public static void main(String[] args) {
        int[] lista1 = {1, 2, 3, 4};
        int[] lista2 = {3, 4, 5, 6};

        OperacoesConjunto oc = new OperacoesConjunto();
        System.out.println("Diferença: ");
        System.out.println(java.util.Arrays.toString(oc.difference(lista1, lista2)));
        System.out.println("União: ");
        System.out.println(java.util.Arrays.toString(oc.union(lista1, lista2)));
        System.out.println("Interseção: ");
        System.out.println(java.util.Arrays.toString(oc.intersection(lista1, lista2)));
    }
}